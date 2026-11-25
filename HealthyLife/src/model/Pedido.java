package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private int id;
    private Cliente cliente;
    private List<ItemPedido> itens;
    private StatusPedido status;
    private LocalDateTime dataPedido;
    private String formaPagamento;

    public Pedido(int id, Cliente cliente, String formaPagamento) {
        this.id = id;
        this.cliente = cliente;
        this.formaPagamento = formaPagamento;
        this.itens = new ArrayList<>();
        this.status = StatusPedido.PENDENTE;
        this.dataPedido = LocalDateTime.now();
    }

    public void adicionarItem(ItemPedido item) {
        itens.add(item);
    }

    public double getTotal() {
        return itens.stream().mapToDouble(ItemPedido::getSubtotal).sum();
    }

    public void confirmar() { this.status = StatusPedido.CONFIRMADO; }
    public void cancelar() { this.status = StatusPedido.CANCELADO; }

    public int getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public List<ItemPedido> getItens() { return itens; }
    public StatusPedido getStatus() { return status; }
    public void setStatus(StatusPedido status) { this.status = status; }
    public String getFormaPagamento() { return formaPagamento; }

    public void exibirDetalhes() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        System.out.println("========================================");
        System.out.println("  PEDIDO #" + id);
        System.out.println("  Data: " + dataPedido.format(fmt));
        System.out.println("  Cliente: " + cliente.getNome());
        System.out.println("  Status: " + status);
        System.out.println("  Pagamento: " + formaPagamento);
        System.out.println("  Itens:");
        for (ItemPedido item : itens) {
            System.out.println(item);
        }
        System.out.printf("  TOTAL: R$ %.2f%n", getTotal());
        System.out.println("========================================");
    }
}
