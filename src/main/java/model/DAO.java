package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class DAO {

	// Parametros de conexão

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/dbagenda?useTimezone=true&serverTimezone=UTC";
	private String user = "root";
	private String pass = "";

	// Metodos de conexão

	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, pass);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	// teste conexão

	public void testeConexao() {
		try {
			Connection con = conectar();
			System.out.println(con);
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	// CREATE
	public void inserirContato(JavaBeans contato) {
		String create = "insert into contatos (nome,telefone,email) values (?,?,?)";
		try {
			// abrir conexao
			Connection con = conectar();
			// preparar query para executar
			PreparedStatement pst = con.prepareStatement(create);
			// substituir ? por variaveis no javaBeans
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			// executar query
			pst.executeUpdate();
			// encerra a conexao com banco
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	// SELECIONAR CONTATO
	public void selecionarContato(JavaBeans contato) {
		String read2 = "SELECT * FROM CONTATOS WHERE ID = ?";
try {
	Connection con = conectar();
	PreparedStatement pst = con.prepareStatement(read2);
	pst.setInt(1, contato.getId());
	// contato acessa as variaveis publicas do JavaBean
	ResultSet rs = pst.executeQuery();
	while (rs.next()) {
		// setar variaveis javaBeans
		contato.setId(rs.getInt(1));
		contato.setNome(rs.getString(2));
		contato.setFone(rs.getString(3));	
		contato.setEmail(rs.getString(4));
	}
	con.close();
} catch (Exception e) {
	System.out.println(e);
}
	}
	
	

	// LISTAR

	public ArrayList<JavaBeans> listarContatos() {
		// criando objeto para acessar a classe JavaBeans
		ArrayList<JavaBeans> contatos = new ArrayList<>();
		String read = "select * from contatos order by nome";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			// resultset rs = recebe tudo que o comando sql selecionou e manda
			// para o objeto rs.
			ResultSet rs = pst.executeQuery();

			// laco enquanto houver contatos;
			while (rs.next()) {
				int id = rs.getInt(1);
				String nome = rs.getString(2);
				String telefone = rs.getString(3);
				String email = rs.getString(4);
				// populando array list
				contatos.add(new JavaBeans(id, nome, telefone, email));
			}
			con.close();
			return contatos;

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	//editar contato
	public void alterarContato(JavaBeans contato) {
		String create = "update contatos set nome=?, telefone=?, email=? where id=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(create);
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			pst.setInt(4, contato.getId());
			pst.executeUpdate();
			pst.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void excluirContato(JavaBeans contato) {
		String delete = "DELETE FROM CONTATOS WHERE ID = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setInt(1, contato.getId());
			pst.executeUpdate();
			pst.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	

}
