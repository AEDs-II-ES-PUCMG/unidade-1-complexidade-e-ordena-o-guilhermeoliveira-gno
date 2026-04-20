import java.text.NumberFormat;

/**
 * Representa um item de um pedido, congelando o preço do produto no momento da compra.
 * Cada item contém uma referência a um Produto e a quantidade comprada, além do preço congelado.
 */
public class ItemDePedido {
    
    private Produto produto;
    private int quantidade;
    private double precoCongelado;
    
    /**
     * Construtor do item de pedido.
     * Congela o preço de venda atual do produto no momento da criação.
     * @param produto O produto a ser incluído no item
     * @param quantidade A quantidade de unidades compradas
     */
    public ItemDePedido(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoCongelado = produto.valorDeVenda();
    }
    
    /**
     * Retorna o produto associado a este item.
     * @return O produto deste item
     */
    public Produto getProduto() {
        return produto;
    }
    
    /**
     * Retorna a quantidade de unidades deste item no pedido.
     * @return Quantidade de unidades
     */
    public int getQuantidade() {
        return quantidade;
    }
    
    /**
     * Retorna o preço congelado do produto no momento da compra.
     * @return Preço congelado do item
     */
    public double getPrecoCongelado() {
        return precoCongelado;
    }
    
    /**
     * Retorna o preço de venda atual do produto (valor de catálogo).
     * @return Preço atual de venda do produto
     */
    public double getPrecoAtual() {
        return produto.valorDeVenda();
    }
    
    /**
     * Calcula o valor total do item com preço congelado.
     * @return Quantidade * Preço congelado
     */
    public double getValorTotal() {
        return quantidade * precoCongelado;
    }
    
    /**
     * Calcula o valor total ao preço atual de catálogo.
     * @return Quantidade * Preço atual
     */
    public double getValorTotalAtual() {
        return quantidade * getPrecoAtual();
    }
    
    /**
     * Retorna a representação em string do item do pedido.
     * @return String com descrição, quantidade e preço congelado
     */
    @Override
    public String toString() {
        NumberFormat moeda = NumberFormat.getCurrencyInstance();
        return String.format("%s - Quantidade: %d - Preço congelado: %s",
                produto.toString(), quantidade, moeda.format(precoCongelado));
    }
}
