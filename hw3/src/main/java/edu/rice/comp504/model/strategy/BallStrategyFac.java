package edu.rice.comp504.model.strategy;
import edu.rice.comp504.model.BallWorldStore;
import edu.rice.comp504.model.ball.Ball;


public class BallStrategyFac implements IStrategyFac{

    public IUpdateStrategy make(String body){
        switch (body) {
            case "horizontal":
                return HorizontalStrategy.initiate();
            case "composite":
                return CompositeStrategy.initiate();
            case "linger":
                return LingerStrategy.initiate();
            case "rotate":
                return RotateStrategy.initiate();
            case "shaking":
                return ShakingStrategy.initiate();
            default:
                return VerticalStrategy.initiate();
        }
    }



}
