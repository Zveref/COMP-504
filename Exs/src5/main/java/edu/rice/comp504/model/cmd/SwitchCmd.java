package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.MovingLine;
import edu.rice.comp504.model.strategy.*;


public class SwitchCmd implements ILineCmd{

    private LineStrategyFac factory;

    public SwitchCmd() {
        factory = new LineStrategyFac();
    }

    @Override
    public void execute(MovingLine context) {
        IUpdateStrategy tempStrategy = factory.switchStrategy(context);
        context.setStrategy(tempStrategy);
    }
}

//tempStrategy.getClass().equals(CompositeStrategy.class)? null: