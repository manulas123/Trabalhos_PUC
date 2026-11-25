package service;

import exception.ClienteNaoEncontradoException;
import model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteService {

    private List<Cliente> clientes;
    private int proximoId = 1;

    public ClienteService() {
        this.clientes = new ArrayList<>();
    }

    public Cliente cadastrar(String nome, String cpf, String email, String telefone) {
        boolean cpfExiste = clientes.stream().anyMatch(c -> c.getCpf().equals(cpf));
        if (cpfExiste) {
            throw new IllegalArgumentException("CPF já cadastrado: " + cpf);
        }
        Cliente cliente = new Cliente(proximoId++, nome, cpf, email, telefone);
        clientes.add(cliente);
        System.out.println("Cliente cadastrado: " + nome);
        return cliente;
    }

    public Cliente buscarPorId(int id) throws ClienteNaoEncontradoException {
        return clientes.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ClienteNaoEncontradoException(id));
    }

    public List<Cliente> listarTodos() { return new ArrayList<>(clientes); }
    public int getTotalClientes() { return clientes.size(); }
}
