/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo_logica;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author hp
 */
public class Graph_RedVuelos {
    private List<Aeropuerto> aeropuertos;
    //private boolean esDirigido; se omite porque este siempre será dirigido por literatura
    private Comparator<Aeropuerto> cmp;//para posibles metodos -> probablemente este cmp evalue nombre y codigo del aeropuerto así evito duplicados
    //pero no sé dónde se crearía ese cmp ¿?
    
    //antes de entrar aquí se debió crear el Aeropuerto
    public boolean agregarAeropuerto(Aeropuerto airport){
        
        if(airport==null || AirportIsInGrafo(airport)){
            return false;
        }
        aeropuertos.add(airport);
        return true;
    }
    public boolean AirportIsInGrafo(Aeropuerto airport){
        Iterator<Aeropuerto> it = aeropuertos.iterator();
        while(it.hasNext()){
            if(cmp.compare(it.next(), airport)==0){
                return true;
            }
        }
        return false;
    }
    //conectar aeropuertos/ crear vuelos
    public boolean crearConexion(Aeropuerto origen, Aeropuerto destino,String aerolinea, int distancia, int duracion, int costo){
        if(!AirportIsInGrafo(origen)||!AirportIsInGrafo(destino))
            return false;
        Vuelo v = new Vuelo(aerolinea,origen,destino,distancia, duracion, costo);
        origen.getVuelos().add(v);//si es dirigido basta con esa conexion
        return true;   
    }
    
    public List<Aeropuerto> getAeropuertos() {
        return aeropuertos;
    }

    public void setNodos(List<Aeropuerto> nodos) {
        this.aeropuertos = nodos;
    }

    public Graph_RedVuelos(Comparator<Aeropuerto> cmp) {
        this.cmp = cmp;
        this.aeropuertos = new LinkedList<>();
    }
    

    
}
