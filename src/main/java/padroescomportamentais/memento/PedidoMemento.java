package padroescomportamentais.memento;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Memento: foto imutável do estado do Pedido.
// Os getters não são públicos: só o Pedido (mesmo pacote) lê o conteúdo.
public class PedidoMemento {

    private final List<String> itens;
    private final String observacao;

    PedidoMemento(List<String> itens, String observacao) {
        this.itens = Collections.unmodifiableList(new ArrayList<String>(itens));
        this.observacao = observacao;
    }

    List<String> getItens() {
        return itens;
    }

    String getObservacao() {
        return observacao;
    }
}
