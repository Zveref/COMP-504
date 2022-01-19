package edu.rice.comp504.model.paintobj;

import edu.rice.comp504.model.CollisionStore;
import edu.rice.comp504.model.strategy.LineStrategy;
import junit.framework.TestCase;
import org.junit.Test;

import java.awt.*;

public class BallTest extends TestCase {
    Ball test = Ball.makeBall(LineStrategy.initiate(), new Point(800, 800), false);
    Ball test2 = test;
    @Test
    public void testIsColorable() {
        assertTrue(test.isColorable());
    }

    @Test
    public void testSetColor() {
        test.setColor("black");
        assertEquals(test.getColor(), "black" );
        assertTrue(test.isColorable());
    }

    @Test
    public void testMakeBall() {
        assertEquals(Ball.makeBall(LineStrategy.initiate(), new Point(800, 800), false).getClass(), test.getClass());
    }

    @Test
    public void testGetRadius() {
        test.radius = 5;
        assertTrue(test.getRadius() == 5);
    }

    @Test
    public void testSetRadius() {
        test.setRadius(5);
        assertEquals(test.radius, 5);
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
    }

    @Test
    public void testDetectInnerCollision() {
        test2.setRadius(100);
        assertTrue(test.detectInnerCollision(test2));
    }

    @Test
    public void testSetCharacter() {
        test.setCharacter("normal");
        assertTrue(test.getCharacter().equals("normal"));
    }

    @Test
    public void testGetCharacter() {
        assertTrue(test.getCharacter().equals("normal"));
    }

    @Test
    public void testSetStep() {
        test.setStep(6);
        assertEquals(test.getStep(), 6);
    }

    @Test
    public void testGetIsowned() {
        test2.setIsowned(true);
        assertTrue(test2.getIsowned());
    }

    @Test
    public void testSetIsowned() {
        test2.setIsowned(true);
        assertTrue(test2.getIsowned());
    }

    @Test
    public void testOwnThis() {
        test.ownThis(test2);
        assertTrue(test.getOwnlist().size() != 0);
    }

    @Test
    public void testGetType() {
        assertTrue(test.getType().equals("ball"));
    }

    @Test
    public void testNextLocation() {
        test.setLocation(new Point(0,0));
        test.nextLocation(10, 10);
        assertEquals(test.getLocation(), new Point(10, 10));
    }

    @Test
    public void testSetStrategy() {
        test.setStrategy(LineStrategy.initiate());
        assertTrue(test.getStrategy().getName().equals("line"));
    }

    public void testIsSwitchable() {
        test.setSwitchable(true);
        assertEquals(test.isSwitchable(), true);
    }


    @Test
    public void getOwnlist() {
        assertTrue(test.getOwnlist().size() != 0);
    }
}