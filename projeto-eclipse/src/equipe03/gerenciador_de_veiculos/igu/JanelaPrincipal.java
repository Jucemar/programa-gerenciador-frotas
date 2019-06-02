package equipe03.gerenciador_de_veiculos.igu;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import equipe03.gerenciador_de_veiculos.igu.modulo.ajuda.DialogoSobre;
import equipe03.gerenciador_de_veiculos.igu.modulo.cadastrodedevolucoes.DialogoRegistrarDevolucao;
import equipe03.gerenciador_de_veiculos.igu.modulo.cadastrodeemprestimos.DialogoRegistrarEmprestimo;
import equipe03.gerenciador_de_veiculos.igu.modulo.cadastrodefuncionarios.DialogoCadastrarFuncionario;
import equipe03.gerenciador_de_veiculos.igu.modulo.cadastrodeveiculos.DialogoCadastrarVeiculo;
import equipe03.gerenciador_de_veiculos.igu.menu.BarraDeMenu;
import equipe03.gerenciador_de_veiculos.igu.menu.OpcoesBarraDeMenu;
import equipe03.gerenciador_de_veiculos.igu.paineis.PainelPrincipal;
import equipe03.gerenciador_de_veiculos.logica.emprestimo.Emprestimos;
import equipe03.gerenciador_de_veiculos.logica.funcionario.Funcionarios;
import equipe03.gerenciador_de_veiculos.logica.gerenciadordearquivos.GerenciadorDeArquivos;
import equipe03.gerenciador_de_veiculos.logica.gerenciadordearquivos.excecoes.ExcecaoFormatoInvalido;
import equipe03.gerenciador_de_veiculos.logica.veiculo.Veiculos;

public class JanelaPrincipal extends JFrame implements ActionListener {
	Funcionarios listaDeFuncionarios;
	Veiculos listaDeVeiculos;
	Emprestimos listaDeEmprestimos;
	File arquivoPadrao;
	PainelPrincipal painelPrincipal;


	
	
	private static final long serialVersionUID = 1L;

