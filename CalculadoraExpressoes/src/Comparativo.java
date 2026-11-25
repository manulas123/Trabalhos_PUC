public class Comparativo {

    static class No {
        char tipo;
        int valor;
        No esquerda;
        No direita;

        No(char tipo, int valor, No esquerda, No direita) {
            this.tipo = tipo;
            this.valor = valor;
            this.esquerda = esquerda;
            this.direita = direita;
        }
    }

    static No numero(int v) {
        return new No('n', v, null, null);
    }

    static No soma(No a, No b) {
        return new No('+', 0, a, b);
    }

    static No mult(No a, No b) {
        return new No('*', 0, a, b);
    }

    static No div(No a, No b) {
        return new No('/', 0, a, b);
    }

    static boolean erro = false;

    static int avaliar(No no) {
        if (no.tipo == 'n') {
            return no.valor;
        }

        int esquerda = avaliar(no.esquerda);
        if (erro) {
            return 0;
        }

        int direita = avaliar(no.direita);
        if (erro) {
            return 0;
        }

        int resultado = 0;
        if (no.tipo == '+') {
            resultado = esquerda + direita;
        } else if (no.tipo == '*') {
            resultado = esquerda * direita;
        } else if (no.tipo == '/') {
            if (direita == 0) {
                erro = true;
                return 0;
            }
            resultado = esquerda / direita;
        }
        return resultado;
    }

    static String mostrar(No no) {
        if (no.tipo == 'n') {
            return String.valueOf(no.valor);
        }
        return "(" + mostrar(no.esquerda) + " " + no.tipo + " " + mostrar(no.direita) + ")";
    }

    static void testar(No expressao) {
        erro = false;
        int resultado = avaliar(expressao);
        if (erro) {
            System.out.println(mostrar(expressao) + " = erro: divisao por zero");
        } else {
            System.out.println(mostrar(expressao) + " = " + resultado);
        }
    }

    public static void main(String[] args) {
        System.out.println("Projeto 1 - Calculadora de Expressoes (versao Java)");
        System.out.println("Integrantes: Emanuel Filipy, Matheus Caetano, Wesley Julio");
        System.out.println();

        testar(soma(numero(3), numero(4)));
        testar(mult(soma(numero(3), numero(4)), numero(2)));
        testar(div(numero(8), numero(0)));
        testar(soma(numero(1), div(numero(5), numero(0))));
        testar(div(numero(-7), numero(2)));
        testar(div(numero(10), soma(numero(2), numero(3))));
    }
}
