package model;

public class Suplemento extends Produto {

    private String sabor;
    private double pesoKg;
    private String dataValidade;

    public Suplemento(int id, String nome, double preco, int estoque,
                      String sabor, double pesoKg, String dataValidade) {
        super(id, nome, preco, estoque, "Suplemento");
        this.sabor = sabor;
        this.pesoKg = pesoKg;
        this.dataValidade = dataValidade;
    }

    @Override
    public String getTipo() {
        return "SUPLEMENTO";
    }

    @Override
    public String getDescricao() {
        return super.getDescricao() + String.format(" | Sabor: %s | Peso: %.1fkg | Validade: %s",
                sabor, pesoKg, dataValidade);
    }

    public String getSabor() { return sabor; }
    public double getPesoKg() { return pesoKg; }
    public String getDataValidade() { return dataValidade; }
}
