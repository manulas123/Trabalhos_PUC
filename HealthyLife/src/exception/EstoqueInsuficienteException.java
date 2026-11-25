package exception;

public class EstoqueInsuficienteException extends Exception {
    public EstoqueInsuficienteException(String nomeProduto, int solicitado, int disponivel) {
        super(String.format("Estoque insuficiente para '%s'. Solicitado: %d, Disponível: %d",
                nomeProduto, solicitado, disponivel));
    }
}
