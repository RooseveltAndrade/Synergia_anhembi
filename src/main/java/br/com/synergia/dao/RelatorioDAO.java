package br.com.synergia.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RelatorioDAO {

    public List<String> resumoProjetosPorStatus() {
        String sql = "SELECT status, COUNT(*) as total FROM projetos GROUP BY status";
        List<String> resultados = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                resultados.add(rs.getString("status") + ": " + rs.getInt("total"));
            }
        } catch (SQLException e) {
            resultados.add("Erro: " + e.getMessage());
        }
        return resultados;
    }

    public List<String> desempenhoColaboradores() {
        String sql = "SELECT u.nome, COUNT(t.id) as total, " +
                "SUM(CASE WHEN t.status = 'CONCLUIDA' THEN 1 ELSE 0 END) as concluidas " +
                "FROM usuarios u " +
                "LEFT JOIN tarefas t ON u.id = t.responsavel_id " +
                "GROUP BY u.nome";
        List<String> resultados = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                resultados.add(rs.getString("nome") +
                        " → Total: " + rs.getInt("total") +
                        ", Concluídas: " + rs.getInt("concluidas"));
            }
        } catch (SQLException e) {
            resultados.add("Erro: " + e.getMessage());
        }
        return resultados;
    }

    public List<String> projetosAtrasados() {
        String sql = "SELECT nome, data_fim_prevista " +
                "FROM projetos " +
                "WHERE data_fim_prevista < CURDATE() AND status != 'CONCLUIDO'";
        List<String> resultados = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                resultados.add("Projeto: " + rs.getString("nome") +
                        " | Prazo: " + rs.getDate("data_fim_prevista"));
            }
        } catch (SQLException e) {
            resultados.add("Erro: " + e.getMessage());
        }
        return resultados;
    }
}