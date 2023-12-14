// Importar as bibliotecas necessárias
package BasedeDados;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

// Definir a classe principal
public class App {
    // Criar uma lista para armazenar endereços e um Scanner para entrada de dados
    static ArrayList<Endereco> enderecos = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    // Método principal
    public static void main(String[] args) throws IOException {

        // Criando listas para armazenar clientes, itens e compras
        ArrayList<Cliente> clientes = new ArrayList<>();
        ArrayList<Item> itens = new ArrayList<>();
        ArrayList<efetuarcompra> compras = new ArrayList<>();
        // Criando objetos para Pessoa Física e Pessoa Jurídica
        PessoaFis pf = new PessoaFis();
        PessoaJuri pj = new PessoaJuri();

        // Leitura do documento baseDados/clientes.txt
        try (BufferedReader br = new BufferedReader(new FileReader("BaseDeDados/Clientes.txt"))) {
            String linha;
            // Lendo cada linha do arquivo
            while ((linha = br.readLine()) != null) {
                // Divisão da linha em campos separados por vírgula
                String[] campos = linha.split(",");
                // Verificar se o cliente é uma pessoa física
                if (campos[0].equals("pf")) {
                    // Cria-se um novo objeto PessoaFis e Endereco e se preenche com os dados
                    pf = new PessoaFis();
                    Endereco endereco = new Endereco();
                    pf.setNome(campos[1]);
                    pf.setDataCadastro(campos[2]);
                    pf.setCPF(campos[3]);
                    pf.setQtdParcelas(Integer.parseInt(campos[4]));
                    endereco.setNumero(Integer.parseInt(campos[6]));
                    endereco.setRua(campos[5]);
                    endereco.setBairro(campos[7]);
                    endereco.setCEP(campos[8]);
                    endereco.setCidade(campos[9]);
                    endereco.setEstado(campos[10]);
                    // Adição do cliente à lista de clientes
                    clientes.add(pf);
                } else {
                    // Trate o caso para "Pessoa Jurídica" aqui
                    pj = new PessoaJuri();
                    Endereco endereco = new Endereco();
                    pj.setNome(campos[1]);
                    pj.setCNPJ(campos[9]);
                    pj.setRazaoSocial(campos[10]);
                    pj.setPrazoMaximo(Integer.parseInt(campos[11]));
                    endereco.setNumero(Integer.parseInt(campos[3]));
                    endereco.setRua(campos[2]);
                    endereco.setBairro(campos[4]);
                    endereco.setCEP(campos[5]);
                    endereco.setCidade(campos[6]);
                    endereco.setEstado(campos[7]);
                    pj.setDataCadastro(campos[8]);
                    // Adição do cliente à lista de clientes
                    clientes.add(pj);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Leitura do documento baseDados/produtos.txt
        // Início do bloco try-catch para leitura do arquivo de itens
        try (BufferedReader br = new BufferedReader(new FileReader("BaseDeDados/Itens.txt"))) {
            String linha;
            // Loop para ler cada linha do arquivo
            while ((linha = br.readLine()) != null) {
                // Separação dos campos por vírgula
                String[] campos = linha.split(",");
                // Criação de um novo objeto Item
                Item item = new Item();
                item.setCodigo(Integer.parseInt(campos[0]));
                item.setNomeProduto(campos[1]);

                item.setValorUnitario(Double.parseDouble(campos[2]));

                item.setDataValidade(Integer.parseInt(campos[4]));
                // Adição do item à lista de itens
                itens.add(item);
            }
        } catch (IOException e) {
            // Impressão do stack trace em caso de exceção
            e.printStackTrace();
        }

        // Leitura do documento baseDados/compras.txt
        // Início do bloco try-catch para leitura do arquivo de compras
        try (BufferedReader br = new BufferedReader(new FileReader("BasedeDados/Compras.txt"))) {
            String linha;
            // Loop para ler cada linha do arquivo
            while ((linha = br.readLine()) != null) {
                // Separação dos campos por vírgula
                String[] campos = linha.split(",");
                // Criação de um novo objeto de compra
                efetuarcompra compra = new efetuarcompra();

                // Definição dos atributos da compra com base nos campos lidos
                compra.setQuantidade(Integer.parseInt(campos[0]));
                compra.setNomeProduto(campos[1]);
                compra.setValorUnitario(Double.parseDouble(campos[2]));
                compra.setValorTotal(Double.parseDouble(campos[3]));
                compra.setCodigo(Integer.parseInt(campos[4]));
                compra.setDescricao(campos[5]);
                compra.setCPF(campos[6]);
                compra.setCNPJ(campos[7]);
                // Adição da compra à lista de compras
                compras.add(compra);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Método para escrever as compras no arquivo
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("BasedeDados/Compras.txt"))) {
            for (efetuarcompra compra : compras) {
                bw.write(compra.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Impressão do menu de operações
        System.out.println("Selecione a operação desejada: ");
        System.out.println("1 - Cadastrar cliente");
        System.out.println("2 - Deletar cliente pelo CPF ou CNPJ");
        System.out.println("3 - Deletar cliente pelo nome");
        System.out.println("4 - Cadastro de Produtos");
        System.out.println("5 - Efetuação de uma compra");
        System.out.println("6 - Atualização da situação de pagamento de uma compra");
        System.out.println("7 - Relatório de clientes");
        System.out.println("8 - Sair do sistema");

        String input;
        // Início do loop do menu de operações
        do {
            // Definição das opções do menu
            Object[] options = { "Cadastrar cliente", "Deletar cliente pelo CPF ou CNPJ", "Deletar cliente pelo nome",
                    "Cadastro de Produtos", "Efetuação de uma compra",
                    "Atualização da situação de pagamento de uma compra",
                    "Relatório de clientes", "Sair do sistema" };

            // Exibição do menu de operações
            input = (String) JOptionPane.showInputDialog(null, "Selecione a operação desejada:", "Menu",
                    JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            // Switch case para tratar a opção escolhida pelo usuário
            switch (input) {
                // Chamada do método para cadastrar cliente
                case "Cadastrar cliente":
                    cadastrarCliente();
                    break;

                case "Deletar cliente pelo CPF ou CNPJ":
                    // Solicitação do CPF ou CNPJ do cliente a ser deletado
                    System.out.println("Digite o CPF ou CNPJ do cliente que deseja deletar: ");
                    String cpfCnpj = sc.next();

                    // Loop para encontrar o cliente na lista de clientes
                    for (int l = 0; l < clientes.size(); l++) {
                        Cliente cliente = clientes.get(l);
                        // Verificação se o cliente é uma pessoa física
                        if (cliente instanceof PessoaFis) {
                            PessoaFis pfParaDeletar = (PessoaFis) cliente;
                            // Verificação se o CPF do cliente corresponde ao CPF informado
                            if (pfParaDeletar.getCPF().equals(cpfCnpj)) {
                                // Remoção do cliente da lista de clientes
                                clientes.remove(cliente);
                                // Chamada do método para escrever os clientes no arquivo
                                escreverClientesNoArquivo(clientes);
                                // Mensagem de sucesso na deleção do cliente
                                System.out.println("Cliente deletado com sucesso.");
                                // Interrupção do loop
                                break;
                            }
                        } else if (cliente instanceof PessoaJuri) {
                            PessoaJuri pjParaDeletar = (PessoaJuri) cliente;
                            if (pjParaDeletar.getCNPJ().equals(cpfCnpj)) {
                                clientes.remove(cliente);
                                escreverClientesNoArquivo(clientes);
                                System.out.println("Cliente deletado com sucesso.");
                                break;
                            }
                        } else {
                            System.out.println("Cliente não encontrado.");
                        }
                    }
                    break;

                case "Deletar cliente pelo nome":
                    System.out.println("Digite o nome do cliente que deseja deletar: ");
                    String nome = sc.next();

                    for (int i = 0; i < clientes.size(); i++) {
                        Cliente cliente = clientes.get(i);
                        if (cliente.getNome().equalsIgnoreCase(nome)) {
                            clientes.remove(i);
                            escreverClientesNoArquivo(clientes);
                            System.out.println("Cliente deletado com sucesso.");
                            break;
                        }
                    }

                    break;
                case "Cadastro de Produtos":
                    cadastrarItem();
                    break;
                case "Efetuação de uma compra":

                    File efetuarcompraFile = new File("BaseDeDados\\Compras.txt");
                    efetuarcompraFile.createNewFile();
                    LocalDate dataActual = LocalDate.now();

                    System.out.println("Digite o nome do cliente: ");
                    String nomeCliente = sc.next();
                    Cliente cliente = null;
                    // achar cpf ou cnpj do cliente

                    if (cliente instanceof PessoaFis) {
                        PessoaFis pfP = (PessoaFis) cliente;
                        pfP.setCPF(pfP.getCPF());
                    } else if (cliente instanceof PessoaJuri) {
                        PessoaJuri pjParaDeletar = (PessoaJuri) cliente;
                        pjParaDeletar.setCNPJ(pjParaDeletar.getCNPJ());
                    }
                    for (int x = 0; x < clientes.size(); x++) {
                        Cliente clienteLoop = clientes.get(x);
                        if (clienteLoop.getNome().equalsIgnoreCase(nomeCliente)) {
                            cliente = clienteLoop;
                            System.out.println("Cliente encontrado.");
                            System.out.println("Digite o nome do produto: ");
                            String nomeProduto = sc.next();
                            Item item = null;
                            for (int j = 0; j < itens.size(); j++) {
                                Item itemLoop = itens.get(j);
                                if (itemLoop.getNomeProduto().equalsIgnoreCase(nomeProduto)) {
                                    item = itemLoop;
                                    System.out.println("Produto encontrado.");

                                    // pegar o valor unitario do item no arquivo de itens pelo nome do produto
                                    for (int k = 0; k < itens.size(); k++) {
                                        Item itemLoop2 = itens.get(k);
                                        if (itemLoop2.getNomeProduto().equalsIgnoreCase(nomeProduto)) {
                                            itemLoop2.getValorUnitario();
                                        }
                                    }
                                    System.out.println("Valor unitário: " + itemLoop.getValorUnitario());

                                    System.out.println("Digite ano de validade: ");
                                    int dataValidade = sc.nextInt();
                                    System.out.println("Digite mês de validade: ");
                                    int dataValidadeMes = sc.nextInt();
                                    // ver se o produto está vencido comparado com a data atual usando mes e ano

                                    if (dataValidade < dataActual.getYear()) {
                                        System.out.println("Produto vencido.");
                                    } else if (dataValidade == dataActual.getYear()) {
                                        if (dataValidadeMes < dataActual.getMonthValue()) {
                                            System.out.println("Produto vencido.");
                                        } else {
                                            System.out.println("Produto não vencido.");
                                        }
                                    }
                                    for (int k = 0; k < itens.size(); k++) {
                                        Item itemLoop2 = itens.get(k);
                                        if (itemLoop2.getNomeProduto().equalsIgnoreCase(nomeProduto)) {
                                            itemLoop2.getCodigo();
                                        }
                                    }
                                    System.out.println("Código:  " + itemLoop.getCodigo());

                                }
                                System.out.println("Valor unitário: " + itemLoop.getValorUnitario());
                                System.out.println("Digite a quantidade: ");
                                int quantidadeCompra = sc.nextInt();

                                double valorTotalCompra = quantidadeCompra * itemLoop.getValorUnitario();
                                System.out.println("Descrição: ");
                                String descricao = sc.next();
                                System.out.println("Valor total: " + valorTotalCompra);
                                // colocar valor de valor total compra como valortotal
                                // pegar o cpf ou cnpj do cliente pelo nome
                                System.out.println("Digite o CPF ou CNPJ do cliente: ");
                                String cpfCnpj2 = sc.next();
                                // procurar o cliente pelo cpf ou cnpj pelo arquivo de clientes
                                efetuarcompra compra = new efetuarcompra();

                                compra.setQuantidade(quantidadeCompra);
                                compra.setNomeProduto(nomeProduto);
                                compra.setValorUnitario(itemLoop.getValorUnitario());
                                compra.setValorTotal(valorTotalCompra);
                                // compra.setDataValidade(dataValidade);
                                compra.setCodigo(itemLoop.getCodigo());
                                compra.setDescricao(descricao);
                                // cpf do cliente
                                pf.setCPF(pf.getCPF());
                                pj.setCNPJ(pj.getCNPJ());

                                compras.add(compra);

                                // escrever no arquivo de compras
                                File compraFile = new File("BaseDeDados/Compras.txt");
                                compraFile.createNewFile();
                                FileWriter compraWriter = new FileWriter(compraFile, true);
                                BufferedWriter comprasBufferedWriter = new BufferedWriter(compraWriter);
                                comprasBufferedWriter.write(compra.toString());
                                comprasBufferedWriter.newLine();
                                comprasBufferedWriter.flush();
                                comprasBufferedWriter.close();
                            }
                            // comprasBufferedWriter.write(itemLoop.toString());
                            System.out.println("Compra efetuada com sucesso.");
                            break;
                        }
                    }

                    if (cliente == null) {
                        System.out.println("Cliente não encontrado.");
                    }
                    break;

                case "Atualização da situação de pagamento de uma compra":
                    System.out.println("Atualizar a situação de pagamento de uma compra:");
                    System.out.println("Digite o nome do cliente: ");
                    String nomeCliente1 = sc.next();
                    Cliente cliente1 = null;
                    for (int a = 0; a < clientes.size(); a++) {
                        Cliente clienteLoop = clientes.get(a);
                        if (clienteLoop.getNome().equalsIgnoreCase(nomeCliente1)) {
                            cliente1 = clienteLoop;
                            System.out.println("Cliente encontrado.");
                            System.out.println("Digite o nome do produto: ");
                            // buscar produto pelo nome
                            String nomeProduto = sc.next();
                            Item item = null;
                            for (int j = 0; j < itens.size(); j++) {
                                Item itemLoop = itens.get(j);
                                if (itemLoop.getNomeProduto().equalsIgnoreCase(nomeProduto)) {
                                    item = itemLoop;
                                    System.out.println("Produto encontrado.");
                                    System.out.println("Digite a quantidade: ");
                                    int quantidade = sc.nextInt();
                                    System.out.println("Digite o valor unitário: ");
                                    double valorUnitario = sc.nextDouble();
                                    System.out.println("Digite o valor total: ");
                                    double valorTotal = sc.nextDouble();
                                    System.out.println("Digite a data de validade: ");
                                    int dataValidade = sc.nextInt();
                                    System.out.println("Digite o código: ");
                                    int codigo = sc.nextInt();
                                    System.out.println("Digite a descrição: ");
                                    String descricao = sc.next();
                                    efetuarcompra compra = new efetuarcompra();
                                    compra.setQuantidade(quantidade);
                                    compra.setValorUnitario(valorUnitario);
                                    compra.setValorTotal(valorTotal);
                                    compra.setDataValidade(dataValidade);
                                    compra.setCodigo(codigo);
                                    compra.setDescricao(descricao);
                                    compras.add(compra);
                                    System.out.println("Compra efetuada com sucesso.");
                                    break;
                                }
                            }

                            break;
                        }

                    }
                    break;

                case "Relatório de clientes":

                    String[] OpcoesRela = {
                            "(a) Relação de todos os Clientes que possuem o nome iniciado por uma determinada sequência de caracteres",
                            "(b) Relação de todos os Produtos",
                            "(c) Busca de um produto pelo nome",
                            "(d) Relação de produtos que são perecíveis e que estão com a data de validade vencida",
                            "(e) Relação de todas as compras",
                            "(f) Busca de uma compra pelo número",
                            "(g) Relação de todas as compras que não foram pagas ainda",
                            "(h) Relação das 10 últimas compras pagas",
                            "(i) Apresentação das informações da compra mais cara",
                            "(j) Apresentação das informações da compra mais barata",
                            "(k) Relação do valor total de compras feitas em cada mês nos últimos 12 meses",
                            "Voltar"
                    };
                    String subEscolha = JOptionPane.showInputDialog(null, "Escolha uma opção:",
                            "7 - Relatórios",
                            JOptionPane.PLAIN_MESSAGE, null, OpcoesRela, OpcoesRela[0]).toString();

                    switch (subEscolha) {
                        case "(a) Relação de todos os Clientes que possuem o nome iniciado por uma determinada sequência de caracteres":
                            sc.nextLine();
                            System.out.println("Digite a sequencia de caracteres do cliente do cliente: ");
                            String sequencia = sc.nextLine();
                            sc.close();
                            for (int j = 0; j < clientes.size(); j++) {
                                Cliente clienteLoop = clientes.get(j);
                                if (clienteLoop.getNome().contains(sequencia)) {
                                    System.out.println(clienteLoop.toString());
                                }
                            }
                        case "(b) Relação de todos os Produtos":
                            for (int j = 0; j < itens.size(); j++) {
                                Item itemLoop = itens.get(j);
                                System.out.println(itemLoop.toString());
                            }
                        case "(c) Busca de um produto pelo nome":
                            sc.nextLine();

                            System.out.println("Digite o nome do produto: ");
                            String nomeProduto = sc.nextLine();
                            for (int j = 0; j < itens.size(); j++) {
                                Item itemLoop = itens.get(j);
                                if (itemLoop.getNomeProduto().contains(nomeProduto)) {
                                    System.out.println(itemLoop.toString());
                                }
                            }

                        case "(d) Relação de produtos que são perecíveis e que estão com a data de validade vencida":
                            for (int k = 0; k < itens.size(); k++) {
                                Item itemLoop = itens.get(k);
                                if (itemLoop.getDataValidade() != 0) {
                                    System.out.println(itemLoop.toString());
                                }
                            }

                            break;
                        case "(e) Relação de todas as compras":
                            for (int j = 0; j < compras.size(); j++) {
                                efetuarcompra compraLoop = compras.get(j);
                                System.out.println(compraLoop.toString());
                            }
                            break;
                        case "(f) Busca de uma compra pelo número":
                            System.out.println("Digite o número da compra: ");
                            int numeroCompra = sc.nextInt();
                            for (int j = 0; j < compras.size(); j++) {
                                efetuarcompra compraLoop = compras.get(j);
                                if (compraLoop.getCodigo() == numeroCompra) {
                                    System.out.println(compraLoop.toString());
                                }
                            }
                            break;
                        case "(g) Relação de todas as compras que não foram pagas ainda":
                            for (int k = 0; k < compras.size(); k++) {
                                efetuarcompra compraLoop = compras.get(k);
                                if (compraLoop.getDescricao().equalsIgnoreCase("Não Pago")) {
                                    System.out.println(compraLoop.toString());
                                }
                            }
                            break;
                        case "(h) Relação das 10 últimas compras pagas":
                            for (int j = 0; j < compras.size(); j++) {
                                efetuarcompra compraLoop = compras.get(j);
                                if (compraLoop.getValorUnitario() > 100) {
                                    System.out.println(compraLoop.toString());
                                }
                            }
                            break;
                        case "(j) Apresentação das informações da compra mais barata":
                            for (int k = 0; k < compras.size(); k++) {
                                efetuarcompra compraLoop = compras.get(k);
                                if (compraLoop.getValorUnitario() < 100) {
                                    System.out.println(compraLoop.toString());
                                }
                            }
                            break;
                        case "(k) Relação do valor total de compras feitas em cada mês nos últimos 12 meses":
                            for (int j = 0; j < compras.size(); j++) {
                                efetuarcompra compraLoop = compras.get(j);
                                if (compraLoop.getValorUnitario() < 100) {
                                    System.out.println(compraLoop.toString());
                                }
                            }
                            break;

                        default:
                            break;
                    }

                    if (subEscolha.equals("Voltar") || subEscolha != null) {
                        JOptionPane.showMessageDialog(null, "Voltando...");
                    }

            }

        } while (!input.equals("Sair do sistema"));
        sc.close();
    }
    // compraBufferedWriter.close();

    // Método para cadastrar um cliente
    public static void cadastrarCliente() throws IOException {
        // Cria um arquivo para armazenar os dados dos clientes
        File clienteFile = new File("BaseDeDados/Clientes.txt");
        // Verifica se o arquivo existe, se não existir, cria um novo
        if (!clienteFile.exists())
            clienteFile.createNewFile();

        // Cria um BufferedWriter para escrever no arquivo de clientes
        try (BufferedWriter clienteBufferedWriter = new BufferedWriter(new FileWriter(clienteFile, true))) {
            // Solicita ao usuário para inserir o CPF ou CNPJ do cliente
            System.out.println("Bem vindo ao cadastro de clientes!");
            System.out.println("CPF ou CNPJ do Cliente: ");
            System.out.println("1 - CPF \n2 - CNPJ:");

            // Verifica a escolha do usuário
            switch (sc.nextInt()) {

                case 1:
                    PessoaFis pf = new PessoaFis();
                    System.out.println("Nome do Cliente:\n");
                    pf.setNome(sc.next());
                    System.out.println("\nData de Cadastro do Cliente:\n");
                    LocalDate dataAtual = LocalDate.now();
                    System.out.println(dataAtual);
                    pf.setDataCadastro(dataAtual.toString());
                    System.out.println("\nCPF do Cliente:\n");
                    pf.setCPF(sc.next());
                    System.out.println("\nQuantidade máxima de parcelas da compra:\n ");
                    pf.setQtdParcelas(sc.nextInt());
                    pf.setEndereco(enderecoCliente());
                    System.out.println(pf.ParaString());
                    clienteBufferedWriter.write(pf.ParaString());
                    clienteBufferedWriter.newLine();
                    clienteBufferedWriter.flush(); // Adiciona uma nova linha
                    break;
                case 2:
                    PessoaJuri pj = new PessoaJuri();
                    System.out.println("\nCNPJ do Cliente:\n");
                    pj.setCNPJ(sc.next());
                    System.out.println("\nRazão Social do Cliente:\n");
                    pj.setRazaoSocial(sc.next());
                    System.out.println("Prazo máximo para pagamento da compra em dias: \n");
                    pj.setPrazoMaximo(sc.nextInt());
                    System.out.println("Data Cadastro: ");
                    LocalDate dataActual = LocalDate.now();
                    System.out.println(dataActual);
                    pj.setDataCadastro(dataActual.toString());
                    pj.setEndereco(enderecoCliente());
                    System.out.println(pj.ParaString());
                    clienteBufferedWriter.write(pj.ParaString());
                    clienteBufferedWriter.newLine();// Adiciona uma nova linha
                    clienteBufferedWriter.flush();
                    break;
            }
        }
    }

    public static Endereco enderecoCliente() {
        Endereco endereco = new Endereco();
        System.out.println("\nNúmero da casa do Cliente:\n ");
        endereco.setNumero(sc.nextInt());
        enderecos.add(endereco);
        System.out.println("\nRua do Cliente:\n ");
        endereco.setRua(sc.next());
        enderecos.add(endereco);
        sc.nextLine(); // limpar o buffer do teclado
        System.out.println("\nBairro do Cliente:\n ");
        endereco.setBairro(sc.nextLine());
        enderecos.add(endereco);
        System.out.println("\nCEP do Cliente:\n ");
        endereco.setCEP(sc.next());
        enderecos.add(endereco);
        System.out.println("\nCidade do Cliente:\n ");
        endereco.setCidade(sc.next());
        enderecos.add(endereco);
        System.out.println("\nEstado do Cliente:\n ");
        endereco.setEstado(sc.next());
        enderecos.add(endereco);
        return endereco;
    }

    public static void cadastrarItem() throws IOException {
        File produtoFile = new File("BaseDeDados/Itens.txt");
        if (!produtoFile.exists())
            produtoFile.createNewFile();
        FileWriter produtoWriter = new FileWriter(produtoFile, true);
        BufferedWriter produtoBufferedWriter = new BufferedWriter(produtoWriter);
        System.out.println("Bem vindo ao cadastro de produtos!");
        System.out.println("Quantos produtos deseja cadastrar?");
        int quantidade = sc.nextInt();
        for (int i = 0; i < quantidade; i++) {
            Item itemLoop = new Item(); // Crie um novo objeto Item
            System.out.println("Nome do Produto: ");
            itemLoop.setNomeProduto(sc.next()); // Defina o nome do produto
            System.out.println("Valor Unitário do Produto: ");
            itemLoop.setValorUnitario(sc.nextDouble()); // Defina o valor unitário
            System.out.println("Codigo do Produto: ");
            itemLoop.setCodigo(sc.nextInt()); // Defina a quantidade
            System.out.println("Valor Total do Produto: ");
            itemLoop.setDescricao(sc.next()); // Defina o valor total
            System.out.println("Este produto é perecível? (S/N)");

            String perecivel = sc.next();
            if (perecivel.equalsIgnoreCase("S")) {
                System.out.println("Data de validade do produto: ");
                itemLoop.setDataValidade(sc.nextInt());
            } else if (perecivel.equalsIgnoreCase("N")) {
                System.out.println("Este produto não é perecível.");
                System.out.println(itemLoop.toString());
                produtoBufferedWriter.write(itemLoop.toString());
                produtoBufferedWriter.newLine();
            }
        }
        produtoBufferedWriter.close();
    }

    public static void remover(ArrayList<Cliente> clientes, Cliente cliente) {

        clientes.remove(cliente);
    }

    public static void escreverClientesNoArquivo(ArrayList<Cliente> clientes)
            throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("BaseDeDados/Clientes.txt", false));

        for (Cliente cliente : clientes) {
            bufferedWriter.write(cliente.toString());
            bufferedWriter.newLine();
        }

        bufferedWriter.close();
    }

}
