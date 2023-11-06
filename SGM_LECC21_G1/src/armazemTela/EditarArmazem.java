package armazemTela;



import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;

import armazem.Armazem;
import armazem.ArmazemOperacoes;

import excepcoes.CampoVazioException;
import produto.Produto;
import telas.ListaProdutos;

public class EditarArmazem extends JFrame implements ActionListener {
	ArmazemOperacoes crudArmazem = new ArmazemOperacoes();
	Vector temp = crudArmazem.recuperarBD();
	Armazem armazemente = new Armazem();

	private JLabel jl_nome;
	private JLabel jl_id;


	private JTextField jt_nome;
	private JTextField jt_id;


	private JButton bt_Edite;
	private JButton bt_Cancela;

	public EditarArmazem(Armazem armazem) {
		jl_nome = new JLabel("Nome :");
		jt_nome = new JTextField(armazem.getNome() + "");
		jl_id = new JLabel("codigo :");
		jt_id = new JTextField(armazem.getId() + "");
		jt_id.setEditable(false);
	
//		jl_cell = new JLabel("Telefone :");
//		jt_cell = new JTextField(armazem.getCell() + "");
//		jl_quantidade = new JLabel("Quantidade :");
//		jt_quantidade = new JTextField(armazem.getQuantidade() + "");

		bt_Edite = new JButton("Editar");
		bt_Cancela = new JButton("Cancelar");

		bt_Edite.addActionListener(this);
		bt_Cancela.addActionListener(this);

		this.setLayout(new GridLayout(6,2,5,5));
		this.setTitle("Editar Cliente");// O tittulo da janela.
		this.setSize(450, 210);// Width and Height em pixels.[Comprimento, Largura]
		this.setLocation(100, 100);// Onde o programa vai arrancar
		this.setLocationRelativeTo(null);// Onde o programa vai arrancar
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Quando o utilizador armazemcar no x. Mata todos os

		this.add(jl_id);
		this.add(jt_id);
		this.add(jl_nome);
		this.add(jt_nome);
		
		
		this.add(bt_Cancela);
		this.add(bt_Edite);

		// frames
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bt_Cancela) {
			new ListaArmazem();
			this.setVisible(false);
		}
		if ((e.getSource() == bt_Edite)) {
			if (!(jt_nome.getText().equals(""))) {
				int posicaoArmazemVector = crudArmazem.procurarCodigo(temp, Integer.parseInt(jt_id.getText()));
				if (posicaoArmazemVector != -1) {
					Armazem armazemente = (Armazem) temp.get(posicaoArmazemVector);
						armazemente.setNome(jt_nome.getText());
//						armazemente.setBI(jt_tipoProduto.getText());
//						armazemente.setCell(jt_cell.getText());
//						armazemente.setSaldo(Double.parseDouble(jt_quantidade.getText()));
						temp.set(posicaoArmazemVector, armazemente);
						crudArmazem.gravarObjecto(temp);
						JOptionPane.showMessageDialog(null, "Cliente Modificado com sucesso!", "SUCESSO",JOptionPane.INFORMATION_MESSAGE); // OK
						this.setVisible(false);
						new ListaArmazem();
				} else {
					JOptionPane.showMessageDialog(null, "Cliente não foi encontrado", "ERROR", JOptionPane.ERROR_MESSAGE); // OK

				}
			} else {
				JOptionPane.showMessageDialog(null, "EXISTÊNCIA DE CAMPOS VAZIOS! PREENCHA TODOS OS CAMPOS", "",
						JOptionPane.WARNING_MESSAGE); // OK
				throw new CampoVazioException("CAMPOS VAZIOS");
			}
		}

	}
}

