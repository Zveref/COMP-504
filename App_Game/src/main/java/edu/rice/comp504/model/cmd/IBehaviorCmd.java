package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.character.ACharacter;

public interface IBehaviorCmd {
    public void execute(ACharacter context);
}
