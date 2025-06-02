package com.pucp.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBManager {
    private static DBManager instance;

    private String jdbcUrl;
    private String username;
    private String password;

    private DBManager() {
        cargarConfiguracion();
    }

    public static synchronized DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    private void cargarConfiguracion() {
        Properties properties = new Properties();
        String propertiesFile = "db.properties";

        try (InputStream input = getClass().getClassLoader().getResourceAsStream(propertiesFile)) {
            if (input == null) {
                throw new IOException("No se pudo encontrar el archivo: " + propertiesFile);
            }

            properties.load(input);

            String dbType = properties.getProperty("db.type");
            this.jdbcUrl = properties.getProperty(dbType + ".jdbcUrl");
            this.username = properties.getProperty(dbType + ".username");
            this.password = properties.getProperty(dbType + ".password");

        } catch (IOException e) {
            throw new RuntimeException("Error al cargar configuración de base de datos", e);
        }
    }

    public Connection obtenerConexion() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(jdbcUrl, username, password);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver JDBC no encontrado", e);
        }
    }
}
