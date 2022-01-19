package edu.rice.comp504.model.paintobj;

import edu.rice.comp504.model.CollisionStore;
import edu.rice.comp504.model.cmd.UpdateStateCmd;
import edu.rice.comp504.model.strategy.IUpdateStrategy;
import edu.rice.comp504.model.strategy.RotateStrategy;
import edu.rice.comp504.util.CalcuUtil;
import edu.rice.comp504.util.RandUtil;

import java.awt.*;
import java.beans.PropertyChangeEvent;

/**
 * A vertical wall at a location within the canvas.
 */
public class Fish extends APaintObject {
    private Point direction;
    private boolean needflip;



    private Fish(Point loc, Point vel, int radius, int step, Boolean switchable, IUpdateStrategy strategy) {
        super(loc, vel, 10, "fish", step, switchable, strategy);
        this.radius = radius;
        this.step = step;
        this.switchable = RandUtil.getBoolean();
        this.direction = vel;
        this.strategy = strategy;
        this.needflip = false;
    }



    public static Fish makeFish(IUpdateStrategy strategy, Point dims, Boolean switchable) {
        int R = RandUtil.getRnd(10,10);

        return new Fish(new Point(RandUtil.getRnd(R, dims.x - 2 * R), RandUtil.getRnd(R, dims.y - 2 * R) ),
                        new Point(RandUtil.getRnd(-6, 12), RandUtil.getRnd(-6,12)),
                        R,
                        0,
                        switchable,
                        strategy);
    }


    public boolean detectCollisionBoundary() {
        // The canvas dimensions
        Point dims = CollisionStore.getCanvasDims();
        // left wall collision
        if ((vel.x < 0) && ((loc.x - radius) <= 0)) {
            vel.x = vel.x * -1;
            return true;
        } else if ((vel.x > 0) && ((loc.x + radius) >= dims.x)) {
            // right wall collision
            vel.x = vel.x * -1;
            return true;
        }
        // top wall collision
        if ((vel.y < 0) && ((loc.y - radius) <= 0)) {
            vel.y = vel.y * -1;
            return true;
        } else if ((vel.y > 0) && ((loc.y + radius) >= dims.y)) {
            // bottom wall collision
            vel.y = vel.y * -1;
            return true;
        }
        return false;
    }



    public boolean detectBall(Ball ball2) {
        Point loc2 = ball2.getLocation();
        int radius2 = ball2.getRadius();
        if(CalcuUtil.getDis(this.loc, loc2) <= radius2 + 2 * this.radius ){
            return true;
        }
        return false;
    }

    /**
     * Set the wall update strategy.
     * @param strat  The new strategy
     */
    public void setStrategy(IUpdateStrategy strat) {
        this.strategy = strat;
    }

    public void setDirection(Point direction) {
        this.direction = direction;
    }

    /**
     * Set the wall velocity.
     * @param vel The new velocity
     */
    public void setVelocity(Point vel) {
        this.vel = vel;
    }


    public boolean getNeedflip() {
        return needflip;
    }

    public void setNeedflip(boolean needflip) {
        this.needflip = needflip;
    }

    public Point getDirection() {
        return direction;
    }


    /**
     * Update state of the inner wall when the property change event occurs by executing the command.
     */
    public void propertyChange(PropertyChangeEvent e) {
        ((UpdateStateCmd) e.getNewValue()).execute(this);
    }

}
