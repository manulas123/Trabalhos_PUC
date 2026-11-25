package exception;

public class ClienteNaoEncontradoException extends Exception {
    public ClienteNaoEncontradoException(int id) {
        super("Cliente com ID " + id + " não encontrado.");
    }
}
