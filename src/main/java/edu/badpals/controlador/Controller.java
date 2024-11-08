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
            for (List s : Conexion.leerEmpleadosPorLocalidad(c,"Lugo")){
                System.out.println(s);
            }
            c.close();
        } catch (SQLException e) {}


    }

}
