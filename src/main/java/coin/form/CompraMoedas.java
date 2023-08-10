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
import coin.entity.PessoaEntity;
import coin.service.BordaService;
import coin.service.ColecaoMoedaService;
import coin.service.ColecaoService;
import coin.service.MaterialService;
import coin.service.MoedaService;
import coin.service.PessoaService;
import coin.util.ConfirmacaoUtil;
import coin.util.DateFormater;

public class CompraMoedas extends JFrame {

	private JPanel contentPane;
	private JButton btnComprarMoeda;
	private JLabel lblColeoSelecionada;
	private JTable table;
	public DefaultTableModel modeloColecao;
	private final Long PESSOA_ID = LoginUser.id;
	private JScrollPane scrollPane;
	private JComboBox<Object> colecaoCompradorSelecionada;
	private JFormattedTextField txtQuantidade;
	/**
	 * Launch the application.
	 */

	private static DateFormater dateFormater = new DateFormater();
	private static MoedaService moedaService = new MoedaService();
	private static ColecaoMoedaService colecaoMoedaService = new ColecaoMoedaService();
	private static ColecaoService colecaoService = new ColecaoService();
	private static PessoaService pessoaService = new PessoaService();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CompraMoedas frame = new CompraMoedas();
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
	public void tabelaColecao() {
		try {
			List<ColecaoMoedaEntity> colecaoMoedaEntity = colecaoMoedaService.listarMoedasVenda();
			for (ColecaoMoedaEntity colecaoMoeda : colecaoMoedaEntity) {
				if (colecaoMoeda.getQuantidade() > 0) {
					MoedaEntity moedaEntity = new MoedaEntity();
					ColecaoEntity colecao = colecaoService.pesquisaId(colecaoMoeda.getId().getIdColecao().getId());
					String nomeColecao = colecao.getNome();
					PessoaEntity pessoa = new PessoaEntity();
					pessoa = colecao.getId_pessoa();
					moedaEntity = moedaService.pesquisaId(colecaoMoeda.getId().getIdMoeda().getId());
					Object[] rowData = { colecaoMoeda.getId().getIdMoeda().getId(), moedaEntity.getNome(),
							moedaEntity.getCod(), colecaoMoeda.getValorUnitario(), colecaoMoeda.getQuantidade(),
							pessoa.getNome(), nomeColecao };
					modeloColecao.addRow(rowData);
				}
			}
		} catch (Exception e) {
		}
		table = new JTable(modeloColecao);
		scrollPane.setViewportView(table);
	}

	public boolean validaDados() {
		Integer quantidadeColecaoMoeda = null;
		int quantidadeTable = (Integer) table.getValueAt(table.getSelectedRow(), 4);
		PessoaEntity usuario = new PessoaEntity();
		usuario = pessoaService.pesquisaId(PESSOA_ID);
		try {
			quantidadeColecaoMoeda = Integer.parseInt(txtQuantidade.getText().replace(" ", ""));

			if (quantidadeColecaoMoeda < 1) {
				JOptionPane.showMessageDialog(null, "Por favor, adicione uma quantidade válida");
				return false;
			}
			if (quantidadeColecaoMoeda > quantidadeTable) {
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
			Float valorUnitario = (Float) table.getValueAt(table.getSelectedRow(), 3);
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Valor inválido");
			return false;
		}
		if (table.getSelectedRow() <= -1) {
			JOptionPane.showMessageDialog(null, "Por Favor, selecione uma moeda");
			return false;
		}
		if (colecaoCompradorSelecionada.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(null, "Por favor, escolha/cadastre uma coleção");
			return false;
		}
		quantidadeColecaoMoeda = Integer.parseInt(txtQuantidade.getText().replace(" ", ""));
		String colecaoVendedorSelecionada = (String) table.getValueAt(table.getSelectedRow(), 6);
		String vendedorSelecionado = (String) table.getValueAt(table.getSelectedRow(), 5);
		Long idMoeda = (Long) table.getValueAt(table.getSelectedRow(), 0);
		PessoaEntity pessoaVendedor = new PessoaEntity();
		PessoaEntity pessoaComprador = new PessoaEntity();
		pessoaVendedor = pessoaService.pesquisaNome(vendedorSelecionado);
		pessoaComprador = pessoaService.pesquisaId(PESSOA_ID);
		ColecaoEntity colecaoVendedor = new ColecaoEntity();
		colecaoVendedor = colecaoService.pesquisaColecaoPessoa(colecaoVendedorSelecionada, pessoaVendedor);
		ColecaoEntity colecaoComprador = new ColecaoEntity();
		colecaoComprador = colecaoService
				.pesquisaColecaoPessoa(colecaoCompradorSelecionada.getSelectedItem().toString(), pessoaComprador);
		if (pessoaVendedor.getId() == pessoaComprador.getId()) {
			JOptionPane.showMessageDialog(null, "Essa moeda já é sua");
			return false;
		}
		return true;
	}

	public CompraMoedas() {
		PessoaEntity usuario = new PessoaEntity();
		usuario = pessoaService.pesquisaId(PESSOA_ID);
		modeloColecao = new DefaultTableModel() {
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				return String.class;
			}

			public boolean isCellEditable(int row, int column) {
				return false; // Torna todas as células não editáveis
			}
		};
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1070, 604);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblColao = new JLabel("Comprar Moeda");
		lblColao.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblColao.setBounds(411, 10, 186, 28);
		contentPane.add(lblColao);

