package exception;

public class ProdutoNaoEncontradoException extends Exception {
    public ProdutoNaoEncontradoException(int id) {
        super("Produto com ID " + id + " não encontrado.");
    }
    public ProdutoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
