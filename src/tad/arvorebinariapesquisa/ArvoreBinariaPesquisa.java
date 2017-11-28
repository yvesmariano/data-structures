package tad.arvorebinariapesquisa;

import java.util.ArrayList;
import java.util.Iterator;

public class ArvoreBinariaPesquisa {
    private int tamanho;
    private NoABP raiz;

    public ArvoreBinariaPesquisa(Object k, Object o) {
        tamanho = 1;
        raiz = new NoABP(k, o);
    }
    
    public int tamanho() {
        return tamanho;
    }
    
    public boolean estaVazia() {
        return false;
    }
    
    public int altura() {
        return this.altura(this.raiz);
    }
    
    private int altura(NoABP n) {
        if (this.eNoExterno(n)) {
            return 0;
        }
        int h = 0;
        if (this.temFilhoEsquerdo(n)) {
            h = this.altura(n.getFilhoEsquerdo());
        }
        if (this.temFilhoDireito(n)) {
            h = Math.max(h, this.altura(n.getFilhoDireito()));
        }
        return 1 + h;
    }
    
    public Iterator elementos() {
        ArrayList v = new ArrayList();
        for (Iterator<NoABP> it = this.nos(); it.hasNext();) {
            v.add(it.next().getElemento());
        }
        return v.iterator();
    }
    
    public Iterator nos() {
        return this.nos(this.raiz).iterator();
    }
    
    private ArrayList nos(NoABP n) {
        ArrayList v = new ArrayList();
        v.add(n);
        if (this.temFilhoEsquerdo(n)) {
            v.addAll(this.nos(n.getFilhoEsquerdo()));
        }
        if (this.temFilhoDireito(n)) {
            v.addAll(this.nos(n.getFilhoDireito()));
        }
        return v;
    }
    
    public NoABP raiz() {
        return this.raiz;
    }
    
    public boolean eNoInterno(NoABP n) {
        return (this.temFilhoEsquerdo(n) || this.temFilhoDireito(n));
    }
    
    public boolean eNoExterno(NoABP n) {
        return (!this.temFilhoEsquerdo(n) && !this.temFilhoDireito(n));
    }
    
    public boolean eRaiz(NoABP n) {
        return (n == this.raiz);
    }
    
    public int profundidade(NoABP n) {
        if (n == this.raiz) {
            return 0;
        }
        return 1 + this.profundidade(n.getPai());
    }
    
    public boolean temFilhoEsquerdo(NoABP n) {
        return (n.getFilhoEsquerdo() != null);
    }
    
    public boolean temFilhoDireito(NoABP n) {
        return (n.getFilhoDireito() != null);
    }
    
    public NoABP encontrar(Object chave) {
        return encontrar(chave, this.raiz());
    }

    private NoABP encontrar(Object chave, NoABP n) {
        if (this.eNoExterno(n)) {
            return n;
        }
        if ((int) n.getChave() > (int) chave && this.temFilhoEsquerdo(n)) {
            return encontrar(chave, n.getFilhoEsquerdo());
        } else if ((int) n.getChave() < (int) chave && this.temFilhoDireito(n)) {
            return encontrar(chave, n.getFilhoDireito());
        }
        return n;
    }
    
    public void inserir(Object chave, Object o) {
        NoABP p = this.encontrar(chave);

        if ((int) p.getChave() != (int) chave) {
            NoABP n = new NoABP(chave, o, p);
            if ((int) p.getChave() > (int) chave) {
                p.setFilhoEsquerdo(n);
            } else {
                p.setFilhoDireito(n);
            }
            this.tamanho++;
        }

    }
    
    public Object remover(Object chave) {
        NoABP n = this.encontrar(chave);
        return this.remover(n);
    }

    private Object remover(NoABP n) {
        Object o = n.getElemento();

        if (this.eNoInterno(n)) {
            NoABP m;

            if (this.temFilhoDireito(n)) {
                m = (NoABP) ArvoreBinariaPesquisa.this.inOrder(n.getFilhoDireito()).next();

                n.setChave(m.getChave());
                n.setElemento(m.getElemento());

                this.remover(m);
            } else {
                m = n.getFilhoEsquerdo();
                m.setPai(n.getPai());
                if (n.getPai().getFilhoEsquerdo() == n) {
                    n.getPai().setFilhoEsquerdo(m);
                } else {
                    n.getPai().setFilhoDireito(m);
                }
                n.limpar();
                this.tamanho--;
            }
        } else {
            if (n.getPai().getFilhoEsquerdo() == n) {
                n.getPai().setFilhoEsquerdo(null);
            } else {
                n.getPai().setFilhoDireito(null);
            }
            n.limpar();
            this.tamanho--;
        }

        return o;
    }

    public Iterator inOrder() {
        return this.inOrder(this.raiz);
    }

    public Iterator inOrder(NoABP n) {
        ArrayList v = new ArrayList();
        this.inOrder(n, v);
        return v.iterator();
    }

    private void inOrder(NoABP n, ArrayList v) {
        if (this.temFilhoEsquerdo(n)) {
            inOrder(n.getFilhoEsquerdo(), v);
        }
        v.add(n);
        if (this.temFilhoDireito(n)) {
            inOrder(n.getFilhoDireito(), v);
        }
    }
}
