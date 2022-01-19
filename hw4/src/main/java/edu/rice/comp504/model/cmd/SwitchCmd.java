package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.CollisionStore;
import edu.rice.comp504.model.paintobj.APaintObject;
import edu.rice.comp504.model.paintobj.Fish;
import edu.rice.comp504.model.strategy.*;
import edu.rice.comp504.util.CalcuUtil;

import java.awt.*;

public class SwitchCmd implements ObjCmd{
    private Point dims;


    public SwitchCmd(){
        dims = CollisionStore.getCanvasDims();
    }


    public void execute(APaintObject Obj){
        if(!Obj.isSwitchable()){
            return;
        }
        IUpdateStrategy thisStrategy = Obj.getStrategy();
        switch(thisStrategy.getName()){
            case"line":
                Obj.setStrategy(LingerStrategy.initiate());
                break;
            case"linger":
                Obj.setStrategy(ShakingStrategy.initiate());
                break;
            case"shaking":
                if(  (int) CalcuUtil.getDis(Obj.getLocation(), new Point(400, 400)) <= (400 -  2 * Obj.getRadius()) ){
                    Obj.setStrategy(RotateStrategy.initiate());
                    if(Obj.getClass() == Fish.class){
                        Obj.setVelocity(new Point(Math.abs(Obj.getVelocity().x), Obj.getVelocity().y));
                    }
                }else{
                    Obj.setStrategy(TriangleStrategy.initiate());
                }
                break;
            case"rotate":
                Obj.setStrategy(TriangleStrategy.initiate());
                break;
            case"triangle":
                Obj.setStrategy(SquareStrategy.initiate());
                break;
            case"square":
                Obj.setStrategy(SinStrategy.initiate());
                break;
            case"sin":
                Obj.getLocation().y = Obj.getLocation().y > 60 + Obj.getRadius() ? Obj.getLocation().y : 60 + Obj.getRadius();
                Obj.getLocation().y = Obj.getLocation().y < 800 - Obj.getRadius() ? Obj.getLocation().y : 800 - Obj.getRadius();
                Obj.setStep(Obj.getLocation().y);
                Obj.setStrategy(BoundaryStrategy.initiate());
                break;
            case"boundary":
                Obj.setStrategy(StairStrategy.initiate());
                break;
            case"stair":
                Obj.setStrategy(TornadoStrategy.initiate());
                break;
            case"tornado":
                Obj.setStrategy(LineStrategy.initiate());
                break;
//            default:
//                Obj.setStrategy(LineStrategy.initiate());
        }
    }
}
