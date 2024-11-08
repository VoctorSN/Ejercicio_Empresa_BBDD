package edu.badpals.modelo;

public class EmpleadoProyecto {
    private String empleado;
    private int proyecto;
    private int horas;

    public EmpleadoProyecto(String empleado, int proyecto, int horas) {
        this.empleado = empleado;
        this.proyecto = proyecto;
        this.horas = horas;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public int getProyecto() {
        return proyecto;
    }

    public void setProyecto(int proyecto) {
        this.proyecto = proyecto;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    @Override
    public String toString() {
        return "EmpleadoProyecto{" +
                "empleado='" + empleado + '\'' +
                ", proyecto=" + proyecto +
                ", horas=" + horas +
                '}';
    }
}
