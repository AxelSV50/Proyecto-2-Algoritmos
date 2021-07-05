/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author Usuario
 */
public class Product {
    private static int autoId;
    private String name;
    private double price;
    private int supermarketID;

    public Product(int autoId,String name, double price, int supermarketID) {
        this.name = name;
        this.price = price;
        this.autoId = ++autoId;
        this.supermarketID = supermarketID;
    } // Constrcutor

    public static int getAutoId() {
        return autoId;
    }

    public static void setAutoId(int autoId) {
        Product.autoId = autoId;
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

    public int getSupermarketID() {
        return supermarketID;
    }

    public void setSupermarketID(int supermarketID) {
        this.supermarketID = supermarketID;
    }

} // Fin de clase
