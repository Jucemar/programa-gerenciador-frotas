package equipe03.gerenciador_de_veiculos.logica.gerenciadordearquivos.excecoes;

public class ExcecaoFormatoInvalido extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExcecaoFormatoInvalido() {
		super("Arquivo com formato incompatível!");
	}

}
