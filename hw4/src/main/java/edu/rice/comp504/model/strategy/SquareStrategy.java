package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.CollisionStore;
import edu.rice.comp504.model.paintobj.APaintObject;

import java.awt.*;

public class SquareStrategy implements IUpdateStrategy{
    private static IUpdateStrategy ONLY;
    private static Point dims;

    private SquareStrategy() {
        dims = CollisionStore.getCanvasDims();
    }

    public static IUpdateStrategy initiate(){
        if(ONLY == null){
            ONLY = new SquareStrategy();
        }
        return ONLY;
    }

    @Override
    public String getName() {
        return "square";
    }

    @Override
    public void updateState(APaintObject context) {
        context.detectCollisionBoundary();
        int currStep = context.getStep();
        Point tempPos = context.getLocation();
        Point tempVel = context.getVelocity();

        if(currStep < 6){
            context.setLocation(new Point(tempPos.x + tempVel.x, tempPos.y));
        }else if(currStep >= 6 && currStep < 18){
            context.setLocation(new Point(tempPos.x , tempPos.y + tempVel.y));
        }else if(currStep >= 18 && currStep < 24){
            context.setLocation(new Point(tempPos.x - tempVel.x, tempPos.y));
        }else if(currStep >= 24 && currStep < 36){
            context.setLocation(new Point(tempPos.x , tempPos.y - tempVel.y));
        }

        context.setStep(currStep + 1);

        if(currStep >= 36){
            context.setStep(0);
        }

    }
}
