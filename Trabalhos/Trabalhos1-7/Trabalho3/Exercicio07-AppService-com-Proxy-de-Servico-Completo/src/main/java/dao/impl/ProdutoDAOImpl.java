package dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

import anotacao.PersistenceContext;
import dao.ProdutoDAO;
import excecao.ObjetoNaoEncontradoException;
import modelo.Produto;

public class ProdutoDAOImpl implements ProdutoDAO {

	@PersistenceContext
	protected EntityManager em;

	public long inclui(Produto umProduto) {
		em.persist(umProduto);
		return umProduto.getId();
	}

	public void altera(Produto umProduto) throws ObjetoNaoEncontradoException {
		Produto produto = em.find(Produto.class, umProduto.getId(), LockModeType.PESSIMISTIC_WRITE);
		if (produto == null) {
			throw new ObjetoNaoEncontradoException();
		}
		em.merge(umProduto);
	}

	public void exclui(long id) throws ObjetoNaoEncontradoException {
		Produto produto = em.find(Produto.class, id, LockModeType.PESSIMISTIC_WRITE);
		if (produto == null) {
			throw new ObjetoNaoEncontradoException();
		}
		em.remove(produto);
	}

	public Produto recuperaUmProduto(long id) throws ObjetoNaoEncontradoException {
		Produto umProduto = (Produto) em.find(Produto.class, new Long(id));

		if (umProduto == null) {
			throw new ObjetoNaoEncontradoException();
		}

		return umProduto;
	}

	public Produto recuperaUmProdutoComLock(long id) throws ObjetoNaoEncontradoException {
		Produto umProduto = em.find(Produto.class, id, LockModeType.PESSIMISTIC_WRITE);

		if (umProduto == null) {
			throw new ObjetoNaoEncontradoException();
		}

		return umProduto;
	}

	@SuppressWarnings("unchecked")
	public List<Produto> recuperaProdutos() {

		List<Produto> produtos = em.createQuery("select p from Produto p order by p.id asc").getResultList();
		return produtos;
	}
}