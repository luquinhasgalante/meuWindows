package Visao;

import Aplicativos.Notas;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.ImageIcon;

public final class BlocoDeNotas extends Atalho {
    
    private File arquivo = null;

    public BlocoDeNotas(){}

    public BlocoDeNotas(int i, int j, File arquivo) {
        super(i, j);
        
        this.arquivo = arquivo;
        
        this.setImagem(new ImageIcon(getClass().getResource("/Imagens/notas.png")).getImage());
        this.setOpaque(false);
        this.setHasComponent(true);
        this.setNome("Bloco de notas");
    }

    
    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        if(e.getButton() == MouseEvent.BUTTON1 && this.isRun()) new Notas(arquivo);
        repaint();
    }
}
