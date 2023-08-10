package coin.form;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import coin.entity.BordaEntity;
import coin.entity.ColecaoEntity;
import coin.entity.ColecaoMoedaEntity;
import coin.entity.ColecaoMoedaId;
import coin.entity.MaterialEntity;
import coin.entity.MoedaEntity;
import coin.entity.PaisEntity;
import coin.entity.PessoaEntity;
import coin.service.BordaService;
import coin.service.ColecaoMoedaService;
import coin.service.ColecaoService;
import coin.service.MaterialService;
import coin.service.MoedaService;
import coin.service.PaisService;
import coin.service.PessoaService;
import coin.util.ConfirmacaoUtil;
import coin.util.DateFormater;

public class Colecao extends JFrame {

	private JPanel contentPane;
	private JLabel lblColao;
	private JTable table;
	private final Long PESSOA_ID = LoginUser.id;
	private static PaisService paisService = new PaisService();
	private static BordaService bordaService = new BordaService();
	private static MaterialService materialService = new MaterialService();
	private ColecaoMoedaService colecaoMoedaService = new ColecaoMoedaService();
	private JButton btnVerColecao;
	private JButton btnAdicionarMoeda;
	private JButton btnEditar;
	private JButton btnSalvarMoeda;
	private JComboBox<Object> colecaoSelecionada;
	private DefaultTableModel modeloMoedas;
	public DefaultTableModel modeloColecao;
	private JFormattedTextField txtQuantidade;
	private JFormattedTextField txtValor;
	private JRadioButton rdbtnVenda;
	private JRadioButton rdbtnColecao;
	private Integer quantidadeColecaoMoeda;
	private Float valorColecaoMoeda;
	private Character tipoColecaoMoeda;

	/**
	 * Launch the application.
	 */
	private static DateFormater dateFormater = new DateFormater();
	private MoedaService moedaService = new MoedaService();
	private ColecaoService colecaoService = new ColecaoService();
	private PessoaService pessoaService = new PessoaService();
	private JScrollPane scrollPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					Colecao frame = new Colecao();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public boolean validaDados() {
		tipoColecaoMoeda = null;
		quantidadeColecaoMoeda = null;
		valorColecaoMoeda = null;
		try {
			quantidadeColecaoMoeda = Integer.parseInt(txtQuantidade.getText().replace(" ", ""));
			if (quantidadeColecaoMoeda < 0) {
				JOptionPane.showMessageDialog(null, "Por favor, adicione uma quantidade válida");
				return false;
			}
			if (quantidadeColecaoMoeda > 10000) {
				JOptionPane.showMessageDialog(null, "A quantidade é muito alta");
				return false;

			}
		} catch (Exception e) {
			if (quantidadeColecaoMoeda == null) {
				JOptionPane.showMessageDialog(null, "Por favor, adicione a quantidade");
				return false;
			} else {
				JOptionPane.showMessageDialog(null, "Quantidade inválida");
				return false;
			}
		}

		try {
			valorColecaoMoeda = Float.parseFloat(txtValor.getText().replace(" ", "").replace(",", "."));
			if (valorColecaoMoeda < 0) {
				JOptionPane.showMessageDialog(null, "Por favor, adicione um valor válido");
				return false;
			}
			if (txtValor.getText().trim().length() > 11) {
				JOptionPane.showMessageDialog(null, "Valor muito alto");
				return false;
			}
		} catch (Exception e) {
			if (valorColecaoMoeda == null) {
				JOptionPane.showMessageDialog(null, "Por favor, adicione o valor");
				return false;
			} else {
				JOptionPane.showMessageDialog(null, "Valor inválido");
				return false;
			}
		}

		if (!rdbtnColecao.isSelected() && !rdbtnVenda.isSelected()) {
			JOptionPane.showMessageDialog(null, "Selecione um tipo para a moeda");
			return false;
		}
		if (rdbtnColecao.isSelected()) {
			tipoColecaoMoeda = 'c';
		}
		if (rdbtnVenda.isSelected()) {
			tipoColecaoMoeda = 'v';
		}
		if (colecaoSelecionada.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(null, "Por favor, escolha/cadastre uma coleção");
			return false;
		}
		if (table.getSelectedRow() <= -1) {
			JOptionPane.showMessageDialog(null, "Por Favor, selecione uma moeda");
			return false;
		}

		return true;
	}

