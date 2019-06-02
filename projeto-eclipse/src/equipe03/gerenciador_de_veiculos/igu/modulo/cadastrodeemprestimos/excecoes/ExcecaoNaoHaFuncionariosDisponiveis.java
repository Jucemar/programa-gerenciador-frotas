package equipe03.gerenciador_de_veiculos.igu.modulo.cadastrodeemprestimos.excecoes;

public class ExcecaoNaoHaFuncionariosDisponiveis extends Exception {
	

	private static final long serialVersionUID = 1L;

	public ExcecaoNaoHaFuncionariosDisponiveis() {
		super("Não há funcionários Disponíveis\npara registrar um novo emprestimo.");
	}

}
