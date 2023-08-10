package coin.service;

import java.util.List;

import coin.entity.BordaEntity;
import coin.entity.MaterialEntity;
import coin.entity.MoedaEntity;
import coin.repository.MoedaRepository;

public class MoedaService {
	private MoedaRepository moedaRepository;

	public MoedaService() {
		moedaRepository = new MoedaRepository();
	}

	public MoedaEntity salvar(MoedaEntity moedaEntity) {
		if (moedaEntity.getId() == null) {
			MoedaEntity entity = moedaRepository.pesquisaPeloNome(moedaEntity.getNome());
			if (entity == null) {
				moedaRepository.inserir(moedaEntity);
			} else {
				System.out.println("Moeda já existe");
			}
		} else {
			moedaRepository.atualizar(moedaEntity);
		}
		return moedaEntity;
	}

	public List<MoedaEntity> listar() {
		return moedaRepository.listar();
	}

	public void remover(MoedaEntity moedaEntity) {
		moedaRepository.remover(moedaEntity);
	}

	public void remover(Long id) {
		moedaRepository.remover(id);
	}
	public boolean validaNome (MoedaEntity moedaEntity) {
		MoedaEntity entity = new MoedaEntity();
		 entity = moedaRepository.pesquisaPeloNome(moedaEntity.getNome());
		if (entity == null) {
			return true;
		} else {
			return false;
		}	
	}	
	public boolean validaCod (MoedaEntity moedaEntity) {
		MoedaEntity entity = new MoedaEntity();
		 entity = moedaRepository.pesquisaPeloCod(moedaEntity.getCod());
		if (entity == null) {
			return true;
		} else {
			return false;
		}	
	}
	public MoedaEntity pesquisaId(Long id) {
		MoedaEntity moeda = moedaRepository.pesquisaPeloId(id);
		return moeda;
	}
	
	public List<MaterialEntity> materialMoeda(Long id){
		List<MaterialEntity> materialEntities = moedaRepository.listarMateriaisPeloMoeda(id);
		return materialEntities;
	}
	public List<BordaEntity> bordaMoeda(Long id){
		List<BordaEntity> bordaEntities = moedaRepository.listarBordasPeloMoeda(id);
		return bordaEntities;
	}
}
