package template;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Janela implements ActionListener{
	private JFrame jf_janela;
	
	private JButton bt_botao;
	
	private JMenu jm_menu;
	
	private JMenuItem jmi_menuItem;
	
	private JPanel jp_panel;
	
	public Janela() {
		jf_janela=new JFrame();
		
		//-----DEFINIÇÕES DA JANELA*INICIO-------
		jf_janela.setTitle("Template de JForm");// O tittulo da janela.
		jf_janela.setSize(500, 300);// Width and Height em pixels.[Comprimento, Largura]
		jf_janela.setLocation(100, 100);// Onde o programa vai arrancar
		jf_janela.setLocationRelativeTo(null);// Onde o programa vai arrancar
		jf_janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Quando o utilizador clicar no x. Mata todos os frames				
		//------DEFINIÇÕES DA JANELA*FIM--------
		
		jf_janela.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
