package fornecedor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioApp {
    private List<Funcionario> funcionarios;
    private JFrame frame;

    public FuncionarioApp() {
        funcionarios = new ArrayList<>();
        initializeUI();
        loadFuncionarios();
    }

    private void initializeUI() {
        frame = new JFrame("Gestão de Funcionários");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
        JMenu menuPrincipal = new JMenu("Menu Principal");
        JMenu menuSair = new JMenu("Sair");

        JMenuItem sairItem = new JMenuItem("Sair do Programa");
        sairItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveFuncionarios();
                System.exit(0);
            }
        });
        menuSair.add(sairItem);

        menuBar.add(menuPrincipal);
        menuBar.add(menuSair);
        frame.setJMenuBar(menuBar);

        frame.add(createMainOptionsPanel(), BorderLayout.CENTER);
        frame.add(createMenuGestaoPanel(), BorderLayout.SOUTH);

        frame.setSize(400, 300);
        frame.setVisible(true);
    }

    private JPanel createMainOptionsPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 1));

        JButton criarButton = new JButton("Criar Funcionário");
        criarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showCreateFuncionarioForm();
            }
        });

        JButton editarButton = new JButton("Editar Funcionário");
        editarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showEditFuncionarioForm();
            }
        });

        JButton removerButton = new JButton("Remover Funcionário");
        removerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showRemoveFuncionarioForm();
            }
        });

        JButton listarButton = new JButton("Listar Funcionários");
        listarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showListFuncionarios();
            }
        });

        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showMainOptions();
            }
        });

        panel.add(criarButton);
        panel.add(editarButton);
        panel.add(removerButton);
        panel.add(listarButton);
        panel.add(voltarButton);

        return panel;
    }

    private JPanel createMenuGestaoPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel menuLabel = new JLabel("Menu de Gestão");
        menuLabel.setHorizontalAlignment(SwingConstants.CENTER);

        panel.add(menuLabel, BorderLayout.NORTH);

        return panel;
    }

    private void showMainOptions() {
        frame.getContentPane().removeAll();
        frame.add(createMainOptionsPanel(), BorderLayout.CENTER);
        frame.add(createMenuGestaoPanel(), BorderLayout.SOUTH);
        frame.revalidate();
        frame.repaint();
    }
    
    private void showCreateFuncionarioForm() {
        String nome = JOptionPane.showInputDialog("Nome do funcionário:");
        if (nome != null && !nome.trim().isEmpty()) {
            String funcao = JOptionPane.showInputDialog("Função do funcionário:");
            Funcionario funcionario = new Funcionario(nome, funcao);
            funcionarios.add(funcionario);
            saveFuncionarios();
            showMainOptions();
        }
    }

    private void showEditFuncionarioForm() {
        String nome = JOptionPane.showInputDialog("Nome do funcionário para editar:");
        if (nome != null && !nome.trim().isEmpty()) {
            Funcionario funcionario = findFuncionarioByNome(nome);
            if (funcionario != null) {
                String novoNome = JOptionPane.showInputDialog("Novo nome do funcionário (ou deixe em branco para não alterar):");
                if (novoNome != null) {
                    if (!novoNome.trim().isEmpty()) {
                        funcionario.setNome(novoNome);
                    }
                    String novaFuncao = JOptionPane.showInputDialog("Nova função do funcionário (ou deixe em branco para não alterar):");
                    if (novaFuncao != null) {
                        if (!novaFuncao.trim().isEmpty()) {
                            funcionario.setFuncao(novaFuncao);
                        }
                    }
                    saveFuncionarios();
                }
            }
            showMainOptions();
        }
    }

    private void showRemoveFuncionarioForm() {
        String nome = JOptionPane.showInputDialog("Nome do funcionário para remover:");
        if (nome != null && !nome.trim().isEmpty()) {
            Funcionario funcionario = findFuncionarioByNome(nome);
            if (funcionario != null) {
                funcionarios.remove(funcionario);
                saveFuncionarios();
            }
            showMainOptions();
        }
    }

    private void showListFuncionarios() {
        StringBuilder funcionariosText = new StringBuilder("Funcionários:\n");
        for (Funcionario funcionario : funcionarios) {
            funcionariosText.append(funcionario.getNome()).append(" - ").append(funcionario.getFuncao()).append("\n");
        }
        JTextArea textArea = new JTextArea(funcionariosText.toString());
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
        frame.add(createMenuGestaoPanel(), BorderLayout.SOUTH);
        frame.revalidate();
        frame.repaint();
    }

    private Funcionario findFuncionarioByNome(String nome) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getNome().equals(nome)) {
                return funcionario;
            }
        }
        return null;
    }

    private void saveFuncionarios() {
        try (PrintWriter writer = new PrintWriter("fornecedores.dat")) {
            for (Funcionario funcionario : funcionarios) {
                writer.println(funcionario.getNome() + ";" + funcionario.getFuncao());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadFuncionarios() {
        try (BufferedReader reader = new BufferedReader(new FileReader("fornecedores.dat"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 2) {
                    Funcionario funcionario = new Funcionario(parts[0], parts[1]);
                    funcionarios.add(funcionario);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new FuncionarioApp();
            }
        });
    }

    class Funcionario {
        private String nome;
        private String funcao;

        public Funcionario(String nome, String funcao) {
            this.nome = nome;
            this.funcao = funcao;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getFuncao() {
            return funcao;
        }

        public void setFuncao(String funcao) {
            this.funcao = funcao;
        }
    }
}
