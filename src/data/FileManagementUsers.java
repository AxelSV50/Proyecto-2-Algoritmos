/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import domain.list.CircularLinkedList;
import domain.Security;
import domain.list.SinglyLinkedList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
//

/**
 *
 * @author Usuario
 */
public class FileManagementUsers {

    private static String nameFileAdmin = "Administration.txt";
    private static String nameFileUsers = "Users.txt";

    public static String getNameFileAdmin() {
        return nameFileAdmin;
    }

    public static String getNameFileStudents() {
        return nameFileUsers;
    }

    public static boolean add(String userData, String password, String fileName) {
        try {

            File f1 = new File(fileName);

            if (!f1.exists()) {
                f1.createNewFile();
            }
            //Abre un flujo de escritua a el fichero
            FileWriter fw = new FileWriter(f1, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(userData + "~" + Security.encrypt(password) + "\n");
            bw.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
        return true;
    }

    public static SinglyLinkedList getStudentsList() {

        SinglyLinkedList list = new SinglyLinkedList();

        try {

            File f1 = new File(nameFileUsers);
            String array[];
            String array2[];

            if (f1.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(f1));
                String line;
                while ((line = br.readLine()) != null) {
                    list.add(line);
                }
                br.close();

            }
        } catch (IOException | NumberFormatException ex) {
        }
        return list;

    }

    public static CircularLinkedList getDataLogin(String fileName) {

        CircularLinkedList list = new CircularLinkedList();

        try {

            File f1 = new File(fileName);
            String array[];

            if (f1.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(f1));
                String line;
                while ((line = br.readLine()) != null) {
                    array = line.split("~");//la '~' se designó para separar los elementos del fichero

                    if (fileName.equals(nameFileAdmin)) {

                        list.add(array[0] + "~" + array[1]);

                    } else {

                        list.add(array[0] + "~" + array[9]);
                    }
                }
                br.close();
            } else {
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
   public static boolean overwriteStudentsFile(SinglyLinkedList list) {
        try {

            File f1 = new File(nameFileUsers);
            CircularLinkedList list2 = getDataLogin(nameFileUsers);
            
            if (f1.exists()) {
                f1.delete();
            }
            f1.createNewFile();

            //Abre un flujo de escritua a el fichero
            FileWriter fw = new FileWriter(f1, true);
            BufferedWriter bw = new BufferedWriter(fw);

            if (!list.isEmpty()) {
                for (int i = 1; i <= list.size(); i++) {

                    if (list.getNode(i) != null && list2.getNode(i) != null) {
                        
                        String data = (String) list.getNode(i).data;
                        bw.write(data);
                    }
                }
                bw.close();
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
        return true;
    }
}
