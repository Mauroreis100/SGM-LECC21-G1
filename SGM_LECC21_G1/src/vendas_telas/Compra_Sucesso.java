package vendas_telas;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import javax.swing.*;

import clientes.Cliente;
import produto.Produto;
import vendas.OperacoesVenda_Dinheiro;
import vendas.Venda_Dinheiro;

public class Compra_Sucesso extends JFrame {
//	OperacoesVenda_Dinheiro vendas=new OperacoesVenda_Dinheiro();
//	Venda_Dinheiro ultima_venda=(Venda_Dinheiro)vendas.recuperarVendas().lastElement();
	Vector itens=new Vector();
	

	private JButton bt_fechar;
	private JTextArea ta_recibo;
	Compra_Sucesso() {
		itens.add(new Produto(1,1,"Maçã",2,20,2,"Fornecedor"));
		itens.add(new Produto(1,1,"Maçã",2,20,2,"Fornecedor"));
		itens.add(new Produto(1,1,"Maçã",2,20,2,"Fornecedor"));
		Venda_Dinheiro ultima_venda=new Venda_Dinheiro(1,new Cliente(1,"Mauro","213","3112",3000),itens,30000);;
		ta_recibo=new JTextArea();
		String Venda=ultima_venda.toString();
		ta_recibo.setFont(new Font("Monospaced",Font.PLAIN,15));
		ta_recibo.setText("\t"+Venda+"\t");
		ta_recibo.setEditable(false);
		// ---------------------------------------------------------------------------------------------------------------
		this.setTitle("Recibo");// O tittulo da janela.
		this.setSize(500, 300);// Width and Height em pixels.[Comprimento, Largura]
		this.setLocation(100, 100);// Onde o programa vai arrancar
		this.setLocationRelativeTo(null);// Onde o programa vai arrancar
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Quando o utilizador clicar no x. Mata todos os frames
		// --------------------------------------------------------------------------------------------------------------
this.add(ta_recibo);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new Compra_Sucesso();
	}

}
