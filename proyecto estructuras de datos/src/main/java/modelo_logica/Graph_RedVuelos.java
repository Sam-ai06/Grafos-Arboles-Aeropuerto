/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo_logica;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;


/**
 *
 * @author hp
 */
public class Graph_RedVuelos {
    private List<Aeropuerto> aeropuertos;
    // prívate boolean esDirigido; se omite porque este siempre será dirigido por
    // literatura
    private Comparator<Aeropuerto> cmp;// para posibles metodos -> probablemente este cmp evalue nombre y codigo del
                                       // aeropuerto así evito duplicados
    // pero no sé dónde se crearía ese cmp ¿?

    // antes de entrar aquí se debió crear el Aeropuerto
    public boolean agregarAeropuerto(Aeropuerto airport) {

        if (airport == null || AirportIsInGrafo(airport)) {
            return false;
        }
        aeropuertos.add(airport);
        return true;
    }

    public boolean AirportIsInGrafo(Aeropuerto airport) {
        Iterator<Aeropuerto> it = aeropuertos.iterator();
        while (it.hasNext()) {
            if (cmp.compare(it.next(), airport) == 0) {
                return true;
            }
        }
        return false;
    }

    public boolean eliminarVuelo(String numeroVuelo) {
        if (numeroVuelo == null || numeroVuelo.trim().isEmpty()) {
            return false;
        }

        for (Aeropuerto aeropuerto : aeropuertos) {
            Iterator<Vuelo> it = aeropuerto.getVuelos().iterator();
            while (it.hasNext()) {
                Vuelo vuelo = it.next();
                if (vuelo.getNumeroVuelo() != null && vuelo.getNumeroVuelo().equals(numeroVuelo)) {
                    it.remove();
                    return true;
                }
            }
        }
        return false;
    }

