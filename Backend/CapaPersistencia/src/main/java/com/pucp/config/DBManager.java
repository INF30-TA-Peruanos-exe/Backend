package com.pucp.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBManager {
    private static DBManager instance;
    private HikariDataSource dataSource;
    
//    private String jdbcUrl;
//    private String username;
//    private String password;

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
            
            HikariConfig config = new HikariConfig();
            String dbType = properties.getProperty("db.type");
            config.setJdbcUrl(properties.getProperty(dbType + ".jdbcUrl"));
            config.setUsername(properties.getProperty(dbType + ".username"));
            config.setPassword(properties.getProperty(dbType + ".password"));

            // Configuración del pool
            config.setMaximumPoolSize(Integer.parseInt(properties.getProperty("db.maxPoolSize")));
            config.setMinimumIdle(Integer.parseInt(properties.getProperty("db.minIdle")));  
            config.setIdleTimeout(Integer.parseInt(properties.getProperty("db.idleTimeout")));
            config.setConnectionTimeout(Integer.parseInt(properties.getProperty("db.connectionTimeout")));
//            this.jdbcUrl = properties.getProperty(dbType + ".jdbcUrl");
//            this.username = properties.getProperty(dbType + ".username");
//            this.password = properties.getProperty(dbType + ".password");
                    // Configuraciones específicas según el tipo de base de datos
            if ("mysql".equals(dbType)) {
                config.addDataSourceProperty("cachePrepStmts", "true");
                config.addDataSourceProperty("prepStmtCacheSize", "250");
                config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
            }
            config.setDriverClassName("com.mysql.cj.jdbc.Driver");
            dataSource = new HikariDataSource(config);
        } catch (IOException e) {
            throw new RuntimeException("Error al cargar configuración de base de datos", e);
        }
    }

    public Connection obtenerConexion() throws SQLException { 
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            return DriverManager.getConnection(jdbcUrl, username, password);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException("Driver JDBC no encontrado", e);
//        }
        return dataSource.getConnection();
    }
    public void cerrarPool() {
        if (dataSource != null) {
            dataSource.close();
        }
    }
}
