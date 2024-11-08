package edu.badpals.controlador;

import edu.badpals.modelo.Conexion;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Controller {
    Connection c;

    public Controller(){
        c = Conexion.conectar();
    }


    public void displayMenu(){
        try {
            Conexion.subirSalarioDepartamento(c,500,"ESTADÍSTICA");
            for (List s : Conexion.leerEmpleadosPorLocalidad(c,"Vigo")){
                System.out.println(s);
            }
            c.close();
        } catch (SQLException e) {}


    }

}
