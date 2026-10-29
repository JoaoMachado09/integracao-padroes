package padroescomportamentais.integracao;

public class AprovadorSupervisor extends Aprovador {

    public AprovadorSupervisor(Aprovador superior) {
        setAprovadorSuperior(superior);
    }

    public String getDescricaoCargo() {
        return "Supervisor";
    }

    public float getLimiteAprovacao() {
        return 1000.0f;
    }

}
