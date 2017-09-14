package tad.pilha;

public interface IPilha {
    public void empilhar(Object o);
    public Object desempilhar() throws EPilhaVazia;
    public Object topo() throws EPilhaVazia ;
    public boolean estaVazia();
    public int tamanho();
}
