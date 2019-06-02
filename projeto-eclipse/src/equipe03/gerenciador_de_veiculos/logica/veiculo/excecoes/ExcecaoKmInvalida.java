package equipe03.gerenciador_de_veiculos.logica.veiculo.excecoes;

public class ExcecaoKmInvalida extends Exception {
	

	private static final long serialVersionUID = 1L;

	public ExcecaoKmInvalida() {
		super("Kilometragem do veículo não é inválida!");
	}

}
