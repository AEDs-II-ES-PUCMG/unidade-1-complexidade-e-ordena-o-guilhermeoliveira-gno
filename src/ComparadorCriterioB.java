import java.util.Comparator;

/**
 * Critério B - Volume Total de Itens (crescente).
 * Desempate 1: Data do Pedido.
 * Desempate 2: Código Identificador do pedido.
 */
public class ComparadorCriterioB implements Comparator<Pedido> {

    @Override
    public int compare(Pedido o1, Pedido o2) {
        // Compara pelo volume total de itens
        int volume1 = o1.getTotalItens();
        int volume2 = o2.getTotalItens();
        
        if (volume1 != volume2) {
            return volume1 - volume2;
        }
        
        // Desempate 1: Data do Pedido
        int dataCompare = o1.getDataPedido().compareTo(o2.getDataPedido());
        if (dataCompare != 0) {
            return dataCompare;
        }
        
        // Desempate 2: Código Identificador do pedido
        return o1.getIdPedido() - o2.getIdPedido();
    }
}
