package edu.rice.comp504.model;

import java.awt.*;
import java.nio.file.Path;
import java.util.Random;

public class Randomizer {
    public static String[] ColorField = {"red", "blue", "yellow", "green", "black", "purple", "orange", "gray", "brown"};

    public static Point RandomLoc(){
        int x = 400 + new Random().nextInt(700) - 350;
        int y = 400 + new Random().nextInt(700) - 350;
        return new Point(x, y);
    }

    public static Point RandomVel(){
        int Velx = 10 + new Random().nextInt(10);
        int Vely = 10 + new Random().nextInt(10);
        return new Point(Velx, Vely);
    }

    public static int RandomSize(){
        return 10 + new Random().nextInt(10);
    }

    public static String RandomColor(){
        int ColorIndex = new Random().nextInt(9);
        return ColorField[ColorIndex];
    }

}
