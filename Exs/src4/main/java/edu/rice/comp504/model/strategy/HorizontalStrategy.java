package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.MovingLine;

import java.awt.*;

/**
 * Horizontal strategy moves the line to the right only in the x direction.
 */
public class HorizontalStrategy implements IUpdateStrategy {

    /**
     * Constructor.
     */
    public HorizontalStrategy() {
    }

    /**
     * Get the strategy name.
     * @return strategy name
     */
    public String getName() {
        return "horizontal";
    }

    /**
     * Update the ball state in the ball world.
     * @param context The ball to update
     */
    public void updateState(MovingLine context) {
        // TODO: update the moving line
        Point currntV = new Point(context.getVelocity().x, context.getVelocity().y);
        Point LastSP = new Point(context.getStartLine().x, context.getStartLine().y);
        Point LastEP = new Point(context.getEndLine().x, context.getEndLine().y);
        context.setStartLine( new Point(LastSP.x + currntV.x, LastSP.y));
        context.setEndLine( new Point(LastEP.x + currntV.x, LastEP.y));
    }
}
