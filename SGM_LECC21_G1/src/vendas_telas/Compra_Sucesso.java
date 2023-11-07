package vendas_telas;

import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.*;

import carrinho.Carritxo;
import clientes.Cliente;
import menu.Menu__Prin;
import produto.Produto;
import vendas.OperacoesVenda_Dinheiro;
import vendas.Venda_Dinheiro;

public class Compra_Sucesso extends JFrame implements ActionListener {
	OperacoesVenda_Dinheiro opVendas=new OperacoesVenda_Dinheiro();
//	Venda_Dinheiro ultima_venda=(Venda_Dinheiro)opVendas.recuperarVendas().lastElement();
	Vector vendas_Vector = opVendas.recuperarVendas();

	private JButton bt_fechar;
	private JTextArea ta_recibo;

	public Compra_Sucesso(Cliente cliente,Vector carrinho) {
//		itens.add(new Produto(1, 1, "Maçã", 2, 20, 2, "Fornecedor"));
//		itens.add(new Produto(1, 1, "Maçã", 2, 20, 2, "Fornecedor"));
//		itens.add(new Produto(1, 1, "Maçã", 2, 20, 2, "Fornecedor"));
		double total=0;
	for(int i=0;i<carrinho.size();i++) {
		total+=((Carritxo)carrinho.get(i)).getTotal();
	}
		Venda_Dinheiro ultima_venda = new Venda_Dinheiro(1, cliente, carrinho, total);
		if(vendas_Vector==null) {
			vendas_Vector=new Vector();
		}
		vendas_Vector.add(ultima_venda);
		ta_recibo = new JTextArea();

		bt_fechar=new JButton("FECHAR");
		
		
		String Venda = ultima_venda.toString()+"\n----------------------------------------------------------------\nTOTAL PAGO:\t\t\t\t\t"+ultima_venda.getTotal()+"\n\n----------------------\nEmail:clickme@clike.co.mz\nTel: +2588373812";
		ta_recibo.setFont(new Font("Monospaced", Font.PLAIN, 15));
		ta_recibo.setText("\t" + Venda + "\t");
		
		ta_recibo.setEditable(false);
		// ---------------------------------------------------------------------------------------------------------------
		this.setTitle("Recibo");// O tittulo da janela.
		this.setSize(720, 700);// Width and Height em pixels.[Comprimento, Largura]
		this.setLocation(100, 100);// Onde o programa vai arrancar
		this.setLocationRelativeTo(null);// Onde o programa vai arrancar
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Quando o utilizador clicar no x. Mata todos os frames
		// --------------------------------------------------------------------------------------------------------------
		this.add(new JScrollPane(ta_recibo));
		this.add(bt_fechar,  BorderLayout.SOUTH);
		bt_fechar.addActionListener(this);
		
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bt_fechar) {
			opVendas.gravarVendas(vendas_Vector);
			//fazer operações disto onde podes ler too
			this.setVisible(false);
			new Menu__Prin();
		}
	}

}
