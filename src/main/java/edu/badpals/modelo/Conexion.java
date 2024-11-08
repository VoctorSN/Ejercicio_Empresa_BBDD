package edu.badpals.modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
    public static Connection conectar(){
       try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "a23victorsn", "renaido");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static int subirSalarioDepartamento(Connection c, int subida, String nombreDep){
        try {

            Statement statementDep = c.createStatement();
            String stringSQLDep =
                            " SELECT NUMERO" +
                            " FROM DEPARTAMENTOS" +
                            " WHERE NOMBRE = '" + nombreDep + "'";
            ResultSet rs = statementDep.executeQuery(stringSQLDep);
            rs.next();

            int numeroDep = rs.getInt(1);

            Statement s = c.createStatement();
            String stringSQLSalario =
                    " UPDATE EMPLEADOS" +
                    " SET SALARIO = SALARIO + '" + subida + "'" +
                    " WHERE DEPARTAMENTO = '" + numeroDep + "'";
            int rowsUpdated = s.executeUpdate(stringSQLSalario);
            s.close();
            return rowsUpdated;


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static List<List> leerEmpleadosPorLocalidad(Connection c, String localidad){
        try {
            List<List> out = new ArrayList<>();

            Statement s = c.createStatement();
            String stringSQLEmpleado = "" +
                    " SELECT NOMBRE,APELLIDO1,APELLIDO2,LOCALIDAD,SALARIO,FECHA_NACIMIENTO,SUPERVISOR,DEPARTAMENTO" +
                    " FROM EMPLEADOS " +
                    " WHERE LOCALIDAD = '" + localidad + "' ";
            ResultSet rs = s.executeQuery(stringSQLEmpleado);


            while(rs.next()) {
                List<String> empleado = new ArrayList<>();

                empleado.add(rs.getString(1));
                empleado.add(rs.getString(2));
                empleado.add(rs.getString(3));
                empleado.add(rs.getString(4));
                empleado.add(String.valueOf(rs.getInt(5)));
                empleado.add(String.valueOf(rs.getDate(6)));

                String nss = rs.getString(7);
                if (nss != null){
                    String stringSQLSupervisor = "" +
                            " SELECT NOMBRE " +
                            " FROM EMPLEADOS " +
                            " WHERE NSS = '" + nss + "' ";
                    Statement statementNSS = c.createStatement();
                    ResultSet resultSetNSS = statementNSS.executeQuery(stringSQLSupervisor);

                    resultSetNSS.next();
                    empleado.add(resultSetNSS.getString(1));
                } else{
                    empleado.add(null);
                }

                String dep = rs.getString(8);
                String stringSQLDepartamento = "" +
                        " SELECT NOMBRE " +
                        " FROM DEPARTAMENTOS" +
                        " WHERE NUMERO = '" + dep + "' ";
                Statement statementDep = c.createStatement();
                ResultSet resultSetDep = statementDep.executeQuery(stringSQLDepartamento);

                resultSetDep.next();
                empleado.add(resultSetDep.getString(1));


                out.add(empleado);
            }
            return out;


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}