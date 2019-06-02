package equipe03.gerenciador_de_veiculos.logica.funcionario.excecoes;

public class ExcecaoDataDeNascimentoInvalida extends Exception{
	

	private static final long serialVersionUID = 1L;

	public ExcecaoDataDeNascimentoInvalida() {
		super("Data de nascimento inv�lida!\n" +
			  "O Funcion�rio deve ter idade \n" +
				"acima de 18 anos. ");
	}

}
