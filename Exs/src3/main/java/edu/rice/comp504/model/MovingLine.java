package edu.rice.comp504.model;

/**
 * Represents a line that moves.
 */
public class MovingLine {
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private int velX;

    /**
     * MovingLine constructor.
     * @param velX velocity in the x direction
     */
    public MovingLine(int velX) {
        this.velX = velX;
        this.startX = 0;
        this.startY = 0;
        this.endX = 200;
        this.endY = 100;
    }

    /**
     * Update the line position.
     */
    public void update() {
        startX += velX;
        endX += velX;
        // TODO: Update the line position in the x direction only
    }

    /**
     * Reset the line position to the original position.
     */
    public void resetPos() {
        startX = 0;
        startY = 0;
        endX = 200;
        endY = 100;
    }


}
