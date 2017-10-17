package tad;

import java.util.Iterator;
import tad.arvore.*;
import tad.pilha.*;
import tad.fila.*;

public class TadApplication {
    
    public static void main(String[] args) {
        testarArvore();
    }
    
    public static void testarArvore() {
        ArvoreSimples arvore = new ArvoreSimples("a");
        No r = arvore.root();
        arvore.addChild(r, "b");
        arvore.addChild(r, "c");
        mostrarArvore(arvore);
        System.out.println("Altura: " + arvore.height());
        System.out.println("Tamanho: " + arvore.size());
    }
    
    public static void mostrarArvore (ArvoreSimples a) {
        mostrarSubArvore(a.root(), 2, 0);
    }
    
    public static void mostrarSubArvore(No no, int indent, int level)
    {
        for (int i = 0; i < indent * level; i++) {
            System.out.print(" ");
        }
        System.out.println(no.element());
        Iterator<No> iterator = no.children();
        while(iterator.hasNext())
            mostrarSubArvore(iterator.next(), indent, level + 1);
    }
    
    public static void testarPilha() {
        PilhaLista p = new PilhaLista();
        p.empilhar(10);
        p.empilhar(20);
        p.empilhar(30);
        System.out.println("Tamanho: " + String.valueOf(p.tamanho()));
        try {
            System.out.println("Desempilhando: " + String.valueOf(p.desempilhar()));
            System.out.println("Desempilhando: " + String.valueOf(p.desempilhar()));
            System.out.println("Desempilhando: " + String.valueOf(p.desempilhar()));
        }
        catch(EPilhaVazia e){
            
        }
        System.out.println("Pilha vazia? " + (p.estaVazia() ? "Sim" : "Não"));
    }
    
    public static void testarFila()
    {
        FilaArray f = new FilaArray(1);
        f.enfileirar(10);
        f.enfileirar(20);
        f.enfileirar(30);
        try {
            f.desenfileirar();
            f.desenfileirar();
            f.enfileirar(40);
            f.enfileirar(50);
            f.enfileirar(60);
            f.enfileirar(70);
            System.out.println("Tamanho: " + String.valueOf(f.tamanho()));
            while(!f.estaVazia()) {
                System.out.println("Desenfileirando: " + String.valueOf(f.desenfileirar()));
            }
        }
        catch(EFilaVazia e){
            
        }
    }
    
}
