package equipe03.gerenciador_de_veiculos.logica.veiculo;

import java.io.Serializable;

import equipe03.gerenciador_de_veiculos.logica.veiculo.excecoes.ExcecaoAnoInvalido;
import equipe03.gerenciador_de_veiculos.logica.veiculo.excecoes.ExcecaoChassiInvalido;
import equipe03.gerenciador_de_veiculos.logica.veiculo.excecoes.ExcecaoDocumentoInvalido;
import equipe03.gerenciador_de_veiculos.logica.veiculo.excecoes.ExcecaoKmInvalida;
import equipe03.gerenciador_de_veiculos.logica.veiculo.excecoes.ExcecaoMarcaInvalida;
import equipe03.gerenciador_de_veiculos.logica.veiculo.excecoes.ExcecaoModeloInvalido;
import equipe03.gerenciador_de_veiculos.logica.veiculo.excecoes.ExcecaoPlacaInvalida;
import equipe03.gerenciador_de_veiculos.logica.veiculo.excecoes.ExcecaoPlacaNull;
import equipe03.gerenciador_de_veiculos.logica.veiculo.excecoes.ExcecaoTipoInvalido;


public class Veiculo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	String placa;
	String documento;
	String chassi;
	String tipo;
	String marca;
	String modelo;
	String ano;
	float km;
	boolean disponibilidade;
	
	
	public Veiculo (){
		disponibilidade=true;
		
	}

	
	
	public String getPlaca() {
		return placa;
	}

	
	
	public String getDocumento() {
		return documento;
	}

	
	
	public String getChassi() {
		return chassi;
	}

	
	
	public String getTipo() {
		return tipo;
	}

	
	
	public String getMarca() {
		return marca;
	}

	
	
	public String getModelo() {
		return modelo;
	}

	
	
	public String getAno() {
		return ano;
	}

	
	
	public float getKm() {
		return km;
	}

	
	
	public boolean getDisponibilidade() {
		return disponibilidade;
	}

	
	
	public void setPlaca(String placa) throws ExcecaoPlacaNull,ExcecaoPlacaInvalida {
		if (placa.length() == 0) {
			throw new ExcecaoPlacaNull();
		} else {
			if(placa.length()>7||placa.length()<7){
				throw new ExcecaoPlacaInvalida();
			}else{
				this.placa = placa;
			}
		}
	}

	
	
	public void setDocumento(String documento)throws ExcecaoDocumentoInvalido {
		if(documento.length()==0){
			throw new ExcecaoDocumentoInvalido();
		}else{
			this.documento = documento;
		}
	}

	
	
	public void setChassi(String chassi) throws ExcecaoChassiInvalido {
		if (chassi.length() == 0) {
			throw new ExcecaoChassiInvalido();
		} else {
			this.chassi = chassi;
		}
	}

	
	
	public void setTipo(String tipo) throws ExcecaoTipoInvalido{
		if(tipo.length()==0){
			throw new ExcecaoTipoInvalido();
		}else{
			this.tipo = tipo;
		}
	}

	
	
	public void setMarca(String marca)throws ExcecaoMarcaInvalida {
		if(marca.length()==0){
			throw new ExcecaoMarcaInvalida();
		}else{
			this.marca = marca;
		}
	}

	
	
	public void setModelo(String modelo)throws ExcecaoModeloInvalido {
		if(modelo.length()==0){
			throw new ExcecaoModeloInvalido();
		}else{
			this.modelo = modelo;
		}
	}

	
	
	public void setAno(String ano) throws ExcecaoAnoInvalido{
		if(ano.length()==0){
			throw new ExcecaoAnoInvalido();
		}else{
			this.ano = ano;
		}
	}

	
	
	public void setKm(float km) throws ExcecaoKmInvalida{
		if(km<0){
			throw new ExcecaoKmInvalida();
		}else{
			this.km = km;
		}
	}

	
	
	public void setDisponibilidade(boolean disponibilidade) {
		this.disponibilidade = disponibilidade;
	}
	
}
