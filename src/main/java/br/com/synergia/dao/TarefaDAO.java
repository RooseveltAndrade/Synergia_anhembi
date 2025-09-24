package br.com.synergia.dao;

import br.com.synergia.model.Tarefa;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TarefaDAO {

    public void inserir(Tarefa tarefa) {
        String sql = "INSERT INTO tarefas (titulo, descricao, projeto_id, responsavel_id, status, data_inicio, data_fim_prevista) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tarefa.getTitulo());
            stmt.setString(2, tarefa.getDescricao());
            stmt.setInt(3, tarefa.getProjetoId());
            stmt.setInt(4, tarefa.getResponsavelId());
            stmt.setString(5, tarefa.getStatus());
            stmt.setDate(6, tarefa.getDataInicio());
            stmt.setDate(7, tarefa.getDataFimPrevista());
            stmt.executeUpdate();
            System.out.println("✅ Tarefa inserida com sucesso!");
        } catch (SQLException e) {
            System.err.println("❌ Erro ao inserir tarefa: " + e.getMessage());
        }
    }

    public List<Tarefa> listarTodos() {
        List<Tarefa> lista = new ArrayList<>();
        String sql = "SELECT * FROM tarefas";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Tarefa tarefa = new Tarefa(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("descricao"),
                        rs.getInt("projeto_id"),
                        rs.getInt("responsavel_id"),
                        rs.getString("status"),
                        rs.getDate("data_inicio"),
                        rs.getDate("data_fim_prevista"),
                        rs.getDate("data_fim_real")
                );
                lista.add(tarefa);
            }
        } catch (SQLException e) {
            System.err.println("❌ Erro ao listar tarefas: " + e.getMessage());
        }
        return lista;
    }

    public void excluir(int id) {
        String sql = "DELETE FROM tarefas WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("✅ Tarefa excluída com sucesso!");
        } catch (SQLException e) {
            System.err.println("❌ Erro ao excluir tarefa: " + e.getMessage());
        }
    }
}
