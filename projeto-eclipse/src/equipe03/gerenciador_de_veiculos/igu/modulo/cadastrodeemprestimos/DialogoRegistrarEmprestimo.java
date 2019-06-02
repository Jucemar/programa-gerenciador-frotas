package equipe03.gerenciador_de_veiculos.igu.modulo.cadastrodeemprestimos;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;

import equipe03.gerenciador_de_veiculos.igu.JanelaPrincipal;
import equipe03.gerenciador_de_veiculos.igu.modulo.cadastrodeemprestimos.excecoes.ExcecaoNaoHaFuncionariosDisponiveis;
import equipe03.gerenciador_de_veiculos.igu.modulo.cadastrodeemprestimos.excecoes.ExcecaoNaoHaVeiculosDisponiveis;
import equipe03.gerenciador_de_veiculos.igu.utilitarios.FormatadorDeDatas;
import equipe03.gerenciador_de_veiculos.logica.emprestimo.Emprestimo;
import equipe03.gerenciador_de_veiculos.logica.emprestimo.Emprestimos;
import equipe03.gerenciador_de_veiculos.logica.emprestimo.excecoes.ExcecaoDataDeEmprestimoIvalida;
import equipe03.gerenciador_de_veiculos.logica.emprestimo.excecoes.ExcecaoDataDePrevisaoInvalida;
import equipe03.gerenciador_de_veiculos.logica.emprestimo.excecoes.ExcecaoEmprestimoJaCadastrado;
import equipe03.gerenciador_de_veiculos.logica.funcionario.Funcionarios;
import equipe03.gerenciador_de_veiculos.logica.veiculo.Veiculos;

public class DialogoRegistrarEmprestimo extends JDialog implements ActionListener{

	Funcionarios listaDeFuncionarios;
	Veiculos listaDeVeiculos;
	Emprestimos listaDeEmprestimos;
	JanelaPrincipal janelaPricipal;
	
	PainelRegistrarNovoEmprestimo painelRegistroDeEmprestimos;
	JButton botao_salvar;
	JButton botao_cancelar;
	JButton botao_limpar;
	

	private static final long serialVersionUID = 1L;
	
	public DialogoRegistrarEmprestimo(JanelaPrincipal igu) {
		super(igu, "Registro de Empréstimos", true);
		janelaPricipal=igu;
		listaDeFuncionarios=igu.getListaDeFuncionarios();
		listaDeVeiculos=igu.getListaDeVeiculos();
		listaDeEmprestimos=igu.getListaDeEmprestimos();
		janelaPricipal=igu;
		definaComponentes();
		posicionaComponentes();		
		setResizable(false);
		pack();
		setLocationRelativeTo(igu);
		setVisible(true);
		
	}
	
	
	private void definaComponentes() {
		
		painelRegistroDeEmprestimos=new PainelRegistrarNovoEmprestimo(this);
		botao_salvar = new JButton("Salvar");
		botao_salvar.setActionCommand(OpcoesEmprestimo.SALVAR_EMPRESTIMO.name());
		botao_salvar.addActionListener(this);

		botao_cancelar = new JButton("Cancelar");
		botao_cancelar.setActionCommand(OpcoesEmprestimo.CANCELAR_JANELA.name());
		botao_cancelar.addActionListener(this);

		botao_limpar = new JButton("Limpar");
		botao_limpar.setActionCommand(OpcoesEmprestimo.LIMPAR_CAMPOS.name());
		botao_limpar.addActionListener(this);
		
		
		
	}

	private void posicionaComponentes() {
		Container dialogo = getContentPane();
		GroupLayout layout = new GroupLayout(dialogo);
		dialogo.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.linkSize(SwingConstants.HORIZONTAL, botao_limpar, botao_salvar,botao_cancelar);


		GroupLayout.SequentialGroup horizontal = layout.createSequentialGroup();

		horizontal.addGroup(layout
				.createParallelGroup(Alignment.CENTER)
				.addComponent(painelRegistroDeEmprestimos)
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(botao_salvar)
								.addComponent(botao_limpar)
								.addComponent(botao_cancelar)));
				
		layout.setHorizontalGroup(horizontal);

		GroupLayout.SequentialGroup vertical = layout.createSequentialGroup();

