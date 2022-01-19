package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.CollisionStore;
import edu.rice.comp504.model.paintobj.APaintObject;

import java.awt.*;

public class TornadoStrategy implements IUpdateStrategy {
    private static IUpdateStrategy ONLY;
    private Point dims;

    private TornadoStrategy() {
        dims = CollisionStore.getCanvasDims();
    }

    public static IUpdateStrategy initiate(){
        if(ONLY == null){
            ONLY = new TornadoStrategy();
        }
        return ONLY;
    }

    @Override
    public String getName() {
        return "tornado";
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
        double newX = tempPos.x + tempVel.x * 10 * Math.cos(currStep * Math.PI / 6);
        newX = newX >= context.getRadius() ? newX : context.getRadius();
        newX = newX <= dims.x - context.getRadius() ? newX : dims.x - context.getRadius();
        context.setLocation(new Point((int) (newX), tempPos.y + tempVel.y ));
        context.setStep(currStep + 1);
    }
}
