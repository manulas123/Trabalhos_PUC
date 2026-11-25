# Calculadora de Expressões em Haskell

Trabalho final da disciplina de **Paradigmas de Linguagens de Programação** (Projeto 1). A calculadora representa uma expressão aritmética como uma árvore e implementa as funções no **paradigma funcional**, em Haskell. Uma das funções também foi escrita em **Java**, para comparar com a versão imperativa.

O programa roda só no terminal. A `main` demonstra as funções com expressões definidas no próprio código, sem menu e sem leitura do teclado.

**Integrantes:** Emanuel Filipy, Matheus Caetano e Wesley Julio

## Funções

- **`avaliar :: Expr -> Maybe Int`:** calcula o resultado. Divisão por zero dá `Nothing`, e o erro se propaga para a expressão inteira. A divisão usa `quot`.
- **`mostrar :: Expr -> String`:** escreve a expressão com parênteses em toda operação.
- **`simplificar :: Expr -> Expr`:** aplica `x + 0 = x`, `0 + x = x`, `x * 1 = x` e `1 * x = x` na árvore inteira, sem mudar o resultado.
- **`contar :: Expr -> Int`:** conta quantas operações existem na expressão.

## Conceitos do paradigma funcional aplicados

- **Tipo algébrico:** `data Expr` com os construtores `Numero`, `Soma`, `Mult` e `Div`.
- **Recursão com casamento de padrões:** uma equação para cada construtor.
- **`Maybe` para tratar erro:** o erro é um valor, não uma variável nem uma exceção.
- **Imutabilidade:** `simplificar` devolve uma expressão nova e a original continua igual.
- **Funções puras:** só a `main` faz entrada e saída (`IO`).

## Estrutura do projeto

```
src/
├── calculadora.hs     # Programa principal em Haskell (tem a main)
└── Comparativo.java   # Função avaliar em Java, para comparação
docs/
├── Relatorio_Calculadora_Haskell.pdf
├── Relatorio_Calculadora_Haskell.docx
└── Guia_de_estudo.pdf # Explicação do código para a apresentação
```

## Como executar

Requisitos: **GHC** (instalado pelo [GHCup](https://www.haskell.org/ghcup/)) e **JDK**.

```bash
cd src

runghc calculadora.hs

javac Comparativo.java
java Comparativo
```

Sem o GHC instalado, dá para colar o conteúdo de `calculadora.hs` no [Haskell Playground](https://play.haskell.org) e rodar pelo navegador.
