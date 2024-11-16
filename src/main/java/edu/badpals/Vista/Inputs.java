package edu.badpals.Vista;

import java.util.Scanner;

public class Inputs {
    Scanner scanner = new Scanner(System.in);

    public int getOptionMenu() {
        Prints.menu();
        int option = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println();
        return option;
    }

    public int getOptionMenuMetadatos() {
        Prints.menuMetadatos();
        int option = scanner.nextInt();
        scanner.nextLine();// Consume newline
        System.out.println();
        return option;
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
}