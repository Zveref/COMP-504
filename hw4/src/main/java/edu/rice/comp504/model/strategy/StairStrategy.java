package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paintobj.APaintObject;

import java.awt.*;

public class StairStrategy implements IUpdateStrategy{

    private static IUpdateStrategy ONLY;
    private IUpdateStrategy[] children;


    private StairStrategy(IUpdateStrategy[] children) {
        this.children = children;
    }


    public static IUpdateStrategy initiate() {
        if (ONLY == null) {
            IUpdateStrategy[] children = new IUpdateStrategy[2];
            children[0] = HorizontalStrategy.initiate();
            children[1] = VerticalStrategy.initiate();
            ONLY = new StairStrategy(children);
        }
        return ONLY;
    }

    public String getName() {
        return "stair";
    }


    public void updateState(APaintObject context) {
        context.detectCollisionBoundary();
        int currStep = context.getStep();

        if(currStep / 4 % 2 == 0){
            children[0].updateState(context);
        }else{
            children[1].updateState(context);
        }
        context.setStep(currStep + 1);
    }

}
