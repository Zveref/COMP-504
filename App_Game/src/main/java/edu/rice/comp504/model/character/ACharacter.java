package edu.rice.comp504.model.character;

import edu.rice.comp504.model.AGameObj;
import edu.rice.comp504.model.strategy.interaction.IInteractionStrategy;
import edu.rice.comp504.model.strategy.IUpdateStrategy;

import java.beans.PropertyChangeListener;

public abstract class ACharacter extends AGameObj implements PropertyChangeListener {
    protected int direction;
    protected IUpdateStrategy updateStrategy;
    protected IInteractionStrategy interactionStrategy;
    protected String color;




}
