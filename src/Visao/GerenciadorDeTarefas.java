package Visao;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GerenciadorDeTarefas extends JPanel {

    private Dimension d;
    private Image imagem;
    private int ALTURA;
    private int LARGURA;

    public GerenciadorDeTarefas(Dimension d) {
        this.ALTURA = 45;
        this.LARGURA = (int) d.getWidth();
        this.imagem = new ImageIcon(getClass().getResource("/Imagens/tarefas.png")).getImage();
        this.d = new Dimension(LARGURA, ALTURA);
        this.setPreferredSize(this.d);
        this.setLayout(null);
        this.add(new Iniciar(this));
        this.add(new Relogio(this));
    }

    public Dimension getD() {
        return d;
    }

    public void setD(Dimension d) {
        this.d = d;
    }

    public Image getImagem() {
        return imagem;
    }

    public void setImagem(Image imagem) {
        this.imagem = imagem;
    }

    public int getALTURA() {
        return ALTURA;
    }

    public void setALTURA(int aLTURA) {
        ALTURA = aLTURA;
    }

    public int getLARGURA() {
        return LARGURA;
    }

    public void setLARGURA(int lARGURA) {
        LARGURA = lARGURA;
    }

    @Override
    public void paintComponent(Graphics g) {
        draw(g);
    }

    public void draw(Graphics g) {
        g.setColor(new Color(19, 89, 158));
        g.drawImage(imagem, 0, (int) d.getHeight() - this.ALTURA, (int) d.getWidth(), this.ALTURA, null);
    }

}
