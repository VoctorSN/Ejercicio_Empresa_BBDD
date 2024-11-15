package edu.badpals.modelo;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
    public static Connection conectar() {
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

    public static int subirSalarioDepartamento(Connection c, int subida, String nombreDep) {
        try {

            Statement statementDep = c.createStatement();
            String stringSQLDep =
                    " SELECT NUMERO" +
                            " FROM DEPARTAMENTOS" +
                            " WHERE NOMBRE = '" + nombreDep + "'";
            ResultSet rs = statementDep.executeQuery(stringSQLDep);
            if (!rs.next()) return 0;

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

    public static boolean insertarDep(Connection c, int numero, String nombre, String nss) {
        try {
            Statement s = c.createStatement();
            LocalDate fecha = LocalDate.now();
            String stringSQLDep =
                    " INSERT INTO DEPARTAMENTOS (NUMERO, NOMBRE, NSS, FECHA) VALUES ('" + numero + "', '" + nombre + "', '" + nss + "', '" + fecha + "')";
            boolean ejecutado = s.execute(stringSQLDep);
            s.close();
            return ejecutado;


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<List<String>> leerEmpleadosPorLocalidad(Connection c, String localidad) {
        try {
            List<List<String>> out = new ArrayList<>();

            Statement s = c.createStatement();
            String stringSQLEmpleado = " SELECT NOMBRE,APELLIDO1,APELLIDO2,LOCALIDAD,SALARIO,FECHA_NACIMIENTO,SUPERVISOR,DEPARTAMENTO" +
                    " FROM EMPLEADOS " +
                    " WHERE LOCALIDAD = '" + localidad + "' ";
            ResultSet rs = s.executeQuery(stringSQLEmpleado);


            while (rs.next()) {
                List<String> empleado = new ArrayList<>();

                empleado.add(rs.getString(1));
                empleado.add(rs.getString(2));
                empleado.add(rs.getString(3));
                empleado.add(rs.getString(4));
                empleado.add(String.valueOf(rs.getInt(5)));
                empleado.add(String.valueOf(rs.getDate(6)));

                String nss = rs.getString(7);
                if (nss != null) {
                    String stringSQLSupervisor = " SELECT NOMBRE " +
                            " FROM EMPLEADOS " +
                            " WHERE NSS = '" + nss + "' ";
                    Statement statementNSS = c.createStatement();
                    ResultSet resultSetNSS = statementNSS.executeQuery(stringSQLSupervisor);

                    resultSetNSS.next();
                    empleado.add(resultSetNSS.getString(1));
                } else {
                    empleado.add(null);
                }

                String dep = rs.getString(8);
                String stringSQLDepartamento = " SELECT NOMBRE " +
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

    public static int eliminarEmpleadoProyecto(Connection c, String nss, int proyecto) {
        try {
            Statement s = c.createStatement();
            String stringSQLEP = "DELETE FROM EMPLEADOS_PROYECTOS WHERE EMPLEADO = '" + nss + "' AND PROYECTO = '" + proyecto + "'";
            return s.executeUpdate(stringSQLEP);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int cambiarDepProy(Connection c, String dep, String proy) {
        try {

            Integer depKey = getDepByNom(c, dep);
            if (depKey == null) return 0;

            String stringSQLEP = "UPDATE PROYECTOS SET DEPARTAMENTO = ? WHERE NOMBRE = ?";
            PreparedStatement psProy = c.prepareStatement(stringSQLEP);
            psProy.setInt(1, depKey);
            psProy.setString(2, proy);

            return psProy.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    private static Integer getDepByNom(Connection c, String dep) throws SQLException {
        String stringSQLGetDep = "SELECT * FROM DEPARTAMENTOS WHERE NOMBRE = ?";
        PreparedStatement psDep = c.prepareStatement(stringSQLGetDep);
        psDep.setString(1, dep);
        ResultSet rs = psDep.executeQuery();
        if (!rs.next()) {
            return null;
        }
        int depKey = rs.getInt(1);
        return depKey;
    }

    public static boolean insertarProy(Connection c, Proyecto proyecto) {
        try {
            int numero = proyecto.getNumero();
            String nombre = proyecto.getNombre();
            String lugar = proyecto.getLugar();
            int departamento = proyecto.getDepartamento();

            String stringSQLProy =
                    " INSERT INTO PROYECTOS (NUMERO, NOMBRE, LUGAR, DEPARTAMENTO)" +
                            " VALUES (?, ?, ?, ?)";
            PreparedStatement ps = c.prepareStatement(stringSQLProy);
            ps.setInt(1, numero);
            ps.setString(2, nombre);
            ps.setString(3, lugar);
            ps.setInt(4, departamento);

            boolean r = ps.execute();
            ps.close();
            return r;


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int eliminarProy(Connection c, int proyecto) {
        try {
            String stringSQLProy = "DELETE FROM PROYECTOS WHERE NUMERO = ?";
            PreparedStatement ps = c.prepareStatement(stringSQLProy);
            ps.setInt(1, proyecto);

            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 2;
    }

    public static List<Proyecto> getProyectosByDep(Connection c, String dep) {
        try {
            List<Proyecto> proyectos = new ArrayList<>();
            int depKey = getDepByNom(c, dep);
            String stringSQLProy = "SELECT * FROM PROYECTOS WHERE DEPARTAMENTO = ?";
            PreparedStatement ps = c.prepareStatement(stringSQLProy);
            ps.setInt(1, depKey);
            ResultSet rs = ps.executeQuery();
            Proyecto proyecto;
            while (rs.next()) {
                proyecto = new Proyecto(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4));
                proyectos.add(proyecto);
            }
            return proyectos;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean cambiarDomicilio(Connection c, String nss, String calle, int numero_calle, String piso, String CP, String localidad) {
        try {
            String stringSQLCall = "CALL pr_cambioDomicilio(?, ?, ?, ?, ?, ?)";
            CallableStatement cs = c.prepareCall(stringSQLCall);
            cs.setString(1, nss);
            cs.setString(2, calle);
            cs.setInt(3, numero_calle);
            cs.setString(4, piso);
            cs.setString(5, CP);
            cs.setString(6, localidad);
            return cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Proyecto getProy(Connection c, int numero) {
        try {
            String stringSQLCall = "CALL pr_DatosProxectos(?,?,?,?)";
            CallableStatement cs = c.prepareCall(stringSQLCall);
            cs.setInt(1, numero);
            cs.execute();
            if (cs.getString(2) == null) {
                return null;
            }


            Proyecto proyecto = new Proyecto();
            proyecto.setNombre(cs.getString(2));
            proyecto.setLugar(cs.getString(3));
            proyecto.setDepartamento(cs.getInt(4));
            proyecto.setNumero(numero);


            return proyecto;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Departamento> departControlaProxec(Connection c, int numeroProy) {
        try {
            List<Departamento> departamentos = new ArrayList<>();
            String stringSQLCall = "CALL pr_DepartControlaProxec(?)";
            CallableStatement cs = c.prepareCall(stringSQLCall);
            cs.setInt(1, numeroProy);
            cs.execute();
            ResultSet rs = cs.getResultSet();
            while (rs.next()) {
                Departamento dep = new Departamento();
                dep.setNumero(rs.getInt("NUMERO"));
                dep.setNombre(rs.getString("NOMBRE"));
                dep.setNss(rs.getString("NSS"));
                dep.setFecha(rs.getDate("FECHA"));
                departamentos.add(dep);
            }
            return departamentos;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int empDepart(Connection c, String dep) {
        try {
            String sentenciSQL = "SELECT fn_nEmpDepart(?)";
            PreparedStatement ps = c.prepareStatement(sentenciSQL);
            ps.setString(1, dep);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return -1;

    }

    //b) Crea un método que reciba como parámetro un obxecto proxecto
    // e insira os seus datos na táboa proxecto. O obxecto proxecto conten
    // os datos dun proxecto novo. A inserción do novo proxecto realizarase a
    // través dun ResultSet dinámico, xerado mediante unha consulta a todos os
    // datos da táboa proxectos. Para controlar os erros, tedes que implementar
    // os seguintes métodos:

    //– Método que devolva true si o número e o nome do proxecto novo existen no ResultSet e faise no caso contrario.

    //– Método que devolva true si o número de departamento existe na táboa departamento e false no caso contrario.

    public static int insertarProyCheck(Connection c, Proyecto py) {
        try {
            if (depExists(c, py.getDepartamento()) && !pyExists(c, py.getNumero(), py.getNombre())) {
                String stringSQL = "SELECT * FROM PROYECTOS";
                Statement ps = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = ps.executeQuery(stringSQL);
                rs.moveToInsertRow();
                rs.updateInt(1, py.getNumero());
                rs.updateString(2, py.getNombre());
                rs.updateString(3, py.getLugar());
                rs.updateInt(4, py.getDepartamento());
                rs.insertRow();
                return 1;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    public static boolean pyExists(Connection c, int numero, String nombre) {
        try {
            String stringSQL = "SELECT NUMERO,NOMBRE FROM PROYECTOS";
            PreparedStatement ps = c.prepareStatement(stringSQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt(1) == numero || Objects.equals(rs.getString(2), nombre)) return true;
            }
            return false;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public static boolean depExists(Connection c, int dep) {
        try {
            String stringSQL = "SELECT * FROM DEPARTAMENTOS WHERE NUMERO = ?";
            PreparedStatement ps = c.prepareStatement(stringSQL);
            ps.setInt(1, dep);
            ResultSet rs = ps.executeQuery();
            boolean res = rs.next();
            rs.close();
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;

    }

    public static void incrementarSalarioDep(Connection c, int salario, int numero) {
        try {
            String stringSQL = "SELECT * FROM EMPLEADOS WHERE DEPARTAMENTO = " + numero;
            Statement st = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery(stringSQL);
            while (rs.next()) {
                float nuevoSalario = rs.getFloat("SALARIO") + salario;
                rs.updateFloat("SALARIO", nuevoSalario);
                rs.updateRow();
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static ResultSet getEmpleadosNumProyectos(Connection c, int numProy) {
        try {
            String stringSQL = "SELECT NSS, CONCAT(NOMBRE,' ',APELLIDO1,' ',APELLIDO2) AS NOMBRE_COMPLETO, LOCALIDAD, SALARIO" +
                    " FROM EMPLEADOS " +
                    "WHERE NSS IN (SELECT EMPLEADO " +
                    "FROM EMPLEADOS_PROYECTOS " +
                    "GROUP BY EMPLEADO " +
                    "HAVING COUNT(*) > " + numProy +
                    ")";
            Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = s.executeQuery(stringSQL);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void verFilasRS(ResultSet rs) {
        try {
            rs.first();
            System.out.println(rs.getInt(1) + " " + rs.getString(2));
            System.out.println();

            rs.last();
            System.out.println(rs.getInt(1) + " " + rs.getString(2));
            System.out.println();

            rs.previous();
            System.out.println(rs.getInt(1) + " " + rs.getString(2));
            System.out.println();

            rs.last();
            System.out.println(rs.getInt(1) + " " + rs.getString(2));
            System.out.println();
            while (rs.previous()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2));
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}