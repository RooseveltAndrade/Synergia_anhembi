package br.com.synergia.dao;

import br.com.synergia.model.Projeto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjetoDAO {

    public void inserir(Projeto projeto) {
        String sql = "INSERT INTO projetos (nome, descricao, data_inicio, data_fim_prevista, status, gerente_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, projeto.getNome());
            stmt.setString(2, projeto.getDescricao());
            stmt.setDate(3, projeto.getDataInicio());
            stmt.setDate(4, projeto.getDataFimPrevista());
            stmt.setString(5, projeto.getStatus());
            stmt.setInt(6, projeto.getGerenteId());
            stmt.executeUpdate();
            System.out.println("✅ Projeto inserido com sucesso!");
        } catch (SQLException e) {
            System.err.println("❌ Erro ao inserir projeto: " + e.getMessage());
        }
    }

    public List<Projeto> listarTodos() {
        List<Projeto> lista = new ArrayList<>();
        String sql = "SELECT * FROM projetos";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Projeto p = new Projeto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getDate("data_inicio"),
                        rs.getDate("data_fim_prevista"),
                        rs.getString("status"),
                        rs.getInt("gerente_id")
                );
                lista.add(p);
            }
        } catch (SQLException e) {
            System.err.println("❌ Erro ao listar projetos: " + e.getMessage());
        }
        return lista;
    }

    public void excluir(int id) {
        String sql = "DELETE FROM projetos WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("✅ Projeto excluído com sucesso!");
        } catch (SQLException e) {
            System.err.println("❌ Erro ao excluir projeto: " + e.getMessage());
        }
    }
}
