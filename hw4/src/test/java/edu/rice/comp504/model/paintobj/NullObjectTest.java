package edu.rice.comp504.model.paintobj;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.*;

public class NullObjectTest extends TestCase {

    @Test
    public void testMake() {
        NullObject test = edu.rice.comp504.model.paintobj.NullObject.make();
        assertEquals(test.getClass(), NullObject.class);
    }

    @Test
    public void testdDetectCollisionBoundary() {
        NullObject test = edu.rice.comp504.model.paintobj.NullObject.make();
        assertEquals( test.detectCollisionBoundary(), false);
    }

    @Test
    public void propertyChange() {

    }
}