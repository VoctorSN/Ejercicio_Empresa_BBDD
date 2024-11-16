package edu.badpals.controlador;

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

    public Controller() {
        c = Conexion.conectar();
        md = ConexionMetadata.getMetadata(c);
        scanner = new Scanner(System.in);
    }


    public void displayMenu() {

        while (true) {
            System.out.println("======= MENU =======");
            System.out.println("1. Subir salario por departamento");
            System.out.println("2. Insertar departamento");
            System.out.println("3. Leer empleados por localidad");
            System.out.println("4. Eliminar empleado de proyecto");
            System.out.println("5. Cambiar departamento de proyecto");
            System.out.println("6. Insertar proyecto");
            System.out.println("7. Eliminar proyecto");
            System.out.println("8. Obtener proyectos por departamento");
            System.out.println("9. Cambiar domicilio");
            System.out.println("10. Obtener proyecto por ID");
            System.out.println("11. Departamentos que controlan proyectos");
            System.out.println("12. Empleados por departamento");
            System.out.println("13. Insertar proyecto con verificación");
            System.out.println("14. Incrementar salario por departamento");
            System.out.println("15. Ver filas de empleados por número de proyectos");
            System.out.println("16. Menu de metadatos");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

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
                    } catch (SQLException ignored) {}
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    public void displayMetadataMenu() {
        while (true) {
            System.out.println("======= MENU DE METADATOS =======");
            System.out.println("1. Mostrar información");
            System.out.println("2. Mostrar tablas de usuario");
            System.out.println("3. Mostrar columnas de tabla");
            System.out.println("4. Mostrar procedimientos");
            System.out.println("5. Mostrar clave primaria");
            System.out.println("6. Mostrar clave foránea");
            System.out.println("7. Mostrar información adicional");
            System.out.println("8. Mostrar límites del conector");
            System.out.println("9. Mostrar información de transacciones");
            System.out.println("10. Mostrar soporte de características");
            System.out.println("11. Mostrar metadatos de ResultSet");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    ConexionMetadata.mostrarInformacion(md);
                    break;
                case 2:
                    ConexionMetadata.mostrarTablasUsuario(md);
                    break;
                case 3:
                    ConexionMetadata.mostrarColumnasTabla(md, "empresa", "departamentos");
                    break;
                case 4:
                    ConexionMetadata.mostrarProcedimientos(md);
                    break;
                case 5:
                    ConexionMetadata.mostrarClavePrimaria(md, "empresa", "empleados");
                    break;
                case 6:
                    ConexionMetadata.mostrarClaveForanea(md, "empresa", "departamentos");
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
                    ConexionMetadata.mostrarMetadataResultSet(c, "SELECT * FROM PROYECTOS");
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

}