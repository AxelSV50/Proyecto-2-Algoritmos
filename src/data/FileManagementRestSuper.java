/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import com.sun.mail.imap.ACL;
import domain.Place;
import domain.Restaurant;
import domain.Supermarket;
import domain.graph.AdjacencyListGraph;
import domain.graph.AdjacencyMatrixGraph;
import domain.graph.EdgeWeight;
import domain.graph.GraphException;
import domain.graph.Vertex;
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

/**
 *
 * @author Usuario
 */
public class FileManagementRestSuper {

    private static String nameFileListGraphVetexes = "files/GraphRestaurantsAndSupermarkets/GraphRestSupVertexes.txt";
    private static String nameFileListGraphEdgesAndWeights = "files/GraphRestaurantsAndSupermarkets/GraphRestSupEdges.txt";

    public static void addListGraph(AdjacencyListGraph graph) {
        try {

            File f1 = new File(nameFileListGraphVetexes);
            File f2 = new File(nameFileListGraphEdgesAndWeights);

            if (f1.exists()) {
                f1.delete();
                f1.createNewFile();
            } else {
                f1.createNewFile();
            }
            if (f2.exists()) {
                f2.delete();
                f2.createNewFile();
            } else {
                f2.createNewFile();
            }

            if (!graph.isEmpty()) {
                //Se añaden los vértices
                Vertex[] vertexes = graph.getVertexList();

                FileWriter fw = new FileWriter(f1, true);
                BufferedWriter bw = new BufferedWriter(fw);

                for (int i = 0; i < vertexes.length; i++) {
                    if (vertexes[i] != null) {

                        
                        if (vertexes[i].data instanceof Restaurant) {

                            Restaurant r = (Restaurant) vertexes[i].data;
                            bw.write("Restaurant~" + r.toString() + "\n");

                        } else if (vertexes[i].data instanceof Supermarket) {

                            Supermarket s = (Supermarket) vertexes[i].data;
                            bw.write("Supermarket~" + s.toString() + "\n");
                        }
                    }
                }
                bw.close();

                //Se añaden las aristas
                FileWriter fw2  = new FileWriter(f2, true);
                BufferedWriter bw2 = new BufferedWriter(fw2);

                for (int i = 0; i < vertexes.length; i++) {

                    if (vertexes[i] != null) {

                        SinglyLinkedList edges = vertexes[i].edgesList;

                        if (edges != null && !edges.isEmpty()) {

                            String data = "";
                            EdgeWeight e = (EdgeWeight) edges.getNode(1).data;
                            bw2.write(e.getWeight() + "\n");
                        }

                    }
                }
                bw2.close();
            }

        } catch (IOException e) {
        } catch (ListException ex) {
            Logger.getLogger(FileManagementRestSuper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static AdjacencyListGraph getRestSupGraph() {

        AdjacencyListGraph graph = new AdjacencyListGraph(10);

        try {

            File f1 = new File(nameFileListGraphVetexes);
            File f2 = new File(nameFileListGraphEdgesAndWeights);

            if (f1.exists()) {

                BufferedReader br = new BufferedReader(new FileReader(f1));
                String line;
                int count = 0;
                while ((line = br.readLine()) != null) {

                    count++;
                }
                br.close();
                //Se añaden los vértices
                graph = new AdjacencyListGraph(count + 1);

                BufferedReader br2 = new BufferedReader(new FileReader(f1));

                while ((line = br2.readLine()) != null) {

                    String array[] = line.split("~");

                    if (array[0].equalsIgnoreCase("Restaurant")) {

                        graph.addVertex(new Restaurant(Integer.parseInt(array[1]), array[2], array[3] + "~" + array[4]));

                    } else {

                        graph.addVertex(new Supermarket(Integer.parseInt(array[1]), array[2], array[3] + "~" + array[4]));
                    }

                }
                br2.close();

//                Se añaden las aristas y pesos
                BufferedReader br3 = new BufferedReader(new FileReader(f2));
                Vertex[] vertexes = graph.getVertexList();
                Object[][] adMatrix = new Object[vertexes.length][vertexes.length];

                String[] weights = new String[graph.size()];
                int i = 0;
                while ((line = br3.readLine()) != null) {

                    weights[i] = line;
                    i++;
                }
                br3.close();
                for (int j = 0; j < graph.size(); j++) {

                    if (j + 1 < graph.size()) {

                        graph.addEdge(vertexes[j].data, vertexes[j + 1].data);
                        graph.addWeight(vertexes[j].data, vertexes[j + 1].data, weights[j]);
                    }
                }
            }
        } catch (IOException | NumberFormatException | GraphException | ListException ex) {

            ex.printStackTrace();
        }
        return graph;
    }

    public void generateId() {

        File f1 = new File(nameFileListGraphVetexes);
    }
}
