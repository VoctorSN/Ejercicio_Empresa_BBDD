package edu.badpals.modelo;

import java.sql.Date;

public class Empleado {
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String nss;
    private String calle;
    private int numero_calle;
    private String piso;
    private String CP;
    private String localidad;
    private Date fecha_nacimiento;
    private float salario;
    private String sexo;
    private String supervisor;
    private int departamento;

    public Empleado(String nombre, String apellido1, String apellido2, String nss, String calle, int numero_calle, String piso, String CP, String localidad, Date fecha_nacimiento, float salario, String sexo, String supervisor, int departamento) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.nss = nss;
        this.calle = calle;
        this.numero_calle = numero_calle;
        this.piso = piso;
        this.CP = CP;
        this.localidad = localidad;
        this.fecha_nacimiento = fecha_nacimiento;
        this.salario = salario;
        this.sexo = sexo;
        this.supervisor = supervisor;
        this.departamento = departamento;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getNss() {
        return nss;
    }

    public void setNss(String nss) {
        this.nss = nss;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero_calle() {
        return numero_calle;
    }

    public void setNumero_calle(int numero_calle) {
        this.numero_calle = numero_calle;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getCP() {
        return CP;
    }

    public void setCP(String CP) {
        this.CP = CP;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public int getDepartamento() {
        return departamento;
    }

    public void setDepartamento(int departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "nombre='" + nombre + '\'' +
                ", apellido1='" + apellido1 + '\'' +
                ", apellido2='" + apellido2 + '\'' +
                ", nss='" + nss + '\'' +
                ", calle='" + calle + '\'' +
                ", numero_calle=" + numero_calle +
                ", piso='" + piso + '\'' +
                ", CP='" + CP + '\'' +
                ", localidad='" + localidad + '\'' +
                ", fecha_nacimiento=" + fecha_nacimiento +
                ", salario=" + salario +
                ", sexo='" + sexo + '\'' +
                ", supervisor='" + supervisor + '\'' +
                ", departamento=" + departamento +
                '}';
    }
}
