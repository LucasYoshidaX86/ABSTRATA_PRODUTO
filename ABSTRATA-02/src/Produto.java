//Criando classe abstrata de produtos, contendo atributos e definições de métodos.
public abstract class Produto {
	private String nome;
	private double precoCusto;
	private double precoVenda;

//Constructor para inicialização dos atributos criados.
	public Produto(String nome, double precoCusto, double precoVenda) {
		this.nome = nome;
		this.precoCusto = precoCusto;
		this.precoVenda = precoVenda;
	}

//Método concreto para calcular o lucro de produtos, subtraindo preço de custo do preço de venda.
	public double lucro() {
		return
				precoVenda - precoCusto;
	}
	
//Método abstratos criado mas sem definição.
	abstract void salvar();
	abstract void deletar();
	abstract void atualizar();
	
//Encapsulamento para acessar e alterar atributos privados.
	public String getNome() {
		return 
				nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public double getPrecoCusto() {
		return
				precoCusto;
	}
	
	public void setPrecoCusto(double precoCusto) {
		this.precoCusto = precoCusto;
	}
	
	public double getPrecoVenda() {
		return
				precoVenda;
	}
	
	public void setPrecoVenda(double precoVenda) {
		this.precoVenda = precoVenda;
	}

//Método exibição de informações formatadas quando puxadas ao main.
	public String exibicao() {
		return
				"Nome: " + getNome() + "\n" +
				"Preço de custo: " + getPrecoCusto() + "\n" + 
				"Preço de venda: " + getPrecoVenda();
	}
}
