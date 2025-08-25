package com.espol.proyecto_estructuras_de_datos.Controladores;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo_logica.Graph_RedVuelos;

import java.net.URL;
import java.util.ResourceBundle;

public class EditarVueloController implements Initializable {
    @FXML
    private Button btn_cancelar, btn_guardar;
    @FXML
    private Label lbl_msg;
    @FXML
    private ComboBox<String> combo_Opciones;
    @FXML
    private TextField txt_codigo, txt_relleno;


    private Graph_RedVuelos grafo;

    public Graph_RedVuelos getGrafo() {
        return grafo;
    }

    public void setGrafo(Graph_RedVuelos grafo) {
        this.grafo = grafo;
    }

    @FXML
    public void cancel(){
        Stage stage = (Stage) btn_cancelar.getScene().getWindow();
        stage.close();

    }

    @FXML
    public void guardarCambios(){

    }
    //inicializar el comboBox
    private void InitializeComboBox(){
        combo_Opciones.getItems().addAll("cambiar costo", "cambiar distancia", "cambiar tiempo", "cambiar origen", "cambiar destino");
        combo_Opciones.setPromptText("Elige una opción"); // valor por defecto
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        InitializeComboBox();

    }
}
