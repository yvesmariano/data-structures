package tad.arvorebinariapesquisa;

public class No {
    private Object chave;
    private Object elemento;
    private No pai, filhoEsquerdo, filhoDireito;

    public No(Object c, Object e, No pai) {
        this.chave = c;
        this.elemento = e;
        this.pai = pai;
    }

    public No(Object c, Object e) {
        this.chave = c;
        this.elemento = e;
    }
    
    public void limpar()
    {
        this.pai = null;
        this.filhoEsquerdo = null;
        this.filhoDireito = null;
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

    public No getPai() {
        return pai;
    }

    public void setPai(No pai) {
        this.pai = pai;
    }

    public No getFilhoEsquerdo() {
        return filhoEsquerdo;
    }

    public void setFilhoEsquerdo(No filhoEsquerdo) {
        this.filhoEsquerdo = filhoEsquerdo;
    }

    public No getFilhoDireito() {
        return filhoDireito;
    }

    public void setFilhoDireito(No filhoDireito) {
        this.filhoDireito = filhoDireito;
    }
}
