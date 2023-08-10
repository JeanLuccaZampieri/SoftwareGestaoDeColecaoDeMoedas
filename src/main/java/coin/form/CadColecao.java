	package coin.form;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
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

import coin.entity.ColecaoEntity;
import coin.entity.ColecaoMoedaEntity;
import coin.entity.PessoaEntity;
import coin.service.ColecaoMoedaService;
import coin.service.ColecaoService;
import coin.service.PessoaService;
import coin.util.ConfirmacaoUtil;

public class CadColecao extends JFrame {

	private JPanel contentPane;
	private JFormattedTextField txtColecao;
	private PessoaEntity pessoaEntity;
	private JScrollPane scrollPane;
	private JTable table;
	private Long idColecao;
	private ColecaoEntity colecaoEntity = new ColecaoEntity();

	/**
	 * Launch the application.
	 */
	private static PessoaService pessoaService = new PessoaService();
	private static ColecaoService colecaoService = new ColecaoService();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					CadColecao frame = new CadColecao();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public boolean validaDados() {
		pessoaEntity = pessoaService.pesquisaId(LoginUser.id);
		colecaoEntity.setId_pessoa(pessoaEntity);
		List<ColecaoEntity> colecaoNome = colecaoService.pesquisaNome(txtColecao.getText().trim());
		List<ColecaoEntity> colecaoPessoa = colecaoService.pesquisaPessoa(pessoaEntity);
		if(colecaoNome!= null) {
			for (ColecaoEntity colecao : colecaoNome) {
				if(colecaoPessoa != null) {
					for(ColecaoEntity colecao1 : colecaoPessoa) {
						System.out.println("nome:"+colecao.getNome());
						System.out.println("Pessoa:"+colecao1.getNome());
						if(colecao.getNome().equals(colecao1.getNome())){
							JOptionPane.showMessageDialog(null, "Uma coleção com esse nome já foi adicionada, por favor, tente outro");
							return false;
						}
					}
				}
			}
		}
		if(txtColecao.getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "Por favor, adicione algum nome para a coleção");
			return false;
		}
		if(txtColecao.getText().trim().length() < 3) {
			JOptionPane.showMessageDialog(null, "Por favor, adicione um nome maior para a coleção");
			return false;
		}

		colecaoEntity.setNome(txtColecao.getText().trim());
		
		return true;
	}

	public CadColecao() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1070, 604);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCadastrarColecao = new JLabel("Cadastrar Coleção");
		lblCadastrarColecao.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblCadastrarColecao.setBounds(417, 10, 174, 29);
		contentPane.add(lblCadastrarColecao);
		
		JLabel lblNome_1 = new JLabel("Nome");
		lblNome_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNome_1.setBounds(346, 62, 56, 21);
		contentPane.add(lblNome_1);
		
		txtColecao = new JFormattedTextField((AbstractFormatter) null);
		txtColecao.setColumns(10);
		txtColecao.setBounds(346, 83, 309, 28);
		contentPane.add(txtColecao);
		
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
        tableModel.addColumn("Nome");
        pessoaEntity = pessoaService.pesquisaId(LoginUser.id);
		colecaoEntity.setId_pessoa(pessoaEntity);
		if(colecaoService.pesquisaPessoa(pessoaEntity) != null) {
			List<ColecaoEntity> colecaoPessoa = colecaoService.pesquisaPessoa(pessoaEntity);
			for (ColecaoEntity colecao : colecaoPessoa) {
		        
	
				 Object[] rowData = {colecao.getId(), colecao.getNome()};
		            tableModel.addRow(rowData);
			}
		}
        
        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 121, 916, 395);
        getContentPane().add(scrollPane);
		
	    table = new JTable(tableModel);
	    scrollPane.setViewportView(table);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadColecao.this.dispose();
				Colecao.main(null);
			}
		});
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnVoltar.setBounds(10, 526, 110, 37);
		contentPane.add(btnVoltar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() >= 0) {
        		    Long valorColuna1 = (Long) table.getValueAt(table.getSelectedRow(), 0);
        		    ColecaoEntity colecaoEntity = new ColecaoEntity();
        		    colecaoEntity = colecaoService.pesquisaId(valorColuna1);
					ColecaoMoedaService colecaoMoedaService = new ColecaoMoedaService();
					List<ColecaoMoedaEntity> colecaoMoeda = null;
					colecaoMoeda = colecaoMoedaService.pesquisaPelaColecao(colecaoEntity);
					if (colecaoMoeda == null) {
						boolean confirmacao = ConfirmacaoUtil.mostrarPopUpConfirmacao();
						if (confirmacao == false) {
							return;
						}
						colecaoService.remover(valorColuna1);
						tableModel.removeRow(table.getSelectedRow());
						table.revalidate();
						table.repaint();
					}else {
						boolean confirmacaoRmv = ConfirmacaoUtil.mostrarPopUpConfirmacaoRmvColecao();
						if (confirmacaoRmv == false) {
							return;
						} else {
							for (ColecaoMoedaEntity colecaoRmv : colecaoMoeda) {
								colecaoService.remover(valorColuna1);
								tableModel.removeRow(table.getSelectedRow());
								table.revalidate();
								table.repaint();
								colecaoMoedaService.remover(colecaoRmv);
							}
						}
					}
        		}else {
        			JOptionPane.showMessageDialog(null, "Por favor, selecione uma opção");
        		}
			}
		});
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnExcluir.setBounds(932, 205, 110, 37);
		contentPane.add(btnExcluir);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnEditar.setBounds(932, 252, 110, 37);
		contentPane.add(btnEditar);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() >= 0) {
	        		   Long celula1 = (Long) table.getValueAt(table.getSelectedRow(), 0);
	        		   ColecaoEntity colecao = colecaoService.pesquisaId(celula1);
	        		   txtColecao.setText(colecao.getNome());
	        		   idColecao = colecao.getId();
	        	}else {
	        			JOptionPane.showMessageDialog(null, "Por favor, selecione uma opção");
	        	}
			}
		});
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnSalvar.setBounds(932, 299, 110, 37);
		contentPane.add(btnSalvar);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadColecao.this.dispose();
			}
		});
		btnSair.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnSair.setBounds(137, 526, 110, 37);
		contentPane.add(btnSair);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validaDados()) {
					boolean confirmacao = ConfirmacaoUtil.mostrarPopUpConfirmacao();
					if(confirmacao == false) {
						return;
					}
					if(idColecao != null) {
						colecaoEntity.setId(idColecao);
					}
					colecaoService.salvar(colecaoEntity);
					CadColecao.this.dispose();
					Colecao.main(null);
				}
			}
		});
	}
}
