package edu.rice.comp504.util;

import edu.rice.comp504.model.paintobj.APaintObject;

import java.awt.*;

public class CalcuUtil {
    public static double getDis(Point a, Point b){
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }

    //For the sake of accuracy, all set double
    public static double getCos(Point a, Point b){
        return getDis(a, b) == 0 ? 0 : (a.x - b.x) / getDis(a, b);
    }

    public static double getSin(Point a, Point b){
        return getDis(a, b) == 0 ? 0 : (a.y - b.y) / getDis(a, b);
    }

    public static double getSpeed(APaintObject obj){
        return  Math.sqrt(Math.pow(obj.getVelocity().x, 2) + Math.pow(obj.getVelocity().y, 2));
    }

}
