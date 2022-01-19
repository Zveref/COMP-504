package edu.rice.comp504.model.cmd;

/**
 * A factory that makes commands.
 */
public interface ICmdFac {

    /**
     * Makes a ball command.
     * @return A ball command
     */
    IBallCmd make();
}
