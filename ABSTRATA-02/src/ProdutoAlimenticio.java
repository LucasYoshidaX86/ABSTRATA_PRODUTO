import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ProdutoAlimenticio extends Produto {
    private String dataValidade;
    private String infoNutricionais;

    // Constructor para inciar os atributos.
    public ProdutoAlimenticio(String nome, double precoCusto, double precoVenda, String dataValidade, String infoNutricionais) {
        super(nome, precoCusto, precoVenda);
        this.dataValidade = dataValidade;
        this.infoNutricionais = infoNutricionais;
    }

    // Getters e setters para acessar e alterar atributos privados.
    public String getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(String dataValidade) {
        this.dataValidade = dataValidade;
    }

    public String getInfoNutricionais() {
        return infoNutricionais;
    }

    public void setInfoNutricionais(String infoNutricionais) {
        this.infoNutricionais = infoNutricionais;
    }

    @Override
    public void salvar() { //Método criado para inserir dados na tabela .
        try (Connection conexao = ConexaoMySQL.conectar()) { //Cria conexão com o banco de dados.
            String sqlProduto = "INSERT INTO Produtos (nome, preco_custo, preco_venda) VALUES (?, ?, ?)"; //Comando para inserir na tabela 'produtos'.
            try (PreparedStatement stmtProduto = conexao.prepareStatement(sqlProduto, Statement.RETURN_GENERATED_KEYS)) {
                stmtProduto.setString(1, getNome());
                stmtProduto.setDouble(2, getPrecoCusto());
                stmtProduto.setDouble(3, getPrecoVenda());
                stmtProduto.executeUpdate();

                //Buscando o ID.
                ResultSet rs = stmtProduto.getGeneratedKeys();
                if (rs.next()) {
                    int idProduto = rs.getInt(1);

                    String sqlAlimenticio = "INSERT INTO produtos_alimenticios (id, data_validade, info_nutricionais) VALUES (?, ?, ?)";
                    try (PreparedStatement stmtAlimenticio = conexao.prepareStatement(sqlAlimenticio)) {
                        stmtAlimenticio.setInt(1, idProduto);
                        stmtAlimenticio.setString(2, getDataValidade());
                        stmtAlimenticio.setString(3, getInfoNutricionais());
                        stmtAlimenticio.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar() {
        Connection conexao = ConexaoMySQL.conectar(); // Conectando ao banco de dados.
        Scanner entrada = new Scanner(System.in); // Criando objeto scanner para ler a entrada do usuário.

        int escolha = -1; // Inicializando variável para opção do usuário.

        // Loop para garantir uma entrada válida.
        while (true) {
            try {
                System.out.println("Alterar Dados do Produto Alimentício:");
                System.out.println("1. Alterar nome, preço de custo, preço de venda, data de validade e informações nutricionais.");
                System.out.println("2. Alterar apenas o nome.");
                System.out.println("3. Alterar apenas os preços (custo e venda).");
                System.out.println("4. Alterar apenas a data de validade.");
                System.out.println("5. Alterar apenas as informações nutricionais.");
                System.out.print("Escolha uma opção: ");
                escolha = entrada.nextInt();
                entrada.nextLine(); // Limpa o buffer.

                if (escolha >= 1 && escolha <= 5) {
                    break;
                } else {
                    System.out.println("Erro: Opção inválida. Escolha entre 1 e 5.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Insira um número inteiro.");
                entrada.nextLine(); // Limpa o buffer.
            }
        }

        try {
            if (conexao != null) {
                // Passo 1: Buscar o ID do produto pelo nome
                System.out.print("Digite o nome do produto para atualizar: ");
                String nomeProduto = entrada.nextLine();

                String sqlBuscarId = "SELECT id FROM Produtos WHERE nome = ?";
                PreparedStatement stmtBuscarId = conexao.prepareStatement(sqlBuscarId);
                stmtBuscarId.setString(1, nomeProduto);
                ResultSet rs = stmtBuscarId.executeQuery();

                if (rs.next()) {
                    int idProduto = rs.getInt("id"); // Obtendo o ID do produto

                    // Passo 2: Realizar a atualização de acordo com a escolha do usuário
                    if (escolha == 1) {
                        // Atualizar todos os campos.
                        System.out.print("Digite o novo nome: ");
                        setNome(entrada.nextLine());

                        System.out.print("Digite o novo preço de custo: ");
                        setPrecoCusto(entrada.nextDouble());

                        System.out.print("Digite o novo preço de venda: ");
                        setPrecoVenda(entrada.nextDouble());
                        entrada.nextLine(); // Limpa buffer.

                        System.out.print("Digite a nova data de validade: ");
                        setDataValidade(entrada.nextLine());

                        System.out.print("Digite as novas informações nutricionais: ");
                        setInfoNutricionais(entrada.nextLine());

                        // Atualizando a tabela Produtos
                        String sqlProduto = "UPDATE Produtos SET nome = ?, preco_custo = ?, preco_venda = ? WHERE id = ?";
                        try (PreparedStatement stmtProduto = conexao.prepareStatement(sqlProduto)) {
                            stmtProduto.setString(1, getNome());
                            stmtProduto.setDouble(2, getPrecoCusto());
                            stmtProduto.setDouble(3, getPrecoVenda());
                            stmtProduto.setInt(4, idProduto);
                            stmtProduto.executeUpdate();
                        }

                        // Atualizando a tabela produtos_alimenticios
                        String sqlAlimenticio = "UPDATE produtos_alimenticios SET data_validade = ?, info_nutricionais = ? WHERE id = ?";
                        try (PreparedStatement stmtAlimenticio = conexao.prepareStatement(sqlAlimenticio)) {
                            stmtAlimenticio.setString(1, getDataValidade());
                            stmtAlimenticio.setString(2, getInfoNutricionais());
                            stmtAlimenticio.setInt(3, idProduto);
                            stmtAlimenticio.executeUpdate();
                        }

                    } else if (escolha == 2) {
                        // Atualizar apenas o nome.
                        System.out.print("Digite o novo nome: ");
                        setNome(entrada.nextLine());

                        // Atualizando apenas o nome na tabela Produtos
                        String sql = "UPDATE produtos SET nome = ? WHERE id = ?";
                        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                            stmt.setString(1, getNome());
                            stmt.setInt(2, idProduto);
                            stmt.executeUpdate();
                        }

                    } else if (escolha == 3) {
                        // Atualizar apenas os preços.
                        System.out.print("Digite o novo preço de custo: ");
                        setPrecoCusto(entrada.nextDouble());

                        System.out.print("Digite o novo preço de venda: ");
                        setPrecoVenda(entrada.nextDouble());
                        entrada.nextLine(); // Limpa buffer.

                        // Atualizando apenas os preços na tabela Produtos
                        String sql = "UPDATE produtos SET preco_custo = ?, preco_venda = ? WHERE id = ?";
                        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                            stmt.setDouble(1, getPrecoCusto());
                            stmt.setDouble(2, getPrecoVenda());
                            stmt.setInt(3, idProduto);
                            stmt.executeUpdate();
                        }

                    } else if (escolha == 4) {
                        // Atualizar apenas a data de validade.
                        System.out.print("Digite a nova data de validade: ");
                        setDataValidade(entrada.nextLine());

                        // Atualizando apenas a data de validade na tabela produtos_alimenticios
                        String sql = "UPDATE produtos_alimenticios SET data_validade = ? WHERE id = ?";
                        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                            stmt.setString(1, getDataValidade());
                            stmt.setInt(2, idProduto);
                            stmt.executeUpdate();
                        }

                    } else if (escolha == 5) {
                        // Atualizar apenas as informações nutricionais.
                        System.out.print("Digite as novas informações nutricionais: ");
                        setInfoNutricionais(entrada.nextLine());

                        // Atualizando apenas as informações nutricionais na tabela produtos_alimenticios
                        String sql = "UPDATE produtos_alimenticios SET info_nutricionais = ? WHERE id = ?";
                        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                            stmt.setString(1, getInfoNutricionais());
                            stmt.setInt(2, idProduto);
                            stmt.executeUpdate();
                        }
                    }

                    System.out.println("Atualização realizada com sucesso.");
                } else {
                    System.out.println("Produto não encontrado. Tente novamente.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar produto alimentício: " + e.getMessage());
        } finally {
            try {
                if (conexao != null) conexao.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }

    @Override
    public void deletar() {
        Connection conexao = null;
        try {
            conexao = ConexaoMySQL.conectar();
            if (conexao != null) {
                // Verificando se o produto existe na tabela Produtos
                String sqlBuscarId = "SELECT id FROM Produtos WHERE nome = ?";
                PreparedStatement stmtBuscarId = conexao.prepareStatement(sqlBuscarId);
                stmtBuscarId.setString(1, getNome());
                ResultSet rs = stmtBuscarId.executeQuery();

                if (rs.next()) {
                    int idProduto = rs.getInt("id");

                    // Deletando da tabela produtos_alimenticios
                    String sqlAlimenticio = "DELETE FROM produtos_alimenticios WHERE id = ?";
                    PreparedStatement stmtAlimenticio = conexao.prepareStatement(sqlAlimenticio);
                    stmtAlimenticio.setInt(1, idProduto);
                    stmtAlimenticio.executeUpdate();

                    // Deletando da tabela Produtos
                    String sqlProduto = "DELETE FROM produtos WHERE id = ?";
                    PreparedStatement stmtProduto = conexao.prepareStatement(sqlProduto);
                    stmtProduto.setInt(1, idProduto);
                    stmtProduto.executeUpdate();

                    System.out.println("Produto alimentício excluído com sucesso.");
                } else {
                    System.out.println("Produto não encontrado.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao excluir dados: " + e.getMessage());
        } finally {
            try {
                if (conexao != null) conexao.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }


}




