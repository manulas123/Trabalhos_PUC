package model;

public class ItemPedido {

    private Produto produto;
    private int quantidade;
    private double precoUnitario;

    public ItemPedido(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = produto.getPreco();
    }

    public double getSubtotal() {
        return precoUnitario * quantidade;
    }

    public Produto getProduto() { return produto; }
    public int getQuantidade() { return quantidade; }
    public double getPrecoUnitario() { return precoUnitario; }

    @Override
    public String toString() {
        return String.format("  - %s x%d = R$ %.2f", produto.getNome(), quantidade, getSubtotal());
    }
}
