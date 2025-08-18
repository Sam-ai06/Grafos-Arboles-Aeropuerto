/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo_logica;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author hp
 */
public class Aeropuerto{
    private String nombre;
    private boolean esCentral = false;
    private String codigo;
    private String ciudad;
    private String pais;
    private List<Vuelo> vuelos;//es lo que lo conecta con otros aeropuertos
    private boolean isVisited; //para manejar recorrido entre nodos

    public Aeropuerto(String nombre, String codigo, String ciudad, String pais) {
        this.nombre = nombre;
        this.codigo = codigo;//codigo comercial IATA
        this.ciudad = ciudad;
        this.pais = pais;
        if(nombre.equals("Aeropuerto Internacional de Daxing"))
            this.esCentral = true;
        vuelos = new LinkedList<>();
        
    }

    
  
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isEsCentral() {
        return esCentral;
    }

    public void setEsCentral(boolean esCentral) {
        this.esCentral = esCentral;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public List<Vuelo> getVuelos() {
        return vuelos;
    }

    public void setArcos(List<Vuelo> arcos) {
        this.vuelos = arcos;
    }

    public boolean isIsVisited() {
        return isVisited;
    }

    public void setIsVisited(boolean isVisited) {
        this.isVisited = isVisited;
    }
    
    
    
    
}
