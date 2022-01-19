package edu.rice.comp504.model;

import java.awt.*;

public abstract class AGameObj {
    protected Point loc;
    protected String type;
    protected int size;

    /**
     * Get the location of game object.
     * @return location of game object
     */
    public Point getLoc() {
        return this.loc;
    }

    /**
     * Set the location of game object.
     * @param newLoc new location
     */
    public void setLoc(Point newLoc) {
        this.loc = newLoc;
    }

    /**
     * Get the type of game object.
     * @return type of game object
     */
    public String getType() {
        return this.type;
    }

    /**
     * Set the type of game object.
     * @param type new type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Get the size of game object.
     * @return size of game object
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Set the size of game object.
     * @param size new size
     */
    public void setSize(int size) {
        this.size = size;
    }

}
