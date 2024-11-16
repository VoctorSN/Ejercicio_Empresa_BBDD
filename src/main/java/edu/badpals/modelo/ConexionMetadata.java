package edu.badpals.modelo;

import java.sql.*;

public class ConexionMetadata {
    public static DatabaseMetaData getMetadata(Connection c) {
        try {
            return c.getMetaData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void mostrarInformacion(DatabaseMetaData metaData) {
        try {
            System.out.println("======= INFO DE LA BBDD ========");
            System.out.println("Nombre del SXBD: " + metaData.getDatabaseProductName());
            System.out.println("Número de versión del SXBD: " + metaData.getDatabaseProductVersion());
            System.out.println("Número de versión principal del SXBD: " + metaData.getDatabaseMajorVersion());
            System.out.println("Número de versión secundario del SXBD: " + metaData.getDatabaseMinorVersion());
            System.out.println("Nombre del conectador JDBC utilizado: " + metaData.getDriverName());
            System.out.println("Número de versión principal del conectador JDBC: " + metaData.getJDBCMajorVersion());
            System.out.println("Número de versión secundaria del conectador JDBC: " + metaData.getJDBCMinorVersion());
            System.out.println("Número de versión del conectador JDBC utilizado: " + metaData.getDriverVersion());
            System.out.println("URL de la base de datos: " + metaData.getURL());
            System.out.println("Nombre del usuario actual conectado a la base de datos: " + metaData.getUserName());
            System.out.println("¿La base de datos es de solo lectura?: " + metaData.isReadOnly() + "\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void mostrarTablasUsuario(DatabaseMetaData metaData) {
        try {
            System.out.println("======= INFO DE LAS TABLAS ========");
            ResultSet rs = metaData.getTables("empresa", null, "%", new String[]{"TABLE"});
            while (rs.next()) {
                System.out.println("Tabla: " + rs.getString("TABLE_NAME") + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void mostrarColumnasTabla(DatabaseMetaData metaData, String esquema, String tabla) {
        try {
            ResultSet rs = metaData.getColumns(null, esquema, tabla, null);
            System.out.println("======= INFO DE LA TABLA " + tabla + " DEL ESQUEMA " + esquema + " ========");
            while (rs.next()) {
                System.out.println("Columna: " + rs.getString("COLUMN_NAME"));
                System.out.println("Tipo de datos: " + rs.getString("TYPE_NAME"));
                System.out.println("Tamaño: " + rs.getInt("COLUMN_SIZE"));
                System.out.println("¿Admite nulos?: " + rs.getString("IS_NULLABLE") + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void mostrarProcedimientos(DatabaseMetaData metaData) {
        try {
            ResultSet rs = metaData.getProcedures("empresa", null, "%");
            System.out.println("======= PROCEDIMIENTOS DE LA TABLA EMPRESA ========");
            while (rs.next()) {
                System.out.println("Procedimiento: " + rs.getString("PROCEDURE_NAME") + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void mostrarClavePrimaria(DatabaseMetaData metaData, String esquema, String tabla) {
        try {
            ResultSet rs = metaData.getPrimaryKeys(esquema, null, tabla);
            System.out.println("======= CLAVES PRIMARIAS DE LA TABLA " + tabla + " DEL ESQUEMA " + esquema + " ========");
            while (rs.next()) {
                System.out.println("Columna de clave primaria: " + rs.getString("COLUMN_NAME") + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void mostrarClaveForanea(DatabaseMetaData metaData, String esquema, String tabla) {
        try {
            ResultSet rs = metaData.getImportedKeys(null, esquema, tabla);
            System.out.println("======= CLAVES FORANEAS DE LA TABLA " + tabla + " DEL ESQUEMA " + esquema + " ========");
            while (rs.next()) {
                System.out.println("Columna de clave foránea: " + rs.getString("FKCOLUMN_NAME"));
                System.out.println("Referencia a tabla: " + rs.getString("PKTABLE_NAME"));
                System.out.println("Referencia a columna: " + rs.getString("PKCOLUMN_NAME") + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void mostrarInformacionAdicional(DatabaseMetaData metaData) {
        try {
            System.out.println("======= FUNCIONES DISPONIBLES ========");
            System.out.println("Funciones de cadena: " + metaData.getStringFunctions());
            System.out.println("Funciones de fecha y hora: " + metaData.getTimeDateFunctions());
            System.out.println("Funciones matemáticas: " + metaData.getNumericFunctions());
            System.out.println("Funciones de sistema: " + metaData.getSystemFunctions());

            System.out.println("\n======= PALABRAS RESERVADAS ========");
            System.out.println(metaData.getSQLKeywords());

            System.out.println("\n======= DELIMITADORES DE IDENTIFICADORES ========");
            System.out.println("Cadena utilizada para delimitar identificadores: " + metaData.getIdentifierQuoteString());

            System.out.println("\n======= CADENA DE ESCAPE DE CARACTERES COMODÍN ========");
            System.out.println("Cadena de escape de caracteres comodín: " + metaData.getSearchStringEscape());

            System.out.println("\n======= PERMISOS DEL USUARIO ========");
            System.out.println("¿El usuario puede llamar a todos los procedimientos?: " + metaData.allProceduresAreCallable());
            System.out.println("¿El usuario puede acceder a todas las tablas?: " + metaData.allTablesAreSelectable() + "\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void mostrarLimitesConectador(DatabaseMetaData metaData) {
        try {
            System.out.println("======= LÍMITES IMPOSTOS POLO CONECTADOR ========");
            System.out.println("Número de conexións simultáneas: " + metaData.getMaxConnections());
            System.out.println("Número máximo de sentenzas simultáneas: " + metaData.getMaxStatements());
            System.out.println("Número máximo de táboas nunha consulta SELECT: " + metaData.getMaxTablesInSelect());
            System.out.println("Lonxitude máxima do nome dunha táboa: " + metaData.getMaxTableNameLength());
            System.out.println("Lonxitude máxima do nome dunha columna: " + metaData.getMaxColumnNameLength());
            System.out.println("Lonxitude máxima dunha sentenza SQL: " + metaData.getMaxStatementLength());
            System.out.println("Lonxitude máxima dunha fila: " + metaData.getMaxRowSize());
            System.out.println("Lonxitude máxima do nome dun procedemento: " + metaData.getMaxProcedureNameLength());
            System.out.println("Número máximo de columnas nunha cláusula ORDER BY: " + metaData.getMaxColumnsInOrderBy());
            System.out.println("Número máximo de columnas nunha cláusula SELECT: " + metaData.getMaxColumnsInSelect());
            System.out.println("Número máximo de columnas nunha cláusula GROUP BY: " + metaData.getMaxColumnsInGroupBy() + "\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void mostrarInformacionTransacciones(DatabaseMetaData metaData) {
        try {
            System.out.println("======= INFORMACIÓN SOBRE TRANSACCIÓNS ========");
            System.out.println("¿Soporta transaccións?: " + metaData.supportsTransactions());
            System.out.println("Nivel de illamento de transaccións predeterminado: " + metaData.getDefaultTransactionIsolation());
            System.out.println("¿Soporta sentenzas de manipulación de datos dentro das transaccións?: " + metaData.supportsDataManipulationTransactionsOnly());
            System.out.println("¿Soporta sentenzas de definición de datos dentro das transaccións?: " + metaData.supportsDataDefinitionAndDataManipulationTransactions() + "\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void mostrarSoporteCaracteristicas(DatabaseMetaData metaData) {
        try {
            System.out.println("======= SOPORTE DE CARACTERÍSTICAS ========");
            System.out.println("¿ALTER TABLE soporta ADD COLUMN?: " + metaData.supportsAlterTableWithAddColumn());
            System.out.println("¿ALTER TABLE soporta DROP COLUMN?: " + metaData.supportsAlterTableWithDropColumn());
            System.out.println("¿Alias de columnas soportan a palabra AS?: " + metaData.supportsColumnAliasing());
            System.out.println("¿Concatenar NULL e NOT NULL resulta en NULL?: " + metaData.nullPlusNonNullIsNull());
            System.out.println("¿Soporta conversións entre tipos de datos JDBC?: " + metaData.supportsConvert());
            System.out.println("¿Soporta nomes de táboas correlacionadas?: " + metaData.supportsCorrelatedSubqueries());
            System.out.println("¿Soporta uso de columna non na SELECT en ORDER BY?: " + metaData.supportsOrderByUnrelated());
            System.out.println("¿Soporta cláusula GROUP BY?: " + metaData.supportsGroupBy());
            System.out.println("¿Soporta uso de columna non na SELECT en GROUP BY?: " + metaData.supportsGroupByUnrelated());
            System.out.println("¿Soporta cláusulas LIKE?: " + metaData.supportsLikeEscapeClause());
            System.out.println("¿Soporta outer joins?: " + metaData.supportsOuterJoins());
            System.out.println("¿Soporta subconsultas EXISTS?: " + metaData.supportsSubqueriesInExists());
            System.out.println("¿Soporta subconsultas en expresións de comparación?: " + metaData.supportsSubqueriesInComparisons());
            System.out.println("¿Soporta subconsultas en expresións IN?: " + metaData.supportsSubqueriesInIns());
            System.out.println("¿Soporta subconsultas en expresións cuantificadas?: " + metaData.supportsSubqueriesInQuantifieds() + "\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void mostrarMetadataResultSet(Connection c, String consulta) {
        try (Statement stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery(consulta)) {

            ResultSetMetaData rsMetaData = rs.getMetaData();
            int columnCount = rsMetaData.getColumnCount();

            System.out.println("Número de columnas recuperadas: " + columnCount);

            for (int i = 1; i <= columnCount; i++) {
                System.out.println("Columna " + i + ":");
                System.out.println("Nombre: " + rsMetaData.getColumnName(i));
                System.out.println("Tipo: " + rsMetaData.getColumnTypeName(i));
                System.out.println("Tamaño: " + rsMetaData.getColumnDisplaySize(i));
                System.out.println("¿Admite nulos?: " + rsMetaData.isNullable(i) + "\n");
            }
            System.out.println();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}