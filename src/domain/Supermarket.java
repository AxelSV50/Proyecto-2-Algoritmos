/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

public class Supermarket {

    private int id;
    private String name;
    private String location;
    
    public Supermarket(int id, String name, String location) {
        this.id = id;
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
    
    public int getId() {
        return id;
    }

    public void setAutoId(int autoId) {
        this.id = autoId;
    }

    @Override
    public String toString() {
        return id+"~"+name+"~"+location;
    }

    
} // fin de clase
