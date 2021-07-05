/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.food;

import data.FileManagementFoods;
import domain.Food;
import domain.bst.BST;
import domain.bst.TreeException;

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
import javafx.fxml.Initializable;
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

/**
 * FXML Controller class
 *
 * @author yeison
 */
public class FoodFXMLController implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ac;
    @FXML
    private Button btnAddFood;
    @FXML
    private Button btnDeleteFood;
    @FXML
    private Button btnModifyFood;
    @FXML
    private Button btnShowFood;
    @FXML
    private Button btnBack;
    @FXML
    private Pane panelAddFood;
    @FXML
    private TextField tfAddFoodId;
    @FXML
    private TextField tfAddNameFood;
    @FXML
    private Button btnAdd;
    @FXML
    private TextField tfAddPriceFood;
    @FXML
    private ComboBox<String> comboBoxFoodAdd;
    @FXML
    private Button btnCancel;
    @FXML
    private Text txtTitle;
    @FXML
    private Text txtAdded;
    @FXML
    private Pane panelDeleteFood;
    @FXML
    private TextField tfRemoveIdFood;
    @FXML
    private Button btnDelete;
    @FXML
    private Text txtError;
    @FXML
    private TableView<List<String>> foodTable;
    @FXML
    private TableColumn<List<String>, String> colIdFood;
    @FXML
    private TableColumn<List<String>, String> colNameFood;
    @FXML
    private TableColumn<List<String>, String> colPriceFood;
    @FXML
    private TableColumn<List<String>, String> colRestaurantFood;
    @FXML
    private Pane panelModifyFood;
    @FXML
    private TextField tfNameFoodModify;
    @FXML
    private TextField tfPriceFoodModify;
    @FXML
    private Button btnUpdate;
    @FXML
    private ComboBox<String> comboBoxFoodModify;
    @FXML
    private Button btnCancelModify;
    @FXML
    private TextField tfSearchFoodUpdate;
    @FXML
    private Button btnSearchFoodUpdate;
    @FXML
    private Pane foodTablePane;

    private BST bstFood = util.Utility.getBstFood();
    private Food f = new Food();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bstFood = util.Utility.getBstFood();

        ObservableList tableContent2 = FXCollections.observableArrayList();
        tableContent2.add("1234");
        tableContent2.add("4321");
        tableContent2.add("67");

        colIdFood.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(0)); //To change body of generated methods, choose Tools | Templates.
            }
        });
        colNameFood.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(1)); //To change body of generated methods, choose Tools | Templates.
            }
        });
        colPriceFood.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(2)); //To change body of generated methods, choose Tools | Templates.
            }
        });
        colRestaurantFood.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(3)); //To change body of generated methods, choose Tools | Templates.
            }
        });
        comboBoxFoodAdd.setItems(tableContent2);
        comboBoxFoodModify.setItems(tableContent2);
        foodTable.setVisible(true);
        foodTablePane.setVisible(true);

        txtTitle.setText("Lista de comidas");
        initTable();

    }

    private void initTable() {
        ObservableList<List<String>> tableContent = FXCollections.observableArrayList();
        ArrayList<Object> bstList = new ArrayList<Object>();

        if (!bstFood.isEmpty()) {
            try {
                bstList = bstFood.preOrder2();

                for (int i = 0; i < bstList.size(); i++) {

                    Food f = (Food) bstList.get(i);
                    List<String> arrayList = new ArrayList<>();
                    //Agregamos todos los datos al arrayList
                    arrayList.add(f.getId() + "");
                    arrayList.add(f.getName());
                    arrayList.add(f.getPrice() + "");
                    arrayList.add(f.getRestaurantID() + "");

                    tableContent.add(arrayList);

                }
            } catch (TreeException ex) {
                Logger.getLogger(FoodFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        foodTable.setItems(tableContent);
    }

    @FXML
    private void btnAddFood(ActionEvent event) {
        ArrayList<Object> bstList = new ArrayList<Object>();
        cleanAll();
        try {

            if (bstFood.isEmpty()) {
                tfAddFoodId.setText("1");
            } else {
                bstList = bstFood.preOrder2();
                Food f = (Food) bstList.get(bstList.size() - 1);
                tfAddFoodId.setText(f.getId() + 1 + "");
            }
            panelAddFood.setVisible(true);
            txtTitle.setText("Agregar comida");
        } catch (TreeException ex) {
            Logger.getLogger(FoodFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnDeleteFood(ActionEvent event) {
        cleanAll();
        panelDeleteFood.setVisible(true);
        txtTitle.setText("Suprimir comida");

    }

    @FXML
    private void btnModifyFood(ActionEvent event) {
        cleanAll();
        panelModifyFood.setVisible(true);
        txtTitle.setText("Modificar comida");
    }

    @FXML
    private void btnShowFood(ActionEvent event) {
        cleanAll();
        initTable();
        foodTablePane.setVisible(true);
        foodTable.setVisible(true);
        txtTitle.setText("Lista de comidas");

    }

    @FXML
    private void btnBack(ActionEvent event) {
        bp.setVisible(false);
    }

    @FXML
    private void tfAddCourseId(KeyEvent event) {
    }

    @FXML
    private void tfAddNameCourse(KeyEvent event) {
    }

    @FXML
    private void btnAdd(ActionEvent event) {
        if (!(tfAddFoodId.textProperty().getValue().equals("") || tfAddNameFood.textProperty().getValue().equals("") || tfAddPriceFood.textProperty().getValue().equals("") || comboBoxFoodAdd.getValue() == null)) {
            txtError.setText("");
            ArrayList<Object> bstList = new ArrayList<Object>();
            try {
                if (bstFood.isEmpty() || !bstFood.contains(new Food(tfAddNameFood.textProperty().getValue(), 0, 0))) {
                    String idRestaurant = comboBoxFoodAdd.getValue();
                    //Si el usuario no selecciona nada, el combo devuelve null
                    String[] array = idRestaurant.split("-");
                    txtError.setText("");
                    //Agrega la comida al archivo 
                    FileManagementFoods.add(Integer.parseInt(tfAddFoodId.textProperty().getValue()), tfAddNameFood.textProperty().getValue(), Double.parseDouble(tfAddPriceFood.textProperty().getValue()), Integer.parseInt(array[0]), FileManagementFoods.getNameFileFood());
                    //Actualiza la lista para que salga las comidas nuevas
                    bstFood = util.Utility.getBstFood();
                    //Setter los textField
                    bstList = bstFood.preOrder2();
                    if (bstList == null) {
                        tfAddFoodId.setText("1");
                    } else {
                        Food f = (Food) bstList.get(bstList.size() - 1);
                        tfAddFoodId.setText(f.getId() + 1 + "");
                    };
                    tfAddNameFood.setText("");
                    tfAddPriceFood.setText("");
                    comboBoxFoodAdd.setValue("");
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
                Logger.getLogger(FoodFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            txtError.setText("Debe rellenar todos los recuadros");
            txtAdded.setText("");
        }
    } // btn add

    @FXML
    private void btnCancel(ActionEvent event) {
        txtAdded.setText("");
        txtError.setText("");
        tfAddNameFood.setText("");
        tfAddPriceFood.setText("");
        comboBoxFoodAdd.setValue("");
    }

    @FXML
    private void btnDelete(ActionEvent event) {
        if (!tfRemoveIdFood.textProperty().getValue().equals("")) {
            ArrayList<Object> bstList = new ArrayList<Object>();
            try {
                //Busca si el elemento está
                Food element = new Food(Integer.parseInt(tfRemoveIdFood.textProperty().getValue()), "", 0, 0);
                if (bstFood.contains(element)) {
                    bstFood.remove(element);
                    //Reescribe el archivo
                    FileManagementFoods.overwriteFoodFile(bstFood);
                    tfRemoveIdFood.setText("");
                    txtError.setText("");
                    txtAdded.setText("Comida eliminada correctamente");
                } else {
                    txtError.setText("Comida no encontrada");
                    txtAdded.setText("");
                    tfRemoveIdFood.setText("");
                }
            } catch (TreeException ex) {
                txtError.setText("No hay comidas agregadas ");
                txtAdded.setText("");
                tfRemoveIdFood.setText("");
            }
        } else {
            txtError.setText("Debe rellenar todos los recuadros");
            txtAdded.setText("");
        }
    }

    @FXML
    private void tfNameCourseModify(KeyEvent event) {
    }

    @FXML
    private void btnUpdate(ActionEvent event) throws TreeException {
        if (!tfNameFoodModify.textProperty().getValue().equals("") && !tfPriceFoodModify.textProperty().getValue().equals("")
                && !tfSearchFoodUpdate.textProperty().getValue().equals("")) {
            try {
                String idRestaurant = comboBoxFoodModify.getValue();
                String[] array = idRestaurant.split("-");
                txtError.setText("");
                Object newElement = new Food(Integer.parseInt(tfSearchFoodUpdate.textProperty().getValue()), tfNameFoodModify.textProperty().getValue(),
                        Double.parseDouble(tfPriceFoodModify.textProperty().getValue()), Integer.parseInt(array[0]));
                Object oldElement = new Food(Integer.parseInt(tfSearchFoodUpdate.textProperty().getValue()), "", 0, 0);

                if (bstFood.contains(oldElement)) {

                    bstFood.remove(oldElement);
                    bstFood.add(newElement);
                    FileManagementFoods.overwriteFoodFile(bstFood);
                    txtError.setText("");
                    tfNameFoodModify.setText("");
                    tfPriceFoodModify.setText("");
                    tfSearchFoodUpdate.setText("");
                    comboBoxFoodModify.setValue("");
                    btnUpdate.setDisable(true);
                    txtAdded.setText("Comida modificada correctamente");
                    tfNameFoodModify.setDisable(true);
                    tfPriceFoodModify.setDisable(true);
                    btnUpdate.setDisable(true);
                    btnCancelModify.setDisable(true);
                    tfSearchFoodUpdate.setDisable(false);
                    comboBoxFoodModify.setDisable(true);
                }
            } catch (TreeException ex) {

                txtError.setText("No hay comidas agregadas");
                txtAdded.setText("");

                tfNameFoodModify.setText("");
                tfPriceFoodModify.setText("");
                tfSearchFoodUpdate.setText("");
                comboBoxFoodModify.setValue("");
                btnUpdate.setDisable(true);

            } catch (NumberFormatException ex) {

                txtError.setText("El identificador de comida debe ser numérico");
            }
        } else {

            txtError.setText("Debe rellenar todos los recuadros");
        }
    }

    @FXML
    private void btnCancelModify(ActionEvent event) {
    }

    @FXML
    private void btnSearchFoodUpdate(ActionEvent event) {
        if (!tfSearchFoodUpdate.textProperty().getValue().equals("")) {

            try {
                ArrayList<Object> bstList = new ArrayList<Object>();

                bstFood = util.Utility.getBstFood();
                Food element = new Food(Integer.parseInt(tfSearchFoodUpdate.textProperty().getValue()), "", 0, 0);

                if (bstFood.contains(element)) {

                    bstList = bstFood.preOrder2();
                    for (int i = 0; i < bstList.size(); i++) {
                        Food f = (Food) bstList.get(i);
                        if (element.getId() == f.getId()) {

                            tfNameFoodModify.setText(f.getName());
                            tfPriceFoodModify.setText(f.getPrice() + "");
                            comboBoxFoodModify.setValue(f.getRestaurantID() + "");
                            txtError.setText("");
                            txtAdded.setText("");
                            btnUpdate.setDisable(false);
                            btnCancelModify.setDisable(false);
                            tfNameFoodModify.setDisable(false);
                            tfPriceFoodModify.setDisable(false);
                            comboBoxFoodModify.setDisable(false);
                            tfSearchFoodUpdate.setDisable(true);

                        }
                    }
                } else {

                    txtError.setText("Comida no encontrada");
                    txtAdded.setText("");
                    tfAddNameFood.setText("");
                    tfSearchFoodUpdate.setText("");
                    btnUpdate.setDisable(true);

                }

            } catch (TreeException ex) {

                txtError.setText("No hay comidas agregadas");
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

    private void cleanAll() {
        foodTable.setVisible(false);
        panelAddFood.setVisible(false);
        panelDeleteFood.setVisible(false);
        panelModifyFood.setVisible(false);
        foodTablePane.setVisible(false);
        tfAddFoodId.setText("");
        tfAddPriceFood.setText("");
        tfAddNameFood.setText("");
        tfRemoveIdFood.setText("");
        tfSearchFoodUpdate.setText("");
        txtAdded.setText("");
        txtTitle.setText("");
        txtError.setText("");
    }

    @FXML
    private void tfAddPriceFood(KeyEvent event) {
    }

    @FXML
    private void tfRemoveIdFood(KeyEvent event) {
        txtError.setText("");

        try {

            char c = event.getText().charAt(0);

            //isDigit permite sólo números, isAphabetic permite sólo letras
            if (!Character.isDigit(c)) {

                tfRemoveIdFood.setEditable(false);

            } else {

                tfRemoveIdFood.setEditable(true);
            }

        } catch (Exception e) {

            tfRemoveIdFood.setEditable(true);

        }
    }

    @FXML
    private void tfSearchFoodUpdate(KeyEvent event) {
        txtError.setText("");

        try {

            char c = event.getText().charAt(0);

            //isDigit permite sólo números, isAphabetic permite sólo letras
            if (!Character.isDigit(c)) {

                tfSearchFoodUpdate.setEditable(false);

            } else {

                tfSearchFoodUpdate.setEditable(true);
            }

        } catch (Exception e) {

            tfSearchFoodUpdate.setEditable(true);

        }
    }

}