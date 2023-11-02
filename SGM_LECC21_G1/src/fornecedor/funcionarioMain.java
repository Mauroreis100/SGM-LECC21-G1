package fornecedor;

import javax.swing.SwingUtilities;

public class funcionarioMain {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new FornecedorApp();
            }
        });
    }
}
