package Aplicativos;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class Navegador extends JFrame {
    private String url;

    public Navegador(String url) {
        
        JFXPanel jfxPanel = new JFXPanel();
        this.setUndecorated(true);
        this.getRootPane().setWindowDecorationStyle(1);
        this.setTitle("BubbleBrowser");
        this.setIconImage(new ImageIcon(getClass().getResource("/Imagens/Browser.png")).getImage());
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.add(jfxPanel);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                Platform.runLater(() -> jfxPanel.setScene(null));
            }
        });

        Platform.runLater(() -> {
            WebView webView = new WebView();
            WebEngine engine = webView.getEngine();

            engine.load(url != null ? url : "https://www.google.com");

            jfxPanel.setScene(new Scene(webView));
        });
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
}

