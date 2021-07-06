/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.menu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import main.security.SecurityFXMLController;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class MenuFXMLController implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    private Button btnExit;
    @FXML
    private ImageView img9;
    @FXML
    private Circle circle4;
    @FXML
    private Button btnLogout;
    @FXML
    private Circle circle2;
    @FXML
    private Circle circle3;
    @FXML
    private Circle circle1;
    @FXML
    private ImageView img10;
    @FXML
    private ImageView img8;
    @FXML
    private ImageView img2;
    @FXML
    private ImageView img3;
    @FXML
    private ImageView img5;
    @FXML
    private ImageView img6;
    @FXML
    private ImageView img1;
    @FXML
    private ImageView img4;
    @FXML
    private ImageView img7;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void managementSearch(MouseEvent event) {
    }

    @FXML
    private void managementRestSuper(MouseEvent event) {
    }

    @FXML
    private void managementProducts(MouseEvent event) {

        loadPage("/main/product/ProductFXML");

    }

    @FXML
    private void managementFood(MouseEvent event) {

        loadPage("/main/food/FoodFXML");

    }

    @FXML
    private void managemenPlaces(MouseEvent event) {

        loadPage("/main/places/PlacesFXML");
    }

    @FXML
    private void managementReports(MouseEvent event) {

    }

    @FXML
    private void btnExit(ActionEvent event) {
        Stage stage = (Stage) this.btnExit.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void btnLogout(ActionEvent event) {

        bp.setVisible(false);
    }

    @FXML
    private void img8(MouseEvent event) {

        managementSearch(event);
    }

    @FXML
    private void img2(MouseEvent event) {

        managementFood(event);
    }

    @FXML
    private void img3(MouseEvent event) {
        managementRestSuper(event);

    }

    @FXML
    private void img5(MouseEvent event) {
        managementRestSuper(event);

    }

    @FXML
    private void img6(MouseEvent event) {
        managementProducts(event);

    }

    @FXML
    private void img1(MouseEvent event) {
        managementFood(event);

    }

    @FXML
    private void img4(MouseEvent event) {
        managementRestSuper(event);

    }

    @FXML
    private void img7(MouseEvent event) {
        managementProducts(event);

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

}
