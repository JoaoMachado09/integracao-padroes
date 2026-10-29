package padroescomportamentais.integracao;

public class PedidoInternacional extends Pedido {

    public PedidoInternacional(String descricao, float valorItens, FormaPagamento formaPagamento) {
        super(descricao, valorItens, formaPagamento);
    }

    public float calcularFrete() {
        return this.getValorItens() * 0.30f;
    }

    @Override
    public String getTipo() {
        return "PedidoInternacional";
    }
}
