package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.paintobj.Ball;
import edu.rice.comp504.model.paintobj.Fish;
import edu.rice.comp504.model.strategy.LineStrategy;
import edu.rice.comp504.model.strategy.LingerStrategy;
import edu.rice.comp504.model.strategy.RotateStrategy;
import junit.framework.TestCase;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class InnerCollideCmdTest extends TestCase {
    InnerCollideCmd CO = new InnerCollideCmd();

    Point dims = new Point(800, 800);
    @Test
    public void testCollide() {
        Fish fish1 = Fish.makeFish(LineStrategy.initiate(), dims, false);
        fish1.setLocation(new Point(500,400));
        fish1.setVelocity(new Point(-1,0));
        fish1.setRadius(300);

        Ball ball1 = Ball.makeBall(LineStrategy.initiate(), dims, false);
        ball1.setLocation(new Point(300,400));
        ball1.setVelocity(new Point(1,0));
        ball1.setRadius(300);
        CO.collide(fish1,ball1);
        assertNotEquals(fish1.getVelocity().x, -1);

        Ball ball2 = Ball.makeBall(LineStrategy.initiate(), dims, false);
        ball2.setLocation(new Point(500,400));
        ball2.setVelocity(new Point(-1,0));
        ball2.setRadius(300);
        ball2.setCharacter("normal");
        CO.collide(ball2,ball1);
        assertNotEquals(ball2.getVelocity().x, -1);

        Ball ball3 = Ball.makeBall(LineStrategy.initiate(), dims, false);
        ball3.setLocation(new Point(500,400));
        ball3.setVelocity(new Point(-1,0));
        ball3.setRadius(2);
        ball3.setCharacter("fatter");
        CO.collide(ball3,ball1);
        assertNotEquals(ball3.getRadius(), 2);

        Ball ball4 = Ball.makeBall(LineStrategy.initiate(), dims, false);
        ball4.setLocation(new Point(500,400));
        ball4.setVelocity(new Point(-1,0));
        ball4.setRadius(30);
        ball4.setCharacter("slimmer");
        CO.collide(ball4,ball1);
        assertNotEquals(ball4.getRadius(), 30);

        Ball ball5 = Ball.makeBall(LineStrategy.initiate(), dims, false);
        ball5.setLocation(new Point(500,400));
        ball5.setVelocity(new Point(-1,0));
        ball5.setRadius(300);
        ball5.setCharacter("faster");
        CO.collide(ball5,ball1);
        assertNotEquals(ball5.getVelocity().x, -1);

        Ball ball6 = Ball.makeBall(LineStrategy.initiate(), dims, false);
        ball6.setLocation(new Point(500,400));
        ball6.setVelocity(new Point(-30,0));
        ball6.setRadius(300);
        ball6.setCharacter("slower");
        CO.collide(ball6,ball1);
        assertNotEquals(ball6.getVelocity().x, -30);

        Ball ball7 = Ball.makeBall(LineStrategy.initiate(), dims, false);
        ball7.setLocation(new Point(500,400));
        ball7.setVelocity(new Point(-1,0));
        ball7.setRadius(300);
        ball7.setColor("black");
        ball7.setCharacter("colorful");
        CO.collide(ball7,ball1);
        assertNotEquals(ball7.getColor(), "black");

        Ball bal8 = Ball.makeBall(LineStrategy.initiate(), dims, false);
        bal8.setLocation(new Point(500,400));
        bal8.setVelocity(new Point(-1,0));
        bal8.setRadius(300);
        bal8.setCharacter("vanish");
        CO.collide(bal8,ball1);
        assertNotEquals(bal8.getRadius(), 300);

        Ball ball9 = Ball.makeBall(LineStrategy.initiate(), dims, false);
        ball9.setLocation(new Point(500,400));
        ball9.setVelocity(new Point(-1,0));
        ball9.setRadius(300);
        ball9.setCharacter("still");
        CO.collide(ball9,ball1);
        assertEquals(ball9.getLocation(), new Point(500,400));


        //hungary
        Ball ball10 = Ball.makeBall(LineStrategy.initiate(), dims, false);
        ball10.setLocation(new Point(500,400));
        ball10.setVelocity(new Point(-1,0));
        ball10.setRadius(300);
        ball10.setIsowned(false);
        ball10.setCharacter("hungry");
        CO.collide(ball10,ball1);
        assertEquals(false, ball10.getIsowned());

        Ball ball11 = Ball.makeBall(LineStrategy.initiate(), dims, false);
        ball11.setLocation(new Point(500,400));
        ball11.setVelocity(new Point(1,0));
        ball11.setRadius(200);
        ball11.setIsowned(false);
        ball11.setCharacter("hungry");
        CO.collide(ball10,ball11);
        assertEquals(ball11.getIsowned(), true);
        assertEquals(ball10.getOwnlist().size(), 1);

        Ball ball12 = Ball.makeBall(LineStrategy.initiate(), dims, false);
        ball12.setLocation(new Point(300,400));
        ball12.setVelocity(new Point(1,0));
        ball12.setRadius(300);
        ball12.setIsowned(false);
        ball12.setCharacter("hungry");
        CO.collide(ball12,ball10);
        assertNotEquals(1, ball12.getVelocity().x);


        //fusion
        Ball ball13 = Ball.makeBall(LineStrategy.initiate(), dims, false);
        ball13.setLocation(new Point(300,400));
        ball13.setVelocity(new Point(1,0));
        ball13.setRadius(300);
        ball13.setIsowned(false);
        ball13.setCharacter("fusion");

        Ball ball14 = Ball.makeBall(LineStrategy.initiate(), dims, false);
        ball14.setLocation(new Point(300,400));
        ball14.setVelocity(new Point(1,0));
        ball14.setRadius(300);
        ball14.setIsowned(false);
        ball14.setCharacter("fusion");
        CO.collide(ball13, ball14);
        assertNotEquals(ball13.getRadius(), 300);
        assertNotEquals(ball14.getLocation().y, 400);

        CO.collide(ball13, ball1);
        assertEquals(ball13.getRadius(), 50);

        Ball ball15 = Ball.makeBall(LineStrategy.initiate(), dims, false);
        ball15.setLocation(new Point(300,400));
        ball15.setVelocity(new Point(1,0));
        ball15.setRadius(300);
        ball15.setStrategy(RotateStrategy.initiate());

        Ball ball16 = Ball.makeBall(LineStrategy.initiate(), dims, false);
        ball16.setLocation(new Point(310,410));
        ball16.setVelocity(new Point(1,0));
        ball16.setStrategy(LingerStrategy.initiate());
        ball16.setRadius(300);
        CO.collide(ball15, ball16);
        assertEquals(ball15.getVelocity().x, -1);




    }
}