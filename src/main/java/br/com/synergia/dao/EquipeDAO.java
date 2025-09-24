package br.com.synergia.dao;

import br.com.synergia.model.Equipe;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipeDAO {

    public void inserir(Equipe equipe) {
        String sql = "INSERT INTO equipes (nome, descricao) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, equipe.getNome());
            stmt.setString(2, equipe.getDescricao());
            stmt.executeUpdate();
            System.out.println("✅ Equipe inserida com sucesso!");
        } catch (SQLException e) {
            System.err.println("❌ Erro ao inserir equipe: " + e.getMessage());
        }
    }

    public List<Equipe> listar() {
        List<Equipe> equipes = new ArrayList<>();
        String sql = "SELECT * FROM equipes";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Equipe equipe = new Equipe(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("descricao")
                );
                equipes.add(equipe);
            }
        } catch (SQLException e) {
            System.err.println("❌ Erro ao listar equipes: " + e.getMessage());
        }
        return equipes;
    }

    public void atualizar(Equipe equipe) {
        String sql = "UPDATE equipes SET nome = ?, descricao = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, equipe.getNome());
            stmt.setString(2, equipe.getDescricao());
            stmt.setInt(3, equipe.getId());
            stmt.executeUpdate();
            System.out.println("✅ Equipe atualizada com sucesso!");
        } catch (SQLException e) {
            System.err.println("❌ Erro ao atualizar equipe: " + e.getMessage());
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM equipes WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("✅ Equipe deletada com sucesso!");
        } catch (SQLException e) {
            System.err.println("❌ Erro ao deletar equipe: " + e.getMessage());
        }
    }
}
