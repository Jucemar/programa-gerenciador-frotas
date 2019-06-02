package equipe03.gerenciador_de_veiculos.logica.emprestimo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import equipe03.gerenciador_de_veiculos.igu.utilitarios.FormatadorDeDatas;
import equipe03.gerenciador_de_veiculos.logica.emprestimo.excecoes.ExcecaoDataDeDevolucaoErrada;
import equipe03.gerenciador_de_veiculos.logica.emprestimo.excecoes.ExcecaoEmprestimoJaCadastrado;

public class Emprestimos implements Serializable{

	List<Emprestimo> emprestimos;
	
	private static final long serialVersionUID = 1L;

	public Emprestimos() {
		emprestimos=new ArrayList<Emprestimo>();
		
	}
	
	public void registrarNovoEmprestimo(Emprestimo novoEmprestimo)throws ExcecaoEmprestimoJaCadastrado {
		if (emprestimoJaEfetuado(novoEmprestimo) == true) {
			throw new ExcecaoEmprestimoJaCadastrado();
		} else {
			emprestimos.add(novoEmprestimo);
		}
	}

	
	private boolean emprestimoJaEfetuado(Emprestimo novoEmprestimo) {
		boolean emprestimoJaEfetuado = false;
		int i = 0;
		while (emprestimoJaEfetuado == false && i < emprestimos.size()) {

			if (emprestimos.get(i).getFuncionario().getRegistro() == novoEmprestimo.getFuncionario().getRegistro()
					&&
					emprestimos.get(i).getData_emprestimo() == novoEmprestimo.getData_emprestimo()) 
					{
				emprestimoJaEfetuado = true;
			}
			i++;
		}
		return false;
	}
	

	public int getSize(){
		return emprestimos.size();
		
	}

	public List<Emprestimo> pesquisaEmprestimoPorData(String data) {
		List<Emprestimo> resultadoPesquisa=new ArrayList<Emprestimo>(); 
		String dataDoEmprestimo;
		for(Emprestimo emprestimo:emprestimos){
			
			dataDoEmprestimo=FormatadorDeDatas.formateDataDeNascimentoParaString(emprestimo.getData_emprestimo());
			if(dataDoEmprestimo.equalsIgnoreCase(data))
				resultadoPesquisa.add(emprestimo);				
			}
		
		return resultadoPesquisa;		
	}
	
	public int retornaPosicaoDoEmprestimo(Emprestimo devolucao){
		int posicao = -1;
		int i = 0;
		while (posicao == -1 && i < emprestimos.size()) {

			if (emprestimos.get(i).getFuncionario().getRegistro() == devolucao.getFuncionario().getRegistro()
				&& emprestimos.get(i).getVeiculo().getPlaca().equalsIgnoreCase(devolucao.getVeiculo().getPlaca())){
				
				posicao=i;
				}
			i++;
		}
		return posicao;
	}
	
		
	
	public void removeDevolucaoEfetuada(int posicao){
		this.emprestimos.remove(posicao);
		
	}

	public void confirmaDevolucao(int posicao, Date dataDevolucao) throws ExcecaoDataDeDevolucaoErrada   {
		this.emprestimos.get(posicao).setData_devolucao(dataDevolucao);	
		
	}
	
	public Emprestimo getEmprestimo(int posicao){
		
		return emprestimos.get(posicao);
		
	}
	
	public List<Emprestimo> retorneEmprestimosPendentes(){
		List<Emprestimo> pendencias=new ArrayList<Emprestimo>();
		for(Emprestimo emprestimo:emprestimos){
			if(emprestimo.getData_devolucao()==null){
				pendencias.add(emprestimo);
			}
		}	
		
		return pendencias;
		
	}
	

}
