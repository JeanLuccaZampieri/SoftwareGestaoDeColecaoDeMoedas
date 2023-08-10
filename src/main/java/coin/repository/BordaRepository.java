package coin.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import coin.entity.BordaEntity;

public class BordaRepository {
	private static EntityManager em = Persistence
			.createEntityManagerFactory("projeto003-mysql")
			.createEntityManager();

	public void inserir(BordaEntity BordaEntity) {
		try {
			em.getTransaction().begin();
			em.persist(BordaEntity);
			em.getTransaction().commit();
			System.out.println("borda inserido com sucesso");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Ocorreu um erro ao inserir os dados do borda.");
			System.out.println(e.getMessage());
		}
	}

	public void atualizar(BordaEntity BordaEntity) {
		try {
			em.getTransaction().begin();
			em.merge(BordaEntity);
			em.getTransaction().commit();
			System.out.println("borda atualizado com sucesso");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Ocorreu um erro ao atualizar os dados do borda.");
			System.out.println(e.getMessage());
		}
	}

	public BordaEntity pesquisaPeloId(Long id) {
		BordaEntity bordaEntity = null;
		try {
			bordaEntity = em.find(BordaEntity.class, id);
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao pesquisar o borda pelo id");
		}
		return bordaEntity;
	}

	public void remover(Long id) {
		BordaEntity bordaEntity = pesquisaPeloId(id);
		if (bordaEntity == null) {
			System.out.println("Borda não encontrado");
		} else {
			remover(bordaEntity);
		}
	}

	public void remover(BordaEntity bordaEntity) {
		try {
			em.getTransaction().begin();
			em.remove(bordaEntity);
			em.getTransaction().commit();
			System.out.println("Borda removido com sucesso");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Ocorreu um erro ao remover o borda");
		}
	}

	public List<BordaEntity> listar() {
		List<BordaEntity> bordas = null;
		Query query = em.createQuery("SELECT f FROM BordaEntity f");
		try {
			bordas = query.getResultList();
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao listar todos os bordas");
		}
		return bordas;
	}
	
	public List<String> listarNomes() {
		List<String> nomes =null;
		Query query = em.createQuery("SELECT f.nome FROM BordaEntity f");
		try {
			nomes = query.getResultList();
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao listar todos os NOMES de bordas");
		}
		return nomes;
	}

	public BordaEntity pesquisaPeloNome(String nome) {
		BordaEntity bordaEntity = null;
		Query query = em.createQuery("SELECT f FROM BordaEntity f WHERE f.nome = :nome");
		query.setParameter("nome", nome);
		try {
			List<BordaEntity> bordas = null;
			bordas = query.getResultList();
			if (bordas != null && !bordas.isEmpty()) {
				bordaEntity = bordas.get(0);
			}
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao pesquisar um borda pelo nome");
			System.out.println(e);
		}
		return bordaEntity;
	}

}
