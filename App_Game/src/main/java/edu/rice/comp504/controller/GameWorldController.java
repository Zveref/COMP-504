package edu.rice.comp504.controller;

import com.google.gson.Gson;
import edu.rice.comp504.adapter.DispatchAdapter;
import edu.rice.comp504.model.GameBoard;

import java.awt.*;

import static spark.Spark.*;


/**
 * The paint world controller creates the adapter(s) that communicate with the view.
 * The controller responds to requests from the view after contacting the adapter(s).
 */
public class GameWorldController {

    /**
     * The main entry point into the program.
     * @param args  The program arguments normally specified on the cmd line
     */
    public static void main(String[] args) {
        staticFiles.location("/public");
        port(getHerokuAssignedPort());
        Gson gson = new Gson();
        GameBoard gb = new GameBoard();
        DispatchAdapter da = new DispatchAdapter();
        redirect.get("/:", "/");

        get("/initial", (request, response) -> {
            return gson.toJson("initial");
        });

        post("/update", (request, response) -> {
            return gson.toJson("update");
        });

        post("/level/:id", (request, response) -> {
            return gson.toJson("next level");
        });

    }



    /**
     * Get the heroku assigned port number.
     * @return The port number
     */
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}
