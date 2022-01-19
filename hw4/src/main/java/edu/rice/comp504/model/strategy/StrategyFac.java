package edu.rice.comp504.model.strategy;


public class StrategyFac implements IStrategyFac{

    public IUpdateStrategy make(String body){
        switch (body) {
            case "line":
                return LineStrategy.initiate();
            case "rotate":
                return RotateStrategy.initiate();
            case "linger":
                return LingerStrategy.initiate();
            case "shaking":
                return ShakingStrategy.initiate();
            case "square":
                return SquareStrategy.initiate();
            case "sin":
                return SinStrategy.initiate();
            case "boundary":
                return BoundaryStrategy.initiate();
            case "stair":
                return StairStrategy.initiate();
            case "tornado":
                return TornadoStrategy.initiate();
            case "triangle":
                return TriangleStrategy.initiate();
            default:
                return NullStrategy.initiate();
        }
    }



}
