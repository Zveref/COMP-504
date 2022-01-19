package edu.rice.comp504.model.ball;

import edu.rice.comp504.adapter.DispatchAdapter;
import edu.rice.comp504.model.BallWorldStore;
import edu.rice.comp504.model.strategy.*;
import edu.rice.comp504.util.RandUtil;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The balls that will be drawn in the ball world.
 */
public class Ball implements PropertyChangeListener, IBallFac {
    private Point loc;
    private int radius;
    private Point vel;
    private String color;
    public static String[] ColorField = {"red", "blue", "yellow", "green", "black", "purple", "orange", "gray", "brown"};
    private IUpdateStrategy strategy;
    private int index;
    private int shakingVel;

    /**
     * Constructor.
     * @param loc  The location of the ball on the canvas
     * @param radius The ball radius
     * @param vel  The ball velocity
     * @param color The ball color
     */
    public Ball(Point loc, int radius, Point vel, String color) {
        this.loc = loc;
        this.radius = radius;
        this.vel = vel;
        this.color = color;
        this.strategy = null;
        this.shakingVel = Math.min(BallWorldStore.getCanvasDims().x - this.radius - this.loc.x, this.loc.x - this.radius) / 2;
    }

    /**
     * Get the ball color.
     * @return ball color
     */
    public String getColor() {
        return this.color;
    }

    /**
     * Set the ball color.
     * @param c The new ball color
     */
    public void setColor(String c) {
        this.color = c;
    }


    /**
     * Get the ball location in the ball world.
     * @return The ball location.
     */
    public Point getLocation() {
        return this.loc;
    }


    /**
     * Set the ball location in the canvas.  The origin (0,0) is the top left corner of the canvas.
     * @param loc  The ball coordinate.
     */
    public void setLocation(Point loc) {
        this.loc = loc;
    }


    /**
     * Get the index of the ball.
     * @return The ball index
     */
    public int getIndex() {
        return this.index;
    }


    /**
     * Set the index of the ball.
     * @param index The new ball velocity
     */
    public void setIndex(int index) {
        this.index = index;
    }


    public int getShakingVel() {
        return shakingVel;
    }

    public IUpdateStrategy getStrategy() {
        return strategy;
    }


    public void setStrategy(IUpdateStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Get the velocity of the ball.
     * @return The ball velocity
     */
    public  Point getVelocity() {
        return this.vel;
    }


    /**
     * Set the velocity of the ball.
     * @param vel The new ball velocity
     */
    public void setVelocity(Point vel) {
        this.vel = vel;
    }


    /**
     * Get the radius of the ball.
     * @return The ball radius.
     */
    public int getRadius() {
        return this.radius;
    }

    /**
     * Set the radius of the ball.
     * @param r The ball radius.
     */
    public void setRadius(int r) {
        this.radius = r;
    }

    /**
     * Detects collision between a ball and a wall in the ball world.  Change direction if ball collides with a wall.
     * @return True if there was a collision and false otherwise.
     */
    public boolean detectCollision() {
        Point dim = BallWorldStore.getCanvasDims();
        if(loc.x + radius >= dim.x || loc.x - radius <= 0 || loc.y + radius >= dim.y || loc.y - radius <= 0){
            return true;
        }
        return false;  // I don't think this way of detection is virtually real, so I just complete this but implement better detections in every Strategy classes
    }

    @Override
    /**
     *  Ball responds to the property change support indicating that property has changed.
     */
    public void propertyChange(PropertyChangeEvent evt) {
        strategy.updateState(this);
    }

    @Override
    public Ball make() {
        return null;
    }
}
