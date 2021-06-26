/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.security;

import data.FileManagementUsers;
import domain.Security;
import domain.list.CircularLinkedList;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class SecurityFXMLController implements Initializable {

    @FXML
    private AnchorPane paneLoginAdmin;
    @FXML
    private AnchorPane paneLoginUsers;
    @FXML
    private MenuItem opcAdmin;
    @FXML
    private MenuItem opcUsers;
    @FXML
    private Button btnExit;
    @FXML
    private Pane paneImg1;
    @FXML
    private Pane pneImg2;
    @FXML
    private ImageView imgLogoP1;
    @FXML
    private ImageView imgLogo2P1;
    @FXML
    private ImageView imgLogoP2;
    @FXML
    private ImageView imgLogo2P2;
    @FXML
    private TextField tfUserAdmin;
    @FXML
    private TextField tfPasswordAdmin;
    @FXML
    private Button btnLoginAdmin;
    @FXML
    private PasswordField pfPasswordAdmin;
    @FXML
    private Button btnSeePasswordAdmin;
    @FXML
    private TextField tfUser;
    @FXML
    private TextField tfPasswordUser;
    @FXML
    private Button btnLoginUser;
    @FXML
    private PasswordField pfPasswordUser;
    @FXML
    private Button btnSeePasswordUser;
    @FXML
    private BorderPane bp;
    @FXML
    private Text txtErrorLoginAdmin;
    @FXML
    private Text txtErrorLoginUser;

    private CircularLinkedList infoUsers = new CircularLinkedList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        imgLogoP1.setImage(new Image("/Images/restaurant.png"));
        imgLogo2P1.setImage(new Image("/Images/carrito-de-compras.png"));
        imgLogoP2.setImage(new Image("/Images/restaurant.png"));
        imgLogo2P2.setImage(new Image("/Images/carrito-de-compras.png"));

    }

    private void makeTransition(int number) {

        switch (number) {
            case 1: {

                TranslateTransition slide = new TranslateTransition(Duration.seconds(0.5));
                slide.setNode(paneLoginUsers);
                slide.setToX(-356);
                slide.play();
                paneLoginUsers.setTranslateX(200);
                TranslateTransition slide2 = new TranslateTransition(Duration.seconds(0.5));
                slide2.setNode(paneLoginAdmin);
                slide2.setToX(-356);
                slide2.play();
                paneLoginAdmin.setTranslateX(200);

                TranslateTransition slide3 = new TranslateTransition(Duration.seconds(0.5));
                slide3.setNode(paneImg1);
                slide3.setToX(-356);
                slide3.play();

                TranslateTransition slide4 = new TranslateTransition(Duration.seconds(0.5));
                slide4.setNode(pneImg2);
                slide4.setToX(0);
                slide4.play();

                paneLoginUsers.setVisible(false);
                paneLoginAdmin.setVisible(true);
                break;
            }
            case 2: {

                TranslateTransition slide = new TranslateTransition(Duration.seconds(0.5));
                slide.setNode(paneLoginUsers);
                slide.setToX(0);
                slide.play();
                paneLoginUsers.setTranslateX(0);

                TranslateTransition slide2 = new TranslateTransition(Duration.seconds(0.5));
                slide2.setNode(paneLoginAdmin);
                slide2.setToX(0);
                slide2.play();
                paneLoginAdmin.setTranslateX(200);

                TranslateTransition slide3 = new TranslateTransition(Duration.seconds(0.5));
                slide3.setNode(pneImg2);
                slide3.setToX(300);
                slide3.play();

                TranslateTransition slide4 = new TranslateTransition(Duration.seconds(0.5));
                slide4.setNode(paneImg1);
                slide4.setToX(0);
                slide4.play();

                paneLoginAdmin.setVisible(false);
                paneLoginUsers.setVisible(true);

                break;
            }

            default:
                break;
        }

    }

    @FXML
    private void opcAdmin(ActionEvent event) {
        cleanAll();
        makeTransition(1);
    }

    @FXML
    private void opcUsers(ActionEvent event) {
        cleanAll();
        makeTransition(2);

    }

    @FXML
    private void btnExit(ActionEvent event) {
        Stage stage = (Stage) this.btnExit.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void btnLoginAdmin(ActionEvent event) {

        if (paneLoginAdmin.isVisible()) {

            if (!(tfUserAdmin.textProperty().getValue().equals("")
                    || tfPasswordAdmin.textProperty().getValue().equals("")) && tfPasswordAdmin.isVisible()) {

                infoUsers = FileManagementUsers.getDataLogin(FileManagementUsers.getNameFileAdmin());

                Security s = new Security(tfUserAdmin.textProperty().getValue(), tfPasswordAdmin.textProperty().getValue());

                if (s.getAccess(null, infoUsers).equals("ADMIN")) {

                    //Aquí se carga el menu principal con acceso total
                    tfUserAdmin.setText("");
                    tfPasswordAdmin.setText("");
                    tfPasswordUser.setText("");
                    tfUser.setText("");
                    pfPasswordAdmin.setText("");
                    pfPasswordUser.setText("");
                    loadPage("/main/menu/MenuFXML");
                } else {

                    txtErrorLoginAdmin.setVisible(true);
                    txtErrorLoginAdmin.setText("Usuario y/o contraseña incorrectos");
                }
            } else if (!(tfUserAdmin.textProperty().getValue().equals("")
                    || pfPasswordAdmin.textProperty().getValue().equals("")) && pfPasswordAdmin.isVisible()) {

                infoUsers = FileManagementUsers.getDataLogin(FileManagementUsers.getNameFileAdmin());
                Security s = new Security(tfUserAdmin.textProperty().getValue(), pfPasswordAdmin.textProperty().getValue());

                if (s.getAccess(null, infoUsers).equals("ADMIN")) {

                    //Aquí se carga el menu principal con acceso total
                    tfUserAdmin.setText("");
                    tfPasswordAdmin.setText("");
                    tfPasswordUser.setText("");
                    tfUser.setText("");
                    pfPasswordAdmin.setText("");
                    pfPasswordUser.setText("");
                    loadPage("/main/menu/MenuFXML");
                    
                } else {

                    txtErrorLoginAdmin.setVisible(true);
                    txtErrorLoginAdmin.setText("Usuario y/o contraseña incorrectos");
                }
            } else {

                txtErrorLoginAdmin.setVisible(true);
                txtErrorLoginAdmin.setText("Debe rellenar todos los recuadros");
            }

        }
    }

    private boolean showPassword;

    @FXML
    private void btnSeePasswordAdmin(ActionEvent event) {
        if (showPassword) {

            tfPasswordAdmin.setVisible(false);
            pfPasswordAdmin.setVisible(true);
            pfPasswordAdmin.setText(tfPasswordAdmin.getText());
            showPassword = false;
        } else {

            tfPasswordAdmin.setVisible(true);
            pfPasswordAdmin.setVisible(false);
            tfPasswordAdmin.setText(pfPasswordAdmin.getText());
            showPassword = true;
        }
    }

    @FXML
    private void btnLoginUser(ActionEvent event) {
        if (paneLoginUsers.isVisible()) {
            if (!(tfUser.textProperty().getValue().equals("")
                    || tfPasswordUser.textProperty().getValue().equals("")) && tfPasswordUser.isVisible()) {

                infoUsers = FileManagementUsers.getDataLogin(FileManagementUsers.getNameFileStudents());
                Security s = new Security(tfUser.textProperty().getValue(), tfPasswordUser.textProperty().getValue());

                if (s.getAccess(infoUsers, null).equals("STUDENT")) {

                    tfUserAdmin.setText("");
                    tfPasswordAdmin.setText("");
                    tfPasswordUser.setText("");
                    tfUser.setText("");
                    pfPasswordAdmin.setText("");
                    pfPasswordUser.setText("");
                    loadPage("/main/reports/ReportsFXML");

                } else {

                    txtErrorLoginUser.setVisible(true);
                    txtErrorLoginUser.setText("Usuario y/o contraseña incorrectos");
                }

            } else if (!(tfUser.textProperty().getValue().equals("")
                    || pfPasswordUser.textProperty().getValue().equals("")) && pfPasswordUser.isVisible()) {

                infoUsers = FileManagementUsers.getDataLogin(FileManagementUsers.getNameFileStudents());
                Security s = new Security(tfUser.textProperty().getValue(), pfPasswordUser.textProperty().getValue());

                if (s.getAccess(infoUsers, null).equals("STUDENT")) {
                    tfUserAdmin.setText("");
                    tfPasswordAdmin.setText("");
                    tfPasswordUser.setText("");
                    tfUser.setText("");
                    pfPasswordAdmin.setText("");
                    pfPasswordUser.setText("");
               
                    loadPage("/main/reports/ReportsFXML");

                } else {

                    txtErrorLoginUser.setVisible(true);
                    txtErrorLoginUser.setText("Usuario y/o contraseña incorrectos");
                }
            } else {

                txtErrorLoginUser.setVisible(true);
                txtErrorLoginUser.setText("Debe rellenar todos los recuadros");
            }

        }

    }

    @FXML
    private void btnSeePasswordUser(ActionEvent event) {

        if (showPassword) {

            tfPasswordUser.setVisible(false);
            pfPasswordUser.setVisible(true);
            pfPasswordUser.setText(tfPasswordUser.getText());
            showPassword = false;
        } else {

            tfPasswordUser.setVisible(true);
            tfPasswordUser.setText(pfPasswordUser.getText());
            pfPasswordUser.setVisible(false);
            showPassword = true;
        }

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
    private void tfUserAdmin(KeyEvent event) {
        txtErrorLoginAdmin.setText("");
        txtErrorLoginUser.setText("");
    }

    @FXML
    private void tfUser(KeyEvent event) {

        txtErrorLoginAdmin.setText("");
        txtErrorLoginUser.setText("");
    }

    private void cleanAll() {
        tfUserAdmin.setText("");
        tfPasswordAdmin.setText("");
        tfPasswordUser.setText("");
        tfUser.setText("");
        pfPasswordAdmin.setText("");
        pfPasswordUser.setText("");

    }
}
