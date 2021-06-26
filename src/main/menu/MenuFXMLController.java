/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.menu;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class MenuFXMLController implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    private ImageView img9;
    @FXML
    private Circle circle4;
    @FXML
    private Button btnExit;
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
    @FXML
    private Button btnLogout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    
    @FXML
    private void managemenPlaces(MouseEvent event) {
    }

    @FXML
    private void managementSearch(MouseEvent event) {
    }

    @FXML
    private void managementRestSuper(MouseEvent event) {
    }

    @FXML
    private void managementProducts(MouseEvent event) {
    }

    @FXML
    private void managementFood(MouseEvent event) {
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
  

}