		vertical.addGroup(layout
				.createSequentialGroup()
				.addComponent(painelRegistroDeEmprestimos)
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.BASELINE)
								.addComponent(botao_salvar)
								.addComponent(botao_limpar)
								.addComponent(botao_cancelar)));

		layout.setVerticalGroup(vertical);
		
	}





	@Override
	public void actionPerformed(ActionEvent e) {
		OpcoesEmprestimo opcao = OpcoesEmprestimo.valueOf(e.getActionCommand());
		switch (opcao) {
		case SALVAR_EMPRESTIMO:
			registrarNovoEmprestimo();
			
			break;
		case LIMPAR_CAMPOS:
			limparCampos();
			
			break;
		case CANCELAR_JANELA:
			fecharJanela();
			
			break;
	}
	}


	private void fecharJanela() {
		sincronizaListas();
		dispose();

	}


	private void registrarNovoEmprestimo() {
		Emprestimo novoEmprestimo = valideDadosNovoEmprestimo();
		if (novoEmprestimo != null) {

			try {
				listaDeEmprestimos.registrarNovoEmprestimo(novoEmprestimo);
				JOptionPane.showMessageDialog(getContentPane(),"Emprestimo efetuado com sucesso! ","Sucesso - Cadastro de Empréstimos",JOptionPane.INFORMATION_MESSAGE);
			} catch (ExcecaoEmprestimoJaCadastrado e) {
				JOptionPane.showMessageDialog(getContentPane(), e.getMessage(),
						"Erro - Duplicidade", JOptionPane.ERROR_MESSAGE);
			}

			painelRegistroDeEmprestimos.atualizeComboBoxFuncionarios();
			painelRegistroDeEmprestimos.atualizeComboBoxVeiculos();
			mudaDisponibilidadeDoFuncionario(novoEmprestimo,false);
			mudaDisponibilidadeDoVeiculo(novoEmprestimo,false);
			sincronizaListas();
			limparCampoPrevisaoDevolucao();
		}

	}


	private void sincronizaListas() {
		janelaPricipal.setListaDeEmprestimos(listaDeEmprestimos);
		janelaPricipal.setListaDeFuncionarios(listaDeFuncionarios);
		janelaPricipal.setListaDeVeiculos(listaDeVeiculos);
		
	}


	private void mudaDisponibilidadeDoVeiculo(Emprestimo novoEmprestimo,boolean disponibilidade) {
		int i=0;
		String placa=novoEmprestimo.getVeiculo().getPlaca();
		boolean alteracaoFeita=false;
		while(i<listaDeVeiculos.getSize()&&!alteracaoFeita){
			
			if(listaDeVeiculos.getVeiculo(i).getPlaca().equalsIgnoreCase(placa)){
				listaDeVeiculos.getVeiculo(i).setDisponibilidade(disponibilidade);
				alteracaoFeita=true;
			}
			i++;
		}
		
	}


	private void mudaDisponibilidadeDoFuncionario(Emprestimo novoEmprestimo, boolean disponibilidade) {
		int i=0;
		long registro=novoEmprestimo.getFuncionario().getRegistro();
		boolean alteracaoFeita=false;
		while(i<listaDeFuncionarios.getSize()&&!alteracaoFeita){
			
			if(listaDeFuncionarios.getFuncionario(i).getRegistro()==registro){
				listaDeFuncionarios.getFuncionario(i).setDisponibilidade(disponibilidade);
				alteracaoFeita=true;
			}
			i++;
		}
		
	}


	private void limparCampos() {
		painelRegistroDeEmprestimos.campo_dataDoEmprestimo.setText("");
		painelRegistroDeEmprestimos.campo_dataDaprevisaoDevolucao.setText("");
	}
	
	private void limparCampoPrevisaoDevolucao() {
		painelRegistroDeEmprestimos.campo_dataDaprevisaoDevolucao.setText("");
	}


	private Emprestimo valideDadosNovoEmprestimo() {
		String erro = " ";
		boolean validacao = true;
		Emprestimo emprestimoValidado=new Emprestimo();
		Emprestimo emprestimo=new Emprestimo();
		
		try {
			
			emprestimo.setFuncionario(painelRegistroDeEmprestimos.retorneFuncionarioSelecionado());
			
			emprestimo.setVeiculo(painelRegistroDeEmprestimos.retorneVeiculoSelecionado());
			
			if(painelRegistroDeEmprestimos.campo_dataDoEmprestimo.getText().length()==0){
				erro = "Campo data de devolução vazio.";
				validacao = false;
				}else{
					emprestimo.setData_emprestimo(FormatadorDeDatas.formateStringParaDataDeNascimento( painelRegistroDeEmprestimos.campo_dataDoEmprestimo.getText().trim()));
				}
			
			if(painelRegistroDeEmprestimos.campo_dataDaprevisaoDevolucao.getText().length()==0){
				erro = "Campo previsão de devolução vazio.";
				validacao = false;
				}else{
					emprestimo.setPrevisao_devolucao(FormatadorDeDatas.formateStringParaDataDeNascimento(painelRegistroDeEmprestimos.campo_dataDaprevisaoDevolucao.getText().trim()));
				}
				
			
			
			
		} catch (ExcecaoNaoHaFuncionariosDisponiveis e) {
			erro = e.getMessage();
			validacao = false;
			
		} catch (ExcecaoNaoHaVeiculosDisponiveis e) {
			erro = e.getMessage();
			validacao = false;		
			
		} catch (ExcecaoDataDeEmprestimoIvalida e) {
			erro = e.getMessage();
			validacao = false;
			
		} catch (ExcecaoDataDePrevisaoInvalida e) {
			erro = e.getMessage();
			validacao = false;;	

		} catch (ParseException e) {
			erro = "Formato de data iválido!\n"+
			  "O formato correto é dd/mm/aaaa ";
			validacao = false;
		}

		if (validacao == false) {
			JOptionPane.showMessageDialog(this, erro, "Erro",JOptionPane.ERROR_MESSAGE);
		}

		if (validacao == true) {
			emprestimoValidado = emprestimo;

		} else {
			emprestimoValidado = null;

		}

		return emprestimoValidado;
	}

}
