import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Classe criada para conectar o código ao banco de dados MySQL.
public class ConexaoMySQL {

	private static final String URL = "jdbc:mysql://localhost:3306/controle_produtos"; //Definindo URL.
	private static final String USUARIO = "root"; //Definindo usuário.
	private static final String SENHA = ""; //Definindo senha.
	
	public static Connection conectar() { //Criando método static para conexão.
		Connection conexao = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
			System.out.println("Conexão realizada com sucesso!");
		} catch (ClassNotFoundException e) {
			System.err.println("Driver não encontrado: " + e.getMessage()); //Exceção caso o Driver não tenha sido encontrado para rodar o código.
		} catch (SQLException e) {
			System.err.println("Erro ao conectar: " + e.getMessage());
		}
		return conexao;
	}
	
	public static void main(String[] args) {
		conectar();
	}
}
