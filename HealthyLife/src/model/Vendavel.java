package model;

public interface Vendavel {
    double getPreco();
    String getDescricao();
    boolean isDisponivel();
    void aplicarDesconto(double percentual);
}
