package edu.rice.comp504.model;

import java.awt.*;
import java.util.Random;

public class Circle extends AShape  {
    private Point Loc;
    private String Name;
    private String Color;
    private int Radius;
    private String myclass = "Circle";

    public Circle(){
        this.Loc = Randomizer.RandomLoc();
        this.Color = Randomizer.RandomColor();
        this.Radius = Randomizer.RandomSize();
    }

    public Circle(Point Loc, Point Vel, String Color, int Size){
        this.Loc = Loc;
        this.Color = Color;
        this.Radius = Size;
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
        return "Circle";
    }

    //get random attributes
    public void setAttrs(){ //specifically for color change
        int ColorIndex = new Random().nextInt(9);
        Color = availColors[ColorIndex];
    }

}
