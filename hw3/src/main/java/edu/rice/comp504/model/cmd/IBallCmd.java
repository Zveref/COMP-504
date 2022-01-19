package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.ball.Ball;

/**
 * The IBallCmd is an interface used to pass commands to objects in the BallWorld.  The
 * objects must execute the command.
 */
public interface IBallCmd {

/**
 * Execute the command.
 * @param context The receiver on which the command is executed.
 */
    public void execute(Ball context);
}
