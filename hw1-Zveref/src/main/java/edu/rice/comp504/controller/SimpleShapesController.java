package edu.rice.comp504.controller;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import edu.rice.comp504.model.AShape;
import edu.rice.comp504.model.CompositeShape;
import edu.rice.comp504.model.DispatchAdapter;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

import static spark.Spark.*;

/**
 * The SimpleShapesController is responsible for interfacing between the view and the model.  The model will determine
 * how shape objects are created.  The view is the browser.  The browser has a canvas that renders the shapes.
 * The controller interacts with the view by receiving REST get requests for various shapes.
 */
public class SimpleShapesController {

    /**
     *  Entry point into the program.
     * @param args  The arguments
     */
    public static void main(String[] args) {
        staticFiles.location("/public");
        port(getHerokuAssignedPort());
        Gson gson = new Gson();

        ArrayList<AShape> TestList = new ArrayList<AShape>();
        ArrayList<CompositeShape> DoubleTestList = new ArrayList<CompositeShape>();

        DispatchAdapter CirClePath = new DispatchAdapter(TestList, new Point(0,0));
        DispatchAdapter ClubPath = new DispatchAdapter(TestList, new Point(0,0));
        DispatchAdapter CompositePath = new DispatchAdapter(DoubleTestList);
        DispatchAdapter HexagonPath = new DispatchAdapter(TestList, new Point(0,0));
        DispatchAdapter SquarePath = new DispatchAdapter(TestList, new Point(0,0));
        DispatchAdapter TriangleePath = new DispatchAdapter(TestList, new Point(0,0));
        DispatchAdapter CanvasPath = new DispatchAdapter(TestList, new Point(0,0));


        // GET request to create a new circle.  Controller will need to contact the model to service the request
        get("/shape/circle", (request, response) -> {
            if(CirClePath.getListSize() != 0){
                CirClePath.updateShapes();
            }
            CirClePath.addShape("Circle");
            return gson.toJson(CirClePath);
        });

        // GET request to create a new Club.  Controller will need to contact the model to service the request
        get("/shape/club", (request, response) -> {
            if(ClubPath.getListSize() != 0){
                ClubPath.updateShapes();
            }
            ClubPath.addShape("Club");
            return gson.toJson(ClubPath);
        });

        get("/shape/compositeshape", (request, response) -> {
            if(CompositePath.getDoubleListSize() != 0){
                CompositePath.updateDoubleShapes();
            }
            CompositePath.addShape("CompositeShape");
            return gson.toJson(CompositePath);
        });

        // GET request to create a new pentagon.  Controller will need to contact the model to service the request
        get("/shape/hexagon", (request, response) -> {
            if(HexagonPath.getListSize() != 0){
                HexagonPath.updateShapes();
            }
            HexagonPath.addShape("Hexagon");
            return gson.toJson(HexagonPath);
        });

        // GET request to create a new square.  Controller will need to contact the model to service the request
        get("/shape/square", (request, response) -> {
            if(SquarePath.getListSize() != 0){
                SquarePath.updateShapes();
            }
            SquarePath.addShape("Square");
            return gson.toJson(SquarePath);
        });

        // GET request to create a new triangle.  Controller will need to contact the model to service the request
        get("/shape/triangle", (request, response) -> {
            if(TriangleePath.getListSize() != 0){
                TriangleePath.updateShapes();
            }
            TriangleePath.addShape("Triangle");
            return gson.toJson(TriangleePath);
        });

        // GET request to create a new triangle.  Controller will need to contact the model to service the request
        get("/shape/triangle", (request, response) -> {
            if(TriangleePath.getListSize() != 0){
                TriangleePath.updateShapes();
            }
            TriangleePath.addShape("Triangle");
            return gson.toJson(TriangleePath);
        });

        // GET request to remove all the shapes
        get("/clear", (request, response) -> {
            CirClePath.removeShapes();
            ClubPath.removeShapes();
            CompositePath.removeShapes();
            HexagonPath.removeShapes();
            SquarePath.removeShapes();
            TriangleePath.removeShapes();
            CanvasPath.removeShapes();
            return gson.toJson(CanvasPath);
        });


        post("/canvas/dims", (request, response) -> {
            return gson.toJson(CanvasPath);
        });


        before((req, res) -> {

            String s = req.url();
            System.out.println("request:" + req.url());
        });

        //redirect
        redirect.get("/canvas", "/");

    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

}
