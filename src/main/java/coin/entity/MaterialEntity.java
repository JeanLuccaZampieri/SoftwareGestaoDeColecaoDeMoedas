package coin.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_Material")
public class MaterialEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_material")
	private Long id;
	@Column(nullable = false,
			length = 50,
			unique = true)
	private String nome;
	
	@ManyToMany(mappedBy = "materiais",  cascade = CascadeType.ALL)
	private List<MoedaEntity> moedas;	
}

