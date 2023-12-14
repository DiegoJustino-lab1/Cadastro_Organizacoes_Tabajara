package BasedeDados;

public class PessoaJuri extends Cliente {
    private String CNPJ;
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    private String razaoSocial;
    private int prazoMaximo;

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String cNPJ) {
        CNPJ = cNPJ;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public int getPrazoMaximo() {
        return prazoMaximo;
    }

    public void setPrazoMaximo(int prazoMaximo) {
        this.prazoMaximo = prazoMaximo;
    }

    @Override
    public String ParaString() {

        return "pj," + getNome() + "," + getEndereco().ParaString() + ","
                + getDataCadastro() + "," + getCNPJ() + "," + getRazaoSocial() + ","
                + getPrazoMaximo();

    }
}
