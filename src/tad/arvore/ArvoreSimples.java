package tad.arvore;

import java.util.Iterator;
import java.util.ArrayList;

public class ArvoreSimples implements IArvoreGenerica
{
    //Atributos da �rvore
    private NoArvore raiz;
    private int tamanho;

    public ArvoreSimples(Object o)
    {
        raiz = new NoArvore(null, o);
        tamanho = 1;
    }
    
    @Override
    public NoArvore root()
    {
        return raiz;
    }
    
    @Override
    public NoArvore parent(NoArvore v)
    {
        return (v.parent());
    }

    @Override
    public Iterator children(NoArvore v)
    {
        return v.children();
    }
    
    @Override
    public boolean isInternal(NoArvore v)
    {
        return (v.childrenNumber() > 0);
    }
    
    @Override
    public boolean isExternal(NoArvore v)
    {
        return (v.childrenNumber() == 0);
    }
    
    public boolean isRoot(NoArvore v)
    {
        return v == raiz;
    }
    
    @Override
    public void addChild(NoArvore v, Object o)
    {
        NoArvore novo = new NoArvore(v, o);
        v.addChild(novo);
        tamanho++;
    }
    
    @Override
    public Object remove(NoArvore v) throws InvalidNoException
    {
        NoArvore pai = v.parent();
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
    public int depth(NoArvore v)
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
    
    public void swap(NoArvore v, NoArvore w)
    {
        v.parent().removeChild(v);
        w.parent().removeChild(w);
        
        NoArvore pai = v.parent();
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
    
    private int height(NoArvore v)
    {
        if (isExternal(v))
            return 0;
        
        int height = 0;
        Iterator<NoArvore> iterator = this.children(v);
        while (iterator.hasNext()) {
            height = Math.max(height, this.height(iterator.next()));
        }
        
        return 1 + height;
    }
    
    @Override
    public Iterator nos() {
        return this.nos(raiz).iterator();
    }
    
    private ArrayList nos(NoArvore v)
    {
        ArrayList arr = new ArrayList();
        arr.add(v);
        Iterator<NoArvore> iterator = this.children(v);
        while (iterator.hasNext()) {            
            arr.addAll(this.nos(iterator.next()));
        }
        return arr;
    }
    
    @Override
    public Iterator elements()
    {
        ArrayList arr = new ArrayList();
        Iterator<NoArvore> iterator = this.nos();
        while (iterator.hasNext()) {
            arr.add(iterator.next().element());
        }
        return arr.iterator();
    }
    
    @Override
    public Object replace(NoArvore v, Object o)
    {
        Object velho = v.element();
        v.setElement(o);
        return velho;
    }
}
