package edu.rice.comp504.model.cmd;

import edu.rice.comp504.adapter.DispatchAdapter;
import edu.rice.comp504.model.CollisionStore;
import edu.rice.comp504.model.paintobj.APaintObject;
import edu.rice.comp504.model.paintobj.Ball;
import edu.rice.comp504.model.paintobj.Fish;
import edu.rice.comp504.model.paintobj.NullObject;
import edu.rice.comp504.model.strategy.NullStrategy;
import edu.rice.comp504.model.strategy.RotateStrategy;
import edu.rice.comp504.util.CalcuUtil;
import edu.rice.comp504.util.RandUtil;

import java.awt.*;
import java.util.ArrayList;


public class InnerCollideCmd implements ConlliCmd{
    private Point dims;


    public InnerCollideCmd(){
    }



    public void collide(APaintObject Obj1, APaintObject Obj2){
        double speed = CalcuUtil.getSpeed(Obj1);

        //fish avoid ball
        if(Obj1.getClass() == Fish.class && Obj2.getClass() == Ball.class){
            Obj1.setVelocity(CollisionVel(Obj1, Obj2, speed));
            return;
        }

        //ball inner collision
        dims = CollisionStore.getCanvasDims();
        Ball ball1 = (Ball)Obj1;
        Ball ball2 = (Ball)Obj2;
        if(ball1.getStrategy() == RotateStrategy.initiate()){
            ball1.setVelocity(new Point(ball1.getVelocity().x * -1, ball1.getVelocity().y));
        }
        switch (ball1.getCharacter()){
            case "normal":
                ball1.setVelocity(CollisionVel(ball1, ball2, speed));
                break;
            case "fatter":
                ball1.setVelocity(CollisionVel(ball1, ball2, speed));
                if(ball1.getRadius() <= 30){
                    ball1.setRadius(ball1.getRadius() + 2);
                }
                break;
            case "slimmer":
                ball1.setVelocity(CollisionVel(ball1, ball2, speed));
                if(ball1.getRadius() >= 3){
                    ball1.setRadius(ball1.getRadius() - 2);
                }
                break;
            case "faster":
                if(speed <= 20){
                    speed += 2;
                }
                ball1.setVelocity(CollisionVel(ball1, ball2, speed));
                break;
            case "slower":
                if(speed >= 3){
                    speed -=2;
                }
                ball1.setVelocity(CollisionVel(ball1, ball2, speed));
                break;
            case "colorful":
                ball1.setVelocity(CollisionVel(ball1, ball2, speed));
                ball1.setColor(RandUtil.changeColor(ball1.getColor()));
                break;
            case "vanish":
                ball1.setVelocity(CollisionVel(ball1, ball2, CalcuUtil.getSpeed(ball2)));
                ball1.setLocation(new Point(0,0));
                ball1.setVelocity(new Point(0,0));
                ball1.setRadius(0);
                ball1.setSwitchable(false);
                ball1.setVelocity(new Point(0, 0));
                ball1.setIsowned(true);
                break;
            case "still":
                ball1.setVelocity(new Point(0, 0));
                break;
            case "hungry":
                if(ball2.getCharacter().equals(ball1.getCharacter())){
                    if(ball1.getRadius() == ball2.getRadius()){
                        ball1.setVelocity(CollisionVel(ball1, ball2, speed));

                    }else if(ball1.getRadius() > ball2.getRadius() && ! ball2.getIsowned()){
                        ball1.ownThis(ball2);
                        ball2.setIsowned(true);
                        ball2.setSwitchable(false);
                        ball2.setStrategy(NullStrategy.initiate());
                    }
                }else{
                    ball1.setVelocity(CollisionVel(ball1, ball2, speed));
                }
                ArrayList<Ball> preylist = ball1.getOwnlist();
                if(preylist.size() != 0){
                    for(Ball temp: preylist){
                        temp.setLocation(ball1.getLocation());
                    }
                }
                break;
            case "fusion":
                if(ball1.getCharacter().equals(ball2.getCharacter())){
                    ball1.setLocation(new Point((ball1.getLocation().x + ball2.getLocation().x) / 2, (ball1.getLocation().y + ball2.getLocation().y) / 2));
                    ball1.setRadius( (ball1.getRadius() + ball2.getRadius()) < 50 ? (ball1.getRadius() + ball2.getRadius()) : 50 );
                    ball1.setVelocity(new Point(ball1.getVelocity().x + ball2.getVelocity().x, ball1.getVelocity().y + ball2.getVelocity().y));
                    ball2.setLocation(new Point(0,0));
                    ball2.setRadius(0);
                    ball2.setSwitchable(false);
                    ball2.setVelocity(new Point(0, 0));
                    ball2.setIsowned(true);
                    ball2.setStrategy(NullStrategy.initiate());
                }else{
                    ball1.setVelocity(CollisionVel(ball1, ball2, speed));
                }
                break;

        }
    }



    //update velocity after colliding
    private Point CollisionVel(APaintObject Object1, APaintObject Object2, double speed){
        //new Velocity could possibly be rounded to 0(double --> int), here to avoid that:
        if(speed * CalcuUtil.getCos(Object1.getLocation(), Object2.getLocation()) < 0 && speed * CalcuUtil.getCos(Object1.getLocation(), Object2.getLocation()) >= -1){
            if(speed * CalcuUtil.getSin(Object1.getLocation(), Object2.getLocation()) < 0 && speed * CalcuUtil.getSin(Object1.getLocation(), Object2.getLocation()) >= -1){
                return (new Point(-1, -1));
            }else if(speed * CalcuUtil.getSin(Object1.getLocation(), Object2.getLocation()) > 0 && speed * CalcuUtil.getSin(Object1.getLocation(), Object2.getLocation()) <= 1){
                return (new Point(-1, 1));
            }
        }else if(speed * CalcuUtil.getCos(Object1.getLocation(), Object2.getLocation()) > 0 && speed * CalcuUtil.getCos(Object1.getLocation(), Object2.getLocation()) <= 1){
            if(speed * CalcuUtil.getSin(Object1.getLocation(), Object2.getLocation()) < 0 && speed * CalcuUtil.getSin(Object1.getLocation(), Object2.getLocation()) >= -1){
                return (new Point(1, -1));
            }else if(speed * CalcuUtil.getSin(Object1.getLocation(), Object2.getLocation()) > 0 && speed * CalcuUtil.getSin(Object1.getLocation(), Object2.getLocation()) <= 1) {
                return (new Point(1, 1));
            }
        }

        //new velocity based on collision degree
        return ( new Point((int) ( speed * CalcuUtil.getCos(Object1.getLocation(), Object2.getLocation()) ), (int) ( speed * CalcuUtil.getSin(Object1.getLocation(), Object2.getLocation()) )) );

    }
}


