package equipe03.gerenciador_de_veiculos.logica.funcionario;

import java.io.Serializable;
import java.util.Date;

import equipe03.gerenciador_de_veiculos.logica.funcionario.excecoes.ExcecaoCargoInvalido;
import equipe03.gerenciador_de_veiculos.logica.funcionario.excecoes.ExcecaoDataDeNascimentoInvalida;
import equipe03.gerenciador_de_veiculos.logica.funcionario.excecoes.ExcecaoNomeInvalido;
import equipe03.gerenciador_de_veiculos.logica.funcionario.excecoes.ExcecaoNomeNull;
import equipe03.gerenciador_de_veiculos.logica.funcionario.excecoes.ExcecaoRacaInvalida;
import equipe03.gerenciador_de_veiculos.logica.funcionario.excecoes.ExcecaoRegistroInvalido;
import equipe03.gerenciador_de_veiculos.logica.funcionario.excecoes.ExcecaoSexoInvalido;
import equipe03.gerenciador_de_veiculos.logica.funcionario.excecoes.ExcecaoTelefoneInvalido;


public class Funcionario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	String nome;
	long registro;
	Date data_de_nascimento;
	String sexo;
	String raca;
	String telefone;
	String cargo;
	boolean disponibilidade;
	
	
	
	public Funcionario(){
		disponibilidade=true;
	}



	public String getNome() {
		return nome;
	}



	public long getRegistro() {
		return registro;
	}



	public Date getData_de_nascimento() {
		return data_de_nascimento;
	}



	public String getSexo() {
		return sexo;
	}



	public String getRaca() {
		return raca;
	}



	public String getTelefone() {
		return telefone;
	}



	public String getCargo() {
		return cargo;
	}



	public void setNome(String nome) throws ExcecaoNomeNull,ExcecaoNomeInvalido {
		nome.trim();
		if (nome.length() == 0) {
			throw new ExcecaoNomeNull();
		} else {
			if (nome.indexOf(" ") == -1) {
				throw new ExcecaoNomeInvalido();
			} else {
				this.nome = nome;
			}
		}
	}



	public void setRegistro(long registro) throws ExcecaoRegistroInvalido{
		if(registro==0||registro<0){
			throw new ExcecaoRegistroInvalido();
		}else{
			this.registro = registro;
		}
	}



	public void setData_de_nascimento(Date data_de_nascimento)throws ExcecaoDataDeNascimentoInvalida{
		@SuppressWarnings("deprecation")
		Date dataMinima=new Date("01/01/1992");
		
		if(data_de_nascimento.after(dataMinima)){
			throw new ExcecaoDataDeNascimentoInvalida(); 
		} else {
			this.data_de_nascimento = data_de_nascimento;
		}
	}



	public void setSexo(String sexo) throws ExcecaoSexoInvalido {
		if (sexo.equalsIgnoreCase(" ")) {
			throw new ExcecaoSexoInvalido();
		} else {
			this.sexo = sexo;
		}
	}


	
	public void setRaca(String raca) throws ExcecaoRacaInvalida {
		if (raca.equalsIgnoreCase(" ")) {
			throw new ExcecaoRacaInvalida();
		} else {
			this.raca = raca;
		}
	}



	public void setTelefone(String telefone) throws ExcecaoTelefoneInvalido {
		telefone.trim();
		if (telefone.length() < 10||telefone.length() > 10) {
			throw new ExcecaoTelefoneInvalido();
		} else {
			this.telefone = telefone;
		}
	}



	public void setCargo(String cargo) throws ExcecaoCargoInvalido {
		cargo.trim();
		if (cargo.length() == 0) {
			throw new ExcecaoCargoInvalido();
		} else {
			this.cargo = cargo;
		}
	}



	public boolean getDisponibilidade() {
		return disponibilidade;
	}



	public void setDisponibilidade(boolean disponibilidade) {
		this.disponibilidade = disponibilidade;
	}

}
	
	
	