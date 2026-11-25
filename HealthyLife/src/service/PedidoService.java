package service;

import exception.ClienteNaoEncontradoException;
import exception.EstoqueInsuficienteException;
import exception.ProdutoNaoEncontradoException;
import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PedidoService {

    private List<Pedido> pedidos;
    private ProdutoService produtoService;
    private ClienteService clienteService;
    private int proximoId = 1;

    public PedidoService(ProdutoService produtoService, ClienteService clienteService) {
        this.pedidos = new ArrayList<>();
        this.produtoService = produtoService;
        this.clienteService = clienteService;
    }

    public Pedido criarPedido(int clienteId, List<int[]> itens, String formaPagamento)
            throws ClienteNaoEncontradoException, ProdutoNaoEncontradoException, EstoqueInsuficienteException {

        Cliente cliente = clienteService.buscarPorId(clienteId);
        Pedido pedido = new Pedido(proximoId++, cliente, formaPagamento);

        for (int[] item : itens) {
            int produtoId = item[0];
            int quantidade = item[1];
            Produto produto = produtoService.buscarPorId(produtoId);
            produtoService.verificarEstoque(produto, quantidade);
            produto.reduzirEstoque(quantidade);
            pedido.adicionarItem(new ItemPedido(produto, quantidade));
        }

        pedido.confirmar();
        pedidos.add(pedido);
        cliente.enviarNotificacao("Seu pedido #" + pedido.getId() +
                " foi confirmado! Total: R$ " + String.format("%.2f", pedido.getTotal()));

        return pedido;
    }

    public Pedido buscarPorId(int id) throws ProdutoNaoEncontradoException {
        return pedidos.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Pedido #" + id + " não encontrado."));
    }

    public void cancelarPedido(int pedidoId) throws ProdutoNaoEncontradoException {
        Pedido pedido = buscarPorId(pedidoId);
        if (pedido.getStatus() == StatusPedido.ENTREGUE) {
            throw new IllegalStateException("Não é possível cancelar um pedido já entregue.");
        }
        for (ItemPedido item : pedido.getItens()) {
            item.getProduto().setEstoque(item.getProduto().getEstoque() + item.getQuantidade());
        }
        pedido.cancelar();
        pedido.getCliente().enviarNotificacao("Seu pedido #" + pedidoId + " foi cancelado.");
        System.out.println("Pedido #" + pedidoId + " cancelado com sucesso.");
    }

    public List<Pedido> listarPorCliente(int clienteId) {
        return pedidos.stream()
                .filter(p -> p.getCliente().getId() == clienteId)
                .collect(Collectors.toList());
    }

    public List<Pedido> listarTodos() { return new ArrayList<>(pedidos); }

    public double calcularFaturamentoTotal() {
        return pedidos.stream()
                .filter(p -> p.getStatus() != StatusPedido.CANCELADO)
                .mapToDouble(Pedido::getTotal)
                .sum();
    }

    public int getTotalPedidos() { return pedidos.size(); }
}
