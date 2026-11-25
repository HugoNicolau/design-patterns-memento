package padroescomportamentais.memento;

import java.util.ArrayList;
import java.util.List;

// Originator: cria mementos de si mesmo e se restaura a partir deles.
public class Pedido {

    private List<String> itens = new ArrayList<String>();
    private String observacao = "";

    public void adicionarItem(String item) {
        this.itens.add(item);
    }

    public List<String> getItens() {
        return itens;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public PedidoMemento salvar() {
        return new PedidoMemento(this.itens, this.observacao);
    }

    public void restaurar(PedidoMemento memento) {
        this.itens = new ArrayList<String>(memento.getItens());
        this.observacao = memento.getObservacao();
    }
}
