/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.awt.Checkbox;
import javafx.scene.control.CheckBox;

/**
 *
 * @author Usuario
 */
public class Place {
    
    private String name;
    private int autoID;
    
    public Place(String name, int autoID) {
        this.name = name;
        this.autoID = autoID;
    }

    public String getName() {
        return name;
    }

    public int getAutoID() {
        return autoID;
    }
    
    @Override
    public String toString() {
        return name+"~"+autoID;
    }
    
}
