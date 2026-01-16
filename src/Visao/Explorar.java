package Visao;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Explorar extends JFrame {
    
    private Pasta pasta;
    private JPanel painel;
    private JLabel label;
    private JScrollPane scroll;

    public Explorar(Pasta pasta) {
        this.pasta = pasta;
        this.setTitle(pasta.getNome());
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(640, 300);
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);
        this.getRootPane().setWindowDecorationStyle(1);
        
        painel = new JPanel();
        painel.setPreferredSize(new Dimension(640, 400));
        painel.setLayout(null);
        
        scroll = new JScrollPane(painel);
        scroll.setBounds(0, 0, 630, 260);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        label = new JLabel("Ainda não há arquivos nesta pasta.");
        label.setBounds((getWidth() / 2) - (198 / 2), 15, 198, 30);
        painel.add(label);
        
        this.setLayout(null);
        this.add(scroll);
        
        
        exibirAtalhos();
        this.setVisible(true);
    }


    public void exibirAtalhos() {
        if(!pasta.getAtalhos().isEmpty()) {
            painel.remove(label);
            for(Atalho a : pasta.getAtalhos()) {
                a.setRun(false);
                a.setActive(false);
                painel.add(a);
            }
        }
    }
}
