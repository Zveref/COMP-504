package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.CollisionStore;
import edu.rice.comp504.model.paintobj.APaintObject;
import edu.rice.comp504.model.paintobj.Fish;
import edu.rice.comp504.util.RandUtil;

import java.awt.*;

public class LingerStrategy implements IUpdateStrategy{
    private static IUpdateStrategy ONLY;
    private static Point dims;

    private LingerStrategy() {
        dims = CollisionStore.getCanvasDims();
    }

    public static IUpdateStrategy initiate(){
        if(ONLY == null){
            ONLY = new LingerStrategy();
        }
        return ONLY;
    }

    @Override
    public String getName() {
        return "linger";
    }

    @Override
    public void updateState(APaintObject context) {
        context.setLocation(new Point(context.getLocation().x, context.getLocation().y));
        if(context.getClass() == Fish.class){
            return;
        }
        context.setColor(RandUtil.getColor());
    }
}
