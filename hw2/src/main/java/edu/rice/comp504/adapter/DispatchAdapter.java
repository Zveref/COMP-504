package edu.rice.comp504.adapter;

import edu.rice.comp504.model.BallWorldStore;
import edu.rice.comp504.model.ball.Ball;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

/**
 * This adapter interfaces with the view (paint objects) and the controller.
 */
public class DispatchAdapter {
    private static Point dims;
    public ArrayList<Ball> AllBalls;
    public BallWorldStore ballworld;
    /**
     * Constructor call.
     */
    public DispatchAdapter() {
        AllBalls = new ArrayList<Ball>();
        ballworld = new BallWorldStore();
    }

    /**
     * Set the canvas dimensions.
     * @param dims The canvas width (x) and height (y).
     */
    public void setCanvasDims(Point dims) {
        this.dims = dims;
        BallWorldStore.setCanvasDims(dims);
    }

    /**
     * Update the ball world.
     * @return Balls in BallWorld
     */
    public PropertyChangeListener[] updateBallWorld() {
        ballworld.updateBallWorld();
        return null;
    }

    /**
     * Load a ball into the paint world.
     * @param strat  The REST request strategy.
     * @param type The type of object to load on the canvas.
     * @return Balls in BallWorld
     */
    public PropertyChangeListener[] loadBall(String strat, String type) {
        Ball tempBall = ballworld.loadBall(strat, type);
        AllBalls.add(tempBall);
        return null;
    }


    /**
     * Switch the strategy for switcher balls
     * @param strat  The REST request strategy.
     * @return Balls in BallWorld
     */
    public PropertyChangeListener[] switchStrategy(String strat) {
        // TODO: fill in
        return null;
    }


    /**
     * Remove all balls from listening for property change events for a particular property.
     * @return Balls in BallWorld
     */
    public PropertyChangeListener[] removeAll() {
        ballworld.removeBallsFromStore();
        AllBalls.clear();
        return null;
    }

}
