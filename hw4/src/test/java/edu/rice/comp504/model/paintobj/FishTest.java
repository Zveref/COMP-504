package edu.rice.comp504.model.paintobj;

import edu.rice.comp504.model.CollisionStore;
import edu.rice.comp504.model.strategy.LineStrategy;
import junit.framework.TestCase;
import org.junit.Test;

import java.awt.*;

public class FishTest extends TestCase {
    Fish test = Fish.makeFish(LineStrategy.initiate(), new Point(800, 800), false);
    Fish test2 = test;
    Ball test3 = Ball.makeBall(LineStrategy.initiate(), new Point(800, 800), false);
    Ball test4 = Ball.makeBall(LineStrategy.initiate(), new Point(800, 800), false);

    @Test
    public void testMakeFish() {
        assertEquals(Fish.makeFish(LineStrategy.initiate(), new Point(800, 800), false).getClass(), test.getClass());
    }

    @Test
    public void testDetectCollisionBoundary() {
        CollisionStore.setCanvasDims("800", "800");
        test.setLocation(new Point(-5, -5));
        test.setVelocity(new Point(-5, -5 ));
        test.setRadius(800);
        assertEquals(test.detectCollisionBoundary(), true);

        test.setLocation(new Point(-5, -5));
        test.setVelocity(new Point(100, -5 ));
        test.setRadius(800);
        assertEquals(test.detectCollisionBoundary(),true);
        assertTrue(test.getVelocity().x >= 0);

        test.setLocation(new Point(-5, -5));
        test.setVelocity(new Point(-5, -5 ));
        test.setRadius(800);
        assertTrue(test.detectCollisionBoundary());
        assertTrue(test.getVelocity().y <= 0);

        test.setLocation(new Point(-5, 5));
        test.setVelocity(new Point(5, 5 ));
        test.setRadius(22);
        assertTrue(!test.detectCollisionBoundary());

        test.setLocation(new Point(-5, 5));
        test.setVelocity(new Point(5, 5 ));
        test.setRadius(222222);
        assertEquals(test.detectCollisionBoundary(), true);

        test.setLocation(new Point(20, 799));
        test.setVelocity(new Point(5, 5 ));
        test.setRadius(5);
        test.detectCollisionBoundary();
        assertEquals(test.getVelocity().y, -5);


    }

    @Test
    public void testDetectBall() {
        test3.setLocation(test.getLocation());
        test4.setLocation(new Point(8000,8000));
        assertEquals(test.detectBall(test3), true);
        assertEquals(test.detectBall(test4), false);
    }

    @Test
    public void testSetStrategy() {
        test.setStrategy(LineStrategy.initiate());
        assertEquals(test.getStrategy().getClass(), LineStrategy.class);
    }

    @Test
    public void testSetDirection() {
        test.setDirection(new Point(20,20));
        assertEquals(test.getDirection(), new Point(20,20));
    }

    @Test
    public void testSetVelocity() {
        test.setVelocity(new Point(0,0));
        assertEquals(test.vel, new Point(0,0));
    }

    @Test
    public void testGetNeedflip() {
        assertEquals(test.getNeedflip(), false);
    }

    @Test
    public void testSetNeedflip() {
        test.setNeedflip(true);
        assertEquals(test.getNeedflip(), true);
    }

    @Test
    public void testIsColorable() {
        assertTrue(!test.isColorable());
    }

    @Test
    public void testGetRadius() {
        test.setRadius(10);
        assertEquals(test.getRadius(), 10);
    }

    @Test
    public void testIsSwitchable() {
        test.setSwitchable(true);
        assertEquals(test.isSwitchable(), true);
    }

    public void testSetStep() {
        test.setStep(1);
        assertEquals(test.getStep(),1);
    }

    @Test
    public void propertyChange() {
    }
}