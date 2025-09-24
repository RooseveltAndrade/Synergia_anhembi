package br.com.synergia;

import br.com.synergia.view.LoginView;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> new LoginView());
    }
}