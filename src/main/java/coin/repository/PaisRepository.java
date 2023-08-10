package coin.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import coin.entity.PaisEntity;
import coin.entity.PaisEntity;

public class PaisRepository {
	private static EntityManager em = Persistence
			.createEntityManagerFactory("projeto003-mysql")
			.createEntityManager();

	public void inserir(PaisEntity PaisEntity) {
		try {
			em.getTransaction().begin();
			em.persist(PaisEntity);
			em.getTransaction().commit();
			System.out.println("pais inserido com sucesso");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Ocorreu um erro ao inserir os dados do pais.");
			System.out.println(e.getMessage());
		}
	}

	public void atualizar(PaisEntity PaisEntity) {
		try {
			em.getTransaction().begin();
			em.merge(PaisEntity);
			em.getTransaction().commit();
			System.out.println("pais atualizado com sucesso");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Ocorreu um erro ao atualizar os dados do pais.");
			System.out.println(e.getMessage());
		}
	}

	public PaisEntity pesquisaPeloId(Long id) {
		PaisEntity paisEntity = null;
		try {
			paisEntity = em.find(PaisEntity.class, id);
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao pesquisar o pais pelo id");
		}
		return paisEntity;
	}

	public void remover(Long id) {
		PaisEntity paisEntity = pesquisaPeloId(id);
		if (paisEntity == null) {
			System.out.println("Pais não encontrado");
		} else {
			remover(paisEntity);
		}
	}

	public void remover(PaisEntity paisEntity) {
		try {
			em.getTransaction().begin();
			em.remove(paisEntity);
			em.getTransaction().commit();
			System.out.println("Pais removido com sucesso");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Ocorreu um erro ao remover o pais");
		}
	}

	public List<PaisEntity> listar() {
		List<PaisEntity> paises = null;
		Query query = em.createQuery("SELECT f FROM PaisEntity f");
		try {
			paises = query.getResultList();
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao listar todos os paises");
		}
		return paises;
	}
	
	public List<String> listarNomes() {
		List<String> nomes =null;
		Query query = em.createQuery("SELECT f.nome FROM PaisEntity f");
		try {
			nomes = query.getResultList();
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao listar todos os NOMES de paises");
		}
		return nomes;
	}

	public PaisEntity pesquisaPeloNome(String nome) {
		PaisEntity paisEntity = null;
		Query query = em.createQuery("SELECT f FROM PaisEntity f WHERE f.nome = :nome");
		query.setParameter("nome", nome);
		try {
			List<PaisEntity> paises = null;
			paises = query.getResultList();
			if (paises != null && !paises.isEmpty()) {
				paisEntity = paises.get(0);
			}
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao pesquisar um pais pelo nome");
			System.out.println(e);
		}
		return paisEntity;
	}
}
