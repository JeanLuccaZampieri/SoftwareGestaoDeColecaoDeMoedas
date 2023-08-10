package coin.service;

import java.util.List;

import coin.entity.ColecaoEntity;
import coin.entity.ColecaoMoedaEntity;
import coin.entity.ColecaoMoedaId;
import coin.entity.MoedaEntity;
import coin.repository.ColecaoMoedaRepository;

public class ColecaoMoedaService {
	private ColecaoMoedaRepository colecaoMoedaRepository;

	public ColecaoMoedaService() {
		colecaoMoedaRepository = new ColecaoMoedaRepository();
	}

	public ColecaoMoedaEntity salvar(ColecaoMoedaEntity colecaoMoedaEntity, byte ver) {
		
		if (pesquisaPeloId(colecaoMoedaEntity.getId())== null) {
			colecaoMoedaRepository.inserir(colecaoMoedaEntity);	
		} else {
			if(ver == 1) {
				ColecaoMoedaEntity colecaoAntiga = new ColecaoMoedaEntity();
				colecaoAntiga = pesquisaPeloId(colecaoMoedaEntity.getId());
				colecaoMoedaEntity.setQuantidade(colecaoAntiga.getQuantidade()+colecaoMoedaEntity.getQuantidade());
				colecaoMoedaRepository.atualizar(colecaoMoedaEntity);
			}if(ver==2) {
				colecaoMoedaRepository.atualizar(colecaoMoedaEntity);
			}
		}
		return colecaoMoedaEntity;
	}

	public List<ColecaoMoedaEntity> listar() {
		return colecaoMoedaRepository.listar();
	}

	public void remover(ColecaoMoedaEntity colecaoMoedaEntity) {
		colecaoMoedaRepository.remover(colecaoMoedaEntity);
	}

	public ColecaoMoedaEntity pesquisaPeloId(ColecaoMoedaId id) {
		ColecaoMoedaEntity colecaoMoedaEntity = colecaoMoedaRepository.pesquisaPeloId(id);
		return colecaoMoedaEntity;
	}

	public List<ColecaoMoedaEntity> pesquisaPelaMoeda(MoedaEntity moeda) {
		List<ColecaoMoedaEntity> colecaoMoedaEntities = colecaoMoedaRepository.pesquisaPelaMoeda(moeda);
		return colecaoMoedaEntities;
	}
	
	public List<ColecaoMoedaEntity> listarMoedas(Long id) {
		List<ColecaoMoedaEntity> colecaoMoedaEntities = colecaoMoedaRepository.pesquisaPeloIdColecao(id);
		return colecaoMoedaEntities;
	}
	public List<ColecaoMoedaEntity> listarMoedasVenda() {
		List<ColecaoMoedaEntity> colecaoMoedaEntities = colecaoMoedaRepository.listarVendas();
		return colecaoMoedaEntities;
	}
	public List<ColecaoMoedaEntity> pesquisaPelaColecao(ColecaoEntity colecao) {
		List<ColecaoMoedaEntity> colecaoMoedaEntities = colecaoMoedaRepository.pesquisaPelaColecao(colecao);
		return colecaoMoedaEntities;
	}
}