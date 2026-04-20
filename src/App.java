import java.util.Random;
import java.util.Scanner;
import java.util.Comparator;
import java.util.Arrays;

public class App {
    static Random aleatorio = new Random();
    static Scanner scanner = new Scanner(System.in);

    static Integer[] gerarVetorObjetos(int tamanho) {
        Integer[] vetor = new Integer[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = aleatorio.nextInt(1, 10 * tamanho);
        }
        return vetor;
    }

    public static void main(String[] args) {
        int tam = 20;
        Integer[] vetorOriginal = gerarVetorObjetos(tam);

        // 1. Testando BubbleSort
        IOrdenador<Integer> bolha = new BubbleSort<>();
        bolha.ordenar(vetorOriginal);
        imprimirResultado("Bolha (BubbleSort)", bolha);

        // 2. Testando InsertionSort
        IOrdenador<Integer> insercao = new InsertionSort<>();
        insercao.ordenar(vetorOriginal);
        imprimirResultado("Inserção (InsertionSort)", insercao);

        // 3. Testando SelectionSort
        IOrdenador<Integer> selecao = new SelectionSort<>();
        selecao.ordenar(vetorOriginal);
        imprimirResultado("Seleção (SelectionSort)", selecao);
    }

    static void imprimirResultado(String nome, IOrdenador<?> ordenador) {
        System.out.println("\n--- Método: " + nome + " ---");
        System.out.println("Comparações: " + ordenador.getComparacoes());
        System.out.println("Movimentações: " + ordenador.getMovimentacoes());
        System.out.printf("Tempo de ordenação: %.4f ms\n", ordenador.getTempoOrdenacao());
    }

    /**
     * Ordena pedidos usando diferentes algoritmos e critérios de ordenação.
     * Permite interação com o usuário para escolher algoritmo e critério.
     * @param pedidos Array de pedidos a serem ordenados
     */
    static void ordenarPedidos(Pedido[] pedidos) {
        if (pedidos == null || pedidos.length == 0) {
            System.out.println("Nenhum pedido para ordenar.");
            return;
        }
        
        // Menu de algoritmo
        System.out.println("\n=== Escolha o algoritmo de ordenação ===");
        System.out.println("1. BubbleSort (Bolha)");
        System.out.println("2. SelectionSort (Seleção)");
        System.out.println("3. InsertionSort (Inserção)");
        System.out.println("4. Mergesort");
        System.out.println("5. Heapsort");
        System.out.print("Escolha (1-5): ");
        int algoritmo = scanner.nextInt();
        
        // Menu de critério
        System.out.println("\n=== Escolha o critério de ordenação ===");
        System.out.println("A. Valor Final do Pedido (A)");
        System.out.println("B. Volume Total de Itens (B)");
        System.out.println("C. Índice de Economia (C)");
        System.out.print("Escolha (A/B/C): ");
        String criterio = scanner.next().toUpperCase();
        
        // Seleciona o comparador
        Comparator<Pedido> comparador;
        switch (criterio) {
            case "A":
                comparador = new ComparadorCriterioA();
                break;
            case "B":
                comparador = new ComparadorCriterioB();
                break;
            case "C":
                comparador = new ComparadorCriterioC();
                break;
            default:
                System.out.println("Critério inválido! Usando Critério A.");
                comparador = new ComparadorCriterioA();
        }
        
        // Cria uma cópia do array para não modificar o original
        Pedido[] pedidosOrdenados = Arrays.copyOf(pedidos, pedidos.length);
        
        // Executa o algoritmo e mede o tempo
        long tempoExecucao = 0;
        long inicio = System.nanoTime();
        
        try {
            switch (algoritmo) {
                case 1: {
                    BubbleSort<Pedido> sorter = new BubbleSort<>();
                    sorter.ordenar(pedidosOrdenados);
                    break;
                }
                case 2: {
                    SelectionSort<Pedido> sorter = new SelectionSort<>(comparador);
                    sorter.ordenar(pedidosOrdenados);
                    break;
                }
                case 3: {
                    InsertionSort<Pedido> sorter = new InsertionSort<>(comparador);
                    sorter.ordenar(pedidosOrdenados);
                    break;
                }
                case 4: {
                    Mergesort<Pedido> sorter = new Mergesort<>(comparador);
                    sorter.ordenar(pedidosOrdenados);
                    break;
                }
                case 5: {
                    Heapsort<Pedido> sorter = new Heapsort<>(comparador);
                    sorter.ordenar(pedidosOrdenados);
                    break;
                }
                default:
                    System.out.println("Algoritmo inválido!");
                    return;
            }
        } catch (Exception e) {
            System.out.println("Erro ao ordenar: " + e.getMessage());
            e.printStackTrace();
            return;
        }
        
        long fim = System.nanoTime();
        tempoExecucao = (fim - inicio) / 1_000_000; // Converte para milissegundos
        
        // Exibe os resultados
        System.out.println("\n=== Resultado da Ordenação ===");
        System.out.println("Algoritmo: " + getNomeAlgoritmo(algoritmo));
        System.out.println("Critério: " + criterio);
        System.out.println("Tempo de ordenação: " + tempoExecucao + " ms");
        System.out.println("\nPedidos ordenados:");
        for (Pedido p : pedidosOrdenados) {
            System.out.println("\nPedido ID: " + p.getIdPedido() + 
                             " | Valor: R$ " + String.format("%.2f", p.valorFinal()));
        }
    }

