package equipe03.gerenciador_de_veiculos.logica.funcionario.excecoes;

public class ExcecaoFuncionarioEmUso extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ExcecaoFuncionarioEmUso() {
		super("Exclus�o n�o permitida!\nFunci�rio possui pende�ncias");
	}

}