		colecaoCompradorSelecionada = new JComboBox<Object>();
		colecaoCompradorSelecionada.setBounds(854, 49, 165, 28);
		contentPane.add(colecaoCompradorSelecionada);
		colecaoCompradorSelecionada.setBounds(857, 31, 168, 37);
		contentPane.add(colecaoCompradorSelecionada);
		PessoaEntity pessoaEntity = new PessoaEntity();
		pessoaEntity = pessoaService.pesquisaId(PESSOA_ID);
		List<ColecaoEntity> colecoesPessoa = colecaoService.pesquisaPessoa(pessoaEntity);
		try {
			for (ColecaoEntity elemento : colecoesPessoa) {
				String p = elemento.getNome();
				colecaoCompradorSelecionada.addItem(p);
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		modeloColecao.addColumn("Id");
		modeloColecao.addColumn("Nome");
		modeloColecao.addColumn("Cod");
		modeloColecao.addColumn("Valor unitário");
		modeloColecao.addColumn("Quantidade");
		modeloColecao.addColumn("Dono");
		modeloColecao.addColumn("Coleção");

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 69, 837, 449);
		contentPane.add(scrollPane);

		table = new JTable(modeloColecao);
		scrollPane.setViewportView(table);
		table.setEnabled(false);

		JButton btnSair = new JButton("Sair");
		btnSair.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnSair.setBounds(20, 530, 110, 37);
		contentPane.add(btnSair);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CompraMoedas.this.dispose();
				Menu.main(null);
			}
		});
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnVoltar.setBounds(447, 530, 110, 37);
		contentPane.add(btnVoltar);

		lblColeoSelecionada = new JLabel("Coleção Selecionada");
		lblColeoSelecionada.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblColeoSelecionada.setBounds(857, 0, 168, 28);
		contentPane.add(lblColeoSelecionada);

		txtQuantidade = new JFormattedTextField((AbstractFormatter) null);
		txtQuantidade.setColumns(10);
		txtQuantidade.setBounds(857, 248, 168, 30);
		contentPane.add(txtQuantidade);

		JLabel lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblQuantidade.setBounds(857, 218, 168, 28);
		contentPane.add(lblQuantidade);

		JLabel lblMoedasAVenda = new JLabel("Moedas à venda");
		lblMoedasAVenda.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMoedasAVenda.setBounds(357, 74, 140, 28);
		contentPane.add(lblMoedasAVenda);

		tabelaColecao();

		btnComprarMoeda = new JButton("Comprar Moeda");
		btnComprarMoeda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (validaDados()) {
					Integer quantidadeColecaoMoeda = Integer.parseInt(txtQuantidade.getText().replace(" ", ""));
					Float valorUnitario = (Float) table.getValueAt(table.getSelectedRow(), 3);
					
					String colecaoVendedorSelecionada = (String) table.getValueAt(table.getSelectedRow(), 6);
					String vendedorSelecionado = (String) table.getValueAt(table.getSelectedRow(), 5);
					Long idMoeda = (Long) table.getValueAt(table.getSelectedRow(), 0);
					PessoaEntity pessoaVendedor = new PessoaEntity();
					PessoaEntity pessoaComprador = new PessoaEntity();
					pessoaVendedor = pessoaService.pesquisaNome(vendedorSelecionado);
					pessoaComprador = pessoaService.pesquisaId(PESSOA_ID);
					ColecaoEntity colecaoVendedor = new ColecaoEntity();
					colecaoVendedor = colecaoService.pesquisaColecaoPessoa(colecaoVendedorSelecionada, pessoaVendedor);
					ColecaoEntity colecaoComprador = new ColecaoEntity();
					colecaoComprador = colecaoService.pesquisaColecaoPessoa(
							colecaoCompradorSelecionada.getSelectedItem().toString(), pessoaComprador);
					MoedaEntity moedaVendedor = new MoedaEntity();
					moedaVendedor = moedaService.pesquisaId(idMoeda);
					ColecaoMoedaId id = new ColecaoMoedaId();
					ColecaoMoedaId idVendedor = new ColecaoMoedaId();
					idVendedor.setIdColecao(colecaoVendedor);
					idVendedor.setIdMoeda(moedaVendedor);
					ColecaoMoedaEntity colecaoMoedaVendedor = new ColecaoMoedaEntity();
					colecaoMoedaVendedor = colecaoMoedaService.pesquisaPeloId(idVendedor);

					id.setIdColecao(colecaoComprador);
					id.setIdMoeda(moedaVendedor);

					ColecaoMoedaEntity colecaoMoeda = new ColecaoMoedaEntity();
					colecaoMoeda.setId(id);
					colecaoMoeda.setQuantidade(quantidadeColecaoMoeda);
					colecaoMoeda.setTipo('c');
					colecaoMoeda.setValorUnitario(0f);
					Calendar calendario = Calendar.getInstance();
					String data = dateFormater.formatarDataBR(calendario.getTime());
					colecaoMoeda.setData(dateFormater.converterParaData(data));

					int quantidadeVendedor = colecaoMoedaVendedor.getQuantidade();
					int quantidadeComprador = colecaoMoeda.getQuantidade();
					int quantidadeTotalVendedor = quantidadeVendedor - quantidadeComprador;
					Float valorTotal = quantidadeColecaoMoeda * valorUnitario;
					String total =  Float.valueOf(valorTotal).toString();
					boolean confirmacao = ConfirmacaoUtil.mostrarPopUpConfirmacaoCompra(total);
					if (confirmacao == false) {
						return;
					}
					colecaoMoeda.setQuantidade(quantidadeComprador);
					colecaoMoedaVendedor.setQuantidade(quantidadeTotalVendedor);
					try {
						colecaoMoedaService.salvar(colecaoMoeda, (byte) 1);
						modeloColecao.setRowCount(0);
						tabelaColecao();
					} catch (Exception e1) {
						e1.printStackTrace();
					}

				}
			}
		});

		btnComprarMoeda.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnComprarMoeda.setBounds(857, 288, 165, 37);
		contentPane.add(btnComprarMoeda);

	}
}
