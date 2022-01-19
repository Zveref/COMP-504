package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.MovingLine;
import edu.rice.comp504.model.strategy.CompositeStrategy;
import edu.rice.comp504.model.strategy.IUpdateStrategy;

import java.awt.*;

public class UpdateCmd implements ILineCmd{

    public UpdateCmd() {
    }

    @Override
    public void execute(MovingLine context) {
        IUpdateStrategy tempStrategy = context.getStrategy();
        if(tempStrategy.getClass().equals(CompositeStrategy.class)){
            tempStrategy = new IUpdateStrategy() {
                @Override
                public String getName() {
                    return "composite";
                }

                @Override
                public void updateState(MovingLine context) {
                    context.nextLocation(new Point(0,0));
                }
            };
        }
        tempStrategy.updateState(context);
    }
}
