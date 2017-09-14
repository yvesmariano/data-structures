package tad.fila;

import tad.fila.EFilaVazia;

public class FilaArray implements IFila {
    
    private Object fila[];
    private int inicio = 0, fim = 0, incremento;
    
    public FilaArray(int tamanho, int incremento) {
        this.fila = new Object[tamanho];
        this.incremento = incremento;
    }
    
    public FilaArray(int tamanho) {
        this.fila = new Object[tamanho];
        this.incremento = 0;
    }
    
    public FilaArray() {
        this.fila = new Object[10];
        this.incremento = 0;
    }
    
    @Override
    public void enfileirar(Object o) {
        if (tamanho() == fila.length - 1) {

            Object aux[];
            if (incremento == 0) {
                aux = new Object[fila.length * 2];
            } else {
                aux = new Object[fila.length + incremento];
            }

            for (int i = 0; i < fila.length - 1; i++, inicio++) {
                aux[i] = fila[inicio % fila.length];
            }
 
            inicio = 0;
            fim = fila.length - 1;
            fila = aux;
        }

        fila[fim] = o;
        fim = (fim + 1) % fila.length;
    }
    
    @Override
    public Object inicio() throws EFilaVazia {
        if (estaVazia())
            throw new EFilaVazia();
        
        return fila[inicio];
    }

    @Override
    public Object desenfileirar() throws EFilaVazia {
        if (estaVazia()) {
            throw new EFilaVazia();
        }

        Object aux = fila[inicio];
        inicio = (inicio + 1) % fila.length;
        return aux;
    }

    @Override
    public int tamanho() {
        return (fila.length - inicio + fim) % fila.length;
    }

    @Override
    public boolean estaVazia() {
        return inicio == fim;
    }
}
