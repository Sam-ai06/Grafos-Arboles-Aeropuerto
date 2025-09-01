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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("EstadisticaController inicializado");
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
        System.out.println("Grafo recibido en EstadisticaController");
        this.grafo = grafo;
        if (grafo != null) {
            System.out.println("Actualizando estadísticas con " + grafo.getAeropuertos().size() + " aeropuertos");
            actualizarEstadisticas();
        } else {
            System.out.println("Grafo es vacio!");
        }
    }

    @FXML
    private void actualizarEstadisticas() {
        System.out.println("Método actualizarEstadisticas llamado");

        if (grafo == null) {
            System.out.println("Grafo es vacio, no se pueden actualizar estadísticas");
            return;
        }

        try {
            List<Aeropuerto> aeropuertos = grafo.getAeropuertos();
            System.out.println("Procesando " + aeropuertos.size() + " aeropuertos");

            totalAeropuertosLabel.setText(String.valueOf(aeropuertos.size()));

            int totalVuelos = contarTotalVuelos();
            totalVuelosLabel.setText(String.valueOf(totalVuelos));
            System.out.println("Total vuelos: " + totalVuelos);

            int rutasActivas = contarRutasUnicas();
            rutasActivasLabel.setText(String.valueOf(rutasActivas));

            actualizarAeropuertoMasConectado();

            actualizarInformacionDijkstra();

            actualizarDetalleAeropuertos();

            System.out.println("Estadísticas actualizadas correctamente");

        } catch (Exception e) {
            System.err.println("Error actualizando estadísticas: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private int contarTotalVuelos() {
        int total = 0;
        if (grafo != null && grafo.getAeropuertos() != null) {
            for (Aeropuerto aeropuerto : grafo.getAeropuertos()) {
                if (aeropuerto.getVuelos() != null) {
                    total += aeropuerto.getVuelos().size();
                }
            }
        }
        return total;
    }

    private int contarRutasUnicas() {
        int rutas = 0;
        if (grafo != null && grafo.getAeropuertos() != null) {
            for (Aeropuerto aeropuerto : grafo.getAeropuertos()) {
                if (aeropuerto.getVuelos() != null) {
                    rutas += aeropuerto.getVuelos().size();
                }
            }
        }
        return rutas;
    }

    private void actualizarAeropuertoMasConectado() {
        if (grafo == null || grafo.getAeropuertos() == null || grafo.getAeropuertos().isEmpty()) {
            aeropuertoMasConectadoLabel.setText("N/A");
            codigoMasConectadoLabel.setText("N/A");
            conexionesMaxLabel.setText("0");
            ciudadMasConectadoLabel.setText("N/A");
            return;
        }

        Aeropuerto masConectado = null;
        int maxConexiones = 0;

        for (Aeropuerto aeropuerto : grafo.getAeropuertos()) {
            int conexiones = (aeropuerto.getVuelos() != null) ? aeropuerto.getVuelos().size() : 0;
            if (conexiones > maxConexiones) {
                maxConexiones = conexiones;
                masConectado = aeropuerto;
            }
        }

        if (masConectado != null) {
            aeropuertoMasConectadoLabel.setText(masConectado.getNombre() != null ? masConectado.getNombre() : "N/A");
            codigoMasConectadoLabel.setText(masConectado.getCodigo() != null ? masConectado.getCodigo() : "N/A");
            conexionesMaxLabel.setText(String.valueOf(maxConexiones));
            ciudadMasConectadoLabel.setText(masConectado.getCiudad() != null ? masConectado.getCiudad() : "N/A");
        } else {
            aeropuertoMasConectadoLabel.setText("N/A");
            codigoMasConectadoLabel.setText("N/A");
            conexionesMaxLabel.setText("0");
            ciudadMasConectadoLabel.setText("N/A");
        }
    }

    private void actualizarInformacionDijkstra() {

        if (grafo != null && grafo.getRuta_corta() != null && !grafo.getRuta_corta().isEmpty()) {
            int costoTotal = 0;
            int duracionTotal = 0;

            for (Vuelo vuelo : grafo.getRuta_corta()) {
                costoTotal += vuelo.getCosto();
                duracionTotal += vuelo.getDuracion();
            }

            costoMinimoLabel.setText("$" + costoTotal);
            duracionMinimaLabel.setText(duracionTotal + " minutos");
            estadoDijkstraLabel.setText("Ruta calculada (" + grafo.getRuta_corta().size() + " vuelos)");
            estadoDijkstraLabel.setTextFill(javafx.scene.paint.Color.web("#28a745"));

        } else {
            costoMinimoLabel.setText("No calculado");
            duracionMinimaLabel.setText("No calculado");
            estadoDijkstraLabel.setText("No hay ruta calculada");
            estadoDijkstraLabel.setTextFill(javafx.scene.paint.Color.web("#6c757d"));
        }
    }

    private void actualizarDetalleAeropuertos() {
        StringBuilder detalle = new StringBuilder();

        if (grafo == null || grafo.getAeropuertos() == null) {
            detalleAeropuertosLabel.setText("Error: No se pudo cargar la información del grafo.");
            return;
        }

        detalle.append("Lista de aeropuertos registrados:\n\n");

        for (Aeropuerto aeropuerto : grafo.getAeropuertos()) {
            if (aeropuerto != null) {
                detalle.append("• ").append(aeropuerto.getNombre() != null ? aeropuerto.getNombre() : "Sin nombre")
                        .append(" (").append(aeropuerto.getCodigo() != null ? aeropuerto.getCodigo() : "Sin código").append(")")
                        .append(" - ").append(aeropuerto.getCiudad() != null ? aeropuerto.getCiudad() : "Sin ciudad")
                        .append(", ").append(aeropuerto.getPais() != null ? aeropuerto.getPais() : "Sin país");

                int numVuelos = (aeropuerto.getVuelos() != null) ? aeropuerto.getVuelos().size() : 0;
                detalle.append(" - ").append(numVuelos).append(" vuelos")
                        .append("\n");
            }
        }

        if (grafo.getAeropuertos().isEmpty()) {
            detalle.append("No hay aeropuertos registrados en el sistema.");
        }

        detalleAeropuertosLabel.setText(detalle.toString());
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