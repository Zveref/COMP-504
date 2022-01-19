package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paintobj.APaintObject;

import java.awt.*;

public class ShakingStrategy implements IUpdateStrategy{
    private static IUpdateStrategy ONLY;

    private ShakingStrategy() {

    }

    public static IUpdateStrategy initiate(){
        if(ONLY == null){
            ONLY = new ShakingStrategy();
        }
        return ONLY;
    }

    @Override
    public String getName() {
        return "shaking";
    }

    @Override
    public void updateState(APaintObject context) {
        context.detectCollisionBoundary();
        int currStep = context.getStep();
        Point tempPos = context.getLocation();
        Point tempVel = context.getVelocity();
        if(context.detectCollisionBoundary() == true){
            context.setStep(6 - currStep);
        }else if(currStep >= 6){
            context.setStep(0);
        }else if(currStep == 3){
            context.setVelocity(new Point(tempVel.x, - tempVel.y));
        }
        int nextY = (int) (tempPos.y + tempVel.y * 10 * Math.cos(currStep * Math.PI / 6));
        nextY = nextY >= context.getRadius() ? nextY : context.getRadius();
        nextY = nextY <= 800 - context.getRadius() ? nextY : 800 - context.getRadius();
        context.setLocation(new Point(tempPos.x, nextY));
        context.setStep(currStep + 1);
    }
}
