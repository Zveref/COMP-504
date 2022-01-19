package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.CollisionStore;
import edu.rice.comp504.model.paintobj.Ball;
import junit.framework.TestCase;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class StairStrategyTest extends TestCase {


    @Test
    public void testInitiate() {
        IUpdateStrategy test = StairStrategy.initiate();
        assertEquals(test.getName(), "stair");
    }

    @Test
    public void testGetName() {
        IUpdateStrategy test = StairStrategy.initiate();
        assertEquals(test.getName(), "stair");
    }

    @Test
    public void testUpdateState() {
        CollisionStore.setCanvasDims("800", "800");
        IUpdateStrategy test = StairStrategy.initiate();

        Ball test1 = Ball.makeBall(StairStrategy.initiate(), new Point(800,800), false);
        test1.setLocation(new Point(1,1));
        test1.setStep(0);
        test1.setRadius(6);
        test1.setVelocity(new Point(2,2));
        test.updateState(test1);
        assertEquals(test1.getLocation(), new Point(3,1));

        Ball test2 = Ball.makeBall(StairStrategy.initiate(), new Point(800,800), false);
        test2.setLocation(new Point(1,1));
        test2.setStep(5);
        test2.setRadius(6);
        test2.setVelocity(new Point(2,2));
        test.updateState(test2);
        assertEquals(test2.getLocation(), new Point(1,3));

    }

}