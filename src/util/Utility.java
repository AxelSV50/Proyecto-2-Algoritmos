/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import domain.Food;
import domain.Place;
import domain.Product;
import domain.Restaurant;
import domain.Supermarket;
import domain.bst.BST;
import domain.graph.EdgeWeight;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Profesor Lic. Gilberth Chaves Avila
 */
public class Utility {

    private static BST bstFood;
    private static BST bstProduct;

    public static BST getBstFood() {
        bstFood = data.FileManagementFoods.getBstFood();
        return bstFood;
    }

    public static BST getBstProduct() {
        bstProduct = data.FileManagementProduct.getBstProduct();
        return bstProduct;
    }

    public static int random() {
        return 1 + (int) Math.floor(Math.random() * 99);
    }

    public static int random(int bound) {
        //return 1+random.nextInt(bound);
        return (int) Math.floor(Math.random() * bound);
    }

    public static String format(double value) {
        return new DecimalFormat("###,###,###,###.##")
                .format(value);
    }

    public static String $format(double value) {
        return new DecimalFormat("$###,###,###,###.##")
                .format(value);
    }

    public static String perFormat(double value) {
        //#,##0.00 '%'
        return new DecimalFormat("#,##0.00'%'")
                .format(value);
    }

    public static String dateFormat(Date value) {
        return new SimpleDateFormat("dd/MM/yyyy")
                .format(value);
    }

    public static boolean equals(Object a, Object b) {

        switch (instanceOf(a, b)) {

            case "integer":
                Integer x = (Integer) a;
                Integer y = (Integer) b;
                return x.equals(y);
            case "string":
                String s1 = (String) a;
                String s2 = (String) b;
                //return s1.compareTo(s2)==0; //OPCION 1
                return s1.equalsIgnoreCase(s2); //OPCION 2
            case "food":
                Food f1 = (Food) a;
                Food f2 = (Food) b;
                return f1.getId() == f2.getId() || (f1.getId() == f2.getId() && f1.getRestaurantID() == f2.getRestaurantID());
            case "product":
                Product p1 = (Product) a;
                Product p2 = (Product) b;
                return p1.getId() == p2.getId();
            case "place":
                Place place1 = (Place) a;
                Place place2 = (Place) b;
                return place2.getAutoID() == place1.getAutoID();
            case "restaurant":
                Restaurant r1 = (Restaurant) a;
                Restaurant r2 = (Restaurant) b;
                return r1.getId() == r2.getId();
            case "supermarket":
                Supermarket su1 = (Supermarket) a;
                Supermarket su2 = (Supermarket) b;
                return su1.getId() == su2.getId();
            case "edgeWeight":
                EdgeWeight e1 = (EdgeWeight) a;
                EdgeWeight e2 = (EdgeWeight) b;
                return equals(e1.getEdge(), e2.getEdge());

        }//revisar esta condicion
        return false; //en cualquier otro caso
    }

    private static String instanceOf(Object a, Object b) {
        if (a instanceof Integer && b instanceof Integer) {
            return "integer";
        }
        if (a instanceof String && b instanceof String) {
            return "string";
        }
        if (a instanceof Food && b instanceof Food) {
            return "food";
        }
        if (a instanceof Product && b instanceof Product) {
            return "product";
        }
        if (a instanceof Place && b instanceof Place) {
            return "place";
        }
        if (a instanceof Restaurant && b instanceof Restaurant) {
            return "restaurant";
        }
        if (a instanceof EdgeWeight && b instanceof EdgeWeight) {
            return "edgeWeight";
        }
        if (a instanceof Supermarket && b instanceof Supermarket) {
            return "supermarket";
        }
        return "unknown"; //desconocido
    }

    public static boolean lessT(Object a, Object b) {
        switch (instanceOf(a, b)) {
            case "integer":
                Integer x = (Integer) a;
                Integer y = (Integer) b;
                return x < y;
            case "string":
                String s1 = (String) a;
                String s2 = (String) b;
                return s1.compareTo(s2) < 0;
            case "food":
                Food f1 = (Food) a;
                Food f2 = (Food) b;
                return (f1.getId()) < (f2.getId());
            case "product":
                Product p1 = (Product) a;
                Product p2 = (Product) b;
                return (p1.getId()) < (p2.getId());
        }
        return false; //en cualquier otro caso
    }

    public static boolean greaterT(Object a, Object b) {
        switch (instanceOf(a, b)) {
            case "integer":
                Integer x = (Integer) a;
                Integer y = (Integer) b;
                return x > y;
            case "string":
                String s1 = (String) a;
                String s2 = (String) b;
                return s1.compareTo(s2) > 0;
            case "food":
                Food f1 = (Food) a;
                Food f2 = (Food) b;
                return (f1.getId()) > (f2.getId());
            case "product":
                Product p1 = (Product) a;
                Product p2 = (Product) b;
                return (p1.getId()) > (p2.getId());
        }
        return false; //en cualquier otro caso
    }

    public static boolean equals2(Object a, Object b) {

        switch (instanceOf(a, b)) {

            case "integer":
                Integer x = (Integer) a;
                Integer y = (Integer) b;
                return x.equals(y);
            case "string":
                String s1 = (String) a;
                String s2 = (String) b;
                //return s1.compareTo(s2)==0; //OPCION 1
                return s1.equalsIgnoreCase(s2); //OPCION 2
            case "food":
                Food f1 = (Food) a;
                Food f2 = (Food) b;
                return (f1.getName()).equalsIgnoreCase(f2.getName()) && f1.getRestaurantID() == f2.getRestaurantID();
            case "product":
                Product p1 = (Product) a;
                Product p2 = (Product) b;
                return (p1.getName()).equalsIgnoreCase(p2.getName()) && p1.getSupermarketID() == p2.getSupermarketID();
        }//revisar esta condicion
        return false; //en cualquier otro caso
    }

    public static boolean equals3(Object a, Object b) {

        switch (instanceOf(a, b)) {

            case "food":
                Food f1 = (Food) a;
                Food f2 = (Food) b;
                return f1.getRestaurantID() == f2.getRestaurantID();
            case "product":
                Product p1 = (Product) a;
                Product p2 = (Product) b;
                return p1.getSupermarketID() == p2.getSupermarketID();
        }//revisar esta condicion
        return false; //en cualquier otro caso
    }

    public static boolean lessT2(Object a, Object b) {
        switch (instanceOf(a, b)) {
            case "integer":
                Integer x = (Integer) a;
                Integer y = (Integer) b;
                return x < y;
            case "string":
                String s1 = (String) a;
                String s2 = (String) b;
                return s1.compareTo(s2) < 0;
            case "food":
                Food f1 = (Food) a;
                Food f2 = (Food) b;
                return (f1.getName()).compareTo(f2.getName()) < 0 && f1.getRestaurantID() < f2.getRestaurantID();
            case "product":
                Product p1 = (Product) a;
                Product p2 = (Product) b;
                return (p1.getName()).compareTo(p2.getName()) < 0 && p1.getSupermarketID() < p2.getSupermarketID();
        }
        return false; //en cualquier otro caso
    }

    public static boolean lessT3(Object a, Object b) {
        switch (instanceOf(a, b)) {
            case "food":
                Food f1 = (Food) a;
                Food f2 = (Food) b;
                return (f1.getRestaurantID()) < (f2.getRestaurantID());
            case "product":
                Product p1 = (Product) a;
                Product p2 = (Product) b;
                return (p1.getSupermarketID()) < (p2.getSupermarketID());
        }
        return false; //en cualquier otro caso    }
    }
}
