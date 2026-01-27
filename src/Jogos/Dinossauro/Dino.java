package Jogos.Dinossauro;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import Modelo.Bloco;

public class Dino extends JPanel implements ActionListener, KeyListener{
    int largura;
    int altura;

    int velocidade = 20;

    Image personagemAndando;
    Image personagemPulando;
    Image imgObstaculo1;
    Image imgObstaculo2;
    Image imgObstaculo3;
    Image imgObstaculo4;
    Image imgObstaculo5;
    Image personagemMorto;
    Image placar;
    Image placarRecorde;
    Image reiniciar;
    Image pauseContinuar;
    Image pauseMenu;
    Timer loopTimer;
    Timer obsTimer;
    Timer velTimer;

    JFrame frame;
    
    
    int alturaPersonagem;
    int larguraPersonagem;
    int personagemX;
    int personagemY;
    
    int alturaObs = 30;
    int larguraObs = 30;
    int obsX;
    int obsY;
    
    int recorde = 0;
    
    int velocidadeY = 0;
    int gravidade = 2;
    
    int contadorPause = 0;
    
    Bloco personagemBloco;
    
    Bloco fundo1;
    Bloco fundo2;
    Bloco fundo_1;
    Bloco fundo2_1;
    Bloco fundo_2;
    Bloco fundo2_2;
    
    Bloco blocoPauseMenu;
    Bloco blocoPauseContinuar;
    Bloco pauseImage;


    Bloco placarBloco;
    Bloco placarRecordeBloco;

    ArrayList<Bloco> arrayObs;
    ArrayList<Bloco> pauseImages;

    int score = 0;

    boolean gameOver = false;
    boolean pause = false;

