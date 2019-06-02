package equipe03.gerenciador_de_veiculos.igu.modulo.cadastrodeveiculos;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import equipe03.gerenciador_de_veiculos.igu.JanelaPrincipal;
import equipe03.gerenciador_de_veiculos.logica.veiculo.Veiculo;
import equipe03.gerenciador_de_veiculos.logica.veiculo.Veiculos;
import equipe03.gerenciador_de_veiculos.logica.veiculo.excecoes.ExcecaoAnoInvalido;
import equipe03.gerenciador_de_veiculos.logica.veiculo.excecoes.ExcecaoChassiInvalido;
import equipe03.gerenciador_de_veiculos.logica.veiculo.excecoes.ExcecaoDocumentoInvalido;
import equipe03.gerenciador_de_veiculos.logica.veiculo.excecoes.ExcecaoKmInvalida;
import equipe03.gerenciador_de_veiculos.logica.veiculo.excecoes.ExcecaoMarcaInvalida;
import equipe03.gerenciador_de_veiculos.logica.veiculo.excecoes.ExcecaoModeloInvalido;
import equipe03.gerenciador_de_veiculos.logica.veiculo.excecoes.ExcecaoPlacaInvalida;
import equipe03.gerenciador_de_veiculos.logica.veiculo.excecoes.ExcecaoPlacaNull;
import equipe03.gerenciador_de_veiculos.logica.veiculo.excecoes.ExcecaoTipoInvalido;
import equipe03.gerenciador_de_veiculos.logica.veiculo.excecoes.ExcecaoVeiculoEmUso;
import equipe03.gerenciador_de_veiculos.logica.veiculo.excecoes.ExcecaoVeiculoJaCadastrado;

public class DialogoCadastrarVeiculo extends JDialog implements ActionListener {

	PainelCadastrarNovoVeiculo painelCadastroDeVeiculos;
	JButton botao_salvar;
	JButton botao_cancelar;
	JButton botao_limpar;
	JButton botao_excluir;
	JButton botao_pesquisar;
	JButton botao_editar;
	Veiculos listaDeVeiculos;	
	DialogoPesquisarVeiculo pesquisar_veiculo;
	JanelaPrincipal janelaPrincipal;

	private static final long serialVersionUID = 1L;

	public DialogoCadastrarVeiculo(JanelaPrincipal igu) {
		super(igu, "Cadastro de Veículos", true);
		listaDeVeiculos = igu.getListaDeVeiculos();
		janelaPrincipal=igu;
		definaComponentes();
		posicionaComponentes();
		setResizable(false);
		pack();
		setLocationRelativeTo(igu);
		setVisible(true);

	}

