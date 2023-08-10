package coin.form;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import coin.entity.PessoaEntity;
import coin.service.PessoaService;
import coin.util.ConfirmacaoUtil;
import coin.util.MascaraUtil;
import javax.swing.JPasswordField;

public class CadPessoa extends JFrame {

	private JPanel contentPane;
	private JFormattedTextField txtCpf;
	private JTextField txtNome;
	private JTextField txtEmail;
	private JFormattedTextField txtTelefone;
	private String cpfUser;
	private String telefoneUser;
	private String senhaUser;
	private String confirmaSenha;
	private String emailUser;
	private String nomeUser;
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
		pessoa.setCpf(cpfUser); pessoa.setEmail(emailUser); pessoa.setNome(nomeUser);
		pessoa.setSenha(senhaUser);pessoa.setTelefone(telefoneUser);pessoa.setTipo('U');
		pessoa.setColecao(null);
		byte p1 = pessoaService.validacao(pessoa);
		
		if(!confirmaSenha.equals(senhaUser)) {
			JOptionPane.showMessageDialog(null, "A senha não e a confirmação de senha não estão iguais");
			return false;
		}
		if(!pessoaService.verificarFormatoEmail(emailUser)) {
			JOptionPane.showMessageDialog(null, "O Email digitado é inválido!");
			return false;
		}
		if(!pessoaService.verificarFormatoCPF(cpfUser)) {
			JOptionPane.showMessageDialog(null, "O CPF digitado é inválido!");
			return false;
		}
	    if (nomeUser.length() < 3) {
	    	JOptionPane.showMessageDialog(null, "O nome deve ser maior!");
	        return false;
	    }
	    if (nomeUser.length() > 150) {
	    	JOptionPane.showMessageDialog(null, "A nome deve ser menor!");
	        return false;
	    }
	    if (senhaUser.length() < 3) {
	    	JOptionPane.showMessageDialog(null, "A senha deve ser maior!");
	        return false;
	    }
	    if (senhaUser.length() > 50) {
	    	JOptionPane.showMessageDialog(null, "A senha deve ser menor!");
	        return false;
	    }
	    
		if (nomeUser.trim().isBlank()) {
			JOptionPane.showMessageDialog(null, "Por favor, informe o Nome!");
			return false;
		}
		if (emailUser.trim().isBlank()){
			JOptionPane.showMessageDialog(null, "Por favor, informe o Email!");
			return false;
		}
		if(emailUser.length()>150) {
			JOptionPane.showMessageDialog(null, "O email deve ser menor");
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
		if(p1 < 4) {
			if(p1 == 0) {
				JOptionPane.showMessageDialog(null, "O nome de usuário já existe");
				return false;
			}
			if(p1 == 2) {
				JOptionPane.showMessageDialog(null, "O email já existe");
				return false;
			}if(p1 == 3) {
				JOptionPane.showMessageDialog(null, "O telefone já existe");
				return false;
			}
			if(p1 == 1) {
				JOptionPane.showMessageDialog(null, "O cpf já existe");
				return false;
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
					CadPessoa frame = new CadPessoa();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private static PessoaService pessoaService = new PessoaService();	
	private JPasswordField txtSenha;
	private JPasswordField txtConfirmaSenha;
	
	/**
	 * Create the frame.
	 */
	public CadPessoa() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1070, 604);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCadastrarPessoa = new JLabel("Cadastrar pessoa");
		lblCadastrarPessoa.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblCadastrarPessoa.setBounds(411, 10, 246, 29);
		contentPane.add(lblCadastrarPessoa);
		
		txtCpf = new JFormattedTextField(MascaraUtil.getMascaraCpf());
		txtCpf.setColumns(10);
		txtCpf.setBounds(624, 357, 407, 33);
		contentPane.add(txtCpf);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCpf.setBounds(624, 339, 241, 21);
		contentPane.add(lblCpf);
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(10, 84, 407, 33);
		contentPane.add(txtNome);
		
		JLabel lblNome_1 = new JLabel("Nome de usuário");
		lblNome_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNome_1.setBounds(10, 59, 147, 21);
		contentPane.add(lblNome_1);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(624, 84, 407, 33);
		contentPane.add(txtEmail);
		
		JLabel lblEmail = new JLabel("E-Mail");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblEmail.setBounds(624, 59, 147, 21);
		contentPane.add(lblEmail);
		
		txtTelefone = new JFormattedTextField(MascaraUtil.getMascaraTelefone());
		txtTelefone.setColumns(10);
		txtTelefone.setBounds(624, 217, 407, 33);
		contentPane.add(txtTelefone);
		
		JLabel lblNumeroDeTelefone = new JLabel("Telefone");
		lblNumeroDeTelefone.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNumeroDeTelefone.setBounds(624, 197, 147, 21);
		contentPane.add(lblNumeroDeTelefone);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnVoltar.setBounds(194, 506, 162, 51);
		contentPane.add(btnVoltar);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadPessoa.this.dispose();
				LoginUser.main(null);
			}
		});
		
		JButton btnSair = new JButton("Sair");
		btnSair.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnSair.setBounds(10, 506, 162, 51);
		contentPane.add(btnSair);
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean confirmacao = ConfirmacaoUtil.mostrarPopUpConfirmacao();
				if(confirmacao == false) {
					return;
				}
				CadPessoa.this.dispose();
			}
		});
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnSalvar.setBounds(378, 506, 162, 51);
		contentPane.add(btnSalvar);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSenha.setBounds(10, 197, 147, 21);
		contentPane.add(lblSenha);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(10, 218, 407, 33);
		contentPane.add(txtSenha);
		
		txtConfirmaSenha = new JPasswordField();
		txtConfirmaSenha.setBounds(10, 357, 407, 33);
		contentPane.add(txtConfirmaSenha);
		
		JLabel lblConfirmarSenha = new JLabel("Confirmar Senha");
		lblConfirmarSenha.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblConfirmarSenha.setBounds(10, 332, 147, 21);
		contentPane.add(lblConfirmarSenha);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				if(validaDados()) {
					boolean confirmacao = ConfirmacaoUtil.mostrarPopUpConfirmacao();
					if(confirmacao == false) {
						return;
					}
					PessoaEntity pessoa = new PessoaEntity();
					pessoa.setCpf(cpfUser);
					pessoa.setEmail(emailUser);
					pessoa.setNome(nomeUser);
					pessoa.setTelefone(telefoneUser);
					pessoa.setSenha(senhaUser);
					pessoa.setTipo('U');
					pessoa.setColecao(null);
					pessoaService.salvar(pessoa);
					CadPessoa.this.dispose();
					Lgpd.main(null);
				}
			}
		});
		
	}
}
