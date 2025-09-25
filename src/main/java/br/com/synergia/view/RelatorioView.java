package br.com.synergia.view;

import br.com.synergia.dao.RelatorioDAO;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RelatorioView extends JFrame {
    private RelatorioDAO relatorioDAO;
    private JTextArea resultadoArea;

    public RelatorioView() {
        relatorioDAO = new RelatorioDAO();

        setTitle("Relatórios do Sistema");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel botoes = new JPanel(new GridLayout(1, 3));
        JButton btnResumoProjetos = new JButton("📊 Resumo Projetos");
        JButton btnDesempenho = new JButton("👤 Desempenho Colaboradores");
        JButton btnAtrasados = new JButton("⚠ Projetos Atrasados");

        botoes.add(btnResumoProjetos);
        botoes.add(btnDesempenho);
        botoes.add(btnAtrasados);

        resultadoArea = new JTextArea();
        resultadoArea.setEditable(false);

        add(botoes, BorderLayout.NORTH);
        add(new JScrollPane(resultadoArea), BorderLayout.CENTER);

        // Listeners
        btnResumoProjetos.addActionListener(e -> mostrarResultados(relatorioDAO.resumoProjetosPorStatus()));
        btnDesempenho.addActionListener(e -> mostrarResultados(relatorioDAO.desempenhoColaboradores()));
        btnAtrasados.addActionListener(e -> mostrarResultados(relatorioDAO.projetosAtrasados()));

        setVisible(true);
    }

    private void mostrarResultados(List<String> resultados) {
        resultadoArea.setText("");
        for (String linha : resultados) {
            resultadoArea.append(linha + "\n");
        }
    }
}