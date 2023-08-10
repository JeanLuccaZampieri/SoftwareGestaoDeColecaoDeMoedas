package coin.service;

import java.util.List;

import javax.swing.JOptionPane;

import coin.entity.BordaEntity;
import coin.repository.BordaRepository;

public class BordaService {
	private BordaRepository bordaRepository;

	public BordaService() {
		bordaRepository = new BordaRepository();
	}

	public BordaEntity salvar(BordaEntity bordaEntity) {
		if (bordaEntity.getId() == null) {
			BordaEntity entity = bordaRepository.pesquisaPeloNome(bordaEntity.getNome());
			if (entity == null) {
				bordaRepository.inserir(bordaEntity);
				JOptionPane.showMessageDialog(null, "Borda inserida com sucesso!");
			} else {
				JOptionPane.showMessageDialog(null, "A Borda "+bordaEntity.getNome()+" já existe");
			}
		} else {
			bordaRepository.atualizar(bordaEntity);
		}
		return bordaEntity;
	}

	public List<BordaEntity> listar() {
		return bordaRepository.listar();
	}

	public void remover(BordaEntity bordaEntity) {
		bordaRepository.remover(bordaEntity);
	}

	public void remover(Long id) {
		bordaRepository.remover(id);
	}

	public List<String> listarNomes() {
		return bordaRepository.listarNomes();
	}

	public boolean validaNome(BordaEntity bordaEntity) {
		BordaEntity entity = new BordaEntity();
		entity = bordaRepository.pesquisaPeloNome(bordaEntity.getNome());
		if (entity == null) {
			return true;
		} else {
			return false;
		}
	}

	public BordaEntity pesquisaNome(String nome) {
		BordaEntity borda = bordaRepository.pesquisaPeloNome(nome);
		return borda;
	}

	public BordaEntity pesquisaId(Long id) {
		BordaEntity borda = bordaRepository.pesquisaPeloId(id);
		return borda;
	}
}
