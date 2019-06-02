package equipe03.gerenciador_de_veiculos.logica.veiculo.excecoes;

public class ExcecaoMarcaInvalida extends Exception {
	

	private static final long serialVersionUID = 1L;

	public ExcecaoMarcaInvalida() {
		super("Marca de veículo inválida!");
	}

}
