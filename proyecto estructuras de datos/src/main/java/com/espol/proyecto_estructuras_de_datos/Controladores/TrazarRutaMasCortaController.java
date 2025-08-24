package com.espol.proyecto_estructuras_de_datos.Controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo_logica.Graph_RedVuelos;

public class TrazarRutaMasCortaController {
    @FXML
    private Button btn_trazar, btn_cancelar;
    @FXML
    private TextField txt_OriginPort, txt_DestinationPort;
    @FXML
    private Label lbl_msg;
    @FXML
    private ComboBox<String> ComboBox_Opciones;
    private Graph_RedVuelos grafo;

    //inicializar el comboBox con costo, distancia y tiempo
    @FXML
    private void initializeComboBox(){
        ComboBox_Opciones.getItems().addAll("Costo", "Distancia", "Tiempo");
        ComboBox_Opciones.setValue("Elige una opción"); // valor por defecto
    }

    @FXML
    public void cancelar(){
        Stage stage = (Stage) btn_cancelar.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void trazarRuta(){
       /* try{
            //logica necesaria para trazar la ruta más corta
            //usar dijkstra.
            //pensaba en un switch case para las opciones del comboBox, eso depende de como sergio quiera implementarlo
        }*/

    }

    public Graph_RedVuelos getGrafo() {
        return grafo;
    }

    public void setGrafo(Graph_RedVuelos grafo) {
        this.grafo = grafo;
    }
}
