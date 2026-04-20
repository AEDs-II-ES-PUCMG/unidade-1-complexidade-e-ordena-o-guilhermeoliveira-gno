import java.util.Arrays;

public class SelectionSort<T extends Comparable<T>> implements IOrdenador<T> {
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
        T[] dadosOrdenados = Arrays.copyOf(dados, dados.length);
        int n = dadosOrdenados.length;
        iniciar();
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                comparacoes++;
                if (dadosOrdenados[j].compareTo(dadosOrdenados[minIdx]) < 0) {
                    minIdx = j;
                }
            }
            T temp = dadosOrdenados[minIdx];
            dadosOrdenados[minIdx] = dadosOrdenados[i];
            dadosOrdenados[i] = temp;
            movimentacoes += 3;
        }
        terminar();
        return dadosOrdenados;
    }
}