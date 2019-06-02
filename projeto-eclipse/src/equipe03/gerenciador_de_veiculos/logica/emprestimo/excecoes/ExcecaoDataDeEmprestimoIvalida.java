package equipe03.gerenciador_de_veiculos.logica.emprestimo.excecoes;

public class ExcecaoDataDeEmprestimoIvalida extends Exception {

	private static final long serialVersionUID = 1L;

	public ExcecaoDataDeEmprestimoIvalida() {
		super("Data de emprestimo diferente da atual");
	}

}
