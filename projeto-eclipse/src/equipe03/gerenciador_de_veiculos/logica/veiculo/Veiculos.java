package equipe03.gerenciador_de_veiculos.logica.veiculo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import equipe03.gerenciador_de_veiculos.logica.veiculo.excecoes.ExcecaoAnoInvalido;
import equipe03.gerenciador_de_veiculos.logica.veiculo.excecoes.ExcecaoChassiInvalido;
import equipe03.gerenciador_de_veiculos.logica.veiculo.excecoes.ExcecaoDocumentoInvalido;
import equipe03.gerenciador_de_veiculos.logica.veiculo.excecoes.ExcecaoKmInvalida;
import equipe03.gerenciador_de_veiculos.logica.veiculo.excecoes.ExcecaoMarcaInvalida;
import equipe03.gerenciador_de_veiculos.logica.veiculo.excecoes.ExcecaoModeloInvalido;
import equipe03.gerenciador_de_veiculos.logica.veiculo.excecoes.ExcecaoTipoInvalido;
import equipe03.gerenciador_de_veiculos.logica.veiculo.excecoes.ExcecaoVeiculoEmUso;
import equipe03.gerenciador_de_veiculos.logica.veiculo.excecoes.ExcecaoVeiculoJaCadastrado;

public class Veiculos implements Serializable {
	
	List<Veiculo> veiculos;
	
	private static final long serialVersionUID = 1L;
	
	public Veiculos() {
		veiculos=new ArrayList<Veiculo>();
	}	
	
	
	public void cadastrarNovoVeiculo(Veiculo novoVeiculo)throws ExcecaoVeiculoJaCadastrado {
		if (veiculoJaExiste(novoVeiculo) == true) {
			throw new ExcecaoVeiculoJaCadastrado();
		} else {
			veiculos.add(novoVeiculo);
		}
	}

	public void editarDadosDoVeiculo(Veiculo alteracaoDoVeiculo) {
		
		int posicao = retornePosicaoDoVeiculo(alteracaoDoVeiculo);
		try {
			veiculos.get(posicao).setTipo(alteracaoDoVeiculo.getTipo());
			veiculos.get(posicao).setAno(alteracaoDoVeiculo.getAno());
			veiculos.get(posicao).setChassi(alteracaoDoVeiculo.getChassi());
			veiculos.get(posicao).setDocumento(alteracaoDoVeiculo.getDocumento());
			veiculos.get(posicao).setKm(alteracaoDoVeiculo.getKm());
			veiculos.get(posicao).setMarca(alteracaoDoVeiculo.getMarca());
			veiculos.get(posicao).setModelo(alteracaoDoVeiculo.getModelo());
		} catch (ExcecaoTipoInvalido e) {		
		} catch (ExcecaoAnoInvalido e) {			
		} catch (ExcecaoChassiInvalido e) {
		} catch (ExcecaoDocumentoInvalido e) {
		} catch (ExcecaoKmInvalida e) {
		} catch (ExcecaoMarcaInvalida e) {			
		} catch (ExcecaoModeloInvalido e) {
		}
	}

	private boolean veiculoJaExiste(Veiculo novoVeiculo) {
		boolean veiculoJaCadastrado = false;
		int i = 0;
		while (veiculoJaCadastrado == false && i < veiculos.size()) {

			if (veiculos.get(i).getPlaca().equalsIgnoreCase(novoVeiculo.getPlaca())) {
				veiculoJaCadastrado = true;
			}
			i++;
		}
		return veiculoJaCadastrado;
	}

	public void deletarVeiculo(Veiculo veiculoDescartado)throws ExcecaoVeiculoEmUso {

		int posicao = retornePosicaoDoVeiculo(veiculoDescartado);

		if (veiculos.get(posicao).getDisponibilidade() == true) {
			veiculos.remove(posicao);
		} else {
			throw new ExcecaoVeiculoEmUso();
		}

	}

	private int retornePosicaoDoVeiculo(Veiculo veiculo) {
		int i = 0;
		int posicao = -1;
		while (posicao == -1 && i < veiculos.size()) {
			if (veiculos.get(i).getPlaca().equalsIgnoreCase(veiculo.getPlaca())) {
				posicao = i;
			}
			i++;
		}
		return posicao;
	}

	public List<Veiculo> pesquisaVeiculoPelaPlaca(String placa) {
		List<Veiculo> resultadoPesquisa = new ArrayList<Veiculo>();
		String placaDoVeiculo;
		for (Veiculo veiculo: veiculos) {
			placaDoVeiculo = veiculo.getPlaca().toLowerCase();
			if (placaDoVeiculo.indexOf(placa.toLowerCase()) != -1) {
				resultadoPesquisa.add(veiculo);
			}
		}
		return resultadoPesquisa;
	}

	public List<Veiculo> pesquisaVeiculoPeloModelo(String modelo) {
		List<Veiculo> resultadoPesquisa = new ArrayList<Veiculo>();
		String nomeVeiculo;
		for (Veiculo veiculo: veiculos) {
			
			nomeVeiculo = veiculo.getModelo().toLowerCase();
			if (nomeVeiculo.indexOf(modelo.toLowerCase()) != -1) {
				resultadoPesquisa.add(veiculo);
			}
		}
		return resultadoPesquisa;
	}


	public List<Veiculo> retorneVeiculosDispoiveis() {
		List<Veiculo> veiculosDisponiveis=new ArrayList<Veiculo>();
		for(Veiculo veiculo:veiculos){
			if(veiculo.getDisponibilidade()){
				veiculosDisponiveis.add(veiculo);
			}
		}
		return veiculosDisponiveis;
	
	}
	
	public int getSize(){
		return veiculos.size();
		
	}


	public Veiculo getVeiculo(int posicao) {
		
		return veiculos.get(posicao);
	}


	public void disponiblizeVeiculo(Veiculo veiculo) {
		int posicao=retornePosicaoDoVeiculo(veiculo);
		veiculos.get(posicao).setDisponibilidade(true);
		
	}

}
