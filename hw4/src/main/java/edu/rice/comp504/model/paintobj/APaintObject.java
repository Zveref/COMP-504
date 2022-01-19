package edu.rice.comp504.model.paintobj;

import edu.rice.comp504.model.strategy.IUpdateStrategy;
import edu.rice.comp504.util.RandUtil;

import java.awt.*;
import java.beans.PropertyChangeListener;


public abstract class APaintObject implements PropertyChangeListener {
    protected Point loc;
    protected Point vel;
    protected IUpdateStrategy strategy;
    protected String color;
    protected String type;
    protected int radius;
    protected int step;
    protected String character;
    protected boolean switchable;



    public APaintObject(Point loc, Point vel, int radius, String type, int step, Boolean switchable, IUpdateStrategy strategy) {
        this.loc = loc;
        this.vel = vel;
        this.radius = radius;
        this.type = type;
        this.strategy = strategy;
        this.step = step;
        this.character = "normal";
        this.switchable = switchable;
    }



    public abstract boolean detectCollisionBoundary();


    public String getType() {
        return type;
    }


    public Point getLocation() {
        return loc;
    }


    public void setLocation(Point loc) {
        this.loc = loc;
    }


    public boolean isColorable() {
        return false;
    }


    public String getColor() {
        return color;
    }


    public void setVelocity(Point vel) {
        this.vel = vel;
    }


    public void setColor(String color) {
        if (isColorable()) {
            this.color = color;
        }
    }


    public void nextLocation(int velX, int velY) {
        loc.x += velX;
        loc.y += velY;
    }


    public  Point getVelocity() {
        return vel;
    }

    public void setStrategy(IUpdateStrategy strategy) {
        this.strategy = strategy;
    }

    public IUpdateStrategy getStrategy() {
        return strategy;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public boolean isSwitchable() {
        return switchable;
    }

    public void setSwitchable(boolean switchable) {
        this.switchable = switchable;
    }

    public int getStep() {
        return this.step;
    }

    public void setStep(int step) {
        this.step = step;
    }

}
