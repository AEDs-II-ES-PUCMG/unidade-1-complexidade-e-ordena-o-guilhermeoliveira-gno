import java.util.Random;

public class App {
    static Random aleatorio = new Random();

    static Integer[] gerarVetorObjetos(int tamanho) {
        Integer[] vetor = new Integer[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = aleatorio.nextInt(1, 10 * tamanho);
        }
        return vetor;
    }

    /*
    import java.util.Arrays;

public class HeapSort<T extends Comparable<T>> implements IOrdenador<T> {
    private int comparacoes;
    private int movimentacoes;
    private double tempoOrdenacao;
    private double inicio;
    private double nanoToMilli = 1.0/1_000_000;

    @Override
    public int getComparacoes() { return comparacoes; }
    @Override
    public int getMovimentacoes() { return movimentacoes; }
    @Override
    public double getTempoOrdenacao() { return tempoOrdenacao; }

    private void iniciar() {
        this.comparacoes = 0;
        this.movimentacoes = 0;
        this.inicio = System.nanoTime();
    }

    private void terminar() {
        this.tempoOrdenacao = (System.nanoTime() - this.inicio) * nanoToMilli;
    }

    @Override
    public T[] ordenar(T[] dados) {
        T[] vetor = Arrays.copyOf(dados, dados.length);
        int n = vetor.length;
        iniciar();

        // 1. Constroi o Heap (reorganiza o vetor)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(vetor, n, i);
        }

        // 2. Extrai um a um os elementos do heap
        for (int i = n - 1; i > 0; i--) {
            // Move a raiz atual para o fim
            T temp = vetor[0];
            vetor[0] = vetor[i];
            vetor[i] = temp;
            movimentacoes += 3;

            // Chama o heapify na árvore reduzida
            heapify(vetor, i, 0);
        }

        terminar();
        return vetor;
    }

    private void heapify(T[] vetor, int n, int i) {
        int maior = i; 
        int esquerda = 2 * i + 1; 
        int direita = 2 * i + 2; 

        // Se o filho da esquerda for maior que a raiz
        comparacoes++;
        if (esquerda < n && vetor[esquerda].compareTo(vetor[maior]) > 0) {
            maior = esquerda;
        }

        // Se o filho da direita for maior que o maior até agora
        comparacoes++;
        if (direita < n && vetor[direita].compareTo(vetor[maior]) > 0) {
            maior = direita;
        }

        // Se o maior não for a raiz
        if (maior != i) {
            T swap = vetor[i];
            vetor[i] = vetor[maior];
            vetor[maior] = swap;
            movimentacoes += 3;

            // Recursivamente faz o heapify na subárvore afetada
            heapify(vetor, n, maior);
        }
    }
} */

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

    static void imprimirResultado(String nome, IOrdenador ordenador) {
        System.out.println("\n--- Método: " + nome + " ---");
        System.out.println("Comparações: " + ordenador.getComparacoes());
        System.out.println("Movimentações: " + ordenador.getMovimentacoes());
        System.out.printf("Tempo de ordenação: %.4f ms\n", ordenador.getTempoOrdenacao());
    }
}