import java.util.Comparator;

/**
 * Interface para algoritmos de ordenação que suportam comparadores customizados.
 * Estende a funcionalidade básica de IOrdenador com suporte a Comparator.
 */
public interface IOrdenator<T extends Comparable<T>> extends IOrdenador<T> {
    public void setComparador(Comparator<T> comparador);
}