	private void posicionaComponentes() {

		Container dialogo = getContentPane();
		GroupLayout layout = new GroupLayout(dialogo);
		dialogo.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.linkSize(SwingConstants.HORIZONTAL, botao_editar,
				botao_pesquisar, botao_excluir, botao_limpar, botao_salvar,
				botao_cancelar);

		GroupLayout.SequentialGroup horizontal = layout.createSequentialGroup();

		horizontal.addGroup(layout
				.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addComponent(painelCadastroDeVeiculos)
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(botao_pesquisar)
								.addComponent(botao_editar)
								.addComponent(botao_excluir))
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(botao_limpar)
								.addComponent(botao_salvar)
								.addComponent(botao_cancelar)));
		layout.setHorizontalGroup(horizontal);

		GroupLayout.SequentialGroup vertical = layout.createSequentialGroup();

		vertical.addGroup(layout
				.createSequentialGroup()
				.addComponent(painelCadastroDeVeiculos)
				.addGroup(
						layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(botao_pesquisar)
								.addComponent(botao_editar)
								.addComponent(botao_excluir))
				.addGroup(
						layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(botao_limpar)
								.addComponent(botao_salvar)
								.addComponent(botao_cancelar)));
		layout.setVerticalGroup(vertical);

	}

	private void definaComponentes() {

		painelCadastroDeVeiculos = new PainelCadastrarNovoVeiculo();

		botao_salvar = new JButton("Salvar");
		botao_salvar.setActionCommand(OpcoesVeiculo.SALVAR_VEICULO.name());
		botao_salvar.addActionListener(this);

		botao_cancelar = new JButton("Cancelar");
		botao_cancelar.setActionCommand(OpcoesVeiculo.CANCELAR_JANELA.name());
		botao_cancelar.addActionListener(this);

		botao_limpar = new JButton("Limpar");
		botao_limpar.setActionCommand(OpcoesVeiculo.LIMPAR_CAMPOS.name());
		botao_limpar.addActionListener(this);

		botao_excluir = new JButton("Excluir");
		botao_excluir.setActionCommand(OpcoesVeiculo.EXLUIR_VEICULO.name());
		botao_excluir.addActionListener(this);
		botao_excluir.setEnabled(false);

		botao_pesquisar = new JButton("Pesquisar");
		botao_pesquisar.setActionCommand(OpcoesVeiculo.PESQUISAR_VEICULO.name());
		botao_pesquisar.addActionListener(this);

		botao_editar = new JButton("Editar");
		botao_editar.setActionCommand(OpcoesVeiculo.EDITAR_VEICULO.name());
		botao_editar.addActionListener(this);
		botao_editar.setEnabled(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		OpcoesVeiculo opcao = OpcoesVeiculo.valueOf(e.getActionCommand());

		switch (opcao) {

		case LIMPAR_CAMPOS:
			limparCampos();
			cofiguraBotoesInicial();

			break;

		case CANCELAR_JANELA:
			janelaPrincipal.setListaDeVeiculos(listaDeVeiculos);
			dispose();

			break;

		case SALVAR_VEICULO:
			cadastrarVeiculo();

			break;

		case EDITAR_VEICULO:
			editarVeiculo();

			break;

		case PESQUISAR_VEICULO:
			pesquisarVeiculo();

			break;

		case EXLUIR_VEICULO:
			try {
				excluirVeiculo();
			} catch (ExcecaoPlacaNull e1) {
			} catch (ExcecaoPlacaInvalida e1) {
			}

			break;
		}
	}




	private void editarVeiculo() {
		configuraDesbolqueioDeCamposParaEdicao();
		cofiguraBotoesParaEdicao();	
		
	}

	private void limparCampos() {
		painelCadastroDeVeiculos.campo_placa.setText("");
		painelCadastroDeVeiculos.campo_documento.setText("");
		painelCadastroDeVeiculos.campo_chassi.setText("");
		painelCadastroDeVeiculos.combobox_tipo.setSelectedIndex(0);
		painelCadastroDeVeiculos.combobox_marca.setSelectedIndex(0);
		painelCadastroDeVeiculos.campo_modelo.setText("");
		painelCadastroDeVeiculos.combobox_ano.setSelectedIndex(0);
		painelCadastroDeVeiculos.campo_KM.setText("");		
		
		painelCadastroDeVeiculos.campo_placa.setEditable(true);
		painelCadastroDeVeiculos.campo_documento.setEditable(true);
		painelCadastroDeVeiculos.campo_chassi.setEditable(true);
		painelCadastroDeVeiculos.combobox_tipo.setEnabled(true);
		painelCadastroDeVeiculos.combobox_marca.setEnabled(true);
		painelCadastroDeVeiculos.campo_modelo.setEditable(true);
		painelCadastroDeVeiculos.combobox_ano.setEnabled(true);
		painelCadastroDeVeiculos.campo_KM.setEditable(true);
	}

	private void pesquisarVeiculo() {
		new DialogoPesquisarVeiculo(this, listaDeVeiculos);
		
	}
	
	
	private void excluirVeiculo() throws ExcecaoPlacaNull, ExcecaoPlacaInvalida {
		Veiculo veiculoDescartado = new Veiculo();

		veiculoDescartado.setPlaca(painelCadastroDeVeiculos.campo_placa.getText().trim());
		

		int resposta = JOptionPane.showConfirmDialog(getContentPane(),
				"Deseja realmente excluir este funcionário do cadastro?",
				"Confirmação de Exclusão", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);

		if (resposta == 0) {

			try {
				listaDeVeiculos.deletarVeiculo(veiculoDescartado);
				limparCampos();
				cofiguraBotoesInicial();
				janelaPrincipal.setListaDeVeiculos(listaDeVeiculos);
				
			} catch (ExcecaoVeiculoEmUso e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Erro - Exclusão não permitida",JOptionPane.ERROR_MESSAGE);
			}
			
			
		}
	}
	
	
	private void cadastrarVeiculo() {
		Veiculo veiculo= new Veiculo();
		veiculo = valideDadosNovoVeiculo();
		
		if (veiculo != null) {

			if (painelCadastroDeVeiculos.campo_placa.isEditable() == false) {
				listaDeVeiculos.editarDadosDoVeiculo(veiculo);
				JOptionPane.showMessageDialog(getContentPane(),"Veículo atualizado com sucesso! ","Sucesso - Cadastro de Veículo",JOptionPane.INFORMATION_MESSAGE);
				
			} else {

				try {
					
					listaDeVeiculos.cadastrarNovoVeiculo(veiculo);
					JOptionPane.showMessageDialog(getContentPane(),"Veículo cadastrado com sucesso! ","Sucesso - Cadastro de Veículo",JOptionPane.INFORMATION_MESSAGE);
					
				} catch (ExcecaoVeiculoJaCadastrado e) {
					JOptionPane.showMessageDialog(getContentPane(),e.getMessage(), "Erro - Duplicidade",JOptionPane.ERROR_MESSAGE);

				}

			}
			limparCampos();
			cofiguraBotoesInicial();
			janelaPrincipal.setListaDeVeiculos(listaDeVeiculos);
		}
		
	}
		
	
	
	private Veiculo valideDadosNovoVeiculo() {
		Veiculo veiculo= new Veiculo();
		Veiculo veiculoValidado = new Veiculo();
		String erro = " ";
		boolean validacao = true;
		
		try {
			veiculo.setPlaca(painelCadastroDeVeiculos.campo_placa.getText().trim());
			veiculo.setDocumento(painelCadastroDeVeiculos.campo_documento.getText().trim());	
			veiculo.setChassi(painelCadastroDeVeiculos.campo_chassi.getText().trim());
			veiculo.setTipo(painelCadastroDeVeiculos.combobox_tipo.getSelectedItem().toString().trim());
			veiculo.setMarca(painelCadastroDeVeiculos.combobox_marca.getSelectedItem().toString().trim());
			veiculo.setModelo(painelCadastroDeVeiculos.campo_modelo.getText().trim());
			veiculo.setAno(painelCadastroDeVeiculos.combobox_ano.getSelectedItem().toString().trim());
			veiculo.setKm(Float.parseFloat(painelCadastroDeVeiculos.campo_KM.getText().trim()));
		
		
		} catch (ExcecaoPlacaNull e) {
			erro = e.getMessage();
			validacao = false;

		} catch (ExcecaoPlacaInvalida e) {
			erro = e.getMessage();
			validacao = false;

		} catch (ExcecaoDocumentoInvalido e) {
			erro = e.getMessage();
			validacao = false;

		} catch (ExcecaoChassiInvalido e) {
			erro = e.getMessage();
			validacao = false;

		} catch (ExcecaoTipoInvalido e) {
			erro = e.getMessage();
			validacao = false;

		} catch (ExcecaoMarcaInvalida e) {
			erro = e.getMessage();
			validacao = false;

		} catch (ExcecaoModeloInvalido e) {
			erro = e.getMessage();
			validacao = false;

		} catch (ExcecaoAnoInvalido e) {
			erro = e.getMessage();
			validacao = false;

		} catch (NumberFormatException e) {
			erro = "Kilometragem iválida!\nDigite um valor no formato 0.00";
			validacao = false;

		} catch (ExcecaoKmInvalida e) {
			erro = e.getMessage();
			validacao = false;
		}

		if (validacao == false) {
			JOptionPane.showMessageDialog(this, erro,
					"Erro - Validação de dados", JOptionPane.ERROR_MESSAGE);
		}

		if (validacao == true) {
			veiculoValidado = veiculo;

		} else {
			veiculoValidado = null;

		}

		return veiculoValidado;

	}
	
	public void mostreVeiculoProcurado(Veiculo veiculo) {
		painelCadastroDeVeiculos.campo_placa.setText(veiculo.getPlaca());
		painelCadastroDeVeiculos.campo_documento.setText(veiculo.getDocumento());
		painelCadastroDeVeiculos.campo_chassi.setText(veiculo.getChassi());
		painelCadastroDeVeiculos.combobox_tipo.setSelectedItem(veiculo.getTipo());
		painelCadastroDeVeiculos.combobox_marca.setSelectedItem(veiculo.getMarca());
		painelCadastroDeVeiculos.campo_modelo.setText(veiculo.getModelo());
		painelCadastroDeVeiculos.combobox_ano.setSelectedItem(veiculo.getAno());
		painelCadastroDeVeiculos.campo_KM.setText(Float.toString(veiculo.getKm()));
		configuraBolqueioDeCampos();
		cofiguraBotoesAposPesquisa();
		
	}
	
	private void configuraBolqueioDeCampos() {
		painelCadastroDeVeiculos.campo_placa.setEditable(false);
		painelCadastroDeVeiculos.campo_documento.setEditable(false);
		painelCadastroDeVeiculos.campo_chassi.setEditable(false);
		painelCadastroDeVeiculos.combobox_tipo.setEnabled(false);
		painelCadastroDeVeiculos.combobox_marca.setEnabled(false);
		painelCadastroDeVeiculos.campo_modelo.setEditable(false);
		painelCadastroDeVeiculos.combobox_ano.setEnabled(false);
		painelCadastroDeVeiculos.campo_KM.setEditable(false);
		
	}
	
	
	private void configuraDesbolqueioDeCamposParaEdicao() {
		painelCadastroDeVeiculos.campo_documento.setEditable(true);
		painelCadastroDeVeiculos.campo_chassi.setEditable(true);
		painelCadastroDeVeiculos.combobox_tipo.setEnabled(true);
		painelCadastroDeVeiculos.combobox_marca.setEnabled(true);
		painelCadastroDeVeiculos.campo_modelo.setEditable(true);
		painelCadastroDeVeiculos.combobox_ano.setEnabled(true);
		painelCadastroDeVeiculos.campo_KM.setEditable(true);

		
	}
	

	private void cofiguraBotoesAposPesquisa() {
		botao_pesquisar.setEnabled(true);
		botao_limpar.setEnabled(true);
		botao_editar.setEnabled(true);
		botao_excluir.setEnabled(true);
		botao_salvar.setEnabled(false);
		
	}
	
	private void cofiguraBotoesInicial() {
		botao_pesquisar.setEnabled(true);
		botao_editar.setEnabled(false);
		botao_excluir.setEnabled(false);
		botao_limpar.setEnabled(true);
		botao_salvar.setEnabled(true);
		
		
		
	}
	
	private void cofiguraBotoesParaEdicao() {
		botao_pesquisar.setEnabled(false);
		botao_limpar.setEnabled(false);
		botao_editar.setEnabled(false);
		botao_excluir.setEnabled(false);
		botao_salvar.setEnabled(true);
		
	}

}
