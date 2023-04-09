/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.Singleton;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

/**
 * REST Web Service
 *
 * @author ummiaishaibrahim
 * @version 1.0
 */
@Singleton
@Path("Mountains")
public class MountainRest {

    @Context
    private UriInfo context;
    private HashMap<String, Mountain> mountainList = new HashMap<>();
    private final Object lock = new Object();

    /**
     * Creates a new instance of MountainRest
     */
    public MountainRest() {
    }
    
     /**
     * this POST method is used for adding new mountain constructors or details.
     * @param mountainName
     * @param mountainRange
     * @param country
     * @param mountainHeight
     * @param hemisphere
     */
    @POST 
    //@Path("/mountainName/mountainRange/country/mountainHeight/hemisphere")
    @Consumes("application/json")
    public void addMountain(JsonObject mountain){
        synchronized (lock){
            String country = mountain.getJsonString("country").getString();
            String mountainRange = mountain.getJsonString("mountainRange").getString();
            String mountainName = mountain.getJsonString("mountainName").getString();
            int mountainHeight = mountain.getJsonNumber("mountainHeight").hashCode();
            String hemisphere = mountain.getJsonString("hemisphere").getString();
            if(!(mountainList.containsKey(country) && mountainList.containsKey(mountainRange))){
                Mountain mountain1 = new Mountain(mountainName, mountainRange, country, mountainHeight, hemisphere);
                mountainList.put(country, mountain1);
            }else if(mountainList.containsKey(mountainName) && mountainList.containsKey(country) && mountainList.containsKey(mountainRange)){
                System.out.println("Already present");
            } 
        }  
    }
    
    
    /**
     * this delete method is used to delete a method 
     * @param mountainName
     * @param mountainRange
     * @param country
     * @throws NotFoundException
     */
    @DELETE
    @Path("/{mountainName}/{mountainRange}/{country}")
    public void deleteMountain(@PathParam("mountainName")String mountainName, @PathParam("mountainRange")String mountainRange, 
            @PathParam("country")String country) throws NotFoundException{
        synchronized (lock){
            if((mountainList.containsKey(mountainName) && mountainList.containsKey(mountainRange) && mountainList.containsKey(country))){
                mountainList.remove(mountainName);
                mountainList.remove(mountainRange);
                mountainList.remove(country);
                System.out.println("it has been deleted");
            }else{
                throw new NotFoundException();
            }
        } 
    }

    /**
     * this PUT method is used for updating the mountain height
     * @param mountainName
     * @param mountainRange
     * @param country
     * @param newHeight
     */ 
    @PUT
    @Path("/{mountainName}/{mountainRange}/{country}/{newHeight}")
    @Consumes("application/x-www-form-urlencoded")
    public void updateMountainHeight(@FormParam("mountainName")String mountainName, @FormParam("mountainRange")String mountainRange, 
            @FormParam("country")String country, @FormParam("newHeight") int newHeight){
        synchronized(lock){
            Mountain mountain = null;
            if((mountainList.containsKey(mountainName) && mountainList.containsKey(mountainRange) && mountainList.containsKey(country))){
                mountain.setMountainHeight(newHeight);
                mountainList.put(mountainName, mountain);
                System.out.println("The new height has been changed to: " + newHeight);
                
            }else{
                System.out.println("No such mountain");
            }
        } 
    }
    
    /**
     * this method gets the mountain height of the name name, range and country of a mountain.
     */
    @GET
    @Path("/mountainName/mountainRange/country")
    @Produces("text/plain")  
    public int getMountainHeight(@QueryParam("mountainName")String mountainName, @QueryParam("mountainRange")String mountainRange, 
            @QueryParam("country")String country){
        synchronized (lock){
            int height = 0;
            Mountain mountain = null;
            if((mountainList.containsKey(mountainName) && mountainList.containsKey(mountainRange) && mountainList.containsKey(country))){
                height = mountain.getMountainHeight();
                mountainList.put(country, mountain);
            }else{
                System.out.println("No such mountain");
            }
            return height;
        }
    }
    
    /**
     * this method gets all the mountain details connected to the named range or country
     */
    @GET
    @Path("/mountainRange/country")
    @Produces("application/json")
    public JsonArray getMountainRangeAndCountry(@QueryParam("mountainRange")String mountainRange, @QueryParam("country")String country){
        synchronized (lock){
            JsonArrayBuilder array = Json.createArrayBuilder();
            for(Map.Entry<String, Mountain> mountain: mountainList.entrySet()){
                if(mountain != null){
                    if((mountainList.containsKey(mountainRange) && mountainList.containsKey(country))){
                        array.add(Json.createObjectBuilder().add("mountainName", mountain.getKey()).add("mountainHeight", mountain.hashCode()));
                    }
                }
            }
            return array.build();
        }
    }
    
    /**
     * this method gets all the mountain details connected to the named country
     */
    @GET
    @Path("/country")
    @Produces("application/json")
    public JsonArray getMountainByCountry(@QueryParam("country")String country){
        synchronized (lock){
            JsonArrayBuilder array = Json.createArrayBuilder();
            for(Map.Entry<String, Mountain> mountain: mountainList.entrySet()){
                if(mountain != null){
                    if((mountainList.containsKey(country))){
                        array.add(Json.createObjectBuilder().add("mountainName", mountain.getKey()).add("mountainHeight", mountain.hashCode()));
                    }
                } 
            }
            return array.build();
        }
        
    }
    
    /**
     * this method gets all the mountain details connected to the named hemisphere
     */
    @GET
    @Path("/hemisphere")
    @Produces("application/json")
    public JsonArray getMountainByHemisphere(@QueryParam("hemisphere")String hemisphere){
        synchronized (lock){
            JsonArrayBuilder array = Json.createArrayBuilder();
            for(Map.Entry<String, Mountain> mountain: mountainList.entrySet()){
                if(mountain != null){
                    if((mountainList.containsKey(hemisphere))){
                        array.add(Json.createObjectBuilder().add("mountainName", mountain.getKey()).add("mountainRange", mountain.getKey())
                                .add("country", mountain.getKey()).add("mountainHeight", mountain.hashCode()).add("hemisphere", mountain.getKey()));
                    }
                }
            }
            return array.build();
        }
    } 
    
    /**
     * this method gets all the mountains added to the method.
     */
    @GET
    @Produces("application/json")
    public JsonArray getMountainList() throws JsonProcessingException{
        JsonArray list = null;
        
        Mountain mountain1 = new Mountain("Mount Blanc", "Graian Alpes", "France-Italy", 4810, "Northern");
        mountainList.put("Mount Blanc", mountain1);
        Mountain mountain2 = new Mountain("Aconcagua", "Andes - Principal Cordillera", "Argentina", 6962, "Southern");
        mountainList.put("Aconcagua", mountain2);
        Mountain mountain3 = new Mountain("Mount Everest", "Himalayas", "Nepal", 8848, "Northern");
        mountainList.put("Mount Everest", mountain3);
        Mountain mountain4 = new Mountain("Toubkal", "Atlas", "Morrocco", 4167, "Southern");
        mountainList.put("Toubkal", mountain4);
        Mountain mountain5 = new Mountain("Zardkooh", "Zagros", "Iran", 4221, "Northern");
        mountainList.put("Zardkooh", mountain5);
                
       ObjectMapper objectMapper = new ObjectMapper();
       try{
           String temp = objectMapper.writeValueAsString(mountainList);
           JsonReader reader = Json.createReader(new StringReader(temp));
           list = reader.readArray();
       }catch(JsonProcessingException e){
           e.printStackTrace();
       }
       finally{
           return list;
       }
    }
}
