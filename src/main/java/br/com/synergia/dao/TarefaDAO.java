package br.com.synergia.dao;

import br.com.synergia.model.Tarefa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TarefaDAO {

    public void inserir(Tarefa tarefa) {
        String sql = "INSERT INTO tarefas (titulo, descricao, projeto_id, responsavel_id, status, data_inicio_prevista, data_fim_prevista) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tarefa.getTitulo());
            stmt.setString(2, tarefa.getDescricao());
            stmt.setInt(3, tarefa.getProjetoId());
            stmt.setInt(4, tarefa.getResponsavelId());
            stmt.setString(5, tarefa.getStatus());
            stmt.setDate(6, Date.valueOf(tarefa.getDataInicioPrevista()));
            stmt.setDate(7, Date.valueOf(tarefa.getDataFimPrevista()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("❌ Erro ao inserir tarefa: " + e.getMessage());
        }
    }

    public List<Tarefa> listar() {
        List<Tarefa> tarefas = new ArrayList<>();
        String sql = "SELECT * FROM tarefas";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                tarefas.add(new Tarefa(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("descricao"),
                        rs.getInt("projeto_id"),
                        rs.getInt("responsavel_id"),
                        rs.getString("status"),
                        rs.getDate("data_inicio_prevista").toLocalDate(),
                        rs.getDate("data_fim_prevista").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            System.err.println("❌ Erro ao listar tarefas: " + e.getMessage());
        }
        return tarefas;
    }

    public void atualizar(Tarefa tarefa) {
        String sql = "UPDATE tarefas SET titulo=?, descricao=?, projeto_id=?, responsavel_id=?, status=?, data_inicio_prevista=?, data_fim_prevista=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tarefa.getTitulo());
            stmt.setString(2, tarefa.getDescricao());
            stmt.setInt(3, tarefa.getProjetoId());
            stmt.setInt(4, tarefa.getResponsavelId());
            stmt.setString(5, tarefa.getStatus());
            stmt.setDate(6, Date.valueOf(tarefa.getDataInicioPrevista()));
            stmt.setDate(7, Date.valueOf(tarefa.getDataFimPrevista()));
            stmt.setInt(8, tarefa.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("❌ Erro ao atualizar tarefa: " + e.getMessage());
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM tarefas WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("❌ Erro ao deletar tarefa: " + e.getMessage());
        }
    }
}