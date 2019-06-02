package equipe03.gerenciador_de_veiculos.logica.veiculo.excecoes;

public class ExcecaoVeiculoNaoCadastrado extends Exception {
	

	private static final long serialVersionUID = 1L;

	public ExcecaoVeiculoNaoCadastrado() {
		super("Veículo não cadastrado!");
	}

}
