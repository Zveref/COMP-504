package edu.rice.comp504.adapter;

import edu.rice.comp504.model.ball.Ball;
import junit.framework.TestCase;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class DispatchAdapterTest extends TestCase {

    @Test
    public void testLoadBall() {
        DispatchAdapter ds = new DispatchAdapter();
        int oldLength = ds.AllBalls.size();
        ds.loadBall("Anystrategy", "Anytype");
        int newLength = ds.AllBalls.size();
        assertEquals(newLength - oldLength, 1);
    }

    @Test
    public void testRemoveAll() {
        DispatchAdapter ds = new DispatchAdapter();
        ds.loadBall("Anystrategy", "Anytype");
        ds.loadBall("Anystrategy", "Anytype");
        ds.loadBall("Anystrategy", "Anytype");
        ds.loadBall("Anystrategy", "Anytype");
        ds.loadBall("Anystrategy", "Anytype");
        ds.loadBall("Anystrategy", "Anytype");
        ds.removeAll();
        int size = ds.AllBalls.size();
        assertEquals(size, 0);
    }
}