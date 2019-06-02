package equipe03.gerenciador_de_veiculos.igu.modulo.ajuda;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import equipe03.gerenciador_de_veiculos.igu.JanelaPrincipal;

public class DialogoSobre extends JDialog implements ActionListener{

	JTextArea campo_texto;


	private static final long serialVersionUID = 1L;
	
	public DialogoSobre(JanelaPrincipal igu) {
		super(igu, "Sobre o Programa", true);


 		JPanel painel=new JPanel();
 		JLabel dado1=new JLabel("Programa Gerenciador de Veículos.");
 		dado1.setFont(new Font(Font.SANS_SERIF,Font.BOLD,15));
 		JLabel dado2=new JLabel("Versão: 1.0");
 		JLabel dado3=new JLabel("Desenvolvido por: Jucemar Dimon e Rodrigo Andrigheto.");
 		JLabel dado4=new JLabel("Tarefa Final da Disciplina INE5605.");
 		JLabel dado5=new JLabel("Curso de Sistemas de Informação.");
 		JLabel dado6=new JLabel("UFSC - CTC 2010-02");
 		painel.setLayout(new GridLayout(6,1));
		painel.add(dado1);
		painel.add(dado2);
		painel.add(dado3);
		painel.add(dado4);
		painel.add(dado5);
		painel.add(dado6);
		setLayout(new FlowLayout());
		
		
		painel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		add(painel);
		


 		pack();
 		setResizable(false);
		
		setLocationRelativeTo(igu);
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
