package Visao;

import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;

import Aplicativos.MostraImagem;
import java.awt.Dimension;

public class Imagem extends Atalho {

    private Pasta pasta;

    public Imagem(int i, int j, Pasta pasta) {
        super(i, j);

        this.pasta = pasta;
        
        this.setOpaque(false);
        this.setHasComponent(true);
    }
    
    @Override
    public void abrirMenu(MouseEvent e) {
        super.abrirMenu(e);

        JMenuItem mudarFundo = new JMenuItem("Selecionar como plano de fundo");
        mudarFundo.addActionListener(e1 -> setarPlano());
        this.getMenu().setPreferredSize(new Dimension(200, 50));
        this.getMenu().add(mudarFundo);

        this.getMenu().show(this, e.getX(), e.getY());
    }

    public void setarPlano() {
        if(pasta.getParent() instanceof TelaPrincipal tela) {
            tela.setPlanoFundo(this.getImagem());
            tela.setCorSolida(false);
            tela.repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        if(e.getButton() == MouseEvent.BUTTON1 && this.isRun()) new MostraImagem(this);
        repaint();
    }
}
