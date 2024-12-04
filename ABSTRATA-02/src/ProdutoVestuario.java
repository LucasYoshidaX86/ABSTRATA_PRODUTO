import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ProdutoVestuario extends Produto {
    private String tamanho;
    private String cor;
    private String material;
	private int rowsAffected;

    // Construtor
    public ProdutoVestuario(String nome, double precoCusto, double precoVenda, String tamanho, String cor, String material) {
        super(nome, precoCusto, precoVenda);
        this.tamanho = tamanho;
        this.cor = cor;
        this.material = material;
    }

    // Getters e setters
    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public void salvar() {
        try (Connection conexao = ConexaoMySQL.conectar()) {
            String sqlProduto = "INSERT INTO Produtos (nome, preco_custo, preco_venda) VALUES (?, ?, ?)";
            try (PreparedStatement stmtProduto = conexao.prepareStatement(sqlProduto, Statement.RETURN_GENERATED_KEYS)) {
                stmtProduto.setString(1, getNome());
                stmtProduto.setDouble(2, getPrecoCusto());
                stmtProduto.setDouble(3, getPrecoVenda());
                stmtProduto.executeUpdate();

                ResultSet rs = stmtProduto.getGeneratedKeys();
                if (rs.next()) {
                    int idProduto = rs.getInt(1);

                    String sqlVestuario = "INSERT INTO produtos_vestuario (id, tamanho, cor, material) VALUES (?, ?, ?, ?)";
                    try (PreparedStatement stmtVestuario = conexao.prepareStatement(sqlVestuario)) {
                        stmtVestuario.setInt(1, idProduto);
                        stmtVestuario.setString(2, getTamanho());
                        stmtVestuario.setString(3, getCor());
                        stmtVestuario.setString(4, getMaterial());
                        rowsAffected = stmtVestuario.executeUpdate();
                        if (rowsAffected > 0) {
                            System.out.println("Produto inserido com sucesso.");
                        } else {
                            System.out.println("Produto não encontrado.");
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar() {
        Connection conexao = ConexaoMySQL.conectar();
        Scanner entrada = new Scanner(System.in);

        int escolha = -1;

        // Loop para garantir entrada válida
        while (true) {
            try {
                System.out.println("Alterar Dados do Produto Vestuário:");
                System.out.println("1. Alterar todos os campos (nome, preços, tamanho, cor, material).");
                System.out.println("2. Alterar apenas o nome.");
                System.out.println("3. Alterar apenas os preços (custo e venda).");
                System.out.println("4. Alterar apenas o tamanho.");
                System.out.println("5. Alterar apenas a cor.");
                System.out.println("6. Alterar apenas o material.");
                System.out.print("Escolha uma opção: ");
                escolha = entrada.nextInt();
                entrada.nextLine(); // Limpa o buffer.

                if (escolha >= 1 && escolha <= 6) {
                    break;
                } else {
                    System.out.println("Erro: Opção inválida. Escolha entre 1 e 6.");
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
                        // Atualizar todos os campos
                        System.out.print("Digite o novo nome: ");
                        setNome(entrada.nextLine());

                        System.out.print("Digite o novo preço de custo: ");
                        setPrecoCusto(entrada.nextDouble());

                        System.out.print("Digite o novo preço de venda: ");
                        setPrecoVenda(entrada.nextDouble());
                        entrada.nextLine(); // Limpa o buffer.

                        System.out.print("Digite o novo tamanho: ");
                        setTamanho(entrada.nextLine());

                        System.out.print("Digite a nova cor: ");
                        setCor(entrada.nextLine());

                        System.out.print("Digite o novo material: ");
                        setMaterial(entrada.nextLine());

                        // Atualizando a tabela Produtos
                        String sqlProduto = "UPDATE produtos SET nome = ?, preco_custo = ?, preco_venda = ? WHERE id = ?";
                        try (PreparedStatement stmtProduto = conexao.prepareStatement(sqlProduto)) {
                            stmtProduto.setString(1, getNome());
                            stmtProduto.setDouble(2, getPrecoCusto());
                            stmtProduto.setDouble(3, getPrecoVenda());
                            stmtProduto.setInt(4, idProduto);
                            stmtProduto.executeUpdate();
                        }

                        // Atualizando a tabela produtos_vestuario
                        String sqlVestuario = "UPDATE produtos_vestuario SET tamanho = ?, cor = ?, material = ? WHERE id = ?";
                        try (PreparedStatement stmtVestuario = conexao.prepareStatement(sqlVestuario)) {
                            stmtVestuario.setString(1, getTamanho());
                            stmtVestuario.setString(2, getCor());
                            stmtVestuario.setString(3, getMaterial());
                            stmtVestuario.setInt(4, idProduto);
                            stmtVestuario.executeUpdate();
                        }
                    } else if (escolha == 2) {
                        // Atualizar apenas o nome
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
                        // Atualizar apenas os preços
                        System.out.print("Digite o novo preço de custo: ");
                        setPrecoCusto(entrada.nextDouble());

                        System.out.print("Digite o novo preço de venda: ");
                        setPrecoVenda(entrada.nextDouble());
                        entrada.nextLine(); // Limpa o buffer.

                        // Atualizando apenas os preços na tabela Produtos
                        String sql = "UPDATE produtos SET preco_custo = ?, preco_venda = ? WHERE id = ?";
                        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                            stmt.setDouble(1, getPrecoCusto());
                            stmt.setDouble(2, getPrecoVenda());
                            stmt.setInt(3, idProduto);
                            stmt.executeUpdate();
                        }
                    } else if (escolha == 4) {
                        // Atualizar apenas o tamanho
                        System.out.print("Digite o novo tamanho: ");
                        setTamanho(entrada.nextLine());

                        // Atualizando apenas o tamanho na tabela produtos_vestuario
                        String sql = "UPDATE produtos_vestuario SET tamanho = ? WHERE id = ?";
                        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                            stmt.setString(1, getTamanho());
                            stmt.setInt(2, idProduto);
                            stmt.executeUpdate();
                        }
                    } else if (escolha == 5) {
                        // Atualizar apenas a cor
                        System.out.print("Digite a nova cor: ");
                        setCor(entrada.nextLine());

                        // Atualizando apenas a cor na tabela produtos_vestuario
                        String sql = "UPDATE produtos_vestuario SET cor = ? WHERE id = ?";
                        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                            stmt.setString(1, getCor());
                            stmt.setInt(2, idProduto);
                            stmt.executeUpdate();
                        }
                    } else if (escolha == 6) {
                        // Atualizar apenas o material
                        System.out.print("Digite o novo material: ");
                        setMaterial(entrada.nextLine());

                        // Atualizando apenas o material na tabela produtos_vestuario
                        String sql = "UPDATE produtos_vestuario SET material = ? WHERE id = ?";
                        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                            stmt.setString(1, getMaterial());
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
            System.err.println("Erro ao atualizar produto vestuário: " + e.getMessage());
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

                    // Deletando da tabela produtos_vestuario
                    String sqlVestuario = "DELETE FROM produtos_vestuario WHERE id = ?";
                    PreparedStatement stmtVestuario = conexao.prepareStatement(sqlVestuario);
                    stmtVestuario.setInt(1, idProduto);
                    stmtVestuario.executeUpdate();

                    // Deletando da tabela Produtos
                    String sqlProduto = "DELETE FROM Produtos WHERE id = ?";
                    PreparedStatement stmtProduto = conexao.prepareStatement(sqlProduto);
                    stmtProduto.setInt(1, idProduto);
                    stmtProduto.executeUpdate();

                    System.out.println("Produto de vestuário excluído com sucesso.");
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

