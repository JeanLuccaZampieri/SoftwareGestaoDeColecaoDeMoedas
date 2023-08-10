package coin.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import coin.entity.ColecaoEntity;
import coin.entity.MoedaEntity;
import coin.entity.PessoaEntity;

public class ColecaoRepository {
	private static EntityManager em = Persistence.createEntityManagerFactory("projeto003-mysql").createEntityManager();

	public void inserir(ColecaoEntity colecaoEntity) {
		try {
			em.getTransaction().begin();
			em.persist(colecaoEntity);
			em.getTransaction().commit();
			System.out.println("Colecao inserido com sucesso");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Ocorreu um erro ao inserir os dados do Colecao.");
			System.out.println(e.getMessage());
		}
	}

	public void atualizar(ColecaoEntity colecaoEntity) {
		try {
			em.getTransaction().begin();
			em.merge(colecaoEntity);
			em.getTransaction().commit();
			System.out.println("Colecao atualizado com sucesso");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Ocorreu um erro ao atualizar os dados do Colecao.");
			System.out.println(e.getMessage());
		}
	}

	public ColecaoEntity pesquisaPeloId(Long id) {
		ColecaoEntity colecaoEntity = null;
		try {
			colecaoEntity = em.find(ColecaoEntity.class, id);
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao pesquisar a colecao pelo id");
		}
		return colecaoEntity;
	}

	public List<ColecaoEntity> pesquisaPelaPessoa(PessoaEntity entity) {
		List<ColecaoEntity> colecaoEntity = null;
		Query query = em.createQuery("SELECT f FROM ColecaoEntity f WHERE f.id_pessoa = :id_pessoa");
		query.setParameter("id_pessoa", entity);
		try {
			List<ColecaoEntity> colecoes = null;
			colecoes = query.getResultList();
			if (colecoes != null && !colecoes.isEmpty()) {
				colecaoEntity = colecoes;
			}
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao pesquisar uma colecao pela pessoa");
			System.out.println(e);
		}
		return colecaoEntity;
	}
	
	
	public List<ColecaoEntity> pesquisaPeloNome(String nome) {
		List<ColecaoEntity> colecaoEntity = null;
		Query query = em.createQuery("SELECT f FROM ColecaoEntity f WHERE f.nome = :nome");
		query.setParameter("nome", nome);
		try {
			List<ColecaoEntity> colecoes = null;
			colecoes = query.getResultList();
			if (colecoes != null && !colecoes.isEmpty()) {
				colecaoEntity = colecoes;
			}
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao pesquisar colecoes pela nome");
			System.out.println(e);
		}
		return colecaoEntity;
	}

	public void remover(Long id) {
		ColecaoEntity colecaoEntity = pesquisaPeloId(id);
		if (colecaoEntity == null) {
			System.out.println("Colecao não encontrado");
		} else {
			remover(colecaoEntity);
		}
	}

	public void remover(ColecaoEntity colecaoEntity) {
		try {
			em.getTransaction().begin();
			em.remove(colecaoEntity);
			em.getTransaction().commit();
			System.out.println("Colecao removido com sucesso");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Ocorreu um erro ao remover o Colecao");
		}
	}

	public ColecaoEntity pesquisaColecaoPessoa(String colecao, PessoaEntity pessoa) {
		try {
			List<ColecaoEntity> colecaoNome = pesquisaPeloNome(colecao);
			for (ColecaoEntity colecaoEntity : colecaoNome) {
				if (colecaoEntity.getId_pessoa().getId() == pessoa.getId()) {
					colecaoEntity = pesquisaPeloId(colecaoEntity.getId());
					return colecaoEntity;
				}
			}

		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao pesquisar pela colecao da pessoa");

		}
		return null;
	}

	public List<ColecaoEntity> listar() {
		List<ColecaoEntity> colecoes = null;
		Query query = em.createQuery("SELECT f FROM ColecaoEntity f");
		try {
			colecoes = query.getResultList();
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao listar todos os Colecoes");
		}
		return colecoes;
	}

}
