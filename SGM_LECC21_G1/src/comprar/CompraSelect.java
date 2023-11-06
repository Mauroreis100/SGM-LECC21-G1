package comprar;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import menu.Menu__Prin;

public class CompraSelect extends JFrame implements ActionListener {
	private JButton jb_nome;
	private JButton jb_cell;

	private JLabel jl_f;

	private JPanel jp;

	public CompraSelect() {
		jl_f = new JLabel("Escolha um opção");
		jb_nome = new JButton("Fazer Compra");
		jb_cell = new JButton("Ver Historico");

		jb_nome.addActionListener(this);
		jb_cell.addActionListener(this);
		// -----DEFINIÇÕES DA JANELA*INICIO-------
		this.setTitle("Compra");// O tittulo da janela.
		this.setSize(300, 100);// Width and Height em pixels.[Comprimento, Largura]
		this.setLocation(980, 500);// Onde o programa vai arrancar
		this.setLocationRelativeTo(null);// Onde o programa vai arrancar
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);// Quando o utilizador clicar no x. Mata todos os
		this.setResizable(false);
		this.setLayout(new FlowLayout(FlowLayout.CENTER));

		jp = new JPanel();
		jp.add(jb_nome);
		jp.add(jb_cell);

		this.add(jl_f);
		this.add(jp);

		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == jb_nome) {
			this.setVisible(false); 
			new CompraSele();
		}
		if (e.getSource() == jb_cell) {

		}
	}
}
