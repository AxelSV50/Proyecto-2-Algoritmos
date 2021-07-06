/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import domain.Food;

import domain.bst.BST;
import domain.bst.BTreeNode;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 *
 * @author yeison
 */
public class FileManagementFoods {

    private static final String nameFileFood = "Food.txt";

    public static String getNameFileFood() {
        return nameFileFood;
    }
//    5. Food (auto-id(int), name(String), price(Double), restaurantID(int)

    public static boolean add(int id, String name, double price, int restaurantID, String fileName) {
        try {

            File f1 = new File(fileName);

            if (!f1.exists()) {
                f1.createNewFile();
            }
            //Abre un flujo de escritua a el fichero
            FileWriter fw = new FileWriter(f1, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(id + "~" + name + "~" + price + "~" + restaurantID + "\n");
            bw.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
        return true;
    }

    public static boolean overwriteFoodFile(BST bstFood) {
        try {
            ArrayList<Object> bstList = new ArrayList<Object>();
            File f1 = new File(nameFileFood);
            if (f1.exists()) {
                f1.delete();
            }
            f1.createNewFile();

            //Abre un flujo de escritua a el fichero
            FileWriter fw = new FileWriter(f1, true);
            BufferedWriter bw = new BufferedWriter(fw);

            if (!bstFood.isEmpty()) {
                bstList = bstFood.preOrder2();
                for (int i = 0; i < bstList.size(); i++) {
                    Food f = (Food) bstList.get(i);
                    bw.write(f.getId() + "~" + f.getName() + "~" + f.getPrice() + "~" + f.getRestaurantID() + "\n");
                }
                bw.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public static BST getBstFood() {
        BST bst = new BST();
        try {

            File f1 = new File(nameFileFood);
            String array[];

            if (f1.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(f1));
                String line;
                while ((line = br.readLine()) != null) {
                    array = line.split("~");//la ',' se designó para separar los elementos del fichero
                    bst.add(new Food(Integer.parseInt(array[0]), array[1], Double.parseDouble(array[2]), Integer.parseInt(array[3])));
                }
                br.close();
            } else {
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return bst;

    }

}
