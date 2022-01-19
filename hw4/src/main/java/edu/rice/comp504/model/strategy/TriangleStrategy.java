package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.CollisionStore;
import edu.rice.comp504.model.paintobj.APaintObject;

import java.awt.*;

public class TriangleStrategy implements IUpdateStrategy {

    private static IUpdateStrategy ONLY;
    private IUpdateStrategy[] children;


    private TriangleStrategy(IUpdateStrategy[] children) {
        this.children = children;
    }


    public static IUpdateStrategy initiate() {
        if (ONLY == null) {
            IUpdateStrategy[] children = new IUpdateStrategy[2];
            children[0] = HorizontalStrategy.initiate();
            children[1] = VerticalStrategy.initiate();
            ONLY = new TriangleStrategy(children);
        }
        return ONLY;
    }

    public String getName() {
        return "triangle";
    }


    public void updateState(APaintObject context) {
        context.detectCollisionBoundary();
        int currStep = context.getStep();
        Point tempPos = context.getLocation();
        Point tempVel = context.getVelocity();

        switch(currStep){
            case 10:
                context.setVelocity(new Point(-tempVel.x, tempVel.y));
            case 20:
                context.setVelocity(new Point(tempVel.x, - tempVel.y));
        }

        if (currStep < 10) {
            children[1].updateState(context);
        } else if (currStep >= 10 && currStep < 30) {
            for (IUpdateStrategy child : children) {
                child.updateState(context);
            }
        }

        context.setStep(currStep + 1);
        if(context.getStep() >= 30){
            context.setVelocity(new Point(- tempVel.x, -tempVel.y));
            context.setStep(0);
        }
    }
}
