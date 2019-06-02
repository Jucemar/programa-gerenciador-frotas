package equipe03.gerenciador_de_veiculos.igu.modulo.cadastrodefuncionarios;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;

import equipe03.gerenciador_de_veiculos.igu.utilitarios.FormatadorDeDatas;
import equipe03.gerenciador_de_veiculos.logica.funcionario.Funcionario;
import equipe03.gerenciador_de_veiculos.logica.funcionario.Funcionarios;


public class DialogoPesquisarFuncionario extends JDialog implements
		ActionListener {

	private static final long serialVersionUID = 1L;
	DialogoCadastrarFuncionario janelalMostraFuncionarioEncontrado;
	JTextField campo_busca;
	DefaultListModel modeloDosResultados;
	JList Campo_resultado;
	Funcionarios listaDeFuncionarios;
	List<Funcionario> resultadoDaBusca;
	JButton botao_pesquisar;
	JButton botao_confirmar;
	JButton botao_cancelar;
	JComboBox combobox_tiposDePesquisa;

	public DialogoPesquisarFuncionario(DialogoCadastrarFuncionario dcf, Funcionarios listaDeFuncionarios) {
		super(dcf, "Pesquisar Funcionários", true);
		definaComponentes(listaDeFuncionarios,dcf);
		posicionaComponentes();
		pack();
		setResizable(false);
		setLocationRelativeTo(dcf);
		setVisible(true);
	}

	private void posicionaComponentes() {
		JScrollPane Campo_resultado_scroll = new JScrollPane(Campo_resultado);
		Container dialogo = getContentPane();
		GroupLayout layout = new GroupLayout(dialogo);
		dialogo.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		GroupLayout.SequentialGroup horizontal = layout.createSequentialGroup();
		horizontal.addGroup(layout
				.createParallelGroup(Alignment.TRAILING)
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(campo_busca)
								.addComponent(combobox_tiposDePesquisa))
				.addComponent(Campo_resultado_scroll)
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(botao_pesquisar)
								.addComponent(botao_confirmar)
								.addComponent(botao_cancelar)));

		layout.setHorizontalGroup(horizontal);

		GroupLayout.SequentialGroup vertical = layout.createSequentialGroup();

		vertical.addGroup(layout.createParallelGroup(Alignment.CENTER)

		.addComponent(campo_busca).addComponent(combobox_tiposDePesquisa));

		vertical.addComponent(Campo_resultado_scroll);

		vertical.addGroup(layout.createParallelGroup(Alignment.CENTER)
				.addComponent(botao_pesquisar)
				.addComponent(botao_confirmar)
				.addComponent(botao_cancelar));

		layout.setVerticalGroup(vertical);

	}

	private void definaComponentes(Funcionarios listaDeFuncionarios, DialogoCadastrarFuncionario dcf) {
		
		this.janelalMostraFuncionarioEncontrado=dcf;
		this.listaDeFuncionarios=listaDeFuncionarios;
		campo_busca = new JTextField(20);
		
		botao_pesquisar = new JButton("Pesquisar");
		botao_pesquisar.addActionListener(this);
		botao_pesquisar.setActionCommand(OpcoesFuncionario.PESQUISAR_FUNCIONARIO.name());
		
		botao_confirmar = new JButton("Confirma");
		botao_confirmar.addActionListener(this);
		botao_confirmar.setActionCommand(OpcoesFuncionario.CONFIRMAR.name());
		
		
		botao_cancelar = new JButton("Cancelar");
		botao_cancelar.addActionListener(this);
		botao_cancelar.setActionCommand(OpcoesFuncionario.CANCELAR_JANELA.name());


		modeloDosResultados=new DefaultListModel();
		Campo_resultado = new JList();
		Campo_resultado.setFixedCellHeight(20);
		Campo_resultado.setFixedCellWidth(380);
		Campo_resultado.setLayoutOrientation(JList.VERTICAL);
		
		String[] opcoes_de_pesquisa={"Nome","Registro"};
		combobox_tiposDePesquisa=new JComboBox(opcoes_de_pesquisa);
		combobox_tiposDePesquisa.setSelectedIndex(0);
		combobox_tiposDePesquisa.setMaximumRowCount(5);


	}

	@Override
	public void actionPerformed(ActionEvent e) {
		OpcoesFuncionario opcao = OpcoesFuncionario.valueOf(e.getActionCommand());
		switch (opcao) {
		case PESQUISAR_FUNCIONARIO:
			pesquisarFuncionario(campo_busca.getText().trim());
			
			break;
			
		case CANCELAR_JANELA:
			dispose();
			
			break;
			
		case CONFIRMAR:
			mostrarFuncionarioConfirmado();
			 
			
			break;

		}

	}



	private void pesquisarFuncionario(String informacaoParaPesquisar) {
		String opcaoDePesquisa = combobox_tiposDePesquisa.getSelectedItem().toString();
		String erro = "O Campo de pesquisa está vazio !\nFavor preechê-lo para poder prosseguir.\n";
		if (informacaoParaPesquisar.equalsIgnoreCase("")) {
			JOptionPane
					.showMessageDialog(getContentPane(), erro, "Atenção - Campo de pesquisa", JOptionPane.WARNING_MESSAGE);
		} else {
			if (opcaoDePesquisa.equalsIgnoreCase("Nome")) {
				pesquisarFuncionarioComNome(informacaoParaPesquisar);
			}
			if (opcaoDePesquisa.equalsIgnoreCase("Registro")) {
				pesquisarFuncionarioComRegistro(informacaoParaPesquisar);
			}
		}

	}

	private void mostrarFuncionarioConfirmado() {
		int posicaoFuncionario = Campo_resultado.getSelectedIndex();
		String erro = "Nenhum funcionário foi selecionado !\nSelecione um para poder prosseguir.\n";
		if (posicaoFuncionario == -1) {
			JOptionPane.showMessageDialog(getContentPane(), erro,
					"Erro - Campo de resultado da pesquisa",
					JOptionPane.ERROR_MESSAGE);

		} else {
			janelalMostraFuncionarioEncontrado
					.mostreFuncionarioProcurado(resultadoDaBusca
							.get(posicaoFuncionario));
			dispose();
		}

	}

	private void pesquisarFuncionarioComNome(String nome) {
		modeloDosResultados.clear();
		resultadoDaBusca=new ArrayList<Funcionario>();
		String formatacao = "%08d    %-10s    %-40s  "; 
		String dado="";
		resultadoDaBusca=listaDeFuncionarios.pesquisaFuncionarioPeloNome(nome);
		for(int i=0;i<resultadoDaBusca.size();i++){
			dado=String.format(formatacao,
					resultadoDaBusca.get(i).getRegistro(),					
					FormatadorDeDatas.formateDataDeNascimentoParaString(resultadoDaBusca.get(i).getData_de_nascimento()),
					resultadoDaBusca.get(i).getNome()
					);
			modeloDosResultados.add(i,dado);

		}
		Campo_resultado.setModel(modeloDosResultados);
				
	}
	
	private void pesquisarFuncionarioComRegistro(String registro) {
		modeloDosResultados.clear();
		resultadoDaBusca=new ArrayList<Funcionario>();
		String formatacao = "%08d    %-10s    %-40s  "; 
		String dado="";
		resultadoDaBusca=listaDeFuncionarios.pesquisaFuncionarioPeloRegistro(registro);
		for(int i=0;i<resultadoDaBusca.size();i++){
			dado=String.format(formatacao,
					resultadoDaBusca.get(i).getRegistro(),					
					FormatadorDeDatas.formateDataDeNascimentoParaString(resultadoDaBusca.get(i).getData_de_nascimento()),
					resultadoDaBusca.get(i).getNome()
					);
			modeloDosResultados.add(i,dado);

		}
		Campo_resultado.setModel(modeloDosResultados);
		
				
	}
}
