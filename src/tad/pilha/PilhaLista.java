package tad.pilha;

public class PilhaLista implements IPilha {
    private class No {
        public No anterior = null;
        public Object valor;
    }
    
    private No topo = null;
    private int tamanho = 0;
    
    @Override
    public void empilhar(Object o) {
        No novo = new No();
        novo.valor = o;
        tamanho++;
        
        novo.anterior = topo;
        topo = novo;
    }
    
    @Override
    public Object desempilhar() throws EPilhaVazia {
        if(estaVazia())
            throw new EPilhaVazia();
        
        No desempilhado = topo;
        topo = desempilhado.anterior;
        desempilhado.anterior = null;
        tamanho--;
        return desempilhado.valor;
    }
    
    @Override
    public Object topo() throws EPilhaVazia {
        if(estaVazia())
            throw new EPilhaVazia();
        return topo.valor;
    }
    
    @Override
    public boolean estaVazia() {
        return topo == null;
    }
    
    @Override
    public int tamanho() {
        return tamanho;
    }
}
