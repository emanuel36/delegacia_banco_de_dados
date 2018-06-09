package delegacia.dao;

import java.sql.*;
import java.util.ArrayList;
import delegacia.jdbc.ConnectionFactory;
import delegacia.pojo.Criminoso;


public class criminosoDAO {
	private Connection connection;

	public criminosoDAO(){
		super();
		this.connection = new ConnectionFactory().getConnection();;
	}
	
	public boolean adicionarCriminoso(Criminoso criminoso) {
		String sql = "INSERT INTO criminoso (cpf, nome, idade, sexo) VALUES (?, ?, ?, ?)";
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setLong(1, criminoso.getCpf());
			ps.setString(2, criminoso.getNome());
			ps.setInt(3, criminoso.getIdade());
			ps.setString(4, criminoso.getSexo());

			int rowsAffected = ps.executeUpdate();
			ps.close();
			if(rowsAffected > 0){
				return true;
			}
		}
		catch(SQLException e){
			//System.err.println(e.getMessage());
		}
		return false;
	}
	
	public boolean removerCriminoso(Criminoso criminoso) {
		String sql = "DELETE FROM criminoso WHERE cpf = ?";
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setLong(1, criminoso.getCpf());
			
			int rowsAffected = ps.executeUpdate();
			ps.close();
			if(rowsAffected > 0){
				return true;
			}
		}catch(SQLException e) {
			//System.err.println(e.getMessage());
		}
		return false;
	}
	
	public ArrayList<Criminoso> getCriminosos() {
		ArrayList<Criminoso> criminosos = new ArrayList<Criminoso>();
		String sql = "SELECT * FROM criminoso";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();	
			
			while(rs.next()) {
				Criminoso criminoso = new Criminoso();
				criminoso.setCpf(rs.getLong("cpf"));
				criminoso.setIdade(rs.getInt("idade"));
				criminoso.setNome(rs.getString("nome"));
				criminoso.setSexo(rs.getString("sexo"));
				criminosos.add(criminoso);
			}
			ps.close();
			rs.close();
		}
		catch(SQLException e) {
			//System.err.println(e.getMessage());
		}
		return criminosos;
	}
	
	public Criminoso buscarCriminoso(long cpf) {
		String sql = "SELECT * FROM criminoso WHERE cpf = ?";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setLong(1, cpf);
			
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			Criminoso criminoso = new Criminoso(cpf, rs.getString("nome"), rs.getInt("idade"), rs.getString("sexo"));
			
			stmt.close();
			
			return criminoso;
		}catch(SQLException e) {
			//System.err.println(e.getMessage());
		}
		return null;
	}
	
	public void getOcorrenciasCriminoso(long cpf) {
		String sql = "select C.cpf as cpf_criminoso, C.nome as nome_criminoso, V.cpf as cpf_vitima, V.nome as nome_vitima, D.nome as nome_delito, O.descricao as descricao, O.data as data, O.hora as hora from vitima as V, criminoso as C, delito as D, ocorrencia as O where O.cpf_vitima = V.cpf and C.cpf = ? and C.cpf = O.cpf_criminoso and O.id_delito = D.id";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setLong(1, cpf);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				System.out.println("[CPF criminoso: " + rs.getLong("cpf_criminoso") +
									" | Criminoso: " + rs.getString("nome_criminoso") +
									" | CPF vítima: " + rs.getLong("cpf_vitima") +
									" | Vítima: " + rs.getString("nome_vitima") +
									" | Delito: " + rs.getString("nome_delito") +
									" | Descrição: " + rs.getString("descricao") +
									" | Data: " + rs.getDate("data") + 
									" | Hora: " + rs.getTime("hora") +
									"]");
			}
			ps.close();
		}
		catch(SQLException e){
			//System.err.println(e.getMessage());
		}
	}
}
