package equipe03.gerenciador_de_veiculos.igu.modulo.cadastrodeveiculos;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;



public class PainelCadastrarNovoVeiculo extends JPanel{
	JTextField campo_placa;
	JTextField campo_documento;
	JTextField campo_chassi;
	JComboBox combobox_tipo;
	JComboBox combobox_marca;
	JTextField campo_modelo;
	JComboBox combobox_ano;
	JTextField campo_KM;
	
	JLabel rotulo_placa;
	JLabel rotulo_documento;
	JLabel rotulo_chassi;
	JLabel rotulo_tipo;
	JLabel rotulo_marca;
	JLabel rotulo_modelo;
	JLabel rotulo_ano;
	JLabel rotulo_KM;	
	
	
	
	private static final long serialVersionUID = 1L;

	public PainelCadastrarNovoVeiculo(){
		
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
				.addComponent(rotulo_placa)
				.addComponent(rotulo_documento)
				.addComponent(rotulo_chassi)
				.addComponent(rotulo_tipo)
				.addComponent(rotulo_marca)
				.addComponent(rotulo_modelo)
				.addComponent(rotulo_ano)
				.addComponent(rotulo_KM));
		
		
		horizontal.addGroup(layout.createParallelGroup()
				.addComponent(campo_placa)
				.addComponent(campo_documento)
				.addComponent(campo_chassi)
				.addComponent(combobox_tipo)
				.addComponent(combobox_marca)
				.addComponent(campo_modelo)
				.addComponent(combobox_ano)
				.addComponent(campo_KM));
		layout.setHorizontalGroup(horizontal);

		GroupLayout.SequentialGroup vertical = layout.createSequentialGroup();

		vertical.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(rotulo_placa).addComponent(campo_placa));
		
		vertical.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(rotulo_documento).addComponent(campo_documento));
		
		vertical.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(rotulo_chassi).addComponent(campo_chassi));
		
		vertical.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(rotulo_tipo).addComponent(combobox_tipo));
		
		vertical.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(rotulo_marca).addComponent(combobox_marca));
		
		vertical.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(rotulo_modelo).addComponent(campo_modelo));
		
		vertical.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(rotulo_ano).addComponent(combobox_ano));
		
		vertical.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(rotulo_KM).addComponent(campo_KM));
		
		layout.setVerticalGroup(vertical);
		add(painel);
		setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY), BorderFactory.createLineBorder(Color.white)));
		
		
		

	}

	private void definaComponentes() {
		
		String[] tipos_veiculos={
				" ",
				"Motocicleta",
				"Automóvel",
				"Caminhão",
				"Ônibus",
				"Caminhoneta",
				"Caminhonete",
				"Microônibus",
				"Triciculo",
				"Reboq"};
		
		
		String[] anos={" ","2010","2009","2008","2007","2006","2005","2004","2003","2002","2001","2000",
				"1999","1998","1997","1996","1995","1994","1993","1992","1991","1990","1989","1988",
				"1987","1986","1985","1984","1983","1982","1981","1980","1979","1978","1977","1976",
				"1974","1973","1972","1971","1970","1969","1968","1967","1966","1965","1964","1963",
				"1962","1961","1960"};
		
		String[] marcas={" ","Alfa Romeo","Audi","BMW","Cross Lander","Citroën","DaimlerChrysler","Ferrari",
				"Fiat","Ford","General Motors","Honda","Hyundai","Jaguar","Kia","Land Rover","Mitsubishi",
				"Nissan","Peugeot","Porsche","Renault","Seat","SsangYong","Subaru","Toyota","Troller","Volkswagen","Volvo"};

		
		campo_placa=new JTextField(15);
		campo_placa.setEditable(true);		

		
		campo_documento=new JTextField(15);
		campo_documento.setEditable(true);

		
		campo_chassi=new JTextField(15);
		campo_chassi.setEditable(true);

		
		combobox_tipo=new JComboBox(tipos_veiculos);
		combobox_tipo.setMaximumRowCount(5);
		
		combobox_marca=new JComboBox(marcas);
		combobox_marca.setMaximumRowCount(5);
		
		campo_modelo=new JTextField(15);
		campo_modelo.setEditable(true);
		
		combobox_ano=new JComboBox(anos);
		combobox_ano.setMaximumRowCount(5);
		
		campo_KM=new JTextField(15);
		campo_KM.setEditable(true);
		
		rotulo_placa=new JLabel("Placa: ");
		
		rotulo_documento=new JLabel("Documento: ");
		
		rotulo_chassi=new JLabel("Chassi: ");
		
		rotulo_tipo=new JLabel("Tipo: ");
		
		rotulo_marca=new JLabel("Marca: ");
		
		rotulo_modelo=new JLabel("Modelo: ");
		
		rotulo_ano=new JLabel("Ano: ");
		
		rotulo_KM=new JLabel("KM: ");

	}

}
