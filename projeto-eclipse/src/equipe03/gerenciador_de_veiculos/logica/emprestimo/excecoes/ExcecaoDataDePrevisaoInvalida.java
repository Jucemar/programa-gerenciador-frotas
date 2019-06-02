package equipe03.gerenciador_de_veiculos.logica.emprestimo.excecoes;

public class ExcecaoDataDePrevisaoInvalida extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ExcecaoDataDePrevisaoInvalida() {
		super("Data de previsão de devolução\nanterior a data do emprestimo.");
	}
	
	
	

}
