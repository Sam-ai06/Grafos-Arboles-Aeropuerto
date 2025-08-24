/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.espol.proyecto_estructuras_de_datos.Controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo_logica.Aeropuerto;
import modelo_logica.Graph_RedVuelos;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class CrearVueloConexionController {
    @FXML
    private Button btn_cancel, btn_conectar;
    @FXML
    private Label lbl_msg;
    @FXML
    private TextField txtField_numeroVuelo, txtField_OriginCode, txtField_DestinyCode;
    @FXML
    private TextField txtField_Aerolinea, txtField_distancia, txtField_duracion, txtField_costo;

    private Graph_RedVuelos grafo;

    private boolean esCampoVacio(TextField campo) {
        return campo.getText() == null || campo.getText().trim().isEmpty();
    }

    public boolean conectarAeropuertosVista() {
        try{
            if (esCampoVacio(txtField_Aerolinea) || esCampoVacio(txtField_DestinyCode) || esCampoVacio(txtField_OriginCode) ||
                    esCampoVacio(txtField_numeroVuelo) || esCampoVacio(txtField_distancia) || esCampoVacio(txtField_duracion)
                    || esCampoVacio(txtField_costo)){
                lbl_msg.setText("Error. Debe rellenar todos los campos.");
            }
            String numeroVuelo = txtField_numeroVuelo.getText().trim();
            String codigoOrigen = txtField_OriginCode.getText().trim();
            String codigoDestino = txtField_DestinyCode.getText().trim();
            String aerolinea = txtField_Aerolinea.getText().trim();

            int distancia = Integer.parseInt(txtField_distancia.getText().trim());
            int duracion = Integer.parseInt(txtField_duracion.getText().trim());
            int costo = Integer.parseInt(txtField_costo.getText().trim());

            Aeropuerto origen = grafo.findAirport(codigoOrigen);
            Aeropuerto destino = grafo.findAirport(codigoDestino);

            if (origen == null){
                lbl_msg.setText("no se encontró el puerto de origen");
            }
            else if (destino == null){
                lbl_msg.setText("no se encontró el puerto de destino");
            }

            boolean conexionCreada = grafo.crearConexion(origen, destino, aerolinea, distancia, duracion, costo, numeroVuelo);
            if (conexionCreada){
                lbl_msg.setText("conexion creada exitosamente");
                javafx.animation.Timeline timeline = new Timeline(new javafx.animation.KeyFrame(javafx.util.Duration.seconds(1.5),
                        e -> cerrar_ventana()));

                timeline.play();
            }

        }catch (Exception e){
            lbl_msg.setText("error crítico");
            e.printStackTrace();
        }
        return true; //placeholder
    }


    public void setGrafo(Graph_RedVuelos grafo){
        this.grafo = grafo;
    }


    @FXML
    private void cerrar_ventana() {
        Stage stage = (Stage) btn_cancel.getScene().getWindow();
        stage.close();
    }


    public Graph_RedVuelos getGrafo() {
        return grafo;
    }
}
