package com.example.mylittleplanet;

import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static android.media.AudioManager.STREAM_MUSIC;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private AudioPlayer mPlayer = new AudioPlayer();
    private SoundPool mSoundPool;
    private int mSoundId = 1;
    private int mStreamId;



    Button but1;
    Button but2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPlayer.play(this);
        mSoundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 100);
        mSoundPool.load(this, R.raw.start, 1);

         but1 = (Button) findViewById(R.id.buttons);
         but2 = (Button) findViewById(R.id.button);

        but1.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                                        float curVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                                        float maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
                                        float leftVolume = curVolume / maxVolume;
                                        float rightVolume = curVolume / maxVolume;
                                        int priority = 1;
                                        int no_loop = 0;
                                        float normal_playback_rate = 1f;
                                        mStreamId = mSoundPool.play(mSoundId, leftVolume, rightVolume, priority, no_loop,
                                                normal_playback_rate);

                                    }
                                }




);
        but2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(MainActivity.this,

                Main2Activity.class));
    }


    public class AudioPlayer {

        private MediaPlayer mPlayer;

        public void stop() {

            if (mPlayer != null){

                mPlayer.release();

                mPlayer = null;

            }

        }
        public void play(Context c){
            stop();
            mPlayer = MediaPlayer.create(c, R.raw.song);
            mPlayer.setOnCompletionListener ( new MediaPlayer.OnCompletionListener() {

                public void onCompletion(MediaPlayer mp) {
                    stop();
                }
            });

            mPlayer.start();
        }

    }


    @Override

    public void onDestroy() {

        super.onDestroy();

        mPlayer.stop();

    }

    @Override
    protected void onStop() {
        mPlayer.stop();
        super.onStop();
    }
}
