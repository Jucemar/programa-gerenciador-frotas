package equipe03.gerenciador_de_veiculos.logica.emprestimo.excecoes;

public class ExcecaoEmprestimoJaCadastrado extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ExcecaoEmprestimoJaCadastrado() {
		super("O funcionario já possui emprestimo efetuado neste dia");

	}

}
