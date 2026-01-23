package Visao;

import Jogos.Cobra.SnakeFrame;
import java.awt.*;
import java.awt.event.MouseEvent;
import javax.swing.*;

public final class Snake extends Atalho {

    public Snake(int i, int j) {
        super(i, j);

        this.setImagem(new ImageIcon(getClass().getResource("/Imagens/cobra.png")).getImage());
        this.setOpaque(false);
        this.setHasComponent(true);
        this.setNome("Jogo Cobrinha");
    }

    
    @Override
    public void abrirMenu(MouseEvent e) {
        super.abrirMenu(e);

        JMenuItem abrir = new JMenuItem("Abrir");
        abrir.addActionListener(e1 -> abrir());
        this.getMenu().add(abrir);
        this.getMenu().show(this, e.getX(), e.getY());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public void abrir() {
        this.setRun(true);
        new SnakeFrame();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        if(e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2) abrir();
        repaint();
    }

}
