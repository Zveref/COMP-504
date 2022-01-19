package edu.rice.comp504.adapter;

import edu.rice.comp504.model.CollisionStore;
import edu.rice.comp504.model.paintobj.APaintObject;

import java.beans.PropertyChangeListener;

/**
 * This adapter interfaces with the view (paint objects) and the controller.
 */
public class DispatchAdapter {
    public CollisionStore coliStore;
    /**
     * Constructor call.
     */
    public DispatchAdapter(CollisionStore store) {
        this.coliStore = store;
    }


    public void setCanvasDims(String Height, String Width) {
        CollisionStore.setCanvasDims(Height, Width);
    }

    public PropertyChangeListener[] updateWorld() {
        return coliStore.updateWorld();
    }

    public APaintObject loadObj(String strategy, String type, String character, Boolean switchable) {
        return coliStore.loadObj(strategy, type, character, switchable);
    }


    public void switchStrategy() {
        coliStore.switchStrategy();
    }


    public PropertyChangeListener[] remove(String strategy, String type) {
        coliStore.removeListeners(strategy, type);
        return null;
    }

}
