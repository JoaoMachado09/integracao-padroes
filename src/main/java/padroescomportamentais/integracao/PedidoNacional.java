package padroescomportamentais.integracao;

public class PedidoNacional extends Pedido {

    public PedidoNacional(String descricao, float valorItens, FormaPagamento formaPagamento) {
        super(descricao, valorItens, formaPagamento);
    }

    public float calcularFrete() {
        return 50.0f;
    }

    @Override
    public String getTipo() {
        return "PedidoNacional";
    }
}
