package equipe03.gerenciador_de_veiculos.logica.emprestimo.excecoes;

public class ExcecaoDataDePrevisaoInvalida extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ExcecaoDataDePrevisaoInvalida() {
		super("Data de previs�o de devolu��o\nanterior a data do emprestimo.");
	}
	
	
	

}
