package edu.rice.comp504;

import static spark.Spark.*;

/**
 * Testing to ensure that we've properly setup basic software packages needed for the course.
 */
public class HelloWorld {

    /**
     * Entry point into program.
     * @param args  The command line arguments.
     */
    public static void main(String[] args) {

        //TODO Add the REST get request method to with a "/" endpoint that returns the string "Hello, World!"
        get("/", (req, res) -> "Hello World");

        //TODO add a redirect from the endpoint "/hello" to "/"
        redirect.get("/hello", "/");

        //TODO system print out the request method and url
        before((req, res) -> {

            String s = req.url();
            System.out.println("request:" + s);

        });
    }
}
