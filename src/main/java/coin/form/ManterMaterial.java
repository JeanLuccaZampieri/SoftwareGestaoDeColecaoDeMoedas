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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import coin.entity.MaterialEntity;
import coin.entity.MaterialEntity;
import coin.entity.MaterialEntity;
import coin.service.MaterialService;
import coin.util.ConfirmacaoUtil;
import coin.util.MascaraUtil;

import javax.swing.JFormattedTextField;

public class ManterMaterial extends JFrame {

	private JPanel contentPane;
	private JFormattedTextField txtNome;
	private String nomeMaterial;
	private Long idMaterial;
	private JScrollPane scrollPane;
	private JTable table;
	/**
	 * Launch the application.
	 */
	private boolean validaDados() {
		nomeMaterial = txtNome.getText().toUpperCase().trim(); 
		MaterialEntity material = new MaterialEntity();
		material.setNome(nomeMaterial);
		if(nomeMaterial.isBlank()) {
			JOptionPane.showMessageDialog(null, "Por favor, informe o Material!");
			return false;
		}
		if(!materialService.validaNome(material)) {
			JOptionPane.showMessageDialog(null, "O Material já existe, por favor insira outro!");
			return false;
		}
		if(nomeMaterial.length()>50) {
			JOptionPane.showMessageDialog(null, "Por favor, insira um nome menor!");
			return false;
		}
		return true;
	}
	MaterialService materialService = new MaterialService();	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManterMaterial frame = new ManterMaterial();
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
	public ManterMaterial() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1070, 604);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCadastrarMaterial = new JLabel("Manter Material");
		lblCadastrarMaterial.setBounds(405, 10, 189, 23);
		lblCadastrarMaterial.setFont(new Font("Tahoma", Font.BOLD, 19));
		contentPane.add(lblCadastrarMaterial);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNome.setBounds(357, 55, 81, 28);
		contentPane.add(lblNome);
		
		txtNome = new JFormattedTextField(MascaraUtil.getMascaraNome());
		txtNome.setColumns(10);
		txtNome.setBounds(357, 83, 260, 28);
		contentPane.add(txtNome);
		
		JButton btnSair = new JButton("Sair");
		btnSair.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnSair.setBounds(10, 526, 110, 37);
		contentPane.add(btnSair);
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManterMaterial.this.dispose();
			}
		});
		
		
	
		
		final DefaultTableModel tableModel = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return String.class;
            }
            public boolean isCellEditable(int row, int column) {
                return false; // Torna todas as células não editáveis
            }
        };
        List<MaterialEntity> materiais = null;
        materiais = materialService.listar();
        
        tableModel.addColumn("Id");
        tableModel.addColumn("Nome");
		
		for (MaterialEntity material : materiais) {
			
			 Object[] rowData = {material.getId(),material.getNome()};
	            tableModel.addRow(rowData);
	}
        
        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 121, 916, 395);
        contentPane.add(scrollPane);
        
        table = new JTable(tableModel);
        scrollPane.setViewportView(table);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnSalvar.setBounds(936, 315, 110, 37);
		contentPane.add(btnSalvar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnExcluir.setBounds(936, 187, 110, 37);
		contentPane.add(btnExcluir);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() >= 0) {
					boolean confirmacao = ConfirmacaoUtil.mostrarPopUpConfirmacao();
					if(confirmacao == false) {
						return;
					}
        		    // Obtenha os valores das células na linha selecionada
        		    Long valorColuna1 = (Long) table.getValueAt(table.getSelectedRow(), 0);
        		    materialService.remover(valorColuna1);
        		    // Remova a linha do modelo de dados
        		    tableModel.removeRow(table.getSelectedRow());
        		    
        		    // Atualize a exibição da JTable
        		    table.revalidate();
        		    table.repaint();
        		}else {
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
	        		   MaterialEntity material = materialService.pesquisaId(celula1);
	        		   txtNome.setText(material.getNome().toString());
	        		   idMaterial = material.getId();
	        	}else {
	        			JOptionPane.showMessageDialog(null, "Por favor, selecione uma opção");
	        	}
			}
		});
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnLimpar.setBounds(936, 121, 110, 37);
		contentPane.add(btnLimpar);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManterMaterial.this.dispose();
				Cads.main(null);
			}
		});
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnVoltar.setBounds(443, 526, 110, 37);
		contentPane.add(btnVoltar);
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 txtNome.setText(null);
	     		 idMaterial = null;
			}
		});
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validaDados()) {
					boolean confirmacao = ConfirmacaoUtil.mostrarPopUpConfirmacao();
					if(confirmacao == false) {
						return;
					}
					MaterialEntity material = new MaterialEntity();
					material.setNome(nomeMaterial);
					if(idMaterial != null) {
						material.setId(idMaterial);
					}
					materialService.salvar(material);
					ManterMaterial.this.dispose();
					Cads.main(null);
				}
			}
		});
	}
}
