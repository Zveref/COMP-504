package edu.rice.comp504.model.strategy;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.*;

public class StrategyFacTest extends TestCase {

    @Test
    public void testMake() {
        StrategyFac test = new StrategyFac();
        assertEquals(test.make("line").getClass(), LineStrategy.class);
        assertEquals(test.make("rotate").getClass(), RotateStrategy.class);
        assertEquals(test.make("linger").getClass(), LingerStrategy.class);
        assertEquals(test.make("shaking").getClass(), ShakingStrategy.class);
        assertEquals(test.make("square").getClass(), SquareStrategy.class);
        assertEquals(test.make("sin").getClass(), SinStrategy.class);
        assertEquals(test.make("boundary").getClass(), BoundaryStrategy.class);
        assertEquals(test.make("stair").getClass(), StairStrategy.class);
        assertEquals(test.make("tornado").getClass(), TornadoStrategy.class);
        assertEquals(test.make("triangle").getClass(), TriangleStrategy.class);
        assertEquals(test.make(" ").getClass(), NullStrategy.class);
    }

}