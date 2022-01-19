package edu.rice.comp504.model.strategy;

import edu.rice.comp504.adapter.DispatchAdapter;
import edu.rice.comp504.model.BallWorldStore;
import edu.rice.comp504.model.ball.Ball;

import java.awt.*;

public class HorizontalStrategy implements IUpdateStrategy{
    private static IUpdateStrategy ONLY;
    private static Point dims;

    private HorizontalStrategy() {
        dims = BallWorldStore.getCanvasDims();
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
    public void updateState(Ball context) {
        Point tempPos = context.getLocation();
        Point tempVel = context.getVelocity();
        if(tempPos.x + context.getRadius() + tempVel.x > dims.x){
            context.setLocation(new Point(dims.x - context.getRadius(), tempPos.y));
            context.setVelocity(new Point(- tempVel.x, tempVel.y));
        }else if (tempPos.x - context.getRadius() + tempVel.x < 0){
            context.setLocation(new Point(context.getRadius(), tempPos.y));
            context.setVelocity(new Point(- tempVel.x, tempVel.y));
        }else{
            context.setLocation(new Point(context.getLocation().x + tempVel.x, context.getLocation().y));
        }
    }
}
