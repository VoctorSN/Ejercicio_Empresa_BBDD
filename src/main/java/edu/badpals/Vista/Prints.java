package edu.badpals.Vista;

public class Prints {
    public static void menuMetadatos() {
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
    }

    public static void getEsquema(){
        System.out.println("INTRODUCE EL ESQUEMA");
    }

    public static void menu() {
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
    }

    public static void getTabla() {
        System.out.println("INTRODUCE LA TABLA");
    }

    public static void getResoultSet() {
        System.out.println("INTRODUCE EL RESOULT SET");
    }

    public static void getDepartamento() {
        System.out.print("Ingrese el nombre del departamento: ");
    }

    public static void getAmount() {
        System.out.print("Ingrese la cantidad: ");
    }

    public static void getDepId() {
        System.out.print("Ingrese el ID del departamento: ");
    }

    public static void getDepName() {
        System.out.print("Ingrese el nombre del departamento: ");
    }

    public static void getDepCode() {
        System.out.print("Ingrese el código del departamento: ");
    }

    public static void getLocalidad() {
        System.out.print("Ingrese la localidad: ");
    }

    public static void getEmpId() {
        System.out.print("Ingrese el ID del empleado: ");
    }

    public static void getProjId() {
        System.out.print("Ingrese el ID del proyecto: ");
    }

    public static void getOldDept() {
        System.out.print("Ingrese el nombre del departamento antiguo: ");
    }

    public static void getNewProy() {
        System.out.print("Ingrese el nombre del proyecto: ");
    }

    public static void getProyectoId() {
        System.out.print("Ingrese el ID del proyecto: ");
    }

    public static void getProyectoNombre() {
        System.out.print("Ingrese el nombre del proyecto: ");
    }

    public static void getProyectoUbicacion() {
        System.out.print("Ingrese la ubicación del proyecto: ");
    }

    public static void getProyectoDepId() {
        System.out.print("Ingrese el ID del departamento: ");
    }

    public static void getProjIdToDelete() {
        System.out.print("Ingrese el ID del proyecto a eliminar: ");
    }

    public static void getCalle() {
        System.out.print("Ingrese la calle: ");
    }

    public static void getNumero() {
        System.out.print("Ingrese el número: ");
    }

    public static void getPiso() {
        System.out.print("Ingrese el piso: ");
    }

    public static void getCp() {
        System.out.print("Ingrese el código postal: ");
    }

    public static void getProjIdToGet() {
        System.out.print("Ingrese el ID del proyecto a obtener: ");
    }

    public static void getNumProyectos() {
        System.out.print("Ingrese el número de proyectos: ");
    }
}