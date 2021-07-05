/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.product;

import data.FileManagementProduct;
import domain.Product;
import domain.bst.BST;
import domain.bst.TreeException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
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
    private TextField tfAddName;
    @FXML
    private TextField tfRemoveProductId;
    @FXML
    private TextField tfIdProductUpdate;
    @FXML
    private TextField tfNameUpdate;
    @FXML
    private TableView<List<String>> tableProduct;
    @FXML
    private TableColumn<Product, Integer> colIdProduct;
    @FXML
    private TableColumn<Product, String> colNameProduct;
    @FXML
    private TableColumn<Product, Double> colPriceProduct;
    @FXML
    private TableColumn<Product, Integer> colIdSuperProduct;
    @FXML
    private TextField tfSearchProductUpdate;
    @FXML
    private Text txtProductSearchUpdate;
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
    private TextField tfAddPrecio;
    @FXML
    private TextField tfPriceUpdate;
    private TextField tfIdSupermarketUpdate;
    @FXML
    private ComboBox<String> cbIdSupermarketUpdate;
    @FXML
    private ComboBox<String> cbIdSupermarket;
//    private AdjacencyListGraph studentList = util.Utility.getRestYSuperGraph();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.cbIdSupermarket.getItems().add("1");
        this.cbIdSupermarket.getItems().add("2");
        this.cbIdSupermarket.getItems().add("3");
        ;
    }

    @FXML
    private void btnBack(ActionEvent event) {
        bp.setVisible(false);
    }

    @FXML
    private void btnAdd(ActionEvent event) throws TreeException {
        if (!(tfAddProductId.textProperty().getValue().equals("") || tfAddName.textProperty().getValue().equals("") || tfAddPrecio.textProperty().getValue().equals("") || cbIdSupermarket.getValue() == null)) {
            txtError.setText("");
            ArrayList<Object> bstList = new ArrayList<Object>();

            try {

                if (bstProduct.isEmpty() || !bstProduct.contains(new Product(0, tfAddName.textProperty().getValue(), 0, 0))) {
                    String idSuperMarket = cbIdSupermarket.getValue();
                    //Si el usuario no selecciona nada, el combo devuelve null

                    String[] array = idSuperMarket.split("-");
                    txtError.setText("");
                    //Agrega la carrera al archivo 
                    FileManagementProduct.add(Integer.parseInt(tfAddProductId.textProperty().getValue()), tfAddName.textProperty().getValue(), Double.parseDouble(tfAddPrecio.textProperty().getValue()), Integer.parseInt(array[0]), FileManagementProduct.getNameFileProduct());
                    //Actualiza la lista para que salga la carrera nueva
                    bstProduct = util.Utility.getBstProduct();
                    //Setter los textField

                    bstList = bstProduct.preOrder2();
                    if (bstList == null) {
                        tfAddProductId.setText("1");
                    } else {
                        Product f = (Product) bstList.get(bstList.size() - 1);
                        tfAddProductId.setText(f.getAutoId() + 1 + "");
                    };
                    tfAddName.setText("");
                    tfAddPrecio.setText("");
                    cbIdSupermarket.setValue("");
                    //Le dice al usuario que se agrego
                    txtAdded.setText("Agregado con éxito");
                } else {

                    //Si existe lanza este mensaje al usuario
                    txtError.setText("La nombre de la comida ya está en uso");
                    txtAdded.setText("");

                }

            } catch (NumberFormatException e) {

                txtError.setText("El precio debe ser numérico");
                txtAdded.setText("");

            } catch (TreeException ex) {
                Logger.getLogger(ProductFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {

            txtError.setText("Debe rellenar todos los recuadros");
            txtAdded.setText("");

        }
    } // Add

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
                    txtError.setText("Producto no encontrada");
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
                String idRestaurant = cbIdSupermarketUpdate.getValue();
                String[] array = idRestaurant.split("-");
                txtError.setText("");
                Object newElement = new Product(Integer.parseInt(tfSearchProductUpdate.textProperty().getValue()), tfNameUpdate.textProperty().getValue(),
                Double.parseDouble(tfPriceUpdate.textProperty().getValue()), Integer.parseInt(array[0]));
                Object oldElement = new Product(Integer.parseInt(tfSearchProductUpdate.textProperty().getValue()), "", 0, 0);
                
                if (bstProduct.contains(oldElement)) {
                    bstProduct.remove(oldElement);
                    bstProduct.add(newElement);
                    FileManagementProduct.overwriteProductFile(bstProduct);
                    txtError.setText("");
                    tfNameUpdate.setText("");
                    tfPriceUpdate.setText("");
                    tfSearchProductUpdate.setText("");
                    cbIdSupermarketUpdate.setValue("");
                    btnUpdate.setDisable(true);
                    txtAdded.setText("Carrera modificada correctamente");
                    tfNameUpdate.setDisable(true);
                    tfPriceUpdate.setDisable(true);
                    btnUpdate.setDisable(true);
                    btnUpdateCancel.setDisable(true);
                    tfSearchProductUpdate.setDisable(false);
                }
            } catch (NumberFormatException ex) {
                txtError.setText("El código de carrera debe ser numérico");
            }
        } else {
            txtError.setText("Debe rellenar todos los recuadros");
        }
    }

    private void cleanAll() {
        panelAddProduct.setVisible(false);
        panelModificar.setVisible(false);
        panelDeleteProduct.setVisible(false);
        tableProduct.setVisible(false);
        tfSearchProductUpdate.setVisible(false);
        btnSearchProductUpdate.setVisible(false);
        txtProductSearchUpdate.setVisible(false);
        showProductPane.setVisible(false);
        tfRemoveProductId.setText("");
        tfAddName.setText("");
        tfIdProductUpdate.setText("");
        tfNameUpdate.setText("");
        tfIdProductUpdate.setDisable(true);
        tfNameUpdate.setDisable(true);
        btnUpdate.setDisable(true);
        tfSearchProductUpdate.setDisable(false);
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

    @FXML
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

    private void tfIdProductUpdate(KeyEvent event) {

        txtAdded.setText("");

        try {

            char c = event.getText().charAt(0);

            if (!Character.isDigit(c)) {

                tfIdProductUpdate.setEditable(false);

            } else {

                tfIdProductUpdate.setEditable(true);
            }

        } catch (Exception e) {

            tfIdProductUpdate.setEditable(true);

        }
    }

    @FXML
    private void btnUpdateCancel(ActionEvent event) {

        txtError.setText("");
        txtAdded.setText("");
        tfIdProductUpdate.setText("");
        tfNameUpdate.setText("");
        tfSearchProductUpdate.setText("");
        tfSearchProductUpdate.setDisable(false);
        tfIdProductUpdate.setDisable(true);
        tfNameUpdate.setDisable(true);
        btnUpdate.setDisable(true);
        btnUpdateCancel.setDisable(true);
    }

    @FXML
    private void btnAddProduct(ActionEvent event) {
        cleanAll();
        panelAddProduct.setVisible(true);
        txtTitle.setText("Agregar producto");
        tfAddProductId.setText(Product.getAutoId() + "");
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
        txtProductSearchUpdate.setVisible(true);
        txtTitle.setText("Modificar producto");
    }

    @FXML
    private void btnShowProduct(ActionEvent event) throws TreeException {
        cleanAll();
        initTable();
        tableProduct.setVisible(true);
    }

    @FXML
    private void btnSearchProductUpdate(ActionEvent event) throws TreeException {
        if (!tfSearchProductUpdate.textProperty().getValue().equals("")) {

            ArrayList<Object> bstList = new ArrayList<Object>();
            bstProduct = util.Utility.getBstProduct();
            Product element = new Product(Integer.parseInt(tfSearchProductUpdate.textProperty().getValue()), "", 0, 0);
            if (bstProduct.contains(element)) {
                bstList = bstProduct.preOrder2();
                for (int i = 0; i < bstList.size(); i++) {
                    Product f = (Product) bstList.get(i);
                    if (element.getAutoId()==f.getAutoId()) {
                        
                        tfNameUpdate.setText(f.getName());
                        tfPriceUpdate.setText(f.getPrice() + "");
                        cbIdSupermarketUpdate.setValue(f.getSupermarketID() + "");
                        txtError.setText("");
                        txtAdded.setText("");
                        tfSearchProductUpdate.setDisable(true);
                        btnUpdate.setDisable(false);
                        btnUpdateCancel.setDisable(false);
                        tfNameUpdate.setDisable(false);
                        tfPriceUpdate.setDisable(false);
                        cbIdSupermarketUpdate.setDisable(false);
                        tfSearchProductUpdate.setDisable(false);
                    }
                }
            } else {
                txtError.setText("Producto no encontrada");
                txtAdded.setText("");
                tfAddName.setText("");
                tfSearchProductUpdate.setText("");
                btnUpdate.setDisable(true);
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
                    Product f = (Product) bstList.get(i);
                    List<String> arrayList = new ArrayList<>();
                    //Agregamos todos los datos al arrayList
                    arrayList.add(f.getAutoId() + "");
                    arrayList.add(f.getName());
                    arrayList.add(f.getPrice() + "");
                    arrayList.add(f.getSupermarketID() + "");
                    tableContent.add(arrayList);
                }
            } catch (TreeException ex) {
                Logger.getLogger(ProductFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        tableProduct.setItems(tableContent);
    } // Init

} // Fin de clase
