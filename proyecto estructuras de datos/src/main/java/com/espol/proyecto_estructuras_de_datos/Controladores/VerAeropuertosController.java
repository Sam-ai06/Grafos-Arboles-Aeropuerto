package com.espol.proyecto_estructuras_de_datos.Controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class VerAeropuertosController {
    @FXML
    private Button btn_salir, btn_editar, btn_eliminar, btn_trazar, btn_anadir, btn_conectarAero;
    //ya se implementó el método que va para salir
    //faltan los métodos para editar aeropuertos, eliminar aeropuertos(vertices), trazar rutas entre aeropuertos(dijkstra) y anadir aeropuertos

    //cambiar a la vista principal
    @FXML
    private void switchToMainView(ActionEvent event) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/espol/proyecto_estructuras_de_datos/MainView.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Proyecto | inicio");
            stage.show();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }


}
