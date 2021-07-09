/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.restaurantsAndSupermarkets;

import data.FileManagementPlaces;
import data.FileManagementRestSuper;
import domain.Dijkstra;
import domain.Food;
import domain.Place;
import domain.Product;
import domain.Restaurant;
import domain.Supermarket;
import domain.bst.BST;
import domain.bst.TreeException;
import domain.graph.AdjacencyListGraph;
import domain.graph.AdjacencyMatrixGraph;
import domain.graph.GraphException;
import domain.graph.Vertex;
import domain.list.ListException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class RestSuperFXMLController implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ac;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnCancel;
    @FXML
    private Text txtTitle;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnCancelModify;
    @FXML
    private Button btnBack;
    @FXML
    private Text txtError;
    @FXML
    private Pane panelAddRestSuper;
    @FXML
    private TextField tfAddRestSuperId;
    @FXML
    private TextField tfAddNameRestSuper;
    @FXML
    private ComboBox<String> comboBoxLocationAdd;
    @FXML
    private ComboBox<String> comboBoxTypeAdd;
    @FXML
    private TextField tfRemoveIdSuperRest;
    @FXML
    private TextField tfNameSuperRestModify;
    @FXML
    private ComboBox<String> comboBoxSuperRestModify;
    @FXML
    private TextField tfSearchRestSuperUpdate;
    @FXML
    private Button btnSearchRestSuperUpdate;
    @FXML
    private TextField tfTypeSupeRestUpdate;
    @FXML
    private Button opcAdd;
    @FXML
    private Button opcDelete;
    @FXML
    private Button opcModify;
    @FXML
    private Button opcShow;
    @FXML
    private Text txtSucces;
    @FXML
    private Pane paneShowRestSuper;
    @FXML
    private TableView<Restaurant> restTable;
    @FXML
    private TableColumn<Restaurant, Integer> colIdRest;
    @FXML
    private TableColumn<Restaurant, String> colNameRest;
    @FXML
    private TableColumn<Restaurant, String> colRestLocaction;
    @FXML
    private TableView<Supermarket> superTable;
    @FXML
    private TableColumn<Supermarket, Integer> colIdSuper;
    @FXML
    private TableColumn<Supermarket, String> colNameSuper;
    @FXML
    private TableColumn<Supermarket, String> colSuperLocaction;
    @FXML
    private Pane panelDeleteRestSuper;
    @FXML
    private Pane restTablePane;
    @FXML
    private Pane superTablePane;
    @FXML
    private Pane panelModifyRestSuper;

    private AdjacencyListGraph graphRestSup;
    private AdjacencyMatrixGraph graphPlace;
    private BST bstFood;
    private BST bstProduct;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        graphRestSup = FileManagementRestSuper.getRestSupGraph();
        graphPlace = FileManagementPlaces.getPlacesGraph();
        bstFood = util.Utility.getBstFood();
        bstProduct = util.Utility.getBstProduct();
        initComboBox(comboBoxLocationAdd);
        initComboBox(comboBoxSuperRestModify);
        initComboBox(comboBoxTypeAdd);
        initRestaurantSupermarketTables();
        setAutoid();
    }

    private void setAutoid() {
        if (graphRestSup.isEmpty()) {

            tfAddRestSuperId.setText("1");

        } else {

            Vertex[] vertexes = graphRestSup.getVertexList();

            for (int i = 0; i < vertexes.length; i++) {
                if (vertexes[i] != null) {
                    if (vertexes[i].data instanceof Restaurant) {
                        Restaurant a = (Restaurant) vertexes[i].data;
                        tfAddRestSuperId.setText((a.getId() + 1) + "");
                    } else {
                        Supermarket a = (Supermarket) vertexes[i].data;
                        tfAddRestSuperId.setText((a.getId() + 1) + "");

                    }
                }
            }
        }
    }

    private void initRestaurantSupermarketTables() {

        colIdRest.setCellValueFactory(new PropertyValueFactory<Restaurant, Integer>("id"));
        colNameRest.setCellValueFactory(new PropertyValueFactory<Restaurant, String>("name"));
        colRestLocaction.setCellValueFactory(new PropertyValueFactory<Restaurant, String>("location"));

        colIdSuper.setCellValueFactory(new PropertyValueFactory<Supermarket, Integer>("id"));
        colNameSuper.setCellValueFactory(new PropertyValueFactory<Supermarket, String>("name"));
        colSuperLocaction.setCellValueFactory(new PropertyValueFactory<Supermarket, String>("location"));

        ObservableList<Restaurant> tableContent = FXCollections.observableArrayList();
        ObservableList<Supermarket> tableContent2 = FXCollections.observableArrayList();

        if (!graphRestSup.isEmpty()) {

            Vertex[] vertexes = graphRestSup.getVertexList();

            for (int i = 0; i < vertexes.length; i++) {

                if (vertexes[i] != null) {
                    if (vertexes[i].data instanceof Restaurant) {
                        Restaurant a = (Restaurant) vertexes[i].data;
                        String array[] = a.getLocation().split("~");
                        a.setLocation(array[0]);
                        tableContent.add(a);
                    } else {
                        Supermarket a = (Supermarket) vertexes[i].data;
                        String array[] = a.getLocation().split("~");
                        a.setLocation(array[0]);
                        tableContent2.add(a);
                    }
                }

            }
        }
        graphRestSup = FileManagementRestSuper.getRestSupGraph();
        restTable.setItems(tableContent);
        superTable.setItems(tableContent2);
    }

    private void initComboBox(ComboBox<String> comboBox) {

        ObservableList tableContent = FXCollections.observableArrayList();

        if (!graphPlace.isEmpty() && (comboBox == comboBoxLocationAdd || comboBox == comboBoxSuperRestModify)) {

            Vertex[] vertexes = graphPlace.getVertexList();

            for (int i = 0; i < vertexes.length; i++) {

                Place p = (Place) vertexes[i].data;
                tableContent.add(p.toString() + "");
            }
        } else {

            tableContent.add("Restaurante");
            tableContent.add("Supermercado");
            comboBox.setItems(tableContent);

        }

        comboBox.setPromptText("Seleccione");
        comboBox.setItems(tableContent);
        comboBox.setVisibleRowCount(3);

    }

    @FXML
    private void btnAdd(ActionEvent event) {
        try {
            if (!(tfAddNameRestSuper.getText().equals("") || comboBoxLocationAdd.getValue() == null
                    || comboBoxTypeAdd.getValue() == null)) {

                graphRestSup = FileManagementRestSuper.getRestSupGraph();

                if (comboBoxTypeAdd.getValue().equals("Restaurante")) {

                    Restaurant rest = new Restaurant(Integer.parseInt(tfAddRestSuperId.getText()), tfAddNameRestSuper.getText(), comboBoxLocationAdd.getValue());
                    graphRestSup.addVertex(rest);
                    addEveryEdgesAndWeight();
                    FileManagementRestSuper.addListGraph(graphRestSup);
                    txtError.setText("");
                    txtSucces.setText("Agregado con éxito");
                    tfAddNameRestSuper.setText("");
                    graphRestSup = FileManagementRestSuper.getRestSupGraph();
                    comboBoxLocationAdd.setValue(null);
                    comboBoxTypeAdd.setValue(null);

                } else if (comboBoxTypeAdd.getValue().equals("Supermercado")) {

                    Supermarket sup = new Supermarket(Integer.parseInt(tfAddRestSuperId.getText()), tfAddNameRestSuper.getText(), comboBoxLocationAdd.getValue());
                    graphRestSup.addVertex(sup);
                    addEveryEdgesAndWeight();
                    FileManagementRestSuper.addListGraph(graphRestSup);
                    txtError.setText("");
                    txtSucces.setText("Agregado con éxito");
                    tfAddNameRestSuper.setText("");
                    graphRestSup = FileManagementRestSuper.getRestSupGraph();
                    comboBoxLocationAdd.setValue(null);
                    comboBoxTypeAdd.setValue(null);

                }
                initRestaurantSupermarketTables();
                setAutoid();

            } else {

                txtError.setText("Debe rellenar todos los recuadros");
                txtSucces.setText("");
            }
        } catch (GraphException | ListException ex) {
        }
    }

    private void addEveryEdgesAndWeight() {

        try {
            if (graphRestSup.size() > 1) {

                Vertex[] vertexes = graphRestSup.getVertexList();
                Object[][] adMatrix = graphPlace.getAdjacencyMatrix();

                for (int i = 0; i < vertexes.length; i++) {

                    if (vertexes[i] != null && i + 1 < vertexes.length) {

                        graphRestSup.addEdge(vertexes[i].data, vertexes[i + 1].data);

                        if (vertexes[i].data instanceof Restaurant && vertexes[i + 1].data instanceof Restaurant) {

                            Restaurant r = (Restaurant) vertexes[i].data;
                            Restaurant r2 = (Restaurant) vertexes[i + 1].data;

                            //Si están en el mismo lugar
                            if (r.getLocation().equalsIgnoreCase(r2.getLocation())) {

                                graphRestSup.addWeight(vertexes[i].data, vertexes[i + 1].data, r.getLocation());

                            } else {

                                //Usamos dijkstra, y así obtenemos las distancias entre cada vértice
                                int index = indexOfPlaces(r.getLocation());
                                int index2 = indexOfPlaces(r2.getLocation());

                                int[] distances = Dijkstra.dijkstra(graphPlace, index);
                                graphRestSup.addWeight(vertexes[i].data, vertexes[i + 1].data, distances[index2] + " KM");
                            }
                        }
                        if (vertexes[i].data instanceof Restaurant && vertexes[i + 1].data instanceof Supermarket) {

                            Restaurant r = (Restaurant) vertexes[i].data;
                            Supermarket r2 = (Supermarket) vertexes[i + 1].data;

                            //Si están en el mismo lugar
                            if (r.getLocation().equalsIgnoreCase(r2.getLocation())) {

                                graphRestSup.addWeight(vertexes[i].data, vertexes[i + 1].data, r.getLocation());

                            } else {

                                //Usamos dijkstra, y así obtenemos las distancias entre cada vértice
                                int index = indexOfPlaces(r.getLocation());
                                int index2 = indexOfPlaces(r2.getLocation());

                                int[] distances = Dijkstra.dijkstra(graphPlace, index);
                                graphRestSup.addWeight(vertexes[i].data, vertexes[i + 1].data, distances[index2] + " KM");
                            }
                        } else if (vertexes[i].data instanceof Supermarket && vertexes[i + 1].data instanceof Restaurant) {

                            Restaurant r2 = (Restaurant) vertexes[i + 1].data;
                            Supermarket r = (Supermarket) vertexes[i].data;

                            //Si están en el mismo lugar
                            if (r.getLocation().equalsIgnoreCase(r2.getLocation())) {

                                graphRestSup.addWeight(vertexes[i].data, vertexes[i + 1].data, r.getLocation());

                            } else {

                                //Usamos dijkstra, y así obtenemos las distancias entre cada vértice
                                int index = indexOfPlaces(r.getLocation());
                                int index2 = indexOfPlaces(r2.getLocation());

                                int[] distances = Dijkstra.dijkstra(graphPlace, index);
                                graphRestSup.addWeight(vertexes[i].data, vertexes[i + 1].data, distances[index2] + " KM");
                            }
                        } else if (vertexes[i].data instanceof Supermarket && vertexes[i + 1].data instanceof Supermarket) {

                            Supermarket r = (Supermarket) vertexes[i].data;
                            Supermarket r2 = (Supermarket) vertexes[i + 1].data;

                            //Si están en el mismo lugar
                            if (r.getLocation().equalsIgnoreCase(r2.getLocation())) {

                                graphRestSup.addWeight(vertexes[i].data, vertexes[i + 1].data, r.getLocation());

                            } else {

                                //Usamos dijkstra, y así obtenemos las distancias entre cada vértice
                                int index = indexOfPlaces(r.getLocation());
                                int index2 = indexOfPlaces(r2.getLocation());

                                int[] distances = Dijkstra.dijkstra(graphPlace, index);
                                graphRestSup.addWeight(vertexes[i].data, vertexes[i + 1].data, distances[index2] + " KM");
                            }
                        }
                    }
                }
            }
        } catch (ListException | GraphException ex) {

            ex.printStackTrace();
        }
    }

    private int indexOfPlaces(String location) {

        Vertex[] vertexes = graphPlace.getVertexList();

        for (int i = 0; i < vertexes.length; i++) {

            Place p = (Place) vertexes[i].data;

            if (p.toString().equals(location)) {
                return i;
            }
        }
        return -1;
    }

    @FXML
    private void btnCancel(ActionEvent event) {

        txtError.setText("");
        txtSucces.setText("");
        tfAddNameRestSuper.setText("");
        comboBoxLocationAdd.setValue(null);
        comboBoxTypeAdd.setValue(null);
    }

    @FXML
    private void btnDelete(ActionEvent event) {

        if (!tfRemoveIdSuperRest.getText().equals("")) {

            Vertex[] vertexes = graphRestSup.getVertexList();

            if (!graphRestSup.isEmpty()) {
                try {
                    txtSucces.setText("");
                    txtError.setText("Establecimiento no encontrado");
                    for (int i = 0; i < vertexes.length; i++) {

                        if (vertexes[i] != null) {

                            if (vertexes[i].data instanceof Restaurant) {

                                Restaurant r = (Restaurant) vertexes[i].data;
                                if (r.getId() == Integer.parseInt(tfRemoveIdSuperRest.getText())) {
                                    
                                    if (bstFood.isEmpty() || !bstFood.contains3(new Food("", 0, r.getId()))) {

                                        graphRestSup.removeVertex(vertexes[i].data);
                                        FileManagementRestSuper.addListGraph(graphRestSup);
                                        txtError.setText("");
                                        txtSucces.setText("Eliminado con éxito");
                                        tfRemoveIdSuperRest.setText("");
                                        initRestaurantSupermarketTables();
                                        i = vertexes.length;
                                    } else {
                                        txtError.setText("Imposible eliminar, el establecimiento tiene productos y/o comidas registradas");
                                        txtSucces.setText("");
                                        i = vertexes.length;
                                    }

                                } else {
                                    txtError.setText("Establecimiento no agregado");
                                    txtSucces.setText("");
                                }
                            } else {
                                Supermarket s = (Supermarket) vertexes[i].data;

                                if (s.getId() == Integer.parseInt(tfRemoveIdSuperRest.getText())) {
                                    
                                    if (bstProduct.isEmpty() || !bstProduct.contains3(new Product("", 0, s.getId()))) {
                                        graphRestSup.removeVertex(vertexes[i].data);
                                        FileManagementRestSuper.addListGraph(graphRestSup);
                                        txtError.setText("");
                                        txtSucces.setText("Eliminado con éxito");
                                        tfRemoveIdSuperRest.setText("");
                                        initRestaurantSupermarketTables();
                                        i = vertexes.length;
                                    } else {
                                        txtError.setText("Imposible eliminar, el establecimiento tiene productos y/o comidas registradas");
                                        txtSucces.setText("");
                                        i = vertexes.length;
                                    }
                                }
                            }
                        }
                    }

                } catch (NumberFormatException e) {
                    txtError.setText("El identificador debe ser numérico");
                    txtSucces.setText("");
                } catch (GraphException | ListException | TreeException ex) {
                    ex.printStackTrace();
                }
            } else {
                txtError.setText("No hay establecimientos agregados");
                txtSucces.setText("");
            }

        } else {
            txtError.setText("Debe rellenar todos los recuadros");
            txtSucces.setText("");
        }
    }

    @FXML
    private void btnUpdate(ActionEvent event) {

        if (!(tfSearchRestSuperUpdate.getText().equals("") || tfNameSuperRestModify.getText().equals("")
                || comboBoxSuperRestModify.getValue() == null)) {

            txtError.setText("");
            txtSucces.setText("");
            Vertex[] vertexes = graphRestSup.getVertexList();
            try {

                for (int i = 0; i < vertexes.length; i++) {

                    if (vertexes[i] != null) {

                        if (vertexes[i].data instanceof Restaurant) {

                            Restaurant r = (Restaurant) vertexes[i].data;

                            if (r.getId() == Integer.parseInt(tfSearchRestSuperUpdate.getText())) {

                                //Se elimina el elemento viejo
                                graphRestSup.removeVertex(vertexes[i].data);
                                FileManagementRestSuper.addListGraph(graphRestSup);
                                initRestaurantSupermarketTables();

                                //Se añade el nuevo
                                Restaurant rest = new Restaurant(Integer.parseInt(tfSearchRestSuperUpdate.getText()), tfNameSuperRestModify.getText(), comboBoxSuperRestModify.getValue());
                                graphRestSup.addVertex(rest);
                                addEveryEdgesAndWeight();
                                FileManagementRestSuper.addListGraph(graphRestSup);
                                graphRestSup = FileManagementRestSuper.getRestSupGraph();
                                btnCancelModify(event);
                                txtSucces.setText("Modificado con éxito");
                                txtError.setText("");
                                i = vertexes.length;
                            }
                        } else {

                            Supermarket s = (Supermarket) vertexes[i].data;

                            if (s.getId() == Integer.parseInt(tfSearchRestSuperUpdate.getText())) {

                                //Se elimina el elemento viejo
                                graphRestSup.removeVertex(vertexes[i].data);
                                FileManagementRestSuper.addListGraph(graphRestSup);
                                initRestaurantSupermarketTables();

                                //Se añade el nuevo
                                Supermarket sup = new Supermarket(Integer.parseInt(tfSearchRestSuperUpdate.getText()), tfNameSuperRestModify.getText(), comboBoxSuperRestModify.getValue());
                                graphRestSup.addVertex(sup);
                                addEveryEdgesAndWeight();
                                FileManagementRestSuper.addListGraph(graphRestSup);
                                graphRestSup = FileManagementRestSuper.getRestSupGraph();
                                btnCancelModify(event);
                                txtSucces.setText("Modificado con éxito");
                                txtError.setText("");
                                i = vertexes.length;
                            }
                        }
                    }
                }
                initRestaurantSupermarketTables();
            } catch (GraphException | ListException ex) {
                ex.printStackTrace();
            }

        }
    }

    @FXML
    private void btnCancelModify(ActionEvent event) {
        tfSearchRestSuperUpdate.setText("");
        tfNameSuperRestModify.setText("");
        tfSearchRestSuperUpdate.setText("");
        tfTypeSupeRestUpdate.setText("");
        tfNameSuperRestModify.setDisable(true);
        tfSearchRestSuperUpdate.setDisable(false);
        comboBoxSuperRestModify.setDisable(true);
        btnUpdate.setDisable(true);
        btnCancelModify.setDisable(true);
        comboBoxSuperRestModify.setValue(null);
    }

    @FXML
    private void btnBack(ActionEvent event) {

        bp.setVisible(false);
    }

    @FXML
    private void tfAddNameRestSuper(KeyEvent event) {

        txtError.setText("");
        txtSucces.setText("");
    }

    @FXML
    private void tfRemoveIdSuperRest(KeyEvent event) {

        txtError.setText("");
        txtSucces.setText("");

        try {

            char c = event.getText().charAt(0);

            //isDigit permite sólo números, isAphabetic permite sólo letras
            if (!Character.isDigit(c)) {

                tfRemoveIdSuperRest.setEditable(false);

            } else {

                tfRemoveIdSuperRest.setEditable(true);
            }

        } catch (Exception e) {

            tfRemoveIdSuperRest.setEditable(true);

        }
    }

    @FXML
    private void tfSearchRestSuperUpdate(KeyEvent event) {

        txtError.setText("");
        txtSucces.setText("");

        try {

            char c = event.getText().charAt(0);

            //isDigit permite sólo números, isAphabetic permite sólo letras
            if (!Character.isDigit(c)) {

                tfSearchRestSuperUpdate.setEditable(false);

            } else {

                tfSearchRestSuperUpdate.setEditable(true);
            }

        } catch (Exception e) {

            tfSearchRestSuperUpdate.setEditable(true);

        }
    }

    @FXML
    private void btnSearchRestSuperUpdate(ActionEvent event) {

        Vertex[] vertexes = graphRestSup.getVertexList();

        if (!tfSearchRestSuperUpdate.getText().equals("")) {

            if (!graphRestSup.isEmpty()) {
                try {
                    txtError.setText("Establecimiento no agregado");
                    txtSucces.setText("");
                    for (int i = 0; i < vertexes.length; i++) {

                        if (vertexes[i] != null) {

                            if (vertexes[i].data instanceof Restaurant) {

                                Restaurant r = (Restaurant) vertexes[i].data;

                                if (r.getId() == Integer.parseInt(tfSearchRestSuperUpdate.getText())) {

                                    txtError.setText("");
                                    txtSucces.setText("");
                                    tfNameSuperRestModify.setDisable(false);
                                    tfSearchRestSuperUpdate.setDisable(true);
                                    comboBoxSuperRestModify.setDisable(false);
                                    btnUpdate.setDisable(false);
                                    btnCancelModify.setDisable(false);
                                    tfNameSuperRestModify.setText(r.getName());
                                    tfTypeSupeRestUpdate.setText("Restaurant");
                                    comboBoxSuperRestModify.setValue(r.getLocation());
                                    i = vertexes.length;

                                }
                            } else {
                                Supermarket s = (Supermarket) vertexes[i].data;

                                if (s.getId() == Integer.parseInt(tfSearchRestSuperUpdate.getText())) {

                                    txtError.setText("");
                                    txtSucces.setText("");
                                    tfNameSuperRestModify.setDisable(false);
                                    tfSearchRestSuperUpdate.setDisable(true);
                                    comboBoxSuperRestModify.setDisable(false);
                                    btnUpdate.setDisable(false);
                                    btnCancelModify.setDisable(false);
                                    tfNameSuperRestModify.setText(s.getName());
                                    tfTypeSupeRestUpdate.setText("Supermarket");
                                    comboBoxSuperRestModify.setValue(s.getLocation());
                                    i = vertexes.length;
                                }
                            }
                        }
                    }

                } catch (NumberFormatException e) {
                    txtError.setText("El identificador debe ser numérico");
                    txtSucces.setText("");
                }

            } else {
                txtError.setText("No hay establecimientos agregados");
                txtSucces.setText("");
            }
        } else {
            txtError.setText("Debe rellenar el recuadro");
            txtSucces.setText("");
        }
    }

    @FXML
    private void opcAdd(ActionEvent event
    ) {

        cleanAll();
        setAutoid();
        btnCancel(event);
        panelAddRestSuper.setVisible(true);
        txtTitle.setText("Agregar restaurantes & supermercados");
    }

    @FXML
    private void opcDelete(ActionEvent event
    ) {

        cleanAll();
        panelDeleteRestSuper.setVisible(true);
        txtTitle.setText("Eliminar restaurantes & supermercados");
    }

    @FXML
    private void opcModify(ActionEvent event
    ) {

        cleanAll();
        panelModifyRestSuper.setVisible(true);
        txtTitle.setText("Modificar restaurantes & supermercados");
    }

    @FXML
    private void opcShow(ActionEvent event
    ) {

        cleanAll();
        paneShowRestSuper.setVisible(true);
        txtTitle.setText("Listas de restaurantes & supermercados");
    }

    private void cleanAll() {

        txtError.setText("");
        txtSucces.setText("");
        tfRemoveIdSuperRest.setText("");
        tfAddNameRestSuper.setText("");
        tfSearchRestSuperUpdate.setText("");
        tfNameSuperRestModify.setText("");
        tfSearchRestSuperUpdate.setText("");
        tfTypeSupeRestUpdate.setText("");

        comboBoxSuperRestModify.setValue(null);
        comboBoxLocationAdd.setValue(null);
        comboBoxTypeAdd.setValue(null);
        paneShowRestSuper.setVisible(false);
        panelAddRestSuper.setVisible(false);
        panelDeleteRestSuper.setVisible(false);
        panelModifyRestSuper.setVisible(false);

        tfNameSuperRestModify.setDisable(true);
        tfSearchRestSuperUpdate.setDisable(false);
        comboBoxSuperRestModify.setDisable(true);
        btnUpdate.setDisable(true);
        btnCancelModify.setDisable(true);
    }
}
