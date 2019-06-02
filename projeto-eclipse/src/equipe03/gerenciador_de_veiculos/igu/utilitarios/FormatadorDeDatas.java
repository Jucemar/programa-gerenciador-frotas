package equipe03.gerenciador_de_veiculos.igu.utilitarios;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatadorDeDatas {

	public static Date formateStringParaDataDeNascimento(String dataDeNascimento) throws ParseException {
		dataDeNascimento.trim();
		Date dataFormatada = new Date();
		SimpleDateFormat formatandoData = new SimpleDateFormat("dd/MM/yyyy");

			dataFormatada = formatandoData.parse(dataDeNascimento);


		return dataFormatada;
	}

	public static String formateDataDeNascimentoParaString(Date dataDeNascimento) {
		DateFormat formatandoData = new SimpleDateFormat("dd/MM/yyyy");
		String dataFormatada = formatandoData.format(dataDeNascimento);
		return dataFormatada;
	}

}
