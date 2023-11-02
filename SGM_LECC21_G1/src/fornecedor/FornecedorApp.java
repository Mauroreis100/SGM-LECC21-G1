package fornecedor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Vector;

public class FornecedorApp {
    private Vector<Fornecedor> fornecedores;
    private JFrame frame;

    public FornecedorApp() {
        fornecedores = new Vector<>();
        initializeUI();
        loadFornecedores();
    }

    private void initializeUI() {
        frame = new JFrame("Gestão de Fornecedores");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
        JMenu menuPrincipal = new JMenu("Menu Principal");
        JMenu menuGestao = new JMenu("Gestão");
        JMenu menuSair = new JMenu("Sair");

        JMenuItem criarFornecedorItem = new JMenuItem("Criar Fornecedor");
        criarFornecedorItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showCreateFornecedorForm();
            }
        });

        JMenuItem editarFornecedorItem = new JMenuItem("Editar Fornecedor");
        editarFornecedorItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showEditFornecedorForm();
            }
        });

        JMenuItem removerFornecedorItem = new JMenuItem("Remover Fornecedor");
        removerFornecedorItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showRemoveFornecedorForm();
            }
        });

        JMenuItem listarFornecedoresItem = new JMenuItem("Listar Fornecedores");
        listarFornecedoresItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showListFornecedores();
            }
        });

        JMenuItem sairItem = new JMenuItem("Sair do Programa");
        sairItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveFornecedores();
                System.exit(0);
            }
        });

        menuGestao.add(criarFornecedorItem);
        menuGestao.add(editarFornecedorItem);
        menuGestao.add(removerFornecedorItem);
        menuGestao.add(listarFornecedoresItem);
        menuSair.add(sairItem);

        menuBar.add(menuPrincipal);
        menuBar.add(menuGestao);
        menuBar.add(menuSair);
        frame.setJMenuBar(menuBar);

        frame.add(createMainOptionsPanel(), BorderLayout.CENTER);

        frame.setSize(400, 300);
        frame.setVisible(true);
    }

    private JPanel createMainOptionsPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 1));

        JButton criarFornecedorButton = new JButton("Criar Fornecedor");
        criarFornecedorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showCreateFornecedorForm();
            }
        });

        JButton editarButton = new JButton("Editar Fornecedor");
        editarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showEditFornecedorForm();
            }
        });

        JButton removerButton = new JButton("Remover Fornecedor");
        removerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showRemoveFornecedorForm();
            }
        });

        JButton listarButton = new JButton("Listar Fornecedores");
        listarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showListFornecedores();
            }
        });

        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showMainOptions();
            }
        });

        panel.add(criarFornecedorButton);
        panel.add(editarButton);
        panel.add(removerButton);
        panel.add(listarButton);
        panel.add(voltarButton);

        return panel;
    }

    private void showMainOptions() {
        frame.getContentPane().removeAll();
        frame.add(createMainOptionsPanel(), BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    private void showCreateFornecedorForm() {
        String nome = JOptionPane.showInputDialog("Nome do fornecedor:");
        if (nome != null && !nome.trim().isEmpty()) {
            Fornecedor fornecedor = new Fornecedor(nome, "");
    
            int respostaEmpresa = JOptionPane.showConfirmDialog(null, "É uma empresa?", "Tipo de Fornecedor", JOptionPane.YES_NO_OPTION);
            if (respostaEmpresa == JOptionPane.YES_OPTION) {
                String nomeEmpresa = JOptionPane.showInputDialog("Nome da empresa:");
                if (nomeEmpresa != null && !nomeEmpresa.trim().isEmpty()) {
                    fornecedor.setEmpresa(nomeEmpresa);
                }
            }
    
            String produtoFornecido = JOptionPane.showInputDialog("O que ele vai fornecer?");
            int quantidade = 0;
            double valorUnitario = 0.0;
    
            try {
                String quantidadeStr = JOptionPane.showInputDialog("Quantidade:");
                if (quantidadeStr != null && !quantidadeStr.trim().isEmpty()) {
                    quantidade = Integer.parseInt(quantidadeStr);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Quantidade inválida. Será definida como zero.");
            }
            
            try {
                String valorUnitarioStr = JOptionPane.showInputDialog("Valor de cada produto fornecido:");
                if (valorUnitarioStr != null && !valorUnitarioStr.trim().isEmpty()) {
                    valorUnitario = Double.parseDouble(valorUnitarioStr);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Valor de estoque inválido. Será definido como zero.");
            }
    
            // Cálculo do preço final
            double precoFinal = quantidade * valorUnitario;
    
            fornecedor.setProdutoFornecido(produtoFornecido);
            fornecedor.setQuantidade(quantidade);
            fornecedor.setValorUnitario(valorUnitario);
            fornecedor.setPrecoFinal(precoFinal);
    
            fornecedores.add(fornecedor);
            saveFornecedores();
            showMainOptions();
        }
    }

    private void showEditFornecedorForm() {
        String nome = JOptionPane.showInputDialog("Nome do fornecedor para editar:");
        if (nome != null && !nome.trim().isEmpty()) {
            Fornecedor fornecedor = findFornecedorByNome(nome);
            if (fornecedor != null) {
                String novoNome = JOptionPane.showInputDialog("Novo nome do fornecedor (ou deixe em branco para não alterar):");
                if (novoNome != null) {
                    if (!novoNome.trim().isEmpty()) {
                        fornecedor.setNome(novoNome);
                    }
                    String novaEmpresa = JOptionPane.showInputDialog("Nova empresa (ou deixe em branco para não alterar):");
                    if (novaEmpresa != null) {
                        if (!novaEmpresa.trim().isEmpty()) {
                            fornecedor.setEmpresa(novaEmpresa);
                        }
                    }
                    String novoProdutoFornecido = JOptionPane.showInputDialog("Novo produto fornecido (ou deixe em branco para não alterar):");
                    if (novoProdutoFornecido != null) {
                        if (!novoProdutoFornecido.trim().isEmpty()) {
                            fornecedor.setProdutoFornecido(novoProdutoFornecido);
                        }
                    }
                    try {
                        String novaQuantidadeStr = JOptionPane.showInputDialog("Nova quantidade (ou deixe em branco para não alterar):");
                        if (novaQuantidadeStr != null && !novaQuantidadeStr.trim().isEmpty()) {
                            int novaQuantidade = Integer.parseInt(novaQuantidadeStr);
                            fornecedor.setQuantidade(novaQuantidade);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Quantidade inválida. Não será alterada.");
                    }
                    try {
                        String novoValorUnitarioStr = JOptionPane.showInputDialog("Novo valor unitário (ou deixe em branco para não alterar):");
                        if (novoValorUnitarioStr != null && !novoValorUnitarioStr.trim().isEmpty()) {
                            double novoValorUnitario = Double.parseDouble(novoValorUnitarioStr);
                            fornecedor.setValorUnitario(novoValorUnitario);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Valor unitário inválido. Não será alterado.");
                    }
                    // Atualização do preço final
                    double precoFinal = fornecedor.getQuantidade() * fornecedor.getValorUnitario();
                    fornecedor.setPrecoFinal(precoFinal);
                    saveFornecedores();
                }
            }
            showMainOptions();
        }
    }

    private void showRemoveFornecedorForm() {
        String nome = JOptionPane.showInputDialog("Nome do fornecedor para remover:");
        if (nome != null && !nome.trim().isEmpty()) {
            Fornecedor fornecedor = findFornecedorByNome(nome);
            if (fornecedor != null) {
                fornecedores.remove(fornecedor);
                saveFornecedores();
            }
            showMainOptions();
        }
    }

    private void showListFornecedores() {
        StringBuilder fornecedoresText = new StringBuilder("Fornecedores:\n");
        for (Fornecedor fornecedor : fornecedores) {
            fornecedoresText.append("Nome: ").append(fornecedor.getNome()).append("\n");
            if (fornecedor.getEmpresa() != null && !fornecedor.getEmpresa().isEmpty()) {
                fornecedoresText.append("Empresa: ").append(fornecedor.getEmpresa()).append("\n");
            } else {
                fornecedoresText.append("Empresa: Não especificada\n");
            }
            fornecedoresText.append("Produto Fornecido: ").append(fornecedor.getProdutoFornecido()).append("\n");
            fornecedoresText.append("Quantidade: ").append(fornecedor.getQuantidade()).append("\n");
            fornecedoresText.append("Valor Unitário: ").append(fornecedor.getValorUnitario()).append("\n");
            fornecedoresText.append("Preço Final: ").append(fornecedor.getPrecoFinal()).append("\n\n");
        }
        JTextArea textArea = new JTextArea(fornecedoresText.toString());
        textArea.setEditable(false);

        JButton voltarButton = new JButton("Voltar ao Menu Principal");
        voltarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showMainOptions();
            }
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        panel.add(voltarButton, BorderLayout.SOUTH);

        frame.getContentPane().removeAll();
        frame.add(panel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    private Fornecedor findFornecedorByNome(String nome) {
        for (Fornecedor fornecedor : fornecedores) {
            if (fornecedor.getNome().equals(nome)) {
                return fornecedor;
            }
        }
        return null;
    }

    private void saveFornecedores() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("fornecedores.dat"))) {
            oos.writeObject(fornecedores);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadFornecedores() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("fornecedores.dat"))) {
            Vector<Fornecedor> savedFornecedores = (Vector<Fornecedor>) ois.readObject();
            fornecedores.clear();
            fornecedores.addAll(savedFornecedores);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
