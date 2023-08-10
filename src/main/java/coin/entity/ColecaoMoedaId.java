package coin.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.ToString;

@Data
@Embeddable
public class ColecaoMoedaId implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_colecao", nullable = false, referencedColumnName = "id_colecao")
	private ColecaoEntity idColecao;
	
	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_moeda", nullable = false, referencedColumnName = "id_moeda")
	private MoedaEntity idMoeda;
}
