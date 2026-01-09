package Modelo;
import java.awt.Image;

public class Bloco {
    public int altura, largura;
    public int x, y;
    public Image imagem1;
    public Image imagem2;
    public Image imagem3;
    public Image preview;

    
    public Bloco(int largura, int altura, int x, int y, Image imagem1, Image imagem2, Image imagem3) {
        this.largura = largura;
        this.altura = altura;
        this.x = x;
        this.y = y;
        this.imagem1 = imagem1;
        this.imagem2 = imagem2;
        this.imagem3 = imagem3;
    }

    public Bloco(int largura, int altura, int x, int y, Image imagem1, Image imagem2, Image imagem3, Image preview) {
        this.largura = largura;
        this.altura = altura;
        this.x = x;
        this.y = y;
        this.imagem1 = imagem1;
        this.imagem2 = imagem2;
        this.imagem3 = imagem3;
        this.preview = preview;
    }

    public Bloco(int largura, int altura, int x, int y, Image imagem) {
        this.largura = largura;
        this.altura = altura;
        this.x = x;
        this.y = y;
        this.imagem1 = imagem;
    }
}
