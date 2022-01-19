package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.ball.Ball;

public class CompositeStrategy implements IUpdateStrategy{
    private static IUpdateStrategy ONLY;
    private IUpdateStrategy[] children;


    private CompositeStrategy(IUpdateStrategy[] children) {
        this.children = children;
    }


    public static IUpdateStrategy initiate() {
        if (ONLY == null) {
            IUpdateStrategy[] children = new IUpdateStrategy[2];
            children[0] = HorizontalStrategy.initiate();
            children[1] = VerticalStrategy.initiate();
            ONLY = new CompositeStrategy(children);
        }
        return ONLY;
    }

    public String getName() {
        return "composite";
    }


    public void updateState(Ball context) {
        for (IUpdateStrategy child: children) {
            child.updateState(context);
        }
    }
}
