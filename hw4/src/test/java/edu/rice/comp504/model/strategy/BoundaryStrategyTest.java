package edu.rice.comp504.model.strategy;

import edu.rice.comp504.adapter.DispatchAdapter;
import edu.rice.comp504.model.CollisionStore;
import edu.rice.comp504.model.paintobj.Ball;
import junit.framework.TestCase;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class BoundaryStrategyTest extends TestCase {

    @Test
    public void testInitiate() {
        IUpdateStrategy test = BoundaryStrategy.initiate();
        assertEquals(test.getName(), "boundary");

    }

    @Test
    public void testGetName() {
    }

    @Test
    public void testUpdateState() {
        CollisionStore.setCanvasDims("800", "800");
        IUpdateStrategy test = BoundaryStrategy.initiate();

        Ball test1 = Ball.makeBall(BoundaryStrategy.initiate(), new Point(800,800), false);
        test1.setLocation(new Point(1,1));
        test1.setStep(0);
        test1.setRadius(6);
        test1.setVelocity(new Point(2,2));
        test.updateState(test1);
        assertEquals(test1.getStep(), 0);

        Ball test2 = Ball.makeBall(BoundaryStrategy.initiate(), new Point(800,800), false);
        test2.setLocation(new Point(799,1));
        test2.setStep(0);
        test2.setRadius(6);
        test2.setVelocity(new Point(2,2));
        test.updateState(test2);
        assertEquals(test2.getStep(), 1);

        Ball test3 = Ball.makeBall(BoundaryStrategy.initiate(), new Point(800,800), false);
        test3.setLocation(new Point(1,1));
        test3.setStep(1);
        test3.setRadius(6);
        test3.setVelocity(new Point(2,2));
        test.updateState(test3);
        assertEquals(test3.getStep(), 1);

        Ball test4 = Ball.makeBall(BoundaryStrategy.initiate(), new Point(800,800), false);
        test4.setLocation(new Point(1,799));
        test4.setStep(1);
        test4.setRadius(6);
        test4.setVelocity(new Point(2,2));
        test.updateState(test4);
        assertEquals(test4.getStep(), 0);

        Ball test5 = Ball.makeBall(BoundaryStrategy.initiate(), new Point(800,800), false);
        test5.setLocation(new Point(1,799));
        test5.setStep(33);
        test5.setRadius(6);
        test5.setVelocity(new Point(2,2));
        test.updateState(test5);
        assertEquals(test5.getStep(), 0);
    }
}