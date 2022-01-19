package edu.rice.comp504.controller;

import com.google.gson.Gson;
import edu.rice.comp504.adapter.DispatchAdapter;
import edu.rice.comp504.model.CollisionStore;
import edu.rice.comp504.model.paintobj.APaintObject;

import static spark.Spark.*;


/**
 * The paint world controller creates the adapter(s) that communicate with the view.
 * The controller responds to requests from the view after contacting the adapter(s).
 */
public class BallWorldController {

    /**
     * The main entry point into the program.
     * @param args  The program arguments normally specified on the cmd line
     */
    public static void main(String[] args) {
        staticFiles.location("/public");
        port(getHerokuAssignedPort());
        Gson gson = new Gson();
        CollisionStore coliStore = new CollisionStore();
        DispatchAdapter dis = new DispatchAdapter(coliStore);

        post("/load/:strategy/:type/:character/:switchable", (request, response) -> {
            String strategy  = request.params(":strategy");
            String type = request.params(":type");
            String Switch = request.params(":switchable");
            String Character = request.params(":character");
            Boolean switchable = Switch.equals("true") ? true : false;
            System.out.println("load: " + strategy + " " + type + " " + Character + " switchable == " + switchable );
            APaintObject tempObj = dis.loadObj(strategy, type, Character, switchable);
            return gson.toJson(tempObj);
        });


        post("/switch", (request, response) -> {
            System.out.println("switch");
            dis.switchStrategy();
            return gson.toJson("switched");
        });

        get("/update", (request, response) -> {
            System.out.println("update");
            dis.updateWorld();
            return gson.toJson(dis.updateWorld());
        });



        post("/canvas/dims", (request, response) -> {
            CollisionStore.setCanvasDims(request.queryParams("height"),request.queryParams("width"));
            return gson.toJson("canvas dimensions");
        });

        get("/remove/:strategy/:type", (request, response) -> {
            String strategy = request.params(":strategy");
            String type = request.params(":type");
            System.out.println("remove: " +strategy +"  "+ type );
            dis.remove(strategy, type);
            return gson.toJson("removed certain group of object from the world");
        });

        get("/clear", (request, response) -> {
            System.out.println("clear all");
            dis.remove("all", "all");
            return gson.toJson(null);
        });

        //show requests in the monitor
        before((request, respond) -> {
            System.out.println("request:" + request.url());
        });

        //redirect
        redirect.get("/canvas", "/");


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
