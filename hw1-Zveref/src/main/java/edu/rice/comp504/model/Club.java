package edu.rice.comp504.model;

import java.awt.*;
import java.util.Random;

public class Club extends AShape  {
    private Point Loc;
    private String Name;
    private String Color;
    private int Radius;
    private String myclass = "Club";

    public Club(){
        this.Loc = Randomizer.RandomLoc();
        this.Color = Randomizer.RandomColor();
        this.Radius = Randomizer.RandomSize();
    }

    public String getColor() {
        return Color;
    }

    public String getName() {
        return Name;
    }

    public void setColor(String color) {
        Color = color;
    }

    public int getRadius() {
        return Radius;
    }

    public Point getLoc() {
        return Loc;
    }

    public String myclass(){
        return "Club";
    }

    //get random attributes
    public void setAttrs(){ //specifically for color change
        int ColorIndex = new Random().nextInt(9);
        Color = availColors[ColorIndex];
    }

    public void RandomLoc(){
        int x = 100 + new Random().nextInt(600);
        int y = 100 + new Random().nextInt(600);
        Loc = new Point(x,y);
    }

    public void RandomRadius(){
        Radius = 20 + new Random().nextInt(40);
    }

}
