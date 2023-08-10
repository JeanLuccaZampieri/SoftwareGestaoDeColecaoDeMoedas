package coin.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import coin.entity.ColecaoEntity;
import coin.entity.ColecaoMoedaEntity;
import coin.entity.ColecaoMoedaId;
import coin.entity.MoedaEntity;
import coin.entity.PessoaEntity;
import coin.service.ColecaoService;

public class ColecaoMoedaRepository {
	private static EntityManager em = Persistence.createEntityManagerFactory("projeto003-mysql").createEntityManager();

	public void inserir(ColecaoMoedaEntity colecaoMoedaEntity) {
		em.getTransaction().begin();
		em.persist(colecaoMoedaEntity);
		em.getTransaction().commit();
		System.out.println("colecao moeda inserido");
	}

	public void atualizar(ColecaoMoedaEntity colecaoMoedaEntity) {
		try {
			em.getTransaction().begin();
			em.merge(colecaoMoedaEntity);
			em.getTransaction().commit();
			System.out.println("colecao moeda Atualizado");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Ocorreu um erro ao atualizar os dados da colecao.");
			System.out.println(e.getMessage());
		}
	}

	public ColecaoMoedaEntity pesquisaPeloId(ColecaoMoedaId id) {
		ColecaoMoedaEntity colecaoMoedaEntity = null;
		try {
			colecaoMoedaEntity = em.find(ColecaoMoedaEntity.class, id);
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao pesquisar a colecao pelo id");
		}
		return colecaoMoedaEntity;
	}

	public void remover(ColecaoMoedaId id) {
		ColecaoMoedaEntity colecaoMoedaEntity = pesquisaPeloId(id);
		remover(colecaoMoedaEntity);
	}

	public void remover(ColecaoMoedaEntity colecaoMoedaEntity) {
		try {
			em.getTransaction().begin();
			em.remove(colecaoMoedaEntity);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Ocorreu um erro ao remover a colecao");
		}
	}

	public List<ColecaoMoedaEntity> listar() {
		List<ColecaoMoedaEntity> colecaoMoedas = null;
		Query query = em.createQuery("SELECT p FROM ColecaoMoedaEntity p");
		try {
			colecaoMoedas = query.getResultList();
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao listar todos as colecaos");
		}
		return colecaoMoedas;
	}

	public List<ColecaoMoedaEntity> pesquisaPeloIdColecao(Long id) {
		ColecaoService colecaoService = new ColecaoService();
		ColecaoEntity colecaoEntity = colecaoService.pesquisaId(id);
		List<ColecaoMoedaEntity> colecaoMoedaEntity = new ArrayList<ColecaoMoedaEntity>();
		Query query = em.createQuery("SELECT f FROM ColecaoMoedaEntity f WHERE f.id.idColecao = :colecaoEntity");
		query.setParameter("colecaoEntity", colecaoEntity);
		try {
			List<ColecaoMoedaEntity> colecoes = null;
			colecoes = query.getResultList();
			colecaoMoedaEntity = colecoes;
			System.out.println("|||||||||||||||||||||||||||||||||");

			for (ColecaoMoedaEntity colecaoMoedaEntity2 : colecoes) {
				System.out.println(colecaoMoedaEntity2.getId());
			}
			System.out.println("|||||||||||||||||||||||||||||||||");
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao pesquisar uma colecao moeda pela colecao");
			System.out.println(e);
		}
		return colecaoMoedaEntity;
	}

	public List<ColecaoMoedaEntity> pesquisaPelaMoeda(MoedaEntity entity) {
		List<ColecaoMoedaEntity> colecaoEntity = null;
		Query query = em.createQuery("SELECT f FROM ColecaoMoedaEntity f WHERE f.id.idMoeda = :id_moeda");
		query.setParameter("id_moeda", entity);
		try {
			List<ColecaoMoedaEntity> colecoes = null;
			colecoes = query.getResultList();
			if (colecoes != null && !colecoes.isEmpty()) {
				colecaoEntity = colecoes;
			}
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao pesquisar uma colecao pela moeda");
			System.out.println(e);
		}
		return colecaoEntity;
	}
	
	public List<ColecaoMoedaEntity> pesquisaPelaColecao(ColecaoEntity entity) {
		List<ColecaoMoedaEntity> colecaoEntity = null;
		Query query = em.createQuery("SELECT f FROM ColecaoMoedaEntity f WHERE f.id.idColecao = :id_colecao");
		query.setParameter("id_colecao", entity);
		try {
			List<ColecaoMoedaEntity> moedas = null;
			moedas = query.getResultList();
			if (moedas != null && !moedas.isEmpty()) {
				colecaoEntity = moedas;
			}
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao pesquisar uma colecao pela colecao");
			System.out.println(e);
		}
		return colecaoEntity;
	}

	public List<ColecaoMoedaEntity> listarVendas() {
		char tipo = 'v';
		List<ColecaoMoedaEntity> colecaoMoedas = null;
		Query query = em.createQuery("SELECT f FROM ColecaoMoedaEntity f WHERE f.tipo = :tipo");
		query.setParameter("tipo", tipo);
		try {
			List<ColecaoMoedaEntity> colecoes = null;
			colecoes = query.getResultList();
			if (colecoes != null && !colecoes.isEmpty()) {
				colecaoMoedas = colecoes;
			}
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao listar as moedas a venda");
			System.out.println(e);
		}
		return colecaoMoedas;
	}

}