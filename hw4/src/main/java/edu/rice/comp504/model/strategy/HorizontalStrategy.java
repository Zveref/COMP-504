package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.CollisionStore;
import edu.rice.comp504.model.paintobj.APaintObject;

import java.awt.*;

public class HorizontalStrategy implements IUpdateStrategy{
    private static IUpdateStrategy ONLY;

    private HorizontalStrategy() {

    }

    public static IUpdateStrategy initiate(){
        if(ONLY == null){
            ONLY = new HorizontalStrategy();
        }
        return ONLY;
    }

    @Override
    public String getName() {
        return "horizontal";
    }

    @Override
    public void updateState(APaintObject context) {
        context.detectCollisionBoundary();
        Point tempPos = context.getLocation();
        Point tempVel = context.getVelocity();
            context.setLocation(new Point(tempPos.x + tempVel.x,tempPos.y));
    }
}
