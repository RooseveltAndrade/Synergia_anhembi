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
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2));

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();
        JLabel senhaLabel = new JLabel("Senha:");
        senhaField = new JPasswordField();
        JButton loginButton = new JButton("Login");

        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(senhaLabel);
        panel.add(senhaField);
        panel.add(new JLabel()); // espaço vazio
        panel.add(loginButton);

        add(panel);

        // ação do botão de login
        loginButton.addActionListener(e -> autenticar());

        setVisible(true);
    }

    private void autenticar() {
        String email = emailField.getText();
        String senha = new String(senhaField.getPassword());

        Usuario usuario = usuarioDAO.autenticar(email, senha);

        if (usuario != null) {
            JOptionPane.showMessageDialog(this,
                    "Bem-vindo, " + usuario.getNome() + "! Perfil: " + usuario.getPerfil());

            // 👉 Abre o Dashboard após login bem-sucedido
            new DashboardView(usuario.getNome(), usuario.getPerfil());

            // 👉 Fecha a tela de login
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Email ou senha inválidos!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
