package equipe03.gerenciador_de_veiculos.igu.modulo.cadastrodedevolucoes;

import java.awt.Color;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import equipe03.gerenciador_de_veiculos.igu.utilitarios.FormatadorDeDatas;
import equipe03.gerenciador_de_veiculos.logica.emprestimo.Emprestimo;

public class PainelDadosDoEmprestimo extends JPanel {

	private static final long serialVersionUID = 1L;
	DialogoRegistrarDevolucao dialogoRegistrarDevolucao;;
	
	JLabel rotulo_funcionario;
	JLabel rotulo_RegistroFuncionario;
	JLabel rotulo_CargoFuncionario;
	JLabel rotulo_PlacaVeiculo;
	JLabel rotulo_ModeloVeiculo;
	JLabel rotulo_AnoVeiculo;
	JLabel rotulo_PrevDevolucaoVeiculo;
	JLabel rotulo_DataDevolucaoVeiculo;
	JLabel rotulo_DiasDeAtrazo;
	
	JTextField funcionario;
	JTextField registroFuncionario;
	JTextField cargoFuncionario;
	JTextField placaVeiculo;
	JTextField modeloVeiculo;
	JTextField anoVeiculo;
	JTextField prevDevolucaoVeiculo;
	JTextField dataDevolucaoVeiculo;
	
	public PainelDadosDoEmprestimo(DialogoRegistrarDevolucao drd) {
		dialogoRegistrarDevolucao=drd;
		definaComponentes();
		posicionaComponentes();	
	}


	private void definaComponentes() {
		
		rotulo_funcionario=new JLabel("Funcionário: ");
		rotulo_RegistroFuncionario=new JLabel("Registro do funcionário: ");
		rotulo_CargoFuncionario=new JLabel("Cargo do funncionário: ");
		rotulo_PlacaVeiculo=new JLabel("Placa do veículo: ");
		rotulo_ModeloVeiculo=new JLabel("Modelo do veiculo");
		rotulo_AnoVeiculo=new JLabel("Ano do veículo: ");
		rotulo_PrevDevolucaoVeiculo=new JLabel("Devolução prevista para: ");
		rotulo_DataDevolucaoVeiculo=new JLabel("Data da devolução: ");
		rotulo_DiasDeAtrazo=new JLabel();
		
		funcionario=new JTextField();
		funcionario.setEditable(false);
		
		registroFuncionario=new JTextField();
		registroFuncionario.setEditable(false);
		
		cargoFuncionario=new JTextField();
		cargoFuncionario.setEditable(false);
		
		placaVeiculo=new JTextField();
		placaVeiculo.setEditable(false);
		
		modeloVeiculo=new JTextField();
		modeloVeiculo.setEditable(false);
		
		anoVeiculo=new JTextField();
		anoVeiculo.setEditable(false);
		
		prevDevolucaoVeiculo=new JTextField();
		prevDevolucaoVeiculo.setEditable(false);
		
		dataDevolucaoVeiculo=new JTextField();
		dataDevolucaoVeiculo.setEditable(true);
		
	}
	
	private void posicionaComponentes() {
		JPanel painel = new JPanel();
		GroupLayout layout = new GroupLayout(painel);
		painel.setLayout(layout);

		layout.setAutoCreateGaps(true);



		GroupLayout.SequentialGroup horizontal = layout.createSequentialGroup();

		horizontal.addGroup(layout.createParallelGroup()
				.addComponent(rotulo_funcionario)
				.addComponent(funcionario)
				.addComponent(rotulo_RegistroFuncionario)
				.addComponent(registroFuncionario)
				.addComponent(rotulo_CargoFuncionario)
				.addComponent(cargoFuncionario)
				.addComponent(rotulo_PlacaVeiculo)
				.addComponent(placaVeiculo)
				.addComponent(rotulo_ModeloVeiculo)
				.addComponent(modeloVeiculo)
				.addComponent(rotulo_AnoVeiculo)
				.addComponent(anoVeiculo)
				.addComponent(rotulo_PrevDevolucaoVeiculo)
				.addComponent(prevDevolucaoVeiculo)
				.addComponent(rotulo_DataDevolucaoVeiculo)
				.addComponent(dataDevolucaoVeiculo)
				.addComponent(rotulo_DiasDeAtrazo));
		
		

		
		layout.setHorizontalGroup(horizontal);

		GroupLayout.SequentialGroup vertical = layout.createSequentialGroup();

		vertical.addComponent(rotulo_funcionario)
				.addComponent(funcionario)
				.addComponent(rotulo_RegistroFuncionario)
				.addComponent(registroFuncionario)
				.addComponent(rotulo_CargoFuncionario)
				.addComponent(cargoFuncionario)
				.addComponent(rotulo_PlacaVeiculo)
				.addComponent(placaVeiculo)
				.addComponent(rotulo_ModeloVeiculo)
				.addComponent(modeloVeiculo)
				.addComponent(rotulo_AnoVeiculo)
				.addComponent(anoVeiculo)
				.addComponent(rotulo_PrevDevolucaoVeiculo)
				.addComponent(prevDevolucaoVeiculo)
				.addComponent(rotulo_DataDevolucaoVeiculo)
				.addComponent(dataDevolucaoVeiculo)
				.addComponent(rotulo_DiasDeAtrazo);
		
		layout.setVerticalGroup(vertical);
		add(painel);
		setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY), BorderFactory.createLineBorder(Color.white)));
		
	}
	
	public void atualizePainelDeDados(int posicao){
		Emprestimo emprestimo=dialogoRegistrarDevolucao.resultadoDaBusca.get(posicao);
		Date hoje=new Date();
		String texto_funcionario=emprestimo.getFuncionario().getNome();
		String texto_registro=Long.toString(emprestimo.getFuncionario().getRegistro());
		String texto_cargo=emprestimo.getFuncionario().getCargo();
		String texto_placa=emprestimo.getVeiculo().getPlaca();
		String texto_modelo=emprestimo.getVeiculo().getModelo();
		String texto_ano=emprestimo.getVeiculo().getAno();
		String texto_previsao=FormatadorDeDatas.formateDataDeNascimentoParaString(emprestimo.getPrevisao_devolucao());
		String texto_atrazo=Integer.toString(hoje.compareTo(emprestimo.getPrevisao_devolucao()));
		
		funcionario.setText(texto_funcionario);
		registroFuncionario.setText(texto_registro);
		cargoFuncionario.setText(texto_cargo);
		placaVeiculo.setText(texto_placa);
		modeloVeiculo.setText(texto_modelo);
		anoVeiculo.setText(texto_ano);
		prevDevolucaoVeiculo.setText(texto_previsao);
		rotulo_DiasDeAtrazo.setText("Atrasado: "+texto_atrazo+" dia(s).");
		
	}
	
	
	

}
