package edu.rice.comp504.adapter;

import edu.rice.comp504.model.GameBoard;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Objects;

/**
 * This adapter interfaces with the view (paint objects) and the controller.
 */
public class DispatchAdapter {
    private static Point dims;
    private GameBoard game;
    private PropertyChangeSupport pcs;

}
