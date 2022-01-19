package edu.rice.comp504.controller;

import com.google.gson.Gson;

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

        // GET request to create a new circle.  Controller will need to contact the model to service the request
        get("/shape/circle", (request, response) -> {
            // TODO: Need to create a circle object and then return the JSON version back to the view
            return gson.toJson("Hello, World!");
        });

    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}
