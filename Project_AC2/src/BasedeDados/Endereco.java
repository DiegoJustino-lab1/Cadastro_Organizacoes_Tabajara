package BasedeDados;

public class Endereco {
    private String rua;
    private int numero;
    private String bairro;
    private String CEP;
    private String cidade;
    private String estado;

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String cEP) {
        CEP = cEP;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String ParaString() {
        // PessoaFisJuri pfj = new PessoaFisJuri();

        return getRua()
                + "," + getNumero()
                + "," + getBairro()
                + "," + getCEP()
                + "," + getCidade()
                + "," + getEstado();

        // System.out.println("CPF do Cliente: " + pfj.getCPF());
        // System.out.println("CNPJ do Cliente: " + pfj.getCNPJ());
        // System.out.println("Razão Social do Cliente: " + pfj.getrazaoSocial());
        // System.out.println("Quantidade máxima de parcelas da compra: " +
        // pfj.getqtdParcelas());
        // System.out.println("Prazo máximo para pagamento da compra em dias: " +
        // pfj.getprazoMaximo());
    }
}
