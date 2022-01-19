package edu.rice.comp504.model;

import edu.rice.comp504.model.cmd.SwitchCmd;
import edu.rice.comp504.model.cmd.UpdateStateCmd;
import edu.rice.comp504.model.paintobj.APaintObject;
import edu.rice.comp504.model.paintobj.Ball;
import edu.rice.comp504.model.paintobj.Fish;
import edu.rice.comp504.model.paintobj.NullObject;
import edu.rice.comp504.model.strategy.LineStrategy;
import edu.rice.comp504.model.strategy.NullStrategy;
import edu.rice.comp504.model.strategy.StrategyFac;
import edu.rice.comp504.util.RandUtil;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


public class CollisionStore {
    private final PropertyChangeSupport pcs;
    private static Point dims;
    private static StrategyFac ONLY;

    public static StrategyFac getOnlyFac(){
        if (ONLY == null){
            ONLY = new StrategyFac();
        }
        return ONLY;
    }


    public CollisionStore() {
        pcs = new PropertyChangeSupport(this);
    }


    public static Point getCanvasDims() {
        return dims;
    }


    public static void setCanvasDims(String cHeight, String cWidth) {
        dims = new Point(Integer.parseInt(cHeight), Integer.parseInt((cWidth)));
    }




    public APaintObject loadObj(String strategy, String type,String character, Boolean switchable) {
        if(type.equals("ball")) {
            Ball thisBall = Ball.makeBall(getOnlyFac().make(strategy), dims, switchable);
            thisBall.setSwitchable(switchable);
            thisBall.setCharacter(RandUtil.getCharacter());
            switch(strategy) {
                case "sin":
                    thisBall.setLocation(new Point(thisBall.getRadius(), RandUtil.getRnd(100, dims.y -200)));
                    thisBall.setStep(thisBall.getLocation().y);
                    break;
                case "rotate":
                    //in case of wired movements
                    thisBall.setLocation(new Point(300, 500));
                    thisBall.setCharacter(RandUtil.getCharacter());
                    break;

            }
            this.pcs.addPropertyChangeListener("ball", thisBall);
            if(!character.equals("random")){
                thisBall.setCharacter(character);
            }
            return thisBall;
        }else if(type.equals("fish")){
            Fish thisFish = Fish.makeFish(getOnlyFac().make(strategy), dims, switchable);
            thisFish.setSwitchable(switchable);
            switch(strategy) {
                case "sin":
                    thisFish.setLocation(new Point(thisFish.getRadius(), RandUtil.getRnd(100, dims.y - 200)));
                    thisFish.setStep(thisFish.getLocation().y);
                    break;
                case "rotate":
                    //in case of wired movements
                    thisFish.setLocation(new Point(300, 500));
                    thisFish.setVelocity(new Point(Math.abs(thisFish.getVelocity().x), thisFish.getVelocity().y));
                    break;
                case "linger":
                    thisFish.setStrategy(LineStrategy.initiate());
                    thisFish.setVelocity(new Point(0,0));
                    break;
            }

            this.pcs.addPropertyChangeListener("fish", thisFish);
            return thisFish;
        }else{
            return NullObject.make();
        }
    }


    public PropertyChangeListener[] updateWorld() {
        UpdateStateCmd tempCmd = new UpdateStateCmd(pcs.getPropertyChangeListeners("ball"), pcs.getPropertyChangeListeners("fish"));
        pcs.firePropertyChange("ball", null, tempCmd);
        pcs.firePropertyChange("fish", null, tempCmd);
        return pcs.getPropertyChangeListeners();
    }



    public void switchStrategy() {
        SwitchCmd SCmd = new SwitchCmd();
        PropertyChangeListener[] pclBall = pcs.getPropertyChangeListeners("ball");
        PropertyChangeListener[] pclFish = pcs.getPropertyChangeListeners("fish");
        for(PropertyChangeListener pcl1 : pclBall){
                APaintObject tempBall = (APaintObject)pcl1;
                SCmd.execute(tempBall);
        }
        for(PropertyChangeListener pcl2 : pclFish){
                APaintObject tempFish = (APaintObject)pcl2;
                SCmd.execute(tempFish);
        }
    }



    public void removeListeners(String strategy, String type) {
        PropertyChangeListener[] pclBall = pcs.getPropertyChangeListeners("ball");
        PropertyChangeListener[] pclFish = pcs.getPropertyChangeListeners("fish");
        PropertyChangeListener[] pclAll = pcs.getPropertyChangeListeners();
        if(type.equals("ball")) {
            for(PropertyChangeListener pcl: pclBall){
                if(((APaintObject)pcl).getStrategy().getName().equals(strategy)){
                    pcs.removePropertyChangeListener("ball", pcl);
                }
            }
        }else if(type.equals("fish")){
            for(PropertyChangeListener pcl: pclFish){
                if(  ((APaintObject)pcl).getStrategy().getName().equals(strategy)){
                    pcs.removePropertyChangeListener("fish", pcl);
                }
            }
        }
        else if(type.equals("all")){
            for(PropertyChangeListener pcl: pclAll){
                pcs.removePropertyChangeListener(pcl);
            }
        }
    }

    //help testing the switch and remove function in CollisionStire
    public PropertyChangeListener[] getType(String type){
        if(type.equals("ball") || type.equals("fish")){
            return pcs.getPropertyChangeListeners(type);
        }else if(type.equals("all")){
            return pcs.getPropertyChangeListeners();
        }
        return null;
    }
}
