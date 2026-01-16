package Visao;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class Iniciar extends JPanel {

    private int LARGURA = 100;
    private boolean mouseIn = false;

    public Iniciar(GerenciadorDeTarefas g) {
        this.setBounds(0, 0, LARGURA, g.getALTURA());
        this.setOpaque(false);
        this.addMouseListener(ma);
    }

    MouseAdapter ma = new MouseAdapter() {
        
        @Override
        public void mouseEntered(MouseEvent e) {
            mouseIn = true;
            repaint();
        }

        @Override
        public void mouseExited(MouseEvent e) {
            mouseIn = false;
            repaint();
        }
    };

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(new Color(0, 255, 23, 128));
        g2d.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.WHITE);
        g.drawString("Iniciar",24, getParent().getHeight() - 15);

        if(mouseIn) {
            g2d.setColor(new Color(255, 255, 255, 128));
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}
