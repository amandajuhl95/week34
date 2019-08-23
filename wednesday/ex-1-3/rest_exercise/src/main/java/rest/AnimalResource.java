/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import entites.Animal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author aamandajuhl
 */
@Path("animal")
public class AnimalResource {
    
    Gson gson = new Gson();

    private static List<Animal> animals = new ArrayList();
    {
        animals.add(new Animal("dog", 2009, "vuf"));
        animals.add(new Animal("bunny", 2017, "pivpiv"));
        animals.add(new Animal("frog", 2019, "quack"));
        animals.add(new Animal("cat", 2011, "miav"));

    }

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AnimalResource
     */
    public AnimalResource() {
    }

    /**
     * Retrieves representation of an instance of rest.AnimalResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    //@Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        return "wild animal";
    }

    @GET
    @Path("/random")
    @Produces(MediaType.APPLICATION_JSON)
    public String getRandom() {
        //TODO return proper representation object
        Random rnd = new Random();
        Animal a = animals.get(rnd.nextInt(animals.size()));
        return gson.toJson(a);
    }

//    /**
//     * PUT method for updating or creating an instance of AnimalResource
//     * @param content representation for the resource
//     */
//    @PUT
//    @Consumes(MediaType.APPLICATION_JSON)
//    public void putJson(String content) {
//    }
}