	public void colecaoPessoa() {
		try {
			PessoaEntity pessoaEntity = new PessoaEntity();
			pessoaEntity = pessoaService.pesquisaId(PESSOA_ID);
			System.out.println(1);
			if (colecaoSelecionada.getSelectedItem() != null) {
				ColecaoEntity colecaoEntity = colecaoService
						.pesquisaColecaoPessoa(colecaoSelecionada.getSelectedItem().toString(), pessoaEntity);
				System.out.println(2);
				List<ColecaoMoedaEntity> colecaoMoedaEntity = colecaoMoedaService.listarMoedas(colecaoEntity.getId());
				System.out.println(3);
				for (ColecaoMoedaEntity colecaoMoeda : colecaoMoedaEntity) {
					MoedaEntity moedaEntity = new MoedaEntity();
					moedaEntity = moedaService.pesquisaId(colecaoMoeda.getId().getIdMoeda().getId());
					StringBuilder materiais = new StringBuilder();
					StringBuilder bordas = new StringBuilder();
					for (MaterialEntity material1 : moedaEntity.getMateriais()) {
						material1 = materialService.pesquisaId(material1.getId());
						materiais.append(material1.getNome()).append(", ");
					}
					String materiaisConcatenados = materiais.toString();
					if (materiaisConcatenados.length() > 0) {
						materiaisConcatenados = materiaisConcatenados.substring(0, materiaisConcatenados.length() - 2);
					}
					for (BordaEntity borda1 : moedaEntity.getBordas()) {
						borda1 = bordaService.pesquisaId(borda1.getId());
						bordas.append(borda1.getNome()).append(", ");
					}
					String bordasConcatenados = bordas.toString();
					if (bordasConcatenados.length() > 0) {
						bordasConcatenados = bordasConcatenados.substring(0, bordasConcatenados.length() - 2);
					}
					String tipoMoeda;
					if (colecaoMoeda.getTipo() == 'v') {
						tipoMoeda = "Venda";
					} else {
						tipoMoeda = "Coleção";
					}
					if (colecaoMoeda.getQuantidade() < 1) {
						colecaoMoedaService.remover(colecaoMoeda);
						return;
					}
					String data = dateFormater.formatarDataBR(colecaoMoeda.getData());
					Object[] rowData = { colecaoMoeda.getId().getIdMoeda().getId(), moedaEntity.getCod(),
							moedaEntity.getNome(), materiaisConcatenados, bordasConcatenados,
							colecaoMoeda.getValorUnitario(), data, colecaoMoeda.getQuantidade(), tipoMoeda };
					modeloColecao.addRow(rowData);

				}
				table = new JTable(modeloColecao);
				scrollPane.setViewportView(table);
			}
		} catch (Exception e) {
			System.out.println("ERRO!!!");
			System.out.println(e);
		}
	}

	/**
	 * Create the frame.
	 */
	public Colecao() {
		modeloColecao = new DefaultTableModel() {
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				return String.class;
			}

			public boolean isCellEditable(int row, int column) {
				return false; // Torna todas as células não editáveis
			}
		};

