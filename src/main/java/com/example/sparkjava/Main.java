/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sparkjava;

import com.example.sparkjava.Person.PersonServicesImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import org.apache.log4j.Logger;
import static spark.Spark.*;

/**
 *
 * @author Rojan Maharjan<rojan@moco.com.np>
 */
public class Main {

    private static final Logger logger = Logger.getLogger(Main.class);
    private static final String LOG_FILE = "log4j.properties";
    private static ObjectMapper om = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        /**
         * Load log properties file
         */
        try (InputStream logFile = Main.class.getClassLoader().getResourceAsStream(LOG_FILE)) {
        } catch (IOException ex) {
        }

        /**
         * Start embedded server at this port
         */
        port(8080);
        staticFiles.location("/public");
        init();
        get("/", (request, response) -> {
            response.type("text/html");
            response.redirect("html/add.html");
            return "";
        });
        PersonServicesImpl person = new PersonServicesImpl();
        get("/getAll", (request, response) -> {
            response.type("application/json");
            return om.writeValueAsString(person.getAll());
        });
        
        get("/getById", (request, response) -> {
            response.type("application/json");
            String id = request.queryParams("id");
            return om.writeValueAsString(person.getbyId(id));
        });

        post("/create", (request, response) -> {
            response.type("application/json");
            logger.info(request.body());
            String data = request.body();
            return om.writeValueAsString(person.createPerson(data));
        });

        put("/update", (request, response) -> {
            response.type("application/json");
            String data = request.body();
            return om.writeValueAsString(person.updatePerson(data));
        });

        delete("/delete", (request, response) -> {
            response.type("application/json");
            String id = request.queryParams("id");
            return om.writeValueAsString(person.deletePerson(id));
        });
    }

}
