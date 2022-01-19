package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.CollisionStore;
import edu.rice.comp504.model.paintobj.APaintObject;
import edu.rice.comp504.util.RandUtil;

import java.awt.*;

public class SinStrategy implements IUpdateStrategy{

    private static IUpdateStrategy ONLY;
    private static Point dims;

    private SinStrategy() {
        dims = CollisionStore.getCanvasDims();
    }

    public static IUpdateStrategy initiate(){
        if(ONLY == null){
            ONLY = new SinStrategy();
        }
        return ONLY;
    }

    @Override
    public String getName() {
        return "sin";
    }

    @Override
    public void updateState(APaintObject context) {
        context.detectCollisionBoundary();
        Point tempPos = context.getLocation();
        Point tempVel = context.getVelocity();
        // use step to store the relative baseline
        int nextX = tempPos.x + tempVel.x;
        double nextY = context.getStep() + 60 * Math.sin(nextX * 2 * Math.PI / dims.x);
        nextY = nextY + context.getRadius() > 800 ? 800 - context.getRadius() : nextY;
        nextY = nextY - context.getRadius() < 0 ?  context.getRadius(): nextY;
        context.setLocation(new Point(nextX, (int)nextY));
    }

}
