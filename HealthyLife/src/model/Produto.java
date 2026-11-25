package model;

public abstract class Produto implements Vendavel {

    private int id;
    private String nome;
    private double preco;
    private int estoque;
    private String categoria;

    public Produto(int id, String nome, double preco, int estoque, String categoria) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
        this.categoria = categoria;
    }

    public abstract String getTipo();

    @Override
    public double getPreco() {
        return preco;
    }

    @Override
    public boolean isDisponivel() {
        return estoque > 0;
    }

    @Override
    public void aplicarDesconto(double percentual) {
        if (percentual <= 0 || percentual >= 100) {
            throw new IllegalArgumentException("Percentual de desconto inválido: " + percentual);
        }
        this.preco = this.preco * (1 - percentual / 100);
    }

    @Override
    public String getDescricao() {
        return String.format("[%s] %s - R$ %.2f (Estoque: %d)", getTipo(), nome, preco, estoque);
    }

    public void reduzirEstoque(int quantidade) {
        if (quantidade > estoque) {
            throw new IllegalStateException("Estoque insuficiente para o produto: " + nome);
        }
        this.estoque -= quantidade;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public void setPreco(double preco) { this.preco = preco; }
    public int getEstoque() { return estoque; }
    public void setEstoque(int estoque) { this.estoque = estoque; }
    public String getCategoria() { return categoria; }

    @Override
    public String toString() {
        return String.format("ID: %d | %s", id, getDescricao());
    }
}
