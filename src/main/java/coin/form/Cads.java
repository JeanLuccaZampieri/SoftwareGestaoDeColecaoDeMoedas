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

public class Cads extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cads frame = new Cads();
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
	public Cads() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1070, 604);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblMenu = new JLabel("Cadastros");
		lblMenu.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblMenu.setBounds(491, 10, 101, 28);
		getContentPane().add(lblMenu);
		
		JButton btnCadMoeda = new JButton("Manter moeda");
		btnCadMoeda.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnCadMoeda.setBounds(73, 107, 207, 66);
		getContentPane().add(btnCadMoeda);
		btnCadMoeda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Colecao colecao = new Colecao();
				colecao.setVisible(false);
				Cads.this.dispose();
				ManterMoeda.main(null);
			}
		});
		
		JButton btnCadastrarPessoa = new JButton("Manter pessoa");
		btnCadastrarPessoa.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnCadastrarPessoa.setBounds(449, 306, 207, 66);
		getContentPane().add(btnCadastrarPessoa);
		btnCadastrarPessoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManterUser.main(null);
				Cads.this.dispose();
				
			}
		});
		
		JButton btnCadastrarPais = new JButton("Manter país");
		btnCadastrarPais.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnCadastrarPais.setBounds(73, 306, 207, 66);
		getContentPane().add(btnCadastrarPais);
		btnCadastrarPais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cads.this.dispose();
				ManterPais.main(null);
			}
		});
		
		
		JButton btnCadastrarBorda = new JButton("Manter borda");
		btnCadastrarBorda.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnCadastrarBorda.setBounds(779, 107, 207, 66);
		getContentPane().add(btnCadastrarBorda);
		btnCadastrarBorda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cads.this.dispose();
				ManterBorda.main(null);
			}
		});
		
		JButton btnCadastrarMaterial = new JButton("Manter Material");
		btnCadastrarMaterial.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnCadastrarMaterial.setBounds(449, 107, 207, 66);
		getContentPane().add(btnCadastrarMaterial);
		btnCadastrarMaterial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cads.this.dispose();
				ManterMaterial.main(null);
			}
		});
		
		JButton btnSair = new JButton("Sair");
		btnSair.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnSair.setBounds(10, 492, 207, 66);
		getContentPane().add(btnSair);
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cads.this.dispose();
			}
		});
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnVoltar.setBounds(233, 492, 207, 66);
		getContentPane().add(btnVoltar);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cads.this.dispose();
				Menu.main(null);
			}
		});
		
	}

}
