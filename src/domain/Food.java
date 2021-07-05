/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author yeison
 */
public class Food {
//    5. Food (auto-id(int), name(String), price(Double), restaurantID(int)
    private int id;
    private static int autoId;
    private String name;
    private double price;
    private int restaurantID;

    public Food(String name, double price, int restaurantID) {
        this.id = ++autoId;
        this.name = name;
        this.price = price;
        this.restaurantID = restaurantID;
    }

    public Food() {
        
    }

    public Food(int id,String name, double price, int restaurantID) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.restaurantID = restaurantID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getAutoId() {
        return autoId;
    }

    public static void setAutoId(int autoId) {
        Food.autoId = autoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(int restaurantID) {
        this.restaurantID = restaurantID;
    }

    @Override
    public String toString() {
        return id + "~" + name + "~" + price + "~" + restaurantID;
    }
    
}
