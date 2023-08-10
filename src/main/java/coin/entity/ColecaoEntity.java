package coin.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "TB_Colecao")
public class ColecaoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_colecao")
	private Long id;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@OneToMany(mappedBy = "id.idColecao", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	List<ColecaoMoedaEntity> colecaoMoedas;
	
	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "id_pessoa", nullable = false)
	private PessoaEntity id_pessoa;
}
