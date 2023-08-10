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
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import coin.entity.ColecaoEntity;
import coin.entity.PessoaEntity;
import coin.service.ColecaoService;
import coin.service.PessoaService;
import coin.util.ConfirmacaoUtil;
import coin.util.MascaraUtil;

public class ManterUser extends JFrame {

	private JPanel contentPane;
	private JFormattedTextField txtCpf;
	private JFormattedTextField txtNome;
	private JFormattedTextField txtEmail;
	private JFormattedTextField txtTelefone;
	private String cpfUser;
	private String telefoneUser;
	private String confirmaSenha;
	private String senhaUser;
	private String emailUser;
	private String nomeUser;
	private Long idUser;
	private static PessoaService pessoaService = new PessoaService();
	private JScrollPane scrollPane;
	private JTable table;
	private JPasswordField txtSenha;

	private JPasswordField txtConfirmaSenha;

	private boolean validaDados() {
		char[] password = txtSenha.getPassword();
		char[] passwordConfirm = txtConfirmaSenha.getPassword();
		senhaUser = new String(password);
		confirmaSenha = new String(passwordConfirm);
		cpfUser = txtCpf.getText().replace("-", "").replace(".", "");
		telefoneUser = txtTelefone.getText().replace("(", "").replace(")", "").replace(" ", "").replace("-", "");
		senhaUser = senhaUser.replace(" ", "");
		confirmaSenha = confirmaSenha.replace("", "");
		emailUser = txtEmail.getText().replace(" ", "").toUpperCase();
		nomeUser = txtNome.getText().trim().toUpperCase();
		PessoaEntity pessoa = new PessoaEntity();
		pessoa.setCpf(cpfUser);
		pessoa.setEmail(emailUser);
		pessoa.setNome(nomeUser);
		pessoa.setSenha(senhaUser);
		pessoa.setTelefone(telefoneUser);
		pessoa.setTipo('U');
		pessoa.setColecao(null);
		PessoaEntity pessoaEntity = new PessoaEntity();
		byte verificadorEmail = 0;
		byte verificadorTelefone = 0;
		byte verificadorCpf = 0;
		byte verificadorNome = 0;
		verificadorEmail = 0;
		verificadorTelefone = 0;
		verificadorCpf = 0;
		verificadorNome = 0;
		if (idUser != null) {
			pessoaEntity = pessoaService.pesquisaId(idUser);
			if (!pessoaEntity.getEmail().equals(pessoa.getEmail())) {
				verificadorEmail = 1;
			}
			if (!pessoaEntity.getTelefone().equals(pessoa.getTelefone())) {
				verificadorTelefone = 2;
			}
			if (!pessoaEntity.getCpf().equals(pessoa.getCpf())) {
				verificadorCpf = 3;
			}
			if (!pessoaEntity.getNome().equals(pessoa.getNome())) {
				verificadorNome = 4;
			}
		}
		byte pessoa1 = pessoaService.validacao(pessoa);

		if (!confirmaSenha.equals(senhaUser)) {
			JOptionPane.showMessageDialog(null, "A senha não e a confirmação de senha não estão iguais");
			return false;
		}
		if (!pessoaService.verificarFormatoEmail(emailUser)) {
			JOptionPane.showMessageDialog(null, "O Email digitado é  inválido!");
			return false;
		}
		if (!pessoaService.verificarFormatoCPF(cpfUser)) {
			JOptionPane.showMessageDialog(null, "O CPF digitado é inválido!");
			return false;
		}
		if (nomeUser.trim().isBlank()) {
			JOptionPane.showMessageDialog(null, "Por favor, informe o Nome!");
			return false;
		}
		if (emailUser.trim().isBlank()) {
			JOptionPane.showMessageDialog(null, "Por favor, informe o Email!");
			return false;
		}
		if (senhaUser.trim().isBlank()) {
			JOptionPane.showMessageDialog(null, "Por favor, informe a Senha!");
			return false;
		}
		if (telefoneUser.trim().isBlank()) {
			JOptionPane.showMessageDialog(null, "Por favor, informe o Telefone!");
			return false;
		}
		if (cpfUser.trim().isBlank()) {
			JOptionPane.showMessageDialog(null, "Por favor, informe o cpf!");
			return false;
		}
		if (nomeUser.length() < 3) {
			JOptionPane.showMessageDialog(null, "O nome deve ser maior!");
			return false;
		}
		if (senhaUser.length() < 3) {
			JOptionPane.showMessageDialog(null, "A senha deve ser maior!");
			return false;
		}
		if (pessoa1 < 4) {
			if (idUser == null) {
				if (pessoa1 == 0) {
					JOptionPane.showMessageDialog(null, "O nome já existe");
					return false;
				}
				if (pessoa1 == 2) {
					JOptionPane.showMessageDialog(null, "O email já existe");
					return false;
				}
				if (pessoa1 == 3) {
					JOptionPane.showMessageDialog(null, "O telefone já existe");
					return false;
				}
				if (pessoa1 == 1) {
					JOptionPane.showMessageDialog(null, "O cpf já existe");
					return false;
				}
			}
			if (idUser != null) {
				if (!pessoaService.validaEmail(pessoa) && verificadorEmail == 1) {
					JOptionPane.showMessageDialog(null, "O email já existe");
					return false;
				}
				if (!pessoaService.validaTelefone(pessoa) && verificadorTelefone == 2) {
					JOptionPane.showMessageDialog(null, "O telefone já existe");
					return false;
				}
				if (!pessoaService.validaCpf(pessoa) && verificadorCpf == 3) {
					JOptionPane.showMessageDialog(null, "O cpf já existe");
					return false;
				}
				if (!pessoaService.validaNome(pessoa) && verificadorNome == 3) {
					JOptionPane.showMessageDialog(null, "O nome já existe");
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManterUser frame = new ManterUser();
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
	public ManterUser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1070, 604);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCadastrarPessoa = new JLabel("Manter Pessoa");
		lblCadastrarPessoa.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblCadastrarPessoa.setBounds(435, 0, 158, 29);
		contentPane.add(lblCadastrarPessoa);

		txtCpf = new JFormattedTextField(MascaraUtil.getMascaraCpf());
		txtCpf.setColumns(10);
		txtCpf.setBounds(750, 66, 246, 30);
		contentPane.add(txtCpf);

		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCpf.setBounds(750, 48, 138, 21);
		contentPane.add(lblCpf);

		txtNome = new JFormattedTextField(MascaraUtil.getMascaraNome());
		txtNome.setColumns(10);
		txtNome.setBounds(10, 71, 234, 30);
		contentPane.add(txtNome);

		JLabel lblNome_1 = new JLabel("Nome de usuário");
		lblNome_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNome_1.setBounds(10, 48, 147, 21);
		contentPane.add(lblNome_1);

		txtEmail = new JFormattedTextField(MascaraUtil.getMascaraNome());
		txtEmail.setColumns(10);
		txtEmail.setBounds(382, 71, 241, 30);
		contentPane.add(txtEmail);

		JLabel lblEmail = new JLabel("E-Mail");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblEmail.setBounds(382, 48, 147, 21);
		contentPane.add(lblEmail);

		txtTelefone = new JFormattedTextField(MascaraUtil.getMascaraTelefone());
		txtTelefone.setColumns(10);
		txtTelefone.setBounds(755, 142, 241, 30);
		contentPane.add(txtTelefone);

		JLabel lblNumeroDeTelefone = new JLabel("Telefone");
		lblNumeroDeTelefone.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNumeroDeTelefone.setBounds(749, 111, 147, 21);
		contentPane.add(lblNumeroDeTelefone);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnVoltar.setBounds(483, 533, 110, 37);
		contentPane.add(btnVoltar);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManterUser.this.dispose();
				Cads.main(null);
			}
		});

