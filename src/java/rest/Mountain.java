/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

/**
 *
 * @author ummiaishaibrahim
 * Feedback Response Statement (based of the last assignment): you told me to be careful when assigning class names, 
 * so i made the this class a singular name not a plural like the last assignment.
 */
public class Mountain {
    private String mountainName;
    private String mountainRange;
    private String country;
    private int mountainHeight;
    private String hemisphere;
    private final String s = String.format("mountainName:%s", "mountainRange:%s", "country:%s", "mountainHeight:%d", "hemisphere:%s", mountainName, 
            mountainRange, country, mountainHeight, hemisphere);
    
    
    public Mountain(String mountainName, String mountainRange, String country, int mountainHeight, String hemisphere){
        this.mountainName = mountainName;
        this.mountainRange = mountainRange;
        this.country = country;
        this.mountainHeight = mountainHeight;
        this.hemisphere = hemisphere;
    }
    
    public void setMountainName(String name){
        name = this.mountainName;
    }
    
    public void setMountainRange(String range){
        range = this.mountainRange;
    }
    
    public void setCountry(String place){
        place = this.country;
    }
    
    public void setMountainHeight(int height){
        height = this.mountainHeight;
    }
    
    public void setHemisphere(String hemis){
        hemis = this.hemisphere;
    }
    
    public String getMountainName(){
        return mountainName;
    }
    
    public String getMountainRange(){
        return mountainRange;
    }
    
    public String getCountry(){
        return country;
    }
    
    public int getMountainHeight(){
        return mountainHeight;
    }
    
    public String getHemisphere(){
        return hemisphere;
    }
}
