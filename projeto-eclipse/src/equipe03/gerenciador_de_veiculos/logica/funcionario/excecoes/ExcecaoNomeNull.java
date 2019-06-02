package equipe03.gerenciador_de_veiculos.logica.funcionario.excecoes;

public class ExcecaoNomeNull extends Exception{

	private static final long serialVersionUID = 1L;

	public ExcecaoNomeNull(){
		super("O nome do funcionário não foi informado!");
	}

}
