/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import static data.FileManagementUsers.getDataLogin;
import domain.AdjacencyMatrixGraph;
import domain.GraphException;
import domain.Place;
import domain.Vertex;
import domain.list.CircularLinkedList;
import domain.list.ListException;
import domain.list.SinglyLinkedList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
//

/**
 *
 * @author Usuario
 */
public class FileManagementPlaces {

    private static String nameFilePlaces = "Places.txt";
    private static String nameFileMatrixGraphVetexes = "files/GraphPlaces/GraphPlacesVertexes.txt";
    private static String nameFileMatrixGraphEdgesAndWeights = "files/GraphPlaces/GraphPlacesEdges.txt";

    public static String getNameFilePlaces() {
        return nameFilePlaces;
    }

    public static boolean addPlace(Place p) {
        try {

            File f1 = new File(nameFilePlaces);

            if (!f1.exists()) {
                f1.createNewFile();
            }
            //Abre un flujo de escritua a el fichero
            FileWriter fw = new FileWriter(f1, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(p.toString());
            bw.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
        return true;
    }

    public static SinglyLinkedList getPlacesList() {

        SinglyLinkedList list = new SinglyLinkedList();

        try {

            File f1 = new File(nameFilePlaces);
            String array[];
            String array2[];

            if (f1.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(f1));
                String line;
                while ((line = br.readLine()) != null) {

                    array = line.split("~");
                    list.add(new Place(array[0], Integer.parseInt(array[1])));

                }
                br.close();

            }
        } catch (IOException | NumberFormatException ex) {
        }
        return list;

    }

    public static AdjacencyMatrixGraph getPlacesGraph() {

        AdjacencyMatrixGraph graph = null;

        try {

            File f1 = new File(nameFileMatrixGraphVetexes);
            File f2 = new File(nameFileMatrixGraphEdgesAndWeights);

            if (f1.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(f1));
                String line;
                int count = 0;
                while ((line = br.readLine()) != null) {

                    count++;
                }
                br.close();
                //Se añaden los vértices
                graph = new AdjacencyMatrixGraph(count);

                BufferedReader br2 = new BufferedReader(new FileReader(f1));

                while ((line = br2.readLine()) != null) {
                    graph.addVertex(line);
                }
                br2.close();

                //Se añaden las aristas y pesos
                BufferedReader br3 = new BufferedReader(new FileReader(f2));
                Vertex[] vertexes = graph.getVertexList();
                Object[][] adMatrix = new Object[vertexes.length][vertexes.length];

                for (int i = 0; i < vertexes.length; i++) {
                    for (int j = 0; j < vertexes.length; j++) {
                            String element = br3.readLine();
                        try {
                            adMatrix[i][j] = Integer.parseInt(element);

                        } catch (NumberFormatException e) {
                            adMatrix[i][j] = element;
                        }

                    }
                }
                graph.setAdjacencyMatrix(adMatrix);
                br3.close();
            }
        } catch (IOException | NumberFormatException | GraphException | ListException ex) {
        }
        return graph;

    }

    public static void addMatrixGraph(AdjacencyMatrixGraph graph) {
        try {

            File f1 = new File(nameFileMatrixGraphVetexes);
            File f2 = new File(nameFileMatrixGraphEdgesAndWeights);

            if (f1.exists()) {
                f1.delete();
                f1.createNewFile();
            }
            if (f2.exists()) {
                f2.delete();
                f2.createNewFile();
            }

            //Se añaden los vértices
            Vertex[] vertexes = graph.getVertexList();

            FileWriter fw = new FileWriter(f1, true);
            BufferedWriter bw = new BufferedWriter(fw);

            for (int i = 0; i < vertexes.length; i++) {
                if (vertexes[i] != null) {
                    bw.write(vertexes[i].data + "\n");
                }
            }
            bw.close();

            //Se añaden las aristas y pesos
            Object[][] adMatrix = graph.getAdjacencyMatrix();

            FileWriter fw2 = new FileWriter(f2, true);
            BufferedWriter bw2 = new BufferedWriter(fw2);

            for (int i = 0; i < adMatrix.length; i++) {
                for (int j = 0; j < adMatrix[0].length; j++) {

                    bw2.write(adMatrix[i][j] + "\n");
                }
            }
            bw2.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    public static void deleteFilesGraph(){
        
            File f1 = new File(nameFileMatrixGraphVetexes);
            File f2 = new File(nameFileMatrixGraphEdgesAndWeights);

            if (f1.exists()) {
                f1.delete();
            }
            if (f2.exists()) {
                f2.delete();
            }

        
    }

}