    public List<Aeropuerto> rutadistanciacorta(Aeropuerto origen, Aeropuerto Destino) {
        int n = aeropuertos.size();
        if (cmp.compare(origen, Destino) == 0) {
            return null;
        }
        int[] dist = new int[n];
        int[] predecesor = new int[n];
        boolean[] visitado = new boolean[n];
        for (int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
            predecesor[i] = -1;
        }
        int indiceinicio = aeropuertos.indexOf(origen);
        int indicefinal = aeropuertos.indexOf(Destino);
        dist[indiceinicio] = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> dist[a] - dist[b]);
        pq.offer(indiceinicio);
        while (!pq.isEmpty()) {
            int inicio = pq.poll();
            int distanciaact = dist[inicio];
            if (!visitado[inicio]) {
                visitado[inicio] = true;
                for (Vuelo vuelo : aeropuertos.get(inicio).getVuelos()) {
                    int vecino = aeropuertos.indexOf(vuelo.getDestino());
                    int nuevo = distanciaact + vuelo.getDistancia();

                    if (nuevo < dist[vecino]) {
                        dist[vecino] = nuevo;
                        predecesor[vecino] = inicio;
                        pq.offer(vecino);
                    }
                }
            }

        }
        if (dist[indicefinal] == Integer.MAX_VALUE) {
            return null;
        }
        List<Aeropuerto> regresar = new LinkedList<>();
        for (int at = indicefinal; at != -1; at = predecesor[at]) {
            regresar.addFirst(aeropuertos.get(at));
        }
        return regresar;
    }
    public List<Aeropuerto> rutaduracioncorta(Aeropuerto origen,Aeropuerto Destino){
        int n = aeropuertos.size();
        if (cmp.compare(origen, Destino) == 0) {
            return null;
        }
        int[] duracion = new int[n];
        int[] predecesor = new int[n];
        boolean[] visitado = new boolean[n];
        for (int i = 0; i < n; i++) {
            duracion[i] = Integer.MAX_VALUE;
            predecesor[i] = -1;
        }
        int indiceinicio = aeropuertos.indexOf(origen);
        int indicefinal = aeropuertos.indexOf(Destino);
        duracion[indiceinicio] = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> duracion[a] - duracion[b]);
        pq.offer(indiceinicio);
        while (!pq.isEmpty()) {
            int inicio = pq.poll();
            int duracionact=duracion[inicio];
            if (!visitado[inicio]) {
                visitado[inicio] = true;
            for(Vuelo vuelo:aeropuertos.get(inicio).getVuelos()){
                int vecino=aeropuertos.indexOf(vuelo.getDestino());
                int duracionsuma=duracionact+vuelo.getDuracion();
                if(duracionsuma<duracion[vecino]){
                    duracion[vecino]=duracionsuma;
                    predecesor[vecino]=inicio;
                    pq.offer(vecino);
                }
            }
            }
        
        }
        if (duracion[indicefinal] == Integer.MAX_VALUE) {
            return null;
        }
        List<Aeropuerto> regresar = new LinkedList<>();
        for (int at = indicefinal; at != -1; at = predecesor[at]) {
            regresar.addFirst(aeropuertos.get(at));
        }
        return regresar;
    }
    public List<Vuelo> rutaduracioncortaauxiliar(Aeropuerto origen,Aeropuerto Destino){
        int n = aeropuertos.size();
        if (cmp.compare(origen, Destino) == 0) {
            return null;
        }
        int[] duracion = new int[n];
        int[] predecesor = new int[n];
        boolean[] visitado = new boolean[n];
        for (int i = 0; i < n; i++) {
            duracion[i] = Integer.MAX_VALUE;
            predecesor[i] = -1;
        }
        int indiceinicio = aeropuertos.indexOf(origen);
        int indicefinal = aeropuertos.indexOf(Destino);
        duracion[indiceinicio] = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> duracion[a] - duracion[b]);
        pq.offer(indiceinicio);
        while (!pq.isEmpty()) {
            int inicio = pq.poll();
            int duracionact=duracion[inicio];
            if (!visitado[inicio]) {
                visitado[inicio] = true;
            for(Vuelo vuelo:aeropuertos.get(inicio).getVuelos()){
                int vecino=aeropuertos.indexOf(vuelo.getDestino());
                int duracionsuma=duracionact+vuelo.getDuracion();
                if(duracionsuma<duracion[vecino]){
                    duracion[vecino]=duracionsuma;
                    predecesor[vecino]=inicio;
                    pq.offer(vecino);
                }
            }
            }
        
        }
        if (duracion[indicefinal] == Integer.MAX_VALUE) {
            return null;
        }
        List<Aeropuerto> regresar = new LinkedList<>();
        for (int at = indicefinal; at != -1; at = predecesor[at]) {
            regresar.addFirst(aeropuertos.get(at));
        }
        List<Vuelo> vuelosregresar = new LinkedList<>();
        for (int i = 0; i < regresar.size() - 1; i++) {
            Aeropuerto actual = regresar.get(i);
            Aeropuerto siguiente = regresar.get(i + 1);
            for (Vuelo vueloact : actual.getVuelos()) {
            if (cmp.compare(vueloact.getDestino(), siguiente)==0) {
                vuelosregresar.add(vueloact);
            }
        }
        }
        return vuelosregresar;
    }

    // conectar aeropuertos/ crear vuelos
    public boolean crearConexion(Aeropuerto origen, Aeropuerto destino, String aerolinea, int distancia, int duracion, int costo, String numeroVuelo) {
        if (!AirportIsInGrafo(origen) || !AirportIsInGrafo(destino))
            return false;
        Vuelo v = new Vuelo(numeroVuelo, aerolinea, origen, destino, distancia, duracion, costo);
        origen.getVuelos().add(v);// si es dirigido basta con esa conexion
        return true;
    }

    // Devuelve la cantidad de conexiones que tiene un aeropuerto
    public int numconexionesaeropuerto(Aeropuerto a) {
        if (a == null || !AirportIsInGrafo(a)) {
            return -1;
        }
        int salida = a.getVuelos() != null ? a.getVuelos().size() : 0;
        return salida;
    }

    // Devuelve el aeropuerto que posee más conexiones
    public Aeropuerto aeropuertomasconectado() {
        if (aeropuertos.isEmpty()) {
            return null;
        }
        PriorityQueue<Aeropuerto> pq = new PriorityQueue<>(
                (a, b) -> numconexionesaeropuerto(b) - numconexionesaeropuerto(a));
        pq.addAll(aeropuertos);
        Aeropuerto salida = pq.peek();
        return salida;

    }

    // elimina el aeropuerto y sus existencias en las listas vuelo
    public boolean eliminaraeropuerto(Aeropuerto aeropuertoeliminar) {
        if (aeropuertoeliminar == null || !AirportIsInGrafo(aeropuertoeliminar)) {
            return false;
        }
        for (Aeropuerto aeropuerto : aeropuertos) {
            if (cmp.compare(aeropuerto, aeropuertoeliminar) != 0) {
                Iterator<Vuelo> it = aeropuerto.getVuelos().iterator();
                while (it.hasNext()) {
                    Vuelo vueloact = it.next();
                    // si son iguales
                    if (cmp.compare(vueloact.getDestino(), aeropuertoeliminar) == 0) {
                        it.remove();
                    }
                }
            }
        }
        aeropuertos.remove(aeropuertoeliminar);
        return true;
    }
    //da la duracion haciendo uso de la ruta
    public int duracionviaje(Aeropuerto origen, Aeropuerto destino) {
        if (cmp.compare(origen, destino) == 0)
            return 0;
        List<Aeropuerto>ruta=rutaduracioncorta( origen,  destino);
        if (ruta == null || ruta.size() < 2) return -1;
        int duracionTotal = 0;
        for (int i = 0; i < ruta.size() - 1; i++) {
            Aeropuerto actual = ruta.get(i);
            Aeropuerto siguiente = ruta.get(i + 1);
            for (Vuelo vueloact : actual.getVuelos()) {
            if (cmp.compare(vueloact.getDestino(), siguiente)==0) {
                duracionTotal += vueloact.getDuracion();}
            }
        
        }
        return duracionTotal;
    }
    public List<Aeropuerto> rutacostobajo(Aeropuerto origen, Aeropuerto Destino){
        int n = aeropuertos.size();
        if (cmp.compare(origen, Destino) == 0) {
            return null;
        }
        int[] costo = new int[n];
        int[] predecesor = new int[n];
        boolean[] visitado = new boolean[n];
        for (int i = 0; i < n; i++) {
            costo[i] = Integer.MAX_VALUE;
            predecesor[i] = -1;
        }
        int indiceinicio = aeropuertos.indexOf(origen);
        int indicefinal = aeropuertos.indexOf(Destino);
        costo[indiceinicio] = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> costo[a] - costo[b]);
        pq.offer(indiceinicio);
        while (!pq.isEmpty()) {
            int inicio = pq.poll();
            int costoact=costo[inicio];
            if (!visitado[inicio]) {
                visitado[inicio] = true;
            for(Vuelo vuelo:aeropuertos.get(inicio).getVuelos()){
                int vecino=aeropuertos.indexOf(vuelo.getDestino());
                int costosuma=costoact+vuelo.getCosto();
                if(costosuma<costo[vecino]){
                    costo[vecino]=costosuma;
                    predecesor[vecino]=inicio;
                    pq.offer(vecino);
                }
            }
            }
        
        }
        if (costo[indicefinal] == Integer.MAX_VALUE) {
            return null;
        }
        List<Aeropuerto> regresar = new LinkedList<>();
        for (int at = indicefinal; at != -1; at = predecesor[at]) {
            regresar.addFirst(aeropuertos.get(at));
        }
        return regresar;
    }
    ///da el costo haciendo uso de la ruta
    public int costoviaje(Aeropuerto origen, Aeropuerto destino) {
        if (cmp.compare(origen, destino) == 0)
            return 0;
        List<Aeropuerto>ruta=rutacostobajo( origen,  destino);
        if (ruta == null || ruta.size() < 2) return -1;
        int costoTotal = 0;
        for (int i = 0; i < ruta.size() - 1; i++) {
            Aeropuerto actual = ruta.get(i);
            Aeropuerto siguiente = ruta.get(i + 1);
            for (Vuelo vueloact : actual.getVuelos()) {
            if (cmp.compare(vueloact.getDestino(), siguiente)==0) {
                costoTotal += vueloact.getCosto();}
            }
        
        }
        return costoTotal;
    }

    // da el la distancia haciendo uso de la ruta
    public int distanciaviaje(Aeropuerto origen, Aeropuerto destino) {
        if (cmp.compare(origen, destino) == 0)
            return 0;
        List<Aeropuerto>ruta=rutadistanciacorta( origen,  destino);
        if (ruta == null || ruta.size() < 2) return -1;
        int distanciatotal = 0;
        for (int i = 0; i < ruta.size() - 1; i++) {
            Aeropuerto actual = ruta.get(i);
            Aeropuerto siguiente = ruta.get(i + 1);
            for (Vuelo vueloact : actual.getVuelos()) {
            if (cmp.compare(vueloact.getDestino(), siguiente)==0) {
                distanciatotal += vueloact.getDistancia();}
            }
        
        }
        return distanciatotal;
    }

    public Aeropuerto findAirport(String codigo) {
        if (codigo == null) {
            return null;
        }
        for (Aeropuerto aeropuertoact : aeropuertos) {
            String airportcode = aeropuertoact.getCodigo();
            if (airportcode.equals(codigo)) {
                return aeropuertoact;
            }
        }
        return null;
    }

    public boolean contieneaeropuerto(Aeropuerto a1, Aeropuerto a2) {
        Iterator<Vuelo> it = a1.getVuelos().iterator();
        while (it.hasNext()) {
            if (cmp.compare(it.next().getDestino(), a2) == 0) {
                return true;
            }
        }
        return false;
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
