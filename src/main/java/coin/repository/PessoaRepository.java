package coin.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import coin.entity.PessoaEntity;

public class PessoaRepository {
	private static EntityManager em = Persistence
			.createEntityManagerFactory("projeto003-mysql")
			.createEntityManager();

	public void inserir(PessoaEntity pessoaEntity) {
		try {
			em.getTransaction().begin();
			em.persist(pessoaEntity);
			em.getTransaction().commit();
			System.out.println("Pessoa inserido com sucesso");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Ocorreu um erro ao inserir os dados do Pessoa.");
			System.out.println(e.getMessage());
		}
	}

	public void atualizar(PessoaEntity pessoaEntity) {
		try {
			em.getTransaction().begin();
			em.merge(pessoaEntity);
			em.getTransaction().commit();
			System.out.println("Pessoa atualizado com sucesso");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Ocorreu um erro ao atualizar os dados do Pessoa.");
			System.out.println(e.getMessage());
		}
	}

	public PessoaEntity pesquisaPeloId(Long id) {
		PessoaEntity pessoaEntity = null;
		try {
			pessoaEntity = em.find(PessoaEntity.class, id);
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao pesquisar o Pessoa pelo id");
		}
		return pessoaEntity;
	}
	
	public void remover(Long id) {
		PessoaEntity pessoaEntity = pesquisaPeloId(id);
		if (pessoaEntity == null) {
			System.out.println("Pessoa não encontrado");
		} else {
			remover(pessoaEntity);
		}
	}

	public void remover(PessoaEntity pessoaEntity) {
		try {
			em.getTransaction().begin();
			em.remove(pessoaEntity);
			em.getTransaction().commit();
			System.out.println("Pessoa removido com sucesso");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Ocorreu um erro ao remover o Pessoa");
		}
	}

	public List<PessoaEntity> listar() {
		List<PessoaEntity> pessoas = null;
		Query query = em.createQuery("SELECT f FROM PessoaEntity f");
		try {
			pessoas = query.getResultList();
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao listar todos os pessoas");
		}
		return pessoas;
	}
	
	public List<PessoaEntity> pesquisaPelaSenha(String senha) {
		List<PessoaEntity> pessoaEntity = null;
		Query query = em.createQuery("SELECT f FROM PessoaEntity f WHERE f.senha = :senha");
		query.setParameter("senha", senha);
		try {
			List<PessoaEntity> pessoas = null;
			pessoas = query.getResultList();
			if (pessoas != null && !pessoas.isEmpty()) {
				pessoaEntity = pessoas;
			}
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao pesquisar um Pessoa pela senha");
			System.out.println(e);
		}
		return pessoaEntity;
	}
	
	public PessoaEntity pesquisaPeloEmail(String email) {
		PessoaEntity pessoaEntity = null;
		Query query = em.createQuery("SELECT f FROM PessoaEntity f WHERE f.email = :email");
		query.setParameter("email", email);
		try {
			List<PessoaEntity> pessoas = null;
			pessoas = query.getResultList();
			if (pessoas != null && !pessoas.isEmpty()) {
				pessoaEntity = pessoas.get(0);
			}
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao pesquisar um Pessoa pelo email");
			System.out.println(e);
		}
		return pessoaEntity;
	}
	public PessoaEntity pesquisaPeloCpf(String cpf) {
		PessoaEntity pessoaEntity = null;
		Query query = em.createQuery("SELECT f FROM PessoaEntity f WHERE f.cpf = :cpf");
		query.setParameter("cpf", cpf);
		try {
			List<PessoaEntity> pessoas = null;
			pessoas = query.getResultList();
			if (pessoas != null && !pessoas.isEmpty()) {
				pessoaEntity = pessoas.get(0);
			}
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao pesquisar um Pessoa pelo cpf");
			System.out.println(e);
		}
		return pessoaEntity;
	}
	
	
	public PessoaEntity pesquisaPeloNome(String nome) {
		PessoaEntity pessoaEntity = null;
		Query query = em.createQuery("SELECT f FROM PessoaEntity f WHERE f.nome = :nome");
		query.setParameter("nome", nome);
		try {
			List<PessoaEntity> pessoas = null;
			pessoas = query.getResultList();
			if (pessoas != null && !pessoas.isEmpty()) {
				pessoaEntity = pessoas.get(0);
			}
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao pesquisar um Pessoa pelo nome");
			System.out.println(e);
		}
		return pessoaEntity;
	}
	public PessoaEntity pesquisaPeloTelefone(String telefone) {
		PessoaEntity pessoaEntity = null;
		Query query = em.createQuery("SELECT f FROM PessoaEntity f WHERE f.telefone = :telefone");
		query.setParameter("telefone", telefone);
		try {
			List<PessoaEntity> pessoas = null;
			pessoas = query.getResultList();
			if (pessoas != null && !pessoas.isEmpty()) {
				pessoaEntity = pessoas.get(0);
			}
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao pesquisar um Pessoa pelo telefone");
			System.out.println(e);
		}
		return pessoaEntity;
	}
}
