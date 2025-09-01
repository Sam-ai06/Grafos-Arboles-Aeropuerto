package com.espol.proyecto_estructuras_de_datos.Controladores;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import modelo_logica.Aeropuerto;
import modelo_logica.Graph_RedVuelos;
import modelo_logica.Vuelo;

public class EstadisticaController implements Initializable {

    @FXML private Label totalAeropuertosLabel;
    @FXML private Label totalVuelosLabel;
    @FXML private Label rutasActivasLabel;
    @FXML private Label aeropuertoMasConectadoLabel;
    @FXML private Label codigoMasConectadoLabel;
    @FXML private Label conexionesMaxLabel;
    @FXML private Label ciudadMasConectadoLabel;
    @FXML private Label costoMinimoLabel;
    @FXML private Label duracionMinimaLabel;
    @FXML private Label estadoDijkstraLabel;
    @FXML private Label detalleAeropuertosLabel;
    @FXML private Button actualizarBtn;
    @FXML private Button cerrarBtn;

    private Graph_RedVuelos grafo;
    private Aeropuerto origenActual;
    private Aeropuerto destinoActual;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (grafo == null) {
            inicializarValoresPorDefecto();
        }
    }

    private void inicializarValoresPorDefecto() {
        totalAeropuertosLabel.setText("0");
        totalVuelosLabel.setText("0");
        rutasActivasLabel.setText("0");
        aeropuertoMasConectadoLabel.setText("N/A");
        codigoMasConectadoLabel.setText("N/A");
        conexionesMaxLabel.setText("0");
        ciudadMasConectadoLabel.setText("N/A");
        costoMinimoLabel.setText("No calculado");
        duracionMinimaLabel.setText("No calculado");
        estadoDijkstraLabel.setText("No hay ruta calculada");
        detalleAeropuertosLabel.setText("Esperando datos del grafo...");
    }

    public void setGrafo(Graph_RedVuelos grafo) {
        this.grafo = grafo;
        if (grafo != null) {
            actualizarEstadisticas();
        }
    }

    public void setRutaCalculada(Aeropuerto origen, Aeropuerto destino) {
        this.origenActual = origen;
        this.destinoActual = destino;
        actualizarEstadisticas();
    }

    @FXML
    private void actualizarEstadisticas() {
        if (grafo == null) {
            return;
        }

        try {
            totalAeropuertosLabel.setText(String.valueOf(grafo.getAeropuertos().size()));
            totalVuelosLabel.setText(String.valueOf(grafo.TotalVuelos()));
            rutasActivasLabel.setText(String.valueOf(grafo.TotalVuelos()));

            Aeropuerto masConectado = grafo.aeropuertomasconectado();

            if (masConectado != null) {
                String nombre = "N/A";
                if (masConectado.getNombre() != null) {
                    nombre = masConectado.getNombre();
                }

                String codigo = "N/A";
                if (masConectado.getCodigo() != null) {
                    codigo = masConectado.getCodigo();
                }

                String ciudad = "N/A";
                if (masConectado.getCiudad() != null) {
                    ciudad = masConectado.getCiudad();
                }

                int conexiones = grafo.numconexionesaeropuerto(masConectado);

                aeropuertoMasConectadoLabel.setText(nombre);
                codigoMasConectadoLabel.setText(codigo);
                conexionesMaxLabel.setText(String.valueOf(conexiones));
                ciudadMasConectadoLabel.setText(ciudad);
            } else {
                aeropuertoMasConectadoLabel.setText("N/A");
                codigoMasConectadoLabel.setText("N/A");
                conexionesMaxLabel.setText("0");
                ciudadMasConectadoLabel.setText("N/A");
            }

            if (grafo.isDijkstra() && grafo.getRuta_corta() != null && !grafo.getRuta_corta().isEmpty()) {
                List<Vuelo> rutaCalculada = grafo.getRuta_corta();

                int costoTotal = 0;
                int duracionTotal = 0;
                int distanciaTotal = 0;

                for (Vuelo vuelo : rutaCalculada) {
                    costoTotal += vuelo.getCosto();
                    duracionTotal += vuelo.getDuracion();
                    distanciaTotal += vuelo.getDistancia();
                }

                costoMinimoLabel.setText("$" + costoTotal);
                duracionMinimaLabel.setText(duracionTotal + " horas");

                Aeropuerto origen = rutaCalculada.get(0).getOrigen();
                Aeropuerto destino = rutaCalculada.get(rutaCalculada.size() - 1).getDestino();

                estadoDijkstraLabel.setText("Ruta: " + origen.getCodigo() + " → " + destino.getCodigo() +
                        " (" + rutaCalculada.size() + " vuelos, " + distanciaTotal + " km)");
                estadoDijkstraLabel.setTextFill(javafx.scene.paint.Color.web("#28a745"));
            } else {
                costoMinimoLabel.setText("No calculado");
                duracionMinimaLabel.setText("No calculado");
                estadoDijkstraLabel.setText("No hay ruta calculada");
                estadoDijkstraLabel.setTextFill(javafx.scene.paint.Color.web("#6c757d"));
            }

            detalleAeropuertosLabel.setText(grafo.DetalleAeropuertos());

        } catch (Exception e) {
            System.err.println("Error actualizando estadísticas: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void cerrarVentana() {
        try {
            Stage stage = (Stage) cerrarBtn.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            System.err.println("Error cerrando ventana: " + e.getMessage());
        }
    }
}