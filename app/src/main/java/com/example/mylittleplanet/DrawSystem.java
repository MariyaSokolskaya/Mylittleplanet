package com.example.mylittleplanet;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Михаил on 05.04.2017.
 */

public class DrawSystem extends View {
    ArrayList<Planet> listPlanet = new ArrayList<>();
    Sun sun;
    boolean needDraw = true, needPlanet = true;
    int countTouch = 0;
    MyTimer timer;

    public DrawSystem(Context context, AttributeSet attrs) {
        super(context, attrs);
        timer = new MyTimer();
        timer.start();
    }

    public DrawSystem(Context context) {
        super(context);
        timer = new MyTimer();
        timer.start();

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        setBackgroundColor(Color.BLACK);
        sun = new Sun(150, Color.YELLOW, canvas.getWidth() / 2, canvas.getHeight() / 2);
        sun.drawSun(canvas);
        if(needPlanet){
            listPlanet.add(new Planet(3, 57, 180, 23.5, Color.GRAY, sun));//Меркурий
            listPlanet.add(new Planet(9, 108, 225, 17.5,Color.CYAN, sun));//Венера
            listPlanet.add(new Planet(9.9, 149, 90, 14.5, Color.GREEN, sun));//Земля
            listPlanet.add(new Planet(5.4, 200, 0, 12, Color.RED, sun));//Марс
            listPlanet.add(new Planet(71.4, 300, 20, 6.5, Color.MAGENTA, sun));//Юпитер

            needPlanet = false;}
            for (int i = 0; i < listPlanet.size(); i++) {
                listPlanet.get(i).drawPlanet(canvas);

            }
        }








    public void repeatDraw(){
        invalidate();
    }

/*    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == event.ACTION_DOWN && countTouch % 2 == 1) {
            countTouch++;
            needDraw = false;
        }
        if(event.getAction() == event.ACTION_DOWN && countTouch % 2 == 0) {
            countTouch++;
            needDraw = true;
            repeatDraw();
        }
        return true;
    }*/


    class MyTimer extends CountDownTimer {

        MyTimer(){
            super(1000000, 30);
        }
        @Override
        public void onTick(long millisUntilFinish){
            repeatDraw();
        }
        @Override
        public void onFinish(){}
    }


}
