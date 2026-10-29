package padroescomportamentais.integracao;

public class PedidoRetirada extends Pedido {

    public PedidoRetirada(String descricao, float valorItens, FormaPagamento formaPagamento) {
        super(descricao, valorItens, formaPagamento);
    }

    public float calcularFrete() {
        return 0.0f;
    }

}
