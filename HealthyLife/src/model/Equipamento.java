package model;

public class Equipamento extends Produto {

    private String material;
    private int garantiaMeses;

    public Equipamento(int id, String nome, double preco, int estoque,
                       String material, int garantiaMeses) {
        super(id, nome, preco, estoque, "Equipamento");
        this.material = material;
        this.garantiaMeses = garantiaMeses;
    }

    @Override
    public String getTipo() {
        return "EQUIPAMENTO";
    }

    @Override
    public String getDescricao() {
        return super.getDescricao() + String.format(" | Material: %s | Garantia: %d meses",
                material, garantiaMeses);
    }

    public String getMaterial() { return material; }
    public int getGarantiaMeses() { return garantiaMeses; }
}
