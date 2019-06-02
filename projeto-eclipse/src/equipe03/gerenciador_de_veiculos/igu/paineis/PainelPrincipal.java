package equipe03.gerenciador_de_veiculos.igu.paineis;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import equipe03.gerenciador_de_veiculos.igu.JanelaPrincipal;

public class PainelPrincipal extends JPanel {

	private static final long serialVersionUID = 1L;
	
	JanelaPrincipal janelaPrincipal;
	JTextField enderecoDoArquivo;

	public PainelPrincipal(JanelaPrincipal igu){
		janelaPrincipal=igu;	
		
		
		JLabel rotulo_arquivo=new JLabel("Arquivo em uso: ");
		enderecoDoArquivo=new JTextField(74);
		enderecoDoArquivo.setEditable(false);
		
		
		

		
		
		JPanel barraDestatus=new JPanel();
		barraDestatus.setLayout(new BorderLayout());
		barraDestatus.add(BorderLayout.WEST, rotulo_arquivo);
		barraDestatus.add(BorderLayout.EAST,enderecoDoArquivo);
		
		
		
		
		
		
		
		
		
		
		String msg="Essa é a primeira versão de teste de interface gráfica com " +
				"o usuário do programa Gerenciador de Veículos em fase de desenvolvimento " +
				"pelos academicos do curso de Sistemas de Informações Jucemar e Rodrigo, implementado com os conhecimentos adquiridos" +
				" na disciplina INE5605 - Desenvolvimento de Sistemas Orientados a Objetos I - 2010-02.";
		JTextArea planoFundo=new JTextArea(15,25);
		planoFundo.setText(msg);
		setLayout(new BorderLayout());
		planoFundo.setLineWrap(true);
		planoFundo.setWrapStyleWord(true);
//		planoFundo.setOpaque(false);
		planoFundo.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		
		setBorder(BorderFactory.createTitledBorder("Seja Bem Vindo"));
//		add(BorderLayout.NORTH,planoFundo);
		add(BorderLayout.SOUTH,barraDestatus);	
		
	}
	
	public void atualizaBarraDeEndereco(){
		if(janelaPrincipal.getArquivoPadrao()!=null){
			enderecoDoArquivo.setText(janelaPrincipal.getArquivoPadrao().getAbsolutePath().toString());
		}
	}
	

}
