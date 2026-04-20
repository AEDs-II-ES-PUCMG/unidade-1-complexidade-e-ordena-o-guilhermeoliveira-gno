import java.util.Comparator;

/**
 * Critério A - Valor Final do Pedido (crescente).
 * Desempate 1: Volume Total de Itens (quantProdutos).
 * Desempate 2: Código Identificador do primeiro item do pedido.
 */
public class ComparadorCriterioA implements Comparator<Pedido> {

    @Override
    public int compare(Pedido o1, Pedido o2) {
        // Compara pelo valor final do pedido
        double valor1 = o1.valorFinal();
        double valor2 = o2.valorFinal();
        
        if (Math.abs(valor1 - valor2) > 0.001) { // Comparação com tolerância para double
            return valor1 < valor2 ? -1 : 1;
        }
        
        // Desempate 1: Volume Total de Itens
        int volume1 = o1.getTotalItens();
        int volume2 = o2.getTotalItens();
        
        if (volume1 != volume2) {
            return volume1 - volume2;
        }
        
        // Desempate 2: Código Identificador do primeiro produto
        int id1 = o1.getIdPrimeiroProduto();
        int id2 = o2.getIdPrimeiroProduto();
        
        return id1 - id2;
    }
}
