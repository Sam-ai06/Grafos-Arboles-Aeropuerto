package com.espol.proyecto_estructuras_de_datos.Controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

public class MainViewController  {
    @FXML
    private Label welcomeText;
    @FXML
    private Button btn_verAeropuertos;
    
   

    //cambiar a la vista principal de manejo del aeropuerto
    @FXML
    protected void SwitchToViewAirports(ActionEvent event) {
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/espol/proyecto_estructuras_de_datos/VerAeropuertosView.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Proyecto | ver aeropuertos");
            stage.show();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }
}
