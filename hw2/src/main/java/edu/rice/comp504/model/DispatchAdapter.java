package edu.rice.comp504.model;

import edu.rice.comp504.model.BasicShape.*;

import java.awt.Point;
import java.nio.file.Path;
import java.util.ArrayList;

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
    public  Point getCanvasDims() {
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
        switch(type){
            case "Circle":
                shapes.add(new Circle());
                break;
            case "Club":
                shapes.add(new Club());
                break;
            case "Hexagon":
                shapes.add(new Hexagon());
                break;
            case "Square":
                shapes.add(new Square());
                break;
            case "Triangle":
                shapes.add(new Triangle());
                break;
            default:
                ShapeModel model = new ShapeModel();
                DoubleShapes.add(model.getShape(type));
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
