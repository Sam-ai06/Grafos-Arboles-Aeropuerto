/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo_logica;

/**
 *
 * @author hp
 */
public class Vuelo {
    private String aerolinea;
   
    //origen

    // origen
    private Aeropuerto origen;
    // destino
    private Aeropuerto destino;
    // pesos
    private double distancia;
    private double duracion;
    private double costo;
    // Número de vuelo - usado para la vista eliminar vuelo
    private String numeroVuelo;

    public Vuelo(String numVuelo, String aerolinea, Aeropuerto origen, Aeropuerto destino, int distancia, int duracion, int costo) {
        this.numeroVuelo = numVuelo;
        this.aerolinea = aerolinea;
        this.origen = origen;
        this.destino = destino;
        this.distancia = distancia;
        this.duracion = duracion;
        this.costo = costo;
    }

    public Vuelo(Aeropuerto origen, Aeropuerto destino) {
        this(null, null, origen, destino, 0, 0, 0);
    }

    public String getAerolinea() {
        return aerolinea;
    }

    public void setAerolinea(String aerolinea) {
        this.aerolinea = aerolinea;
    }

    public Aeropuerto getOrigen() {
        return origen;
    }

    public void setOrigen(Aeropuerto origen) {
        this.origen = origen;
    }

    public Aeropuerto getDestino() {
        return destino;
    }

    public void setDestino(Aeropuerto destino) {
        this.destino = destino;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getNumeroVuelo() {
        return numeroVuelo;
    }

    public void setNumeroVuelo(String numeroVuelo) {
        this.numeroVuelo = numeroVuelo;
    }

}
