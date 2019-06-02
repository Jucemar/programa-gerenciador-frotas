package equipe03.gerenciador_de_veiculos.logica.funcionario.excecoes;

public class ExcecaoNomeInvalido extends Exception{
	

	private static final long serialVersionUID = 1L;

	public ExcecaoNomeInvalido(){
		super("Nome Inv·lido!\n… necess·rio informar o nome e o sobrenome");
	}

}
