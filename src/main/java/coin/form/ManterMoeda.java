package coin.form;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import coin.entity.BordaEntity;
import coin.entity.ColecaoMoedaEntity;
import coin.entity.MaterialEntity;
import coin.entity.MoedaEntity;
import coin.entity.PaisEntity;
import coin.service.BordaService;
import coin.service.ColecaoMoedaService;
import coin.service.MaterialService;
import coin.service.MoedaService;
import coin.service.PaisService;
import coin.util.ConfirmacaoUtil;
import coin.util.MascaraUtil;

public class ManterMoeda extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JFormattedTextField txtValor;
	private JFormattedTextField txtAno;
	private JFormattedTextField txtCod;
	private JFormattedTextField txtEspessura;
	private JFormattedTextField txtPeso;
	private JFormattedTextField txtDiametro;
	private String nomeMoeda;
	private String codMoeda;
	private Float valorMoeda;
	private Float diametroMoeda;
	private Float espessuraMoeda;
	private PaisEntity paisMoeda;
	private Short anoMoeda;
	private Float pesoMoeda;
	private Long idMoeda;
	private JScrollPane scrollPane;
	private JTable table;
	private List<BordaEntity> bordaMoeda;
	private List<MaterialEntity> materialMoeda;
	private static PaisService paisService = new PaisService();
	private static BordaService bordaService = new BordaService();
	private static MaterialService materialService = new MaterialService();
	private List<PaisEntity> paises = paisService.listar();
	private List<String> bordas = bordaService.listarNomes();
	private List<String> materiais = materialService.listarNomes();
	private JComboBox<String> materialCombo = new JComboBox<String>();
	private JComboBox<String> bordaCombo = new JComboBox<String>();
	private JComboBox<String> paisCombo = new JComboBox<String>();

	/**
	 * Launch the application.
	 */

	private boolean validaDados() {
		nomeMoeda = txtNome.getText().toUpperCase().trim();
		codMoeda = txtCod.getText().toUpperCase().replace(" ", "");
		try {
			anoMoeda = Short.parseShort(txtAno.getText().trim());
		} catch (Exception e) {
			if (anoMoeda == null) {
				JOptionPane.showMessageDialog(null, "Por favor, informe o ano");
				return false;
			}
		}
		if (nomeMoeda.length() > 300) {
			JOptionPane.showMessageDialog(null, "Por favor, adicione um nome menor");
			return false;
		}
		try {
			valorMoeda = Float.parseFloat(txtValor.getText().replace(" ", "").replace(",", "."));
			if (valorMoeda < 0) {
				JOptionPane.showMessageDialog(null, "Por favor, informe um valor maior que zero");
				return false;
			}
		} catch (Exception e) {
			if (valorMoeda == null) {
				JOptionPane.showMessageDialog(null, "Por favor, adicione o valor");
				return false;
			} else {
				JOptionPane.showMessageDialog(null, "Valor Invalido no valor");
				return false;
			}
		}

		try {
			espessuraMoeda = Float.parseFloat(txtEspessura.getText().replace(" ", "").replace(",", "."));
			if (espessuraMoeda < 0) {
				JOptionPane.showMessageDialog(null, "Por favor, informe uma espessura maior que zero");
				return false;
			}
		} catch (Exception e) {
			if (espessuraMoeda == null) {
				JOptionPane.showMessageDialog(null, "Por favor, adicione um valor na espessura");
				return false;
			} else {
				JOptionPane.showMessageDialog(null, "Valor Invalido na espessura");
				return false;
			}
		}
		try {
			diametroMoeda = Float.parseFloat(txtDiametro.getText().replace(" ", "").replace(",", "."));
			if (diametroMoeda < 0) {
				JOptionPane.showMessageDialog(null, "Por favor, informe um diâmetro maior que zero");
				return false;
			}
		} catch (Exception e) {
			if (diametroMoeda == null) {
				JOptionPane.showMessageDialog(null, "Por favor, adicione o diâmetro");
				return false;
			} else {
				JOptionPane.showMessageDialog(null, "Valor Invalido na diâmetro");
				return false;
			}
		}
		try {
			pesoMoeda = Float.parseFloat(txtPeso.getText().replace(" ", "").replace(",", "."));
			if (pesoMoeda < 0) {
				JOptionPane.showMessageDialog(null, "Por favor, informe um peso maior que zero");
				return false;
			}
		} catch (Exception e) {
			if (pesoMoeda == null) {
				JOptionPane.showMessageDialog(null, "Por favor, adicione o peso");
				return false;
			} else {
				JOptionPane.showMessageDialog(null, "Valor Invalido no peso");
				return false;
			}
		}

		materialMoeda = new ArrayList<>();
		for (int i = 0; i <= materialCombo.getModel().getSize(); i++) {
			String item = (String) materialCombo.getModel().getElementAt(i);
			if (item != null) {
				MaterialEntity m = materialService.perquisaNome(item);
				materialMoeda.add(m);
			}
		}
		bordaMoeda = new ArrayList<>();
		for (int i = 0; i <= bordaCombo.getModel().getSize(); i++) {
			try {
				String item = (String) bordaCombo.getModel().getElementAt(i);
				if (item != null) {
					BordaEntity b = bordaService.pesquisaNome(item);
					bordaMoeda.add(b);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Algo deu errado na inserção da borda");
				return false;
			}
		}
		try {
			paisMoeda = paisService.pesquisaNome((String) paisCombo.getSelectedItem());
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Algo deu errado na inserção do país");
			return false;
		}
		MoedaEntity moeda = new MoedaEntity();
		moeda.setNome(nomeMoeda);
		moeda.setCod(codMoeda);
		MoedaEntity moedaEntity = new MoedaEntity();
		byte verificadorNome = 0;
		byte verificadorCod = 0;
		verificadorNome = 0;
		verificadorCod = 0;
		moedaEntity = null;
		if (idMoeda != null) {
			moedaEntity = moedaService.pesquisaId(idMoeda);
			if (!moedaEntity.getNome().equals(moeda.getNome())) {
				verificadorNome = 1;
			}
			if (!moedaEntity.getCod().equals(moeda.getCod())) {
				verificadorCod = 2;
			}
		}
		if (paisMoeda == null) {
			JOptionPane.showMessageDialog(null, "Por favor, escolha um pais");
			return false;
		}
		if (materialMoeda.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Por favor, escolha um material");
			return false;
		}
		if (bordaMoeda.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Por favor, escolha uma borda");
			return false;
		}
		if (nomeMoeda.isBlank()) {
			JOptionPane.showMessageDialog(null, "Por favor, informe o nome!");
			return false;
		}
		if (nomeMoeda.length() < 2) {
			JOptionPane.showMessageDialog(null, "Por favor, informe um nome maior!");
			return false;
		}
		if (codMoeda.isBlank()) {
			JOptionPane.showMessageDialog(null, "Por favor, informe o Codigo!");
			return false;
		}
		if (!moedaService.validaNome(moeda)) {
			if (idMoeda == null) {
				JOptionPane.showMessageDialog(null, "O nome do moeda já existe, por favor insira outro!");
				return false;
			}
			if (idMoeda != null && verificadorNome == 1) {
				JOptionPane.showMessageDialog(null, "O nome do moeda já existe, por favor insira outro!");
				return false;
			}
		}
		if (!moedaService.validaCod(moeda)) {
			if (idMoeda == null) {
				JOptionPane.showMessageDialog(null, "O Codigo do moeda já existe, por favor insira outro!");
				return false;
			}
			if (idMoeda != null && verificadorCod == 2) {
				JOptionPane.showMessageDialog(null, "O Codigo do moeda já existe, por favor insira outro!");
				return false;
			}
		}
		moedaEntity = null;
		return true;
	}

	MoedaService moedaService = new MoedaService();

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManterMoeda frame = new ManterMoeda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ManterMoeda() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1070, 604);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCadastrarMoeda = new JLabel("Manter Moeda");
		lblCadastrarMoeda.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblCadastrarMoeda.setBounds(401, 10, 180, 28);
		contentPane.add(lblCadastrarMoeda);

		final JComboBox<String> borda = new JComboBox<String>();
		borda.setFont(new Font("Tahoma", Font.PLAIN, 15));
		borda.setModel(new DefaultComboBoxModel<String>(new String[] { "Selecionar borda" }));
		borda.setBounds(429, 173, 236, 30);
		contentPane.add(borda);
		for (String elemento : bordas) {
			String p = elemento;
			borda.addItem(p);
		}

		final JComboBox<String> material = new JComboBox<String>();
		material.setFont(new Font("Tahoma", Font.PLAIN, 15));
		material.setModel(new DefaultComboBoxModel<String>(new String[] { "Selecionar material" }));
		for (String elemento : materiais) {
			String p = elemento;
			material.addItem(p);
		}
		material.setBounds(59, 173, 236, 30);
		contentPane.add(material);

		paisCombo = new JComboBox<String>();
		paisCombo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		paisCombo.setModel(new DefaultComboBoxModel<String>(new String[] { "Selecionar país" }));
		for (PaisEntity elemento : paises) {
			elemento = paisService.pesquisaId(elemento.getId());
			paisCombo.addItem(elemento.getNome());
		}
		paisCombo.setBounds(429, 130, 236, 30);
		contentPane.add(paisCombo);

		materialCombo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		materialCombo.setBounds(59, 231, 236, 30);
		contentPane.add(materialCombo);

		bordaCombo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		bordaCombo.setBounds(429, 231, 236, 30);
		contentPane.add(bordaCombo);

		JButton btnSair = new JButton("Sair");
		btnSair.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnSair.setBounds(10, 530, 110, 37);
		contentPane.add(btnSair);
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManterMoeda.this.dispose();
			}
		});

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnVoltar.setBounds(413, 530, 110, 37);
		contentPane.add(btnVoltar);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManterMoeda.this.dispose();
				Cads.main(null);
			}
		});

		txtNome = new JTextField();
		txtNome.setBounds(59, 73, 236, 30);
		contentPane.add(txtNome);
		txtNome.setColumns(10);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNome.setBounds(59, 48, 147, 21);
		contentPane.add(lblNome);

		JLabel lblValor = new JLabel("Valor");
		lblValor.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblValor.setBounds(429, 48, 147, 21);
		contentPane.add(lblValor);

		JLabel lblAno = new JLabel("Ano");
		lblAno.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblAno.setBounds(767, 210, 48, 21);
		contentPane.add(lblAno);

		txtValor = new JFormattedTextField(MascaraUtil.getMascaraValor());
		txtValor.setColumns(10);
		txtValor.setBounds(429, 73, 236, 30);
		contentPane.add(txtValor);

		txtAno = new JFormattedTextField(MascaraUtil.getMascaraAno());
		txtAno.setColumns(10);
		txtAno.setBounds(767, 231, 236, 30);
		contentPane.add(txtAno);

		JLabel lblCdigo = new JLabel("Código");
		lblCdigo.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCdigo.setBounds(767, 48, 147, 21);
		contentPane.add(lblCdigo);

		txtCod = new JFormattedTextField(MascaraUtil.getMascaraNome());
		txtCod.setColumns(10);
		txtCod.setBounds(767, 70, 236, 30);
		contentPane.add(txtCod);

		txtEspessura = new JFormattedTextField(MascaraUtil.getMascaraValor());
		txtEspessura.setColumns(10);
		txtEspessura.setBounds(59, 133, 236, 30);
		contentPane.add(txtEspessura);

		JLabel lblEspessura = new JLabel("Espessura(mm)");
		lblEspessura.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblEspessura.setBounds(59, 113, 147, 21);
		contentPane.add(lblEspessura);

		txtPeso = new JFormattedTextField(MascaraUtil.getMascaraValor());
		txtPeso.setColumns(10);
		txtPeso.setBounds(767, 123, 236, 30);
		contentPane.add(txtPeso);

		JLabel lblPeso = new JLabel("Peso(g)");
		lblPeso.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblPeso.setBounds(765, 97, 64, 21);
		contentPane.add(lblPeso);

		JButton btnAddMaterial = new JButton("+");
		btnAddMaterial.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAddMaterial.setBounds(10, 174, 49, 29);
		contentPane.add(btnAddMaterial);
		btnAddMaterial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				byte ver = 0;
				for (int i = 0; i <= materialCombo.getItemCount(); i++) {
					if (material.getSelectedItem().equals(materialCombo.getItemAt(i))) {
						ver = 1;
					}
					if (material.getSelectedItem().equals("Selecionar material")) {
						ver = 2;
					}
				}
				if (ver == 0) {
					materialCombo.addItem((String) material.getSelectedItem());
					material.setSelectedItem("Selecionar material");
				}
				if (ver == 1) {
					JOptionPane.showMessageDialog(null,
							"O material " + material.getSelectedItem() + " ja foi adicionado!");
				}
				if (ver == 2) {
					JOptionPane.showMessageDialog(null, "Por favor selecione um material");
				}
			}
		});

		JButton btnAddBorda = new JButton("+");
		btnAddBorda.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAddBorda.setBounds(382, 173, 48, 30);
		contentPane.add(btnAddBorda);
		btnAddBorda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				byte ver = 0;
				for (int i = 0; i <= bordaCombo.getItemCount(); i++) {
					if (borda.getSelectedItem().equals(bordaCombo.getItemAt(i))) {
						ver = 1;
					}
					if (borda.getSelectedItem().equals("Selecionar borda")) {
						ver = 2;
					}
				}
				if (ver == 0) {
					bordaCombo.addItem((String) borda.getSelectedItem());
					borda.setSelectedItem("Selecionar borda");
				}
				if (ver == 1) {
					JOptionPane.showMessageDialog(null, "A borda " + borda.getSelectedItem() + " já foi adicionada!");

				}
				if (ver == 2) {
					JOptionPane.showMessageDialog(null, "Por favor selecione uma borda");
				}
			}
		});

		JButton btnRmvBorda = new JButton("-");
		btnRmvBorda.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnRmvBorda.setBounds(382, 231, 48, 30);
		contentPane.add(btnRmvBorda);
		btnRmvBorda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bordaCombo.removeItem(bordaCombo.getSelectedItem());
			}
		});

		JButton btnRmvMaterial = new JButton("-");
		btnRmvMaterial.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnRmvMaterial.setBounds(10, 231, 49, 30);
		contentPane.add(btnRmvMaterial);
		btnRmvMaterial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				materialCombo.removeItem(materialCombo.getSelectedItem());
			}
		});

		final DefaultTableModel modeloMoedas = new DefaultTableModel() {
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				return String.class;
			}

			public boolean isCellEditable(int row, int column) {
				return false; // Torna todas as células não editáveis
			}
		};
		List<MoedaEntity> moedas = null;
		moedas = moedaService.listar();

		modeloMoedas.addColumn("Id");
		modeloMoedas.addColumn("Nome");
		modeloMoedas.addColumn("Valor");
		modeloMoedas.addColumn("Cod");
		modeloMoedas.addColumn("Espessura(mm)");
		modeloMoedas.addColumn("Diâmetro(mm)");
		modeloMoedas.addColumn("Ano");
		modeloMoedas.addColumn("Peso(g)");
		modeloMoedas.addColumn("Pais");
		modeloMoedas.addColumn("Material");
		modeloMoedas.addColumn("Borda");

		for (MoedaEntity moeda : moedas) {
			StringBuilder materiais = new StringBuilder();
			StringBuilder bordas = new StringBuilder();
			for (MaterialEntity material1 : moeda.getMateriais()) {
				material1 = materialService.pesquisaId(material1.getId());
				materiais.append(material1.getNome()).append(", ");
			}
			String materiaisConcatenados = materiais.toString();
			if (materiaisConcatenados.length() > 0) {
				materiaisConcatenados = materiaisConcatenados.substring(0, materiaisConcatenados.length() - 2);
			}
			for (BordaEntity borda1 : moeda.getBordas()) {
				borda1 = bordaService.pesquisaId(borda1.getId());
				bordas.append(borda1.getNome()).append(", ");
			}
			String bordasConcatenados = bordas.toString();
			if (bordasConcatenados.length() > 0) {
				bordasConcatenados = bordasConcatenados.substring(0, bordasConcatenados.length() - 2);
			}
			Object[] rowData = { moeda.getId(), moeda.getNome(), moeda.getValor(), moeda.getCod(), moeda.getEspessura(),
					moeda.getDiametro(), moeda.getAno(), moeda.getPeso(), moeda.getPais().getNome(), materiaisConcatenados, bordasConcatenados};
			modeloMoedas.addRow(rowData);
		}

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 271, 863, 255);
		contentPane.add(scrollPane);

		table = new JTable(modeloMoedas);
		scrollPane.setViewportView(table);

		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(1).setPreferredWidth(125);
		table.getColumnModel().getColumn(2).setPreferredWidth(60);
		table.getColumnModel().getColumn(3).setPreferredWidth(50);
		table.getColumnModel().getColumn(4).setPreferredWidth(90);
		table.getColumnModel().getColumn(5).setPreferredWidth(85);
		table.getColumnModel().getColumn(6).setPreferredWidth(40);
		table.getColumnModel().getColumn(7).setPreferredWidth(50);
		table.getColumnModel().getColumn(8).setPreferredWidth(105);
		table.getColumnModel().getColumn(9).setPreferredWidth(170);
		table.getColumnModel().getColumn(10).setPreferredWidth(150);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnExcluir.setBounds(893, 331, 110, 37);
		contentPane.add(btnExcluir);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() >= 0) {
					Long valorColuna1 = (Long) table.getValueAt(table.getSelectedRow(), 0);
					MoedaEntity moedaEntity = new MoedaEntity();
					moedaEntity = moedaService.pesquisaId(valorColuna1);
					ColecaoMoedaService colecaoMoedaService = new ColecaoMoedaService();
					List<ColecaoMoedaEntity> colecaoMoeda = null;
					colecaoMoeda = colecaoMoedaService.pesquisaPelaMoeda(moedaEntity);
					if (colecaoMoeda == null) {
						boolean confirmacao = ConfirmacaoUtil.mostrarPopUpConfirmacao();
						if (confirmacao == false) {
							return;
						}
						moedaService.remover(valorColuna1);
						modeloMoedas.removeRow(table.getSelectedRow());
						table.revalidate();
						table.repaint();
					} else {
						boolean confirmacaoRmv = ConfirmacaoUtil.mostrarPopUpConfirmacaoRmvMoeda();
						if (confirmacaoRmv == false) {
							return;
						} else {
							for (ColecaoMoedaEntity colecaoRmv : colecaoMoeda) {
								moedaService.remover(valorColuna1);
								modeloMoedas.removeRow(table.getSelectedRow());
								table.revalidate();
								table.repaint();
								colecaoMoedaService.remover(colecaoRmv);

							}
						}
					}

				} else {
					JOptionPane.showMessageDialog(null, "Por favor, selecione uma opção");
				}
			}
		});

		JButton btnEditar = new JButton("Editar");
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnEditar.setBounds(893, 387, 110, 37);
		contentPane.add(btnEditar);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {
					Long celula1 = (Long) table.getValueAt(table.getSelectedRow(), 0);
					MoedaEntity moeda = moedaService.pesquisaId(celula1);
					List<MaterialEntity> materiais = moeda.getMateriais();
					List<BordaEntity> bordas = moeda.getBordas();
					txtNome.setText(moeda.getNome().toString());
					txtValor.setText(moeda.getValor().toString());
					txtCod.setText(moeda.getCod().toString());
					txtEspessura.setText(moeda.getEspessura().toString());
					txtDiametro.setText(moeda.getDiametro().toString());
					txtAno.setText(moeda.getAno().toString());
					txtPeso.setText(moeda.getPeso().toString());
					PaisEntity paisEntity = paisService.pesquisaId(moeda.getPais().getId());
					paisCombo.setSelectedItem(paisEntity.getNome());
					bordaCombo.removeAllItems();
					for (BordaEntity bordaEntity : bordas) {
						bordaEntity = bordaService.pesquisaId(bordaEntity.getId());
						borda.setSelectedItem(bordaEntity.getNome());
						bordaCombo.addItem((String) borda.getSelectedItem());
					}
					materialCombo.removeAllItems();
					for (MaterialEntity materialEntity : materiais) {
						materialEntity = materialService.pesquisaId(materialEntity.getId());
						material.setSelectedItem(materialEntity.getNome());
						materialCombo.addItem((String) material.getSelectedItem());
					}
					borda.setSelectedItem("Selecionar borda");
					material.setSelectedItem("Selecionar material");
					idMoeda = moeda.getId();
				} else {
					JOptionPane.showMessageDialog(null, "Por favor, selecione um moeda na tabela para editar!");
				}
			}
		});

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnLimpar.setBounds(893, 270, 110, 37);
		contentPane.add(btnLimpar);
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNome.setText(null);
				txtCod.setText(null);
				txtAno.setText(null);
				txtEspessura.setText(null);
				txtValor.setText(null);
				txtPeso.setText(null);
				txtDiametro.setText(null);
				materialCombo.removeAllItems();
				bordaCombo.removeAllItems();
				paisCombo.setSelectedItem("Selecionar país");
				borda.setSelectedItem("Selecionar borda");
				material.setSelectedItem("Selecionar material");
				idMoeda = null;
			}
		});

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnSalvar.setBounds(893, 444, 110, 37);
		contentPane.add(btnSalvar);

		JLabel lblMateriaisSelecionados = new JLabel("Materiais Selecionados");
		lblMateriaisSelecionados.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblMateriaisSelecionados.setBounds(59, 210, 180, 21);
		contentPane.add(lblMateriaisSelecionados);

		JLabel lblBordasSelecionadas = new JLabel("Bordas Selecionadas");
		lblBordasSelecionadas.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblBordasSelecionadas.setBounds(429, 212, 168, 21);
		contentPane.add(lblBordasSelecionadas);

		JLabel lblDiametro = new JLabel("Diâmetro(mm)");
		lblDiametro.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblDiametro.setBounds(767, 154, 152, 21);
		contentPane.add(lblDiametro);

		txtDiametro = new JFormattedTextField();
		txtDiametro.setColumns(10);
		txtDiametro.setBounds(767, 173, 236, 30);
		contentPane.add(txtDiametro);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validaDados()) {
					boolean confirmacao = ConfirmacaoUtil.mostrarPopUpConfirmacao();
					if(confirmacao == false) {
						return;
					}
					MoedaEntity moeda = new MoedaEntity();
					moeda.setBordas(bordaMoeda);
					moeda.setMateriais(materialMoeda);
					moeda.setAno(anoMoeda);
					moeda.setNome(nomeMoeda);
					moeda.setEspessura(espessuraMoeda);
					moeda.setPeso(pesoMoeda);
					moeda.setCod(codMoeda);
					moeda.setValor(valorMoeda);
					moeda.setDiametro(diametroMoeda);
					moeda.setPais(paisMoeda);
					if (idMoeda != null) {
						moeda.setId(idMoeda);
					}
					moedaService.salvar(moeda);
					ManterMoeda.this.dispose();
					Cads.main(null);

				}
			}
		});

	}
}