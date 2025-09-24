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
    private JTable table;
    private DefaultTableModel tableModel;
    private TarefaDAO tarefaDAO;

    public TarefaView() {
        tarefaDAO = new TarefaDAO();

        setTitle("Gerenciamento de Tarefas");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel formPanel = new JPanel(new GridLayout(8, 2));
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

        JButton addButton = new JButton("Adicionar");
        JButton updateButton = new JButton("Atualizar");
        JButton deleteButton = new JButton("Excluir");

        formPanel.add(addButton);
        formPanel.add(updateButton);
        formPanel.add(deleteButton);

        tableModel = new DefaultTableModel(new Object[]{"ID", "Título", "Descrição", "Projeto", "Responsável", "Status", "Início Previsto", "Fim Previsto"}, 0);
        table = new JTable(tableModel);
        JScrollPane tableScroll = new JScrollPane(table);

        add(formPanel, BorderLayout.NORTH);
        add(tableScroll, BorderLayout.CENTER);

        addButton.addActionListener(e -> adicionarTarefa());
        updateButton.addActionListener(e -> atualizarTarefa());
        deleteButton.addActionListener(e -> deletarTarefa());

        carregarTarefas();
        setVisible(true);
    }

    private void carregarTarefas() {
        tableModel.setRowCount(0);
        List<Tarefa> tarefas = tarefaDAO.listar();
        for (Tarefa t : tarefas) {
            tableModel.addRow(new Object[]{
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
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) return;
        try {
            int id = (int) tableModel.getValueAt(selectedRow, 0);
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
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) return;
        int id = (int) tableModel.getValueAt(selectedRow, 0);
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