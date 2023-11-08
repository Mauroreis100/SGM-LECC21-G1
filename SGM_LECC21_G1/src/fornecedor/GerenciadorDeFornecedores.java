package fornecedor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

public class GerenciadorDeFornecedores extends JFrame {
    // Componentes das Janelas

    private JTextField nomeField, biField, telefoneField, empresaField;
    private DefaultTableModel fornecedoresTableModel;
    private JTable fornecedoresTable;
    private static final String DATA_FILE = "fornecedores.dat";
    private int proximoID = 1; // Variável para rastrear o próximo ID disponível
    public GerenciadorDeFornecedores() {
        
        setTitle("Gerenciador de Fornecedores");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JMenuBar menuBar = new JMenuBar();
        // Menu "Arquivo"
        JMenu arquivoMenu = new JMenu("Arquivo");
        JMenuItem salvarItem = new JMenuItem("Salvar");
        JMenuItem carregarItem = new JMenuItem("Carregar");
        arquivoMenu.add(salvarItem);
        arquivoMenu.add(carregarItem);
        menuBar.add(arquivoMenu);

        // Menu "Ajuda"
        JMenu ajudaMenu = new JMenu("Ajuda");
        JMenuItem sobreItem = new JMenuItem("Sobre");
        ajudaMenu.add(sobreItem);
        menuBar.add(ajudaMenu);

        setJMenuBar(menuBar);
        // Painel para os campos de entrada
        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Nome:"));
        nomeField = new JTextField();
        inputPanel.add(nomeField);
        inputPanel.add(new JLabel("BI:"));
        biField = new JTextField();
        inputPanel.add(biField);
        inputPanel.add(new JLabel("Telefone:"));
        telefoneField = new JTextField();
        inputPanel.add(telefoneField);
        inputPanel.add(new JLabel("Empresa:"));
        empresaField = new JTextField();
        inputPanel.add(empresaField);

        // Painel de botões
        JPanel buttonPanel = new JPanel();
        JButton criarButton = new JButton("Criar");
        JButton pesquisarButton = new JButton("Pesquisar");
        JButton deletarButton = new JButton("Deletar");
        JButton alterarButton = new JButton("Alterar");
        JButton relatorioButton = new JButton("Relatório");
        buttonPanel.add(criarButton);
        buttonPanel.add(pesquisarButton);
        buttonPanel.add(deletarButton);
        buttonPanel.add(alterarButton);
        buttonPanel.add(relatorioButton);

        // Cabeçalhos para a tabela de fornecedores
        String[] colunas = {"ID", "Nome", "BI", "Telefone", "Empresa"};
        fornecedoresTableModel = new DefaultTableModel(colunas, 0);
        fornecedoresTable = new JTable(fornecedoresTableModel);

        // Adiciona a tabela a um JScrollPane
        JScrollPane tableScrollPane = new JScrollPane(fornecedoresTable);

        // Adicione um ouvinte ao botão "Criar"
        criarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                String bi = biField.getText();
                String telefone = telefoneField.getText();
                String empresa = empresaField.getText();

                String[] novoFornecedor = {String.valueOf(proximoID), nome, bi, telefone, empresa};
                fornecedoresTableModel.addRow(novoFornecedor);
                limparCampos();
                proximoID++; // Incremente o próximo ID disponível
            }
        });
        salvarItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                salvarDados();
            }
        });
        carregarItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                carregarDados();
            }
        });
        sobreItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Gerenciador de Fornecedores v1.0\nDesenvolvido por [Grupo de Programadores Lecc]", "Sobre", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        // Adicione um evento ao botão "Deletar"
        deletarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = fornecedoresTable.getSelectedRow();
                if (selectedRow != -1) {
                    fornecedoresTableModel.removeRow(selectedRow);
                }
            }
        });
        carregarDados();

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                salvarDados();
                System.exit(0);
            }
        });

       
    // Adiciona um evento ao botão "Pesquisar"
    pesquisarButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        String termoPesquisa = JOptionPane.showInputDialog("Digite um termo de pesquisa:");
        for (int row = 0; row < fornecedoresTableModel.getRowCount(); row++) {
            boolean encontrado = false;
            for (int col = 0; col < fornecedoresTableModel.getColumnCount(); col++) {
                String cellValue = String.valueOf(fornecedoresTableModel.getValueAt(row, col));
                if (cellValue.contains(termoPesquisa)) {
                    encontrado = true;
                    break;
                }
            }
            if (encontrado) {
                fornecedoresTable.setRowSelectionInterval(row, row);
            }
        }
    }
});


// Adiciona um evento ao botão "Alterar"
alterarButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        int selectedRow = fornecedoresTable.getSelectedRow();
        if (selectedRow != -1) {
            String nome = nomeField.getText();
            String bi = biField.getText();
            String telefone = telefoneField.getText();
            String empresa = empresaField.getText();

            fornecedoresTableModel.setValueAt(nome, selectedRow, 1); // Alterar o nome
            fornecedoresTableModel.setValueAt(bi, selectedRow, 2); // Alterar o BI
            fornecedoresTableModel.setValueAt(telefone, selectedRow, 3); // Alterar o telefone
            fornecedoresTableModel.setValueAt(empresa, selectedRow, 4); // Alterar a empresa

            limparCampos();
        }
    }
});


// Adiciona um ouvento ao botão "Relatório"
relatorioButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        // Implementa a lógica para gerar um relatório aqui
        // percorremos as linhas da tabela para coletar os dados dos fornecedores
        // e gera um relatório em formato desejado, como uma caixa de diálogo ou um arquivo.
        
        StringBuilder relatorio = new StringBuilder("Relatório de Fornecedores:\n");
        for (int row = 0; row < fornecedoresTableModel.getRowCount(); row++) {
            for (int col = 0; col < fornecedoresTableModel.getColumnCount(); col++) {
                String cellValue = String.valueOf(fornecedoresTableModel.getValueAt(row, col));
                relatorio.append(fornecedoresTableModel.getColumnName(col)).append(": ").append(cellValue).append("\n");
            }
            relatorio.append("\n");
        }
        JOptionPane.showMessageDialog(null, relatorio.toString(), "Relatório de Fornecedores", JOptionPane.INFORMATION_MESSAGE);
    }
});

        // Layout da janela
        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Exiba a janela
        setVisible(true);
    }

    private void limparCampos() {
        nomeField.setText("");
        biField.setText("");
        telefoneField.setText("");
        empresaField.setText("");
    }
    private void carregarDados() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            Vector<Vector<String>> data = (Vector<Vector<String>>) ois.readObject();
            for (Vector<String> rowData : data) {
                fornecedoresTableModel.addRow(rowData.toArray(new String[0]));
            }
            proximoID = fornecedoresTableModel.getRowCount() + 1;
        } catch (IOException | ClassNotFoundException e) {
            // Em caso de erro ao carregar os dados, continue com o próximo ID como 1
        }
    }

    private void salvarDados() {
        Vector<Vector<String>> data = new Vector<>();
        for (int row = 0; row < fornecedoresTableModel.getRowCount(); row++) {
            Vector<String> rowData = new Vector<>();
            for (int col = 0; col < fornecedoresTableModel.getColumnCount(); col++) {
                rowData.add(String.valueOf(fornecedoresTableModel.getValueAt(row, col)));
            }
            data.add(rowData);
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
            new GerenciadorDeFornecedores();
    }
}
