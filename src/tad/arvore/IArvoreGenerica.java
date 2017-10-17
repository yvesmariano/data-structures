package tad.arvore;

import tad.arvore.IArvore;

/**
 * Classe que extende de Arvore e acrescenta m�todos para adicionar 
 * nos em uma �rvore e remover n�s da �rvore
 */
public interface IArvoreGenerica extends IArvore
{
	
	public void addChild(No v, Object o);
	
	/**
	 * @param v
	 * @return Objeto que estava na posicao que foi removida
	 * @throws InvalidNoException
	 */
	public Object remove(No v) throws InvalidNoException;
}
