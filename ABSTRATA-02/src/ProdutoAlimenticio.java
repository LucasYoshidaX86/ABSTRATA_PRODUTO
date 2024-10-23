import java.util.Scanner; //Import para a utilização da função Scanner.

//Classe criada contendo atributos herdados e próprios.
public class ProdutoAlimenticio extends Produto {
	Scanner entrada = new Scanner(System.in);
	
	private String dataValidade;
	private String infoNutricionais;

//Constructor para inicialização dos atributos criados.
	public ProdutoAlimenticio(String nome, double precoCusto, double precoVenda, String dataValidade, String infoNutricionais) {
		super(nome, precoCusto, precoVenda);
		this.dataValidade = dataValidade;
		this.infoNutricionais = infoNutricionais;
		}
	
//Encapsulamento para acessar e alterar atributos privados.
		public String getDataValidade() {
			return
					dataValidade;
		}
		
		public void setDataValidade(String dataValidade) {
			this.dataValidade = dataValidade;
		}
		
		public String getInfoNutricionais() {
			return
					infoNutricionais;
		}
		
		public void setInfoNutricionais(String infoNutricionais) {
			this.infoNutricionais = infoNutricionais;
		}
	
//Métodos puxados da classe abstrata.
		@Override
//Método para salvar um produto.	
		public void salvar() {
			System.out.println("Salvando produto alimentício ao banco de dados: " + getNome());
		}
//Método para deletar um produto, utilizando comandos "if e else" para perguntar ao usuário se realmente deseja excluir o item.	
		public void deletar() {
			System.out.println("Você tem certeza que deseja deletar este produto? \n 'SIM' ou 'NÃO' ");
			String resposta = entrada.nextLine();
			
			if( resposta.equals("SIM")) {
				System.out.println("Deletando produto alimentício do banco de dados: " + getNome() + "\n");
			}
			else if(resposta.equals("NÃO")) {
				System.out.println("Operação deletar cancelada." + "\n");
			}
			else {
				System.out.println("ERROR!! Operação inválida" + "\n");
			}
			
		}
//Método para atualizar produtos.		
		public void atualizar() {
			System.out.println("Atualizando produto no banco de dados: " + getNome());
		}
	
//Método exibição de informações formatadas, quando puxadas ao main.
		public String exibicao() {
			return
					super.exibicao() + "\n" + 
					"Data de vencimento: " + getDataValidade() + "\n" +
					"Informações nutricionais: " + getInfoNutricionais();
		}
		
		
		
	
	
			
}
