package Sons;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Som {
    private static String click = "click.wav";
    private static String erro = "erro.wav";
    
    public static void padrao(String caminho) {
        try {
            URL url = Som.class.getResource(caminho);
            AudioInputStream audio = AudioSystem.getAudioInputStream(url);

            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getClick() {
        return click;
    }

    public static void setClick(String click) {
        Som.click = click;
    }

    public static String getErro() {
        return erro;
    }

    public static void setErro(String erro) {
        Som.erro = erro;
    }
}
