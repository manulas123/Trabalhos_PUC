package service;

import exception.EstoqueInsuficienteException;
import exception.ProdutoNaoEncontradoException;
import model.Produto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProdutoService {

    private List<Produto> produtos;

    public ProdutoService() {
        this.produtos = new ArrayList<>();
    }

    public void cadastrar(Produto produto) {
        produtos.add(produto);
        System.out.println("Produto cadastrado: " + produto.getNome());
    }

    public Produto buscarPorId(int id) throws ProdutoNaoEncontradoException {
        return produtos.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ProdutoNaoEncontradoException(id));
    }

    public List<Produto> listarTodos() {
        return new ArrayList<>(produtos);
    }

    public List<Produto> listarDisponiveis() {
        return produtos.stream()
                .filter(Produto::isDisponivel)
                .collect(Collectors.toList());
    }

    public List<Produto> buscarPorCategoria(String categoria) {
        return produtos.stream()
                .filter(p -> p.getCategoria().equalsIgnoreCase(categoria))
                .collect(Collectors.toList());
    }

    public void verificarEstoque(Produto produto, int quantidade) throws EstoqueInsuficienteException {
        if (produto.getEstoque() < quantidade) {
            throw new EstoqueInsuficienteException(produto.getNome(), quantidade, produto.getEstoque());
        }
    }

    public void listarComDetalhes() {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }
        for (Produto p : produtos) {
            System.out.println(p);
            System.out.println("  Detalhes: " + p.getDescricao());
            System.out.println("  Disponível: " + (p.isDisponivel() ? "Sim" : "Não"));
            System.out.println();
        }
    }

    public void aplicarDescontoGeral(String categoria, double percentual) throws ProdutoNaoEncontradoException {
        List<Produto> filtrados = buscarPorCategoria(categoria);
        if (filtrados.isEmpty()) {
            throw new ProdutoNaoEncontradoException("Nenhum produto encontrado na categoria: " + categoria);
        }
        for (Produto p : filtrados) {
            p.aplicarDesconto(percentual);
        }
        System.out.printf("Desconto de %.0f%% aplicado em %d produto(s) da categoria %s.%n",
                percentual, filtrados.size(), categoria);
    }

    public int getTotalProdutos() { return produtos.size(); }
}
