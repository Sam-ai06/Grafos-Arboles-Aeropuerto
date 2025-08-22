package com.espol.proyecto_estructuras_de_datos.Controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import javafx.fxml.Initializable;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Modality;
import modelo_logica.Aeropuerto;
import modelo_logica.Graph_RedVuelos;
import modelo_logica.Vuelo;

public class VerAeropuertosController implements Initializable{
    @FXML
    private Button btn_salir, btn_editar, btn_eliminar, btn_trazar, btn_anadir, btn_conectarAero;
    @FXML
    private Pane espacio_grafo;
    public Graph_RedVuelos grafo;
    //ya se implementó el método que va para salir
    //faltan los métodos para editar aeropuertos, eliminar aeropuertos(vertices), trazar rutas entre aeropuertos(dijkstra) y anadir aeropuertos


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
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //como es la vista principal al clicar en empezar se inicializa el grafo
            //comparador actual
            //el código es unico por eso comparo códigos
        Comparator<Aeropuerto> cmp = (a1,a2)->{
            return a1.getCodigo().compareTo(a2.getCodigo());};
        grafo = new Graph_RedVuelos(cmp);
        //metodos de inicialización del panel
        configurarPanel();
    }
    //dibujar aristas - pendiente


    //propios de la clase no deben salir de aquí
    private void configurarPanel(){
        espacio_grafo.setStyle("-fx-background-color: #f8f9fa; -fx-border-color: #dee2e6; -fx-border-width: 2; -fx-border-radius: 10;");
    }

    @FXML
    private void agregarAeropuertoVista(ActionEvent event) {
        try {
            // Cargar el FXML de la ventana secundaria
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/espol/proyecto_estructuras_de_datos/AniadirAirportView.fxml"));
            Parent root = loader.load();
            // PASO CLAVE: obtener el controlador de la nueva ventana
            AniadirAirportViewController controller = loader.getController();
            controller.setGrafo_general(grafo); // ingresamos nuestro grafo
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
            System.out.println("Error al agregarAeropuerto desde la vista");
        }
    }
    
    public void actualizarGrafo() {
        espacio_grafo.getChildren().clear(); // borra dibujo anterior

        double centroX = espacio_grafo.getWidth() / 2;
        double centroY = espacio_grafo.getHeight() / 2;
        double radio = Math.min(centroX, centroY) - 50; // margen

        List<Aeropuerto> aeropuertos = grafo.getAeropuertos();
        if (aeropuertos.isEmpty()) return; // nada que dibujar

        Map<Aeropuerto, double[]> posiciones = new HashMap<>();

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

        crearArcos(posiciones);
    
    }

    private void crearArcos(Map<Aeropuerto,double[]> posiciones) {
        for (Aeropuerto Origin : grafo.getAeropuertos()){
            double[] posOrigin = posiciones.get(Origin);
            if (posOrigin == null){
                continue;
            }
            for (Vuelo vuelo : Origin.getVuelos()){
                Aeropuerto Destiny = vuelo.getDestino();
                double[] posDestiny = posiciones.get(Destiny);
                if (posDestiny == null){
                    continue;
                }

                //El arco en cuestión
                Line arco = new Line(posOrigin[0], posOrigin[1], posDestiny[0], posDestiny[1]);
                arco.setStroke(Color.BLACK);
                arco.setStrokeWidth(2);
                dibujarFlecha(arco, posOrigin, posDestiny);
            }
        }
    }

    private void dibujarFlecha(Line arco, double[] posOrigin, double[] posDestiny) {
        double distanciaX = posDestiny[0] - posOrigin[0];
        double distanciaY = posDestiny[1] - posOrigin[1];
        double largo = Math.sqrt(distanciaX * distanciaX + distanciaY * distanciaY);
        if (largo == 0){
            return;
        }
        distanciaX /= largo;
        distanciaY /= largo;

        double FlechaX =  posDestiny[0] - distanciaX * 15;
        double FlechaY = posDestiny[1] - distanciaY * 15;

        arco.setEndX(FlechaX);
        arco.setEndY(FlechaY);

        //punta de la flecha
        double ArrowLength = 10;
        double arrowAngle = Math.PI / 6;
        double x1 = FlechaX - ArrowLength * Math.cos(Math.atan2(distanciaY, distanciaX) - arrowAngle);
        double y1 = FlechaY - ArrowLength * Math.sin(Math.atan2(distanciaY, distanciaX) - arrowAngle);
        double x2 = FlechaX - ArrowLength * Math.cos(Math.atan2(distanciaY, distanciaX) + arrowAngle);
        double y2 = FlechaY - ArrowLength * Math.sin(Math.atan2(distanciaY, distanciaX) + arrowAngle);

        Line parte1 = new Line(FlechaX, FlechaY, x1, y1);
        Line parte2 = new Line(FlechaX, FlechaY, x2, y2);
        parte1.setStroke(Color.BLACK);
        parte2.setStroke(Color.BLACK);
        parte1.setStrokeWidth(2);
        parte2.setStrokeWidth(2);
        espacio_grafo.getChildren().addAll(parte1, parte2);

    }

    @FXML
    public void eliminarAeropuerto(){
        try {
            // Cargar el FXML de la ventana secundaria 
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/espol/proyecto_estructuras_de_datos/EliminarAirport.fxml"));
            Parent root = loader.load();
            // PASO CLAVE: obtener el controlador de la nueva ventana
            EliminarAirportController ventana_actual = loader.getController();
            ventana_actual.setGrafo(grafo);
            // Crear un nuevo Stage (ventana)
            Stage stage = new Stage();
            stage.setTitle("Eliminar Aeropuerto");
            stage.setScene(new Scene(root));

            // Opcional: hacerla modal para bloquear la ventana principal
            stage.initModality(Modality.APPLICATION_MODAL);

            // Mostrar la ventana y esperar a que se cierre
            stage.showAndWait();
            actualizarGrafo();

        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Error al eliminarAeropuerto desde la vista");
        }
    }

    @FXML
    public void SwitchToConnectAirPortsView(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/espol/proyecto_estructuras_de_datos/CrearVueloConexion.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Conectar Aeropuertos");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            actualizarGrafo();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
