package padroescomportamentais.integracao;

public abstract class Pedido {

    private String descricao;
    private float valorItens;
    private FormaPagamento formaPagamento;

    public Pedido(String descricao, float valorItens, FormaPagamento formaPagamento) {
        this.descricao = descricao;
        this.valorItens = valorItens;
        this.formaPagamento = formaPagamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public float getValorItens() {
        return valorItens;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    // Passo variável do Template Method
    public abstract float calcularFrete();

    // Usa a estratégia (Strategy) escolhida para o pagamento
    public float calcularValorFinal() {
        if (valorItens <= 0) {
            throw new IllegalArgumentException("Valor inválido");
        }
        return formaPagamento.calcular(valorItens + calcularFrete());
    }

    // Gancho (hook) que as subclasses podem sobrescrever
    public String getTipo() {
        return "Pedido";
    }

    // Template Method: define o algoritmo de processamento do pedido
    public String processar(Aprovador aprovador) {
        return getTipo() + "{" +
                "descricao='" + this.descricao + '\'' +
                ", valorFinal=" + this.calcularValorFinal() +
                ", aprovacao=" + aprovador.aprovarPedido(this) +
                '}';
    }
}
