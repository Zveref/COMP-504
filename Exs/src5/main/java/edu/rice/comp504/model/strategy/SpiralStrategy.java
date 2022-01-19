package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.CollisionStore;
import edu.rice.comp504.model.paintobj.APaintObject;

import java.awt.*;

public class SpiralStrategy implements IUpdateStrategy{


    private static IUpdateStrategy ONLY;
    private static Point dims;

    private SpiralStrategy() {
        dims = CollisionStore.getCanvasDims();
    }

    public static IUpdateStrategy initiate(){
        if(ONLY == null){
            ONLY = new SpiralStrategy();
        }
        return ONLY;
    }

    @Override
    public String getName() {
        return "spiral";
    }

    @Override
    public void updateState(APaintObject context) {
        double degree = Math.PI * 3 / 7 * (Math.abs(context.getStep() % 2) == 0 ? 1 : -1);
        Point dimCenter = new Point(dims.x / 2, dims.y / 2);
        Point tempPos = context.getLocation();
        Point temVel = context.getVelocity();
        Point center = new Point(dimCenter.x + temVel.x * context.getStep() , dims.y / 2);
        double newX =  center.x + (tempPos.x - center.x) * Math.sin(degree) + (tempPos.y - center.y) * Math.cos(degree);
        double newY =  center.y - (tempPos.x - center.x) * Math.cos(degree) + (tempPos.y - center.y) * Math.sin(degree);
        if(newX + context.getRadius() > dims.x ){
            degree *= -1;
            context.setStep(context.getStep() -7);
        }else if(newX - context.getRadius() < 0){
            degree *= -1;
            context.setStep(context.getStep() + 7);
            }else{
                context.setLocation(new Point ((int) newX, (int) newY));
            }

        if(Math.abs(context.getStep() % 2) == 0){
            context.setStep(context.getStep() + 2);
        }else{
            context.setStep(context.getStep() - 2);
        }

        }


}


