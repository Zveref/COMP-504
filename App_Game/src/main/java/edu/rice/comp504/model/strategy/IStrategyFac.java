package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.strategy.IUpdateStrategy;

/**
 * A factory that makes strategies.
 */
public interface IStrategyFac {

    /**
     * Makes a strategy.
     * @return A strategy
     */
    IUpdateStrategy make(String strategy);
}
