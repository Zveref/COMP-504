package edu.rice.comp504.model;

import edu.rice.comp504.model.BasicShape.*;

import java.awt.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ShapeModel {
    private HashMap<String, SHapeFac> shapeFactories = new HashMap<>();
    public ShapeModel() {
        setShapeMap();
    }

    private void setShapeMap() {

        shapeFactories.put("DoubleCircle", new SHapeFac() {
            public CompositeShape generate() {
                Circle circle = new Circle();
                int x = (int) circle.getLoc().getX();
                int y = (int) circle.getLoc().getY();
                int size = circle.getSize();
                Circle circle1 = new Circle(new Point(x + 2 * size, y), circle.getVel(), Randomizer.RandomColor(), size);
                CompositeShape Com1 = new CompositeShape(circle, circle1);
                Com1.setLoc(new Point(x + size, y));
                Com1.setWidth(2 * size);
                Com1.setHeight(size);
                return Com1;
            }
        });

        shapeFactories.put("DoubleClub", new SHapeFac() {
            public CompositeShape generate() {
                Club club = new Club();
                int x = (int) club.getLoc().getX();
                int y = (int) club.getLoc().getY();
                int size = club.getSize();
                Club club1 = new Club(new Point(x + size, y), club.getVel(), Randomizer.RandomColor(), size);
                CompositeShape Com2 = new CompositeShape(club, club1);
                Com2.setLoc(new Point(x + size, y));
                Com2.setWidth((int) (size + size * Math.cos(Math.PI / 6)));
                Com2.setHeight(size);
                return Com2;
            }
        });

        shapeFactories.put("DoubleHexagon", new SHapeFac() {
            public CompositeShape generate() {
                Hexagon hexagon = new Hexagon();
                int x = (int) hexagon.getLoc().getX();
                int y = (int) hexagon.getLoc().getY();
                int size = hexagon.getSize();
                Hexagon hexagon1 = new Hexagon(new Point(x + size, y), hexagon.getVel(), Randomizer.RandomColor(), size);
                CompositeShape Com3 = new CompositeShape(hexagon, hexagon1);
                Com3.setLoc(new Point(x + size, y));
                Com3.setWidth(2 * size);
                Com3.setHeight(size);
                return Com3;
            }
        });

        shapeFactories.put("DoubleSquare", new SHapeFac() {
            public CompositeShape generate() {
                Square square = new Square();
                int x = (int) square.getLoc().getX();
                int y = (int) square.getLoc().getY();
                int size = square.getSize();
                Square square1 = new Square(new Point(x + size, y), square.getVel(), Randomizer.RandomColor(), size);
                CompositeShape Com4 = new CompositeShape(square, square1);
                Com4.setLoc(new Point(x + size, y));
                Com4.setWidth((int) (size + size * Math.cos(Math.PI / 4)));
                Com4.setHeight(size);
                return Com4;
            }
        });

        shapeFactories.put("DoubleTriangle", new SHapeFac() {
            public CompositeShape generate() {
                Triangle triangle = new Triangle();
                int x = (int) triangle.getLoc().getX();
                int y = (int) triangle.getLoc().getY();
                int size = triangle.getSize();
                Triangle triangle1 = new Triangle(new Point(x + size, y), triangle.getVel(), Randomizer.RandomColor(), size);
                CompositeShape Com5 = new CompositeShape(triangle, triangle1);
                Com5.setLoc(new Point(x + size, y));
                Com5.setWidth(2 * size);
                Com5.setHeight((int) (size * Math.sin(Math.PI / 3)));
                return Com5;
            }
        });

        shapeFactories.put("Circle+Club", new SHapeFac() {
            public CompositeShape generate() {
                Circle circle = new Circle();
                Point point = circle.getLoc();
                int size = circle.getSize();
                Club club = new Club(point, circle.getVel(), Randomizer.RandomColor(), size);
                return new CompositeShape(circle, club);
            }
        });

        shapeFactories.put("Circle+Hexagon", new SHapeFac() {
            public CompositeShape generate() {
                Circle circle = new Circle();
                Point point = circle.getLoc();
                int size = circle.getSize();
                Hexagon hexagon = new Hexagon(point, circle.getVel(), Randomizer.RandomColor(), size);
                return new CompositeShape(circle, hexagon);
            }
        });

        shapeFactories.put("Circle+Square", new SHapeFac() {
            public CompositeShape generate() {
                Circle circle = new Circle();
                Point point = circle.getLoc();
                int size = circle.getSize();
                Square square = new Square(point, circle.getVel(), Randomizer.RandomColor(), size);
                return new CompositeShape(circle, square);
            }
        });

        shapeFactories.put("Circle+Triangle", new SHapeFac() {
            public CompositeShape generate() {
                Circle circle = new Circle();
                Point point = circle.getLoc();
                int size = circle.getSize();
                Triangle triangle = new Triangle(point, circle.getVel(), Randomizer.RandomColor(), size);
                return new CompositeShape(circle, triangle);
            }
        });

        shapeFactories.put("Triangle+Square", new SHapeFac() {
            public CompositeShape generate() {
                Triangle triangle = new Triangle();
                Point point = triangle.getLoc();
                int size = triangle.getSize();
                Square square = new Square(point, triangle.getVel(), Randomizer.RandomColor(), size);
                CompositeShape Com6 = new CompositeShape(triangle, square);
                Com6.setWidth(size);
                Com6.setHeight((int) (size * Math.sin(Math.PI / 4)));
                return Com6;
            }
        });
    }

    public CompositeShape getShape(String type){
        return shapeFactories.get(type).generate();
    }



}
