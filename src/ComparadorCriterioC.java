import java.util.Comparator;

/**
 * Critério C - Índice de Economia (decrescente).
 * O índice de economia é a diferença entre o valor de catálogo atual e o valor efetivamente pago.
 * Desempate 1: Valor Final do Pedido (crescente).
 * Desempate 2: Código Identificador do pedido (crescente).
 */
public class ComparadorCriterioC implements Comparator<Pedido> {

    @Override
    public int compare(Pedido o1, Pedido o2) {
        // Compara pelo índice de economia em ordem DECRESCENTE
        double economia1 = o1.indiceEconomia();
        double economia2 = o2.indiceEconomia();
        
        if (Math.abs(economia1 - economia2) > 0.001) { // Comparação com tolerância para double
            return economia1 > economia2 ? -1 : 1; // Inverso para ordem decrescente
        }
        
        // Desempate 1: Valor Final do Pedido (crescente)
        double valor1 = o1.valorFinal();
        double valor2 = o2.valorFinal();
        
        if (Math.abs(valor1 - valor2) > 0.001) {
            return valor1 < valor2 ? -1 : 1;
        }
        
        // Desempate 2: Código Identificador do pedido (crescente)
        return o1.getIdPedido() - o2.getIdPedido();
    }
}
