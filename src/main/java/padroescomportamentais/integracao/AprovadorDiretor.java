package padroescomportamentais.integracao;

public class AprovadorDiretor extends Aprovador {

    public AprovadorDiretor(Aprovador superior) {
        setAprovadorSuperior(superior);
    }

    public String getDescricaoCargo() {
        return "Diretor";
    }

    public float getLimiteAprovacao() {
        return 20000.0f;
    }

}