    public Dino(Menu menu, JFrame frame) {

        this.frame = frame;

        this.altura = 250;
        this.largura = 720;

        this.setFocusable(true);
        this.addKeyListener(this);

        this.alturaPersonagem = menu.personagemEscolhido.altura;
        this.larguraPersonagem = menu.personagemEscolhido.largura;
        this.personagemX = 50;
        this.personagemY = altura - alturaPersonagem;
        this.personagemY = altura - alturaPersonagem;
        this.obsX = largura;
        this.obsY = altura - alturaObs;

        arrayObs = new ArrayList<Bloco>();

        this.personagemAndando = menu.personagemEscolhido.imagem1;
        this.personagemPulando = menu.personagemEscolhido.imagem2;
        this.imgObstaculo1 = new ImageIcon(getClass().getResource("img/spike A.png")).getImage();
        this.imgObstaculo2 = new ImageIcon(getClass().getResource("img/spike B.png")).getImage();
        this.imgObstaculo3 = new ImageIcon(getClass().getResource("img/spike C.png")).getImage();
        this.imgObstaculo4 = new ImageIcon(getClass().getResource("img/spike D.png")).getImage();
        this.imgObstaculo5 = new ImageIcon(getClass().getResource("img/lanca.png")).getImage();
        this.placar = new ImageIcon(getClass().getResource("img/pontos.png")).getImage();
        this.placarRecorde = new ImageIcon(getClass().getResource("img/recorde.png")).getImage();
        this.reiniciar = new ImageIcon(getClass().getResource("img/gameover.png")).getImage();
        this.pauseContinuar = new ImageIcon(getClass().getResource("img/pause_continuar.png")).getImage();
        this.pauseMenu = new ImageIcon(getClass().getResource("img/pause_menu.png")).getImage();
        this.personagemMorto = menu.personagemEscolhido.imagem3;

        this.blocoPauseContinuar = new Bloco(largura, altura, 0, 0, pauseContinuar);
        this.blocoPauseMenu = new Bloco(largura, altura, 0, 0, pauseMenu);
        pauseImages = new ArrayList<>();
        pauseImages.add(blocoPauseContinuar);
        pauseImages.add(blocoPauseMenu);

        pauseImage = pauseImages.get(contadorPause);
        
        
        Image background = menu.fundoEscolhido.imagem1;
        Image background_1 = menu.fundoEscolhido.imagem2;
        Image background_2 = menu.fundoEscolhido.imagem3;


        personagemBloco = new Bloco(larguraPersonagem, alturaPersonagem, personagemX, personagemY, personagemAndando);
        fundo1 = new Bloco(largura, altura, 0, 0, background);
        fundo2 = new Bloco(largura, altura, fundo1.largura, 0, background);
        fundo_1 = new Bloco(largura, altura, 0, 0, background_1);
        fundo2_1 = new Bloco(largura, altura, fundo_1.largura, 0, background_1);
        fundo_2 = new Bloco(largura, altura, 0, 0, background_2);
        fundo2_2 = new Bloco(largura, altura, fundo_2.largura, 0, background_2);

        placarRecordeBloco = new Bloco(130, 25, 400, 10, placarRecorde);
        placarBloco = new Bloco(150, 25, 30, 10, placar);


        this.setPreferredSize(new Dimension(largura, altura));
        this.setSize(new Dimension(largura, altura));
        

        velTimer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                velocidade++;
            }
        });
        velTimer.start();

        obsTimer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                botarObs();
            } 
        });
        obsTimer.start();

        loopTimer = new Timer(1000/60, this);
        loopTimer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public boolean intersects(Bloco a, Bloco b) {
        return a.x < b.x + b.largura && a.x + a.largura > b.x && a.y < b.y + b.altura && a.y + a.altura > b.y;
    }

    public void botarObs() {
        double chance = Math.random();
        
        if(chance < .2) {
            Bloco obs = new Bloco(larguraObs, alturaObs, obsX, obsY, imgObstaculo1);
            arrayObs.add(obs);
        }
        else if(chance < .4) {
            Bloco obs = new Bloco(larguraObs, alturaObs, obsX, obsY, imgObstaculo2);
            arrayObs.add(obs);
        }
        else if(chance < .6) {
            Bloco obs = new Bloco(larguraObs, alturaObs, obsX, obsY, imgObstaculo3);
            arrayObs.add(obs);
        }
        else if(chance < .8) {
            Bloco obs = new Bloco(larguraObs, alturaObs, obsX, obsY, imgObstaculo4);
            arrayObs.add(obs);
        }else{
            Bloco obs = new Bloco(larguraObs + 20, alturaObs, obsX, obsY - 36, imgObstaculo5);
            arrayObs.add(obs);
        }
    }

    public void draw(Graphics g) {
        g.drawImage(fundo1.imagem1, fundo1.x, fundo1.y, fundo1.largura, fundo1.altura, null);
        g.drawImage(fundo2.imagem1, fundo2.x, fundo2.y, fundo2.largura, fundo2.altura, null);
        g.drawImage(fundo_1.imagem1, fundo_1.x, fundo_1.y, fundo_1.largura, fundo_1.altura, null);
        g.drawImage(fundo2_1.imagem1, fundo2_1.x, fundo2_1.y, fundo2_1.largura, fundo2_1.altura, null);
        g.drawImage(fundo_2.imagem1, fundo_2.x, fundo_2.y, fundo_2.largura, fundo_2.altura, null);
        g.drawImage(fundo2_2.imagem1, fundo2_2.x, fundo2_2.y, fundo2_2.largura, fundo2_2.altura, null);
        g.drawImage(personagemBloco.imagem1, personagemBloco.x, personagemBloco.y, personagemBloco.largura, personagemBloco.altura, null );

        for(int i = 0; i < arrayObs.size(); i++) {
            Bloco obs = arrayObs.get(i);
            g.drawImage(obs.imagem1, obs.x, obs.y, obs.largura, obs.altura, null);
        }

        
        if(gameOver) {
            g.drawImage(reiniciar, 0, 0, largura, altura, null);
        }else {
            g.setColor(Color.WHITE);
            g.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
            g.drawImage(placarBloco.imagem1, placarBloco.x, placarBloco.y, placarBloco.largura, placarBloco.altura, null);
            g.drawString(" " + score, 180, 33);
            g.drawImage(placarRecordeBloco.imagem1, placarRecordeBloco.x, placarRecordeBloco.y, placarRecordeBloco.largura, placarRecordeBloco.altura, null);
            g.drawString("" + recorde, 550, 33);
        }

        if(pause) {
            g.drawImage(pauseImage.imagem1, pauseImage.x, pauseImage.y, pauseImage.largura, pauseImage.altura, null);
        }
    }

    public void mover() {
        velocidadeY += gravidade;
        personagemBloco.y += velocidadeY;

        if(personagemBloco.y > (altura - personagemBloco.altura)) {
            personagemBloco.y = (altura - personagemBloco.altura);
            velocidadeY = 0;
            personagemBloco.imagem1 = personagemAndando;
        }

        if(fundo1.x <= - largura) {
            fundo1.x = fundo2.x + fundo1.largura;
        }

        if(fundo2.x <= - largura) {
            fundo2.x = fundo1.x + fundo1.largura;
        }

        if(fundo_1.x <= -largura) {
            fundo_1.x = fundo2_1.x + fundo_1.largura;
        }

        if(fundo2_1.x <= -largura) {
            fundo2_1.x = fundo_1.x + fundo_1.largura;
        }

        if(fundo_2.x <= -largura) {
            fundo_2.x = fundo2_2.x + fundo2_2.largura;
        }
        
        if(fundo2_2.x <= -largura) {
            fundo2_2.x = fundo_2.x + fundo_2.largura;
        }

        for(Bloco b : arrayObs) {
            b.x -= velocidade;
    
            if(intersects(personagemBloco, b)) {
                gameOver = true;
                personagemBloco.imagem1 = personagemMorto;
            }
        }

        fundo1.x -= (velocidade - 5);
        fundo2.x -= (velocidade - 5);

        fundo_1.x -= (velocidade - 3);
        fundo2_1.x -= (velocidade - 3);

        fundo_2.x -= velocidade;
        fundo2_2.x -= velocidade;

        score++;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mover();
        repaint();

        if(gameOver) {
            loopTimer.stop();
            obsTimer.stop();
        }
        
        if(pause) {
            loopTimer.stop();
            obsTimer.stop();
            velTimer.stop();
        }
    }
    
    @Override
    public void keyPressed(KeyEvent e) {

        
        if(pause) {
            if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_UP) {
                contadorPause++;
                pauseImage = pauseImages.get(contadorPause % 2);
                repaint();
            }

            if(e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_SPACE) {
                if(contadorPause % 2 == 0) {
                    pause = false;
                    loopTimer.start();
                    obsTimer.start();
                    velTimer.start();
                }else {
                    int largura = 720;
                    int altura = 250;

                    JFrame janela = new JFrame();
                    Menu menu = new Menu(janela);
                    janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    janela.setSize(largura, altura);
                    janela.setLayout(new BorderLayout());
                    janela.setResizable(false);
                    janela.setUndecorated(true);
                    janela.getRootPane().setWindowDecorationStyle(1);
                    
                    janela.add(menu);
                    janela.setLocationRelativeTo(null);
                    
                    
                    menu.requestFocus();
                    janela.pack();
                    janela.setVisible(true);
                    this.loopTimer.stop();
                    this.obsTimer.stop();
                    this.velTimer.stop();
                    this.frame.dispose();
                }
            }
        }else {
            
            if(e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_UP) {
                if(personagemBloco.y == personagemY) {
                    velocidadeY = -20;
                    personagemBloco.imagem1 = personagemPulando;
                }
                
                if(gameOver) {
                    gameOver = false;
                    fundo1.x = 0;
                    fundo2.x = fundo2.largura;
                    
                    fundo_2.x = 0;
                    fundo2_2.x = fundo_2.largura;
                    
                    recorde = Math.max(recorde, score);
                    score = 0;
                    arrayObs.clear();
                    velocidade = 20;
                    
                    loopTimer.start();
                    obsTimer.start();
                    
                }
            }
            
            if(e.getKeyCode() == KeyEvent.VK_DOWN) {
                if(personagemBloco.y == (altura - personagemBloco.altura)) {
                    personagemBloco.altura = 35;
                    personagemBloco.y = (altura - personagemBloco.altura);
                }
                else {
                    gravidade = 10;
                }
                
                if(gameOver) {
                    gameOver = false;
                    fundo1.x = 0;
                    fundo2.x = fundo2.largura;
                    
                    fundo_2.x = 0;
                    fundo2_2.x = fundo_2.largura;
                    
                    recorde = Math.max(recorde, score);
                    score = 0;
                    arrayObs.clear();
                    velocidade = 20;
                    
                    loopTimer.start();
                    obsTimer.start();
                    
                }
            }
            
            if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                
                if(!gameOver) {
                    pause = true;
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        personagemBloco.altura = alturaPersonagem;
        gravidade = 2;
    }

}

