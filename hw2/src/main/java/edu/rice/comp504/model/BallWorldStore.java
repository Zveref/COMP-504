package edu.rice.comp504.model;

import edu.rice.comp504.adapter.DispatchAdapter;
import edu.rice.comp504.model.ball.Ball;
import edu.rice.comp504.model.strategy.BallStrategyFac;
import edu.rice.comp504.model.strategy.IStrategyFac;
import edu.rice.comp504.util.RandUtil;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Random;

/**
 * A store containing current Ball World.
 */
public class BallWorldStore {
    private PropertyChangeSupport pcs;
    private static Point dims;
    private static BallStrategyFac ONLY;

    public static BallStrategyFac getOnlyFac(){
        if (ONLY == null){
            ONLY = new BallStrategyFac();
        }
        return ONLY;
    }

    /**
     * Constructor.
     */
    public BallWorldStore() {
        pcs = new PropertyChangeSupport(this);
        dims = new Point(0,0);
    }

    /**
     * Get the canvas dimensions.
     * @return The canvas dimensions
     */
    public static Point getCanvasDims() {
        return dims;
    }

    /**
     * Set the canvas dimensions.
     * @param CanvasDim The canvas width (x) and height (y).
     */
    public static void setCanvasDims(Point CanvasDim) {
        dims = CanvasDim;
    }

    /**
     * Call the update method on all the ball observers to update their position in the ball world.
     */
    public PropertyChangeListener[] updateBallWorld() {
        pcs.firePropertyChange(null, null, null);
        return null;
    }

    /**
     * Load a ball into the Ball World.
     * @param body  The REST request body has the strategy names.
     * @return A ball
     */
    public Ball loadBall(String body, String type) {
        int radius = RandUtil.getRnd(10,10);
        Point loc = new Point(RandUtil.getRnd(0 + radius, dims.x - radius), RandUtil.getRnd(0 + radius, dims.y - radius));

        // I've not handled with dynamic collision characteristics between rotating ball and walls, here to prevent it from happening
        if(body == "rotate"){
            loc = new Point(RandUtil.getRnd(160 + radius, 640 - radius), RandUtil.getRnd(160 + radius, 640 - radius));
        }

        Point vel = new Point(RandUtil.getRnd(20,20), RandUtil.getRnd(20,20));
        int ColorIndex = new Random().nextInt(9);
        String color = Ball.ColorField[ColorIndex];
        Ball tempBall = new Ball(loc, radius, vel, color);
        tempBall.setStrategy(getOnlyFac().make(body));
        addBallToStore(tempBall);
        return tempBall;
    }


    /**
     * Switch the strategy of switcher balls
     * @param body  The REST request strategy.
     */
    public PropertyChangeListener[] switchStrategy(String body) {
        // TODO: fill in
        return null;
    }

    /**
     * Add a ball that will listen for a property change (i.e. time elapsed)
     * @param pcl  The ball
     */
    private void addBallToStore(PropertyChangeListener pcl) {
        pcs.addPropertyChangeListener(pcl);
    }

    /**
     * Remove all balls from listening for property change events for a particular property.
     */
    public void removeBallsFromStore() {
        pcs = new PropertyChangeSupport(this);
    }
}
