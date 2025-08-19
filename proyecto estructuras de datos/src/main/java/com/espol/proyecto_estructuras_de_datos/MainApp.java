package com.espol.proyecto_estructuras_de_datos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Image icono = new Image(getClass().getResourceAsStream("/Images/graph.png"));
        stage.getIcons().add(icono);
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("MainView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);

        Screen screen = Screen.getPrimary();
        Rectangle2D rectangle = screen.getBounds();

        stage.setTitle("Aeropuerto | Main menu");
        stage.setX(rectangle.getMinX());
        stage.setY(rectangle.getMinY());
        stage.setWidth(rectangle.getWidth());
        stage.setHeight(rectangle.getHeight());
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
}
