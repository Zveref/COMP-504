package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.CollisionStore;
import edu.rice.comp504.model.paintobj.APaintObject;
import edu.rice.comp504.util.RandUtil;

import java.awt.*;

public class BoundaryStrategy implements IUpdateStrategy{
    private static IUpdateStrategy ONLY;
    private IUpdateStrategy[] children;
    private Point dims;


    private BoundaryStrategy(IUpdateStrategy[] children) {
        this.children = children;
        dims = CollisionStore.getCanvasDims();
    }


    public static IUpdateStrategy initiate() {
        if (ONLY == null) {
            IUpdateStrategy[] children = new IUpdateStrategy[2];
            children[0] = HorizontalStrategy.initiate();
            children[1] = VerticalStrategy.initiate();
            ONLY = new BoundaryStrategy(children);
        }
        return ONLY;
    }

    public String getName() {
        return "boundary";
    }


    public void updateState(APaintObject context) {
        int currStep = context.getStep();
        Point tempPos = context.getLocation();
        Point tempVel = context.getVelocity();
//        if(tempVel.x * tempVel.y == 0){
//            context.setLocation(new Point(0 + context.getRadius(), RandUtil.getRnd(200,600)));
//        }
        if(currStep >1){
            context.setStep(0);
        }
        tempVel.x = tempVel.x == 0 ? 2 :tempVel.x;
        tempVel.y = tempVel.x == 0 ? 2 :tempVel.y;
        switch (currStep){
            //
            case 0:
                if( ( ((tempPos.x + context.getRadius() +tempVel.x <= dims.x) && tempVel.x > 0) || ((tempPos.x + tempVel.x- context.getRadius() >= 0) && tempVel.x < 0) )){
                    children[0].updateState(context);
                    break;
                }else{
                    context.setVelocity(new Point( - tempVel.x, tempVel.y));
                    context.setStep(1);
                    children[1].updateState(context);
                    break;
                }
            case 1:
                if( ( ((tempPos.y + context.getRadius() + tempVel.y <= dims.y) && tempVel.y > 0) || ((tempPos.y + tempVel.y - context.getRadius() >= 0) && tempVel.y < 0) )){
                    children[1].updateState(context);
                    break;
                }else{
                    context.setVelocity(new Point( tempVel.x, - tempVel.y));
                    context.setStep(0);
                    children[1].updateState(context);
                    break;
                }
        }
        //determine the location
//        if( ( ((tempPos.x + context.getRadius() <= dims.x) && tempVel.x > 0) || ((tempPos.x - context.getRadius() >= 0) && tempVel.x < 0) ) ){
//            context.setStep(0);
//        }else{
//            context.detectCollisionBoundary();
//        }
//        //implement the moving
//        if(currStep == 0){
//            children[0].updateState(context);
//        }else{
//            children[1].updateState(context);
//        }
    }
}
