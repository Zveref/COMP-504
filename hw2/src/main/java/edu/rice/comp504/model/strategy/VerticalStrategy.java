package edu.rice.comp504.model.strategy;

import edu.rice.comp504.adapter.DispatchAdapter;
import edu.rice.comp504.model.BallWorldStore;
import edu.rice.comp504.model.ball.Ball;

import java.awt.*;

public class VerticalStrategy implements IUpdateStrategy{
    private static IUpdateStrategy ONLY;
    private static Point dims;

    private VerticalStrategy() {
        dims = BallWorldStore.getCanvasDims();
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
    public void updateState(Ball context) {
        Point tempPos = context.getLocation();
        Point tempVel = context.getVelocity();
        if(tempPos.y + context.getRadius() + tempVel.y > dims.y){
            context.setLocation(new Point(tempPos.x, dims.y - context.getRadius()));
            context.setVelocity(new Point(tempVel.x, - tempVel.y));
        }else if (tempPos.y - context.getRadius() + tempVel.y < 0){
            context.setLocation(new Point(tempPos.x, context.getRadius()));
            context.setVelocity(new Point(tempVel.x, - tempVel.y));
        }else{
            context.setLocation(new Point(context.getLocation().x, context.getLocation().y + tempVel.y));
        }
    }
}
