/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author carlo
 */
public class Supermarket {

    private static int autoId;
    private String name;
    private String location;
    
    public Supermarket(int autoId, String name, String location) {
        this.autoId = autoId++;
        this.name = name;
        this.location = location;
    } // Constructor

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
    public static int getAutoId() {
        return autoId;
    }

    public static void setAutoId(int autoId) {
        Supermarket.autoId = autoId;
    }

    
} // fin de clase
