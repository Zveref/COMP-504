package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.CollisionStore;
import edu.rice.comp504.model.paintobj.APaintObject;

import java.awt.*;

public class VerticalStrategy implements IUpdateStrategy{
    private static IUpdateStrategy ONLY;
    private static Point dims;

    private VerticalStrategy() {
        dims = CollisionStore.getCanvasDims();
    }

    public static IUpdateStrategy initiate(){
        if(ONLY == null){
            ONLY = new VerticalStrategy();
        }
        return ONLY;
    }

    @Override
    public String getName() {
        return "vertical";
    }

    @Override
    public void updateState(APaintObject context) {
        Point tempPos = context.getLocation();
        Point tempVel = context.getVelocity();
            context.setLocation(new Point(tempPos.x, tempPos.y + tempVel.y));
    }
}
