package equipe03.gerenciador_de_veiculos.logica.veiculo.excecoes;

public class ExcecaoPlacaNull extends Exception {


	private static final long serialVersionUID = 1L;
	
	public ExcecaoPlacaNull() {
		super("Placa inválida!");
	}

}
