package coin.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_Colecao_Moeda")
public class ColecaoMoedaEntity {
	@EmbeddedId
	private ColecaoMoedaId id;
	@Column(name = "valorUnitario", nullable = false,unique = false)
	private Float valorUnitario;
	
	@Column(name = "tipo", nullable = false, unique = false)
	private Character tipo;
	
	@Column(nullable = false,
			unique = false)
	private Date data;
	
	@Column(nullable = false,
			unique = false)
	private Integer quantidade;
}
