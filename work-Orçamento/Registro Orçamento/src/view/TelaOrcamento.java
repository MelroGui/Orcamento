package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import control.OrcamentoProcessa;
import model.Orcamento;

public class TelaOrcamento extends JDialog implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private JPanel painel;
	private JTextField id, fornecedor, produto, preco;
	private JButton adicionar, alterar, excluir, buscar;
	private JTextArea texto;
	
	public TelaOrcamento() {
		setTitle("Cadastro de Usuários");
		setBounds(320, 250, 470, 400);
		painel = new JPanel();
		painel.setBackground(new Color(255, 233, 213));
		setContentPane(painel);
		setLayout(null);

		adicionar = new JButton("Cadastrar");
		buscar = new JButton("Consultar");
		alterar = new JButton("Alterar");
		excluir = new JButton("Excluir");

		adicionar.setBounds(20, 90, 100, 30);
		buscar.setBounds(125, 90, 100, 30);
		alterar.setBounds(230, 90, 100, 30);
		excluir.setBounds(335, 90, 100, 30);

		painel.add(adicionar);
		painel.add(buscar);
		painel.add(alterar);
		painel.add(excluir);

		adicionar.addActionListener(this);
		buscar.addActionListener(this);
		alterar.addActionListener(this);
		excluir.addActionListener(this);

		alterar.setEnabled(false);
		excluir.setEnabled(false);
	}

	private void create() {
		if (tfEmail.getText().length() > 0 && new String(pfSenha.getPassword()).length() > 3) {
			Orcamento user = new Orcamento(tfEmail.getText(), Cripto.encripta(new String(pfSenha.getPassword())));
			if (OrcamentoProcessa.orcamentos.contains(user)) {
				JOptionPane.showMessageDialog(this, "Usuário já cadastrado");
			} else {
				OrcamentoProcessa.usuarios.add(user);
				OrcamentoProcessa.salvar();
				preencheTabela();
				limparCampos();
			}
		} else {
			JOptionPane.showMessageDialog(this, "Preencha o email e a senha de no mínimo 4 dígitos");
		}
	}

	private void read() {
		if (tfEmail.getText().length() > 0) {
			Orcamento user = new Orcamento(tfEmail.getText(), "");
			if (OrcamentoProcessa.orcamentos.contains(user)) {
				indice = OrcamentoProcessa.orcamentos.indexOf(user);
				tfEmail.setEnabled(false);
				adicionar.setEnabled(false);
				buscar.setEnabled(false);
				alterar.setEnabled(true);
				excluir.setEnabled(true);
			} else {
				JOptionPane.showMessageDialog(this, "Usuário não encontrado");
			}
		} else {
			JOptionPane.showMessageDialog(this, "Preencha o campo email");
		}
	}

	private void update() {
		if (tfEmail.getText().length() > 0 && new String(pfSenha.getPassword()).length() > 3) {
			Orcamento user = new Orcamento(tfEmail.getText(),(new String(pfSenha.getPassword())));
			OrcamentoProcessa.orcamentos.set(indice, user);
			OrcamentoProcessa.salvar();
			adicionar.setEnabled(true);
			buscar.setEnabled(true);
			alterar.setEnabled(false);
			excluir.setEnabled(false);
		}
	}

	private void delete() {
		OrcamentoProcessa.orcamentos.remove(indice);
		OrcamentoProcessa.salvar();
		adicionar.setEnabled(true);
		buscar.setEnabled(true);
		alterar.setEnabled(false);
		excluir.setEnabled(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == adicionar) {
			create();
		}
		if (e.getSource() == buscar) {
			read();
		}
		if (e.getSource() == alterar) {
			update();
		}
		if (e.getSource() == excluir) {
			delete();
		}
	}
}