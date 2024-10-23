import java.util.Scanner; //Import para a utilização da função Scanner.

//Criação de classe contendo atributos herdados e próprios.
public class ProdutoVestuario extends Produto{
	Scanner entrada = new Scanner(System.in);
	
	private String tamanho;
	private String cor;
	private String material;

//Constructor para inicialização dos atributos criados.
	public ProdutoVestuario(String nome, double precoCusto, double precoVenda, String tamanho, String cor, String material) {
		super(nome, precoCusto, precoVenda);
		this.tamanho = tamanho;
		this.cor = cor;
		this.material = material;
		
	}
	
//Encapsulamento para acessar e alterar atributos privados.
	public String getTamanho() {
		return
				tamanho;
	}
	
	public void setTamanho(String tamanho) {
		this.tamanho = tamanho; 
	}
	
	public String getCor() {
		return 
				cor;
	}
	
	public void setCor(String cor) {
		this.cor = cor;
	}
	
	public String getMaterial() {
		return
				material;
	}
	
	public void setMaterial(String material) {
		this.material = material;
	}
	
//Métodos puxados da classe abstrata.
	@Override 
//Método para salvar produtos.	
	public void salvar() {
		System.out.println("Salvando produto vestuário ao banco de dados: " + getNome());
	}
//Método para deletar produtos, utilizando comandos "if e else" para perguntar ao usuário se realmente deseja excluir o item.	
	public void deletar() {
		System.out.println("Você tem certeza que deseja deletar este produto? \n 'SIM' ou 'NÃO' ");
		String resposta = entrada.nextLine();
		
		if( resposta.equals("SIM")) {
			System.out.println("Deletando produto vestuário do banco de dados: " + getNome() + "\n");
		}
		else if(resposta.equals("NÃO")) {
			System.out.println("Operação 'deletar' cancelada." + "\n");
		}
		else {
			System.out.println("ERROR!! Operação inválida" + "\n");
		}
		
	}
//Método para atualizar um produto.
	public void atualizar() {
		System.out.println("Atualizando produto no banco de dados: " + getNome());
	}

//Método exibição de informações formatadas, quando puxadas ao main.
	public String exibicao() {
		return
				super.exibicao() + "\n" +
				"Tamanho: " + getTamanho() + "\n" +
				"Cor: " + getCor() + "\n" + 
				"Material: " + getMaterial() + "\n";
	}
	
	
	
	
	
	
	
}
