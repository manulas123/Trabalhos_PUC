# 💪 Healthy Life — Sistema de Vendas de Produtos Fitness

Sistema de vendas de produtos fitness desenvolvido em **Java** com interface via **console (terminal)**, como trabalho da disciplina de **Programação Orientada a Objetos (POO)**.

Simula uma loja virtual que vende suplementos alimentares, equipamentos de ginástica e roupas esportivas.

## Funcionalidades

- **Produtos:** listar (todos ou só com estoque), buscar por ID, cadastrar suplementos/equipamentos/vestuário e aplicar desconto por categoria.
- **Clientes:** cadastro com nome, CPF, e-mail e telefone (sem CPF duplicado), listagem e consulta de notificações recebidas.
- **Pedidos:** criar pedido com vários itens, validação de estoque, baixa automática no estoque, notificação ao cliente com o valor total, cancelamento (devolve o estoque) e listagem/busca.
- **Relatório geral** de vendas.

## Conceitos de POO aplicados

- **Abstração e herança:** classe abstrata `Produto` com as subclasses `Suplemento`, `Equipamento` e `Vestuario`.
- **Interfaces:** `Vendavel` e `Notificavel`.
- **Polimorfismo:** tratamento uniforme dos produtos por meio da classe/interface base.
- **Encapsulamento:** atributos privados com acesso por getters e métodos.
- **Tratamento de exceções:** `ProdutoNaoEncontradoException`, `ClienteNaoEncontradoException` e `EstoqueInsuficienteException`.
- **Camada de serviço:** regras de negócio separadas da interface (`ProdutoService`, `ClienteService`, `PedidoService`).

## Estrutura do projeto

```
src/
├── Main.java
├── model/        # Entidades e interfaces (Produto, Cliente, Pedido, ...)
├── service/      # Regras de negócio
├── exception/    # Exceções personalizadas
└── ui/           # Menu de console
docs/
└── Relatorio_HealthyLife_POO.docx   # Relatório do trabalho
```

## Como executar

Requisito: **JDK 11 ou superior**.

Linux / macOS:

```bash
javac -d out $(find src -name "*.java")
java -cp out Main
```

Windows (PowerShell):

```powershell
javac -d out (Get-ChildItem -Recurse src -Filter *.java).FullName
java -cp out Main
```

## Documentação

O relatório completo do trabalho está em [`docs/Relatorio_HealthyLife_POO.docx`](docs/Relatorio_HealthyLife_POO.docx).

## Autor

Emanuel Filipy
