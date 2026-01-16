package Visao;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class TelaPrincipal extends JLayeredPane {
    
    private int COLUNAS = 16;
    private int LINHAS = 7;
    private static int GRID_LAYER = 40;
    private static int GRAB_LAYER = 0;
    private boolean corSolida;
    public Color corDeFundo;
    private Image planoFundo;
    public int getCOLUNAS() {
        return COLUNAS;
    }


    private Dimension d;
    private JPanel[][] array = new JPanel[LINHAS][COLUNAS];
    private Pasta pastaPrincipal = new Pasta(ALLBITS, ABORT);

    public TelaPrincipal(Dimension d) {
        
        this.d = d;
        this.setPreferredSize(d);
        this.setSize(d);
        this.setLayout(null);
        organizarTela();
        planoFundo = new ImageIcon(getClass().getResource("/Imagens/planodefundo.png")).getImage();
    }

    public void organizarTela() {
        adicionarAtalhos();
        for(int i = 0; i < LINHAS; i++) {
            for(int j = 0; j < COLUNAS; j++) {
                if(array[i][j] == null) {
                    array[i][j] = new Atalho(i, j) {};
                }
                
                this.add(array[i][j], GRID_LAYER);
            }
        }
    }

    public void adicionarAtalhos() {
        array[0][0] = new Dinossauro(0, 0);
        array[0][1] = new BlocoDeNotas(0, 1, null);
        array[0][2] = new Pasta(0, 2);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void selecionarCor(Color c) {
        corSolida = true;
        corDeFundo = c;
    }

    public void draw(Graphics g) {
        g.drawImage(planoFundo, 0, 0,(int) d.getWidth(),(int) d.getHeight(), null);
        g.setColor(corDeFundo);
        if(corSolida) g.fillRect(0, 0, getWidth(), getHeight());
    }

    
    public void setCOLUNAS(int cOLUNAS) {
        COLUNAS = cOLUNAS;
    }

    public int getLINHAS() {
        return LINHAS;
    }

    public void setLINHAS(int lINHAS) {
        LINHAS = lINHAS;
    }

    public static int getGRID_LAYER() {
        return GRID_LAYER;
    }

    public static void setGRID_LAYER(int gRID_LAYER) {
        GRID_LAYER = gRID_LAYER;
    }

    public static int getGRAB_LAYER() {
        return GRAB_LAYER;
    }

    public static void setGRAB_LAYER(int gRAB_LAYER) {
        GRAB_LAYER = gRAB_LAYER;
    }

    public Image getPlanoFundo() {
        return planoFundo;
    }

    public void setPlanoFundo(Image planoFundo) {
        this.planoFundo = planoFundo;
    }

    public Dimension getD() {
        return d;
    }

    public void setD(Dimension d) {
        this.d = d;
    }

    public JPanel[][] getArray() {
        return array;
    }

    public void setArray(JPanel[][] array) {
        this.array = array;
    }

    public boolean isCorSolida() {
        return corSolida;
    }

    public void setCorSolida(boolean corSolida) {
        this.corSolida = corSolida;
    }
}

