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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_Moeda")
public class MoedaEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_moeda")
	private Long id;
	@Column(nullable = false,
			unique = true,
			length = 300)
	private String nome;
	
	@Column(nullable = false,
			unique = true,
			length = 50)
	private String cod;
	
	@Column(nullable = false,
			unique = false)
	private Short ano;
	
	@Column(nullable = false,
			unique = false)
	private Float valor;
	
	@Column(nullable = false,
			unique = false)
	private Float peso;
	
	@Column(nullable = false,
			unique = false)
	private Float espessura;
	
	@Column(nullable = false,
			unique = false)
	private Float diametro;
	
	@ManyToOne
	@JoinColumn(nullable = false, 
				name = "id_pais")
	private PaisEntity pais;
	
	@ManyToMany()
    @JoinTable(name = "tb_moeda_material",
        	   joinColumns = @JoinColumn(name = "id_moeda", nullable = false),
        	   inverseJoinColumns = @JoinColumn(name = "id_material", nullable = false))
	private List<MaterialEntity> materiais;
	
	@ManyToMany()
    @JoinTable(name = "tb_moeda_borda",
        	   joinColumns = @JoinColumn(name = "id_moeda", nullable = false),
        	   inverseJoinColumns = @JoinColumn(name = "id_borda", nullable = false))
	private List<BordaEntity> bordas;
	
	@OneToMany(mappedBy = "id.idMoeda", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ColecaoMoedaEntity> colecaoMoeda;
	
}
