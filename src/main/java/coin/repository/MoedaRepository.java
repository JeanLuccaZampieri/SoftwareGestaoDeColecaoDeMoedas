package coin.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import coin.entity.BordaEntity;
import coin.entity.MaterialEntity;
import coin.entity.MoedaEntity;
import coin.entity.PaisEntity;

public class MoedaRepository {
	private static EntityManager em = Persistence
			.createEntityManagerFactory("projeto003-mysql")
			.createEntityManager();

	public void inserir(MoedaEntity moedaEntity) {
		try {
			em.getTransaction().begin();
			em.persist(moedaEntity);
			em.getTransaction().commit();
			System.out.println("Moeda inserido com sucesso");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Ocorreu um erro ao inserir os dados do Moeda.");
			System.out.println(e.getMessage());
		}
	}

	public void atualizar(MoedaEntity moedaEntity) {
		try {
			em.getTransaction().begin();
			em.merge(moedaEntity);
			em.getTransaction().commit();
			System.out.println("Moeda atualizado com sucesso");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Ocorreu um erro ao atualizar os dados do Moeda.");
			System.out.println(e.getMessage());
		}
	}

	public MoedaEntity pesquisaPeloId(Long id) {
		MoedaEntity MoedaEntity = null;
		try {
			MoedaEntity = em.find(MoedaEntity.class, id);
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao pesquisar o Moeda pelo id");
		}
		return MoedaEntity;
	}

	public void remover(Long id) {
		MoedaEntity MoedaEntity = pesquisaPeloId(id);
		if (MoedaEntity == null) {
			System.out.println("Moeda não encontrado");
		} else {
			remover(MoedaEntity);
		}
	}

	public void remover(MoedaEntity moedaEntity) {
		try {
			em.getTransaction().begin();
			em.remove(moedaEntity);
			em.getTransaction().commit();
			System.out.println("Moeda removido com sucesso");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Ocorreu um erro ao remover o Moeda");
		}
	}

	public List<MoedaEntity> listar() {
		List<MoedaEntity> moedas = null;
		Query query = em.createQuery("SELECT f FROM MoedaEntity f");
		try {
			moedas = query.getResultList();
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao listar todos os Moedaes");
		}
		return moedas;
	}

	public MoedaEntity pesquisaPeloNome(String nome) {
		MoedaEntity moedaEntity = null;
		Query query = em.createQuery("SELECT f FROM MoedaEntity f WHERE f.nome = :nome");
		query.setParameter("nome", nome);
		try {
			List<MoedaEntity> moedas = null;
			moedas = query.getResultList();
			if (moedas != null && !moedas.isEmpty()) {
				moedaEntity = moedas.get(0);
			}
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao pesquisar um Moeda pelo nome");
			System.out.println(e);
		}
		return moedaEntity;
	}
	public MoedaEntity pesquisaPeloCod(String cod) {
		MoedaEntity moedaEntity = null;
		Query query = em.createQuery("SELECT f FROM MoedaEntity f WHERE f.cod = :cod");
		query.setParameter("cod", cod);
		try {
			List<MoedaEntity> moedas = null;
			moedas = query.getResultList();
			if (moedas != null && !moedas.isEmpty()) {
				moedaEntity = moedas.get(0);
			}
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao pesquisar um Moeda pelo codigo");
			System.out.println(e);
		}
		return moedaEntity;
	}
	public List<BordaEntity> listarBordasPeloMoeda(Long id){
		List <BordaEntity> bordaEntity = null;
		MoedaEntity moedaEntity = pesquisaPeloId(id);
		bordaEntity = moedaEntity.getBordas();
		return bordaEntity;
	}
	
	public List<MaterialEntity> listarMateriaisPeloMoeda(Long id){
		List <MaterialEntity> materialEntity = null;
		MoedaEntity moedaEntity = pesquisaPeloId(id);
		materialEntity = moedaEntity.getMateriais();
		return materialEntity;
	}
	
	
	
}
