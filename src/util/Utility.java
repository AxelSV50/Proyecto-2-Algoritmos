/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import domain.bst.BST;
import domain.list.CircularDoublyLinkedList;

import domain.list.SinglyLinkedList;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Profesor Lic. Gilberth Chaves Avila
 */
public class Utility {

    private static BST bstProduct;
    private static SinglyLinkedList studentsList;
    private static CircularDoublyLinkedList coursesList;
    private static CircularDoublyLinkedList enrollmentList;
    private static CircularDoublyLinkedList deEnrollmentList;
    private static SinglyLinkedList timeTableList;

   
//
//    //Tiene los estudiantes agregados
//    public static SinglyLinkedList getStudentsList() {
//        studentsList = data.FileManagementUsers.getStudentsList();
//        return studentsList;
//    }
//
//    public static CircularDoublyLinkedList getCoursesList() {
//        coursesList = data.FileManagementCourses.getCoursesList();
//        return coursesList;
//    }
//
//    public static SinglyLinkedList getTimeTableList() {
//        timeTableList = data.FileManagementTimeTable.getTimeTableList();
//        return timeTableList;
//    }
//
//    public static CircularDoublyLinkedList getEnrollmentList() {
//        enrollmentList = data.FileManagementEnrollement.getEnrollmentList();
//        return enrollmentList;
//    }
//
//    public static CircularDoublyLinkedList getDeEnrollmentList() {
//        deEnrollmentList = data.FileManagementEnrollement.getDeEnrollmentList();
//        return deEnrollmentList;
//    }

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
  

        }
        return false; //en cualquier otro caso
    }

    public static boolean equals2(Object a, Object b) {

        switch (instanceOf(a, b)) {
        }//revisar esta condicion

        return false; //en cualquier otro caso
    }

}
