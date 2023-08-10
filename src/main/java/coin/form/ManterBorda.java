package coin.form;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import coin.entity.BordaEntity;
import coin.entity.ColecaoEntity;
import coin.entity.BordaEntity;
import coin.entity.BordaEntity;
import coin.entity.BordaEntity;
import coin.service.BordaService;
import coin.util.ConfirmacaoUtil;
import coin.util.MascaraUtil;

import javax.swing.JFormattedTextField;

public class ManterBorda extends JDialog {

	private JFormattedTextField txtNome;
	private String nomeBorda;
	private Long idBorda;
	private JScrollPane scrollPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	private boolean validaDados() {
		nomeBorda = txtNome.getText().toUpperCase().replace(" ", "");
		BordaEntity borda = new BordaEntity();
		nomeBorda.toUpperCase().trim();
		borda.setNome(nomeBorda);
		if (nomeBorda.isBlank()) {
			JOptionPane.showMessageDialog(null, "Por favor, informe a Borda!");
			return false;
		}
		if (!bordaService.validaNome(borda)) {
			JOptionPane.showMessageDialog(null, "A Borda já existe, por favor insira outra!");
			return false;
		}
		if(nomeBorda.length()>50) {
			JOptionPane.showMessageDialog(null, "Por favor, insira um nome menor!");
			return false;
		}
		return true;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {

			ManterBorda dialog = new ManterBorda();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static BordaService bordaService = new BordaService();

	/**
	 * Create the dialog.
	 */

	public ManterBorda() {

		setBounds(100, 100, 1070, 604);
		getContentPane().setLayout(null);

		JLabel lblCadastrarBorda = new JLabel("Manter Borda");
		lblCadastrarBorda.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblCadastrarBorda.setBounds(398, 21, 182, 23);
		getContentPane().add(lblCadastrarBorda);

		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblTipo.setBounds(358, 54, 81, 28);
		getContentPane().add(lblTipo);

		txtNome = new JFormattedTextField(MascaraUtil.getMascaraNome());
		txtNome.setColumns(10);
		txtNome.setBounds(357, 83, 260, 28);
		getContentPane().add(txtNome);

		final DefaultTableModel tableModel = new DefaultTableModel() {
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				return String.class;
			}

			public boolean isCellEditable(int row, int column) {
				return false; // Torna todas as células não editáveis
			}
		};
		tableModel.addColumn("Id");
		tableModel.addColumn("Tipo");

		List<BordaEntity> bordas = null;
		bordas = bordaService.listar();
		for (BordaEntity pessoa : bordas) {

			Object[] rowData = { pessoa.getId(), pessoa.getNome() };
			tableModel.addRow(rowData);
		}

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 121, 916, 395);
		getContentPane().add(scrollPane);

		table = new JTable(tableModel);
		scrollPane.setViewportView(table);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnSalvar.setBounds(936, 315, 110, 37);
		getContentPane().add(btnSalvar);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validaDados()) {
					boolean confirmacao = ConfirmacaoUtil.mostrarPopUpConfirmacao();
					if(confirmacao == false) {
						return;
					}
					BordaEntity borda = new BordaEntity();
					borda.setNome(nomeBorda);
					if (idBorda != null) {
						borda.setId(idBorda);
					}
					bordaService.salvar(borda);
					ManterBorda.this.dispose();
					Cads.main(null);
				}
			}
		});

		JButton btnSair = new JButton("Sair");
		btnSair.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnSair.setBounds(10, 526, 110, 37);
		getContentPane().add(btnSair);
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManterBorda.this.dispose();
			}
		});

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnVoltar.setBounds(443, 526, 110, 37);
		getContentPane().add(btnVoltar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnExcluir.setBounds(936, 187, 110, 37);
		getContentPane().add(btnExcluir);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() >= 0) {
					boolean confirmacao = ConfirmacaoUtil.mostrarPopUpConfirmacao();
					if(confirmacao == false) {
						return;
					}
					// Obtenha os valores das células na linha selecionada
					Long valorColuna1 = (Long) table.getValueAt(table.getSelectedRow(), 0);
					bordaService.remover(valorColuna1);
					// Remova a linha do modelo de dados
					tableModel.removeRow(table.getSelectedRow());

					// Atualize a exibição da JTable
				} else {
					JOptionPane.showMessageDialog(null, "Por favor, selecione uma opção");
				}
			}
		});

		JButton btnEditar = new JButton("Editar");
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnEditar.setBounds(936, 251, 110, 37);
		getContentPane().add(btnEditar);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() >= 0) {
					Long celula1 = (Long) table.getValueAt(table.getSelectedRow(), 0);
					BordaEntity borda = bordaService.pesquisaId(celula1);
					txtNome.setText(borda.getNome().toString());
					idBorda = borda.getId();
				} else {
					JOptionPane.showMessageDialog(null, "Por favor, selecione uma opção");
				}
			}
		});

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnLimpar.setBounds(936, 121, 110, 37);
		getContentPane().add(btnLimpar);
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNome.setText(null);
				idBorda = null;
			}
		});
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManterBorda.this.dispose();
				Cads.main(null);
			}
		});

	}
}
