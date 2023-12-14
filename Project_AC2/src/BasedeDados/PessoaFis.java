package BasedeDados;

public class PessoaFis extends Cliente {

    private String CPF;
    private int qtdParcelas;
    // razão social e o prazo máximo (em dias) para pagamento da compra

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getCPF() {
        return CPF;
    }

    public void setQtdParcelas(int qtdParcelas) {
        this.qtdParcelas = qtdParcelas;
    }

    public int getQtdParcelas() {
        return qtdParcelas;
    }

    @Override
    public String ParaString() {

        return "pf," + getNome() + ","
                + getDataCadastro() + "," + getCPF() + "," + getQtdParcelas() + "," + getEndereco().ParaString();

    }

    // public void pessoatipo() {
    // Scanner sc = new Scanner(System.in);
    // PessoaFisJuri pfj = new PessoaFisJuri();
    // System.out.println("CPF OU CNPJ do Cliente: ");
    // System.out.println("1 - CPF \n2 - CNPJ:");
    // i = sc.nextInt();
    // // swicth case com 1 para cpf 2 para cnpj
    // switch (i) {
    // case 1:
    // System.out.println("CPF do Cliente:");
    // pfj.setCPF(sc.next());
    // // qual a quantidade máxima de parcelas da compra
    // System.out.println("Quantidade máxima de parcelas da compra: ");
    // pfj.setqtdParcelas(sc.nextInt());
    // break;
    // case 2:
    // System.out.println("CNPJ do Cliente:");
    // pfj.setCNPJ(sc.next());
    // System.out.println("Razão Social do Cliente:");
    // pfj.setrazaoSocial(sc.next());
    // System.out.println("Prazo máximo para pagamento da compra em dias: ");
    // pfj.setprazoMaximo(sc.nextInt());
    // break;
    // default:
    // sc.close();
    // }

    // }
}
