package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.CollisionStore;
import edu.rice.comp504.model.paintobj.Ball;
import edu.rice.comp504.model.strategy.*;
import junit.framework.TestCase;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class SwitchCmdTest extends TestCase {
    StrategyFac factory;

    @Test
    public void testExecute1() {
        CollisionStore.setCanvasDims("800", "800");
        SwitchCmd testS = new SwitchCmd();

        Ball test1 = Ball.makeBall(LineStrategy.initiate(), new Point(800, 800), true);
        test1.setSwitchable(true);
        testS.execute(test1);
        assertEquals(test1.getStrategy().getClass(), LingerStrategy.class);

        Ball test2 = Ball.makeBall(LingerStrategy.initiate(), new Point(800, 800), true);
        test2.setSwitchable(true);
        testS.execute(test2);
        assertEquals(ShakingStrategy.class, test2.getStrategy().getClass());

        Ball test3 = Ball.makeBall(RotateStrategy.initiate(), new Point(800, 800), true);
        test3.setSwitchable(true);
        testS.execute(test3);
        assertEquals(TriangleStrategy.class, test3.getStrategy().getClass());

        Ball test4 = Ball.makeBall(TriangleStrategy.initiate(), new Point(800, 800), true);
        test4.setSwitchable(true);
        testS.execute(test4);
        assertEquals(SquareStrategy.class, test4.getStrategy().getClass());

        Ball test5 = Ball.makeBall(SquareStrategy.initiate(), new Point(800, 800), true);
        test5.setSwitchable(true);
        testS.execute(test5);
        assertEquals(SinStrategy.class, test5.getStrategy().getClass());

        Ball test6 = Ball.makeBall(SinStrategy.initiate(), new Point(800, 800), true);
        test6.setSwitchable(true);
        testS.execute(test6);
        assertEquals(BoundaryStrategy.class, test6.getStrategy().getClass());

        Ball test7 = Ball.makeBall(BoundaryStrategy.initiate(), new Point(800, 800), true);
        test7.setSwitchable(true);
        testS.execute(test7);
        assertEquals(StairStrategy.class, test7.getStrategy().getClass());

        Ball test8 = Ball.makeBall(StairStrategy.initiate(), new Point(800, 800), true);
        test8.setSwitchable(true);
        testS.execute(test8);
        assertEquals(TornadoStrategy.class, test8.getStrategy().getClass());

        Ball test9 = Ball.makeBall(TornadoStrategy.initiate(), new Point(800, 800), true);
        test9.setSwitchable(true);
        testS.execute(test9);
        assertEquals(LineStrategy.class, test9.getStrategy().getClass());


    }

}