package equipe03.gerenciador_de_veiculos.logica.gerenciadordearquivos.excecoes;

public class ExcecaoArquivoJaExiste extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ExcecaoArquivoJaExiste(String nomeDoArquivo) {
		super(nomeDoArquivo);
	}

}
