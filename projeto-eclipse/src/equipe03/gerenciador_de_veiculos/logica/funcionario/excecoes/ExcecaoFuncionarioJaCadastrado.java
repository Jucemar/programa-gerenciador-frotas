package equipe03.gerenciador_de_veiculos.logica.funcionario.excecoes;

public class ExcecaoFuncionarioJaCadastrado extends Exception {
	

	private static final long serialVersionUID = 1L;

	public ExcecaoFuncionarioJaCadastrado(){
		super("Funcionário já cadastrado !");
	}
}


