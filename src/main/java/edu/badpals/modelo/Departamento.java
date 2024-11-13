package edu.badpals.modelo;

import java.sql.Date;

public class Departamento {
    private int numero;
    private String nombre;
    private String nss;
    private Date fecha;

    public Departamento() {
    }

    public Departamento(int numDepartamento, String nomeDepartamento, String NSSdirige, Date dataDireccion) {
        this.numero = numDepartamento;
        this.nombre = nomeDepartamento;
        this.nss = NSSdirige;
        this.fecha = dataDireccion;
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

    public String getNss() {
        return nss;
    }

    public void setNss(String nss) {
        this.nss = nss;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Departamento {" +
                "Número: " + numero +
                ", Nombre: '" + nombre + '\'' +
                ", NSS: '" + nss + '\'' +
                ", Fecha: " + fecha +
                '}';
    }
}