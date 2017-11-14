package tad.hashtable;

import java.util.Iterator;
import java.util.ArrayList;

public class Hashtable {

    private int tamanho;
    private Item table[];

    public Hashtable() {
        tamanho = 0;
        table = new Item[13];
    }

    private int hash(Object k) {
        int y = k.hashCode();
        return y % table.length;
    }

    public Object encontrar(Object k) throws NoSuchKeyException {
        return table[indice(k)].getElemento();
    }
    
    private int indice(Object k) throws NoSuchKeyException {
        Item c;
        int i = hash(k);
        int p = 0;

        while (p != table.length) {
            c = table[i];
            if (c == null) {
                throw new NoSuchKeyException();
            } else if (c.getChave().equals(k)) {
                return i;
            } else {
                i = (i + 1) % table.length;
                p++;
            }
        }

        throw new NoSuchKeyException();
    }

    public void inserir(Object k, Object o) throws HashTableCheiaException {
        if (tamanho == table.length) {
            throw new HashTableCheiaException();
        }
        
        int i = hash(k);
        
        while (table[i] != null && table[i].getClass() != ItemAvailable.class) {
            i = (i + 1) % table.length;            
        }
        
        table[i] = new Item(k, o);
        tamanho++;
    }

    public Object remover(Object k) throws NoSuchKeyException {
        int i = indice(k);
        Object o = table[i].getElemento();
        table[i] = new ItemAvailable();
        tamanho--;
        return o;
    }

    public int tamanho() {
        return tamanho;
    }

    public boolean estaVazio() {
        return tamanho == 0;
    }

    public Iterator chaves() {
        ArrayList v = new ArrayList();
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                v.add(table[i].getChave());
            }
        }
        return v.iterator();
    }

    public Iterator elementos() {
        ArrayList v = new ArrayList();
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                v.add(table[i].getElemento());
            }
        }
        return v.iterator();
    }
    
    @Override
    public String toString() {
        String s = "";
        
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null || table[i].getClass() == ItemAvailable.class) {
                s += "-- | ";
            } else {
                s += table[i].getChave() + " | ";    
            }            
        }
        
        return s.substring(0, s.length() - 3);
    }
}
