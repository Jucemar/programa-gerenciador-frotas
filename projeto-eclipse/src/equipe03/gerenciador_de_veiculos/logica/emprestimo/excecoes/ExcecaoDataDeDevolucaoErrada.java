package equipe03.gerenciador_de_veiculos.logica.emprestimo.excecoes;

public class ExcecaoDataDeDevolucaoErrada extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ExcecaoDataDeDevolucaoErrada() {
		super("Data de devolu��o menor que a data do empr�stimo");
	}

}
