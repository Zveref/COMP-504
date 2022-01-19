package edu.rice.comp504.model;

import java.awt.*;
import java.util.*;

public class CompositeShape extends AShape{
    private ArrayList<AShape> DoubleShape = new ArrayList<>();
    private Point Loc;
    private String Color;
    private int Size;



    public CompositeShape(AShape shape1, AShape shape2){
        DoubleShape.add(shape1);
        DoubleShape.add(shape2);
    }



    //to get random attributes
    public void setAttrs(){ //specifically for color change
        int ColorIndex1 = new Random().nextInt(9);
        int ColorIndex2 = new Random().nextInt(9);
        AShape temp0 = DoubleShape.get(0);
        AShape temp1 = DoubleShape.get(1);
        temp0.setAttrs();
        temp1.setAttrs();
        DoubleShape.removeAll(DoubleShape);
        DoubleShape.add(temp0);
        DoubleShape.add(temp1);
        }

    public void RandomShape(){
        AShape[] Allshape = new AShape[5];
        Allshape[0] = new Circle();
        Allshape[1] = new Club();
        Allshape[2] = new Hexagon();
        Allshape[3] = new Square();
        Allshape[4] = new Triangle();
        int ran1 = new Random().nextInt(5);
        int ran2 = new Random().nextInt(5);
        DoubleShape.removeAll(DoubleShape);
        DoubleShape.add(Allshape[ran1]);
        DoubleShape.add(Allshape[ran2]);
    }


    public void RandomLoc(){
        int x = 100 + new Random().nextInt(600);
        int y = 100 + new Random().nextInt(600);
        Loc = new Point(x,y);
    }

    public void RandomSize(){
        Size = 40 + new Random().nextInt(30);
    }


    public ArrayList<AShape> getShapes(){
        ArrayList<AShape> res = new ArrayList<>();
        res.add(DoubleShape.get(0));
        res.add(DoubleShape.get(1));
        return res;
    }
}
