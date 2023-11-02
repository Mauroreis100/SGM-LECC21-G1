package fornecedor;
import java.io.Serializable;

public class Fornecedor implements Serializable {
    private String nome;
    private String funcao;
    private String empresa;
    private String produtoFornecido;
    private int quantidade;
    private double valorStock;
    private double valorUnitario;
    private double precoFinal;

    public Fornecedor(String nome, String funcao) {
        this.nome = nome;
        this.funcao = funcao;
        this.empresa = "";
        this.produtoFornecido = "";
        this.quantidade = 0;
        this.valorStock = 0.0;
        this.valorUnitario = 0.0;
        this.precoFinal = 0.0;
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

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getProdutoFornecido() {
        return produtoFornecido;
    }

    public void setProdutoFornecido(String produtoFornecido) {
        this.produtoFornecido = produtoFornecido;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorStock() {
        return valorStock;
    }

    public void setValorStock(double valorStock) {
        this.valorStock = valorStock;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public double getPrecoFinal() {
        return precoFinal;
    }

    public void setPrecoFinal(double precoFinal) {
        this.precoFinal = precoFinal;
    }

    public String toCSV() {
        return nome + ";" + funcao + ";" + empresa + ";" + produtoFornecido + ";" + quantidade + ";" + valorStock + ";" + valorUnitario + ";" + precoFinal;
    }

    public static Fornecedor fromCSV(String csv) {
        String[] parts = csv.split(";");
        if (parts.length == 8) {
            Fornecedor fornecedor = new Fornecedor(parts[0], parts[1]);
            fornecedor.setEmpresa(parts[2]);
            fornecedor.setProdutoFornecido(parts[3]);
            fornecedor.setQuantidade(Integer.parseInt(parts[4]));
            fornecedor.setValorStock(Double.parseDouble(parts[5]));
            fornecedor.setValorUnitario(Double.parseDouble(parts[6]));
            fornecedor.setPrecoFinal(Double.parseDouble(parts[7]));
            return fornecedor;
        }
        return null;
    }
}
