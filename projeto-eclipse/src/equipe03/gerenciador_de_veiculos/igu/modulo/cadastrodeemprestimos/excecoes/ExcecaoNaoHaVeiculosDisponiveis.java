package equipe03.gerenciador_de_veiculos.igu.modulo.cadastrodeemprestimos.excecoes;

public class ExcecaoNaoHaVeiculosDisponiveis extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ExcecaoNaoHaVeiculosDisponiveis() {
		super("N�o h� mais ve�culos dispon�veis.");
	}

}
