package Visao;

import Jogos.Dinossauro.Game;
import java.awt.*;
import java.awt.event.MouseEvent;
import javax.swing.*;

public final class Dinossauro extends Atalho {

    public Dinossauro(int i, int j) {
        super(i, j);

        this.setImagem(new ImageIcon(getClass().getResource("/Jogos/Dinossauro/img/dino.png")).getImage());
        this.setOpaque(false);
        this.setHasComponent(true);
        this.setNome("Jogo Dinossauro");
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public static void abrir() {
        new Game();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        if(e.getButton() == MouseEvent.BUTTON1 && this.isRun()) new Game();
        repaint();
    }

}
