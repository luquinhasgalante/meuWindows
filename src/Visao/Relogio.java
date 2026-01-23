package Visao;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Relogio extends JPanel implements ActionListener {

    private int LARGURA = 150;
    private Timer timer;
    private String horario = "";

    public Relogio(GerenciadorDeTarefas g) {
        initComponents(g);
    }

    public void initComponents(GerenciadorDeTarefas g) {
        
        this.setBounds(g.getLARGURA() - LARGURA, 0, LARGURA, g.getALTURA());
        this.setOpaque(false);
        timer = new Timer(1000, this);
        timer.start();
    }

    private void desenharHorario(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(new Color(255, 255, 255, 64));
        g2d.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.BLACK);
        g.drawString("Horario: " + atualizarHorario(),24, getParent().getHeight() - 15);
    }

    public String atualizarHorario() {
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        horario = time.format(formatter)+"";
        return horario;
    }

    public void paintComponent(Graphics g) {
        desenharHorario(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        atualizarHorario();
        revalidate();
        repaint();
    }

}






