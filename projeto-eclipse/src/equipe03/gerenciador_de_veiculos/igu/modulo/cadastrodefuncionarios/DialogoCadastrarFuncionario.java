package equipe03.gerenciador_de_veiculos.igu.modulo.cadastrodefuncionarios;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;

import equipe03.gerenciador_de_veiculos.igu.JanelaPrincipal;
import equipe03.gerenciador_de_veiculos.igu.utilitarios.FormatadorDeDatas;
import equipe03.gerenciador_de_veiculos.logica.funcionario.Funcionario;
import equipe03.gerenciador_de_veiculos.logica.funcionario.Funcionarios;
import equipe03.gerenciador_de_veiculos.logica.funcionario.excecoes.ExcecaoCargoInvalido;
import equipe03.gerenciador_de_veiculos.logica.funcionario.excecoes.ExcecaoDataDeNascimentoInvalida;
import equipe03.gerenciador_de_veiculos.logica.funcionario.excecoes.ExcecaoFuncionarioEmUso;
import equipe03.gerenciador_de_veiculos.logica.funcionario.excecoes.ExcecaoFuncionarioJaCadastrado;
import equipe03.gerenciador_de_veiculos.logica.funcionario.excecoes.ExcecaoNomeInvalido;
import equipe03.gerenciador_de_veiculos.logica.funcionario.excecoes.ExcecaoNomeNull;
import equipe03.gerenciador_de_veiculos.logica.funcionario.excecoes.ExcecaoRacaInvalida;
import equipe03.gerenciador_de_veiculos.logica.funcionario.excecoes.ExcecaoRegistroInvalido;
import equipe03.gerenciador_de_veiculos.logica.funcionario.excecoes.ExcecaoSexoInvalido;
import equipe03.gerenciador_de_veiculos.logica.funcionario.excecoes.ExcecaoTelefoneInvalido;

public class DialogoCadastrarFuncionario extends JDialog implements	ActionListener {
	PainelCadastrarNovoFuncionario painelCadastroDeFuncionarios;
	JButton botao_salvar;
	JButton botao_cancelar;
	JButton botao_limpar;
	JButton botao_excluir;
	JButton botao_pesquisar;
	JButton botao_editar;
	Funcionarios listaDeFuncionarios;
	DialogoPesquisarFuncionario pesquisar_funcionario;
	JanelaPrincipal janelaPrincipal;

	private static final long serialVersionUID = 1L;