    /**
     * Localiza e exibe pedidos premium (valor final acima de um limite especificado).
     * Usa uma estratégia otimizada com busca binária após ordenação.
     * @param pedidos Array de pedidos
     */
    static void localizarPedidosPremium(Pedido[] pedidos) {
        if (pedidos == null || pedidos.length == 0) {
            System.out.println("Nenhum pedido para buscar.");
            return;
        }
        
        System.out.print("\nInforme o valor mínimo para pedidos premium (R$): ");
        double valorMinimo = scanner.nextDouble();
        
        // Estratégia otimizada: ordena por valor final uma única vez
        Pedido[] pedidosOrdenados = Arrays.copyOf(pedidos, pedidos.length);
        Comparator<Pedido> comparadorValor = new Comparator<Pedido>() {
            @Override
            public int compare(Pedido o1, Pedido o2) {
                double diff = o1.valorFinal() - o2.valorFinal();
                if (Math.abs(diff) < 0.001) return 0;
                return diff < 0 ? -1 : 1;
            }
        };
        
        // Ordena por valor
        SelectionSort<Pedido> sorter = new SelectionSort<>(comparadorValor);
        sorter.ordenar(pedidosOrdenados);
        
        // Busca binária para encontrar o primeiro pedido com valor >= valorMinimo
        int inicio = buscaBinaria(pedidosOrdenados, valorMinimo);
        
        // Exibe os resultados
        System.out.println("\n=== Pedidos Premium (Valor >= R$ " + String.format("%.2f", valorMinimo) + ") ===");
        if (inicio == -1) {
            System.out.println("Nenhum pedido encontrado com valor >= R$ " + String.format("%.2f", valorMinimo));
            return;
        }
        
        // Exibe pedidos a partir do índice encontrado
        for (int i = inicio; i < pedidosOrdenados.length; i++) {
            System.out.println(pedidosOrdenados[i].toString());
            System.out.println("---");
        }
    }

    /**
     * Implementa busca binária para encontrar o primeiro pedido com valor >= valorMinimo.
     * Evita varredura completa do array, otimizando a busca.
     * @param pedidos Array de pedidos ordenado por valor
     * @param valorMinimo Valor mínimo desejado
     * @return Índice do primeiro pedido com valor >= valorMinimo, ou -1 se não encontrado
     */
    static int buscaBinaria(Pedido[] pedidos, double valorMinimo) {
        int esquerda = 0;
        int direita = pedidos.length - 1;
        int resultado = -1;
        
        while (esquerda <= direita) {
            int meio = (esquerda + direita) / 2;
            double valorMeio = pedidos[meio].valorFinal();
            
            if (valorMeio >= valorMinimo) {
                resultado = meio;
                direita = meio - 1; // Continua procurando à esquerda para encontrar o primeiro
            } else {
                esquerda = meio + 1; // Procura à direita
            }
        }
        
        return resultado;
    }

    static String getNomeAlgoritmo(int algoritmo) {
        switch (algoritmo) {
            case 1: return "BubbleSort (Bolha)";
            case 2: return "SelectionSort (Seleção)";
            case 3: return "InsertionSort (Inserção)";
            case 4: return "Mergesort";
            case 5: return "Heapsort";
            default: return "Desconhecido";
        }
    }
}