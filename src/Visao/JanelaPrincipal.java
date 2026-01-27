package Visao;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class JanelaPrincipal extends JFrame {
    
    private static final int LARGURA = 1280;
    private static final int ALTURA = 720;
    private static final Dimension d = new Dimension(LARGURA, ALTURA);

    public JanelaPrincipal() {
        initComponents();
    }

    public final void initComponents() {
        this.setSize(d);
        this.setLayout(new BorderLayout());
        Image img = new ImageIcon(getClass().getResource("/Imagens/bolha.png")).getImage();
        this.setIconImage(img);
        this.setTitle("BubbleOS");
        this.setResizable(true);
        this.add(new TelaPrincipal(d), BorderLayout.CENTER);
        this.setUndecorated(true);
        this.add(new GerenciadorDeTarefas(d), BorderLayout.SOUTH); 
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.getRootPane().setWindowDecorationStyle(1);
        pack();
    }

}
