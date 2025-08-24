/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.espol.proyecto_estructuras_de_datos.Controladores;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
public class AniadirAirportViewController {

   
    @FXML
    private Button btncancelar, btnGuardar;
    @FXML
    private TextField text_name;
    @FXML
    private TextField text_codigo;
    @FXML
    private TextField text_pais;
    @FXML
    private TextField text_ciudad;
    @FXML
    private Label label_msg;
    
    private Graph_RedVuelos grafo_general;

    private boolean esCampoVacio(TextField campo) {
        return campo.getText() == null || campo.getText().trim().isEmpty();
    }
    //usado varias veces (para las validaciones de los campos que lo requieran)

    public Graph_RedVuelos getGrafo_general() {
        return grafo_general;
    }

    public void setGrafo_general(Graph_RedVuelos grafo_general) {
        this.grafo_general = grafo_general;
    }
   
    @FXML
    private void crearAeropuerto(ActionEvent event) {
        if (esCampoVacio(text_ciudad) || esCampoVacio(text_codigo) || esCampoVacio(text_pais) || esCampoVacio(text_name)) {
            label_msg.setText("Error. Debe rellenar todos los campos");
        }
        else{
            //luego de crear el aeropuerto
            String name = text_name.getText();
            String codigo = text_codigo.getText();
            String ciudad = text_ciudad.getText();
            String pais = text_pais.getText();
            Aeropuerto airport = new Aeropuerto(name,codigo,ciudad,pais);
            grafo_general.agregarAeropuerto(airport);//se creo de manera logica
            //hay que guradrlo en archivo
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/Persistencia_Archivos/aeropuertos.txt",true))) {
                writer.write(name+","+codigo+","+ciudad+","+pais+"\n");
            } catch (IOException e) {
                System.err.println("Error escribiendo archivo: " + e.getMessage());
            }
            Stage stage = (Stage) btnGuardar.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void cerrarVentana(ActionEvent event) {
        // Obtiene la ventana actual a partir de cualquier control de esa ventana
        Stage stage = (Stage) btncancelar.getScene().getWindow();
        stage.close(); // cierra la ventana
    }
    
}
