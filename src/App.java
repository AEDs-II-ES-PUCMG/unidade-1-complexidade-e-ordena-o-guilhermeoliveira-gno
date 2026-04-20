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



    static void ordenarPedidos(){
        if (metodo=="heap"){

        }
        if (metodo=="selection"){
            
        }
        if (metodo=="bubble"){
            
        }
        if (metodo=="insertion"){
            
        }

    }

    static void localizarPedidosPremium(){
// deveria ler o vetor ate o momento em que o valor do vetor ordenado superasse o valor estipulado
//de modo que nao fizesse mais comparacoes, apenas listaria os seguintes dados
for (i=0;i<(array.length-1);i++){
    if (valor>=array[i]){
        limite=i;
        for (i=limite;i<(array.length-limite);i++)
            print nome;
    }
} 
    }


}