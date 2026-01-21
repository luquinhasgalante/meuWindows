package Jogos.Dinossauro;

import Sons.Som;
import java.awt.BorderLayout;
import javax.swing.*;

public class Game extends JFrame {
	public Game() {
		int largura = 720;
		int altura = 250;

		Menu menu = new Menu(this);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(largura, altura);
		this.setLayout(new BorderLayout());
		this.setResizable(false);
		setUndecorated(true);
		this.getRootPane().setWindowDecorationStyle(1);
		
		this.add(menu);
		this.setLocationRelativeTo(null);
        Som.padrao(Som.getClick());
		
		
		menu.requestFocus();
		this.pack();
		this.setVisible(true);
	}
}
