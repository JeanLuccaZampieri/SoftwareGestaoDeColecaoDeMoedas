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
@Table(name = "TB_Pais")
public class PaisEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pais")
	private Long id;
	@Column(nullable = false,
			length = 100,
			unique = true)
	private String nome;
	
	@OneToMany(mappedBy = "pais", cascade = CascadeType.ALL)
	private List<MoedaEntity> moedas;
}
