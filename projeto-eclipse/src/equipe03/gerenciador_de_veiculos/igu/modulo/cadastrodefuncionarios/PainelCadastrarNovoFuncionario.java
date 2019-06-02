package equipe03.gerenciador_de_veiculos.igu.modulo.cadastrodefuncionarios;

import java.awt.Color;
import java.awt.Container;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;

public class PainelCadastrarNovoFuncionario extends JPanel{
	

	private static final long serialVersionUID = 1L;
	JTextField campo_nome;
	JTextField campo_dataNascimento;
	JComboBox combobox_sexo;
	JComboBox combobox_raca;
	JTextField campo_telefone;
	JTextField campo_cargo;
	JTextField campo_registro;
	
	JLabel rotulo_registro;
	JLabel rotulo_nome;
	JLabel rotulo_dataNascimento;
	JLabel rotulo_sexo;
	JLabel rotulo_raca;
	JLabel rotulo_telefone;
	JLabel rotulo_cargo;	
	

	public PainelCadastrarNovoFuncionario(){
		
		definaComponentes();
		posicionaComponentes();	
		
	}

	private void posicionaComponentes() {		

		JPanel painel = new JPanel();
		GroupLayout layout = new GroupLayout(painel);
		painel.setLayout(layout);

		layout.setAutoCreateGaps(true);

		layout.setAutoCreateContainerGaps(true);

		GroupLayout.SequentialGroup horizontal = layout.createSequentialGroup();

		horizontal.addGroup(layout.createParallelGroup()
				.addComponent(rotulo_nome)
				.addComponent(rotulo_registro)
				.addComponent(rotulo_dataNascimento)
				.addComponent(rotulo_sexo)
				.addComponent(rotulo_raca)
				.addComponent(rotulo_telefone)
				.addComponent(rotulo_cargo));
		
		
		horizontal.addGroup(layout.createParallelGroup()
				.addComponent(campo_nome)
				.addComponent(campo_registro)
				.addComponent(campo_dataNascimento)
				.addComponent(combobox_sexo)
				.addComponent(combobox_raca)
				.addComponent(campo_telefone)
				.addComponent(campo_cargo));
		layout.setHorizontalGroup(horizontal);

		GroupLayout.SequentialGroup vertical = layout.createSequentialGroup();

		vertical.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(rotulo_nome).addComponent(campo_nome));
		
		vertical.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(rotulo_registro).addComponent(campo_registro));
		
		vertical.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(rotulo_dataNascimento).addComponent(campo_dataNascimento));
		
		vertical.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(rotulo_sexo).addComponent(combobox_sexo));
		
		vertical.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(rotulo_raca).addComponent(combobox_raca));
		
		vertical.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(rotulo_telefone).addComponent(campo_telefone));
		
		vertical.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(rotulo_cargo).addComponent(campo_cargo));
		
		layout.setVerticalGroup(vertical);
		add(painel);
		setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY), BorderFactory.createLineBorder(Color.white)));
		

	}

	private void definaComponentes() {
		
		String[] sexo={" ","Masculino","Feminino"};
		String[] raca={" ","Branca","Negra","Amarela","Parda","Indigena","Outra"};	
		
		campo_nome=new JTextField(15);
		campo_nome.setEditable(true);		

		
		campo_dataNascimento=new JTextField(15);
		campo_dataNascimento.setEditable(true);

		
		combobox_sexo=new JComboBox(sexo);
		combobox_sexo.setMaximumRowCount(5);
		
		
		
		combobox_raca=new JComboBox(raca);
		combobox_raca.setMaximumRowCount(5);
		
		campo_telefone=new JTextField(15);
		campo_telefone.setEditable(true);

		
		campo_cargo=new JTextField(15);
		campo_cargo.setEditable(true);

		
		campo_registro=new JTextField(15);		
		campo_registro.setEditable(true);

		
		rotulo_nome=new JLabel("Nome: ");

		
		rotulo_dataNascimento=new JLabel("Nascimento: ");

		
		rotulo_sexo=new JLabel("Sexo: ");

		
		rotulo_raca=new JLabel("Raça: ");

		
		rotulo_telefone=new JLabel("Telefone: ");

		
		rotulo_cargo=new JLabel("Cargo: ");

		
		rotulo_registro=new JLabel("Registro: ");

		
	}

}



