package coin.service;

import java.util.List;

import coin.entity.ColecaoEntity;
import coin.entity.PessoaEntity;
import coin.repository.ColecaoRepository;
public class ColecaoService {
	private ColecaoRepository colecaoRepository;

	public ColecaoService() {
		colecaoRepository = new ColecaoRepository();
	}

	public ColecaoEntity salvar(ColecaoEntity colecaoEntity) {
		if (colecaoEntity.getId() == null) {
			colecaoRepository.inserir(colecaoEntity);	
		} else {
			colecaoRepository.atualizar(colecaoEntity);
		}
		return colecaoEntity;
	}
	
	public List<ColecaoEntity> listar() {
		return colecaoRepository.listar();
	}
	
	public ColecaoEntity pesquisaId(Long id) {
		ColecaoEntity colecaoEntity = colecaoRepository.pesquisaPeloId(id);
		return colecaoEntity;
	}
	
	public List<ColecaoEntity> pesquisaPessoa(PessoaEntity pessoa) {
		List<ColecaoEntity>colecaoEntities = colecaoRepository.pesquisaPelaPessoa(pessoa);
		return colecaoEntities;
	}
	
	public void remover(ColecaoEntity colecaoEntity) {
		colecaoRepository.remover(colecaoEntity);
	}

	public void remover(Long id) {
		colecaoRepository.remover(id);
	}
	public List<ColecaoEntity> pesquisaNome(String nome){
		List<ColecaoEntity>colecaoEntities = colecaoRepository.pesquisaPeloNome(nome);
		return colecaoEntities;
	}
	public ColecaoEntity pesquisaColecaoPessoa(String colecao, PessoaEntity pessoa) {
		ColecaoEntity colecaoEntity = colecaoRepository.pesquisaColecaoPessoa(colecao, pessoa);
		return colecaoEntity;
	}
}
