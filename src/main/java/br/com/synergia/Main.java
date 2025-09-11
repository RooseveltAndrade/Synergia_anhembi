package br.com.synergia;

import br.com.synergia.dao.DatabaseConnection;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            System.out.println("✅ Conexão bem-sucedida com o MySQL!");
        } catch (Exception e) {
            System.out.println("❌ Falha na conexão: " + e.getMessage());
        }
    }
}
