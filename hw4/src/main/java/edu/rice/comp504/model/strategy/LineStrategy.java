package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paintobj.APaintObject;

public class LineStrategy implements IUpdateStrategy{
    private static IUpdateStrategy ONLY;
    private IUpdateStrategy[] children;


    private LineStrategy(IUpdateStrategy[] children) {
        this.children = children;
    }


    public static IUpdateStrategy initiate() {
        if (ONLY == null) {
            IUpdateStrategy[] children = new IUpdateStrategy[2];
            children[0] = HorizontalStrategy.initiate();
            children[1] = VerticalStrategy.initiate();
            ONLY = new LineStrategy(children);
        }
        return ONLY;
    }

    public String getName() {
        return "line";
    }


    public void updateState(APaintObject context) {
        for (IUpdateStrategy child: children) {
            child.updateState(context);
        }
    }
}
