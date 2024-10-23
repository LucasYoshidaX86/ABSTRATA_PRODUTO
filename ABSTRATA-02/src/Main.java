
public class Main {

	public static void main(String[] args) {
		
//Criando instãncia de um produto alimentício, testando todos os métodos criados.
		ProdutoAlimenticio pa1 = new ProdutoAlimenticio("Barrinha.", 1.50, 3, "12/04/2025.", "Contém Glúten e lactose.");
			System.out.println("Dados do produto: \n" );
			System.out.println(pa1.exibicao());
			System.out.println("Lucro: " + pa1.lucro() + "\n");
			pa1.salvar();
			pa1.atualizar();
			pa1.deletar();
			
//Criando instância de um produto vestuário, testando todos os métodos criados.		
		ProdutoVestuario pa2 = new ProdutoVestuario("Meia.", 5.50, 20, "42.", "Branco.", "Algodão.");
			System.out.println("Dados do produto: \n" );
			System.out.println(pa2.exibicao());
			System.out.println("Lucro: " + pa2.lucro() + "\n");
			pa2.salvar();
			pa2.atualizar();
			pa2.deletar();

	}

}
