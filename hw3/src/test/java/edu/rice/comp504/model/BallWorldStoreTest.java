package edu.rice.comp504.model;

import edu.rice.comp504.adapter.DispatchAdapter;
import edu.rice.comp504.model.ball.Ball;
import edu.rice.comp504.model.strategy.*;
import edu.rice.comp504.util.RandUtil;
import junit.framework.TestCase;
import org.junit.Test;

import java.awt.*;
import java.util.Random;

import static org.junit.Assert.*;

public class BallWorldStoreTest extends TestCase {

    @Test
    public void testUpdateBallWorld() {
        BallWorldStore.setCanvasDims(new Point(800,800));

        Ball ball1 = new Ball(new Point(500, 400), 10, new Point(10,10), "red");
        ball1.setStrategy(HorizontalStrategy.initiate());
        ball1.getStrategy().updateState(ball1);


        Ball ball2 = new Ball(new Point(500, 400), 10, new Point(10,10), "yellow");
        ball2.setStrategy(CompositeStrategy.initiate());
        ball2.getStrategy().updateState(ball2);

        Ball ball3 = new Ball(new Point(500, 400), 10, new Point(10,10), "red");
        ball3.setStrategy(LingerStrategy.initiate());
        ball3.getStrategy().updateState(ball3);

        Ball ball4 = new Ball(new Point(500, 400), 10, new Point(10,10), "red");
        ball4.setStrategy(RotateStrategy.initiate());
        ball4.getStrategy().updateState(ball4);

        Ball ball5 = new Ball(new Point(500, 500), 10, new Point(10,10), "red");
        ball5.setStrategy(ShakingStrategy.initiate());
        ball5.getStrategy().updateState(ball5);

        Ball ball6 = new Ball(new Point(500, 400), 10, new Point(10,10), "red");
        ball6.setStrategy(VerticalStrategy.initiate());
        ball6.getStrategy().updateState(ball6);

        assertEquals(new Point(510,400), ball1.getLocation());
        assertEquals(new Point(510,410), ball2.getLocation());
        assertEquals(new Point(500,400), ball3.getLocation());
        assertEquals(new Point(450,313), ball4.getLocation());
        assertEquals(new Point(645,500), ball5.getLocation());
        assertEquals(new Point(500,410), ball6.getLocation());


    }

    @Test
    public void testLoadBall() {
        BallWorldStore TestStore = new BallWorldStore();
        String[] Select = {"composite", "horizontal", "vertical", "linger", "rotate", "shaking"};
        int Index = RandUtil.getRnd(0,5);
        Ball TestBall = TestStore.loadBall(Select[Index], "Anytype");
        assertSame(Select[Index], TestBall.getStrategy().getName());
    }
}