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
    private Aeropuerto origen;
    //destino
    private Aeropuerto destino;
    //pesos
    private int distancia;
    private int duracion;
    private int costo;

    public Vuelo(String aerolinea, Aeropuerto origen, Aeropuerto destino, int distancia, int duracion, int costo) {
        this.aerolinea = aerolinea;
        this.origen = origen;
        this.destino = destino;
        this.distancia = distancia;
        this.duracion = duracion;
        this.costo = costo;
    }

    public Vuelo(Aeropuerto origen, Aeropuerto destino) {
        this (null, origen, destino , 0, 0,0);
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

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }
    
    
    
    
    
}
