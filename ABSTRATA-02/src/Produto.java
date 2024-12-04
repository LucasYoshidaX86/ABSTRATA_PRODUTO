public abstract class Produto {
    private int id;
    private String nome;
    private double precoCusto;
    private double precoVenda;

    // Construtor sem ID (para salvar novos produtos)
    public Produto(String nome, double precoCusto, double precoVenda) {
        this.nome = nome;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
    }

    // Construtor com ID (para atualizar ou deletar produtos existentes)
    public Produto(int id, String nome, double precoCusto, double precoVenda) {
        this.id = id;
        this.nome = nome;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(double precoCusto) {
        this.precoCusto = precoCusto;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }

    // Métodos abstratos para as operações de CRUD
    public abstract void salvar();

    public abstract void atualizar();

    public abstract void deletar();
}




