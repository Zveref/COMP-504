package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.CollisionStore;
import edu.rice.comp504.model.paintobj.Ball;
import junit.framework.TestCase;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class TornadoStrategyTest extends TestCase {

    @Test
    public void testInitiate() {
        IUpdateStrategy test = TornadoStrategy.initiate();
        assertEquals(test.getName(), "tornado");
    }

    @Test
    public void testGetName() {
        IUpdateStrategy test = TornadoStrategy.initiate();
        assertEquals(test.getName(), "tornado");
    }

    @Test
    public void testUpdateState() {
        CollisionStore.setCanvasDims("800", "800");
        IUpdateStrategy test = TornadoStrategy.initiate();

        Ball test1 = Ball.makeBall(TornadoStrategy.initiate(), new Point(800,800), false);
        test1.setLocation(new Point(1,1));
        test1.setStep(0);
        test1.setRadius(6);
        test1.setVelocity(new Point(2,2));
        test.updateState(test1);
        assertEquals(test1.getLocation(), new Point(21,3));

        Ball test2 = Ball.makeBall(TornadoStrategy.initiate(), new Point(800,800), false);
        test2.setLocation(new Point(1,1));
        test2.setStep(3);
        test2.setRadius(6);
        test2.setVelocity(new Point(2,2));
        test.updateState(test2);
        assertEquals(test2.getLocation(), new Point(6,3));

        Ball test3 = Ball.makeBall(TornadoStrategy.initiate(), new Point(800,800), false);
        test3.setLocation(new Point(1,1));
        test3.setStep(6);
        test3.setRadius(6);
        test3.setVelocity(new Point(2,2));
        test.updateState(test3);
        assertEquals(test3.getLocation(), new Point(6,3));

    }

}