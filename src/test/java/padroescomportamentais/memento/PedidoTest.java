package padroescomportamentais.memento;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PedidoTest {

    Pedido pedido;
    HistoricoPedido historico;

    @BeforeEach
    void setUp() {
        pedido = new Pedido();
        historico = new HistoricoPedido();
    }

    @Test
    void deveArmazenarVersoes() {
        pedido.adicionarItem("X-Burguer");
        historico.adicionar(pedido.salvar());
        pedido.adicionarItem("Batata");
        historico.adicionar(pedido.salvar());

        assertEquals(2, historico.getTotalVersoes());
    }

    @Test
    void deveRestaurarVersaoInicial() {
        pedido.adicionarItem("X-Burguer");
        historico.adicionar(pedido.salvar());
        pedido.adicionarItem("Batata");
        pedido.setObservacao("sem cebola");
        historico.adicionar(pedido.salvar());

        pedido.restaurar(historico.getVersao(0));

        assertEquals(Arrays.asList("X-Burguer"), pedido.getItens());
        assertEquals("", pedido.getObservacao());
    }

    @Test
    void deveRestaurarVersaoIntermediaria() {
        pedido.adicionarItem("X-Burguer");
        historico.adicionar(pedido.salvar());
        pedido.adicionarItem("Batata");
        pedido.setObservacao("sem cebola");
        historico.adicionar(pedido.salvar());
        pedido.adicionarItem("Milkshake");
        pedido.setObservacao("sem cebola e sem gelo");
        historico.adicionar(pedido.salvar());

        pedido.restaurar(historico.getVersao(1));

        assertEquals(Arrays.asList("X-Burguer", "Batata"), pedido.getItens());
        assertEquals("sem cebola", pedido.getObservacao());
    }

    @Test
    void naoDeveAlterarVersaoSalvaAoAlterarPedido() {
        pedido.adicionarItem("X-Burguer");
        historico.adicionar(pedido.salvar());

        pedido.adicionarItem("Milkshake");
        pedido.restaurar(historico.getVersao(0));
        pedido.adicionarItem("Suco");
        pedido.restaurar(historico.getVersao(0));

        assertEquals(Arrays.asList("X-Burguer"), pedido.getItens());
    }

    @Test
    void deveRetornarExcecaoIndiceInvalido() {
        try {
            historico.getVersao(0);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Índice inválido", e.getMessage());
        }
    }

}
