package edu.rice.comp504.model;

import edu.rice.comp504.adapter.DispatchAdapter;
import edu.rice.comp504.model.paintobj.APaintObject;
import edu.rice.comp504.model.paintobj.Ball;
import edu.rice.comp504.model.paintobj.NullObject;
import edu.rice.comp504.model.strategy.LineStrategy;
import edu.rice.comp504.model.strategy.LingerStrategy;
import edu.rice.comp504.model.strategy.StrategyFac;
import edu.rice.comp504.util.RandUtil;
import junit.framework.TestCase;
import org.junit.Test;

import java.awt.*;
import java.beans.PropertyChangeListener;

import static org.junit.Assert.*;

public class CollisionStoreTest extends TestCase {
    CollisionStore test = new CollisionStore();
    @Test public void testGetOnlyFac() {
        assertEquals(CollisionStore.getOnlyFac().getClass(), StrategyFac.class);
    }

    @Test
    public void testGetCanvasDims() {
        CollisionStore.setCanvasDims("800","800");
        Point dims = CollisionStore.getCanvasDims();
        assertEquals(dims.x, 800);
    }

    @Test
    public void setCanvasDims() {

    }

    @Test
    public void testLoadObj() {
        CollisionStore.setCanvasDims("800", "800");
        APaintObject test1 = test.loadObj("line", "ball", "normal", true);
        assertEquals((test1).getType(), "ball");

        APaintObject test2 = test.loadObj("sin", "ball","normal", true);
        assertEquals((test2).getType(), "ball");

        APaintObject test3 = test.loadObj("rotate", "ball","normal", true);
        assertEquals((test3).getType(), "ball");

        APaintObject test4 = test.loadObj("sin", "fish","normal", true);
        assertEquals((test4).getType(), "fish");

        APaintObject test5 = test.loadObj("rotate", "fish","normal", true);
        assertEquals(test5.getLocation(), new Point(300,500));

        APaintObject test6 = test.loadObj("linger", "fish","normal", true);
        assertEquals(test6.getStrategy().getClass(), LineStrategy.class);

        APaintObject test7 = test.loadObj(" ", " ", "normal",true);
        assertEquals(test7.getClass(), NullObject.class);
    }

    @Test
    public void testUpdateWorld() {
        CollisionStore.setCanvasDims("800", "800");
        test.loadObj("rotate", "ball","normal", true);
        PropertyChangeListener[] test8 = test.updateWorld();
        assertEquals(test8.length, 1);
    }

    @Test
    public void testSwitchStrategy() {
        CollisionStore test0 = new CollisionStore();
        CollisionStore.setCanvasDims("800", "800");
        test0.loadObj("line", "ball","normal",true);
        test0.switchStrategy();
        APaintObject test9 = (APaintObject) test0.getType("ball")[0];
        assertEquals(test9.getStrategy().getClass(), LingerStrategy.class);

    }

    @Test
    public void testRemoveListeners() {
        CollisionStore test0 = new CollisionStore();
        CollisionStore.setCanvasDims("800", "800");
        test0.loadObj("line", "ball", "normal",true);
        test0.loadObj("line", "ball","normal", true);
        test0.loadObj("line", "ball", "normal",true);
        test0.loadObj("line", "ball", "normal",true);
        test0.loadObj("line", "ball", "normal",true);
        test0.loadObj("line", "fish", "normal",true);
        test0.loadObj("line", "fish", "normal",true);
        test0.removeListeners("line", "ball");
        test0.removeListeners("line", "fish");
        assertEquals(test0.getType("ball").length, 0);
        assertEquals(test0.getType("fish").length, 0);

        test0.loadObj("line", "ball", "normal",true);
        test0.loadObj("line", "ball", "normal",true);
        test0.loadObj("line", "ball", "normal",true);
        test0.loadObj("line", "ball", "normal",true);
        test0.removeListeners("line", "all");
        assertEquals(test0.getType("all").length, 0);

    }
}