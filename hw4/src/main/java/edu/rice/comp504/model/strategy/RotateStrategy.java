package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.CollisionStore;
import edu.rice.comp504.model.paintobj.APaintObject;
import edu.rice.comp504.model.paintobj.Fish;

import java.awt.*;

public class RotateStrategy implements IUpdateStrategy{
    private static IUpdateStrategy ONLY;
    private static Point dims;

    private RotateStrategy() {
        dims = CollisionStore.getCanvasDims();
    }

    public static IUpdateStrategy initiate(){
        if(ONLY == null){
            ONLY = new RotateStrategy();
        }
        return ONLY;
    }

    @Override
    public String getName() {
        return "rotate";
    }

    @Override
    public void updateState(APaintObject context) {
        context.detectCollisionBoundary();
        Point center = new Point(dims.x / 2, dims.y / 2);
        double degree = Math.PI * 11 / 23;
        Point tempPos = context.getLocation();
        Point temVel = context.getVelocity();
        double dire = temVel.x / (Math.abs(temVel.x) == 0 ? 1 : (Math.abs(temVel.x))); //clockwise is negative
        double newX =  center.x + (tempPos.x - center.x) * Math.sin(degree * dire) + (tempPos.y - center.y) * Math.cos(degree * dire);
        double newY =  center.y - (tempPos.x - center.x) * Math.cos(degree * dire) + (tempPos.y - center.y) * Math.sin(degree * dire);
        context.setLocation(new Point ((int) newX, (int) newY));
    }
}
