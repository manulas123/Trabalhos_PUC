package model;

public class Vestuario extends Produto {

    private String tamanho;
    private String cor;
    private String genero;

    public Vestuario(int id, String nome, double preco, int estoque,
                     String tamanho, String cor, String genero) {
        super(id, nome, preco, estoque, "Vestuário");
        this.tamanho = tamanho;
        this.cor = cor;
        this.genero = genero;
    }

    @Override
    public String getTipo() {
        return "VESTUARIO";
    }

    @Override
    public String getDescricao() {
        return super.getDescricao() + String.format(" | Tam: %s | Cor: %s | Gênero: %s",
                tamanho, cor, genero);
    }

    public String getTamanho() { return tamanho; }
    public String getCor() { return cor; }
    public String getGenero() { return genero; }
}
