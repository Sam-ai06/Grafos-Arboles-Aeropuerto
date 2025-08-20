package com.espol.proyecto_estructuras_de_datos.Controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Modality;
import modelo_logica.Aeropuerto;
import modelo_logica.Graph_RedVuelos;

public class VerAeropuertosController implements Initializable{
    @FXML
    private Button btn_salir, btn_editar, btn_eliminar, btn_trazar, btn_anadir, btn_conectarAero;
    @FXML
    private Pane espacio_grafo;
    public static Graph_RedVuelos grafo;
    //ya se implementó el método que va para salir
    //faltan los métodos para editar aeropuertos, eliminar aeropuertos(vertices), trazar rutas entre aeropuertos(dijkstra) y anadir aeropuertos
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //como es la vista principal al clicar en empezar se inicializa el grafo
            //comparador actual
        Comparator<Aeropuerto> cmp = (a1,a2)->{
            return a1.getCodigo().compareTo(a2.getCodigo());};
        grafo = new Graph_RedVuelos(cmp);
    }  

    //cambiar a la vista principal
    @FXML
    private void switchToMainView(ActionEvent event) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/espol/proyecto_estructuras_de_datos/MainView.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Proyecto | inicio");
            stage.show();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    
    @FXML
    private void agregarAeropuertoVista(ActionEvent event) {
        try {
            // Cargar el FXML de la ventana secundaria
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/espol/proyecto_estructuras_de_datos/AniadirAirportView.fxml"));
            Parent root = loader.load();

            // Crear un nuevo Stage (ventana)
            Stage stage = new Stage();
            stage.setTitle("Agregar Aeropuerto");
            stage.setScene(new Scene(root));

            // Opcional: hacerla modal para bloquear la ventana principal
            stage.initModality(Modality.APPLICATION_MODAL);

            // Mostrar la ventana y esperar a que se cierre
            stage.showAndWait();
            actualizarGrafo();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void actualizarGrafo() {
        espacio_grafo.getChildren().clear(); // borra dibujo anterior

        double centroX = espacio_grafo.getWidth() / 2;
        double centroY = espacio_grafo.getHeight() / 2;
        double radio = Math.min(centroX, centroY) - 50; // margen

        List<Aeropuerto> aeropuertos = grafo.getAeropuertos();
        if (aeropuertos.isEmpty()) return; // nada que dibujar

        // Nodo central (primer aeropuerto)
        Aeropuerto central = aeropuertos.get(0);
        Circle nodoCentral = new Circle(centroX, centroY, 20, Color.RED);
        // opcional: poner tooltip o texto con el nombre del aeropuerto
        Tooltip.install(nodoCentral, new Tooltip(central.getNombre()));
        espacio_grafo.getChildren().add(nodoCentral);

        // Aeropuertos secundarios
        int n = aeropuertos.size() - 1; // excluyendo el central
        for (int i = 1; i < aeropuertos.size(); i++) {
            double angle = 2 * Math.PI * (i - 1) / n; // distribuir uniformemente
            double x = centroX + radio * Math.cos(angle);
            double y = centroY + radio * Math.sin(angle);

            Circle nodo = new Circle(x, y, 15, Color.BLUE);
            Tooltip.install(nodo, new Tooltip(aeropuertos.get(i).getNombre()));
            espacio_grafo.getChildren().add(nodo);
        }
}


}
