package edu.rice.comp504.model.paintobj;

import edu.rice.comp504.model.CollisionStore;
import edu.rice.comp504.model.cmd.InnerCollideCmd;
import edu.rice.comp504.model.cmd.ObjCmd;
import edu.rice.comp504.model.cmd.SwitchCmd;
import edu.rice.comp504.model.cmd.UpdateStateCmd;
import edu.rice.comp504.model.strategy.IUpdateStrategy;
import edu.rice.comp504.util.CalcuUtil;
import edu.rice.comp504.util.RandUtil;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

/**
 * A circle that the view can draw on the user's canvas.
 */
public class Ball extends APaintObject{
    private transient ArrayList<Ball> ownlist;
    private boolean isowned;

    private Ball(Point loc,  Point vel, int radius, String color, int step, Boolean switchable, IUpdateStrategy strategy) {
        super(loc, vel, radius, "ball", step, switchable, strategy);
        this.color = color;
        this.character = "normal";
        this.ownlist = new ArrayList<Ball>();
        this.isowned = false;
        this.switchable = RandUtil.getBoolean();
    }


    public boolean isColorable() {
        return true;
    }

    public void setColor(String color){
        this.color = color;
    }

    public static Ball makeBall(IUpdateStrategy strategy, Point dims, Boolean switchable) {
        int R = RandUtil.getRnd(10,10);
        return new Ball(new Point(RandUtil.getRnd(R, dims.x - 2 * R), RandUtil.getRnd(R, dims.y - 2 * R) ),
                        new Point(RandUtil.getRnd(-6, 12), RandUtil.getRnd(-6,12)),
                        R,
                        RandUtil.getColor(),
                        0,
                        switchable,
                        strategy);

    }




    public int getRadius() {
        return radius;
    }


    public void setRadius(int r) {
        radius = r;
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


    public boolean detectInnerCollision(Ball ball2) {
        Point loc2 = ball2.getLocation();
        int radius2 = ball2.getRadius();
        if(CalcuUtil.getDis(this.loc, loc2) <= radius2 + this.radius ){
            return true;
        }
        return false;
    }

    public void setCharacter(String character){
        this.character = character;
    }

    public String getCharacter(){
        return this.character;
    }

    public void setStep(int step) {
        this.step = step;
    }


    public boolean getIsowned() {
        return isowned;
    }

    public void setIsowned(boolean isowned) {
        this.isowned = isowned;
    }

    public void ownThis(Ball ball){
        ownlist.add(ball);
    }

    public ArrayList<Ball> getOwnlist(){
        return ownlist;
    }

    public void propertyChange(PropertyChangeEvent update) {
        ((ObjCmd) update.getNewValue() ).execute(this);
    }



//    public void propertyChange(PropertyChangeEvent update) {
//        ((ObjCmd) update.getNewValue() ).execute(this);
//    }

//    public void propertyChange(PropertyChangeEvent switch) {
//        ((SwitchCmd) switch.getNewValue() ).execute(this);
//    }
//
}
