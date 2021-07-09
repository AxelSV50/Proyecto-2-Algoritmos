/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.product;

import data.FileManagementProduct;
import data.FileManagementRestSuper;
import domain.Place;
import domain.Product;
import domain.Supermarket;
import domain.bst.BST;
import domain.bst.TreeException;
import domain.graph.AdjacencyListGraph;
import domain.graph.Vertex;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import main.food.FoodFXMLController;
import main.security.SecurityFXMLController;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class ProductFXMLController implements Initializable {

    @FXML
    private Button btnBack;
    @FXML
    private Button btnAdd;
    @FXML
    private Text txtTitle;
    @FXML
    private Button btnDelete;
    @FXML
    private Pane panelModificar;
    @FXML
    private Button btnUpdate;
    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ac;
    @FXML
    private Text txtError;
    @FXML
    private Text txtAdded;
    @FXML
    private Button btnUpdateCancel;
    @FXML
    private Button btnAddProduct;
    @FXML
    private Button btnDeleteProduct;
    @FXML
    private Button btnModifyProduct;
    @FXML
    private Button btnShowProduct;
    @FXML
    private TextField tfAddProductId;

    @FXML
    private TextField tfRemoveProductId;
    @FXML
    private TextField tfNameUpdate;
    @FXML
    private TableView<List<String>> tableProduct;
    @FXML
    private TableColumn<List<String>, String> colIdProduct;
    @FXML
    private TableColumn<List<String>, String> colNameProduct;
    @FXML
    private TableColumn<List<String>, String> colPriceProduct;
    @FXML
    private TableColumn<List<String>, String> colIdSuperProduct;
    @FXML
    private TextField tfSearchProductUpdate;
    @FXML
    private Button btnSearchProductUpdate;
    @FXML
    private Pane panelAddProduct;
    @FXML
    private Pane panelDeleteProduct;

    private BST bstProduct = util.Utility.getBstProduct();
    @FXML
    private Pane showProductPane;

    @FXML
    private TextField tfPriceUpdate;
    @FXML
    private ComboBox<String> cbIdSupermarketUpdate;
    @FXML
    private ComboBox<String> cbIdSupermarket;
//    private AdjacencyListGraph studentList = util.Utility.getRestYSuperGraph();
    @FXML
    private Pane foodTablePane;
    @FXML
    private Button btnCancel;
    @FXML
    private TextField tfAddNameProduct;
    @FXML
    private TextField tfAddPriceProduct;

    private AdjacencyListGraph graphRestSup;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bstProduct = util.Utility.getBstProduct();
        graphRestSup = FileManagementRestSuper.getRestSupGraph();

        colIdProduct.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(0)); //To change body of generated methods, choose Tools | Templates.
            }
        });
        colNameProduct.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(1)); //To change body of generated methods, choose Tools | Templates.
            }
        });
        colPriceProduct.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(2)); //To change body of generated methods, choose Tools | Templates.
            }
        });
        colIdSuperProduct.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(3)); //To change body of generated methods, choose Tools | Templates.
            }
        });

        initComboBox(cbIdSupermarket);
        initComboBox(cbIdSupermarketUpdate);
        tableProduct.setVisible(true);
        showProductPane.setVisible(true);

        txtTitle.setText("Lista de productos");
        initTable();

    }

    private void initComboBox(ComboBox<String> comboBox) {

        ObservableList tableContent = FXCollections.observableArrayList();

        if (!graphRestSup.isEmpty() && (comboBox == cbIdSupermarket || comboBox == cbIdSupermarketUpdate)) {

            Vertex[] vertexes = graphRestSup.getVertexList();

            for (int i = 0; i < vertexes.length; i++) {

                if (vertexes[i] != null) {

                    if (vertexes[i].data instanceof Supermarket) {

                        Supermarket s = (Supermarket) vertexes[i].data;
                        tableContent.add(s.getId() + "-" + s.getName());

                    }

                }

            }
        }
        comboBox.setPromptText("Seleccione");
        comboBox.setItems(tableContent);
        comboBox.setVisibleRowCount(3);

    }

    @FXML
    private void btnBack(ActionEvent event) {
        bp.setVisible(false);
    }

    @FXML
    private void btnAdd(ActionEvent event) throws TreeException {

        if (!(tfAddProductId.textProperty().getValue().equals("") || tfAddNameProduct.textProperty().getValue().equals("")
                || tfAddPriceProduct.textProperty().getValue().equals("") || cbIdSupermarket.getValue() == null)) {
            txtError.setText("");
            ArrayList<Object> bstList = new ArrayList<Object>();

            try {

                String[] array2 = cbIdSupermarket.getValue().split("-");

                if (bstProduct.isEmpty() || !bstProduct.contains2(new Product(0, tfAddNameProduct.textProperty().getValue(),
                        0, Integer.parseInt(array2[0])))) {
                    String idRestaurant = cbIdSupermarket.getValue();
                    //Si el usuario no selecciona nada, el combo devuelve null

                    String[] array = idRestaurant.split("-");
                    txtError.setText("");
                    //Agrega la carrera al archivo 
                    FileManagementProduct.add(Integer.parseInt(tfAddProductId.textProperty().getValue()), tfAddNameProduct.textProperty().getValue(), Double.parseDouble(tfAddPriceProduct.textProperty().getValue()), Integer.parseInt(array[0]), FileManagementProduct.getNameFileProduct());
                    //Actualiza la lista para que salga la carrera nueva
                    bstProduct = util.Utility.getBstProduct();
                    //Setter los textField

                    bstList = bstProduct.preOrder2();
                    if (bstList == null) {
                        tfAddProductId.setText("1");
                    } else {
                        Product p = (Product) bstList.get(bstList.size() - 1);
                        int temp = p.getId() + 1;
                        tfAddProductId.setText(temp + "");
                    };

                    tfAddNameProduct.setText("");
                    tfAddPriceProduct.setText("");
                    cbIdSupermarket.setValue("");
                    //Le dice al usuario que se agrego
                    txtAdded.setText("Agregado con éxito");
                } else {

                    //Si existe lanza este mensaje al usuario
                    txtError.setText("El producto ya está registrado en el supermercado seleccionado");
                    txtAdded.setText("");

                }

            } catch (NumberFormatException e) {

                txtError.setText("El precio debe ser numérico");
                txtAdded.setText("");

            } catch (TreeException ex) {
                Logger.getLogger(FoodFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {

            txtError.setText("Debe rellenar todos los recuadros");
            txtAdded.setText("");

        }
    }

    @FXML
    private void btnDelete(ActionEvent event) throws TreeException {
        if (!tfRemoveProductId.textProperty().getValue().equals("")) {
            ArrayList<Object> bstList = new ArrayList<Object>();
            try {
                //Busca si el elemento está
                Product element = new Product(Integer.parseInt(tfRemoveProductId.textProperty().getValue()), "", 0, 0);
                if (bstProduct.contains(element)) {
                    bstProduct.remove(element);
                    //Reescribe el archivo
                    FileManagementProduct.overwriteProductFile(bstProduct);
                    tfRemoveProductId.setText("");
                    txtError.setText("");
                    txtAdded.setText("Producto eliminado correctamente");
                } else {
                    txtError.setText("Producto no encontrado");
                    txtAdded.setText("");
                    tfRemoveProductId.setText("");
                }
            } catch (TreeException ex) {
                txtError.setText("No hay productos agregados");
                txtAdded.setText("");
                tfRemoveProductId.setText("");
            }
        } else {
            txtError.setText("Debe rellenar todos los recuadros");
            txtAdded.setText("");
        }
    }

    @FXML
    private void btnUpdate(ActionEvent event) throws TreeException {
        if (!tfNameUpdate.textProperty().getValue().equals("") && !tfPriceUpdate.textProperty().getValue().equals("")
                && !tfSearchProductUpdate.textProperty().getValue().equals("")) {
            try {
                String idSupermarket = cbIdSupermarketUpdate.getValue();
                String[] array = idSupermarket.split("-");
                txtError.setText("");
                Object newElement = new Product(Integer.parseInt(tfSearchProductUpdate.textProperty().getValue()), tfNameUpdate.textProperty().getValue(),
                        Double.parseDouble(tfPriceUpdate.textProperty().getValue()), Integer.parseInt(array[0]));
                Object oldElement = new Product(Integer.parseInt(tfSearchProductUpdate.textProperty().getValue()), "", 0, 0);

                if (bstProduct.contains(oldElement) && !bstProduct.contains2(newElement)) {

                    bstProduct.remove(oldElement);
                    bstProduct.add(newElement);
                    FileManagementProduct.overwriteProductFile(bstProduct);
                    txtError.setText("");
                    tfNameUpdate.setText("");
                    tfPriceUpdate.setText("");
                    tfSearchProductUpdate.setText("");
                    cbIdSupermarketUpdate.setValue("");
                    btnUpdate.setDisable(true);
                    txtAdded.setText("Producto modificado correctamente");
                    tfNameUpdate.setDisable(true);
                    tfPriceUpdate.setDisable(true);
                    btnUpdate.setDisable(true);
                    btnUpdateCancel.setDisable(true);
                    tfSearchProductUpdate.setDisable(false);
                    cbIdSupermarketUpdate.setDisable(true);
                } else {
                    txtError.setText("El supermercado seleccionado ya contiene este producto");
                    txtAdded.setText("");

                }
            } catch (TreeException ex) {

                txtError.setText("No hay productos agregados");
                txtAdded.setText("");

                tfNameUpdate.setText("");
                tfPriceUpdate.setText("");
                tfSearchProductUpdate.setText("");
                cbIdSupermarketUpdate.setValue("");
                btnUpdate.setDisable(true);

            } catch (NumberFormatException ex) {

                txtError.setText("El identificador del producto debe ser numérico");
                txtAdded.setText("");
            }
        } else {

            txtError.setText("Debe rellenar todos los recuadros");
            txtAdded.setText("");

        }
    }

    private void cleanAll() {
        panelAddProduct.setVisible(false);
        panelModificar.setVisible(false);
        panelDeleteProduct.setVisible(false);
        tfSearchProductUpdate.setVisible(false);
        btnSearchProductUpdate.setVisible(false);
        showProductPane.setVisible(false);
        tfRemoveProductId.setText("");
        tfAddNameProduct.setText("");
        tfNameUpdate.setText("");
        tfAddProductId.setText("");
        tfAddPriceProduct.setText("");
        tfPriceUpdate.setText("");
        tfPriceUpdate.setDisable(true);
        cbIdSupermarket.setValue(null);
        cbIdSupermarketUpdate.setValue(null);
        tfNameUpdate.setDisable(true);
        btnUpdate.setDisable(true);
        tfSearchProductUpdate.setDisable(false);
        btnUpdateCancel.setDisable(true);
        cbIdSupermarketUpdate.setDisable(true);
        tfSearchProductUpdate.setText("");
        txtTitle.setText("");
        txtError.setText("");
        txtAdded.setText("");

    }

    private void loadPage(String page) {

        Parent root = null;

        try {

            root = FXMLLoader.load(getClass().getResource(page + ".fxml"));

        } catch (IOException ex) {
            Logger.getLogger(SecurityFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.bp.setRight(root);

    }

    private void tfRemoveId(KeyEvent event) {

        txtAdded.setText("");

        try {

            char c = event.getText().charAt(0);

            if (!Character.isDigit(c)) {

                tfRemoveProductId.setEditable(false);

            } else {

                tfRemoveProductId.setEditable(true);
            }

        } catch (Exception e) {

            tfRemoveProductId.setEditable(true);

        }
    }

    @FXML
    private void tfSearchProductUpdate(KeyEvent event) {

        txtAdded.setText("");

        try {

            char c = event.getText().charAt(0);

            if (!Character.isDigit(c)) {

                tfSearchProductUpdate.setEditable(false);

            } else {

                tfSearchProductUpdate.setEditable(true);
            }

        } catch (Exception e) {

            tfSearchProductUpdate.setEditable(true);

        }
    }

    @FXML
    private void btnUpdateCancel(ActionEvent event) {
        txtError.setText("");
        txtAdded.setText("");
        tfNameUpdate.setText("");
        tfPriceUpdate.setText("");
        tfSearchProductUpdate.setText("");
        cbIdSupermarketUpdate.setValue("");
        btnUpdate.setDisable(true);
        tfNameUpdate.setDisable(true);
        tfPriceUpdate.setDisable(true);
        btnUpdate.setDisable(true);
        btnUpdateCancel.setDisable(true);
        tfSearchProductUpdate.setDisable(false);
        cbIdSupermarketUpdate.setDisable(true);
    }

    @FXML
    private void btnAddProduct(ActionEvent event) {
        ArrayList<Object> bstList = new ArrayList<Object>();
        cleanAll();
        try {

            if (bstProduct.isEmpty()) {
                tfAddProductId.setText("1");
            } else {
                bstList = bstProduct.preOrder2();
                Product p = (Product) bstList.get(bstList.size() - 1);
                tfAddProductId.setText(p.getId() + 1 + "");
            }
            panelAddProduct.setVisible(true);
            txtTitle.setText("Agregar producto");
        } catch (TreeException ex) {
            Logger.getLogger(FoodFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnDeleteProduct(ActionEvent event) {
        cleanAll();
        panelDeleteProduct.setVisible(true);
        txtTitle.setText("Suprimir producto");
    }

    @FXML
    private void btnModifyProduct(ActionEvent event) {
        cleanAll();
        panelModificar.setVisible(true);
        tfSearchProductUpdate.setVisible(true);
        btnSearchProductUpdate.setVisible(true);
        txtTitle.setText("Modificar producto");
    }

    @FXML
    private void btnShowProduct(ActionEvent event) throws TreeException {
        cleanAll();
        initTable();
        showProductPane.setVisible(true);
        txtTitle.setText("Lista de productos");
    }

    @FXML
    private void btnSearchProductUpdate(ActionEvent event) throws TreeException {
        if (!tfSearchProductUpdate.textProperty().getValue().equals("")) {

            try {
                ArrayList<Object> bstList = new ArrayList<Object>();

                bstProduct = util.Utility.getBstProduct();
                Product element = new Product(Integer.parseInt(tfSearchProductUpdate.textProperty().getValue()), "", 0, 0);

                if (bstProduct.contains(element)) {

                    bstList = bstProduct.preOrder2();
                    for (int i = 0; i < bstList.size(); i++) {
                        Product p = (Product) bstList.get(i);
                        if (element.getId() == p.getId()) {

                            tfNameUpdate.setText(p.getName());
                            tfPriceUpdate.setText(p.getPrice() + "");
                            cbIdSupermarketUpdate.setValue(p.getSupermarketID() + "");
                            txtError.setText("");
                            txtAdded.setText("");
                            btnUpdate.setDisable(false);
                            btnUpdateCancel.setDisable(false);
                            tfNameUpdate.setDisable(false);
                            tfPriceUpdate.setDisable(false);
                            cbIdSupermarketUpdate.setDisable(false);
                            tfSearchProductUpdate.setDisable(true);

                        }
                    }
                } else {

                    txtError.setText("Producto no encontrado");
                    txtAdded.setText("");
                    tfNameUpdate.setText("");
                    tfSearchProductUpdate.setText("");
                    btnUpdate.setDisable(true);

                }

            } catch (TreeException ex) {

                txtError.setText("No hay productos agregados");
                txtAdded.setText("");

            } catch (NumberFormatException ex) {
                txtError.setText("El identificador debe ser numérico");
                txtAdded.setText("");
            }
        } else {

            txtError.setText("Debe rellenar todos los recuadros");
            txtAdded.setText("");

        }
    }

    private void initTable() {
        ObservableList<List<String>> tableContent = FXCollections.observableArrayList();
        ArrayList<Object> bstList = new ArrayList<Object>();

        if (!bstProduct.isEmpty()) {
            try {
                bstList = bstProduct.preOrder2();

                for (int i = 0; i < bstList.size(); i++) {

                    Product p = (Product) bstList.get(i);
                    List<String> arrayList = new ArrayList<>();
                    //Agregamos todos los datos al arrayList
                    arrayList.add(p.getId() + "");
                    arrayList.add(p.getName());
                    arrayList.add(p.getPrice() + "");
                    arrayList.add(p.getSupermarketID() + "");

                    tableContent.add(arrayList);

                }
            } catch (TreeException ex) {
                Logger.getLogger(FoodFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        tableProduct.setItems(tableContent);
    }

    @FXML
    private void btnCancel(ActionEvent event) {
        txtAdded.setText("");
        txtError.setText("");
        tfAddNameProduct.setText("");
        tfAddPriceProduct.setText("");
        cbIdSupermarket.setValue("");
    }

    @FXML
    private void tfRemoveProductId(KeyEvent event) {
        txtAdded.setText("");

        try {

            char c = event.getText().charAt(0);

            if (!Character.isDigit(c)) {

                tfRemoveProductId.setEditable(false);

            } else {

                tfRemoveProductId.setEditable(true);
            }

        } catch (Exception e) {

            tfRemoveProductId.setEditable(true);

        }
    }

    @FXML
    private void tfNameUpdate(KeyEvent event) {
        txtAdded.setText("");

        try {

            char c = event.getText().charAt(0);

            //isDigit permite sólo números, isAphabetic permite sólo letras
            if (!Character.isAlphabetic(c) && !Character.isSpaceChar(c)) {

                tfNameUpdate.setEditable(false);

            } else {

                tfNameUpdate.setEditable(true);
            }

        } catch (Exception e) {

            tfNameUpdate.setEditable(true);

        }

    }

    @FXML
    private void tfPriceUpdate(KeyEvent event) {
        txtAdded.setText("");

        try {

            char c = event.getText().charAt(0);

            if (Character.isDigit(c) || c == '.') {
                tfPriceUpdate.setEditable(true);

            } else {

                tfPriceUpdate.setEditable(false);
            }

        } catch (Exception e) {

            tfPriceUpdate.setEditable(true);

        }
    }

    @FXML
    private void tfAddProductId(KeyEvent event) {
        txtAdded.setText("");

        try {

            char c = event.getText().charAt(0);

            if (!Character.isDigit(c)) {

                tfAddProductId.setEditable(false);

            } else {

                tfAddProductId.setEditable(true);
            }

        } catch (Exception e) {

            tfAddProductId.setEditable(true);

        }
    }

    @FXML
    private void tfAddNameProduct(KeyEvent event) {
        txtAdded.setText("");

        try {

            char c = event.getText().charAt(0);

            //isDigit permite sólo números, isAphabetic permite sólo letras
            if (!Character.isAlphabetic(c) && !Character.isSpaceChar(c)) {

                tfAddNameProduct.setEditable(false);

            } else {

                tfAddNameProduct.setEditable(true);
            }

        } catch (Exception e) {

            tfAddNameProduct.setEditable(true);

        }

    }

    @FXML
    private void tfAddPriceProduct(KeyEvent event) {
        txtAdded.setText("");

        try {

            char c = event.getText().charAt(0);

            if (Character.isDigit(c) || c == '.') {
                tfAddPriceProduct.setEditable(true);

            } else {

                tfAddPriceProduct.setEditable(false);
            }

        } catch (Exception e) {

            tfAddPriceProduct.setEditable(true);

        }
    }

}
