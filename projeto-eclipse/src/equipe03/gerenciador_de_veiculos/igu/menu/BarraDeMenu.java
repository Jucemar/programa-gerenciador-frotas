package equipe03.gerenciador_de_veiculos.igu.menu;



import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import equipe03.gerenciador_de_veiculos.igu.JanelaPrincipal;

public class BarraDeMenu extends JMenuBar {	

	private static final long serialVersionUID = 1L;

	public BarraDeMenu(JanelaPrincipal igu){
		JMenu menu;
		JMenuItem item;
		
		menu=new JMenu("Arquivo");
		
		item = new JMenuItem("Abrir");
		item.setActionCommand(OpcoesBarraDeMenu.ABRIR.name());
		item.addActionListener(igu);
		menu.add(item);
		
		item = new JMenuItem("Salvar");
		item.setActionCommand(OpcoesBarraDeMenu.SALVAR.name());
		item.addActionListener(igu);
		menu.add(item);
		
		item = new JMenuItem("Salvar Como");
		item.setActionCommand(OpcoesBarraDeMenu.SALVAR_COMO.name());
		item.addActionListener(igu);
		menu.add(item);
		
		item = new JMenuItem("Sair");
		item.setActionCommand(OpcoesBarraDeMenu.SAIR.name());
		item.addActionListener(igu);
		menu.add(item);
				
		add(menu);
		
		menu=new JMenu("Cadastro");
		
		item = new JMenuItem("Veículos");
		item.setActionCommand(OpcoesBarraDeMenu.CADASTRAR_VEICULO.name());
		item.addActionListener(igu);
		menu.add(item);
		
		item = new JMenuItem("Funcionários");
		item.setActionCommand(OpcoesBarraDeMenu.CADASTRAR_FUNCIONARIO.name());
		item.addActionListener(igu);
		menu.add(item);
		
		add(menu);
		
		
		menu=new JMenu("Registro");
		
		item = new JMenuItem("Registrar emprestimo");
		item.setActionCommand(OpcoesBarraDeMenu.REGISTRAR_EMPRESTIMO.name());
		item.addActionListener(igu);
		menu.add(item);
		
		item = new JMenuItem("Registrar devolução");
		item.setActionCommand(OpcoesBarraDeMenu.REGISTRAR_DEVOLUCAO.name());
		item.addActionListener(igu);
		menu.add(item);
				
		add(menu);
		
		
		menu=new JMenu("Sobre");
		
		item = new JMenuItem("Sobre o programa");
		item.setActionCommand(OpcoesBarraDeMenu.SOBRE_PROGRAMA.name());
		item.addActionListener(igu);
		menu.add(item);
				
		add(menu);

	}
	

}
