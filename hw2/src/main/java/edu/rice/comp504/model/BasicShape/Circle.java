package edu.rice.comp504.model.BasicShape;

import edu.rice.comp504.model.AShape;
import edu.rice.comp504.model.Randomizer;

import java.awt.*;
import java.util.Random;

public class Circle extends AShape {
    private Point Loc;
    private Point Vel;
    private String Name;
    private String Color;
    private int Size;
    private String myclass = "Circle";
    private int Height;
    private int Width;

    //this one's for separate shape building
    public Circle(){
        this.Loc = Randomizer.RandomLoc();
        this.Vel = Randomizer.RandomVel();
        this.Color = Randomizer.RandomColor();
        this.Size = Randomizer.RandomSize();
        Height = Width = Size;
    }

    //this one's for alignment in composite generation
    public Circle(Point Loc, Point Vel, String Color, int Size){
        this.Loc = Loc;
        this.Vel = Vel;
        this.Color = Color;
        this.Size = Size;
        Height = Width = Size;
    }

    public Point getLoc() {
        return Loc;
    }

    public Point getVel(){return Vel;}

    public String getColor() {
        return Color;
    }

    public int getSize() {
        return Size;
    }

    public String getName() {
        return Name;
    }

    public void setColor(String color) {
        Color = color;
    }

    public String myclass(){
        return "Circle";
    }

    //for collision
    public int getHeight() {
        return Height;
    }

    public int getWidth() {
        return Width;
    }

    //get random attributes
    public void setAttrs(){ //specifically for color change
        int ColorIndex = new Random().nextInt(9);
        Color = availColors[ColorIndex];
    }

}
