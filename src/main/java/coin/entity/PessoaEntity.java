package coin.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_Pessoa")
public class PessoaEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pessoa")
	private Long id;
	@Column(nullable = false,
			length = 150,
			unique = true)
	private String nome;
	@Column(nullable = false,
			length = 11,
			unique = true)
	private String cpf;
	@Column(nullable = false,
			length = 150,
			unique = true)
	private String email;
	@Column(nullable = false,
			length = 14,
			unique = true)
	private String telefone;
	@Column(nullable = false,
			length = 150,
			unique = false)
	private String senha;
	@Column(nullable = false,
			length = 1,
			unique = false)
	private Character tipo;
	
	@OneToMany(mappedBy = "id_pessoa", cascade = CascadeType.ALL)
	List<ColecaoEntity> colecao;
}
