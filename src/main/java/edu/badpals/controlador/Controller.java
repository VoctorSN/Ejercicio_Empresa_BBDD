package edu.badpals.controlador;

import edu.badpals.Vista.Inputs;
import edu.badpals.modelo.Conexion;
import edu.badpals.modelo.ConexionMetadata;
import edu.badpals.modelo.Departamento;
import edu.badpals.modelo.Proyecto;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
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
                    String dept1 = in.getDepartamento();
                    int amount1 = in.getAmount();
                    Conexion.subirSalarioDepartamento(c, amount1, dept1);
                    break;
                case 2:
                    int depId = in.getDepId();
                    String depName = in.getDepName();
                    String depCode = in.getDepCode();
                    Conexion.insertarDep(c, depId, depName, depCode);
                    break;
                case 3:
                    String localidad = in.getLocalidad();
                    for (List<String> s : Conexion.leerEmpleadosPorLocalidad(c, localidad)) {
                        System.out.println(s);
                    }
                    break;
                case 4:
                    String empId = in.getEmpId();
                    int projId = in.getProjId();
                    Conexion.eliminarEmpleadoProyecto(c, empId, projId);
                    break;
                case 5:
                    String oldDept = in.getOldDept();
                    String newDept = in.getNewProy();
                    Conexion.cambiarDepProy(c, oldDept, newDept);
                    break;
                case 6:
                    Proyecto proyecto = in.getProyecto();
                    Conexion.insertarProy(c, proyecto);
                    break;
                case 7:
                    int projIdToDelete = in.getProjIdToDelete();
                    Conexion.eliminarProy(c, projIdToDelete);
                    break;
                case 8:
                    String dept2 = in.getDepartamento();
                    for (Proyecto py : Conexion.getProyectosByDep(c, dept2)) {
                        System.out.println(py);
                    }
                    break;
                case 9:
                    String empId2 = in.getEmpId();
                    String calle = in.getCalle();
                    int numero = in.getNumero();
                    String piso = in.getPiso();
                    String cp = in.getCp();
                    String localidad2 = in.getLocalidad();
                    Conexion.cambiarDomicilio(c, empId2, calle, numero, piso, cp, localidad2);
                    break;
                case 10:
                    int projIdToGet = in.getProjIdToGet();
                    proyecto = Conexion.getProy(c, projIdToGet);
                    if (proyecto == null) {
                        System.out.println("No existe el proyecto");
                    } else {
                        System.out.println(proyecto);
                    }
                    break;
                case 11:
                    int projId3 = in.getProjId();
                    for (Departamento departamento : Conexion.departControlaProxec(c, projId3)) {
                        System.out.println(departamento);
                    }
                    break;
                case 12:
                    String dept3 = in.getDepartamento();
                    System.out.println(Conexion.empDepart(c, dept3));
                    break;
                case 13:
                    Proyecto proyectoCheck = in.getProyecto();
                    Conexion.insertarProyCheck(c, proyectoCheck);
                    break;
                case 14:
                    int amount2 = in.getAmount();
                    int depId2 = in.getDepId();
                    Conexion.incrementarSalarioDep(c, amount2, depId2);
                    break;
                case 15:
                    int numProyectos = in.getNumProyectos();
                    ResultSet rs = Conexion.getEmpleadosNumProyectos(c, numProyectos);
                    if (rs != null) {
                        Conexion.verFilasRS(rs);
                    } else {
                        System.out.println("Ocurrio un error en getEmpleadosNumProyectos");
                    }
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