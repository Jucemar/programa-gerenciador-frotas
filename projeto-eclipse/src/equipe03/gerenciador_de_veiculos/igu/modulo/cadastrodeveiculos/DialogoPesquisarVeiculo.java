package equipe03.gerenciador_de_veiculos.igu.modulo.cadastrodeveiculos;

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

import equipe03.gerenciador_de_veiculos.logica.veiculo.Veiculo;
import equipe03.gerenciador_de_veiculos.logica.veiculo.Veiculos;


public class DialogoPesquisarVeiculo extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	DialogoCadastrarVeiculo janelalMostraVeiculoEncontrado;
	JTextField campo_busca;
	DefaultListModel modeloDosResultados;
	JList Campo_resultado;
	Veiculos listaDeVeiculos;
	List<Veiculo> resultadoDaBusca;
	JButton botao_pesquisar;
	JButton botao_confirmar;
	JButton botao_cancelar;
	JComboBox combobox_tiposDePesquisa;

	public DialogoPesquisarVeiculo(DialogoCadastrarVeiculo dcv, Veiculos listaDeVeiculos) {
		super(dcv, "Pesquisar Veiculos", true);
		definaComponentes(listaDeVeiculos,dcv);
		posicionaComponentes();
		pack();
		setResizable(false);
		setLocationRelativeTo(dcv);
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

	private void definaComponentes(Veiculos listaDeVeiculos, DialogoCadastrarVeiculo dcv) {
		
		this.janelalMostraVeiculoEncontrado=dcv;
		this.listaDeVeiculos=listaDeVeiculos;
		campo_busca = new JTextField(20);
		
		botao_pesquisar = new JButton("Pesquisar");
		botao_pesquisar.addActionListener(this);
		botao_pesquisar.setActionCommand(OpcoesVeiculo.PESQUISAR_VEICULO.name());
		
		botao_confirmar = new JButton("Confirma");
		botao_confirmar.addActionListener(this);
		botao_confirmar.setActionCommand(OpcoesVeiculo.CONFIRMAR.name());
		
		
		botao_cancelar = new JButton("Cancelar");
		botao_cancelar.addActionListener(this);
		botao_cancelar.setActionCommand(OpcoesVeiculo.CANCELAR_JANELA.name());


		modeloDosResultados=new DefaultListModel();
		Campo_resultado = new JList();
		Campo_resultado.setFixedCellHeight(20);
		Campo_resultado.setFixedCellWidth(380);
		Campo_resultado.setLayoutOrientation(JList.VERTICAL);
		
		String[] opcoes_de_pesquisa={"Modelo","Placa"};
		combobox_tiposDePesquisa=new JComboBox(opcoes_de_pesquisa);
		combobox_tiposDePesquisa.setSelectedIndex(0);
		combobox_tiposDePesquisa.setMaximumRowCount(5);


	}

	@Override
	public void actionPerformed(ActionEvent e) {
		OpcoesVeiculo opcao = OpcoesVeiculo.valueOf(e.getActionCommand());
		switch (opcao) {
		
		case PESQUISAR_VEICULO:
			pesquisarVeiculo(campo_busca.getText().trim());
			
			break;
			
		case CANCELAR_JANELA:
			dispose();
			
			break;
			
		case CONFIRMAR:
			mostrarVeiculoSelecionado();
			
			
			break;
		}
	}



	private void pesquisarVeiculo(String informacaoParaPesquisar) {
		String opcaoDePesquisa = combobox_tiposDePesquisa.getSelectedItem().toString();
		
		String erro = "O Campo de pesquisa está vazio !\nFavor preechê-lo para poder prosseguir.\n";
		
		if (informacaoParaPesquisar.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(getContentPane(), erro, "Atenção - Campo de pesquisa", JOptionPane.WARNING_MESSAGE);
		
		} else {
			
			if (opcaoDePesquisa.equalsIgnoreCase("Modelo")) {
				pesquisarVeiculoComModelo(informacaoParaPesquisar);
			}
			if (opcaoDePesquisa.equalsIgnoreCase("Placa")) {
				pesquisarVeiculoComPlaca(informacaoParaPesquisar);
			}
		}

	}

	private void mostrarVeiculoSelecionado() {
		int posicaoVeiculo= Campo_resultado.getSelectedIndex();
		String erro = "Nenhum veículo foi selecionado !\nSelecione um para poder prosseguir.\n";
		if (posicaoVeiculo == -1) {
			JOptionPane.showMessageDialog(getContentPane(), erro,"Erro - Campo de resultado da pesquisa",JOptionPane.ERROR_MESSAGE);

		} else {
			janelalMostraVeiculoEncontrado.mostreVeiculoProcurado(resultadoDaBusca.get(posicaoVeiculo));
			dispose();
		}

	}

	private void pesquisarVeiculoComModelo(String modelo) {
		modeloDosResultados.clear();
		resultadoDaBusca=new ArrayList<Veiculo>();
		String formatacao = "%7s    %-4s    %-20s  "; 
		String dado="";
		resultadoDaBusca=listaDeVeiculos.pesquisaVeiculoPeloModelo(modelo);
		for(int i=0;i<resultadoDaBusca.size();i++){
			dado=String.format(formatacao,
					resultadoDaBusca.get(i).getPlaca(),					
					resultadoDaBusca.get(i).getAno(),
					resultadoDaBusca.get(i).getModelo());
			
			modeloDosResultados.add(i,dado);

		}
		Campo_resultado.setModel(modeloDosResultados);
				
	}
	
	private void pesquisarVeiculoComPlaca(String placa) {
		modeloDosResultados.clear();
		resultadoDaBusca=new ArrayList<Veiculo>();
		String formatacao = "%7s    %-4s    %-20s  ";  
		String dado="";
		resultadoDaBusca=listaDeVeiculos.pesquisaVeiculoPelaPlaca(placa);
		for(int i=0;i<resultadoDaBusca.size();i++){
			dado=String.format(formatacao,
					resultadoDaBusca.get(i).getPlaca(),					
					resultadoDaBusca.get(i).getAno(),
					resultadoDaBusca.get(i).getModelo());
			
			modeloDosResultados.add(i,dado);
			
		}
		Campo_resultado.setModel(modeloDosResultados);
		
				
	}
}