		JButton btnSair = new JButton("Sair");
		btnSair.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnSair.setBounds(10, 533, 110, 37);
		contentPane.add(btnSair);
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManterUser.this.dispose();
			}
		});

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnSalvar.setBounds(936, 368, 110, 37);
		contentPane.add(btnSalvar);

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSenha.setBounds(10, 114, 147, 21);
		contentPane.add(lblSenha);

		final DefaultTableModel tableModel = new DefaultTableModel() {
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				return String.class;
			}

			public boolean isCellEditable(int row, int column) {
				return false; // Torna todas as células não editáveis
			}
		};
		List<PessoaEntity> pessoas = null;
		pessoas = pessoaService.listar();

		tableModel.addColumn("Id");
		tableModel.addColumn("Nome");
		tableModel.addColumn("Cpf");
		tableModel.addColumn("Email");
		tableModel.addColumn("Telefone");

		for (PessoaEntity pessoa : pessoas) {

			Object[] rowData = { pessoa.getId(), pessoa.getNome(), pessoa.getCpf(), pessoa.getEmail(),
					pessoa.getTelefone() };
			tableModel.addRow(rowData);
		}

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 198, 916, 325);
		contentPane.add(scrollPane);

		table = new JTable(tableModel);
		scrollPane.setViewportView(table);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnExcluir.setBounds(936, 255, 110, 37);
		contentPane.add(btnExcluir);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() >= 0) {
					Long valorColuna1 = (Long) table.getValueAt(table.getSelectedRow(), 0);
					PessoaEntity pessoaEntity = new PessoaEntity();
					pessoaEntity = pessoaService.pesquisaId(valorColuna1);
					ColecaoService colecaoService = new ColecaoService();
					List<ColecaoEntity>colecao = null;
					colecao = colecaoService.pesquisaPessoa(pessoaEntity);
					if (colecao == null) {
						boolean confirmacao = ConfirmacaoUtil.mostrarPopUpConfirmacao();
						if (confirmacao == false) {
							return;
						}
						pessoaService.remover(valorColuna1);
						tableModel.removeRow(table.getSelectedRow());
						table.revalidate();
						table.repaint();
					}else{
						boolean confirmacaoRmv = ConfirmacaoUtil.mostrarPopUpConfirmacaoRmvUser();
						if (confirmacaoRmv == false) {
							return;
						}else {
							for (ColecaoEntity colecaoRmv : colecao) {
								pessoaService.remover(valorColuna1);
								tableModel.removeRow(table.getSelectedRow());
								table.revalidate();
								table.repaint();
								colecaoService.remover(colecaoRmv);
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
		btnEditar.setBounds(936, 312, 110, 37);
		contentPane.add(btnEditar);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {

	public void actionPerformed(ActionEvent e) {
				txtNome.setText(null);
				txtCpf.setText(null);
				txtEmail.setText(null);
				txtTelefone.setText(null);
				idUser = null;
			}
		});
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnLimpar.setBounds(936, 198, 110, 37);
		contentPane.add(btnLimpar);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(10, 142, 234, 30);
		contentPane.add(txtSenha);

		JLabel lblConfirmarSenha = new JLabel("Confirmar senha");
		lblConfirmarSenha.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblConfirmarSenha.setBounds(382, 114, 147, 21);
		contentPane.add(lblConfirmarSenha);

		txtConfirmaSenha = new JPasswordField();
		txtConfirmaSenha.setBounds(382, 142, 234, 30);
		contentPane.add(txtConfirmaSenha);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() >= 0) {
					Long celula1 = (Long) table.getValueAt(table.getSelectedRow(), 0);
					PessoaEntity pessoa = pessoaService.pesquisaId(celula1);
					txtNome.setText(pessoa.getNome().toString());
					txtCpf.setText(pessoa.getCpf().toString());
					txtEmail.setText(pessoa.getEmail().toString());
					txtTelefone.setText(pessoa.getTelefone().toString());
					idUser = pessoa.getId();
				} else {
					JOptionPane.showMessageDialog(null, "Por favor, selecione uma opção");
				}
			}
		});

		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validaDados()) {
					boolean confirmacao = ConfirmacaoUtil.mostrarPopUpConfirmacao();
					if (confirmacao == false) {
						return;
					}
					PessoaEntity pessoa = new PessoaEntity();

					pessoa.setCpf(cpfUser);
					pessoa.setEmail(emailUser);
					pessoa.setNome(nomeUser);
					pessoa.setTelefone(telefoneUser);
					pessoa.setSenha(senhaUser);
					pessoa.setTipo('U');
					if (idUser != null) {
						pessoa.setId(idUser);
					}
					pessoa.setColecao(null);
					pessoaService.salvar(pessoa);
					ManterUser.this.dispose();
					Lgpd.main(null);

				}
			}
		});

	}
}
