package com.espol.proyecto_estructuras_de_datos.Controladores;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import modelo_logica.Graph_RedVuelos;

public class EliminarVueloController {
    @FXML
    private Button btn_eliminar,btn_cancelar;
    @FXML
    private TextField txt_eliminar;
    @FXML
    private Label lbl_msg;

    private Graph_RedVuelos grafo;

    public Graph_RedVuelos getGrafo() {
        return grafo;
    }

    public void setGrafo(Graph_RedVuelos grafo) {
        this.grafo = grafo;
    }

    @FXML
    private void cancelar(){
        Stage stage = (Stage) btn_cancelar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void eliminarVuelo(){
        try{
            if (txt_eliminar.getText() == null || txt_eliminar.getText().trim().isEmpty()){
                lbl_msg.setStyle("-fx-text-fill: red;");
                lbl_msg.setText("Error. Debe rellenar el campo.");
                return;
            }
            grafo.eliminarVuelo(txt_eliminar.getText().trim()); //metodo pendiente por implementar
            //color del label a verde
            lbl_msg.setStyle("-fx-text-fill: green;");
            lbl_msg.setText("Vuelo eliminado con éxito");
            // Esperar 1 segundo y luego cerrar
            Stage stage = (Stage) btn_eliminar.getScene().getWindow();
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> stage.close());
            delay.play();

        } catch (Exception e) {
            lbl_msg.setStyle("-fx-text-fill: red;");
            lbl_msg.setText("Error. No se pudo eliminar el vuelo.");
            throw new RuntimeException(e);
        }
    }
}
