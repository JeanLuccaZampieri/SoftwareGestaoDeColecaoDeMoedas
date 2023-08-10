package coin.util;

import java.text.ParseException;

import javax.swing.text.MaskFormatter;

public abstract class MascaraUtil {

	/* * * * * * * * * * * * * * * * * * * * * * * 
	 * 
	 * ? - Qualquer letra
	 * U - Letras maiusculas
	 * L - Letras minusculas
	 * A - Letras e numeros
	 * # - Numero
	 * * - Qualquer caracter
	 * 
	 * * * * * * * * * * * * * * * * * * * * * * */

	private static final String MASCARA_CPF = "###.###.###-##";
	private static final String MASCARA_TELEFONE = "(##)#####-####";
	private static final String MASCARA_ANO = "####";
	private static final String MASCARA_NOME = "*************************************************************************"
			+ "*********************************************************************************************************************";
	private static final String MASCARA_VALOR = "***********";
		
	public static MaskFormatter getMascaraCpf() {
		MaskFormatter maskFormmater = null;
		try {
			maskFormmater = new MaskFormatter(MASCARA_CPF);
		} catch (ParseException e1) {
			System.out.println("ERRO: ocorreu um erro ao criar a mascara da cpf");
		}
		return maskFormmater;
	}
	public static MaskFormatter getMascaraAno() {
		MaskFormatter maskFormmater = null;
		try {
			maskFormmater = new MaskFormatter(MASCARA_ANO);
		} catch (ParseException e1) {
			System.out.println("ERRO: ocorreu um erro ao criar a máscara do ano");
		}
		return maskFormmater;
	}
	public static MaskFormatter getMascaraTelefone() {
		MaskFormatter maskFormmater = null;
		try {
			maskFormmater = new MaskFormatter(MASCARA_TELEFONE);
		} catch (ParseException e1) {
			System.out.println("ERRO: ocorreu um erro ao criar a mascara da telefone");
		}
		return maskFormmater;
	}
	public static MaskFormatter getMascaraNome() {
		MaskFormatter maskFormmater = null;
		try {
			maskFormmater = new MaskFormatter(MASCARA_NOME);
		} catch (ParseException e1) {
			System.out.println("ERRO: ocorreu um erro ao criar a mascara da nome");
		}
		return maskFormmater;
	}
	
	public static MaskFormatter getMascaraValor() {
		MaskFormatter maskFormmater = null;
		try {
			maskFormmater = new MaskFormatter(MASCARA_VALOR);
		} catch (ParseException e1) {
			System.out.println("ERRO: ocorreu um erro ao criar a mascara da valor");
		}
		return maskFormmater;
	}
	
}
