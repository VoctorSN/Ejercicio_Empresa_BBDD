package edu.badpals.controlador;

import edu.badpals.modelo.Conexion;
import edu.badpals.modelo.Departamento;
import edu.badpals.modelo.Proyecto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Controller {
    Connection c;

    public Controller() {
        c = Conexion.conectar();
    }


    public void displayMenu() {
        try {
            //Conexion.subirSalarioDepartamento(c, 500, "ESTADÍSTICA");
            //Conexion.insertarDep(c,8,"NuevoDep","1100222");
            for (List<String> s : Conexion.leerEmpleadosPorLocalidad(c, "Vigo")) {
                System.out.println(s);
            }
            //Conexion.eliminarEmpleadoProyecto(c, "0010010", 8);
            //Conexion.cambiarDepProy(c, "TÉCNICO", "XESTION DE PERSOAL");
            //Conexion.insertarProy(c, new Proyecto(11, "NuevoDep", "MiCasa", 3));
            //Conexion.eliminarProy(c, 11);
            for (Proyecto py : Conexion.getProyectosByDep(c, "TÉCNICO")) {
                System.out.println(py);
            }
            //Conexion.cambiarDomicilio(c, "9998888", "calle", 1, "piso", "cp", "localidad");
            //System.out.println(Conexion.getProy(c, 1));
            for (Departamento departamento : Conexion.departControlaProxec(c, 3)) {
                System.out.println(departamento);
            }
            System.out.println(Conexion.empDepart(c, "PERSOAL"));
            
            System.out.println(Conexion.insertarProyCheck(c, new Proyecto(11, "NuevoDep", "MiCasa", 3)));
            System.out.println(Conexion.insertarProyCheck(c, new Proyecto(200, "NuevoDep", "MiCasa", 3)));
            System.out.println(Conexion.insertarProyCheck(c, new Proyecto(11, "OtroNombre", "MiCasa", 3)));
            System.out.println(Conexion.insertarProyCheck(c, new Proyecto(200, "OtroNombre", "MiCasa", 200)));
            c.close();
        } catch (SQLException ignored) {
        }

    }

}