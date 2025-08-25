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
import modelo_logica.Vuelo;

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
        String opcionSeleccionada = combo_Opciones.getValue();
        //encontramos el aeropuerto a editar
        Vuelo vuelo_actual = grafo.findVuelo(txt_codigo.getText().trim());
        //validar si el vuelo es null
        String dato_cambio = txt_relleno.getText().trim();
        switch (opcionSeleccionada) {
            case "cambiar costo":
                //validar que sea un numero en el caso de costo¿? o no se puede validar
                vuelo_actual.setCosto(Double.parseDouble(dato_cambio));
                break;
            case "cambiar distancia":
                vuelo_actual.setDistancia(Double.parseDouble(dato_cambio));
                break;
            case "cambiar tiempo":
                vuelo_actual.setDuracion(Double.parseDouble(dato_cambio));
                break;
            case "cambiar origen":
                //validar que el aeropuerto existe¿? existe un metodo llamado airportisingrafo(recien me acuerdo)
                Aeropuerto new_origin = grafo.findAirport(dato_cambio);
                vuelo_actual.setOrigen(new_origin);
                break;
            case "cambiar destino":
                //porque aquí puro spanglish
                Aeropuerto new_destino = grafo.findAirport(dato_cambio);
                vuelo_actual.setDestino(new_destino);
                break;
            default:
                //no se que hacer aquí (supongo que se valida si no ingreso opcion)
                break;
        }
        Stage stage = (Stage) btn_guardar.getScene().getWindow();
        stage.close();
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
