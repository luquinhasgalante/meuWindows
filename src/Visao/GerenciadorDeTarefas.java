package Visao;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class GerenciadorDeTarefas extends JPanel {

    private Dimension d;
    private int ALTURA;
    private int LARGURA;

    public GerenciadorDeTarefas(Dimension d) {
        this.ALTURA = 65;
        this.LARGURA = (int) d.getWidth();
        this.d = new Dimension(LARGURA, ALTURA);
        this.setPreferredSize(this.d);
        this.setLayout(null);
        this.add(new Relogio());
    }

    @Override
    public void paintComponent(Graphics g) {
        draw(g);
    }

    public void draw(Graphics g) {
        g.setColor(new Color(19, 89, 158));
        g.fillRect(0, (int) d.getHeight() - 65, (int) d.getWidth(), 65);
    }

}
