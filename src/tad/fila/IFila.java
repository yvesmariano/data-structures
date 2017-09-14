package tad.fila;

public interface IFila {
    public void enfileirar(Object o);
    public Object inicio() throws EFilaVazia;
    public Object desenfileirar() throws EFilaVazia;
    public int tamanho();
    public boolean estaVazia();
}
