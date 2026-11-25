package ui;

import exception.ClienteNaoEncontradoException;
import exception.EstoqueInsuficienteException;
import exception.ProdutoNaoEncontradoException;
import model.*;
import service.ClienteService;
import service.PedidoService;
import service.ProdutoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private Scanner scanner;
    private ProdutoService produtoService;
    private ClienteService clienteService;
    private PedidoService pedidoService;

    public Menu() {
        this.scanner = new Scanner(System.in);
        this.produtoService = new ProdutoService();
        this.clienteService = new ClienteService();
        this.pedidoService = new PedidoService(produtoService, clienteService);
        carregarDadosIniciais();
    }

    private void carregarDadosIniciais() {
        produtoService.cadastrar(new Suplemento(1, "Whey Protein Chocolate", 189.90, 50, "Chocolate", 1.0, "12/2026"));
        produtoService.cadastrar(new Suplemento(2, "Creatina Monohidratada", 79.90, 30, "Sem sabor", 0.3, "06/2027"));
        produtoService.cadastrar(new Suplemento(3, "Pre-Treino Explosivo", 129.90, 20, "Frutas Vermelhas", 0.25, "08/2026"));
        produtoService.cadastrar(new Equipamento(4, "Halter 10kg", 89.90, 15, "Ferro fundido", 12));
        produtoService.cadastrar(new Equipamento(5, "Elastico de Resistencia Kit", 59.90, 40, "Latex natural", 6));
        produtoService.cadastrar(new Equipamento(6, "Tapete de Yoga Premium", 149.90, 10, "NBR antiderrapante", 12));
        produtoService.cadastrar(new Vestuario(7, "Camiseta Dry Fit", 69.90, 25, "M", "Preto", "Masculino"));
        produtoService.cadastrar(new Vestuario(8, "Legging Compressao", 99.90, 20, "P", "Rosa", "Feminino"));
        produtoService.cadastrar(new Vestuario(9, "Shorts Academia", 79.90, 18, "G", "Cinza", "Unissex"));
        clienteService.cadastrar("Joao Silva", "123.456.789-00", "joao@email.com", "(62) 99999-0001");
        clienteService.cadastrar("Maria Oliveira", "987.654.321-00", "maria@email.com", "(62) 98888-0002");
        System.out.println("\n>>> Dados iniciais carregados!\n");
    }

    public void iniciar() {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║       HEALTHY LIFE - Fitness Store   ║");
        System.out.println("╚══════════════════════════════════════╝");

        int opcao;
        do {
            exibirMenuPrincipal();
            opcao = lerInteiro("Escolha uma opcao: ");
            switch (opcao) {
                case 1 -> menuProdutos();
                case 2 -> menuClientes();
                case 3 -> menuPedidos();
                case 4 -> exibirRelatorio();
                case 0 -> System.out.println("\nEncerrando o sistema. Ate logo!");
                default -> System.out.println("Opcao invalida!");
            }
        } while (opcao != 0);

        scanner.close();
    }

    private void exibirMenuPrincipal() {
        System.out.println("\n========== MENU PRINCIPAL ==========");
        System.out.println("1. Gerenciar Produtos");
        System.out.println("2. Gerenciar Clientes");
        System.out.println("3. Gerenciar Pedidos");
        System.out.println("4. Relatorio Geral");
        System.out.println("0. Sair");
        System.out.println("=====================================");
    }

    private void menuProdutos() {
        int opcao;
        do {
            System.out.println("\n------- PRODUTOS -------");
            System.out.println("1. Listar todos os produtos");
            System.out.println("2. Listar produtos disponiveis");
            System.out.println("3. Buscar produto por ID");
            System.out.println("4. Cadastrar Suplemento");
            System.out.println("5. Cadastrar Equipamento");
            System.out.println("6. Cadastrar Vestuario");
            System.out.println("7. Aplicar desconto por categoria");
            System.out.println("0. Voltar");

            opcao = lerInteiro("Escolha: ");
            switch (opcao) {
                case 1 -> produtoService.listarTodos().forEach(System.out::println);
                case 2 -> produtoService.listarDisponiveis().forEach(p ->
                        System.out.printf("ID: %d | %s | R$ %.2f | Estoque: %d%n",
                                p.getId(), p.getNome(), p.getPreco(), p.getEstoque()));
                case 3 -> buscarProduto();
                case 4 -> cadastrarSuplemento();
                case 5 -> cadastrarEquipamento();
                case 6 -> cadastrarVestuario();
                case 7 -> aplicarDesconto();
                case 0 -> {}
                default -> System.out.println("Opcao invalida.");
            }
        } while (opcao != 0);
    }

    private void buscarProduto() {
        int id = lerInteiro("Digite o ID do produto: ");
        try {
            Produto p = produtoService.buscarPorId(id);
            System.out.println("Produto encontrado:");
            System.out.println(p);
        } catch (ProdutoNaoEncontradoException e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    private void cadastrarSuplemento() {
        System.out.println("\n-- Cadastrar Suplemento --");
        try {
            int id = lerInteiro("ID: ");
            System.out.print("Nome: "); String nome = scanner.nextLine();
            double preco = lerDouble("Preco: ");
            int estoque = lerInteiro("Estoque: ");
            System.out.print("Sabor: "); String sabor = scanner.nextLine();
            double peso = lerDouble("Peso (kg): ");
            System.out.print("Validade (MM/AAAA): "); String validade = scanner.nextLine();
            produtoService.cadastrar(new Suplemento(id, nome, preco, estoque, sabor, peso, validade));
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    private void cadastrarEquipamento() {
        System.out.println("\n-- Cadastrar Equipamento --");
        try {
            int id = lerInteiro("ID: ");
            System.out.print("Nome: "); String nome = scanner.nextLine();
            double preco = lerDouble("Preco: ");
            int estoque = lerInteiro("Estoque: ");
            System.out.print("Material: "); String material = scanner.nextLine();
            int garantia = lerInteiro("Garantia (meses): ");
            produtoService.cadastrar(new Equipamento(id, nome, preco, estoque, material, garantia));
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    private void cadastrarVestuario() {
        System.out.println("\n-- Cadastrar Vestuario --");
        try {
            int id = lerInteiro("ID: ");
            System.out.print("Nome: "); String nome = scanner.nextLine();
            double preco = lerDouble("Preco: ");
            int estoque = lerInteiro("Estoque: ");
            System.out.print("Tamanho (P/M/G/GG): "); String tam = scanner.nextLine();
            System.out.print("Cor: "); String cor = scanner.nextLine();
            System.out.print("Genero (Masculino/Feminino/Unissex): "); String genero = scanner.nextLine();
            produtoService.cadastrar(new Vestuario(id, nome, preco, estoque, tam, cor, genero));
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    private void aplicarDesconto() {
        System.out.print("Categoria (Suplemento/Equipamento/Vestuario): ");
        String categoria = scanner.nextLine();
        double percentual = lerDouble("Percentual de desconto (%): ");
        try {
            produtoService.aplicarDescontoGeral(categoria, percentual);
        } catch (ProdutoNaoEncontradoException | IllegalArgumentException e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    private void menuClientes() {
        int opcao;
        do {
            System.out.println("\n------- CLIENTES -------");
            System.out.println("1. Listar clientes");
            System.out.println("2. Cadastrar cliente");
            System.out.println("3. Ver notificacoes de um cliente");
            System.out.println("0. Voltar");

            opcao = lerInteiro("Escolha: ");
            switch (opcao) {
                case 1 -> clienteService.listarTodos().forEach(System.out::println);
                case 2 -> cadastrarCliente();
                case 3 -> verNotificacoes();
                case 0 -> {}
                default -> System.out.println("Opcao invalida.");
            }
        } while (opcao != 0);
    }

    private void cadastrarCliente() {
        System.out.println("\n-- Cadastrar Cliente --");
        try {
            System.out.print("Nome: "); String nome = scanner.nextLine();
            System.out.print("CPF: "); String cpf = scanner.nextLine();
            System.out.print("Email: "); String email = scanner.nextLine();
            System.out.print("Telefone: "); String tel = scanner.nextLine();
            clienteService.cadastrar(nome, cpf, email, tel);
        } catch (IllegalArgumentException e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    private void verNotificacoes() {
        int id = lerInteiro("ID do cliente: ");
        try {
            Cliente c = clienteService.buscarPorId(id);
            System.out.println("\nNotificacoes de " + c.getNome() + ":");
            if (c.getNotificacoes().isEmpty()) {
                System.out.println("Nenhuma notificacao.");
            } else {
                c.getNotificacoes().forEach(n -> System.out.println("  - " + n));
            }
        } catch (ClienteNaoEncontradoException e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    private void menuPedidos() {
        int opcao;
        do {
            System.out.println("\n------- PEDIDOS -------");
            System.out.println("1. Novo pedido");
            System.out.println("2. Listar todos os pedidos");
            System.out.println("3. Buscar pedido por ID");
            System.out.println("4. Cancelar pedido");
            System.out.println("0. Voltar");

            opcao = lerInteiro("Escolha: ");
            switch (opcao) {
                case 1 -> realizarPedido();
                case 2 -> pedidoService.listarTodos().forEach(Pedido::exibirDetalhes);
                case 3 -> buscarPedido();
                case 4 -> cancelarPedido();
                case 0 -> {}
                default -> System.out.println("Opcao invalida.");
            }
        } while (opcao != 0);
    }

    private void realizarPedido() {
        System.out.println("\n-- Novo Pedido --");
        int clienteId = lerInteiro("ID do cliente: ");

        List<int[]> itens = new ArrayList<>();
        String continuar = "s";
        while (continuar.equalsIgnoreCase("s")) {
            produtoService.listarDisponiveis().forEach(p ->
                    System.out.printf("ID: %d | %s | R$ %.2f | Estoque: %d%n",
                            p.getId(), p.getNome(), p.getPreco(), p.getEstoque()));
            int prodId = lerInteiro("ID do produto: ");
            int qtd = lerInteiro("Quantidade: ");
            itens.add(new int[]{prodId, qtd});
            System.out.print("Adicionar mais produtos? (s/n): ");
            continuar = scanner.nextLine().trim();
        }

        System.out.print("Forma de pagamento (PIX/Cartao/Boleto): ");
        String pagamento = scanner.nextLine();

        try {
            Pedido pedido = pedidoService.criarPedido(clienteId, itens, pagamento);
            System.out.println("\nPedido realizado com sucesso!");
            pedido.exibirDetalhes();
        } catch (ClienteNaoEncontradoException | ProdutoNaoEncontradoException e) {
            System.out.println("ERRO: " + e.getMessage());
        } catch (EstoqueInsuficienteException e) {
            System.out.println("ERRO DE ESTOQUE: " + e.getMessage());
        }
    }

    private void buscarPedido() {
        int id = lerInteiro("ID do pedido: ");
        try {
            pedidoService.buscarPorId(id).exibirDetalhes();
        } catch (ProdutoNaoEncontradoException e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    private void cancelarPedido() {
        int id = lerInteiro("ID do pedido a cancelar: ");
        try {
            pedidoService.cancelarPedido(id);
        } catch (ProdutoNaoEncontradoException | IllegalStateException e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    private void exibirRelatorio() {
        System.out.println("\n╔══════════════════════════════════╗");
        System.out.println("║       RELATORIO GERAL            ║");
        System.out.println("╠══════════════════════════════════╣");
        System.out.printf("║  Total de produtos: %-12d║%n", produtoService.getTotalProdutos());
        System.out.printf("║  Total de clientes: %-12d║%n", clienteService.getTotalClientes());
        System.out.printf("║  Total de pedidos:  %-12d║%n", pedidoService.getTotalPedidos());
        System.out.printf("║  Faturamento total: R$ %-9.2f║%n", pedidoService.calcularFaturamentoTotal());
        System.out.println("╚══════════════════════════════════╝");

        System.out.println("\nProdutos com estoque baixo (< 5 unidades):");
        produtoService.listarTodos().stream()
                .filter(p -> p.getEstoque() < 5)
                .forEach(p -> System.out.printf("  ! %s (estoque: %d)%n", p.getNome(), p.getEstoque()));
    }

    private int lerInteiro(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Digite um numero inteiro valido.");
            }
        }
    }

    private double lerDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(scanner.nextLine().trim().replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("Digite um numero valido.");
            }
        }
    }
}
