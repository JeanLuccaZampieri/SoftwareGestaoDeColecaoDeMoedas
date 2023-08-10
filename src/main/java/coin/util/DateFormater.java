package coin.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.google.protobuf.TextFormat.ParseException;

public class DateFormater {

	private static final String FORMATO_DATA = "yyyy/MM/dd";
	private static final String FORMATO_DATA_BR = "dd/MM/yyyy";

	public String formatarData(Date data) {
		SimpleDateFormat formatador = new SimpleDateFormat(FORMATO_DATA);
		return formatador.format(data);
	}

	public String formatarDataBR(Date data) {
		SimpleDateFormat formatador = new SimpleDateFormat(FORMATO_DATA_BR);
		return formatador.format(data);
	}

	public Date converterParaData(String dataStr) {
		SimpleDateFormat formatador = new SimpleDateFormat(FORMATO_DATA);
		char[] dataPartida = dataStr.toCharArray();
		String dia = "", mes = "", ano = "";
		for (int i = 0; i < dataPartida.length; i++) {
			if (i < 2) {
				dia += dataPartida[i];
			}
			if (i >= 2 && i < 6) {
				mes += dataPartida[i];
			}
			if (i >= 6) {
				ano += dataPartida[i];
			}

		}

		try {

			return formatador.parse(ano + mes + dia);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}