package coin.service;

import java.util.List;

import javax.swing.JOptionPane;

import coin.entity.PaisEntity;
import coin.repository.PaisRepository;

public class PaisService {
	private PaisRepository paisRepository;

	public PaisService() {
		paisRepository = new PaisRepository();
	}

	public PaisEntity salvar(PaisEntity paisEntity) {
		if (paisEntity.getId() == null) {
			PaisEntity entity = paisRepository.pesquisaPeloNome(paisEntity.getNome());
			if (entity == null) {
				paisRepository.inserir(paisEntity);
				JOptionPane.showMessageDialog(null, "Pais inserido com sucesso!");
			} else {
				System.out.println("Pais já existe");
			}
		} else {
			paisRepository.atualizar(paisEntity);
		}
		return paisEntity;
	}

	public List<PaisEntity> listar() {
		return paisRepository.listar();
	}

	public void remover(PaisEntity paisEntity) {
		paisRepository.remover(paisEntity);
	}

	public void remover(Long id) {
		paisRepository.remover(id);
	}
	public boolean validaNome (PaisEntity paisEntity) {
		PaisEntity entity = new PaisEntity();
		 entity = paisRepository.pesquisaPeloNome(paisEntity.getNome());
		if (entity == null) {
			return true;
		} else {
			return false;
		}
	}
	
	public List<String> listarNomes() {
		return paisRepository.listarNomes();
	}	
	public PaisEntity pesquisaNome(String nome) {
		PaisEntity pais = paisRepository.pesquisaPeloNome(nome);
		return pais;
	}
	public PaisEntity pesquisaId(Long id) {
		PaisEntity pais = paisRepository.pesquisaPeloId(id);
		return pais;
	}
}
