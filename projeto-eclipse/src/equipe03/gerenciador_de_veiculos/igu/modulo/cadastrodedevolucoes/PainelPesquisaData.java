package equipe03.gerenciador_de_veiculos.igu.modulo.cadastrodedevolucoes;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import equipe03.gerenciador_de_veiculos.igu.utilitarios.FormatadorDeDatas;

public class PainelPesquisaData extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	JTextField campo_pesquisa;
	JButton botao_pesquisar;
	DialogoRegistrarDevolucao dialogoRegistrarDevolucao;
	
	public PainelPesquisaData(DialogoRegistrarDevolucao drd) {
		dialogoRegistrarDevolucao=drd;
		
		campo_pesquisa=new JTextField(12);
		
		botao_pesquisar=new JButton("Pesquisar Data");
		botao_pesquisar.setActionCommand(OpcoesDevolucao.PESQUISAR_DEVOLUCAO.name());
		botao_pesquisar.addActionListener(this);
		
		add(campo_pesquisa);
		add(botao_pesquisar);
		setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY), BorderFactory.createLineBorder(Color.white)));
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
OpcoesDevolucao opcao = OpcoesDevolucao.valueOf(e.getActionCommand());
		
		switch (opcao) {
		
		case PESQUISAR_DEVOLUCAO:
			pesquisarDevolucao();
			
			break;
			}
	}

	private void pesquisarDevolucao() {
		String erro = "O Campo de pesquisa está vazio !\nFavor preechê-lo para poder prosseguir.\n";
		if (campo_pesquisa.getText().length()==0) {
			JOptionPane
					.showMessageDialog(dialogoRegistrarDevolucao, erro, "Atenção - Campo de pesquisa", JOptionPane.WARNING_MESSAGE);
		}else{
		Date teste=null;
		try {
				teste = FormatadorDeDatas.formateStringParaDataDeNascimento(campo_pesquisa.getText().trim());
			} catch (ParseException e) {
				erro = "Formato de data iválido!\n"
						+ "O formato correto é dd/mm/aaaa ";
				JOptionPane.showMessageDialog(this, erro,"Erro - Formato errado", JOptionPane.ERROR_MESSAGE);
			}
			if (teste != null) {
				String data = campo_pesquisa.getText().trim();
				dialogoRegistrarDevolucao.pesquisarDevolucao(data);
			}

		}
	}


	

	
	
	

}
