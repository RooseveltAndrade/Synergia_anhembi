package br.com.synergia.view;

import br.com.synergia.dao.UsuarioDAO;
import br.com.synergia.model.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class UsuarioView extends JFrame {

    private UsuarioDAO usuarioDAO;
    private JTable tabela;
    private DefaultTableModel modelo;

    public UsuarioView() {
        usuarioDAO = new UsuarioDAO();

        setTitle("Gerenciamento de Usuários");
        setSize(750, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        modelo = new DefaultTableModel(new String[]{"ID", "Nome", "Email", "Perfil"}, 0);
        tabela = new JTable(modelo);
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // largura fixa
        tabela.setFillsViewportHeight(true);

        // Ajuste de larguras
        tabela.getColumnModel().getColumn(0).setPreferredWidth(50);   // ID
        tabela.getColumnModel().getColumn(1).setPreferredWidth(200);  // Nome
        tabela.getColumnModel().getColumn(2).setPreferredWidth(250);  // Email
        tabela.getColumnModel().getColumn(3).setPreferredWidth(120);  // Perfil

        add(new JScrollPane(tabela), BorderLayout.CENTER);

        // Painel de botões
        JPanel botoes = new JPanel();
        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnExcluir = new JButton("Excluir");
        JButton btnAtualizar = new JButton("Atualizar Lista");
        JButton btnVoltar = new JButton("Voltar");

        botoes.add(btnAdicionar);
        botoes.add(btnExcluir);
        botoes.add(btnAtualizar);
        botoes.add(btnVoltar);

        add(botoes, BorderLayout.SOUTH);

        // Listeners
        btnAdicionar.addActionListener(e -> adicionarUsuario());
        btnExcluir.addActionListener(e -> excluirUsuario());
        btnAtualizar.addActionListener(e -> carregarUsuarios());
        btnVoltar.addActionListener(e -> dispose());

        carregarUsuarios();
        setVisible(true);
    }

    private void carregarUsuarios() {
        modelo.setRowCount(0);
        List<Usuario> usuarios = usuarioDAO.listarTodos();
        for (Usuario u : usuarios) {
            modelo.addRow(new Object[]{u.getId(), u.getNome(), u.getEmail(), u.getPerfil()});
        }
    }

    private void adicionarUsuario() {
        JTextField nomeField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField senhaField = new JTextField();
        String[] perfis = {"ADMIN", "GERENTE", "COLABORADOR"};
        JComboBox<String> perfilBox = new JComboBox<>(perfis);

        Object[] message = {
                "Nome:", nomeField,
                "Email:", emailField,
                "Senha:", senhaField,
                "Perfil:", perfilBox
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Adicionar Usuário", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            Usuario novo = new Usuario();
            novo.setNome(nomeField.getText());
            novo.setEmail(emailField.getText());
            novo.setSenha(senhaField.getText());
            novo.setPerfil((String) perfilBox.getSelectedItem());

            usuarioDAO.inserir(novo);
            carregarUsuarios();
        }
    }

    private void excluirUsuario() {
        int row = tabela.getSelectedRow();
        if (row >= 0) {
            int id = (int) tabela.getValueAt(row, 0);
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Excluir usuário ID " + id + "?", "Confirmação", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                usuarioDAO.excluir(id);
                carregarUsuarios();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um usuário.");
        }
    }
}