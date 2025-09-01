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
import java.util.List;
import modelo_logica.Aeropuerto;
import modelo_logica.Vuelo;

public class TrazarRutaMasCortaController implements Initializable {
    @FXML
    private Button btn_trazar, btn_cancelar, btn_trazarDFS;
    @FXML
    private TextField txt_OriginPort, txt_DestinationPort;
    @FXML
    private Label lbl_msg;
    @FXML
    private ComboBox<String> ComboBox_Opciones;

    private Graph_RedVuelos grafo;
    // Referencia al controlador de estadísticas para comunicar la ruta calculada
    private EstadisticaController estadisticaController;

    public void initialize(URL location, ResourceBundle resources) {
        initializeComboBox();
        return;
    }

    //inicializar el comboBox con costo, distancia y tiempo
    public void initializeComboBox(){
        ComboBox_Opciones.getItems().addAll("Costo", "Distancia", "Tiempo");
        ComboBox_Opciones.setPromptText("Elige una opción"); // valor por defecto
    }

    @FXML
    public void cancelar(){
        Stage stage = (Stage) btn_cancelar.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void trazarRuta(){
        String opcionSeleccionada = ComboBox_Opciones.getValue();
        grafo.setSolicitaDijkstra(true);
        Aeropuerto origen = grafo.findAirport(txt_OriginPort.getText().trim());
        Aeropuerto destino = grafo.findAirport(txt_DestinationPort.getText().trim());
        switch (opcionSeleccionada) {
            case "Costo":
                lbl_msg.setText("Trazando ruta más corta por Costo...");
                grafo.setRuta_corta(grafo.rutacostoauxiliar(origen, destino));
                break;
            case "Distancia":
                lbl_msg.setText("Trazando ruta más corta por Distancia...");
                grafo.setRuta_corta(grafo.rutadistanciaauxiliar(origen, destino));
                break;
            case "Tiempo":
                lbl_msg.setText("Trazando ruta más corta por Tiempo...");
                grafo.setRuta_corta(grafo.rutaduracioncortaauxiliar(origen, destino));
                break;
            default:
                lbl_msg.setText("Por favor, seleccione una opción válida.");
                break;
        }

        if (estadisticaController != null) {
            estadisticaController.setRutaCalculada(origen, destino);
        }

        Stage stage = (Stage) btn_trazar.getScene().getWindow();
        stage.close();
    }

    public Graph_RedVuelos getGrafo() {
        return grafo;
    }

    public void setGrafo(Graph_RedVuelos grafo) {
        this.grafo = grafo;
    }

    public void setEstadisticaController(EstadisticaController estadisticaController) {
        this.estadisticaController = estadisticaController;
    }

    public void trazarDFS(){
        Aeropuerto origen = grafo.findAirport(txt_OriginPort.getText().trim());
        Aeropuerto destino = grafo.findAirport(txt_DestinationPort.getText().trim());

        grafo.mostrarDFS(origen, destino);
    }
}