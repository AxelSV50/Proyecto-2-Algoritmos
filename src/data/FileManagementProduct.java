/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import domain.Product;
import domain.bst.BST;
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
public class FileManagementProduct {

    private static String nameFileProduct = "Product.txt";

    public static String getNameFileProduct() {
        return nameFileProduct;
    }
    
    public static boolean add(int autoId, String name, double price, int supermarketId,String filename) {
        try {

            File f1 = new File(nameFileProduct);

            if (!f1.exists()) {
                f1.createNewFile();
            }
            //Abre un flujo de escritua a el fichero
            FileWriter fw = new FileWriter(f1, true);
            BufferedWriter bw = new BufferedWriter(fw);
            //Escribe los parámetros
            bw.write(autoId + "," + name + "," + price + "," + supermarketId + "\n");
            bw.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
        return true;
    }

    public static boolean overwriteProductFile(BST bstProduct) {
        try {
            ArrayList<Object> bstList = new ArrayList<Object>();
            File f1 = new File(nameFileProduct);
            if (f1.exists()) {
                f1.delete();
            }
            f1.createNewFile();

            //Abre un flujo de escritua a el fichero
            FileWriter fw = new FileWriter(f1, true);
            BufferedWriter bw = new BufferedWriter(fw);

            if (!bstProduct.isEmpty()) {
                bstList = bstProduct.preOrder2();
                for (int i = 0; i < bstList.size(); i++) {
                        Product f = (Product) bstList.get(i);
                        bw.write(f.getAutoId() + "~" + f.getName() + "~" + f.getPrice() + "~" + f.getSupermarketID() + "\n");
                }
                bw.close();
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        return true;
    } // overwrite
    
    public static BST getBstProduct() {
        BST bst = new BST();
        try {

            File f1 = new File(nameFileProduct);
            String array[];

            if (f1.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(f1));
                String line;
                while ((line = br.readLine()) != null) {
                    array = line.split("~");//la ',' se designó para separar los elementos del fichero
                    bst.add(new Product(Integer.parseInt(array[0]), array[1], Double.parseDouble(array[2]), Integer.parseInt(array[3])));
                }
                br.close();
            } else {
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return bst;
    }
} // fin de clase
