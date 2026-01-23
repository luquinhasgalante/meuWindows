package Jogos.Cobra;

import javax.swing.JFrame;

public class SnakeFrame extends JFrame {
    
    public SnakeFrame() {
        this.add(new SnakePanel());
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Jogo da cobrinha");
        this.setResizable(true);
        this.setUndecorated(true);
        this.getRootPane().setWindowDecorationStyle(1);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
}
