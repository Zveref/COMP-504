package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.BallWorldStore;
import edu.rice.comp504.model.ball.Ball;
import edu.rice.comp504.util.RandUtil;

import java.awt.*;

public class ShakingStrategy implements IUpdateStrategy{
    private static IUpdateStrategy ONLY;
    private static Point dims;

    private ShakingStrategy() {
        dims = BallWorldStore.getCanvasDims();
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
    public void updateState(Ball context) {
        Point tempPos = context.getLocation();
        Point tempVel = context.getVelocity();
        if(context.getIndex() == 1){
            context.setVelocity(new Point(- tempVel.x, tempVel.y));
            context.setIndex(0);
        }
        context.setLocation(new Point(tempPos.x + tempVel.x / Math.abs(tempVel.x) * context.getShakingVel(), tempPos.y));
        context.setIndex(context.getIndex()+1);

    }
}
