package equipe03.gerenciador_de_veiculos.logica.veiculo.excecoes;

public class ExcecaoDocumentoInvalido extends Exception {


	private static final long serialVersionUID = 1L;
	
	public ExcecaoDocumentoInvalido() {
		super("N�mero de documento inv�lido!");
	}

}
