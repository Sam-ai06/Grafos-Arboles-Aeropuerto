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
import modelo_logica.Aeropuerto;

public class EditarAeropuertoController implements Initializable {
    @FXML
    private Button btn_cancelar, btn_guardar;
    @FXML
    private Label lbl_msg;
    @FXML
    private ComboBox<String> combo_Opciones;
    @FXML
    private TextField txt_relleno, txt_codigo;

    private Graph_RedVuelos grafo;

    public Graph_RedVuelos getGrafo() {
        return grafo;
    }

    public void setGrafo(Graph_RedVuelos grafo) {
        this.grafo = grafo;
    }

    @FXML
    public void cancelar(){
        Stage stage = (Stage) btn_cancelar.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void guardarCambios(){
        String opcionSeleccionada = combo_Opciones.getValue();
        //encontramos el aeropuerto a editar
        //validar si el aeropuerto es null
        Aeropuerto actual = grafo.findAirport(txt_codigo.getText().trim());
        String dato_cambio = txt_relleno.getText().trim();
        switch (opcionSeleccionada) {
            case "Nombre del aeropuerto":
                actual.setNombre(dato_cambio);
                break;
            case "Codigo IATA":
                actual.setCodigo(dato_cambio);
                break;
            case "Ciudad":
                actual.setCiudad(dato_cambio);
                break;
            case "Pais":
                actual.setPais(dato_cambio);
                break;
            default:
                //no se que hacer aquí , nunca uso switch
                break;
        }
        Stage stage = (Stage) btn_guardar.getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        InitializeComboBox();
    }
    //inicializar el comboBox
    private void InitializeComboBox(){
        combo_Opciones.getItems().addAll("Nombre del aeropuerto", "Codigo IATA", "Ciudad", "Pais");
        combo_Opciones.setPromptText("Elige una opción"); // valor por defecto
    }
}
