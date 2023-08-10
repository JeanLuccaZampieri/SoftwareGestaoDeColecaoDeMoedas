package coin.form;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import coin.entity.PessoaEntity;
import coin.service.PessoaService;

public class Menu extends JFrame {

	private JPanel contentPane;
	private Long PESSOA_ID = LoginUser.id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
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

	public Menu() {
		PessoaService pessoaService = new PessoaService();
		PessoaEntity pessoaEntity = new PessoaEntity();
		pessoaEntity = pessoaService.pesquisaId(PESSOA_ID);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1070, 604);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		if (pessoaEntity.getTipo().equals('A')) {
			JButton btnCadastros = new JButton("Cadastros");
			btnCadastros.setFont(new Font("Tahoma", Font.PLAIN, 19));
			btnCadastros.setBounds(423, 74, 207, 66);
			contentPane.add(btnCadastros);
			btnCadastros.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Menu.this.dispose();
					Cads.main(null);
				}
			});
		}

		JButton btnColecao = new JButton("Coleção");
		btnColecao.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnColecao.setBounds(423, 162, 207, 66);
		contentPane.add(btnColecao);
		btnColecao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu.this.dispose();
				Colecao colecao = new Colecao();
				colecao.setVisible(true);
			}
		});

		JLabel lblMenu = new JLabel("Menu");
		lblMenu.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblMenu.setBounds(486, 10, 112, 29);
		contentPane.add(lblMenu);

		JButton btnSair = new JButton("Sair");
		btnSair.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnSair.setBounds(10, 491, 207, 66);
		contentPane.add(btnSair);
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu.this.dispose();
			}
		});

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnVoltar.setBounds(234, 491, 207, 66);
		contentPane.add(btnVoltar);
		
		JButton btnComprar = new JButton("Comprar");
		btnComprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu.this.dispose();
				CompraMoedas.main(null);;
			}
		});
		btnComprar.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnComprar.setBounds(423, 251, 207, 66);
		contentPane.add(btnComprar);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu.this.dispose();
				LoginUser.main(null);
			}
		});
	}
}
