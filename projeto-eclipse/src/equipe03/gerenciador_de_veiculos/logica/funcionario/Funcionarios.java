package equipe03.gerenciador_de_veiculos.logica.funcionario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import equipe03.gerenciador_de_veiculos.logica.funcionario.excecoes.ExcecaoCargoInvalido;
import equipe03.gerenciador_de_veiculos.logica.funcionario.excecoes.ExcecaoDataDeNascimentoInvalida;
import equipe03.gerenciador_de_veiculos.logica.funcionario.excecoes.ExcecaoFuncionarioEmUso;
import equipe03.gerenciador_de_veiculos.logica.funcionario.excecoes.ExcecaoFuncionarioJaCadastrado;
import equipe03.gerenciador_de_veiculos.logica.funcionario.excecoes.ExcecaoNomeInvalido;
import equipe03.gerenciador_de_veiculos.logica.funcionario.excecoes.ExcecaoNomeNull;
import equipe03.gerenciador_de_veiculos.logica.funcionario.excecoes.ExcecaoRacaInvalida;
import equipe03.gerenciador_de_veiculos.logica.funcionario.excecoes.ExcecaoRegistroInvalido;
import equipe03.gerenciador_de_veiculos.logica.funcionario.excecoes.ExcecaoSexoInvalido;
import equipe03.gerenciador_de_veiculos.logica.funcionario.excecoes.ExcecaoTelefoneInvalido;

public class Funcionarios implements Serializable{

	List<Funcionario> funcionarios;
	
	private static final long serialVersionUID = 1L;

	public Funcionarios() {
		funcionarios = new ArrayList<Funcionario>();

	}

	public void cadastrarNovoFuncionario(Funcionario novoFuncionario)
			throws ExcecaoFuncionarioJaCadastrado {
		if (funcionarioJaExiste(novoFuncionario) == true) {
			throw new ExcecaoFuncionarioJaCadastrado();
		}
		funcionarios.add(novoFuncionario);
		
	}
	
	public void editarDadosDoFuncionario(Funcionario alteracaoDoFuncionario) {
		int posicao = retornePosicaoDoFuncionario(alteracaoDoFuncionario);

		try {

			funcionarios.get(posicao).setNome(alteracaoDoFuncionario.getNome());
			funcionarios.get(posicao).setRegistro(alteracaoDoFuncionario.getRegistro());
			funcionarios.get(posicao).setData_de_nascimento(alteracaoDoFuncionario.getData_de_nascimento());
			funcionarios.get(posicao).setSexo(alteracaoDoFuncionario.getSexo());
			funcionarios.get(posicao).setRaca(alteracaoDoFuncionario.getRaca());
			funcionarios.get(posicao).setTelefone(alteracaoDoFuncionario.getTelefone());
			funcionarios.get(posicao).setCargo(alteracaoDoFuncionario.getCargo());

		} catch (ExcecaoNomeNull e1) {
		} catch (ExcecaoNomeInvalido e1) {
		} catch (NumberFormatException e1) {
		} catch (ExcecaoRegistroInvalido e1) {
		} catch (ExcecaoDataDeNascimentoInvalida e1) {
		} catch (ExcecaoSexoInvalido e1) {
		} catch (ExcecaoRacaInvalida e1) {
		} catch (ExcecaoTelefoneInvalido e1) {
		} catch (ExcecaoCargoInvalido e1) {

		}
		/*
		 * Exceções já tratadas pelo método que valida os campos do formulário
		 */

	}

	private boolean funcionarioJaExiste(Funcionario novoFuncionario) {
		boolean funcionarioJaCadastrado = false;
		int i = 0;
		while (funcionarioJaCadastrado == false && i < funcionarios.size()) {

			if (funcionarios.get(i).getRegistro() == novoFuncionario
					.getRegistro()) {
				funcionarioJaCadastrado = true;
			}
			i++;
		}
		return funcionarioJaCadastrado;
	}

	public void deletarFuncionario(Funcionario funcionarioDemitido) throws ExcecaoFuncionarioEmUso{
		
		int posicao = retornePosicaoDoFuncionario(funcionarioDemitido);

		if(funcionarios.get(posicao).getDisponibilidade()==true){
			funcionarios.remove(posicao);	
		}else{
			throw new ExcecaoFuncionarioEmUso();
		}
		

	}

	private int retornePosicaoDoFuncionario(Funcionario funcionario) {
		int i = 0;
		int posicao = -1;
		while (posicao == -1 && i < funcionarios.size()) {
			if (funcionarios.get(i).getRegistro() == funcionario
					.getRegistro()) {
				posicao = i;
			}
			i++;
		}
		return posicao;
	}

	public List<Funcionario> pesquisaFuncionarioPeloRegistro(String registro){
		List<Funcionario> resultadoPesquisa=new ArrayList<Funcionario>(); 
		String registroDoFuncionario;
		for(Funcionario funcionario:funcionarios){
			registroDoFuncionario=Long.toString(funcionario.getRegistro());
			if(registroDoFuncionario.indexOf(registro)!=-1){
				resultadoPesquisa.add(funcionario);				
			}
		}		
		return resultadoPesquisa;		
	}
	
	public List<Funcionario> pesquisaFuncionarioPeloNome(String nome){
		List<Funcionario> resultadoPesquisa=new ArrayList<Funcionario>(); 
		String nomeFuncionario;
		for(Funcionario funcionario:funcionarios){
			nomeFuncionario=funcionario.getNome().toLowerCase();
			if(nomeFuncionario.indexOf(nome.toLowerCase())!=-1){
				resultadoPesquisa.add(funcionario);				
			}
		}		
		return resultadoPesquisa;		
	}

	public List<Funcionario> retorneFuncioariosDispoiveis() {
		List<Funcionario> funcionariosDisponiveis=new ArrayList<Funcionario>();
		for(Funcionario funcionario:funcionarios){
			if(funcionario.getDisponibilidade()){
				funcionariosDisponiveis.add(funcionario);
			}
		}
		return funcionariosDisponiveis;
	}
	
	public int getSize(){
		return funcionarios.size();
		
	}
	
	public Funcionario getFuncionario(int posicao){
		
		return funcionarios.get(posicao);
		
	}

	public void disponiblizeFuncionario(Funcionario funcionario) {
		int posicao=retornePosicaoDoFuncionario(funcionario);
		funcionarios.get(posicao).setDisponibilidade(true);
		
	}
	

}
