package coin.util;

import java.text.NumberFormat;
import java.util.Locale;

public class FormataValor {
	public static String getValue(Float valor) {
		if (valor == null)
			return "";
		NumberFormat numberFormat = NumberFormat.getNumberInstance(new Locale("pt", "BR"));
		return numberFormat.format(valor);
	}
	
	public static String getPercent(Float valor) {
		if (valor == null)
			return "";
		NumberFormat numberFormat = NumberFormat.getPercentInstance(new Locale("pt", "BR"));
		return numberFormat.format(valor);		
	}
	
	public static String getCurrency(Float valor) {
		if (valor == null)
			return "";
		NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		return numberFormat.format(valor);
	}
	
	public static String getCurrency(Double valor) {
		if (valor == null)
			return "";
		NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		return numberFormat.format(valor);
	}
}
