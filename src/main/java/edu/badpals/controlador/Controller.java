package edu.badpals.controlador;

import edu.badpals.modelo.Conexion;

import java.sql.Connection;
import java.sql.SQLException;

public class Controller {
    Connection c;

    public Controller(){
        c = Conexion.conectar();
    }


    public void displayMenu(){
        try {

        c.close();
        } catch (SQLException e) {}


    }

}
