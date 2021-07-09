package domain;

import domain.graph.AdjacencyMatrixGraph;
import domain.list.ListException;


//Programa en Java para el algoritmo de Dijkstra que encuentra
//el camino mas corto para un unico origen
//Se emplea una matrix de adjacencia para representar el grafo
public class Dijkstra {

// Numero de vertices en el grafo
    static private int V;

// Funcion utilitaria para encontrar el vertice con la distancia minima, 
// a partir del conjunto de los vertices todavia no incluidos en el 
// camino mas corto
    private static int minDistance(int[] dist, boolean[] verticeYaProcesado) {
        // Initialize min value
        int min = Integer.MAX_VALUE;
        int min_index = 0;

        for (int v = 0; v < V; v++) {
            if (verticeYaProcesado[v] == false && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }
        }

        return min_index;
    }

// Funcion utilitaria para imprimir el arreglo de distancias calculadas
    private static void printSolution(int[] dist, int n) {
        System.out.println("Distancia del vertice desde el origen\n");
        for (int i = 0; i < V; i++) {
            System.out.println(i + " \t\t " + dist[i]);
        }
    }

    public static int[] dijkstra(AdjacencyMatrixGraph graph, int src) {

        try {
            int[][] matrix = new int[graph.size()][graph.size()];
            Object[][] adMatrix = graph.getAdjacencyMatrix();

            for (int i = 0; i < adMatrix.length; i++) {
                for (int j = 0; j < adMatrix[0].length; j++) {

                    String weight = adMatrix[i][j] + "";
                    String array[] = weight.split(" ");

                    try {

                        matrix[i][j] = Integer.parseInt(array[0]);
                    } catch (NumberFormatException e) {
                        matrix[i][j] = Integer.parseInt(array[0]);
                    }
                }
            }
            V = graph.size();
            int[] dist = new int[V];
            // dist[i] guarda la distancia mas corta desde src hasta el vertice i

            boolean[] verticeYaProcesado = new boolean[V];
            //Este arreglo tiene true si el vertice i ya fue procesado

            // Initialize all distances as INFINITE and stpSet[] as false
            for (int i = 0; i < V; i++) {
                dist[i] = Integer.MAX_VALUE;
                verticeYaProcesado[i] = false;
            }
            // La distancia del vertice origen hacia el mismo es siempre 0
            dist[src] = 0;

            //Encuentra el camino mas corto para todos los vertices
            for (int count = 0; count < V - 1; count++) {

                //Toma el vertice con la distancia minima del cojunto de vertices aun no procesados
                //En la primera iteracion siempre se devuelve src
                int u = minDistance(dist, verticeYaProcesado);

                // Se marca como ya procesado
                verticeYaProcesado[u] = true;

                // Update dist value of the adjacent vertices of the picked vertex.
                for (int v = 0; v < V; v++) //Se actualiza la dist[v] solo si no esta en verticeYaProcesado, hay un
                //arco desde u a v y el peso total del camino desde src hasta v a traves de u es
                // mas pequeno que el valor actual de dist[v]
                {
                    if (!verticeYaProcesado[v] && matrix[u][v] > 0 && dist[u] != Integer.MAX_VALUE
                            && dist[u] + matrix[u][v] < dist[v]) {
                        dist[v] = dist[u] + matrix[u][v];
                    }
                }
            }

            return dist;

        } catch (ListException ex) {
        }
        return null;
    }

}
