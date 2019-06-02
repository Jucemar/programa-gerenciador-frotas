package equipe03.gerenciador_de_veiculos.logica.emprestimo.excecoes;

public class ExcecaoDataDeDevolucaoErrada extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ExcecaoDataDeDevolucaoErrada() {
		super("Data de devolução menor que a data do empréstimo");
	}

}
