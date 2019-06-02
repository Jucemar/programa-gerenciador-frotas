package equipe03.gerenciador_de_veiculos.logica.veiculo.excecoes;

public class ExcecaoVeiculoEmUso extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ExcecaoVeiculoEmUso() {
		super("Exclusão não permitida!\nVeículo possui pendeências");
	}

}
