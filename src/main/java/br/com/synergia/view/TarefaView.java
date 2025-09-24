package br.com.synergia.view;

import br.com.synergia.dao.TarefaDAO;
import br.com.synergia.model.Tarefa;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class TarefaView extends JFrame {
    private JTextField tituloField, descricaoField, projetoIdField, responsavelIdField, statusField, inicioField, fimField;
    private JTable tabela;
    private DefaultTableModel modelo;
    private TarefaDAO tarefaDAO;

    public TarefaView() {
        tarefaDAO = new TarefaDAO();

        setTitle("Gerenciamento de Tarefas");
        setSize(950, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Painel formulário
        JPanel formPanel = new JPanel(new GridLayout(8, 2, 5, 5));
        formPanel.add(new JLabel("Título:"));
        tituloField = new JTextField();
        formPanel.add(tituloField);

        formPanel.add(new JLabel("Descrição:"));
        descricaoField = new JTextField();
        formPanel.add(descricaoField);

        formPanel.add(new JLabel("ID Projeto:"));
        projetoIdField = new JTextField();
        formPanel.add(projetoIdField);

        formPanel.add(new JLabel("ID Responsável:"));
        responsavelIdField = new JTextField();
        formPanel.add(responsavelIdField);

        formPanel.add(new JLabel("Status:"));
        statusField = new JTextField();
        formPanel.add(statusField);

        formPanel.add(new JLabel("Data Início Prevista (AAAA-MM-DD):"));
        inicioField = new JTextField();
        formPanel.add(inicioField);

        formPanel.add(new JLabel("Data Fim Prevista (AAAA-MM-DD):"));
        fimField = new JTextField();
        formPanel.add(fimField);

        // Botões
        JPanel botoes = new JPanel();
        JButton addButton = new JButton("Adicionar");
        JButton updateButton = new JButton("Atualizar");
        JButton deleteButton = new JButton("Excluir");
        JButton voltarButton = new JButton("Voltar");

        botoes.add(addButton);
        botoes.add(updateButton);
        botoes.add(deleteButton);
        botoes.add(voltarButton);

        // Tabela
        modelo = new DefaultTableModel(new Object[]{
                "ID", "Título", "Descrição", "Projeto", "Responsável", "Status", "Início Previsto", "Fim Previsto"
        }, 0);
        tabela = new JTable(modelo);
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // largura fixa
        tabela.setFillsViewportHeight(true);

        // Ajustando larguras
        tabela.getColumnModel().getColumn(0).setPreferredWidth(50);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(150);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(200);
        tabela.getColumnModel().getColumn(3).setPreferredWidth(80);
        tabela.getColumnModel().getColumn(4).setPreferredWidth(100);
        tabela.getColumnModel().getColumn(5).setPreferredWidth(100);
        tabela.getColumnModel().getColumn(6).setPreferredWidth(120);
        tabela.getColumnModel().getColumn(7).setPreferredWidth(120);

        JScrollPane tableScroll = new JScrollPane(tabela);

        // Layout principal
        add(formPanel, BorderLayout.NORTH);
        add(tableScroll, BorderLayout.CENTER);
        add(botoes, BorderLayout.SOUTH);

        // Listeners
        addButton.addActionListener(e -> adicionarTarefa());
        updateButton.addActionListener(e -> atualizarTarefa());
        deleteButton.addActionListener(e -> deletarTarefa());
        voltarButton.addActionListener(e -> dispose());

        carregarTarefas();
        setVisible(true);
    }

    private void carregarTarefas() {
        modelo.setRowCount(0);
        List<Tarefa> tarefas = tarefaDAO.listar();
        for (Tarefa t : tarefas) {
            modelo.addRow(new Object[]{
                    t.getId(), t.getTitulo(), t.getDescricao(),
                    t.getProjetoId(), t.getResponsavelId(),
                    t.getStatus(), t.getDataInicioPrevista(), t.getDataFimPrevista()
            });
        }
    }

    private void adicionarTarefa() {
        try {
            Tarefa tarefa = new Tarefa(
                    tituloField.getText(),
                    descricaoField.getText(),
                    Integer.parseInt(projetoIdField.getText()),
                    Integer.parseInt(responsavelIdField.getText()),
                    statusField.getText(),
                    LocalDate.parse(inicioField.getText()),
                    LocalDate.parse(fimField.getText())
            );
            tarefaDAO.inserir(tarefa);
            carregarTarefas();
            limparCampos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao adicionar tarefa: " + e.getMessage());
        }
    }

    private void atualizarTarefa() {
        int row = tabela.getSelectedRow();
        if (row == -1) return;
        try {
            int id = (int) modelo.getValueAt(row, 0);
            Tarefa tarefa = new Tarefa(
                    id,
                    tituloField.getText(),
                    descricaoField.getText(),
                    Integer.parseInt(projetoIdField.getText()),
                    Integer.parseInt(responsavelIdField.getText()),
                    statusField.getText(),
                    LocalDate.parse(inicioField.getText()),
                    LocalDate.parse(fimField.getText())
            );
            tarefaDAO.atualizar(tarefa);
            carregarTarefas();
            limparCampos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar tarefa: " + e.getMessage());
        }
    }

    private void deletarTarefa() {
        int row = tabela.getSelectedRow();
        if (row == -1) return;
        int id = (int) modelo.getValueAt(row, 0);
        tarefaDAO.deletar(id);
        carregarTarefas();
        limparCampos();
    }

    private void limparCampos() {
        tituloField.setText("");
        descricaoField.setText("");
        projetoIdField.setText("");
        responsavelIdField.setText("");
        statusField.setText("");
        inicioField.setText("");
        fimField.setText("");
    }
}