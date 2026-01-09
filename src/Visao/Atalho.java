package Visao;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public abstract class Atalho extends JPanel implements MouseListener {
    
    private final int ALTURA = 85;
    private final int LARGURA = 80;
    Point offset = null;

    private Image imagem;
    private int LARGURA_IMAGEM;
    private int ALTURA_IMAGEM;
    private int i;
    private int j;
    private boolean active;
    private boolean hasComponent;
    private boolean mouseIn;
    private boolean run;
    private static Atalho ativo;
    private String nome;


    public Atalho(int i, int j) {
        initComponents(i, j);
        this.setOpaque(false);
    }

    private void initComponents(int i, int j) {
        this.i = (i * ALTURA);
        this.j = (j * LARGURA);

        this.setBounds(this.j, this.i, LARGURA, ALTURA);
        this.LARGURA_IMAGEM = 33;
        this.ALTURA_IMAGEM = 53;
        this.active = false;
        this.hasComponent = false;
        this.mouseIn = false;
        this.run = false;
        this.nome = "";
        Atalho.ativo = null;

    }

    @Override
    public void addNotify() {
        super.addNotify();
        this.addMouseListener(this);
        this.addMouseMotionListener(mouseAdapter);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        pintarQuandoPressionado(g2d);
        desenharHover(g2d);
        desenharComponente(g2d);
    }

    private void desenharComponente(Graphics2D g2d) {

        if(imagem != null) g2d.drawImage(this.getImagem(),(getWidth() / 2) - (LARGURA_IMAGEM / 2),6, LARGURA_IMAGEM, ALTURA_IMAGEM, null);
        g2d.setFont(new Font("Arial", Font.PLAIN, 9));
        if(!nome.isBlank()) g2d.drawString(nome, (getWidth() - g2d.getFontMetrics().stringWidth(nome)) / 2, getHeight() - 5);
    }

    public void pintarQuandoPressionado(Graphics2D g2d) {

        if(this.isActive()) {
            g2d.setColor(new Color(0, 0, 128, 64));
            g2d.fillRect(0, 0, getWidth(), getHeight());
            if(run) g2d.fillRect((getWidth() / 2) - (LARGURA_IMAGEM / 2), 6, LARGURA_IMAGEM, ALTURA_IMAGEM);
            g2d.drawRect(0, 0, getWidth() - 1, getHeight() -1 );
        }

    }

    private void desenharHover(Graphics2D g2d) {
        if(this.isHasComponent()) {

            if(this.getMouseIn()) {
                g2d.setColor(new Color(255, 255, 255, 64));
                g2d.fillRect(0, 0, getWidth(), getHeight());
                g2d.setColor(Color.white);
                g2d.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
            }
        }
    }

    private void pressionado() {

        if(this.isActive()) {
            this.setRun(true);
        } 
        else {
            
            if(ativo != null) {
                ativo.setActive(false);
                ativo.repaint();
            }

            if(this.isHasComponent()) {
                ativo = this;
                this.setRun(false);
                this.setActive(true);
            }
            repaint();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            pressionado();
            offset = e.getPoint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.setMouseIn(true);
        repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setMouseIn(false);
        repaint();
    }


    MouseAdapter mouseAdapter = new MouseAdapter() {
        
        @Override
        public void mouseDragged(MouseEvent e) {
            if(offset != null) {
                int x = getX() + e.getX() - offset.x;
                int y = getY() + e.getY() - offset.y;
                setLocation(x, y);
                setActive(false);
            }
        }
    };


    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isHasComponent() {
        return hasComponent;
    }

    public void setMouseIn(boolean in) {
        this.mouseIn = in;
    }

    public boolean getMouseIn() {
        return this.mouseIn;
    }

    public void setHasComponent(boolean hasComponent) {
        this.hasComponent = hasComponent;
    }

    public Image getImagem() {
        return imagem;
    }

    public void setImagem(Image imagem) {
        this.imagem = imagem;
    }

    public int getLARGURA_IMAGEM() {
        return LARGURA_IMAGEM;
    }

    public void setLARGURA_IMAGEM(int LARGURA_IMAGEM) {
        this.LARGURA_IMAGEM = LARGURA_IMAGEM;
    }

    public int getALTURA_IMAGEM() {
        return ALTURA_IMAGEM;
    }

    public void setALTURA_IMAGEM(int ALTURA_IMAGEM) {
        this.ALTURA_IMAGEM = ALTURA_IMAGEM;
    }

    public boolean isRun() {
        return run;
    }

    public void setRun(boolean run) {
        this.run = run;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
