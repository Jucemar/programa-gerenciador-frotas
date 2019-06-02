package equipe03.gerenciador_de_veiculos.igu.modulo.cadastrodedevolucoes;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout.Alignment;

import equipe03.gerenciador_de_veiculos.igu.JanelaPrincipal;
import equipe03.gerenciador_de_veiculos.igu.utilitarios.FormatadorDeDatas;
import equipe03.gerenciador_de_veiculos.logica.emprestimo.Emprestimo;
import equipe03.gerenciador_de_veiculos.logica.emprestimo.Emprestimos;
import equipe03.gerenciador_de_veiculos.logica.emprestimo.excecoes.ExcecaoDataDeDevolucaoErrada;
import equipe03.gerenciador_de_veiculos.logica.funcionario.Funcionario;
import equipe03.gerenciador_de_veiculos.logica.funcionario.Funcionarios;
import equipe03.gerenciador_de_veiculos.logica.veiculo.Veiculo;
import equipe03.gerenciador_de_veiculos.logica.veiculo.Veiculos;

public class DialogoRegistrarDevolucao extends JDialog implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	Funcionarios listaDeFuncionarios;
	Veiculos listaDeVeiculos;
	Emprestimos listaDeEmprestimos;
	PainelPesquisaData painelPesquisaData;
	PainelDadosDoEmprestimo painelDadosDoEmprestimo;
	List<Emprestimo> resultadoDaBusca;
	JList campo_resultadoDeBusca;
	DefaultListModel modeloDosResultados;
	JanelaPrincipal janelaPrincipal;
	JButton botao_confirma;
	JButton botao_cancelar;
	JButton botao_limpar;
	CapturaDeEventosDaJList eventosDoCampo_resultadoDeBusca;

	
	public DialogoRegistrarDevolucao(JanelaPrincipal igu) {
		super(igu, "Registro de Devoluções", true);
		listaDeEmprestimos=igu.getListaDeEmprestimos();
		listaDeVeiculos=igu.getListaDeVeiculos();
		listaDeFuncionarios=igu.getListaDeFuncionarios();
		janelaPrincipal=igu;
		definaComponentes();
		posicionaComponentes();
		setResizable(false);
		pack();
		setLocationRelativeTo(igu);
		setVisible(true);
	}

	private void posicionaComponentes() {
		JScrollPane Campo_resultado_scroll = new JScrollPane(campo_resultadoDeBusca);
		Campo_resultado_scroll.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY), BorderFactory.createLineBorder(Color.white)));
		JPanel painel = new JPanel();
		GroupLayout layout = new GroupLayout(painel);
		painel.setLayout(layout);

		layout.setAutoCreateGaps(true);

		layout.setAutoCreateContainerGaps(true);

		GroupLayout.SequentialGroup horizontal = layout.createSequentialGroup();

		horizontal.addGroup(layout.createParallelGroup(Alignment.CENTER).addComponent(painelPesquisaData).addComponent(Campo_resultado_scroll)
				.addGroup(layout.createSequentialGroup().addComponent(botao_confirma).addComponent(botao_limpar).addComponent(botao_cancelar)))
								
				.addComponent(painelDadosDoEmprestimo);
		
		layout.setHorizontalGroup(horizontal);

		GroupLayout.SequentialGroup vertical = layout.createSequentialGroup();		
		
		vertical.addGroup(layout.createParallelGroup(Alignment.CENTER)
				.addGroup(layout.createSequentialGroup().addComponent(painelPesquisaData).addComponent(Campo_resultado_scroll)
						.addGroup(layout.createParallelGroup(Alignment.CENTER).addComponent(botao_confirma).addComponent(botao_limpar).addComponent(botao_cancelar)))
						.addComponent(painelDadosDoEmprestimo));		
		
		layout.setVerticalGroup(vertical);
		add(painel);		
		
	}

	private void definaComponentes() {
		painelPesquisaData=new PainelPesquisaData(this);
		painelDadosDoEmprestimo=new PainelDadosDoEmprestimo(this);
		
		
		
		modeloDosResultados=new DefaultListModel();
	
		eventosDoCampo_resultadoDeBusca=new CapturaDeEventosDaJList(this);
		
		campo_resultadoDeBusca=new JList();
		campo_resultadoDeBusca.setFixedCellHeight(42);
		campo_resultadoDeBusca.setFixedCellWidth(10);
		campo_resultadoDeBusca.addListSelectionListener(eventosDoCampo_resultadoDeBusca);
		

		
		botao_confirma=new JButton("Confirmar");
		botao_confirma.setActionCommand(OpcoesDevolucao.CONFIRMAR_DEVOLUCAO.name());
		botao_confirma.addActionListener(this);
		
		botao_cancelar=new JButton("Cancelar");
		botao_cancelar.setActionCommand(OpcoesDevolucao.CANCELAR_JANELA.name());
		botao_cancelar.addActionListener(this);
		
		botao_limpar=new JButton("Limpar");
		botao_limpar.setActionCommand(OpcoesDevolucao.LIMPAR_CAMPOS.name());
		botao_limpar.addActionListener(this);
		

		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		OpcoesDevolucao opcao = OpcoesDevolucao.valueOf(e.getActionCommand());
		
		switch (opcao) {
		
		case CONFIRMAR_DEVOLUCAO:
			registrarDevolucao();
			
			break;
		case LIMPAR_CAMPOS:
			limparCampos();
			
			break;
		case CANCELAR_JANELA:
			fecharJanela();
			
			break;
			}
	}

	private void registrarDevolucao() {
		
		if (painelDadosDoEmprestimo.dataDevolucaoVeiculo.getText().length()==0) {
			String erro = "Campo data de devolução vazio.";
			JOptionPane.showMessageDialog(this, erro, "Erro",JOptionPane.ERROR_MESSAGE);
			
		} else {
			
			int selecao = campo_resultadoDeBusca.getSelectedIndex();

			int posicaoNaListaDeEmprestimos = listaDeEmprestimos.retornaPosicaoDoEmprestimo(resultadoDaBusca.get(selecao));
			Date dataDevolucao = null;
			
			try {
				dataDevolucao = FormatadorDeDatas.formateStringParaDataDeNascimento(painelDadosDoEmprestimo.dataDevolucaoVeiculo.getText().trim());
				
			} catch (ParseException e) {
				
				String erro = "Formato de data iválido!\n"
						+ "O formato correto é dd/mm/aaaa ";
				
				JOptionPane.showMessageDialog(this, erro,"Atenção - Campo de Data", JOptionPane.WARNING_MESSAGE);
			}
			if (dataDevolucao != null) {

				if (posicaoNaListaDeEmprestimos != -1) {
					disponibilizaFuncionario(posicaoNaListaDeEmprestimos);
					disponibilizaVeiculo(posicaoNaListaDeEmprestimos);
					try {
						listaDeEmprestimos.confirmaDevolucao(posicaoNaListaDeEmprestimos, dataDevolucao);
						listaDeEmprestimos.removeDevolucaoEfetuada(posicaoNaListaDeEmprestimos);
						atualizeCampoResultadoDeBusca();
						limparCamposAposExclusao();
						sincronizaListas();
						JOptionPane.showMessageDialog(getContentPane(),"Devolução cadastrada com sucesso! ","Sucesso - Cadastro de Devoluções",JOptionPane.INFORMATION_MESSAGE);
					} catch (ExcecaoDataDeDevolucaoErrada e) {
						String erro=e.getMessage();
						JOptionPane.showMessageDialog(this, erro,"Atenção - Campo de Data", JOptionPane.WARNING_MESSAGE);
					}
					
				}

			}
		}
	}

	private void disponibilizaVeiculo(int posicaoNaListaDeEmprestimos) {
		Veiculo veiculo=listaDeEmprestimos.getEmprestimo(posicaoNaListaDeEmprestimos).getVeiculo();
		
		listaDeVeiculos.disponiblizeVeiculo(veiculo);
		
	}

	private void disponibilizaFuncionario(int posicaoNaListaDeEmprestimos) {
		Funcionario funcionario=listaDeEmprestimos.getEmprestimo(posicaoNaListaDeEmprestimos).getFuncionario();
		
		listaDeFuncionarios.disponiblizeFuncionario(funcionario);
		
		
	}

	private void limparCamposAposExclusao() {
		painelPesquisaData.campo_pesquisa.setText("");
		painelDadosDoEmprestimo.anoVeiculo.setText("");
		painelDadosDoEmprestimo.cargoFuncionario.setText("");
		painelDadosDoEmprestimo.dataDevolucaoVeiculo.setText("");
		painelDadosDoEmprestimo.funcionario.setText("");
		painelDadosDoEmprestimo.modeloVeiculo.setText("");
		painelDadosDoEmprestimo.placaVeiculo.setText("");
		painelDadosDoEmprestimo.prevDevolucaoVeiculo.setText("");
		painelDadosDoEmprestimo.registroFuncionario.setText("");
		painelDadosDoEmprestimo.rotulo_DiasDeAtrazo.setText("");
		
	}

	private void atualizeCampoResultadoDeBusca() {
		int posicao=campo_resultadoDeBusca.getSelectedIndex();
		modeloDosResultados.removeElementAt(posicao);
		campo_resultadoDeBusca.setModel(modeloDosResultados);
		
	}

	private void limparCampos() {
		
		painelPesquisaData.campo_pesquisa.setText("");
		painelDadosDoEmprestimo.anoVeiculo.setText("");
		painelDadosDoEmprestimo.cargoFuncionario.setText("");
		painelDadosDoEmprestimo.dataDevolucaoVeiculo.setText("");
		painelDadosDoEmprestimo.funcionario.setText("");
		painelDadosDoEmprestimo.modeloVeiculo.setText("");
		painelDadosDoEmprestimo.placaVeiculo.setText("");
		painelDadosDoEmprestimo.prevDevolucaoVeiculo.setText("");
		painelDadosDoEmprestimo.registroFuncionario.setText("");
		painelDadosDoEmprestimo.rotulo_DiasDeAtrazo.setText("");
		modeloDosResultados.clear();
	}

	private void fecharJanela() {
		sincronizaListas();
		dispose();
		
	}
		
	private void sincronizaListas() {
		janelaPrincipal.setListaDeEmprestimos(listaDeEmprestimos);
		janelaPrincipal.setListaDeFuncionarios(listaDeFuncionarios);
		janelaPrincipal.setListaDeVeiculos(listaDeVeiculos);

		
	}

	protected void pesquisarDevolucao(String data) {
		modeloDosResultados.clear();
		resultadoDaBusca=new ArrayList<Emprestimo>();
		String formatacao = "%7s    %-10s    %-40s"; 
		String dado="";
		resultadoDaBusca=listaDeEmprestimos.pesquisaEmprestimoPorData(data);
		for(int i=0;i<resultadoDaBusca.size();i++){
			dado=String.format(formatacao,
					resultadoDaBusca.get(i).getVeiculo().getPlaca(),					
					resultadoDaBusca.get(i).getVeiculo().getModelo(),
					resultadoDaBusca.get(i).getFuncionario().getNome());
			modeloDosResultados.add(i,dado);

		}
		campo_resultadoDeBusca.setModel(modeloDosResultados);
				
	}	

}
