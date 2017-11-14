package tad.arvorebinariapesquisa;

import java.util.ArrayList;
import java.util.Iterator;

public class ArvoreBinariaPesquisa {
    private int size;
    private No root;

    public ArvoreBinariaPesquisa(Object k, Object o) {
        size = 1;
        root = new No(k, o);
    }
    
    public int size() {
        return size;
    }
    
    public boolean isEmpty() {
        return false;
    }
    
    public int height() {
        return this.height(this.root);
    }
    
    private int height(No n) {
        if (this.isExternal(n)) {
            return 0;
        }
        int h = 0;
        if (this.hasLeftChild(n)) {
            h = this.height(n.getFilhoEsquerdo());
        }
        if (this.hasRightChild(n)) {
            h = Math.max(h, this.height(n.getFilhoDireito()));
        }
        return 1 + h;
    }
    
    public Iterator elements() {
        ArrayList v = new ArrayList();
        for (Iterator<No> it = this.nodes(); it.hasNext();) {
            v.add(it.next().getElemento());
        }
        return v.iterator();
    }
    
    public Iterator nodes() {
        return this.nodes(this.root).iterator();
    }
    
    private ArrayList nodes(No n) {
        ArrayList v = new ArrayList();
        v.add(n);
        if (this.hasLeftChild(n)) {
            v.addAll(this.nodes(n.getFilhoEsquerdo()));
        }
        if (this.hasRightChild(n)) {
            v.addAll(this.nodes(n.getFilhoDireito()));
        }
        return v;
    }
    
    public No root() {
        return this.root;
    }
    
    public boolean isInternal(No n) {
        return (this.hasLeftChild(n) || this.hasRightChild(n));
    }
    
    public boolean isExternal(No n) {
        return (!this.hasLeftChild(n) && !this.hasRightChild(n));
    }
    
    public boolean isRoot(No n) {
        return (n == this.root);
    }
    
    public int depth(No n) {
        if (n == this.root) {
            return 0;
        }
        return 1 + this.depth(n.getPai());
    }
    
    public boolean hasLeftChild(No n) {
        return (n.getFilhoEsquerdo() != null);
    }
    
    public boolean hasRightChild(No n) {
        return (n.getFilhoDireito() != null);
    }
    
    public No find(Object k) {
        return find(k, this.root());
    }

    private No find(Object k, No n) {
        if (this.isExternal(n)) {
            return n;
        }
        if ((int) n.getChave() > (int) k && this.hasLeftChild(n)) {
            return find(k, n.getFilhoEsquerdo());
        } else if ((int) n.getChave() < (int) k && this.hasRightChild(n)) {
            return find(k, n.getFilhoDireito());
        }
        return n;
    }
    
    public void insert(Object k, Object o) {
        No p = this.find(k);

        if ((int) p.getChave() != (int) k) {
            No n = new No(k, o, p);
            if ((int) p.getChave() > (int) k) {
                p.setFilhoEsquerdo(n);
            } else {
                p.setFilhoDireito(n);
            }
            this.size++;
        }

    }
    
    public Object remove(Object k) {
        No n = this.find(k);
        return this.remove(n);
    }

    private Object remove(No n) {
        Object o = n.getElemento();

        if (this.isInternal(n)) {
            No m;

            if (this.hasRightChild(n)) {
                m = (No) traverse(n.getFilhoDireito()).next();

                n.setChave(m.getChave());
                n.setElemento(m.getElemento());

                this.remove(m);
            } else {
                m = n.getFilhoEsquerdo();
                m.setPai(n.getPai());
                if (n.getPai().getFilhoEsquerdo() == n) {
                    n.getPai().setFilhoEsquerdo(m);
                } else {
                    n.getPai().setFilhoDireito(m);
                }
                n.limpar();
                this.size--;
            }
        } else {
            if (n.getPai().getFilhoEsquerdo() == n) {
                n.getPai().setFilhoEsquerdo(null);
            } else {
                n.getPai().setFilhoDireito(null);
            }
            n.limpar();
            this.size--;
        }

        return o;
    }

    public Iterator traverse() {
        return this.traverse(this.root);
    }

    public Iterator traverse(No n) {
        ArrayList v = new ArrayList();
        this.traverse(n, v);
        return v.iterator();
    }

    private void traverse(No n, ArrayList v) {
        if (this.hasLeftChild(n)) {
            traverse(n.getFilhoEsquerdo(), v);
        }
        v.add(n);
        if (this.hasRightChild(n)) {
            traverse(n.getFilhoDireito(), v);
        }
    }
}
