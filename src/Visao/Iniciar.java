package Visao;

import Aplicativos.Navegador;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

public class Iniciar extends JPanel {

    private int LARGURA = 100;
    private final String LINK_SITE = "https://lucasmilhao.page.gd";
    private boolean mouseIn = false;
    private final JPopupMenu menu;

    public Iniciar(GerenciadorDeTarefas g) {
        this.setBounds(0, 0, LARGURA, g.getALTURA());
        this.setOpaque(false);
        this.addMouseListener(ma);
        menu = new JPopupMenu();
        menuItems();
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

        @Override
        public void mousePressed(MouseEvent e) {
            menu.show(Iniciar.this, 0, getParent().getHeight() - 430);
        }
    };

    private void abrirLink(String caminho) {
        try {
            URI url = new URI(caminho);

            if(Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(url);
            }
            else {
                JOptionPane.showMessageDialog(null, "Desktop não suportado nesse sistema");
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void menuItems() {
        JMenu criador = new JMenu("Ver sobre o criador");
        JMenuItem local = new JMenuItem("Abrir no BubbleBrowser");
        local.addActionListener(e -> new Navegador(LINK_SITE));
        JMenuItem online = new JMenuItem("Abrir no seu navegador");
        online.addActionListener(e -> abrirLink(LINK_SITE));
        criador.add(local);
        criador.add(online);
        menu.add(criador);

        for(int i = 0; i < 3; i++) {
            menu.add(new JMenuItem("Item " + i));
        }
        Icon icon = new ImageIcon(getClass().getResource("/Imagens/desligar.png"));
        JMenuItem sair = new JMenuItem("Sair", icon);
        sair.addActionListener(e -> System.exit(0));
        menu.add(sair);

        menu.setPreferredSize(new Dimension(300, 400));
    }

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