	public DialogoCadastrarFuncionario(JanelaPrincipal igu) {
		super(igu, "Cadastro de Funcionários", true);
		listaDeFuncionarios = igu.getListaDeFuncionarios();
		janelaPrincipal=igu;
		definaComponentes();
		posicionaComponentes();

		setResizable(false);
		pack();
		setLocationRelativeTo(igu);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
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
				.createParallelGroup(Alignment.CENTER)
				.addComponent(painelCadastroDeFuncionarios)
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
				.addComponent(painelCadastroDeFuncionarios)
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.BASELINE)
								.addComponent(botao_pesquisar)
								.addComponent(botao_editar)
								.addComponent(botao_excluir))
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.BASELINE)
								.addComponent(botao_limpar)
								.addComponent(botao_salvar)
								.addComponent(botao_cancelar)));
		layout.setVerticalGroup(vertical);

	}

	private void definaComponentes() {

		painelCadastroDeFuncionarios = new PainelCadastrarNovoFuncionario();
		
		botao_salvar = new JButton("Salvar");
		botao_salvar.setActionCommand(OpcoesFuncionario.SALVAR_FUNCIONARIO.name());
		botao_salvar.addActionListener(this);

		botao_cancelar = new JButton("Cancelar");
		botao_cancelar.setActionCommand(OpcoesFuncionario.CANCELAR_JANELA.name());
		botao_cancelar.addActionListener(this);

		botao_limpar = new JButton("Limpar");
		botao_limpar.setActionCommand(OpcoesFuncionario.LIMPAR_CAMPOS.name());
		botao_limpar.addActionListener(this);

		botao_excluir = new JButton("Excluir");
		botao_excluir.setActionCommand(OpcoesFuncionario.EXLUIR_FUNCIONARIO.name());
		botao_excluir.addActionListener(this);
		botao_excluir.setEnabled(false);

		botao_pesquisar = new JButton("Pesquisar");
		botao_pesquisar.setActionCommand(OpcoesFuncionario.PESQUISAR_FUNCIONARIO.name());
		botao_pesquisar.addActionListener(this);

		botao_editar = new JButton("Editar");
		botao_editar.setActionCommand(OpcoesFuncionario.EDITAR_FUNCIONARIO.name());
		botao_editar.addActionListener(this);
		botao_editar.setEnabled(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		OpcoesFuncionario opcao = OpcoesFuncionario.valueOf(e.getActionCommand());
		switch (opcao) {
		case LIMPAR_CAMPOS:
			limparCampos();
			cofiguraBotoesInicial();

			break;
			
		case CANCELAR_JANELA:
			janelaPrincipal.setListaDeFuncionarios(listaDeFuncionarios);
			dispose();

			break;
			
		case SALVAR_FUNCIONARIO:
			cadastrarFuncionario();
			
			break;
			
		case EDITAR_FUNCIONARIO:
			editarFuncionario();
			
			break;
			
		case PESQUISAR_FUNCIONARIO:
			pesquisarFuncionario();

			break;
			
		case EXLUIR_FUNCIONARIO:
			excluirFuncionario();

			break;
		}

	}

	private void editarFuncionario() {
		configuraDesbolqueioDeCamposParaEdicao();
		cofiguraBotoesParaEdicao();	
		
	}

	private void limparCampos() {
		painelCadastroDeFuncionarios.campo_nome.setText("");
		painelCadastroDeFuncionarios.campo_registro.setText("");
		painelCadastroDeFuncionarios.campo_dataNascimento.setText("");
		painelCadastroDeFuncionarios.combobox_raca.setSelectedIndex(0);
		painelCadastroDeFuncionarios.combobox_sexo.setSelectedIndex(0);
		painelCadastroDeFuncionarios.campo_telefone.setText("");
		painelCadastroDeFuncionarios.campo_cargo.setText("");
		
		painelCadastroDeFuncionarios.campo_nome.setEditable(true);
		painelCadastroDeFuncionarios.campo_registro.setEditable(true);
		painelCadastroDeFuncionarios.campo_dataNascimento.setEditable(true);
		painelCadastroDeFuncionarios.combobox_raca.setEnabled(true);
		painelCadastroDeFuncionarios.combobox_sexo.setEnabled(true);
		painelCadastroDeFuncionarios.campo_telefone.setEditable(true);
		painelCadastroDeFuncionarios.campo_cargo.setEditable(true);
		


		
		
	}

	private void pesquisarFuncionario() {
		new DialogoPesquisarFuncionario(this, listaDeFuncionarios);

	}

	private void excluirFuncionario()  {
		Funcionario funcionarioDemitido = new Funcionario();


			try {
				funcionarioDemitido.setRegistro(Long.parseLong(painelCadastroDeFuncionarios.campo_registro.getText().trim()));
				
			} catch (NumberFormatException e) {

			} catch (ExcecaoRegistroInvalido e) {

			}


		int resposta = JOptionPane.showConfirmDialog(getContentPane(),
				"Deseja realmente excluir este funcionário do cadastro?",
				"Confirmação de Exclusão", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		
		if (resposta == 0) {
			try {
				listaDeFuncionarios.deletarFuncionario(funcionarioDemitido);
				limparCampos();
				cofiguraBotoesInicial();
				janelaPrincipal.setListaDeFuncionarios(listaDeFuncionarios);
			} catch (ExcecaoFuncionarioEmUso e) {			
				JOptionPane.showMessageDialog(this, e.getMessage(), "Erro - Exclusão não permitida",JOptionPane.ERROR_MESSAGE);
			}

			
		}
	}
	

	private void cadastrarFuncionario() {
		Funcionario funcionario = new Funcionario();
		funcionario = valideDadosNovoFuncionario();
		
		if (funcionario != null) {

			if (painelCadastroDeFuncionarios.campo_registro.isEditable() == false) {
				listaDeFuncionarios.editarDadosDoFuncionario(funcionario);
				JOptionPane.showMessageDialog(getContentPane(),"Funcionário atualizado com sucesso! ","Sucesso - Cadastro de Funcionario",JOptionPane.INFORMATION_MESSAGE);
				limparCampos();
				cofiguraBotoesInicial();
				sincronizaListas();
				
			} else {

				try {
					
					listaDeFuncionarios.cadastrarNovoFuncionario(funcionario);
					JOptionPane.showMessageDialog(getContentPane(),"Funcionário cadastrado com sucesso! ","Sucesso - Cadastro de Funcionario",JOptionPane.INFORMATION_MESSAGE);
					limparCampos();
					cofiguraBotoesInicial();
					sincronizaListas();
					
				} catch (ExcecaoFuncionarioJaCadastrado e) {
					JOptionPane.showMessageDialog(getContentPane(),
							e.getMessage(), "Erro - Duplicidade",
							JOptionPane.ERROR_MESSAGE);

				}

			}

			
		}
	}
	
	private void sincronizaListas() {
		janelaPrincipal.setListaDeFuncionarios(listaDeFuncionarios);

		
	}


	private Funcionario valideDadosNovoFuncionario() {
		Funcionario funcionario = new Funcionario();
		Funcionario funcionarioValidado = new Funcionario();
		String erro = " ";
		
		
		boolean validacao = true;

		try {

			funcionario.setNome(painelCadastroDeFuncionarios.campo_nome.getText().trim());
			funcionario.setRegistro(Long.parseLong(painelCadastroDeFuncionarios.campo_registro.getText().trim()));
			funcionario.setData_de_nascimento(FormatadorDeDatas.formateStringParaDataDeNascimento(painelCadastroDeFuncionarios.campo_dataNascimento.getText().trim()));
			funcionario.setSexo(painelCadastroDeFuncionarios.combobox_sexo.getSelectedItem().toString());
			funcionario.setRaca(painelCadastroDeFuncionarios.combobox_raca.getSelectedItem().toString());
			funcionario.setTelefone(painelCadastroDeFuncionarios.campo_telefone.getText().trim());
			funcionario.setCargo(painelCadastroDeFuncionarios.campo_cargo.getText().trim());
			
			
		} catch (ExcecaoNomeNull e1) {
			erro = e1.getMessage();
			validacao = false;
			

		} catch (ExcecaoNomeInvalido e1) {
			erro = e1.getMessage();
			validacao = false;
			

		} catch (NumberFormatException e1) {
			erro = "Registro inválido";
			validacao = false;
			

		} catch (ExcecaoRegistroInvalido e1) {
			erro = e1.getMessage();
			validacao = false;
			

		} catch (ExcecaoDataDeNascimentoInvalida e1) {
			erro = e1.getMessage();
			validacao = false;
			

		} catch (ParseException e) {
			erro = "Data de nascimento iválida!\n"+
			  "Insira uma data no formato dd/mm/aaaa ";
			validacao = false;
			

		} catch (ExcecaoSexoInvalido e1) {
			erro = e1.getMessage();
			validacao = false;
			

		} catch (ExcecaoRacaInvalida e1) {
			erro = e1.getMessage();
			validacao = false;
			

		} catch (ExcecaoTelefoneInvalido e1) {
			erro = e1.getMessage();
			validacao = false;
			

		} catch (ExcecaoCargoInvalido e1) {
			erro = e1.getMessage();
			validacao = false;		

		}
		
		if (validacao == false) {
			JOptionPane.showMessageDialog(this, erro, "Erro",JOptionPane.ERROR_MESSAGE);
		}
		

		if (validacao == true) {
			funcionarioValidado = funcionario;
			
		} else {
			funcionarioValidado = null;

		}
		
		return funcionarioValidado;
	}
	
	
	

	public void mostreFuncionarioProcurado(Funcionario funcionario) {
		painelCadastroDeFuncionarios.campo_nome.setText(funcionario.getNome());
		painelCadastroDeFuncionarios.campo_registro.setText(Long
				.toString((funcionario.getRegistro())));
		painelCadastroDeFuncionarios.campo_dataNascimento
				.setText(FormatadorDeDatas.formateDataDeNascimentoParaString(funcionario
						.getData_de_nascimento()));
		painelCadastroDeFuncionarios.combobox_raca.setSelectedItem(funcionario
				.getRaca());
		painelCadastroDeFuncionarios.combobox_sexo.setSelectedItem(funcionario
				.getSexo());
		painelCadastroDeFuncionarios.campo_telefone.setText(funcionario
				.getTelefone());
		painelCadastroDeFuncionarios.campo_cargo
				.setText(funcionario.getCargo());
		
		configuraBolqueioDeCampos();
		cofiguraBotoesAposPesquisa();
		

	}
		
	
	private void configuraBolqueioDeCampos() {
		painelCadastroDeFuncionarios.campo_nome.setEditable(false);
		painelCadastroDeFuncionarios.campo_registro.setEditable(false);
		painelCadastroDeFuncionarios.campo_dataNascimento.setEditable(false);
		painelCadastroDeFuncionarios.combobox_raca.setEnabled(false);
		painelCadastroDeFuncionarios.combobox_sexo.setEnabled(false);
		painelCadastroDeFuncionarios.campo_telefone.setEditable(false);
		painelCadastroDeFuncionarios.campo_cargo.setEditable(false);		
	}
	
	private void configuraDesbolqueioDeCamposParaEdicao() {
		painelCadastroDeFuncionarios.campo_nome.setEditable(true);
		painelCadastroDeFuncionarios.campo_dataNascimento.setEditable(true);
		painelCadastroDeFuncionarios.combobox_raca.setEnabled(true);
		painelCadastroDeFuncionarios.combobox_sexo.setEnabled(true);
		painelCadastroDeFuncionarios.campo_telefone.setEditable(true);
		painelCadastroDeFuncionarios.campo_cargo.setEditable(true);		
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
