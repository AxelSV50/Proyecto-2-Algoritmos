/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.places;

import data.FileManagementPlaces;
import domain.AdjacencyMatrixGraph;
import domain.GraphException;
import domain.Place;
import domain.Vertex;
import domain.list.ListException;
import domain.list.SinglyLinkedList;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ObservableValueBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class PlacesFXMLController implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    private Button btnExit1;
    @FXML
    private TableView<List<Object>> tablePlacesToSelect;
    @FXML
    private TableColumn<List<Object>, String> colPlaceToSelect;
    @FXML
    private TableColumn<List<Object>, CheckBox> colAddPlaceToSelect;
    @FXML
    private Button btnGenerateGraph;
    @FXML
    private TableView<List<String>> tablePlacesGraph;
    @FXML
    private TableColumn<List<String>, String> colOriginDestinyGraph;
    @FXML
    private TableColumn<List<String>, String> colDistanceGraph;
    @FXML
    private Button btnRandomDistance;
    @FXML
    private AnchorPane panePlacesGraph;
    @FXML
    private Pane panePlaces;
    @FXML
    private Button btnNewGraph;

    private ArrayList<Button> graphicVertexes;
    private ArrayList<Line> graphicEdges;
    private ArrayList<Text> graphicWeight;
    private Button aux;
    private Vertex[] vertexes;
    private Object[][] adMatrix;
    private ObservableList<List<Object>> placesTabledata;
    private ObservableList<List<String>> selectedPlacesTabledata;
    private SinglyLinkedList placesList;
    private AdjacencyMatrixGraph matrixGraph;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        placesList = FileManagementPlaces.getPlacesList();
        matrixGraph = FileManagementPlaces.getPlacesGraph();

        initTablePlaces();

        if (matrixGraph != null) {
            panePlaces.setVisible(true);
            btnNewGraph.setVisible(true);
            vertexes = matrixGraph.getVertexList();
            adMatrix = matrixGraph.getAdjacencyMatrix();
            initTableSelectedPlaces();
            drawAdjacencyMatrixGraph(matrixGraph, panePlacesGraph);
        }
    }

    private void initTablePlaces() {

        colPlaceToSelect.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<Object>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<Object>, String> data) {

                return new ReadOnlyStringWrapper((String) data.getValue().get(0)); //To change body of generated methods, choose Tools | Templates.
            }
        });
        colAddPlaceToSelect.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<Object>, CheckBox>, ObservableValue<CheckBox>>() {
            @Override
            public ObservableValue<CheckBox> call(TableColumn.CellDataFeatures<List<Object>, CheckBox> data) {

                CheckBox c = (CheckBox) data.getValue().get(1);
                ObservableValue<CheckBox> c2 = new ObservableValueBase<CheckBox>() {
                    @Override
                    public CheckBox getValue() {
                        return c;//To change body of generated methods, choose Tools | Templates.
                    }
                };

                return c2; //To change body of generated methods, choose Tools | Templates.
            }
        });

        placesTabledata = FXCollections.observableArrayList();

        try {
            for (int i = 1; i <= placesList.size(); i++) {

                Place p = (Place) placesList.getNode(i).data;

                CheckBox c = new CheckBox("");

                List<Object> list = new ArrayList<>();

                list.add(p.getName());
                list.add(c);

                placesTabledata.add(list);
            }
        } catch (ListException ex) {
        }

        tablePlacesToSelect.setItems(placesTabledata);
    }

    private void initTableSelectedPlaces() {

        colOriginDestinyGraph.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {

                return new ReadOnlyStringWrapper(data.getValue().get(0)); //To change body of generated methods, choose Tools | Templates.
            }
        });
        colDistanceGraph.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {

                return new ReadOnlyStringWrapper(data.getValue().get(1)); //To change body of generated methods, choose Tools | Templates.
            }
        });
        selectedPlacesTabledata = FXCollections.observableArrayList();
        Object[][] adMatrix = matrixGraph.getAdjacencyMatrix();

        int total = 0;
        for (int i = 0; i < vertexes.length; i++) {
            for (int j = 0; j < vertexes.length; j++) {

                List<String> list = new ArrayList<>();
                try {
                    if (vertexes[i] != null && vertexes[j] != null && matrixGraph.containsEdge(vertexes[i].data, vertexes[j].data)) {

                        if (!selectedPlacesTabledata.isEmpty()) {

                            List<String> list2 = new ArrayList<>();

                            list2.add(vertexes[j].data + ", " + vertexes[i].data);
                            list2.add(adMatrix[j][i] + "");

                            if (!selectedPlacesTabledata.contains(list2)) {
                                list.add(vertexes[i].data + ", " + vertexes[j].data);
                                list.add(adMatrix[i][j] + "");

                                String aux = adMatrix[i][j] + "";
                                String[] array = aux.split(" ");
                                total += Integer.parseInt(array[0]);
                                selectedPlacesTabledata.add(list);
                            }

                        } else {
                            list.add(vertexes[i].data + ", " + vertexes[j].data);
                            list.add(adMatrix[i][j] + "");

                            String aux = adMatrix[i][j] + "";
                            String[] array = aux.split(" ");
                            total += Integer.parseInt(array[0]);
                            selectedPlacesTabledata.add(list);
                        }

                    }
                } catch (GraphException | ListException ex) {
                }

            }
        }
        List<String> list = new ArrayList<>();
        list.add("Distancia total: ");
        list.add(total + "KM");
        selectedPlacesTabledata.add(list);

        tablePlacesGraph.setItems(selectedPlacesTabledata);
    }

    @FXML
    private void btnExit(ActionEvent event) {
        
        bp.setVisible(false);
    }

    @FXML
    private void btnGenerateGraph(ActionEvent event) {

        if (!placesTabledata.isEmpty()) {

            int counter = 0;
            for (int i = 0; i < placesTabledata.size(); i++) {

                List<Object> list = placesTabledata.get(i);

                CheckBox c = (CheckBox) list.get(1);

                if (c.isSelected()) {

                    counter++;

                }
            }
            matrixGraph = new AdjacencyMatrixGraph(counter);

            counter = 0;
            for (int i = 0; i < placesTabledata.size(); i++) {

                List<Object> list = placesTabledata.get(i);

                CheckBox c = (CheckBox) list.get(1);

                if (c.isSelected()) {

                    try {
                        matrixGraph.addVertex(list.get(0));
                        counter++;
                    } catch (GraphException | ListException ex) {
                    }
                }
            }
            if (counter < 10) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lugares");
                alert.setHeaderText("Debe agregar al menos 10 lugares para continuar");
                alert.showAndWait();
                matrixGraph = null;

            } else {
                panePlaces.setVisible(true);
                btnNewGraph.setVisible(true);
                vertexes = matrixGraph.getVertexList();
                try {
                    int value1;
                    int value2;
                    //Se añaden las aristas
                    for (int i = 0; i < matrixGraph.size(); i++) {
                        do {

                            value1 = util.Utility.random(vertexes.length);
                            value2 = util.Utility.random(vertexes.length);

                        } while (vertexes[value1] == null || vertexes[value2] == null
                                || (value1 == value2 || matrixGraph.containsEdge(vertexes[value1].data, vertexes[value2].data)));

                        matrixGraph.addEdge(vertexes[value1].data, vertexes[value2].data);
                    }

                    int counter2;

                    //Se añaden aristas a los vértices que quedaron aislados para que todos estén conectados
                    for (int i = 0; i < vertexes.length; i++) {

                        counter2 = 0;
                        for (int j = 0; j < vertexes.length; j++) {

                            if (vertexes[i] != null && vertexes[j] != null && matrixGraph.containsEdge(vertexes[i].data, vertexes[j].data)) {
                                counter2++;
                            }

                        }
                        if (counter2 == 0 && vertexes[i] != null) {
                            do {

                                value2 = util.Utility.random(vertexes.length);

                            } while (vertexes[value2] == null || (i == value2 || matrixGraph.containsEdge(vertexes[i].data, vertexes[value2].data)));
                            matrixGraph.addEdge(vertexes[i].data, vertexes[value2].data);
                        }
                    }
                    btnRandomDistance(event);
                    initTableSelectedPlaces();
                    FileManagementPlaces.addMatrixGraph(matrixGraph);

                } catch (GraphException | ListException e) {

                }

                for (int i = 0; i < placesTabledata.size(); i++) {

                    List<Object> list = placesTabledata.get(i);

                    CheckBox c = (CheckBox) list.get(1);

                    if (c.isSelected()) {

                        c.setSelected(false);
                    }
                }

            }
        }
    }
    EventHandler<MouseEvent> handler3 = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {

            if (graphicVertexes.size() > 1) {
                for (int i = 0; i < graphicVertexes.size(); i++) {

                    if (event.getSource() == graphicVertexes.get(i)) {

                        //Líneas que salen del vértice
                        for (int j = 0; j < graphicEdges.size(); j++) {

                            if ((graphicVertexes.get(i).getLayoutX() == graphicEdges.get(j).getLayoutX()
                                    && graphicVertexes.get(i).getLayoutY() == graphicEdges.get(j).getLayoutY())) {

                                graphicEdges.get(j).setStroke(Color.RED);
                                graphicWeight.get(j).setStroke(Color.RED);
                                graphicWeight.get(j).toFront();

                            }

                        }
                        //Líneas que llegan al vértice
                        for (int j = 0; j < vertexes.length; j++) {

                            if (vertexes[j] != null) {
                                String data = vertexes[j].data + "";

                                if (data.equalsIgnoreCase(graphicVertexes.get(i).getText())) {

                                    for (int k = 0; k < adMatrix[0].length; k++) {

                                        if (!adMatrix[j][k].equals(0)) {

                                            Vertex v = vertexes[k];
                                            String data2 = v.data + "";

                                            for (int l = 0; l < graphicVertexes.size(); l++) {

                                                if (data2.equalsIgnoreCase(graphicVertexes.get(l).getText())) {

                                                    for (int m = 0; m < graphicEdges.size(); m++) {

                                                        double x = graphicVertexes.get(i).getLayoutX() - graphicVertexes.get(l).getLayoutX() + 15;
                                                        double y = graphicVertexes.get(i).getLayoutY() - graphicVertexes.get(l).getLayoutY() + 15;

                                                        if (graphicEdges.get(m).getEndX() == x && graphicEdges.get(m).getEndY() == y) {

                                                            graphicEdges.get(m).setStroke(Color.RED);
                                                            graphicWeight.get(m).setStroke(Color.RED);
                                                            graphicWeight.get(m).toFront();

                                                        }
                                                    }
                                                }
                                            }

                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    };
    EventHandler<MouseEvent> handler4 = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            if (graphicVertexes.size() > 1) {
                for (int i = 0; i < graphicVertexes.size(); i++) {

                    if (event.getSource() == graphicVertexes.get(i)) {

                        //Líneas que salen del vértice
                        for (int j = 0; j < graphicEdges.size(); j++) {

                            if ((graphicVertexes.get(i).getLayoutX() == graphicEdges.get(j).getLayoutX()
                                    && graphicVertexes.get(i).getLayoutY() == graphicEdges.get(j).getLayoutY())) {

                                graphicEdges.get(j).setStroke(Color.GRAY);
                                graphicWeight.get(j).setStroke(Color.GRAY);
                                graphicWeight.get(j).toBack();

                            }

                        }
                        //Líneas que llegan al vértice

                        for (int j = 0; j < vertexes.length; j++) {

                            if (vertexes[j] != null) {
                                String data = vertexes[j].data + "";

                                if (data.equalsIgnoreCase(graphicVertexes.get(i).getText())) {

                                    for (int k = 0; k < adMatrix[0].length; k++) {

                                        if (!adMatrix[j][k].equals(0)) {

                                            Vertex v = vertexes[k];
                                            String data2 = v.data + "";

                                            for (int l = 0; l < graphicVertexes.size(); l++) {

                                                if (data2.equalsIgnoreCase(graphicVertexes.get(l).getText())) {

                                                    for (int m = 0; m < graphicEdges.size(); m++) {

                                                        double x = graphicVertexes.get(i).getLayoutX() - graphicVertexes.get(l).getLayoutX() + 15;
                                                        double y = graphicVertexes.get(i).getLayoutY() - graphicVertexes.get(l).getLayoutY() + 15;

                                                        if (graphicEdges.get(m).getEndX() == x && graphicEdges.get(m).getEndY() == y) {

                                                            graphicEdges.get(m).setStroke(Color.GRAY);
                                                            graphicWeight.get(m).setStroke(Color.GRAY);
                                                            graphicWeight.get(m).toBack();

                                                        }
                                                    }
                                                }
                                            }

                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    };

    private void drawAdjacencyMatrixGraph(AdjacencyMatrixGraph graph, Pane graphPane) {

        try {
            double x = 210 + (graph.size() * 11);
            double y = 210 + (graph.size() * 11);
            int radio = 25 * graph.size();
            float angulo = 360;

            vertexes = graph.getVertexList();

            //Se dibujan los vértices
            for (int i = 0; i < graph.size(); i++) {

                aux = new Button(String.valueOf(vertexes[i].data));
                graphPane.getChildren().addAll(aux);
                aux.setLayoutX(x + radio * Math.cos(i * angulo));
                aux.setLayoutY(y - radio * Math.sin(i * angulo));
                aux.addEventHandler(MouseEvent.MOUSE_ENTERED, handler3);
                aux.addEventHandler(MouseEvent.MOUSE_EXITED, handler4);
                aux.setStyle("-fx-background-color:  #340049");
                aux.setTextFill(Color.WHITE);
                aux.setFont(new Font("Cambria", 13));
                Shape circleShape = new Circle(100);
                aux.setShape(circleShape);
            }

            //Se dibujan las aristas
            ObservableList<javafx.scene.Node> elements = graphPane.getChildren();

            graphicVertexes = new ArrayList<Button>();
            graphicEdges = new ArrayList<Line>();
            graphicWeight = new ArrayList<Text>();

            for (int i = 0; i < elements.size(); i++) {
                graphicVertexes.add((Button) elements.get(i));
            }

            adMatrix = graph.getAdjacencyMatrix();

            for (int i = 0; i < adMatrix.length; i++) {
                for (int j = 0; j < adMatrix[0].length; j++) {

                    if (adMatrix[i][j] != null && !adMatrix[i][j].equals(0)) {

                        Vertex v = vertexes[i];
                        Vertex v2 = vertexes[j];

                        for (int k = 0; k < graphicVertexes.size(); k++) {

                            String data2 = v.data + "";

                            if (data2.equalsIgnoreCase(graphicVertexes.get(k).getText())) {

                                for (int l = 0; l < graphicVertexes.size(); l++) {

                                    String data3 = v2.data + "";
                                    if (data3.equalsIgnoreCase(graphicVertexes.get(l).getText())) {

                                        Line line = new Line();
                                        graphPane.getChildren().add(line);
                                        line.setLayoutX(graphicVertexes.get(k).getLayoutX());
                                        line.setLayoutY(graphicVertexes.get(k).getLayoutY());
                                        line.toBack();
                                        line.setStartX(15);
                                        line.setStartY(15);
                                        line.setEndX(graphicVertexes.get(l).getLayoutX() - graphicVertexes.get(k).getLayoutX() + 15);
                                        line.setEndY(graphicVertexes.get(l).getLayoutY() - graphicVertexes.get(k).getLayoutY() + 15);
                                        line.setStroke(Color.GRAY);
                                        graphicEdges.add(line);

                                        Text txt = new Text();
                                        txt.setLayoutX((graphicVertexes.get(k).getLayoutX() + graphicVertexes.get(l).getLayoutX()) / 2);
                                        txt.setLayoutY((graphicVertexes.get(k).getLayoutY() + graphicVertexes.get(l).getLayoutY()) / 2);
                                        txt.setStroke(Color.GRAY);
                                        graphPane.getChildren().add(txt);

                                        String data = adMatrix[i][j] + "";
                                        if (!data.equalsIgnoreCase("edge")) {

                                            txt.setText(data);
                                        }
                                        graphicWeight.add(txt);

                                    }
                                }
                            }
                        }
                    }
                }
            }

        } catch (ListException ex) {
        }
    }

    @FXML
    private void btnRandomDistance(ActionEvent event) {
        try {

            //Se añaden los pesos
            for (int i = 0; i < vertexes.length; i++) {
                for (int j = 0; j < vertexes.length; j++) {

                    if (vertexes[i] != null && vertexes[j] != null && matrixGraph.containsEdge(vertexes[i].data, vertexes[j].data)) {
                        matrixGraph.addWeight(vertexes[i].data, vertexes[j].data, util.Utility.random(98) + 1 + " KM");
                    }

                }
            }
            FileManagementPlaces.addMatrixGraph(matrixGraph);
            panePlacesGraph.getChildren().clear();
            initTableSelectedPlaces();
            drawAdjacencyMatrixGraph(matrixGraph, panePlacesGraph);
            
        } catch (GraphException | ListException e) {

        }

    }

    @FXML
    private void btnNewGraph(ActionEvent event) {

        Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
        alert2.setTitle("Lugares");
        alert2.setHeaderText("\nLa información actual se perderá y deberá volver a generar otro grafo.\n\n"
                + "Precione aceptar para continuar.");
        Optional<ButtonType> action = alert2.showAndWait();

        if (action.get() == ButtonType.OK) {
            panePlaces.setVisible(false);
            matrixGraph = null;
            FileManagementPlaces.deleteFilesGraph();
        }
    }
}
