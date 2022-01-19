package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.MovingLine;

import java.awt.*;


/**
 * Vertical strategy moves the line to the right only in the y direction.
 */
public class VerticalStrategy implements IUpdateStrategy {

    /**
     * Constructor.
     */
    public VerticalStrategy() {
    }

    /**
     * Get the strategy name.
     * @return strategy name
     */
    public String getName() {
        return "vertical";
    }

    /**
     * Update the ball state in the ball world.
     * @param context The ball to update
     */
    public void updateState(MovingLine context) {
        Point currntV = new Point(context.getVelocity().x, context.getVelocity().y);
        Point LastSP = new Point(context.getStartLine().x, context.getStartLine().y);
        Point LastEP = new Point(context.getEndLine().x, context.getEndLine().y);
        context.setStartLine( new Point(LastSP.x , LastSP.y + currntV.y));
        context.setEndLine( new Point(LastEP.x, LastEP.y + currntV.y));
    }
}
