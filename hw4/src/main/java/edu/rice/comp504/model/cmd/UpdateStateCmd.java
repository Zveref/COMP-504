package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.CollisionStore;
import edu.rice.comp504.model.paintobj.APaintObject;
import edu.rice.comp504.model.paintobj.Ball;
import edu.rice.comp504.model.paintobj.Fish;
import edu.rice.comp504.model.strategy.IUpdateStrategy;
import edu.rice.comp504.model.strategy.RotateStrategy;

import java.awt.*;
import java.beans.PropertyChangeListener;

public class UpdateStateCmd implements ObjCmd{
    private final PropertyChangeListener[] Balls;
    private final PropertyChangeListener[] Fishes;


    public UpdateStateCmd(PropertyChangeListener[] Balls, PropertyChangeListener[] Fishes){
        this.Balls = Balls;
        this.Fishes = Fishes;
    }

    public void execute(APaintObject context){
        IUpdateStrategy CurrentStrategy = context.getStrategy();

        //if is owned ball, skip the innerCollision
        if(context.getClass() == Ball.class && ((Ball) context).getIsowned()){
            return;
        }
        //if is rotating ball, skip the innerCollision----->because it's so messy!
        if(context.getClass() == Ball.class && context.getStrategy().getName().equals("rotate")){
            CurrentStrategy.updateState(context);
            return;
        }

        //ball  ----> detect the innerCollision
        if(context.getClass() == Ball.class){
            Ball thisBall = (Ball)context;
            for(int i = 0; i < Balls.length; i++){
                if(Balls[i] != thisBall && Balls[i].getClass() == Ball.class && thisBall.detectInnerCollision((Ball) Balls[i])){
                    InnerCollideCmd BallCmd = new InnerCollideCmd();
                    BallCmd.collide(thisBall, (Ball) Balls[i]);
                }
            }
            CurrentStrategy.updateState(context);

        //fish ---> avoid ball
        }else if(context.getClass() == Fish.class){
            Fish thisFish = (Fish)context;
            Point LastPos = thisFish.getLocation();

            // delete the if and keep the for-loop if you want to enable rotating fishes to avoid balss
            if(thisFish.getStrategy().getClass() != RotateStrategy.class){
                for(int i = 0; i < Balls.length; i++){
                    if(thisFish.detectBall((Ball) Balls[i])){
                        InnerCollideCmd FishCmd = new InnerCollideCmd();
                        FishCmd.collide(thisFish, (Ball) Balls[i]);
                    }
                }
            }

            //change the fish direction every 100ms
            CurrentStrategy.updateState(context);
            Point CurPos = thisFish.getLocation();

            if(thisFish.getStrategy().getClass() == RotateStrategy.class && thisFish.getVelocity().x < 0){
//                int tempX = thisFish.getLocation().x;
//                int tempY = thisFish.getLocation().y;
//                thisFish.setNeedflip(false);
//                Point dims = CollisionStore.getCanvasDims();
//                if(tempX > dims.x  && tempY < dims.y ){
//                    thisFish.setDirection(new Point(- CurPos.x + LastPos.x, CurPos.y - LastPos.y));
//                    thisFish.setNeedflip(true);
//                }else if(tempX > dims.x && tempY > dims.y ){
//                    if(thisFish.getNeedflip()){
//                        thisFish.setNeedflip(false);
//                    }
//                    thisFish.setDirection(new Point(- CurPos.x + LastPos.x, CurPos.y - LastPos.y));
//                    thisFish.setNeedflip(false);
//                }else if(tempX < dims.x && tempY > dims.y ){
//                    thisFish.setDirection(new Point(- CurPos.x + LastPos.x, - CurPos.y + LastPos.y));
//                }else if(tempX < dims.x  && tempY < dims.y ){
//                    thisFish.setDirection(new Point(- CurPos.x - LastPos.x,  CurPos.y - LastPos.y));
//                    thisFish.setNeedflip(true);
//                }
                thisFish.setNeedflip(true);
                return;
            }else{
                thisFish.setNeedflip(false);
            }
            thisFish.setDirection(new Point(CurPos.x - LastPos.x, CurPos.y - LastPos.y));

        }








    }
}
