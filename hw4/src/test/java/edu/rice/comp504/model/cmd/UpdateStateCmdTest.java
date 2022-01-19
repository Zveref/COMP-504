package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.CollisionStore;
import edu.rice.comp504.model.paintobj.Ball;
import edu.rice.comp504.model.paintobj.Fish;
import edu.rice.comp504.model.strategy.LineStrategy;
import edu.rice.comp504.model.strategy.RotateStrategy;
import junit.framework.TestCase;
import org.junit.Test;

import javax.sound.sampled.Line;
import java.awt.*;
import java.beans.PropertyChangeListener;

import static org.junit.Assert.*;

public class UpdateStateCmdTest extends TestCase {

    @Test
    public void testExecute() {
        CollisionStore.setCanvasDims("800", "800");
        Ball test1 = Ball.makeBall(LineStrategy.initiate(), new Point(800,800), false);
        test1.setLocation(new Point(25,20));
        test1.setVelocity(new Point(1,0));
        test1.setRadius(5);
        test1.setStep(0);

        Ball test2 = Ball.makeBall(LineStrategy.initiate(), new Point(800,800), false);
        test2.setLocation(new Point(15,20));
        test2.setVelocity(new Point(-1,0));
        test2.setRadius(5);
        test2.setStep(0);

        Fish test3 = Fish.makeFish(LineStrategy.initiate(), new Point(800,800), false);
        test3.setLocation(new Point(700,700));
        test3.setVelocity(new Point(1,0));
        test3.setRadius(5);
        test3.setStep(0);

        Fish test4  = Fish.makeFish(LineStrategy.initiate(), new Point(800,800), false);
        test4.setLocation(new Point(691,700));
        test4.setVelocity(new Point(-1,0));
        test4.setRadius(5);
        test4.setStep(0);

        Fish test5  = Fish.makeFish(RotateStrategy.initiate(), new Point(800,800), false);
        test5.setLocation(new Point(100,100));
        test5.setVelocity(new Point(-8,0));
        test5.setRadius(5);
        test5.setStep(0);

        Fish test6  = Fish.makeFish(LineStrategy.initiate(), new Point(800,800), false);
        test6.setLocation(new Point(16,24));
        test6.setVelocity(new Point(-8,0));
        test6.setRadius(10);
        test6.setStep(0);


        PropertyChangeListener[] Balls = {test2};
        PropertyChangeListener[] Fishes = {test3};
        UpdateStateCmd Cmd = new UpdateStateCmd(Balls, Fishes);

        Cmd.execute(test1);
        assertEquals(test1.getVelocity().x, 1);

        Cmd.execute(test4);
        assertEquals(test4.getVelocity().x, -1);

        Cmd.execute(test5);
        assertEquals(test5.getLocation().x, 678);

        Cmd.execute(test6);
        assertEquals(test6.getVelocity().x, 1);

    }
}