package coin.form;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;

public class Home extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
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
	public Home() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1070, 604);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Entradas");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblNewLabel.setBounds(494, 151, 82, 21);
		contentPane.add(lblNewLabel);
		
		JButton btnLogar = new JButton("Logar");
		btnLogar.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnLogar.setBounds(416, 210, 231, 66);
		contentPane.add(btnLogar);
		btnLogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home.this.dispose();
				LoginUser.main(null);
			}
		});
		
		
		JButton btnCadastrarNovoUsurio = new JButton("Cadastrar novo usuário");
		btnCadastrarNovoUsurio.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCadastrarNovoUsurio.setBounds(416, 290, 231, 66);
		contentPane.add(btnCadastrarNovoUsurio);
		btnCadastrarNovoUsurio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home.this.dispose();
				CadPessoa.main(null);
			}
		});
		
		JButton btnSair = new JButton("Sair");
		btnSair.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnSair.setBounds(10, 517, 146, 40);
		contentPane.add(btnSair);
		
		JLabel lblNewLabel_1 = new JLabel("Numis Master");
		lblNewLabel_1.setFont(new Font("Yu Gothic Medium", Font.BOLD, 25));
		lblNewLabel_1.setBounds(443, 32, 246, 51);
		contentPane.add(lblNewLabel_1);
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home.this.dispose();
			}
		});
	}
}
