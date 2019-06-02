package equipe03.gerenciador_de_veiculos.logica.funcionario.excecoes;

public class ExcecaoFuncionarioNaoCadastrado extends Exception {


	private static final long serialVersionUID = 1L;

	public ExcecaoFuncionarioNaoCadastrado() {
		super("Funcionário não cadastrado!");
	}

}
