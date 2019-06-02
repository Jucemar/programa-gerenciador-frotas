package equipe03.gerenciador_de_veiculos.igu.modulo.cadastrodedevolucoes;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


	class CapturaDeEventosDaJList implements ListSelectionListener{
		DialogoRegistrarDevolucao dialogoRegistrarDevolucao;	
	
		
		public CapturaDeEventosDaJList(DialogoRegistrarDevolucao drd) {
			dialogoRegistrarDevolucao=drd;
		}
	

		@Override
	public void valueChanged(ListSelectionEvent evento) {
//		if (!evento.getValueIsAdjusting()) {
			int selecao = dialogoRegistrarDevolucao.campo_resultadoDeBusca
					.getSelectedIndex();
			if (selecao != -1) {
				dialogoRegistrarDevolucao.painelDadosDoEmprestimo
						.atualizePainelDeDados(selecao);
//			}

		}
	}

}
