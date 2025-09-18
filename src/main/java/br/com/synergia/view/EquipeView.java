package br.com.synergia.view;

import br.com.synergia.dao.EquipeDAO;
import br.com.synergia.model.Equipe;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class EquipeView extends JFrame {
    private EquipeDAO equipeDAO;
    private JTable tabela;
    private DefaultTableModel modelo;

    public EquipeView() {
        equipeDAO = new EquipeDAO();

        setTitle("Gerenciamento de Equipes");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        modelo = new DefaultTableModel(new String[]{"ID", "Nome", "Descrição"}, 0);
        tabela = new JTable(modelo);
        add(new JScrollPane(tabela), BorderLayout.CENTER);

        JPanel botoes = new JPanel();
        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnExcluir = new JButton("Excluir");
        JButton btnAtualizar = new JButton("Atualizar Lista");

        botoes.add(btnAdicionar);
        botoes.add(btnExcluir);
        botoes.add(btnAtualizar);
        add(botoes, BorderLayout.SOUTH);

        btnAdicionar.addActionListener(e -> adicionarEquipe());
        btnExcluir.addActionListener(e -> excluirEquipe());
        btnAtualizar.addActionListener(e -> carregarEquipes());

        carregarEquipes();
        setVisible(true);
    }

    private void carregarEquipes() {
        modelo.setRowCount(0);
        List<Equipe> equipes = equipeDAO.listarTodos();
        for (Equipe eq : equipes) {
            modelo.addRow(new Object[]{eq.getId(), eq.getNome(), eq.getDescricao()});
        }
    }

    private void adicionarEquipe() {
        JTextField nomeField = new JTextField();
        JTextField descricaoField = new JTextField();

        Object[] message = {
                "Nome:", nomeField,
                "Descrição:", descricaoField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Adicionar Equipe", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            Equipe nova = new Equipe();
            nova.setNome(nomeField.getText());
            nova.setDescricao(descricaoField.getText());

            equipeDAO.inserir(nova);
            carregarEquipes();
        }
    }

    private void excluirEquipe() {
        int row = tabela.getSelectedRow();
        if (row >= 0) {
            int id = (int) tabela.getValueAt(row, 0);
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Excluir equipe ID " + id + "?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                equipeDAO.excluir(id);
                carregarEquipes();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma equipe.");
        }
    }
}
