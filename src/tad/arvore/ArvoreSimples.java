package tad.arvore;

import java.util.Iterator;
import java.util.ArrayList;

public class ArvoreSimples implements IArvoreGenerica
{
    //Atributos da �rvore
    private No raiz;
    private int tamanho;

    public ArvoreSimples(Object o)
    {
        raiz = new No(null, o);
        tamanho = 1;
    }
    
    @Override
    public No root()
    {
        return raiz;
    }
    
    @Override
    public No parent(No v)
    {
        return (v.parent());
    }

    @Override
    public Iterator children(No v)
    {
        return v.children();
    }
    
    @Override
    public boolean isInternal(No v)
    {
        return (v.childrenNumber() > 0);
    }
    
    @Override
    public boolean isExternal(No v)
    {
        return (v.childrenNumber() == 0);
    }
    
    public boolean isRoot(No v)
    {
        return v == raiz;
    }
    
    @Override
    public void addChild(No v, Object o)
    {
        No novo = new No(v, o);
        v.addChild(novo);
        tamanho++;
    }
    
    @Override
    public Object remove(No v) throws InvalidNoException
    {
        No pai = v.parent();
        if (pai != null || isExternal(v))
                pai.removeChild(v);
        else
                throw new InvalidNoException();
        Object o = v.element();
        tamanho--;
        return o;
    }
    
    @Override
    public boolean isEmpty()
    {
        return false;
    }
    
    @Override
    public int depth(No v)
    {
        if (v == raiz)
                return 0;
        else
                return 1 + depth(v.parent());
    }
    
    @Override
    public int size()
    {
        return tamanho;
    }
    
    public void swap(No v, No w)
    {
        v.parent().removeChild(v);
        w.parent().removeChild(w);
        
        No pai = v.parent();
        v.parent(w.parent());
        w.parent(pai);
        
        v.parent().addChild(v);
        w.parent().addChild(w);
    }
    
    @Override
    public int height()
    {
        return height(raiz);
    }
    
    private int height(No v)
    {
        if (isExternal(v))
            return 0;
        
        int height = 0;
        Iterator<No> iterator = this.children(v);
        while (iterator.hasNext()) {
            height = Math.max(height, this.height(iterator.next()));
        }
        
        return 1 + height;
    }
    
    @Override
    public Iterator nos() {
        return this.nos(raiz).iterator();
    }
    
    private ArrayList nos(No v)
    {
        ArrayList arr = new ArrayList();
        arr.add(v);
        Iterator<No> iterator = this.children(v);
        while (iterator.hasNext()) {            
            arr.addAll(this.nos(iterator.next()));
        }
        return arr;
    }
    
    @Override
    public Iterator elements()
    {
        ArrayList arr = new ArrayList();
        Iterator<No> iterator = this.nos();
        while (iterator.hasNext()) {
            arr.add(iterator.next().element());
        }
        return arr.iterator();
    }
    
    @Override
    public Object replace(No v, Object o)
    {
        Object velho = v.element();
        v.setElement(o);
        return velho;
    }
}
