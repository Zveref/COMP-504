package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.BallWorldStore;
import edu.rice.comp504.model.paintobj.APaintObject;
import edu.rice.comp504.model.paintobj.Ball;
import edu.rice.comp504.model.paintobj.NullObject;
import edu.rice.comp504.model.paintobj.Wall;
import edu.rice.comp504.model.strategy.IUpdateStrategy;
import edu.rice.comp504.model.strategy.StraightStrategy;

import java.awt.*;
import java.beans.PropertyChangeListener;

/**
 * The UpdateStateCmd will possibly update either the paint object location or attribute (color).
 */
public class UpdateStateCmd implements IPaintObjCmd {
    private final PropertyChangeListener[] iWalls;

    /**
     * The constructor.
     * @param iWalls The canvas inner walls
     *
     */
    public UpdateStateCmd(PropertyChangeListener[] iWalls) {
        this.iWalls = iWalls;
    }

    /**
     * Update the state of the paint object
     * @param context  The paint object.
     */
    public void execute(APaintObject context) {
        IUpdateStrategy tempStrategy = context.getStrategy();
        if(context.getClass() == Ball.class){
            context.detectCollisionBoundary();
            tempStrategy.updateState(context);
            Ball tempBall = (Ball)context;
            for(int i = 0; i < iWalls.length; i++){
                if(iWalls[i].getClass().equals(Wall.class) && tempBall.detectCollisionInnerWall((Wall)iWalls[i]) && ((Wall) iWalls[i]).getNotEaten()){
                    tempBall.eatlist.add((Wall) iWalls[i]);
                    ((Wall) iWalls[i]).setNotEaten(false);
                }
            }
            for(Wall tmp: ((Ball) context).eatlist){
                tmp.setLocation(new Point(tempBall.getLocation().x, tempBall.getLocation().y));
                tmp.setLength(tempBall.getRadius());

            }

        }
    }

        // TODO: check if there's a collision between a ball and an inner wal

}
