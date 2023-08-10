package coin.form;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import coin.entity.PessoaEntity;
import coin.service.PessoaService;

public class LoginUser extends JFrame {

	private JPanel contentPane;
	private String senhaEmail;
	private String usuarioEmail;
	private PessoaEntity pessoa1;
	public static Long id;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		id = null;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginUser frame = new LoginUser();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private static PessoaService pessoaService = new PessoaService();
	private JTextField txtEmail;
	private JPasswordField txtSenha;
	
	/**
	 * Create the frame.
	 */
	public LoginUser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1070, 604);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblNewLabel.setBounds(509, 71, 59, 28);
		contentPane.add(lblNewLabel);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblEmail.setBounds(345, 117, 81, 28);
		contentPane.add(lblEmail);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblSenha.setBounds(345, 223, 81, 28);
		contentPane.add(lblSenha);
		
		JButton btnSair = new JButton("Sair");
		btnSair.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnSair.setBounds(464, 489, 162, 51);
		contentPane.add(btnSair);
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginUser.this.dispose();
			}
		});
		
		
		JButton btnProximo = new JButton("Entrar");
		btnProximo.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnProximo.setBounds(464, 304, 162, 51);
		contentPane.add(btnProximo);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(345, 143, 407, 33);
		contentPane.add(txtEmail);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(345, 249, 407, 33);
		contentPane.add(txtSenha);
		
		JLabel lblNewLabel_1 = new JLabel("Numis Master");
		lblNewLabel_1.setFont(new Font("Yu Gothic Medium", Font.BOLD, 25));
		lblNewLabel_1.setBounds(454, 10, 182, 51);
		contentPane.add(lblNewLabel_1);
		
		JButton btnCadastrarNovoUsurio = new JButton("Criar conta");
		
		btnCadastrarNovoUsurio.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnCadastrarNovoUsurio.setBounds(464, 428, 162, 51);
		contentPane.add(btnCadastrarNovoUsurio);
		btnCadastrarNovoUsurio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginUser.this.dispose();
				CadPessoa.main(null);
			}
		});
		
		JButton btnAjuda = new JButton("Ajuda");
		btnAjuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ajuda.main(null);
			}
		});
		btnAjuda.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnAjuda.setBounds(464, 365, 162, 51);
		contentPane.add(btnAjuda);
		btnProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<PessoaEntity> senhas = null;
				char[] password = txtSenha.getPassword();
				senhaEmail = new String(password);
				senhaEmail = senhaEmail.replace(" ", "");
				usuarioEmail = txtEmail.getText().replace(" ", "");
				senhas = pessoaService.pesquisaSenha(senhaEmail);
				pessoa1 = pessoaService.pesquisaEmail(usuarioEmail);
				
				try {
					byte ver =0;
					for (PessoaEntity pessoa2 : senhas) {
						if(pessoa2.getId() == pessoa1.getId()) {
							id = pessoa1.getId();
							LoginUser.this.dispose();
							new Menu().setVisible(true);
							ver=1;
						}
					}
					if(ver ==0 ) {
						JOptionPane.showMessageDialog(null, "A senha e/ou usuario está incorreta");
					}
				}catch(Exception e1) {
					if(usuarioEmail.isBlank()) {
						JOptionPane.showMessageDialog(null, "Por favor, informe o Email!");
					}
					else if(senhaEmail.isBlank()) {
						JOptionPane.showMessageDialog(null, "Por favor, informe a Senha!");
					}
					else {
						JOptionPane.showMessageDialog(null, "A senha e/ou usuario está incorreta");
					}
				}
				
			}
		});
		
		
	}
}
