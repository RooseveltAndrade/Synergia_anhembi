package br.com.synergia.view;

import javax.swing.*;
import java.awt.*;
import br.com.synergia.view.ProjetoView;

public class DashboardView extends JFrame {

    public DashboardView(String nomeUsuario, String perfil) {
        setTitle("Synergia - Dashboard");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // Cabeçalho
        JLabel welcomeLabel = new JLabel(
                "Bem-vindo, " + nomeUsuario + " (" + perfil + ")", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(welcomeLabel, BorderLayout.NORTH);

        // Painel com botões
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

        // Ações básicas
        btnUsuarios.addActionListener(e -> JOptionPane.showMessageDialog(this, "Abrir módulo de Usuários"));
        btnProjetos.addActionListener(e -> new ProjetoView());
        btnEquipes.addActionListener(e -> JOptionPane.showMessageDialog(this, "Abrir módulo de Equipes"));
        btnTarefas.addActionListener(e -> JOptionPane.showMessageDialog(this, "Abrir módulo de Tarefas"));
        btnRelatorios.addActionListener(e -> JOptionPane.showMessageDialog(this, "Abrir módulo de Relatórios"));
        btnSair.addActionListener(e -> System.exit(0));

        setVisible(true);
    }
}
