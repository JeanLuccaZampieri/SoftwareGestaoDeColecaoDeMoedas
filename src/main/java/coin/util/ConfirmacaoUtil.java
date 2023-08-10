package coin.util;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ConfirmacaoUtil {

	public static boolean mostrarPopUpConfirmacao() {
		JFrame frame = new JFrame("Exemplo de Pop-up");

		// Exibir um pop-up com opção de "Sim" ou "Não"
		int result = JOptionPane.showConfirmDialog(frame, "Você deseja continuar?", "Confirmação",
				JOptionPane.YES_NO_OPTION);
		// Verificar a opção selecionada
		if (result == JOptionPane.YES_OPTION) {
			System.out.println("Opção selecionada: Sim");
			return true;
		} else if (result == JOptionPane.NO_OPTION) {
			System.out.println("Opção selecionada: Não");
			return false;
		} else if (result == JOptionPane.CLOSED_OPTION) {
			return false;

		}

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 200);
		frame.setVisible(true);
		return false;
	}
	public static boolean mostrarPopUpConfirmacaoCompra(String valor) {
		JFrame frame = new JFrame("Exemplo de Pop-up");
		// Exibir um pop-up com opção de "Sim" ou "Não"
		int result = JOptionPane.showConfirmDialog(frame, "Você deseja continuar?\nValor Total: R$" + valor, "Valor Total",
				JOptionPane.YES_NO_OPTION);

		// Verificar a opção selecionada
		if (result == JOptionPane.YES_OPTION) {
			System.out.println("Opção selecionada: Sim");
			return true;
		} else if (result == JOptionPane.NO_OPTION) {
			System.out.println("Opção selecionada: Não");
			return false;
		} else if (result == JOptionPane.CLOSED_OPTION) {
			return false;

		}
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 200);
		frame.setVisible(true);
		return false;
	}
	public static boolean mostrarPopUpConfirmacaoRmvUser() {
		JFrame frame = new JFrame("Exemplo de Pop-up");

		// Exibir um pop-up com opção de "Sim" ou "Não"
		int result = JOptionPane.showConfirmDialog(frame, "Esse usuário tem coleções cadastradas no sistema, tem certeza que deseja remove-lo?", "Confirmação",
				JOptionPane.YES_NO_OPTION);
		// Verificar a opção selecionada
		if (result == JOptionPane.YES_OPTION) {
			System.out.println("Opção selecionada: Sim");
			return true;
		} else if (result == JOptionPane.NO_OPTION) {
			System.out.println("Opção selecionada: Não");
			return false;
		} else if (result == JOptionPane.CLOSED_OPTION) {
			return false;

		}

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 200);
		frame.setVisible(true);
		return false;
	}
	public static boolean mostrarPopUpConfirmacaoRmvMoeda() {
		JFrame frame = new JFrame("Exemplo de Pop-up");

		// Exibir um pop-up com opção de "Sim" ou "Não"
		int result = JOptionPane.showConfirmDialog(frame, "Essa moeda está em coleções, tem certeza que deseja remove-la?", "Confirmação",
				JOptionPane.YES_NO_OPTION);
		// Verificar a opção selecionada
		if (result == JOptionPane.YES_OPTION) {
			System.out.println("Opção selecionada: Sim");
			return true;
		} else if (result == JOptionPane.NO_OPTION) {
			System.out.println("Opção selecionada: Não");
			return false;
		} else if (result == JOptionPane.CLOSED_OPTION) {
			return false;

		}

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 200);
		frame.setVisible(true);
		return false;
	}
	public static boolean mostrarPopUpConfirmacaoRmvColecao() {
		JFrame frame = new JFrame("Exemplo de Pop-up");

		// Exibir um pop-up com opção de "Sim" ou "Não"
		int result = JOptionPane.showConfirmDialog(frame, "Essa coleção tem moedas cadastradas, tem certeza que deseja remove-la?", "Confirmação",
				JOptionPane.YES_NO_OPTION);
		// Verificar a opção selecionada
		if (result == JOptionPane.YES_OPTION) {
			System.out.println("Opção selecionada: Sim");
			return true;
		} else if (result == JOptionPane.NO_OPTION) {
			System.out.println("Opção selecionada: Não");
			return false;
		} else if (result == JOptionPane.CLOSED_OPTION) {
			return false;

		}

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 200);
		frame.setVisible(true);
		return false;
	}

}