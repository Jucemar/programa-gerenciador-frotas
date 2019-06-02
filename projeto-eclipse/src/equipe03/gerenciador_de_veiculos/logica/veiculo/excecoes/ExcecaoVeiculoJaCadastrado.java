package equipe03.gerenciador_de_veiculos.logica.veiculo.excecoes;

public class ExcecaoVeiculoJaCadastrado extends Exception {
	

	private static final long serialVersionUID = 1L;

	public ExcecaoVeiculoJaCadastrado() {
		super("Veiculo já cadastrado!");
	}

}
