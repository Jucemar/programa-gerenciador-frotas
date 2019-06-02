package equipe03.gerenciador_de_veiculos.logica.funcionario.excecoes;

public class ExcecaoCargoInvalido extends Exception{
	

	private static final long serialVersionUID = 1L;

	public ExcecaoCargoInvalido() {
		super("Cargo inválido!");
	}

}