	public JanelaPrincipal(){
		super("Gerenciador de Veículos");	
		listaDeFuncionarios=new Funcionarios();
		listaDeVeiculos=new Veiculos();
		listaDeEmprestimos=new Emprestimos();
		setJMenuBar(new BarraDeMenu(this));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		painelPrincipal=new PainelPrincipal(this);
		add(painelPrincipal);
		setSize(new Dimension(700,500));
		setResizable(false);
		setLocationRelativeTo(null);
		definaLookAndFeel();
		
	}
	
	
	private void definaLookAndFeel() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
            

		} catch (Exception e) {
			
		}
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		OpcoesBarraDeMenu opcao = OpcoesBarraDeMenu.valueOf(e.getActionCommand());

		switch (opcao) {
		
		case CADASTRAR_VEICULO:			
			new DialogoCadastrarVeiculo(this);
			
			break;			
		case CADASTRAR_FUNCIONARIO:			
			new DialogoCadastrarFuncionario(this);
			
			break;
			
		case REGISTRAR_EMPRESTIMO:			
			new DialogoRegistrarEmprestimo(this);
			
			break;
			
		case REGISTRAR_DEVOLUCAO:			
			new DialogoRegistrarDevolucao(this);
			
			break;
			
		case SALVAR_COMO:			
			salveBaseEmArquivoAlterativo();
			
			break;
		case ABRIR:			
			leiaBaseDeArquivo();
			
			break;
			
		case SALVAR:			
			salveBaseEmArquivo();
			
			break;
			
		case SOBRE_PROGRAMA:			
			new DialogoSobre(this);
			
			break;
			
		case SAIR:
			encerreOPrograma();
			
			
		}

	}

	private void encerreOPrograma() {
		if (listaDeFuncionarios.getSize() > 0 || listaDeVeiculos.getSize() > 0
				|| listaDeEmprestimos.getSize() > 0) {

			salveBaseEmArquivo();
		}
		System.exit(0);

	}

	private void salveBaseEmArquivo() {

		if (arquivoPadrao == null) {
			JFileChooser jfc = new JFileChooser();

			if (jfc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
				arquivoPadrao = jfc.getSelectedFile();
			}

		}
		if (arquivoPadrao != null) {
			GerenciadorDeArquivos gerenciador = new GerenciadorDeArquivos();
			gerenciador.setListaDeFuncionarios(listaDeFuncionarios);
			gerenciador.setListaDeVeiculos(listaDeVeiculos);
			gerenciador.setListaDeEmprestimos(listaDeEmprestimos);
			try {
				gerenciador.salveBaseDeDadosEmArquivo(arquivoPadrao);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(getContentPane(), e.getMessage(),
						"Erro", JOptionPane.ERROR_MESSAGE);
			}

		}
		painelPrincipal.atualizaBarraDeEndereco();
	}

	private void leiaBaseDeArquivo() {
		JFileChooser jfc = new JFileChooser();

		if (jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			arquivoPadrao = jfc.getSelectedFile();

			GerenciadorDeArquivos gerenciador = new GerenciadorDeArquivos();
			try {
				gerenciador.leiaBaseDeDadosParaMemoria(arquivoPadrao);
				listaDeFuncionarios = gerenciador.getListaDeFuncionarios();
				listaDeVeiculos = gerenciador.getListaDeVeiculos();
				listaDeEmprestimos = gerenciador.getListaDeEmprestimos();

			} catch (IOException e) {
				
				JOptionPane.showMessageDialog(getContentPane(),e.getMessage(),"Erro", JOptionPane.ERROR_MESSAGE);
			} catch (ExcecaoFormatoInvalido e) {
				
				JOptionPane.showMessageDialog(getContentPane(),	e.getMessage(),"Erro - Incompatibilidade", JOptionPane.INFORMATION_MESSAGE);
			}

		}
		painelPrincipal.atualizaBarraDeEndereco();

	}

	private void salveBaseEmArquivoAlterativo() {

		JFileChooser jfc = new JFileChooser();

		if (jfc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
			File arquivo = jfc.getSelectedFile();
			if (arquivo.exists()) {
				JOptionPane.showMessageDialog(getContentPane(), "O arquivo "
						+ arquivo.getAbsolutePath() + " já existe.", "Erro",
						JOptionPane.ERROR_MESSAGE);
			} else {
				arquivoPadrao = arquivo;

				GerenciadorDeArquivos gerenciador = new GerenciadorDeArquivos();
				gerenciador.setListaDeFuncionarios(listaDeFuncionarios);
				gerenciador.setListaDeVeiculos(listaDeVeiculos);
				gerenciador.setListaDeEmprestimos(listaDeEmprestimos);
				try {
					gerenciador.salveBaseDeDadosEmArquivo(arquivoPadrao);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(getContentPane(),
							e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}

		}
		painelPrincipal.atualizaBarraDeEndereco();

	}

	public void interaja() {
		setVisible(true);
		
	}

	public Funcionarios getListaDeFuncionarios() {
		return listaDeFuncionarios;
	}

	public void setListaDeFuncionarios(Funcionarios listaDeFuncionarios) {
		this.listaDeFuncionarios = listaDeFuncionarios;
	}

	public Veiculos getListaDeVeiculos() {
		return listaDeVeiculos;
	}

	public void setListaDeVeiculos(Veiculos listaDeVeiculos) {
		this.listaDeVeiculos = listaDeVeiculos;
	}


	public Emprestimos getListaDeEmprestimos() {
		return listaDeEmprestimos;
	}


	public File getArquivoPadrao() {
		return arquivoPadrao;
	}


	public void setListaDeEmprestimos(Emprestimos listaDeEmprestimos) {
		this.listaDeEmprestimos = listaDeEmprestimos;
	}
	
	

}
