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
    //prívate boolean esDirigido; se omite porque este siempre será dirigido por literatura
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
    public boolean eliminarvuelo(Aeropuerto origen, Aeropuerto Destino){
        Iterator<Vuelo> it = origen.getVuelos().iterator();
        while(it.hasNext()){
            if(cmp.compare(origen, it.next().getDestino())==0){
                it.remove();
                return true;}
        }
        return false;
    }
    public List<Aeropuerto> rutadistanciacorta(Aeropuerto origen, Aeropuerto Destino){
        int n=aeropuertos.size();
        if(cmp.compare(origen, Destino)==0){return null; }
        int[] dist=new int[n];
        int[] predecesor=new int[n];
        boolean[] visitado=new boolean[n];
        for(int i=0;i<n;i++){
            dist[i]=Integer.MAX_VALUE;
            predecesor[i]=-1;
        }
        int indiceinicio=aeropuertos.indexOf(origen);
        int indicefinal=aeropuertos.indexOf(Destino);
        dist[indiceinicio]=0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->dist[a]-dist[b]);
        pq.offer(indiceinicio);
        while(!pq.isEmpty()){
            int inicio=pq.poll();
            int distanciaact=dist[inicio];
            if (!visitado[inicio]) {
            visitado[inicio] = true;
            for(Vuelo vuelo:aeropuertos.get(inicio).getVuelos()){
                int vecino = aeropuertos.indexOf(vuelo.getDestino());
                int nuevo = distanciaact + vuelo.getDistancia();

                if (nuevo < dist[vecino]) {
                    dist[vecino] = nuevo;
                    predecesor[vecino] = inicio;
                    pq.offer(vecino);
                }
            }}
            
        }
        if(dist[indicefinal]==Integer.MAX_VALUE){return null;}
        List<Aeropuerto> regresar = new LinkedList<>();
        for (int at = indicefinal; at != -1; at = predecesor[at]) {
        regresar.addFirst(aeropuertos.get(at));
    }
        return regresar;
    }
    //conectar aeropuertos/ crear vuelos
    public boolean crearConexion(Aeropuerto origen, Aeropuerto destino,String aerolinea, int distancia, int duracion, int costo){
        if(!AirportIsInGrafo(origen)||!AirportIsInGrafo(destino))
            return false;
        Vuelo v = new Vuelo(aerolinea,origen,destino,distancia, duracion, costo);
        origen.getVuelos().add(v);//si es dirigido basta con esa conexion
        return true;   
    }
    //Devuelve la cantidad de conexiones que tiene un aeropuerto
    public int numconexionesaeropuerto(Aeropuerto a){
        if(a==null||!AirportIsInGrafo(a)){return -1;}
        int salida=a.getVuelos()!=null ? a.getVuelos().size():0;
        return salida;
    }
    //Devuelve el aeropuerto que posee más conexiones
    public Aeropuerto aeropuertomasconectado(){
        if(aeropuertos.isEmpty()){return null;}
        PriorityQueue<Aeropuerto> pq = new PriorityQueue<>((a,b)->numconexionesaeropuerto(b)-numconexionesaeropuerto(a));
        pq.addAll(aeropuertos);
        Aeropuerto salida = pq.peek();
        return salida;
        
    }
    //elimina el aeropuerto y sus existencias en las listas vuelo
    public boolean eliminaraeropuerto(Aeropuerto aeropuertoeliminar){
        if(aeropuertoeliminar==null||!AirportIsInGrafo(aeropuertoeliminar)){return false;}
        for(Aeropuerto aeropuerto: aeropuertos){
            if(cmp.compare(aeropuerto, aeropuertoeliminar)!=0){
                Iterator<Vuelo> it = aeropuerto.getVuelos().iterator();
                while(it.hasNext()){
                    Vuelo vueloact=it.next();
                    //si son iguales
                    if(cmp.compare(vueloact.getDestino(), aeropuertoeliminar)==0){
                        it.remove();
                    }
                }
            }
        }
        aeropuertos.remove(aeropuertoeliminar);
        return true;
    }
    public int duracionviaje(Aeropuerto origen, Aeropuerto destino){
        if(cmp.compare(origen, destino)==0) return 0;  
        Map<Aeropuerto, Integer> duracion= new HashMap<>();
        for(Aeropuerto a: aeropuertos) duracion.put(a, Integer.MAX_VALUE);
        duracion.put(origen, 0);
        PriorityQueue<Aeropuerto> pq = new PriorityQueue<>((a,b)->duracion.get(a)-duracion.get(b));
        pq.offer(origen);
        while(!pq.isEmpty()){
            Aeropuerto aeropuertoact=pq.poll();
            int duracionact=duracion.get(aeropuertoact);
            if(cmp.compare(aeropuertoact, destino)==0) return duracionact;
            for(Vuelo a:aeropuertoact.getVuelos()){
                Aeropuerto vecino=a.getDestino();
                int sumduracion=a.getDuracion()+duracionact;
                if(sumduracion<duracion.get(vecino)){
                    duracion.put(vecino, sumduracion);
                    pq.offer(vecino);
                }
            }
        }
        return-1;
    }
    public int costoviaje(Aeropuerto origen, Aeropuerto destino){
        if(cmp.compare(origen, destino)==0) return 0;
        Map<Aeropuerto, Integer> costo= new HashMap<>();
        for(Aeropuerto a: aeropuertos) costo.put(a, Integer.MAX_VALUE);
        costo.put(origen, 0);
        PriorityQueue<Aeropuerto> pq = new PriorityQueue<>((a,b)->costo.get(a)-costo.get(b));
        pq.offer(origen);
        while(!pq.isEmpty()){
            Aeropuerto aeropuertoact=pq.poll();
            int costoact=costo.get(aeropuertoact);
            if(cmp.compare(aeropuertoact, destino)==0) return costoact;
            for(Vuelo a:aeropuertoact.getVuelos()){
                Aeropuerto vecino=a.getDestino();
                int sumcosto=a.getDuracion()+costoact;
                if(sumcosto<costo.get(vecino)){
                    costo.put(vecino, sumcosto);
                    pq.offer(vecino);
                }
            }
        }
        return-1;
    }
      
    //algoritmo de dijkstra
    public int viaje(Aeropuerto origen, Aeropuerto destino) {
        if(cmp.compare(origen, destino)==0) return 0;

        // Inicializar distancias
        Map<Aeropuerto, Integer> dist = new HashMap<>();
        for (Aeropuerto a : aeropuertos) dist.put(a, Integer.MAX_VALUE);
        dist.put(origen, 0);

        // Cola de prioridad para seleccionar siempre el aeropuerto con menor distancia acumulada
        PriorityQueue<Aeropuerto> pq = new PriorityQueue<>((a, b) -> dist.get(a) - dist.get(b));
        pq.offer(origen);

        while (!pq.isEmpty()) {
            Aeropuerto actual = pq.poll();
            int distact = dist.get(actual);

            if(cmp.compare(actual, destino)==0) return distact;

            for (Vuelo vuelo : actual.getVuelos()) {   
                Aeropuerto vecino = vuelo.getDestino(); // Se obtiene el aeropuerto vecino
                int peso = vuelo.getDistancia();  
                int nuevo = distact + peso;

                if (nuevo < dist.get(vecino)) {
                    dist.put(vecino, nuevo);
                    pq.offer(vecino);
                }
            }
    }

    return -1; // No hay camino posible
}
    public Aeropuerto findAirport(String codigo){
        if(codigo==null){return null;}
        for (Aeropuerto aeropuertoact : aeropuertos) {
            String airportcode=aeropuertoact.getCodigo();
            if(airportcode.equals(codigo)){
                return aeropuertoact;
            }
        }
        return null;
    }
    
    public boolean contieneaeropuerto(Aeropuerto a1,Aeropuerto a2){
        Iterator<Vuelo> it= a1.getVuelos().iterator();
        while(it.hasNext()){
            if(cmp.compare(it.next().getDestino(), a2)==0){return true;}
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
