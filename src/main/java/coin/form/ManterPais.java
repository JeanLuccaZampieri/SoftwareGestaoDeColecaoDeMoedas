package coin.form;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import coin.entity.PaisEntity;
import coin.service.PaisService;
import coin.util.ConfirmacaoUtil;
import coin.util.MascaraUtil;

public class ManterPais extends JFrame {

	private JPanel contentPane;
	private JFormattedTextField txtNome;
	private String nomePais;
	private Long idPais;
	private JScrollPane scrollPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	private boolean validaDados() {
		nomePais = txtNome.getText().toUpperCase().trim();
		PaisEntity pais = new PaisEntity();
		pais.setNome(nomePais);
		if (nomePais.isBlank()) {
			JOptionPane.showMessageDialog(null, "Por favor, informe país!");
			return false;
		}
		if (!paisService.validaNome(pais)) {
			JOptionPane.showMessageDialog(null, "país já existe, por favor insira outro!");
			return false;
		}
		if (nomePais.length() < 2) {
			JOptionPane.showMessageDialog(null, "O nome do pais deve ser maior!");
			return false;
		}
		if (nomePais.length() > 30) {
			JOptionPane.showMessageDialog(null, "Por favor, insira um nome menor!");
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManterPais frame = new ManterPais();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static PaisService paisService = new PaisService();

	/**
	 * Create the frame.
	 */
	public ManterPais() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1070, 604);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCadastrarPais = new JLabel("Manter País");
		lblCadastrarPais.setBounds(446, 10, 143, 23);
		lblCadastrarPais.setFont(new Font("Tahoma", Font.BOLD, 19));
		contentPane.add(lblCadastrarPais);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNome.setBounds(374, 51, 62, 28);
		contentPane.add(lblNome);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnSalvar.setBounds(936, 311, 110, 37);
		contentPane.add(btnSalvar);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validaDados()) {
					boolean confirmacao = ConfirmacaoUtil.mostrarPopUpConfirmacao();
					if (confirmacao == false) {
						return;
					}
					PaisEntity pais = new PaisEntity();
					pais.setNome(nomePais);
					if (idPais != null) {
						pais.setId(idPais);
					}
					paisService.salvar(pais);
					ManterPais.this.dispose();
					Cads.main(null);
				}
			}
		});

		JButton btnSair = new JButton("Sair");
		btnSair.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnSair.setBounds(10, 526, 110, 37);
		contentPane.add(btnSair);
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManterPais.this.dispose();
			}
		});

		txtNome = new JFormattedTextField(MascaraUtil.getMascaraNome());
		txtNome.setColumns(10);
		txtNome.setBounds(375, 76, 260, 28);
		contentPane.add(txtNome);

		final DefaultTableModel tableModel = new DefaultTableModel() {
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				return String.class;
			}

			public boolean isCellEditable(int row, int column) {
				return false; // Torna todas as células não editáveis
			}
		};
		List<PaisEntity> paises = null;
		paises = paisService.listar();

		tableModel.addColumn("Id");
		tableModel.addColumn("Nome");

		for (PaisEntity pais : paises) {
			Object[] rowData = { pais.getId(), pais.getNome() };
			tableModel.addRow(rowData);
		}

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 121, 916, 395);
		contentPane.add(scrollPane);

		table = new JTable(tableModel);
		scrollPane.setViewportView(table);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnVoltar.setBounds(443, 526, 110, 37);
		contentPane.add(btnVoltar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnExcluir.setBounds(936, 187, 110, 37);
		contentPane.add(btnExcluir);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() >= 0) {
					boolean confirmacao = ConfirmacaoUtil.mostrarPopUpConfirmacao();
					if (confirmacao == false) {
						return;
					}
					// Obtenha os valores das células na linha selecionada
					Long valorColuna1 = (Long) table.getValueAt(table.getSelectedRow(), 0);
					paisService.remover(valorColuna1);
					// Remova a linha do modelo de dados
					tableModel.removeRow(table.getSelectedRow());

					// Atualize a exibição da JTable
					table.revalidate();
					table.repaint();
				} else {
					JOptionPane.showMessageDialog(null, "Por favor, selecione uma opção");
				}
			}
		});

		JButton btnEditar = new JButton("Editar");
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnEditar.setBounds(936, 251, 110, 37);
		contentPane.add(btnEditar);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() >= 0) {
					Long celula1 = (Long) table.getValueAt(table.getSelectedRow(), 0);
					PaisEntity pais = paisService.pesquisaId(celula1);
					txtNome.setText(pais.getNome().toString());
					idPais = pais.getId();
				} else {
					JOptionPane.showMessageDialog(null, "Por favor, selecione uma opção");
				}
			}
		});

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnLimpar.setBounds(936, 121, 110, 37);
		contentPane.add(btnLimpar);
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNome.setText(null);
				idPais = null;
			}
		});
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManterPais.this.dispose();
				Cads.main(null);
			}
		});
	}
}
