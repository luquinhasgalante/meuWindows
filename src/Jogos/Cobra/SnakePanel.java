package Jogos.Cobra;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SnakePanel extends JPanel implements ActionListener {
    
    private static final int ALTURA = 400;
    private static final int LARGURA = 700;
    private static final int TAMANHO_PIXEL = 25;
    private static final int QTD_PIXELS = (LARGURA * ALTURA) / TAMANHO_PIXEL;
    private static final int DELAY = 75;
    private final int x[] = new int[QTD_PIXELS];
    private final int y[] = new int[QTD_PIXELS];
    private int tamanho = 6;
    private int comidas;
    private int comidaX;
    private int comidaY;
    private char direcao = 'D';
    private boolean rodando = false;
    private Timer timer;
    private Random random;

    public SnakePanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(LARGURA, ALTURA));
        this.setBackground(Color.WHITE);
        this.setFocusable(true);
        this.addKeyListener(new Tecla());
        startGame();
    }

    public void startGame() {
        tamanho = 6;
        comidas = 0;
        direcao = 'D';
        rodando = true;
        x[0] = 0;
        y[0] = 0;
        if(timer != null) {
            timer.stop();
        }

        novaComida();
        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        Image img = new ImageIcon(getClass().getResource("/Jogos/Dinossauro/img/background_preview_2.png")).getImage();
        g.drawImage(img, 0, 0, LARGURA, ALTURA, null);
        g.setColor(Color.gray);
        for(int i = 0; i < ALTURA; i++){
            g.drawLine(i * TAMANHO_PIXEL, 0, i * TAMANHO_PIXEL, ALTURA);
        }
        for(int i = 0; i < LARGURA; i++){
            g.drawLine(0, i * TAMANHO_PIXEL, LARGURA, i * TAMANHO_PIXEL);
        }
        g.setColor(Color.GREEN);
        g.fillOval(comidaX, comidaY, TAMANHO_PIXEL, TAMANHO_PIXEL);
        
        Color corInicio = new Color(0, 0, 255);
        Color corFinal = new Color(0, 255, 0);
        for(int i = 0; i < tamanho; i++) {
            float t = (float) i / (tamanho - 1);
            int green = (int) (corInicio.getGreen() + t * (corFinal.getGreen() - corInicio.getGreen()));
            int blue = (int) (corInicio.getBlue() + t *(corFinal.getBlue() - corInicio.getBlue()));
            g.setColor(new Color(0, green, blue));
            g.fillRect(x[i], y[i], TAMANHO_PIXEL, TAMANHO_PIXEL);
        }
        if(!rodando) gameOver(g);
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        FontMetrics fm = getFontMetrics(g.getFont());
        String score = "Score: " + comidas;
        g.drawString(score, (LARGURA - fm.stringWidth(score)) / 2, 30);
    }

    public void novaComida() {
        comidaX = random.nextInt( (int) (LARGURA / TAMANHO_PIXEL)) * TAMANHO_PIXEL;
        comidaY = random.nextInt( (int) (ALTURA / TAMANHO_PIXEL)) * TAMANHO_PIXEL;
    }

    public void mover() {
       for(int i = tamanho; i > 0; i--) {
        x[i] = x[i - 1];
        y[i] = y[i - 1];
       }

        switch(direcao){
        case 'C':
            y[0] = y[0] - TAMANHO_PIXEL;
            break;
        case 'B':
            y[0] = y[0] + TAMANHO_PIXEL;
            break;
        case 'D':
            x[0] = x[0] + TAMANHO_PIXEL;
            break;
        case 'E':
            x[0] = x[0] - TAMANHO_PIXEL;
            break;
       }
    }

    public void checarComida() {
        if((x[0] == comidaX) && (y[0] == comidaY)) {
            comidas++;
            tamanho++;
            novaComida();
        }
    }

    public void checarColisao() {
       for(int i = tamanho; i > 0; i--) {
        if((x[0] == x[i]) && (y[0] == y[i])) {
            rodando = false;
        }

        if(x[0] <= -1) rodando = false;

        if(x[0] > LARGURA - 1) rodando = false;

        if(y[0] <= -1) rodando = false;

        if(y[0] >= ALTURA - 1) rodando = false;

       }

    }

    public void gameOver(Graphics g) {
        g.setColor(new Color(0, 123, 210));
        g.setFont(new Font("Arial", Font.BOLD, 75));
        FontMetrics fm = getFontMetrics(g.getFont());
        g.drawString("Game Over!", (LARGURA - fm.stringWidth("Game Over!")) / 2, ALTURA / 2);
        g.setColor(new Color(255, 255, 255));
        g.setFont(new Font("Arial", Font.BOLD, 20));
        fm = getFontMetrics(g.getFont());
        g.drawString("ESPAÇO para recomeçar", (LARGURA - fm.stringWidth("ESPAÇO para recomeçar")) / 2, (ALTURA / 2) + 40);
    }

    @Override   
    public void actionPerformed(ActionEvent e) {
        if(rodando) {
            mover();
            checarComida();
            checarColisao();
        }
        repaint();
    }

    private class Tecla extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if(direcao != 'D') direcao = 'E';
                    break;
                case KeyEvent.VK_RIGHT:
                    if(direcao != 'E') direcao = 'D';
                    break;
                case KeyEvent.VK_UP:
                    if(direcao != 'B') direcao = 'C';
                    break;
                case KeyEvent.VK_DOWN:
                    if(direcao != 'C') direcao = 'B';
                    break;
            }
            if(rodando == false) {
                if(e.getKeyCode() == KeyEvent.VK_SPACE) startGame();
            }
        }
    }
}
