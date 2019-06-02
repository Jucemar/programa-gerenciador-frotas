package equipe03.gerenciador_de_veiculos.logica.veiculo.excecoes;

public class ExcecaoModeloInvalido extends Exception {
	

	private static final long serialVersionUID = 1L;

	public ExcecaoModeloInvalido() {
		super("Modelo de veículo inválido!");
	}

}
