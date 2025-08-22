/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.espol.proyecto_estructuras_de_datos.Controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo_logica.Aeropuerto;
import modelo_logica.Graph_RedVuelos;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class EliminarAirportController  {

    @FXML
    private TextField codigo_para_eliminar;
    @FXML
    private Button btn_enviar;
    @FXML
    private Button btn_cancelar;
    private Graph_RedVuelos grafo;

    @FXML
    private void cerrarVentana(ActionEvent event) {
        // Obtiene la ventana actual a partir de cualquier control de esa ventana
        Stage stage = (Stage) btn_cancelar.getScene().getWindow();
        stage.close(); // cierra la ventana
    }

    @FXML
    private void eliminarAiportVista(ActionEvent event) {
        Aeropuerto airport_finded = grafo.findAirport(codigo_para_eliminar.getText());
        grafo.eliminaraeropuerto(airport_finded);
        Stage stage = (Stage) btn_enviar.getScene().getWindow();
        stage.close(); // cierra la ventana
    }

    public Graph_RedVuelos getGrafo() {
        return grafo;
    }

    public void setGrafo(Graph_RedVuelos grafo) {
        this.grafo = grafo;
    }
    
}
