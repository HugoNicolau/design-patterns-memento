package padroescomportamentais.memento;

import java.util.ArrayList;
import java.util.List;

// Caretaker: guarda os mementos, sem nunca olhar o conteúdo deles.
public class HistoricoPedido {

    private List<PedidoMemento> versoes = new ArrayList<PedidoMemento>();

    public void adicionar(PedidoMemento memento) {
        this.versoes.add(memento);
    }

    public PedidoMemento getVersao(int indice) {
        if (indice < 0 || indice > this.versoes.size() - 1) {
            throw new IllegalArgumentException("Índice inválido");
        }
        return this.versoes.get(indice);
    }

    public int getTotalVersoes() {
        return this.versoes.size();
    }
}
