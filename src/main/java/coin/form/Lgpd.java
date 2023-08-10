package coin.form;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Lgpd extends JFrame {

	private JPanel contentPane;
	private final Long PESSOA_ID = LoginUser.id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Lgpd frame = new Lgpd();
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
	public Lgpd() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1070, 604);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 66, 1023, 394);
		contentPane.add(scrollPane);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.append("Contrato de Tratamento de Dados Pessoais\r\n" + "\r\n"
				+ "Este Contrato de Tratamento de Dados Pessoais é celebrado entre Colnect e o usuário do software Numis Master no âmbito do tratamento de dados \n"
				+ "pessoais conforme estabelecido pela Lei Geral de Proteção de Dados (LGPD).\r\n" + "\r\n"
				+ "Definições\r\n"
				+ "1.1. Controlador: refere-se à empresa responsável pelo tratamento dos dados pessoais.\r\n" + "\r\n"
				+ "1.2. Usuário: refere-se à pessoa física que utiliza o software fornecido pela empresa Controlador.\r\n"
				+ "\r\n"
				+ "1.3. Dados Pessoais: refere-se a qualquer informação relacionada a uma pessoa física identificada ou identificável.\r\n"
				+ "\r\n" + "Objeto\r\n"
				+ "2.1. O objetivo deste Contrato é estabelecer os termos e condições para o tratamento dos dados pessoais do Usuário pelo Controlador, de acordo com as disposições da LGPD.\r\n"
				+ "\r\n" + "Obrigações do Controlador\r\n"
				+ "3.1. O Controlador compromete-se a tratar os dados pessoais do Usuário de forma lícita, transparente e de acordo com as finalidades especificadas neste Contrato.\r\n"
				+ "\r\n"
				+ "3.2. O Controlador garante a implementação de medidas técnicas e organizacionais adequadas para proteger os dados pessoais contra acesso não autorizado, perda, alteração, \n"
				+ "destruição ou divulgação não autorizada.\r\n" + "\r\n"
				+ "3.3. O Controlador somente utilizará os dados pessoais do Usuário para as finalidades estabelecidas neste Contrato, sem realizar qualquer tratamento adicional sem consentimento prévio.\r\n"
				+ "\r\n" + "Consentimento do Usuário\r\n"
				+ "4.1. Ao utilizar o software fornecido pelo Controlador, o Usuário fornece seu consentimento expresso para o tratamento dos seus dados pessoais de acordo com as finalidades estabelecidas \n"
				+ "neste Contrato.\r\n" + "\r\n"
				+ "4.2. O Usuário tem o direito de revogar o seu consentimento a qualquer momento, mediante solicitação por escrito ao Controlador.\r\n"
				+ "\r\n" + "Compartilhamento de Dados Pessoais\r\n"
				+ "5.1. O Controlador poderá compartilhar os dados pessoais do Usuário com terceiros apenas quando necessário para cumprir as finalidades estabelecidas neste Contrato, sempre em \n"
				+ "conformidade com a legislação aplicável.\r\n" + "\r\n" + "Direitos do Usuário\r\n"
				+ "6.1. O Usuário possui os direitos estabelecidos pela LGPD, incluindo o direito de acesso, retificação, exclusão, oposição e portabilidade dos seus dados pessoais.\r\n"
				+ "\r\n"
				+ "6.2. Para exercer esses direitos, o Usuário deverá enviar uma solicitação por escrito ao Controlador, que se compromete a responder e tomar as medidas apropriadas em conformidade com a \n"
				+ "legislação aplicável.\r\n" + "\r\n" + "Prazo de Armazenamento\r\n"
				+ "7.1. O Controlador manterá os dados pessoais do Usuário armazenados apenas pelo tempo necessário para cumprir as finalidades estabelecidas neste Contrato, a menos que seja exigido o\n"
				+ " armazenamento por um período mais longo em conformidade com a legislação aplicável.\r\n" + "\r\n"
				+ "Rescisão\r\n"
				+ "8.1. Este Contrato poderá ser rescindido a qualquer momento por qualquer uma das partes, mediante notificação por escrito.\r\n"
				+ "\r\n"
				+ "8.2. Em caso de rescisão, o Controlador compromete-se a tomar todas as medidas necessárias para cessar o tratamento dos dados pessoais do Usuário, conforme estabelecido pela\n"
				+ " legislação aplicável.\r\n" + "Disposições Gerais\r\n"
				+ "9.1. Este Contrato constitui o acordo integral entre as partes e prevalecerá sobre quaisquer acordos anteriores ou contemporâneos, escritos ou verbais, referentes ao\n"
				+ " tratamento dos dados pessoais do Usuário.\r\n" + "\r\n"
				+ "9.2. Quaisquer alterações ou modificações a este Contrato deverão ser feitas por escrito e assinadas por ambas as partes.\r\n"
				+ "\r\n"
				+ "9.3. Este Contrato será regido e interpretado de acordo com as leis do Brasil, sendo eleito o foro da cidade de Araucária para dirimir quaisquer controvérsias decorrentes deste Contrato.	");
		textArea.setBounds(171, 163, 791, 270);

		scrollPane.setViewportView(textArea);

		JButton btnProximo = new JButton("Não");
		btnProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (PESSOA_ID == null) {
					Lgpd.this.dispose();
					LoginUser.main(null);
				} else {
					Lgpd.this.dispose();
					Cads.main(null);
				}
			}
		});
		btnProximo.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnProximo.setBounds(211, 506, 162, 51);
		contentPane.add(btnProximo);

		JButton btnProximo_1 = new JButton("Sim");
		btnProximo_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (PESSOA_ID == null) {
					Lgpd.this.dispose();
					LoginUser.main(null);
				} else {
					Lgpd.this.dispose();
					Cads.main(null);
				}
			}
		});
		btnProximo_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnProximo_1.setBounds(23, 506, 162, 51);
		contentPane.add(btnProximo_1);

		JLabel lblTermosDeContrato = new JLabel("Termos e condições de uso");
		lblTermosDeContrato.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblTermosDeContrato.setBounds(358, 10, 289, 28);
		contentPane.add(lblTermosDeContrato);

		JLabel lblVocAceitaOs = new JLabel("Você aceita os termos?");
		lblVocAceitaOs.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblVocAceitaOs.setBounds(79, 472, 278, 28);
		contentPane.add(lblVocAceitaOs);

	}
}
