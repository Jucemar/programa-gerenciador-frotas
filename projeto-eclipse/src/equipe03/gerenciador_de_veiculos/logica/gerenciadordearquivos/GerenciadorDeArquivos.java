package equipe03.gerenciador_de_veiculos.logica.gerenciadordearquivos;





import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import equipe03.gerenciador_de_veiculos.logica.emprestimo.Emprestimos;
import equipe03.gerenciador_de_veiculos.logica.funcionario.Funcionarios;
import equipe03.gerenciador_de_veiculos.logica.gerenciadordearquivos.excecoes.ExcecaoFormatoInvalido;
import equipe03.gerenciador_de_veiculos.logica.veiculo.Veiculos;

public class GerenciadorDeArquivos {
	Funcionarios listaDeFuncionarios;
	Veiculos listaDeVeiculos;
	Emprestimos listaDeEmprestimos;

	public GerenciadorDeArquivos() {

	}

	public void salveBaseDeDadosEmArquivo(File arquivo) throws IOException {

		ObjectOutputStream outputStream = null;

		try {
			outputStream = new ObjectOutputStream(new BufferedOutputStream(
					new FileOutputStream(arquivo)));

			outputStream.writeObject(listaDeFuncionarios);
			outputStream.writeObject(listaDeVeiculos);
			outputStream.writeObject(listaDeEmprestimos);

		} finally {
			if (outputStream != null)
				outputStream.close();
		}

	}

	public void leiaBaseDeDadosParaMemoria(File arquivo) throws IOException,ExcecaoFormatoInvalido {
		ObjectInputStream inputStream = null;

		try {
			inputStream = new ObjectInputStream(new BufferedInputStream(
					new FileInputStream(arquivo)));

			listaDeFuncionarios = (Funcionarios) inputStream.readObject();
			listaDeVeiculos = (Veiculos) inputStream.readObject();
			listaDeEmprestimos = (Emprestimos) inputStream.readObject();

		} catch (EOFException e) {

		} catch (ClassNotFoundException e) {
			throw new ExcecaoFormatoInvalido();
		}

		finally {
			if (inputStream != null)
				inputStream.close();
		}
	}
	
	

	public Funcionarios getListaDeFuncionarios() {
		return listaDeFuncionarios;
	}

	public Veiculos getListaDeVeiculos() {
		return listaDeVeiculos;
	}

	public Emprestimos getListaDeEmprestimos() {
		return listaDeEmprestimos;
	}

	public void setListaDeFuncionarios(Funcionarios listaDeFuncionarios) {
		this.listaDeFuncionarios = listaDeFuncionarios;
	}

	public void setListaDeVeiculos(Veiculos listaDeVeiculos) {
		this.listaDeVeiculos = listaDeVeiculos;
	}

	public void setListaDeEmprestimos(Emprestimos listaDeEmprestimos) {
		this.listaDeEmprestimos = listaDeEmprestimos;
	}

}
