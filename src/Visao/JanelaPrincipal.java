package Visao;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;

public class JanelaPrincipal extends JFrame {
    
    private static final int LARGURA = 1280;
    private static final int ALTURA = 600;
    private static final Dimension d = new Dimension(LARGURA, ALTURA);

    public JanelaPrincipal() {
        initComponents();
    }

    public final void initComponents() {
        this.setSize(d);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        this.add(new TelaPrincipal(d), BorderLayout.CENTER);
        this.setUndecorated(true);
        this.add(new GerenciadorDeTarefas(d), BorderLayout.SOUTH); 
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        pack();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JanelaPrincipal().setVisible(true);
            }
        });   
    }
}
