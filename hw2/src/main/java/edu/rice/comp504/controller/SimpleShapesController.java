package edu.rice.comp504.controller;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import edu.rice.comp504.model.AShape;
import edu.rice.comp504.model.CompositeShape;
import edu.rice.comp504.model.DispatchAdapter;

import java.awt.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        DispatchAdapter Path = new DispatchAdapter(TestList, new Point(0,0));
        DispatchAdapter DoublePath = new DispatchAdapter(DoubleTestList);

        //get Canvas Size
        post("/canvas/dims", (request, response) -> {
            Matcher m = Pattern.compile("\\d+").matcher(request.body());
            int[] res = new int[2];
            int k =0;
            while (m.find()) {
                res[k] = Integer.parseInt(m.group(0));
            }
            Path.setCanvasDims(new Point(res[0], res[1]));
            DoublePath.setCanvasDims(new Point(res[0], res[1]));
            System.out.println(request.body());
            return gson.toJson(null);
        });

        // GET request to create a new circle.  Controller will need to contact the model to service the request
        get("/shapes/Circle", (request, response) -> {
            if(Path.getListSize() != 0){
                Path.updateShapes();
            }
            Path.addShape("Circle");
            return gson.toJson(Path);
        });

        // GET request to create a new Club.  Controller will need to contact the model to service the request
        get("/shapes/Club", (request, response) -> {
            if(Path.getListSize() != 0){
                Path.updateShapes();
            }
            Path.addShape("Club");
            return gson.toJson(Path);
        });

        // GET request to create a new pentagon.  Controller will need to contact the model to service the request
        get("/shapes/Hexagon", (request, response) -> {
            if(Path.getListSize() != 0){
                Path.updateShapes();
            }
            Path.addShape("Hexagon");
            return gson.toJson(Path);
        });

        // GET request to create a new square.  Controller will need to contact the model to service the request
        get("/shapes/Square", (request, response) -> {
            if(Path.getListSize() != 0){
                Path.updateShapes();
            }
            Path.addShape("Square");
            return gson.toJson(Path);
        });

        // GET request to create a new triangle.  Controller will need to contact the model to service the request
        get("/shapes/Triangle", (request, response) -> {
            if(Path.getListSize() != 0){
                Path.updateShapes();
            }
            Path.addShape("Triangle");
            return gson.toJson(Path);
        });



        //Advanced Shapes

        get("shapes/DoubleCircle", (request, response) -> {
            if(DoublePath.getDoubleListSize() != 0){
                DoublePath.updateDoubleShapes();
            }
            DoublePath.addShape("DoubleCircle");
            return gson.toJson(DoublePath);
        });

        get("shapes/DoubleCLub", (request, response) -> {
            if(DoublePath.getDoubleListSize() != 0){
                DoublePath.updateDoubleShapes();
            }
            DoublePath.addShape("DoubleClub");
            return gson.toJson(DoublePath);
        });

        get("shapes/DoubleSquare", (request, response) -> {
            if(DoublePath.getDoubleListSize() != 0){
                DoublePath.updateDoubleShapes();
            }
            DoublePath.addShape("DoubleSquare");
            return gson.toJson(DoublePath);
        });

        get("shapes/DoubleHexagon", (request, response) -> {
            if(DoublePath.getDoubleListSize() != 0){
                DoublePath.updateDoubleShapes();
            }
            DoublePath.addShape("DoubleHexagon");
            return gson.toJson(DoublePath);
        });

        get("shapes/DoubleTriangle", (request, response) -> {
            if(DoublePath.getDoubleListSize() != 0){
                DoublePath.updateDoubleShapes();
            }
            DoublePath.addShape("DoubleTriangle");
            return gson.toJson(DoublePath);
        });

        get("shapes/Circle+Club", (request, response) -> {
            if(DoublePath.getDoubleListSize() != 0){
                DoublePath.updateDoubleShapes();
            }
            DoublePath.addShape("Circle+Club");
            return gson.toJson(DoublePath);
        });

        get("shapes/Circle+Hexagon", (request, response) -> {
            if(DoublePath.getDoubleListSize() != 0){
                DoublePath.updateDoubleShapes();
            }
            DoublePath.addShape("Circle+Hexagon");
            return gson.toJson(DoublePath);
        });

        get("shapes/Circle+Square", (request, response) -> {
            if(DoublePath.getDoubleListSize() != 0){
                DoublePath.updateDoubleShapes();
            }
            DoublePath.addShape("Circle+Square");
            return gson.toJson(DoublePath);
        });

        get("shapes/Circle+Triangle", (request, response) -> {
            if(DoublePath.getDoubleListSize() != 0){
                DoublePath.updateDoubleShapes();
            }
            DoublePath.addShape("Circle+Triangle");
            return gson.toJson(DoublePath);
        });

        get("shapes/Triangle+Square", (request, response) -> {
            if(DoublePath.getDoubleListSize() != 0){
                DoublePath.updateDoubleShapes();
            }
            DoublePath.addShape("Triangle+Square");
            return gson.toJson(DoublePath);
        });




        // GET request to remove all the shapes
        get("/clear", (request, response) -> {
            Path.removeShapes();
            DoublePath.removeShapes();
            return gson.toJson(Path);
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
