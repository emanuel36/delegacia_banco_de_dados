package delegacia.dao;

import java.sql.*;
import java.util.ArrayList;

import delegacia.jdbc.ConnectionFactory;
import delegacia.pojo.Delito;

public class delitoDAO {
	private Connection connection;

	public delitoDAO() {
		super();
		this.connection = new ConnectionFactory().getConnection();;
	}
	
	public boolean adicionarDelito(Delito delito) {
		String sql = "INSERT INTO delito (nome) VALUES (?)";
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, delito.getNome());
			
			int rowsAffected = ps.executeUpdate();
			ps.close();
			if(rowsAffected > 0){
				return true;
			}
		}
		catch(SQLException e) {
			System.err.println(e.getMessage());
		}
		return false;
	}
	
	public boolean removerDelito(Delito delito) {
		String sql = "DELETE FROM delito WHERE id = ?";
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setLong(1, delito.getId());
			
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
	
	public ArrayList<Delito> getDelitos() {
		ArrayList<Delito> delitos = new ArrayList<Delito>();
		String sql = "SELECT * FROM delito";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();	
			
			while(rs.next()) {
				Delito delito = new Delito();
				delito.setNome(rs.getString("nome"));
				delito.setId(rs.getInt("id"));
				delitos.add(delito);
			}
			ps.close();
			rs.close();
		}
		catch(SQLException e) {
			System.err.println(e.getMessage());
		}
		return delitos;
	}
	
	public Delito buscarDelito(int id) {
		String sql = "SELECT * FROM delito WHERE id = ?";
		
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			Delito delito = new Delito(rs.getString("nome"), rs.getInt("id"));
			
			stmt.close();
			
			return delito;
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}finally {
			try {
				this.connection.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
