package equipe03.gerenciador_de_veiculos.igu.modulo.cadastrodeemprestimos;

import java.awt.Color;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;

import equipe03.gerenciador_de_veiculos.igu.modulo.cadastrodeemprestimos.excecoes.ExcecaoNaoHaFuncionariosDisponiveis;
import equipe03.gerenciador_de_veiculos.igu.modulo.cadastrodeemprestimos.excecoes.ExcecaoNaoHaVeiculosDisponiveis;
import equipe03.gerenciador_de_veiculos.logica.funcionario.Funcionario;
import equipe03.gerenciador_de_veiculos.logica.veiculo.Veiculo;

public class PainelRegistrarNovoEmprestimo extends JPanel{
	
	
	List<Veiculo> veiculosDisponiveis;
	List<Funcionario> funcionariosDisponiveis;
	
	DefaultComboBoxModel modeloComboboxFuncioario; 
	DefaultComboBoxModel modeloComboboxVeiculo;
	
	JLabel rotulo_funcionario;
	JLabel rotulo_veiculo;
	JLabel rotulo_dataDoEmprestimo;
	JLabel rotulo_dataDePrevisaoDevolucao;
	
	JComboBox combobox_funcionario;
	JComboBox combobox_veiculo;
	JTextField campo_dataDoEmprestimo;
	JTextField campo_dataDaprevisaoDevolucao;


	private static final long serialVersionUID = 1L;
	
	public PainelRegistrarNovoEmprestimo(DialogoRegistrarEmprestimo dre) {
		
		veiculosDisponiveis=dre.listaDeVeiculos.retorneVeiculosDispoiveis();
		funcionariosDisponiveis=dre.listaDeFuncionarios.retorneFuncioariosDispoiveis();
		
		definaComponentes();
		posicionaComponentes();	
	}


	private void definaComponentes() {		
		
		rotulo_funcionario=new JLabel("Funcionário: ");
		
		rotulo_veiculo=new JLabel("Veículo: ");
		
		rotulo_dataDoEmprestimo=new JLabel("Data do empréstimo: ");
		
		rotulo_dataDePrevisaoDevolucao=new JLabel("Previsão de devolução: ");
		
		campo_dataDoEmprestimo=new JTextField(20);
		campo_dataDoEmprestimo.setEditable(true);
		
		campo_dataDaprevisaoDevolucao=new JTextField(20);
		campo_dataDaprevisaoDevolucao.setEditable(true);
		
		modeloComboboxFuncioario=retorneModeloFuncionariosAtualizado();		
		combobox_funcionario=new JComboBox(modeloComboboxFuncioario);
		combobox_funcionario.setMaximumRowCount(5);
		
		modeloComboboxVeiculo=retorneModeloVeiculosAtualizado();
		combobox_veiculo=new JComboBox(modeloComboboxVeiculo);
		combobox_veiculo.setMaximumRowCount(5);
		
	}


	private void posicionaComponentes() {
		JPanel painel = new JPanel();
		GroupLayout layout = new GroupLayout(painel);
		painel.setLayout(layout);

		layout.setAutoCreateGaps(true);

		layout.setAutoCreateContainerGaps(true);

		GroupLayout.SequentialGroup horizontal = layout.createSequentialGroup();

		horizontal.addGroup(layout.createParallelGroup()
				.addComponent(rotulo_funcionario)
				.addComponent(rotulo_veiculo)
				.addComponent(rotulo_dataDoEmprestimo)
				.addComponent(rotulo_dataDePrevisaoDevolucao));
		
		
		horizontal.addGroup(layout.createParallelGroup()
				.addComponent(combobox_funcionario)
				.addComponent(combobox_veiculo)
				.addComponent(campo_dataDoEmprestimo)
				.addComponent(campo_dataDaprevisaoDevolucao));
		
		layout.setHorizontalGroup(horizontal);

		GroupLayout.SequentialGroup vertical = layout.createSequentialGroup();

		vertical.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(rotulo_funcionario).addComponent(combobox_funcionario));
		
		vertical.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(rotulo_veiculo).addComponent(combobox_veiculo));
		
		vertical.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(rotulo_dataDoEmprestimo).addComponent(campo_dataDoEmprestimo));
		
		vertical.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(rotulo_dataDePrevisaoDevolucao).addComponent(campo_dataDaprevisaoDevolucao));		
		
		layout.setVerticalGroup(vertical);
		add(painel);
		setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY), BorderFactory.createLineBorder(Color.white)));
		
	}
	
	private DefaultComboBoxModel retorneModeloVeiculosAtualizado() {
		DefaultComboBoxModel novoModeloComboboxFuncioario=new DefaultComboBoxModel();
		for(Veiculo veiculo:veiculosDisponiveis){
			novoModeloComboboxFuncioario.addElement(veiculo.getPlaca()+" - "+veiculo.getMarca()+" - "+veiculo.getModelo());
		}
		return novoModeloComboboxFuncioario;
	}


	private DefaultComboBoxModel retorneModeloFuncionariosAtualizado() {
		DefaultComboBoxModel novoModeloComboboxFuncioario=new DefaultComboBoxModel();
		for(Funcionario funcionario:funcionariosDisponiveis){
			novoModeloComboboxFuncioario.addElement(funcionario.getNome());
		}
		return novoModeloComboboxFuncioario;
	}
	
	public void atualizeComboBoxFuncionarios(){
		int indice=combobox_funcionario.getSelectedIndex();
		modeloComboboxFuncioario.removeElementAt(indice);
		funcionariosDisponiveis.remove(indice);
		

		}
	
	public void atualizeComboBoxVeiculos(){
		int indice=combobox_veiculo.getSelectedIndex();
		modeloComboboxVeiculo.removeElementAt(indice);
		veiculosDisponiveis.remove(indice);
		

		}
	
	public Funcionario retorneFuncionarioSelecionado() throws ExcecaoNaoHaFuncionariosDisponiveis{
		int posicao=combobox_funcionario.getSelectedIndex();
		if(posicao==-1){
			throw new ExcecaoNaoHaFuncionariosDisponiveis();
		}else{	
		
		return funcionariosDisponiveis.get(posicao);
		}
	}
	
	public Veiculo retorneVeiculoSelecionado()throws ExcecaoNaoHaVeiculosDisponiveis {

		int posicao = combobox_veiculo.getSelectedIndex();
		if (posicao == -1) {
			throw new ExcecaoNaoHaVeiculosDisponiveis();
		} else {

			return veiculosDisponiveis.get(posicao);
		}

	}
		

	

}
