package Visao;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class TelaPrincipal extends JPanel {
    
    private int COLUNAS = 16;
    private int LINHAS = 7;
    private Image planoFundo;
    private Dimension d;
    private JPanel[][] array = new JPanel[LINHAS][COLUNAS];

    public TelaPrincipal(Dimension d) {
        this.d = d;
        this.setPreferredSize(d);
        this.setSize(d);
        this.setLayout(null);
        organizarTela();
        planoFundo = new ImageIcon(getClass().getResource("/Imagens/fundo.jpg")).getImage();
    }

    public void organizarTela() {
        adicionarAtalhos();
        for(int i = 0; i < LINHAS; i++) {
            for(int j = 0; j < COLUNAS; j++) {
                if(array[i][j] == null) {
                    array[i][j] = new Atalho(i, j) {
                        
                    };
                }

                this.add(array[i][j]);
            }
        }
    }

    public void adicionarAtalhos() {
        array[0][0] = new Dinossauro(0, 0);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        g.drawImage(planoFundo, 0, 0,(int) d.getWidth(),(int) d.getHeight(), null);
    }
}
