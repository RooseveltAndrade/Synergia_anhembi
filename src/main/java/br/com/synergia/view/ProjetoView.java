package br.com.synergia.view;

import br.com.synergia.dao.ProjetoDAO;
import br.com.synergia.model.Projeto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Date;
import java.util.List;

public class ProjetoView extends JFrame {
    private ProjetoDAO projetoDAO;
    private JTable tabela;
    private DefaultTableModel modelo;

    public ProjetoView() {
        projetoDAO = new ProjetoDAO();

        setTitle("Gerenciamento de Projetos");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Tabela
        modelo = new DefaultTableModel(new String[]{"ID", "Nome", "Descrição", "Início", "Fim Previsto", "Status", "Gerente"}, 0);
        tabela = new JTable(modelo);
        add(new JScrollPane(tabela), BorderLayout.CENTER);

        // Botões
        JPanel botoes = new JPanel();
        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnExcluir = new JButton("Excluir");
        JButton btnAtualizar = new JButton("Atualizar Lista");
        botoes.add(btnAdicionar);
        botoes.add(btnExcluir);
        botoes.add(btnAtualizar);
        add(botoes, BorderLayout.SOUTH);

        // Ações
        btnAdicionar.addActionListener(e -> adicionarProjeto());
        btnExcluir.addActionListener(e -> excluirProjeto());
        btnAtualizar.addActionListener(e -> carregarProjetos());

        carregarProjetos();
        setVisible(true);
    }

    private void carregarProjetos() {
        modelo.setRowCount(0);
        List<Projeto> projetos = projetoDAO.listarTodos();
        for (Projeto p : projetos) {
            modelo.addRow(new Object[]{
                    p.getId(), p.getNome(), p.getDescricao(),
                    p.getDataInicio(), p.getDataFimPrevista(),
                    p.getStatus(), p.getGerenteId()
            });
        }
    }

    private void adicionarProjeto() {
        JTextField nomeField = new JTextField();
        JTextField descField = new JTextField();
        JTextField inicioField = new JTextField("2025-01-01");
        JTextField fimField = new JTextField("2025-12-31");
        String[] status = {"PLANEJADO", "EM_ANDAMENTO", "CONCLUIDO", "CANCELADO"};
        JComboBox<String> statusBox = new JComboBox<>(status);
        JTextField gerenteIdField = new JTextField("1");

        Object[] message = {
                "Nome:", nomeField,
                "Descrição:", descField,
                "Data Início (YYYY-MM-DD):", inicioField,
                "Data Fim Prevista (YYYY-MM-DD):", fimField,
                "Status:", statusBox,
                "ID Gerente:", gerenteIdField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Adicionar Projeto", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            Projeto novo = new Projeto();
            novo.setNome(nomeField.getText());
            novo.setDescricao(descField.getText());
            novo.setDataInicio(Date.valueOf(inicioField.getText()));
            novo.setDataFimPrevista(Date.valueOf(fimField.getText()));
            novo.setStatus((String) statusBox.getSelectedItem());
            novo.setGerenteId(Integer.parseInt(gerenteIdField.getText()));

            projetoDAO.inserir(novo);
            carregarProjetos();
        }
    }

    private void excluirProjeto() {
        int row = tabela.getSelectedRow();
        if (row >= 0) {
            int id = (int) tabela.getValueAt(row, 0);
            int confirm = JOptionPane.showConfirmDialog(this, "Excluir projeto ID " + id + "?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                projetoDAO.excluir(id);
                carregarProjetos();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um projeto.");
        }
    }
}
