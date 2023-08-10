package coin.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import coin.entity.MaterialEntity;
import coin.entity.MaterialEntity;

public class MaterialRepository {
	private static EntityManager em = Persistence
			.createEntityManagerFactory("projeto003-mysql")
			.createEntityManager();

	public void inserir(MaterialEntity materialEntity) {
		try {
			em.getTransaction().begin();
			em.persist(materialEntity);
			em.getTransaction().commit();
			System.out.println("Material inserido com sucesso");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Ocorreu um erro ao inserir os dados do Material.");
			System.out.println(e.getMessage());
		}
	}

	public void atualizar(MaterialEntity materialEntity) {
		try {
			em.getTransaction().begin();
			em.merge(materialEntity);
			em.getTransaction().commit();
			System.out.println("Material atualizado com sucesso");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Ocorreu um erro ao atualizar os dados do Material.");
			System.out.println(e.getMessage());
		}
	}

	public MaterialEntity pesquisaPeloId(Long id) {
		MaterialEntity MaterialEntity = null;
		try {
			MaterialEntity = em.find(MaterialEntity.class, id);
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao pesquisar o Material pelo id");
		}
		return MaterialEntity;
	}

	public void remover(Long id) {
		MaterialEntity MaterialEntity = pesquisaPeloId(id);
		if (MaterialEntity == null) {
			System.out.println("Material não encontrado");
		} else {
			remover(MaterialEntity);
		}
	}

	public void remover(MaterialEntity materialEntity) {
		try {
			em.getTransaction().begin();
			em.remove(materialEntity);
			em.getTransaction().commit();
			System.out.println("Material removido com sucesso");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Ocorreu um erro ao remover o Material");
		}
	}

	public List<MaterialEntity> listar() {
		List<MaterialEntity> materiais = null;
		Query query = em.createQuery("SELECT f FROM MaterialEntity f");
		try {
			materiais = query.getResultList();
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao listar todos os Materiales");
		}
		return materiais;
	}

	public List<String> listarNomes() {
		List<String> nomes =null;
		Query query = em.createQuery("SELECT f.nome FROM MaterialEntity f");
		try {
			nomes = query.getResultList();
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao listar todos os NOMES de materiais");
		}
		return nomes;
	}
	
	public MaterialEntity pesquisaPeloNome(String nome) {
		MaterialEntity MaterialEntity = null;
		Query query = em.createQuery("SELECT f FROM MaterialEntity f WHERE f.nome = :nome");
		query.setParameter("nome", nome);
		try {
			List<MaterialEntity> Materiais = null;
			Materiais = query.getResultList();
			if (Materiais != null && !Materiais.isEmpty()) {
				MaterialEntity = Materiais.get(0);
			}
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao pesquisar um Material pelo nome");
			System.out.println(e);
		}
		return MaterialEntity;
	}
}
