package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.BallWorldStore;
import edu.rice.comp504.model.ball.Ball;

import java.awt.*;

public class RotateStrategy implements IUpdateStrategy{
    private static IUpdateStrategy ONLY;
    private static Point dims;

    private RotateStrategy() {
        dims = BallWorldStore.getCanvasDims();
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
    public void updateState(Ball context) {
        Point center = new Point(dims.x / 2, dims.y / 2);
        Point tempPos = context.getLocation();
        Point temVel = context.getVelocity();
        double newX =  center.x + (tempPos.x - center.x) * Math.sin(Math.PI * 3 / 7) + (tempPos.y - center.y) * Math.cos(Math.PI * 3 / 7);
        double newY =  center.y - (tempPos.x - center.x) * Math.cos(Math.PI * 3 / 7) + (tempPos.y - center.y) * Math.sin(Math.PI * 3 / 7);
        context.setLocation(new Point ((int) newX, (int) newY));
    }
}
