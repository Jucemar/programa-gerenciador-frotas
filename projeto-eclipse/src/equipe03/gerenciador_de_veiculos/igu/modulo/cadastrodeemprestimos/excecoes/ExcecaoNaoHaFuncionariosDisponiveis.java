package equipe03.gerenciador_de_veiculos.igu.modulo.cadastrodeemprestimos.excecoes;

public class ExcecaoNaoHaFuncionariosDisponiveis extends Exception {
	

	private static final long serialVersionUID = 1L;

	public ExcecaoNaoHaFuncionariosDisponiveis() {
		super("N�o h� funcion�rios Dispon�veis\npara registrar um novo emprestimo.");
	}

}
