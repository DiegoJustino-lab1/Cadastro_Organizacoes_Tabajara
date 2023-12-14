package BasedeDados;

import java.time.LocalDate;

public class efetuarcompra extends Item {

    private int quantidade;
    private String nomeProduto;

    public int getQuantidade() {
        return quantidade;
    }

    public LocalDate getDataAtual() {
        return dataAtual;
    }

    public void setDataAtual(LocalDate dataAtual) {
        this.dataAtual = dataAtual;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    private double valorUnitario;
    private double valorTotal;
    private LocalDate dataAtual;
    private String descricao;
    private String CPF;
    private String CNPJ;
    // pegar a variavel codigo do item da lista de itens
    private int codigo;

    public int getCodigo() {
        return codigo;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String cPF) {
        CPF = cPF;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String cNPJ) {
        CNPJ = cNPJ;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public efetuarcompra() {
        dataAtual = LocalDate.now();
        System.out.println("A data atual é: " + dataAtual);
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    // criar um override para o toString
    @Override
    public String toString() {
        return quantidade +
                "," + nomeProduto +
                "," + valorUnitario +
                "," + valorTotal +
                "," + codigo +
                "," + dataAtual
                + "," + descricao
                + "," + CPF
                + "," + CNPJ;
    }
    /*
     * public static void efetuarCompra(Scanner sc, ArrayList<Cliente> clientes,
     * ArrayList<Item> itens) {
     * private int quantidade;
     * if (itens.isEmpty()) {
     * System.out.println("A lista de produtos está vazia.");
     * return;
     * } else {
     * 
     * // System.out.println("Digite o nome do cliente: ");
     * // String nome = sc.nextLine();
     * 
     * }
     * }
     */
}
