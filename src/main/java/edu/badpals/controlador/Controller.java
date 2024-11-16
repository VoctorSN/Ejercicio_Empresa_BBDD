package edu.badpals.controlador;

import edu.badpals.Vista.Inputs;
import edu.badpals.modelo.Conexion;
import edu.badpals.modelo.ConexionMetadata;
import edu.badpals.modelo.Departamento;
import edu.badpals.modelo.Proyecto;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Controller {
    Connection c;
    DatabaseMetaData md;
    Scanner scanner;
    Inputs in;
    String esquema;
    String tabla;
    String rs;

    public Controller() {
        c = Conexion.conectar();
        md = ConexionMetadata.getMetadata(c);
        scanner = new Scanner(System.in);
        in = new Inputs();
    }


    public void displayMenu() {

        while (true) {
            int option = in.getOptionMenu();

            switch (option) {
                case 1:
                    Conexion.subirSalarioDepartamento(c, 500, "ESTADÍSTICA");
                    break;
                case 2:
                    Conexion.insertarDep(c, 8, "NuevoDep", "1100222");
                    break;
                case 3:
                    for (List<String> s : Conexion.leerEmpleadosPorLocalidad(c, "Vigo")) {
                        System.out.println(s);
                    }
                    break;
                case 4:
                    Conexion.eliminarEmpleadoProyecto(c, "0010010", 8);
                    break;
                case 5:
                    Conexion.cambiarDepProy(c, "TÉCNICO", "XESTION DE PERSOAL");
                    break;
                case 6:
                    Conexion.insertarProy(c, new Proyecto(11, "NuevoDep", "MiCasa", 3));
                    break;
                case 7:
                    Conexion.eliminarProy(c, 11);
                    break;
                case 8:
                    for (Proyecto py : Conexion.getProyectosByDep(c, "TÉCNICO")) {
                        System.out.println(py);
                    }
                    break;
                case 9:
                    Conexion.cambiarDomicilio(c, "9998888", "calle", 1, "piso", "cp", "localidad");
                    break;
                case 10:
                    System.out.println(Conexion.getProy(c, 1));
                    break;
                case 11:
                    for (Departamento departamento : Conexion.departControlaProxec(c, 3)) {
                        System.out.println(departamento);
                    }
                    break;
                case 12:
                    System.out.println(Conexion.empDepart(c, "PERSOAL"));
                    break;
                case 13:
                    System.out.println(Conexion.insertarProyCheck(c, new Proyecto(11, "NuevoDep", "MiCasa", 3)));
                    System.out.println(Conexion.insertarProyCheck(c, new Proyecto(200, "NuevoDep", "MiCasa", 3)));
                    System.out.println(Conexion.insertarProyCheck(c, new Proyecto(11, "OtroNombre", "MiCasa", 3)));
                    System.out.println(Conexion.insertarProyCheck(c, new Proyecto(200, "OtroNombre", "MiCasa", 200)));
                    break;
                case 14:
                    Conexion.incrementarSalarioDep(c, 100, 1);
                    break;
                case 15:
                    Conexion.verFilasRS(Conexion.getEmpleadosNumProyectos(c, 1));
                    break;
                case 16:
                    displayMetadataMenu();
                    break;
                case 0:
                    try {
                        c.close();
                    } catch (SQLException ignored) {
                    }
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }


    public void displayMetadataMenu() {
        while (true) {
            int option = in.getOptionMenuMetadatos();

            switch (option) {
                case 1:
                    ConexionMetadata.mostrarInformacion(md);
                    break;
                case 2:
                    ConexionMetadata.mostrarTablasUsuario(md);
                    break;
                case 3:
                    esquema = in.getEsquema();
                    tabla = in.getTabla();
                    ConexionMetadata.mostrarColumnasTabla(md, esquema, tabla);
                    break;
                case 4:
                    ConexionMetadata.mostrarProcedimientos(md);
                    break;
                case 5:
                    esquema = in.getEsquema();
                    tabla = in.getTabla();
                    ConexionMetadata.mostrarClavePrimaria(md, esquema, tabla);
                    break;
                case 6:
                    esquema = in.getEsquema();
                    tabla = in.getTabla();
                    ConexionMetadata.mostrarClaveForanea(md, esquema, tabla);
                    break;
                case 7:
                    ConexionMetadata.mostrarInformacionAdicional(md);
                    break;
                case 8:
                    ConexionMetadata.mostrarLimitesConectador(md);
                    break;
                case 9:
                    ConexionMetadata.mostrarInformacionTransacciones(md);
                    break;
                case 10:
                    ConexionMetadata.mostrarSoporteCaracteristicas(md);
                    break;
                case 11:
                    rs = in.getResoultSet();
                    ConexionMetadata.mostrarMetadataResultSet(c, rs);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }


}