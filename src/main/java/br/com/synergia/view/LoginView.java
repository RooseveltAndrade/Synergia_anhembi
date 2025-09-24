package br.com.synergia.view;

import br.com.synergia.dao.UsuarioDAO;
import br.com.synergia.model.Usuario;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {
    private JTextField emailField;
    private JPasswordField senhaField;
    private UsuarioDAO usuarioDAO;

    public LoginView() {
        usuarioDAO = new UsuarioDAO();

        setTitle("Login - Synergia");
        setSize(400, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Tela inicial deve encerrar app
        setLocationRelativeTo(null);

        // Painel principal com margem
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Painel de formulário
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 8, 8));
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();
        JLabel senhaLabel = new JLabel("Senha:");
        senhaField = new JPasswordField();

        formPanel.add(emailLabel);
        formPanel.add(emailField);
        formPanel.add(senhaLabel);
        formPanel.add(senhaField);

        // Painel de botões
        JPanel botoesPanel = new JPanel();
        JButton loginButton = new JButton("Entrar");
        botoesPanel.add(loginButton);

        // Montagem final
        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(botoesPanel, BorderLayout.SOUTH);

        add(panel);

        // ação do botão de login
        loginButton.addActionListener(e -> autenticar());

        setVisible(true);
    }

    private void autenticar() {
        String email = emailField.getText().trim();
        String senha = new String(senhaField.getPassword()).trim();

        if (email.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Preencha todos os campos!", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Usuario usuario = usuarioDAO.autenticar(email, senha);

        if (usuario != null) {
            JOptionPane.showMessageDialog(this,
                    "Bem-vindo, " + usuario.getNome() + "! Perfil: " + usuario.getPerfil());

            // Abre o Dashboard após login bem-sucedido
            SwingUtilities.invokeLater(() -> new DashboardView(usuario.getNome(), usuario.getPerfil()));

            // Fecha a tela de login
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Email ou senha inválidos!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}