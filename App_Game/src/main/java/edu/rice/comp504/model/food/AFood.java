package edu.rice.comp504.model.food;

import edu.rice.comp504.model.AGameObj;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public abstract class AFood extends AGameObj implements PropertyChangeListener {
    private String color;
    private int itemScore;
    private boolean isEaten;

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
