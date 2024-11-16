package edu.badpals.Vista;

import edu.badpals.modelo.Proyecto;

import java.util.Scanner;

public class Inputs {
    Scanner scanner = new Scanner(System.in);

    public int getOptionMenu() {
        Prints.menu();
        return getIntInput();
    }

    public int getOptionMenuMetadatos() {
        Prints.menuMetadatos();
        return getIntInput();
    }

    public String getEsquema() {
        Prints.getEsquema();
        String option = scanner.nextLine();
        System.out.println();
        return option;
    }

    public String getTabla() {
        Prints.getTabla();
        String option = scanner.nextLine();
        System.out.println();
        return option;
    }

    public String getResoultSet() {
        Prints.getResoultSet();
        String option = scanner.nextLine();
        System.out.println();
        return option;
    }

    public String getDepartamento() {
        Prints.getDepartamento();
        return scanner.next();
    }

    public int getAmount() {
        Prints.getAmount();
        return getIntInput();
    }

    public int getDepId() {
        Prints.getDepId();
        return getIntInput();
    }

    public String getDepName() {
        Prints.getDepName();
        return scanner.next();
    }

    public String getDepCode() {
        Prints.getDepCode();
        return scanner.next();
    }

    public String getLocalidad() {
        Prints.getLocalidad();
        return scanner.next();
    }

    public String getEmpId() {
        Prints.getEmpId();
        return scanner.next();
    }

    public int getProjId() {
        Prints.getProjId();
        return getIntInput();
    }

    public String getOldDept() {
        Prints.getOldDept();
        return scanner.next();
    }

    public String getNewProy() {
        Prints.getNewProy();
        return scanner.next();
    }

    public Proyecto getProyecto() {
        Prints.getProyectoId();
        int id = getIntInput();
        Prints.getProyectoNombre();
        String nombre = scanner.next();
        Prints.getProyectoUbicacion();
        String ubicacion = scanner.next();
        Prints.getProyectoDepId();
        int depId = getIntInput();
        return new Proyecto(id, nombre, ubicacion, depId);
    }

    public int getProjIdToDelete() {
        Prints.getProjIdToDelete();
        return getIntInput();
    }

    public String getCalle() {
        Prints.getCalle();
        return scanner.next();
    }

    public int getNumero() {
        Prints.getNumero();
        return getIntInput();
    }

    public String getPiso() {
        Prints.getPiso();
        return scanner.next();
    }

    public String getCp() {
        Prints.getCp();
        return scanner.next();
    }

    public int getProjIdToGet() {
        Prints.getProjIdToGet();
        return getIntInput();
    }

    public int getNumProyectos() {
        Prints.getNumProyectos();
        return getIntInput();
    }

    private int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.print("Por favor, ingrese un número válido: ");
            scanner.next(); // Consume the invalid input
        }
        int input = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        return input;
    }
}