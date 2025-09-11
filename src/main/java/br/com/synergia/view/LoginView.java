package br.com.synergia.view;

import br.com.synergia.dao.UsuarioDAO;
import br.com.synergia.model.Usuario;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {
    private JTextField emailField;
    private JPasswordField senhaField;
    private JButton loginButton;

    public LoginView() {
        setTitle("Synergia - Login");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 10, 10));

        JLabel emailLabel = new JLabel("Email:");
        JLabel senhaLabel = new JLabel("Senha:");

        emailField = new JTextField();
        senhaField = new JPasswordField();
        loginButton = new JButton("Entrar");

        add(emailLabel);
        add(emailField);
        add(senhaLabel);
        add(senhaField);
        add(new JLabel()); // placeholder vazio
        add(loginButton);

        loginButton.addActionListener(e -> autenticar());

        setVisible(true);
    }

    private void autenticar() {
        String email = emailField.getText();
        String senha = new String(senhaField.getPassword());

        UsuarioDAO dao = new UsuarioDAO();
        Usuario usuario = dao.autenticar(email, senha);

        if (usuario != null) {
            JOptionPane.showMessageDialog(this,
                    "Bem-vindo, " + usuario.getNome() + "! Perfil: " + usuario.getPerfil());
            // aqui você pode abrir a tela principal (dashboard) e fechar o login
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Email ou senha inválidos!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
