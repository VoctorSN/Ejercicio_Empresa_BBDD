package edu.badpals.modelo;

public class Proyecto {
    private int numero;
    private String nombre;
    private String lugar;
    private int departamento;

    public Proyecto() {
    }

    public Proyecto(int numero, String nombre, String lugar, int departamento) {
        this.numero = numero;
        this.nombre = nombre;
        this.lugar = lugar;
        this.departamento = departamento;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public int getDepartamento() {
        return departamento;
    }

    public void setDepartamento(int departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return "Proyecto{" +
                "numero=" + numero +
                ", nombre='" + nombre + '\'' +
                ", lugar='" + lugar + '\'' +
                ", departamento=" + departamento +
                '}';
    }
}