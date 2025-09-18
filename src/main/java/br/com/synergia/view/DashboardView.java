package br.com.synergia.view;

import javax.swing.*;
import java.awt.*;

public class DashboardView extends JFrame {

    public DashboardView(String nomeUsuario, String perfil) {
        setTitle("Synergia - Dashboard");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel(
                "Bem-vindo, " + nomeUsuario + " (" + perfil + ")", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(welcomeLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 10, 10));

        JButton btnUsuarios = new JButton("👥 Usuários");
        JButton btnProjetos = new JButton("📂 Projetos");
        JButton btnEquipes = new JButton("👨‍👩‍👧 Equipes");
        JButton btnTarefas = new JButton("✅ Tarefas");
        JButton btnRelatorios = new JButton("📊 Relatórios");
        JButton btnSair = new JButton("🚪 Sair");

        buttonPanel.add(btnUsuarios);
        buttonPanel.add(btnProjetos);
        buttonPanel.add(btnEquipes);
        buttonPanel.add(btnTarefas);
        buttonPanel.add(btnRelatorios);
        buttonPanel.add(btnSair);

        add(buttonPanel, BorderLayout.CENTER);

        // Ações
        btnUsuarios.addActionListener(e -> new UsuarioView());
        btnProjetos.addActionListener(e -> new ProjetoView());
        btnEquipes.addActionListener(e -> new EquipeView());

        btnTarefas.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Módulo de Tarefas ainda não implementado."));
        btnRelatorios.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Módulo de Relatórios ainda não implementado."));
        btnSair.addActionListener(e -> System.exit(0));

        setVisible(true);
    }
}
