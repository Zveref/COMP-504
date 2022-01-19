package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.CollisionStore;
import edu.rice.comp504.model.paintobj.Ball;
import junit.framework.TestCase;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class VerticalStrategyTest extends TestCase {

    @Test
    public void testInitiate() {
        IUpdateStrategy test = VerticalStrategy.initiate();
        assertEquals(test.getName(), "vertical");
    }

    @Test
    public void testGetName() {
        IUpdateStrategy test = VerticalStrategy.initiate();
        assertEquals(test.getName(), "vertical");
    }

    @Test
    public void testUpdateState() {
        CollisionStore.setCanvasDims("800", "800");
        IUpdateStrategy test = VerticalStrategy.initiate();

        Ball test1 = Ball.makeBall(VerticalStrategy.initiate(), new Point(800,800), false);
        test1.setLocation(new Point(1,1));
        test1.setStep(0);
        test1.setRadius(6);
        test1.setVelocity(new Point(2,2));
        test.updateState(test1);
        assertEquals(test1.getLocation(), new Point(1,3));
    }

}