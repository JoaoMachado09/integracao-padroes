package padroescomportamentais.integracao;

public class AprovadorGerente extends Aprovador {

    public AprovadorGerente(Aprovador superior) {
        setAprovadorSuperior(superior);
    }

    public String getDescricaoCargo() {
        return "Gerente";
    }

    public float getLimiteAprovacao() {
        return 5000.0f;
    }

}