		PessoaEntity pessoaEntity = new PessoaEntity();
		pessoaEntity = pessoaService.pesquisaId(PESSOA_ID);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1070, 604);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblColao = new JLabel("Coleção");
		lblColao.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblColao.setBounds(445, 0, 87, 28);
		contentPane.add(lblColao);

		JButton btnSair = new JButton("Sair");
		btnSair.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnSair.setBounds(10, 528, 110, 37);
		contentPane.add(btnSair);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Colecao.this.dispose();
				Menu.main(null);
			}
		});
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnVoltar.setBounds(480, 528, 110, 37);
		contentPane.add(btnVoltar);
		// MODELO COLECAO

		modeloColecao.addColumn("Id");
		modeloColecao.addColumn("Cod");
		modeloColecao.addColumn("Titulo");
		modeloColecao.addColumn("Material");
		modeloColecao.addColumn("Borda");
		modeloColecao.addColumn("Valor(R$)");
		modeloColecao.addColumn("Data");
		modeloColecao.addColumn("Quantidade");
		modeloColecao.addColumn("Tipo");

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 69, 837, 449);
		contentPane.add(scrollPane);

		table = new JTable(modeloColecao);
		scrollPane.setViewportView(table);
		table.setEnabled(false);

		final JButton btnExcluir = new JButton("Excluir Moeda");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() >= 0) {
					boolean confirmacao = ConfirmacaoUtil.mostrarPopUpConfirmacao();
					if (confirmacao == false) {
						return;
					}
					Long idMoeda = (Long) table.getValueAt(table.getSelectedRow(), 0);
					MoedaEntity moedaEntity = new MoedaEntity();
					moedaEntity = moedaService.pesquisaId(idMoeda);
					PessoaEntity pessoaEntity = new PessoaEntity();
					pessoaEntity = pessoaService.pesquisaId(PESSOA_ID);
					ColecaoEntity colecaoEntity = colecaoService
							.pesquisaColecaoPessoa(colecaoSelecionada.getSelectedItem().toString(), pessoaEntity);
					ColecaoMoedaId colecaoMoedaId = new ColecaoMoedaId();
					colecaoMoedaId.setIdColecao(colecaoEntity);
					colecaoMoedaId.setIdMoeda(moedaEntity);
					ColecaoMoedaEntity colecaoMoeda = new ColecaoMoedaEntity();
					colecaoMoeda = colecaoMoedaService.pesquisaPeloId(colecaoMoedaId);
					// Remova a linha do modelo de dados
					modeloColecao.removeRow(table.getSelectedRow());

					// Atualize a exibição da JTable
					table.revalidate();
					table.repaint();
					colecaoMoedaService.remover(colecaoMoeda);
				} else {
					JOptionPane.showMessageDialog(null, "Por favor, selecione uma celula da tabela");
				}
			}
		});
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnExcluir.setBounds(857, 156, 168, 37);
		contentPane.add(btnExcluir);

		JButton btnAdicionarColecao = new JButton("Adicionar Coleção");
		btnAdicionarColecao.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnAdicionarColecao.setBounds(23, 27, 168, 37);
		contentPane.add(btnAdicionarColecao);
		btnAdicionarColecao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadColecao.main(null);
				Colecao.this.dispose();
			}
		});
		colecaoSelecionada = new JComboBox<Object>();
		colecaoSelecionada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modeloColecao.setRowCount(0);
				colecaoPessoa();
			}
		});
		colecaoSelecionada.setBounds(857, 31, 168, 37);
		contentPane.add(colecaoSelecionada);
		List<ColecaoEntity> colecoesPessoa = colecaoService.pesquisaPessoa(pessoaEntity);
		try {
			if (colecoesPessoa != null) {
				for (ColecaoEntity elemento : colecoesPessoa) {
					String p = elemento.getNome();
					colecaoSelecionada.addItem(p);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		btnAdicionarMoeda = new JButton("Adicionar Moeda");
		btnAdicionarMoeda.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnAdicionarMoeda.setBounds(206, 27, 168, 37);
		contentPane.add(btnAdicionarMoeda);

		btnAdicionarMoeda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modeloMoedas = new DefaultTableModel() {
					@Override
					public Class<?> getColumnClass(int columnIndex) {
						return String.class;
					}

					public boolean isCellEditable(int row, int column) {
						return false; // Torna todas as células não editáveis
					}
				};
				modeloMoedas.setRowCount(0);
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
				modeloMoedas.addColumn("Materiais");
				modeloMoedas.addColumn("Bordas");

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
					PaisEntity pais = paisService.pesquisaId(moeda.getPais().getId());
					Object[] rowData = { moeda.getId(), moeda.getNome(), moeda.getValor(), moeda.getCod(),
							moeda.getEspessura(), moeda.getDiametro(), moeda.getAno(), moeda.getPeso(), pais.getNome(),
							materiaisConcatenados, bordasConcatenados };
					modeloMoedas.addRow(rowData);
				}
				table = new JTable(modeloMoedas);
				scrollPane.setViewportView(table);
				btnAdicionarMoeda.setVisible(false);
				btnEditar.setVisible(false);
				btnExcluir.setVisible(false);
				btnVerColecao.setVisible(true);
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
			}
		});

		txtValor = new JFormattedTextField((AbstractFormatter) null);
		txtValor.setColumns(10);
		txtValor.setBounds(861, 267, 168, 30);
		contentPane.add(txtValor);

		rdbtnVenda = new JRadioButton("Venda");
		rdbtnVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnVenda.isSelected()) {
					rdbtnColecao.setSelected(false);
				}
			}
		});
		rdbtnVenda.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnVenda.setBounds(853, 407, 103, 21);
		contentPane.add(rdbtnVenda);

		btnEditar = new JButton("Editar Moeda");
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnEditar.setBounds(857, 203, 168, 37);
		contentPane.add(btnEditar);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() >= 0) {
					Long idMoeda = (Long) modeloColecao.getValueAt(table.getSelectedRow(), 0);
					MoedaEntity moedaEntity = new MoedaEntity();
					moedaEntity = moedaService.pesquisaId(idMoeda);
					PessoaEntity pessoaEntity = new PessoaEntity();
					pessoaEntity = pessoaService.pesquisaId(PESSOA_ID);
					ColecaoEntity colecaoEntity = colecaoService
							.pesquisaColecaoPessoa(colecaoSelecionada.getSelectedItem().toString(), pessoaEntity);
					ColecaoMoedaId colecaoMoedaId = new ColecaoMoedaId();
					colecaoMoedaId.setIdColecao(colecaoEntity);
					colecaoMoedaId.setIdMoeda(moedaEntity);
					ColecaoMoedaEntity colecaoMoeda = new ColecaoMoedaEntity();
					colecaoMoeda = colecaoMoedaService.pesquisaPeloId(colecaoMoedaId);
					txtQuantidade.setText(colecaoMoeda.getQuantidade().toString());
					txtValor.setText(colecaoMoeda.getValorUnitario().toString());
					if (colecaoMoeda.getTipo().equals('v')) {
						rdbtnVenda.setSelected(true);
						rdbtnColecao.setSelected(false);
					}
					if (colecaoMoeda.getTipo().equals('c')) {
						rdbtnVenda.setSelected(false);
						rdbtnColecao.setSelected(true);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Por favor, selecione uma celula da tabela");
				}
			}
		});

		btnSalvarMoeda = new JButton("Salvar Moeda");
		btnSalvarMoeda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validaDados()) {
					boolean confirmacao = ConfirmacaoUtil.mostrarPopUpConfirmacao();
					if (confirmacao == false) {
						return;
					}
					PessoaEntity pessoaEntity = new PessoaEntity();
					pessoaEntity = pessoaService.pesquisaId(PESSOA_ID);
					Long celula1 = (Long) table.getValueAt(table.getSelectedRow(), 0);
					MoedaEntity moeda = new MoedaEntity();
					moeda = moedaService.pesquisaId(celula1);
					ColecaoMoedaId id = new ColecaoMoedaId();
					List<ColecaoEntity> colecaoEntity = null;
					ColecaoEntity colecaoEntity1 = new ColecaoEntity();
					colecaoEntity = colecaoService.pesquisaPessoa(pessoaEntity);
					for (ColecaoEntity colecaoPessoa : colecaoEntity) {
						if (colecaoPessoa.getNome().equals(colecaoSelecionada.getSelectedItem().toString())) {
							colecaoEntity1 = colecaoPessoa;
						}
					}
					id.setIdMoeda(moeda);
					id.setIdColecao(colecaoEntity1);
					ColecaoMoedaEntity colecaoMoedaEntity = new ColecaoMoedaEntity();
					colecaoMoedaEntity.setId(id);
					colecaoMoedaEntity.setQuantidade(quantidadeColecaoMoeda);
					colecaoMoedaEntity.setTipo(tipoColecaoMoeda);
					colecaoMoedaEntity.setValorUnitario(valorColecaoMoeda);
					Calendar calendario = Calendar.getInstance();
					String data = dateFormater.formatarDataBR(calendario.getTime());
					colecaoMoedaEntity.setData(dateFormater.converterParaData(data));
					colecaoMoedaService.salvar(colecaoMoedaEntity, (byte) 2);
					btnAdicionarMoeda.setVisible(true);
					btnVerColecao.setVisible(false);
					btnEditar.setVisible(true);
					btnExcluir.setVisible(true);
					modeloColecao.setRowCount(0);
					colecaoPessoa();
				}
			}
		});
		btnSalvarMoeda.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnSalvarMoeda.setBounds(857, 469, 168, 37);
		contentPane.add(btnSalvarMoeda);

		JLabel lblSelecioneOTipo = new JLabel("Selecione o tipo:");
		lblSelecioneOTipo.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSelecioneOTipo.setBounds(853, 373, 203, 28);
		contentPane.add(lblSelecioneOTipo);

		JLabel lblColeoSelecionada = new JLabel("Coleção Selecionada");
		lblColeoSelecionada.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblColeoSelecionada.setBounds(857, 0, 168, 28);
		contentPane.add(lblColeoSelecionada);

		btnVerColecao = new JButton("Ver Colecao");
		btnVerColecao.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnVerColecao.setBounds(206, 27, 168, 37);
		contentPane.add(btnVerColecao);

		colecaoPessoa();

		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modeloColecao.setRowCount(0);
				colecaoPessoa();
			}
		});
		btnAtualizar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnAtualizar.setBounds(857, 69, 168, 37);
		contentPane.add(btnAtualizar);

		txtQuantidade = new JFormattedTextField();
		txtQuantidade.setColumns(10);
		txtQuantidade.setBounds(861, 333, 168, 30);
		contentPane.add(txtQuantidade);

		JLabel lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblQuantidade.setBounds(861, 307, 147, 21);
		contentPane.add(lblQuantidade);

		JLabel lblValor = new JLabel("Valor(R$)");
		lblValor.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblValor.setBounds(862, 248, 147, 21);
		contentPane.add(lblValor);

		rdbtnColecao = new JRadioButton("Coleção");
		rdbtnColecao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnColecao.isSelected()) {
					rdbtnVenda.setSelected(false);
					;
				}
			}
		});
		modeloColecao.setRowCount(0);
		colecaoPessoa();
		rdbtnColecao.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnColecao.setBounds(853, 432, 103, 21);
		contentPane.add(rdbtnColecao);

		btnVerColecao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table = new JTable(modeloColecao);
				scrollPane.setViewportView(table);
				btnVerColecao.setVisible(false);
				btnEditar.setVisible(true);
				btnExcluir.setVisible(true);
				btnAdicionarMoeda.setVisible(true);
				modeloColecao.setRowCount(0);
				colecaoPessoa();

			}
		});

		setVisible(true);
	}
}
