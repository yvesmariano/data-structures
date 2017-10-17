package tad.fila;

public class FilaLista implements IFila {
    private class No {
        public No anterior = null;
        public Object valor;
    }
    
    private No inicio = null, fim = null;
    private int tamanho = 0;
    
    @Override
    public void enfileirar(Object o) {
        No novo = new No();
        novo.valor = o;
        
        if(estaVazia())
            inicio = novo;
        else
            fim.anterior = novo;
        
        fim = novo;
        tamanho++;
    }

    @Override
    public Object inicio() throws EFilaVazia {
        if(estaVazia())
            throw new EFilaVazia();
        
        return inicio;
    }

    @Override
    public Object desenfileirar() throws EFilaVazia {
        if(estaVazia())
            throw new EFilaVazia();
        
        No desenfileirado = inicio;
        inicio = desenfileirado.anterior;
        desenfileirado.anterior = null;
        tamanho--;
        return desenfileirado.valor;
    }

    @Override
    public int tamanho() {
        return tamanho;
    }

    @Override
    public boolean estaVazia() {
        return tamanho == 0;
    }
    
}
