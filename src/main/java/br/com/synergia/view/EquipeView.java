package br.com.synergia.view;

import br.com.synergia.dao.EquipeDAO;
import br.com.synergia.model.Equipe;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class EquipeView extends JFrame {
    private JTextField nomeField;
    private JTextField descricaoField;
    private JTable table;
    private DefaultTableModel tableModel;
    private EquipeDAO equipeDAO;

    public EquipeView() {
        equipeDAO = new EquipeDAO();

        setTitle("Gerenciamento de Equipes");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Painel de formulário
        JPanel formPanel = new JPanel(new GridLayout(3, 2));
        formPanel.add(new JLabel("Nome:"));
        nomeField = new JTextField();
        formPanel.add(nomeField);

        formPanel.add(new JLabel("Descrição:"));
        descricaoField = new JTextField();
        formPanel.add(descricaoField);

        JButton addButton = new JButton("Adicionar");
        JButton updateButton = new JButton("Atualizar");
        JButton deleteButton = new JButton("Excluir");

        formPanel.add(addButton);
        formPanel.add(updateButton);
        formPanel.add(deleteButton);

        // Tabela
        tableModel = new DefaultTableModel(new Object[]{"ID", "Nome", "Descrição"}, 0);
        table = new JTable(tableModel);
        JScrollPane tableScroll = new JScrollPane(table);

        // Layout
        add(formPanel, BorderLayout.NORTH);
        add(tableScroll, BorderLayout.CENTER);

        // Listeners
        addButton.addActionListener(e -> adicionarEquipe());
        updateButton.addActionListener(e -> atualizarEquipe());
        deleteButton.addActionListener(e -> deletarEquipe());

        // Carregar dados
        carregarEquipes();

        setVisible(true);
    }

    private void carregarEquipes() {
        tableModel.setRowCount(0);
        List<Equipe> equipes = equipeDAO.listar();
        for (Equipe eq : equipes) {
            tableModel.addRow(new Object[]{eq.getId(), eq.getNome(), eq.getDescricao()});
        }
    }

    private void adicionarEquipe() {
        String nome = nomeField.getText();
        String descricao = descricaoField.getText();
        if (nome.isEmpty() || descricao.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
            return;
        }
        Equipe equipe = new Equipe(nome, descricao);
        equipeDAO.inserir(equipe);
        carregarEquipes();
        limparCampos();
    }

    private void atualizarEquipe() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma equipe para atualizar!");
            return;
        }
        int id = (int) tableModel.getValueAt(selectedRow, 0);
        String nome = nomeField.getText();
        String descricao = descricaoField.getText();
        Equipe equipe = new Equipe(id, nome, descricao);
        equipeDAO.atualizar(equipe);
        carregarEquipes();
        limparCampos();
    }

    private void deletarEquipe() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma equipe para excluir!");
            return;
        }
        int id = (int) tableModel.getValueAt(selectedRow, 0);
        equipeDAO.deletar(id);
        carregarEquipes();
        limparCampos();
    }

    private void limparCampos() {
        nomeField.setText("");
        descricaoField.setText("");
    }
}