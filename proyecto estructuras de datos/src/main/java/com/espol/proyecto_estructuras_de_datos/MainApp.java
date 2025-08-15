package com.espol.proyecto_estructuras_de_datos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Image icono = new Image(getClass().getResourceAsStream("/Images/graph.png"));
        stage.getIcons().add(icono);
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("MainView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("Aeropuerto | Main menu");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
}
