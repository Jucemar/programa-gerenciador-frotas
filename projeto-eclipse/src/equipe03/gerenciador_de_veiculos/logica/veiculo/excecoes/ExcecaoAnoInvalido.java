package equipe03.gerenciador_de_veiculos.logica.veiculo.excecoes;

public class ExcecaoAnoInvalido extends Exception {
	

	private static final long serialVersionUID = 1L;

	public ExcecaoAnoInvalido() {
		super("O Ano do veículo não é válido!");
	}

}
