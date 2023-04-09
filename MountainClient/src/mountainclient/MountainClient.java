/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mountainclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import static javafx.scene.input.KeyCode.T;


/**
 *
 * @author ummiaishaibrahim
 */
public class MountainClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws JsonProcessingException {
        NewJerseyClient client = new NewJerseyClient();

        Mountain [] mountainList = null;
        
        //Mountain constructors 
        Mountain mountain1 =  new Mountain("Aconcagua", "Andes - Principal Cordillera", "Argentina", 6962, "Southern");
        Mountain mountain2 =  new Mountain("Mount Everest", "Himalayas", "Nepal", 8848, "Northern");
        Mountain mountain3 =  new Mountain("Toubkal", "Atlas", "Morrocco", 4167, "Southern");
        Mountain mountain4 =  new Mountain("Zardkooh", "Zagros", "Iran", 4221, "Northern");
        Mountain mountain5 = new Mountain("Mount Blanc", "Graian Alpes", "France-Italy", 4810, "Northern");
        
        //adding all the mountains to the client
        client.addMountain(mountain1);
        client.addMountain(mountain2);
        client.addMountain(mountain3);
        client.addMountain(mountain4);
        client.addMountain(mountain5);
        
        //printing all the mountains
        System.out.println(mountain1);
        System.out.println(mountain2);
        System.out.println(mountain3);
        System.out.println(mountain4);
        System.out.println(mountain5);
        
        // getting the height of the names mountain
        // it keeps printing zero
        int number = client.getMountainHeight(Integer.class, "Aconcagua", "Argentina", "Andes - Principal Cordillera");
        System.out.println(number);
        
        //updating the height
        // in my mountain rest java file my newheight is an integer but when i created the newjerseyclient it switched to a string.
        //client.updateMountainHeight("Toubkal", "Atlas", "Morrocco", "9000");
        
        //getting all the mountain details related to the hemisphere
        // i tried to print it by using the book resource as an example but the responsetype kept giving an error of class type.
        /*
        ArrayList<Mountain> list = client.getMountainByHemisphere(String.class, "Northern");
        for(Mountain elem: list){
            System.out.println(elem);
        }
        */
        
        
        //getting all the mountain details related to the country and range
        // i tried to print it by using the book resource as an example but the responsetype kept giving an error of class type.
        /*
        ArrayList<Mountain> list = client.getMountainRangeAndCountry(String.class, "Argentina", "Andes - Principal Cordillera");
        for(Mountain elem: list){
            System.out.println(elem);
        }
        */
        
        //getting all the mountain details related to the country
        // i tried to print it by using the book resource as an example but the responsetype kept giving an error of class type.
        /*
        ArrayList<Mountain> list = client.getMountainByCountry(String.class, "Iran");
        for(Mountain elem: list){
            System.out.println(elem);
        }
        */
        
        //returning the list of some mountains
        /*
        String value = client.getMountainList(String.class);
        try{
            mountainList = new ObjectMapper().readValue(value, Mountain[].class);
        }catch(IOException e){
            e.printStackTrace();
        }
        
        for(Mountain mout: mountainList){
            System.out.println(mout.toString());
        }
        */
        
        //deleting a book
        //client.deleteMountain("Toubkal", "Atlas", "Morrocco");
        
        
        
        
    }
    
}
