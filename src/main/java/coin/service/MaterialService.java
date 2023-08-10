package coin.service;

import java.util.List;

import javax.swing.JOptionPane;

import coin.entity.MaterialEntity;
import coin.repository.MaterialRepository;

public class MaterialService {
	private MaterialRepository materialRepository;

	public MaterialService() {
		materialRepository = new MaterialRepository();
	}

	public MaterialEntity salvar(MaterialEntity materialEntity) {
		if (materialEntity.getId() == null) {
			MaterialEntity entity = materialRepository.pesquisaPeloNome(materialEntity.getNome());
			if (entity == null) {
				materialRepository.inserir(materialEntity);
				JOptionPane.showMessageDialog(null, "Material inserido com sucesso!");
			} else {
				JOptionPane.showMessageDialog(null, "Material já existe");
			}
		} else {
			materialRepository.atualizar(materialEntity);
		}
		return materialEntity;
	}

	public List<MaterialEntity> listar() {
		return materialRepository.listar();
	}

	public void remover(MaterialEntity materialEntity) {
		materialRepository.remover(materialEntity);
	}

	public void remover(Long id) {
		materialRepository.remover(id);
	}
	
	public List<String> listarNomes() {
		return materialRepository.listarNomes();
	}	
	
	public boolean validaNome (MaterialEntity materialEntity) {
		MaterialEntity entity = new MaterialEntity();
		 entity = materialRepository.pesquisaPeloNome(materialEntity.getNome());
		if (entity == null) {
			return true;
		} else {
			return false;
		}
	}
	public MaterialEntity perquisaNome(String nome) {
		MaterialEntity material = materialRepository.pesquisaPeloNome(nome);
		return material;
	}
	
	public MaterialEntity pesquisaId(Long id) {
		MaterialEntity material = materialRepository.pesquisaPeloId(id);
		return material;
	}
}
