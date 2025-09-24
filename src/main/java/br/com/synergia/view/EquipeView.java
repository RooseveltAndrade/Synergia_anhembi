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
    private JTable tabela;
    private DefaultTableModel modelo;
    private EquipeDAO equipeDAO;

    public EquipeView() {
        equipeDAO = new EquipeDAO();

        setTitle("Gerenciamento de Equipes");
        setSize(750, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Painel de formulário
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        formPanel.add(new JLabel("Nome:"));
        nomeField = new JTextField();
        formPanel.add(nomeField);

        formPanel.add(new JLabel("Descrição:"));
        descricaoField = new JTextField();
        formPanel.add(descricaoField);

        // Botões
        JPanel botoesPanel = new JPanel();
        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnAtualizar = new JButton("Atualizar");
        JButton btnExcluir = new JButton("Excluir");
        JButton btnVoltar = new JButton("Voltar");

        botoesPanel.add(btnAdicionar);
        botoesPanel.add(btnAtualizar);
        botoesPanel.add(btnExcluir);
        botoesPanel.add(btnVoltar);

        // Tabela
        modelo = new DefaultTableModel(new Object[]{"ID", "Nome", "Descrição"}, 0);
        tabela = new JTable(modelo);
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabela.setFillsViewportHeight(true);

        // Ajustando larguras
        tabela.getColumnModel().getColumn(0).setPreferredWidth(50);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(200);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(450);

        JScrollPane tableScroll = new JScrollPane(tabela);

        // Layout principal
        add(formPanel, BorderLayout.NORTH);
        add(tableScroll, BorderLayout.CENTER);
        add(botoesPanel, BorderLayout.SOUTH);

        // Listeners
        btnAdicionar.addActionListener(e -> adicionarEquipe());
        btnAtualizar.addActionListener(e -> atualizarEquipe());
        btnExcluir.addActionListener(e -> deletarEquipe());
        btnVoltar.addActionListener(e -> dispose());

        carregarEquipes();
        setVisible(true);
    }

    private void carregarEquipes() {
        modelo.setRowCount(0);
        List<Equipe> equipes = equipeDAO.listar();
        for (Equipe eq : equipes) {
            modelo.addRow(new Object[]{eq.getId(), eq.getNome(), eq.getDescricao()});
        }
    }

    private void adicionarEquipe() {
        if (nomeField.getText().isEmpty() || descricaoField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
            return;
        }
        Equipe equipe = new Equipe(nomeField.getText(), descricaoField.getText());
        equipeDAO.inserir(equipe);
        carregarEquipes();
        limparCampos();
    }

    private void atualizarEquipe() {
        int row = tabela.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma equipe para atualizar!");
            return;
        }
        int id = (int) modelo.getValueAt(row, 0);
        Equipe equipe = new Equipe(id, nomeField.getText(), descricaoField.getText());
        equipeDAO.atualizar(equipe);
        carregarEquipes();
        limparCampos();
    }

    private void deletarEquipe() {
        int row = tabela.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma equipe para excluir!");
            return;
        }
        int id = (int) modelo.getValueAt(row, 0);
        equipeDAO.deletar(id);
        carregarEquipes();
        limparCampos();
    }

    private void limparCampos() {
        nomeField.setText("");
        descricaoField.setText("");
    }
}