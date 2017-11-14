package tad.hashtable;

public class Item {

    private Object chave, elemento;

    public Item(Object chave, Object elemento) {
        this.chave = chave;
        this.elemento = elemento;
    }
    public Object getChave() {
        return chave;
    }

    public void setChave(Object chave) {
        this.chave = chave;
    }

    public Object getElemento() {
        return elemento;
    }

    public void setElemento(Object elemento) {
        this.elemento = elemento;
    }

}
