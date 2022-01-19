package edu.rice.comp504.model.strategy;

import edu.rice.comp504.adapter.DispatchAdapter;
import edu.rice.comp504.model.BallWorldStore;
import edu.rice.comp504.model.ball.Ball;

import java.awt.*;
import java.util.Random;

public class LingerStrategy implements IUpdateStrategy{
    private static IUpdateStrategy ONLY;
    private static Point dims;

    private LingerStrategy() {
        dims = BallWorldStore.getCanvasDims();
    }

    public static IUpdateStrategy initiate(){
        if(ONLY == null){
            ONLY = new LingerStrategy();
        }
        return ONLY;
    }

    @Override
    public String getName() {
        return "linger";
    }

    @Override
    public void updateState(Ball context) {
        context.setLocation(new Point(context.getLocation().x, context.getLocation().y));
        int ColorIndex = new Random().nextInt(9);
        String color = Ball.ColorField[ColorIndex];
        context.setColor(color);
    }
}
