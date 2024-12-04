import java.util.Scanner;

//Classe com a implementação de uma interface GUI para gerenciar os comandos CRUD após conexão com o bando de dados.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //Inicia porém o scanner. 
        Produto produto;

        while (true) {
            System.out.println("======= Controle de Produtos =======\n");
            System.out.println("Selecione uma das opções:");
            System.out.println("1. Adicionar produto alimentício");
            System.out.println("2. Atualizar produto alimentício");
            System.out.println("3. Deletar produto alimentício");
            System.out.println("============================");
            System.out.println("4. Adicionar produto vestuário");
            System.out.println("5. Atualizar produto vestuário");
            System.out.println("6. Deletar produto vestuário");
            System.out.println("============================");
            System.out.println("0. Sair (Fecha o sistema)");

            int opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    //Adicionar produto alimentício.
                    System.out.println("Digite o nome do produto:");
                    String nomeAlimenticio = scanner.nextLine();
                    System.out.println("Digite o preço de custo:");
                    double precoCustoAlimenticio = scanner.nextDouble();
                    System.out.println("Digite o preço de venda:");
                    double precoVendaAlimenticio = scanner.nextDouble();
                    scanner.nextLine(); 
                    System.out.println("Digite a data de validade:");
                    String dataValidade = scanner.nextLine();
                    System.out.println("Digite as informações nutricionais:");
                    String infoNutricionais = scanner.nextLine();

                    produto = new ProdutoAlimenticio(nomeAlimenticio, precoCustoAlimenticio, precoVendaAlimenticio, dataValidade, infoNutricionais);
                    produto.salvar();
                    break;

                case 2:

                    //Cria a instância de ProdutoAlimenticio com o nome e chama o método atualizar.
                    produto = new ProdutoAlimenticio("", 0, 0, "", "");
                    produto.atualizar(); 
                    break;

                case 3:
                    //Deletar produto alimentício.
                    System.out.println("Digite o nome do produto alimentício para deletar:");
                    String nomeAlimenticioDeletar = scanner.nextLine();
                    produto = new ProdutoAlimenticio(nomeAlimenticioDeletar, 0, 0, "", "");
                    produto.deletar();
                    break;

                case 4:
                    //Adicionar produto vestuário.
                    System.out.println("Digite o nome do produto:");
                    String nomeVestuario = scanner.nextLine();
                    System.out.println("Digite o preço de custo:");
                    double precoCustoVestuario = scanner.nextDouble();
                    System.out.println("Digite o preço de venda:");
                    double precoVendaVestuario = scanner.nextDouble();
                    scanner.nextLine(); 
                    System.out.println("Digite o tamanho:");
                    String tamanho = scanner.nextLine();
                    System.out.println("Digite a cor:");
                    String cor = scanner.nextLine();
                    System.out.println("Digite o material:");
                    String material = scanner.nextLine();

                    produto = new ProdutoVestuario(nomeVestuario, precoCustoVestuario, precoVendaVestuario, tamanho, cor, material);
                    produto.salvar();
                    break;

                case 5:
                  

                    //Cria a instância de ProdutoVestuario com o nome e chama o método atualizar.
                    produto = new ProdutoVestuario("", 0, 0, "", "", "");
                    produto.atualizar(); 
                    break;

                case 6:
                    //Deletar produto vestuário.
                    System.out.println("Digite o nome do produto vestuário para deletar:");
                    String nomeVestuarioDeletar = scanner.nextLine();
                    produto = new ProdutoVestuario(nomeVestuarioDeletar, 0, 0, "", "", "");
                    produto.deletar();
                    break;

                case 0:
                    //Encerra o sistema.
                    System.out.println("Sistema encerrado.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}







