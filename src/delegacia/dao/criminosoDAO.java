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
			System.err.println(e.getMessage());
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
			System.err.println(e.getMessage());
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
			System.err.println(e.getMessage());
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
			System.err.println(e.getMessage());
		}
		return null;
	}
}
