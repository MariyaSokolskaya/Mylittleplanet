package com.example.mylittleplanet;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Михаил on 05.04.2017.
 */

public class Planet {
    double distance;//Данные орбиты
    int xNext, yNext;
    double r;//Характеристика положения планеты и ее радиуса
    double angle ;//угол начального расположения
    double avelocity;//скорость вращения (коэффициент)
    Planet parent;
    Paint paint = new Paint();
    int color;

    public Planet(double r, double distance, double angle, double avelocity, int color, Planet parent){
        this.angle = Math.PI*angle/180;
        this.avelocity = avelocity;
        if (parent != null) {
            xNext = (int) (distance * Math.cos(angle)) + parent.getxNext();
            yNext = (int) (distance * Math.sin(angle)) + parent.getyNext();
            this.distance = distance+parent.r;
            this.avelocity = avelocity + parent.avelocity;
        }
        else{
            xNext = (int) (distance * Math.cos(angle));
            yNext = (int) (distance * Math.sin(angle));
            this.distance = distance;
            this.avelocity = avelocity;
        }
        this.r = r;
        this.color = color;
        this.parent = parent;
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(color);
    }

    public void drawPlanet(Canvas canvas){
        newCoord();
        canvas.drawCircle(xNext, yNext, (float)r, paint);
    }

    public void newCoord(){
        angle -= avelocity/distance;//(2*Math.PI*distance/360)*(Math.PI/180);
        xNext = (int)(distance * Math.cos(angle)) + parent.getxNext();
        yNext = (int)(distance * Math.sin(angle)) + parent.getyNext();
    }

    public void setxNext(int xNext) {
        this.xNext = xNext;
    }

    public void setyNext(int yNext) {
        this.yNext = yNext;
    }

    public int getxNext() { return xNext; }

    public int getyNext() {
        return yNext;
    }
}

class Sun extends Planet{
    //double x, y;
    public Sun(double r, int color, int x, int y) {
        super(r, 0, 0, 0, color, null);
        //this.x = x;
        //this.y = y;
        xNext = x;
        yNext = y;
    }

    // public double getX() { return xNext; }

    // public double getY() { return yNext; }

    public  void drawSun(Canvas canvas){
        paint.setColor(Color.YELLOW);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle((float)xNext, (float)yNext, (float)r, paint);
    }
}
