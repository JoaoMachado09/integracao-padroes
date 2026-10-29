package padroescomportamentais.integracao;

public abstract class Aprovador {

    private Aprovador aprovadorSuperior;

    public Aprovador getAprovadorSuperior() {
        return aprovadorSuperior;
    }

    public void setAprovadorSuperior(Aprovador aprovadorSuperior) {
        this.aprovadorSuperior = aprovadorSuperior;
    }

    public abstract String getDescricaoCargo();

    public abstract float getLimiteAprovacao();

    public String aprovarPedido(Pedido pedido) {
        if (pedido.calcularValorFinal() <= getLimiteAprovacao()) {
            return getDescricaoCargo();
        }
        else {
            if (aprovadorSuperior != null) {
                return aprovadorSuperior.aprovarPedido(pedido);
            }
            else {
                return "Sem aprovação";
            }
        }
    }
}
