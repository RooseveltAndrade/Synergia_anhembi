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

    public List<Equipe> listarTodos() {
        List<Equipe> lista = new ArrayList<>();
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
                lista.add(equipe);
            }
        } catch (SQLException e) {
            System.err.println("❌ Erro ao listar equipes: " + e.getMessage());
        }
        return lista;
    }

    public void excluir(int id) {
        String sql = "DELETE FROM equipes WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("✅ Equipe excluída com sucesso!");
        } catch (SQLException e) {
            System.err.println("❌ Erro ao excluir equipe: " + e.getMessage());
        }
    }
}
