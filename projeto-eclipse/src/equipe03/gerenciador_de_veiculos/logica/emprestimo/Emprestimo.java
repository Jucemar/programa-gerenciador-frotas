package equipe03.gerenciador_de_veiculos.logica.emprestimo;

import java.io.Serializable;
import java.util.Date;

import equipe03.gerenciador_de_veiculos.igu.utilitarios.FormatadorDeDatas;
import equipe03.gerenciador_de_veiculos.logica.emprestimo.excecoes.ExcecaoDataDeDevolucaoErrada;
import equipe03.gerenciador_de_veiculos.logica.emprestimo.excecoes.ExcecaoDataDeEmprestimoIvalida;
import equipe03.gerenciador_de_veiculos.logica.emprestimo.excecoes.ExcecaoDataDePrevisaoInvalida;
import equipe03.gerenciador_de_veiculos.logica.funcionario.Funcionario;
import equipe03.gerenciador_de_veiculos.logica.veiculo.Veiculo;

public class Emprestimo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	Funcionario funcionario;
	Veiculo veiculo;
	Date data_emprestimo;
	Date previsao_devolucao;
	Date data_devolucao;

	public Emprestimo() {
		data_devolucao=null;

	}

	public Funcionario getFuncionario(){
		return funcionario;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public Date getData_emprestimo() {
		return data_emprestimo;
	}

	public Date getPrevisao_devolucao() {
		return previsao_devolucao;
	}

	public Date getData_devolucao() {
		return data_devolucao;
	}

	public void setFuncionario(Funcionario funcionario) {

		this.funcionario = funcionario;

	}

	public void setVeiculo(Veiculo veiculo) {

		this.veiculo = veiculo;

	}

	public void setData_emprestimo(Date data_emprestimo)throws ExcecaoDataDeEmprestimoIvalida {
		Date dataDeHoje = new Date();

		String data_atual = FormatadorDeDatas.formateDataDeNascimentoParaString(dataDeHoje);
		String dataDoEmprestimo = FormatadorDeDatas.formateDataDeNascimentoParaString(data_emprestimo);
		if (data_atual.equalsIgnoreCase(dataDoEmprestimo) == false) {
			throw new ExcecaoDataDeEmprestimoIvalida();
		} else {
			this.data_emprestimo = data_emprestimo;
		}

	}

	public void setPrevisao_devolucao(Date previsao_devolucao)
			throws ExcecaoDataDePrevisaoInvalida {

		if (previsao_devolucao.compareTo(data_emprestimo) < 0) {
			throw new ExcecaoDataDePrevisaoInvalida();
		} else {
			this.previsao_devolucao = previsao_devolucao;
		}

	}

	public void setData_devolucao(Date data_devolucao)throws ExcecaoDataDeDevolucaoErrada {
		if (data_devolucao.compareTo(data_emprestimo) < 0) {
			throw new ExcecaoDataDeDevolucaoErrada();
		} else {
			this.data_devolucao = data_devolucao;
		}
	}

}