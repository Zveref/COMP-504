package edu.rice.comp504.model.paintobj;


import java.awt.*;
import java.beans.PropertyChangeEvent;

/**
 * NullObject represents an initial or unexpected paint object type.
 */
public class NullObject extends APaintObject {
    private static NullObject ONLY;

    /**
     * Constructor.
     */
    private NullObject() {
        super(new Point(0,0), new Point(0,0), 0, "vanish",0, false, null);
        this.step = 0;
        this.character = null;
        this.switchable = false;
    }

    /**
     * Make a null object.  There is only one (singleton).
     * @return A null object
     */
    public static NullObject make() {
        if (ONLY == null) {
            ONLY = new NullObject();
        }
        return ONLY;
    }

    @Override
    public boolean detectCollisionBoundary() {
        return false;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
