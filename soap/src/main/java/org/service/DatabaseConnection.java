package org.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/hospital";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private DatabaseConnection() { }

    public static Connection getConnection() {
        Connection connection = null;
        if (connection == null) {
            try {
                // Cargar el driver (necesario en algunos entornos)
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("¡Conexión exitosa a la base de datos!");
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                throw new RuntimeException("Error al conectar con la base de datos", e);
            }
        }
        return connection;
    }
}

