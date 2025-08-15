module com.espol.proyecto_estructuras_de_datos {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.espol.proyecto_estructuras_de_datos to javafx.fxml;
    exports com.espol.proyecto_estructuras_de_datos;
    exports com.espol.proyecto_estructuras_de_datos.Controladores;
    opens com.espol.proyecto_estructuras_de_datos.Controladores to javafx.fxml;
}