package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.CollisionStore;
import edu.rice.comp504.model.paintobj.Ball;
import junit.framework.TestCase;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class ShakingStrategyTest extends TestCase {

    @Test
    public void testInitiate() {
        IUpdateStrategy test = ShakingStrategy.initiate();
        assertEquals(test.getName(), "shaking");
    }

    @Test
    public void testGetName() {
        IUpdateStrategy test = ShakingStrategy.initiate();
        assertEquals(test.getName(), "shaking");
    }

    @Test
    public void testUpdateState() {
        CollisionStore.setCanvasDims("800", "800");
        IUpdateStrategy test = ShakingStrategy.initiate();

        Ball test1 = Ball.makeBall(ShakingStrategy.initiate(), new Point(800,800), false);
        test1.setLocation(new Point(1,1));
        test1.setStep(0);
        test1.setRadius(6);
        test1.setVelocity(new Point(2,2));
        test.updateState(test1);
        assertEquals(test1.getLocation(), new Point(1,21));

        Ball test2 = Ball.makeBall(ShakingStrategy.initiate(), new Point(800,800), false);
        test2.setLocation(new Point(1,1));
        test2.setStep(6);
        test2.setRadius(6);
        test2.setVelocity(new Point(2,2));
        test.updateState(test2);
        assertEquals(test2.getLocation(), new Point(1,6));

        Ball test3 = Ball.makeBall(ShakingStrategy.initiate(), new Point(800,800), false);
        test3.setLocation(new Point(1,1));
        test3.setStep(200);
        test3.setRadius(6);
        test3.setVelocity(new Point(2,2));
        test.updateState(test3);
        assertEquals(test3.getLocation(), new Point(1,6));

        Ball test4 = Ball.makeBall(ShakingStrategy.initiate(), new Point(800,800), false);
        test4.setLocation(new Point(1,1));
        test4.setStep(2);
        test4.setRadius(6);
        test4.setVelocity(new Point(-5,-5));
        test.updateState(test4);
        assertEquals(test4.getLocation(), new Point(1,26));

        Ball test5 = Ball.makeBall(ShakingStrategy.initiate(), new Point(800,800), false);
        test5.setLocation(new Point(1,1));
        test5.setStep(200);
        test5.setRadius(3);
        test5.setVelocity(new Point(2,2));
        test.updateState(test5);
        assertEquals(test5.getLocation(), new Point(1,3));
    }

}