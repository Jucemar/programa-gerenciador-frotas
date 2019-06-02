package equipe03.gerenciador_de_veiculos.logica.veiculo.excecoes;

public class ExcecaoTipoInvalido extends Exception {
	

	private static final long serialVersionUID = 1L;

	public ExcecaoTipoInvalido() {
		super("Tipo de veículo inválido!");
	}

}
