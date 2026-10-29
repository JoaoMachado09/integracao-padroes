package padroescomportamentais.integracao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PedidoTest {

    AprovadorDiretor diretor;
    AprovadorGerente gerente;
    AprovadorSupervisor supervisor;

    @BeforeEach
    void setUp() {
        diretor = new AprovadorDiretor(null);
        gerente = new AprovadorGerente(diretor);
        supervisor = new AprovadorSupervisor(gerente);
    }

    // ---- Template Method: frete definido por cada subclasse

    @Test
    void deveCalcularFretePedidoNacional() {
        Pedido pedido = new PedidoNacional("Monitor", 800.0f, new PagamentoBoleto());
        assertEquals(50.0f, pedido.calcularFrete());
    }

    @Test
    void deveCalcularFretePedidoInternacional() {
        Pedido pedido = new PedidoInternacional("Servidor", 10000.0f, new PagamentoBoleto());
        assertEquals(3000.0f, pedido.calcularFrete());
    }

    @Test
    void deveCalcularFretePedidoRetirada() {
        Pedido pedido = new PedidoRetirada("Cadeira", 1000.0f, new PagamentoBoleto());
        assertEquals(0.0f, pedido.calcularFrete());
    }

    // ---- Strategy: valor final muda conforme a forma de pagamento

    @Test
    void deveAplicarDescontoPix() {
        Pedido pedido = new PedidoNacional("Teclado", 950.0f, new PagamentoPix());
        assertEquals(900.0f, pedido.calcularValorFinal());
    }

    @Test
    void deveAplicarDescontoBoleto() {
        Pedido pedido = new PedidoInternacional("Servidor", 10000.0f, new PagamentoBoleto());
        assertEquals(12350.0f, pedido.calcularValorFinal());
    }

    @Test
    void deveAplicarAcrescimoCartaoCredito() {
        Pedido pedido = new PedidoNacional("Notebook", 2000.0f, new PagamentoCartaoCredito());
        assertEquals(2460.0f, pedido.calcularValorFinal());
    }

    @Test
    void naoDeveCalcularPedidoComValorInvalido() {
        try {
            Pedido pedido = new PedidoNacional("Vazio", 0.0f, new PagamentoPix());
            pedido.calcularValorFinal();
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Valor inválido", e.getMessage());
        }
    }

    // ---- Chain of Responsibility: aprovação conforme o valor final

    @Test
    void deveRetornarSupervisorParaAprovacao() {
        Pedido pedido = new PedidoNacional("Teclado", 950.0f, new PagamentoPix());
        assertEquals("Supervisor", supervisor.aprovarPedido(pedido));
    }

    @Test
    void deveRetornarGerenteParaAprovacao() {
        Pedido pedido = new PedidoNacional("Notebook", 2000.0f, new PagamentoCartaoCredito());
        assertEquals("Gerente", supervisor.aprovarPedido(pedido));
    }

    @Test
    void deveRetornarDiretorParaAprovacao() {
        Pedido pedido = new PedidoInternacional("Servidor", 10000.0f, new PagamentoBoleto());
        assertEquals("Diretor", supervisor.aprovarPedido(pedido));
    }

    @Test
    void deveRetornarSemAprovacao() {
        Pedido pedido = new PedidoInternacional("Equipamento de imagem", 20000.0f, new PagamentoPix());
        assertEquals("Sem aprovação", supervisor.aprovarPedido(pedido));
    }

    // ---- Integração dos três padrões

    @Test
    void deveMudarAprovadorAoTrocarFormaPagamento() {
        Pedido pedido = new PedidoRetirada("Cadeira", 1000.0f, new PagamentoCartaoCredito());
        assertEquals("Gerente", supervisor.aprovarPedido(pedido));
        pedido.setFormaPagamento(new PagamentoPix());
        assertEquals("Supervisor", supervisor.aprovarPedido(pedido));
    }

    @Test
    void deveProcessarPedidoNacional() {
        Pedido pedido = new PedidoNacional("Notebook", 2000.0f, new PagamentoCartaoCredito());
        assertEquals("PedidoNacional{descricao='Notebook', valorFinal=2460.0, aprovacao=Gerente}", pedido.processar(supervisor));
    }

    @Test
    void deveProcessarPedidoInternacional() {
        Pedido pedido = new PedidoInternacional("Servidor", 10000.0f, new PagamentoBoleto());
        assertEquals("PedidoInternacional{descricao='Servidor', valorFinal=12350.0, aprovacao=Diretor}", pedido.processar(supervisor));
    }

    @Test
    void deveProcessarPedidoRetirada() {
        Pedido pedido = new PedidoRetirada("Cadeira", 1000.0f, new PagamentoPix());
        assertEquals("Pedido{descricao='Cadeira', valorFinal=900.0, aprovacao=Supervisor}", pedido.processar(supervisor));
    }

}
