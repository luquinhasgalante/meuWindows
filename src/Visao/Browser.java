package Visao;

import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;

import Aplicativos.Navegador;

public class Browser extends Atalho {

    private String url;

    public Browser(int i, int j, String url) {
        super(i, j);

        this.url = url;

        this.setImagem(new ImageIcon(getClass().getResource("/Imagens/Browser.png")).getImage());
        this.setOpaque(false);
        this.setHasComponent(true);
        this.setNome("Browser");
    }
    
    @Override
    public void abrirMenu(MouseEvent e) {
        super.abrirMenu(e);

        JMenuItem abrir = new JMenuItem("Abrir");
        abrir.addActionListener(e1 -> abrir());
        this.getMenu().add(abrir);
        this.getMenu().show(this, e.getX(), e.getY());
    }

    public void abrir() {
        this.setRun(true);
        new Navegador(url);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        if(e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2) abrir();
        repaint();
    }
}
