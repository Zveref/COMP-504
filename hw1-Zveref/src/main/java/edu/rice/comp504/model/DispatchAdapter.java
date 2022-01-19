package edu.rice.comp504.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/**
 * The dispatch adapter holds all the shapes. It is responsible for communicating with the model
 * when there is a state change requiring an update to all the shapes.  The controller will
 * pass the JSON representation of the dispatch zadapter to the view.
 */
public class DispatchAdapter {
    private static Point dims;
    private ArrayList<AShape> shapes;
    private ArrayList<CompositeShape> DoubleShapes;


    /**
     * Constructor call.
     */
    public DispatchAdapter(ArrayList<AShape> shapes, Point dims) {
        this.shapes = shapes;
        this.dims = dims;
    }

    public DispatchAdapter(ArrayList<CompositeShape> DoubleShapes){
        this.DoubleShapes = DoubleShapes;
    }




    /**
     * Get the canvas dimensions.
     * @return The canvas dimensions
     */
    public static Point getCanvasDims() {
        return dims;
    }

    /**
     * Set the canvas dimensions.
     * @param d The canvas width (x) and height (y).
     */
    public static void setCanvasDims(Point d) {
        dims = d;
    }

    /**
     * Call the update method on all the shapes.
     */
    public void updateShapes() {
        for(AShape temp: shapes){
            temp.setAttrs();
        }
    }

    public void updateDoubleShapes(){
        for(CompositeShape temp2: DoubleShapes){
            temp2.setAttrs();
        }
    }

    /**
     *  Add a shape.
     * @param type  The type of shape to add
     * @return A ball
     */
    public AShape addShape(String type) {
        int circleNum = 0;
        int clubNum = 0;
        int hexagonNum = 0;
        int squareNum = 0;
        int triangleNum = 0;
        int compositeNum = 0;

        if(type == "Circle"){
            shapes.add(new Circle());
        }else if(type == "Club"){
            String myname = String.valueOf(clubNum);

            Club test2 = new Club(new Point(0,0), myname, "tempcolor" , 0);
            test2.setAttrs();
            test2.RandomLoc();
            test2.RandomRadius();
            clubNum++;
            shapes.add(test2);
        }else if(type == "Hexagon"){
            String myname = String.valueOf(hexagonNum);

            Hexagon test3 = new Hexagon(new Point(0,0), myname, "tempcolor" , 0);
            test3.setAttrs();
            test3.RandomLoc();
            test3.RandomSize();
            hexagonNum++;
            shapes.add(test3);
        }else if(type == "Square"){
            String myname = String.valueOf(squareNum);

            Square test4 = new Square(new Point(0,0), myname, "tempcolor" , 0);
            test4.setAttrs();
            test4.RandomLoc();
            test4.RandomSize();
            squareNum++;
            shapes.add(test4);
        }else if(type == "Triangle"){
            String myname = String.valueOf(triangleNum);

            Triangle test5 = new Triangle(new Point(0,0), myname, "tempcolor" , 0);
            test5.setAttrs();
            test5.RandomLoc();
            test5.RandomSize();
            triangleNum++;
            shapes.add(test5);
        }else if(type == "CompositeShape"){
            String myname = String.valueOf(compositeNum);

            Triangle temp1 = new Triangle(new Point(100,100), myname+"1", "red" , 100);
            Square temp2 = new Square(new Point(100,100), myname+"2", "gray", 100);

            CompositeShape test6 = new CompositeShape(temp1, temp2);
            test6.RandomLoc();
            test6.RandomSize();
            test6.RandomShape();
            test6.setAttrs();
            compositeNum++;
            DoubleShapes.add(test6);
        }
        return null;
    }

    /**
     * Remove all shapes.
     */
    public ArrayList<AShape> removeShapes() {
        shapes = new ArrayList<AShape>();
        DoubleShapes = new ArrayList<CompositeShape>();
        return null;
    }

    /**
     * Indicate the current shapes/DoubleShapes size
     */
    public int getListSize(){
        return shapes.size();
    }

    public int getDoubleListSize(){
        return DoubleShapes.size();
    }

}
