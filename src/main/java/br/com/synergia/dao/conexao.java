package br.com.synergia.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexao {
    private static final String URL = "jdbc:mysql://localhost:3306/synergia";
    private static final String USER = "root"; // altere se for outro usuário
    private static final String PASSWORD = "SUA_SENHA"; // troque pela senha real

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            System.out.println("✅ Conexão bem-sucedida com o banco Synergia!");
        } catch (SQLException e) {
            System.err.println("❌ Erro de conexão: " + e.getMessage());
        }
    }
}
