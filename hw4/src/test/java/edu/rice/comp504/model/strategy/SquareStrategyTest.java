package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.CollisionStore;
import edu.rice.comp504.model.paintobj.Ball;
import junit.framework.TestCase;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class SquareStrategyTest extends TestCase {

    @Test
    public void testInitiate() {
        IUpdateStrategy test = SquareStrategy.initiate();
        assertEquals(test.getName(), "square");
    }

    @Test
    public void testGetName() {
        IUpdateStrategy test = SquareStrategy.initiate();
        assertEquals(test.getName(), "square");
    }

    @Test
    public void testUpdateState() {
        CollisionStore.setCanvasDims("800", "800");
        IUpdateStrategy test = SquareStrategy.initiate();

        Ball test1 = Ball.makeBall(SquareStrategy.initiate(), new Point(800,800), false);
        test1.setLocation(new Point(1,1));
        test1.setStep(0);
        test1.setRadius(6);
        test1.setVelocity(new Point(2,2));
        test.updateState(test1);
        assertEquals(test1.getLocation(), new Point(3,1));

        Ball test2 = Ball.makeBall(SquareStrategy.initiate(), new Point(800,800), false);
        test2.setLocation(new Point(1,1));
        test2.setStep(17);
        test2.setRadius(6);
        test2.setVelocity(new Point(2,2));
        test.updateState(test2);
        assertEquals(test2.getLocation(), new Point(1,3));

        Ball test3 = Ball.makeBall(SquareStrategy.initiate(), new Point(800,800), false);
        test3.setLocation(new Point(1,1));
        test3.setStep(20);
        test3.setRadius(6);
        test3.setVelocity(new Point(2,2));
        test.updateState(test3);
        assertEquals(test3.getLocation(), new Point(-1,1));

        Ball test4 = Ball.makeBall(SquareStrategy.initiate(), new Point(800,800), false);
        test4.setLocation(new Point(1,1));
        test4.setStep(26);
        test4.setRadius(6);
        test4.setVelocity(new Point(2,2));
        test.updateState(test4);
        assertEquals(test4.getLocation(), new Point(1,-1));

        Ball test5 = Ball.makeBall(SquareStrategy.initiate(), new Point(800,800), false);
        test5.setLocation(new Point(1,1));
        test5.setStep(30);
        test5.setRadius(6);
        test5.setVelocity(new Point(2,2));
        test.updateState(test5);
        assertEquals(test5.getLocation(), new Point(1,-1));

        Ball test6 = Ball.makeBall(SquareStrategy.initiate(), new Point(800,800), false);
        test6.setLocation(new Point(1,1));
        test6.setStep(30);
        test6.setRadius(6);
        test6.setVelocity(new Point(2,2));
        test.updateState(test6);
        assertEquals(test6.getLocation(), new Point(1,-1));

    }

}