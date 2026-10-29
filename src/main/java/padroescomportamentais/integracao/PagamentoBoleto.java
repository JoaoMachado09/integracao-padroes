package padroescomportamentais.integracao;

public class PagamentoBoleto implements FormaPagamento {

    public float calcular(float valor) {
        return valor - (valor * 0.05f);
    }
}
