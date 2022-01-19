package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.MovingLine;


/**
 * Composite strategy is composed of the Horizontal and Vertical strategies.
 */
public class CompositeStrategy implements IUpdateStrategy {
    private final IUpdateStrategy[] children;

    /**
     * Constructor.
     * @param children The composite strategy children
     */
    public CompositeStrategy(IUpdateStrategy[] children) {
        this.children = children;
    }

    /**
     * Get the strategy name.
     * @return strategy name
     */
    public String getName() {
        return "composite";
    }

    /**
     * Update the ball state in the ball world.
     * @param context The ball to update
     */
    public void updateState(MovingLine context) {
        // TODO: update the moving line
        for(IUpdateStrategy temp : children)
        temp.updateState(context);
    }
}
