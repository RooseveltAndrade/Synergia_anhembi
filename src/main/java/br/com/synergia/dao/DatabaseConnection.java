package br.com.synergia.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // Ajuste conforme seu banco criado no MySQL
    private static final String URL = "jdbc:mysql://localhost:3306/synergia_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "synergia_user";      // seu usuário do MySQL
    private static final String PASSWORD = "123456";  // senha do MySQL

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
