package edu.rice.comp504.model;

import java.awt.*;
import java.util.Random;

public class Hexagon extends AShape  {
    private Point Loc;
    private String Name;
    private String Color;
    private int Size;
    private String myclass = "Hexagon";

    public Hexagon(){
        this.Loc = Randomizer.RandomLoc();
        this.Color = Randomizer.RandomColor();
        this.Size = Randomizer.RandomSize();
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

    public int getSize() {
        return Size;
    }

    public Point getLoc() {
        return Loc;
    }

    public String myclass(){
        return "Hexagon";
    }

    //get random attributes
    public void setAttrs(){  //specifically for color change
        int ColorIndex = new Random().nextInt(9);
        Color = availColors[ColorIndex];
    }

    public void RandomLoc(){
        int x = 100 + new Random().nextInt(600);
        int y = 100 + new Random().nextInt(600);
        Loc = new Point(x,y);
    }

    public void RandomSize(){
        Size = 40 + new Random().nextInt(30);
    }

}
