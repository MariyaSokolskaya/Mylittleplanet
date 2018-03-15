package com.example.mylittleplanet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Main2Activity extends AppCompatActivity {
    DrawSystem myView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myView = new DrawSystem(this);
        myView.repeatDraw();
        setContentView(myView);
    }
}
