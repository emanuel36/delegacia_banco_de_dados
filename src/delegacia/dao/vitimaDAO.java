package delegacia.dao;

import java.sql.*;
import java.util.ArrayList;

import delegacia.jdbc.ConnectionFactory;
import delegacia.pojo.Vitima;

public class vitimaDAO {
	private Connection connection;

	public vitimaDAO(){
		super();
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public boolean adicionarVitima(Vitima vitima) {
		String sql = "INSERT INTO vitima (cpf, nome, idade, sexo) VALUES (?, ?, ?, ?)";
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setLong(1, vitima.getCpf());
			ps.setString(2, vitima.getNome());
			ps.setInt(3, vitima.getIdade());
			ps.setString(4, vitima.getSexo());

			int rowsAffected = ps.executeUpdate();
			ps.close();
			if(rowsAffected > 0){
				return true;
			}
		}
		catch(SQLException e) {
			//System.err.println(e.getMessage());
		}
		return false;
	}
	
	public boolean removerVitima(Vitima vitima) {
		String sql = "DELETE FROM vitima WHERE cpf = ?";
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setLong(1, vitima.getCpf());
			
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
	
	public void getOcorrenciasVitima(long cpf) {
		String sql = "select V.cpf as cpf_vitima, V.nome as nome_vitima, C.cpf as cpf_criminoso, C.nome as nome_criminoso, D.nome as nome_delito from vitima as V, criminoso as C, delito as D, ocorrencia as O where O.cpf_vitima = ? and C.cpf = O.cpf_criminoso and V.cpf = O.cpf_vitima and O.id_delito = D.id";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setLong(1, cpf);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				System.out.println("[CPF vítima: " + rs.getLong("cpf_vitima") +
									" | Vítima: " + rs.getString("nome_vitima") +
									" | CPF criminoso: " + rs.getLong("cpf_criminoso") +
									" | Criminoso: " + rs.getString("nome_criminoso") +
									" | Delito: " + rs.getString("nome_delito") +
									"]");
			}
			ps.close();
		}
		catch(SQLException e){
			//System.err.println(e.getMessage());
		}
	}
	
	public ArrayList<Vitima> getVitimas() {
		ArrayList<Vitima> vitimas = new ArrayList<Vitima>();
		String sql = "SELECT * FROM vitima";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();	
			
			while(rs.next()) {
				Vitima vitima = new Vitima();
				vitima.setCpf(rs.getLong("cpf"));
				vitima.setIdade(rs.getInt("idade"));
				vitima.setNome(rs.getString("nome"));
				vitima.setSexo(rs.getString("sexo"));
				vitimas.add(vitima);
			}
			ps.close();
			rs.close();
		}
		catch(SQLException e) {
			//System.err.println(e.getMessage());
		}
		return vitimas;
	}
	
	public Vitima buscarVitima(long cpf) {
		String sql = "SELECT * FROM vitima WHERE cpf = ?";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setLong(1, cpf);
			
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			Vitima vitima = new Vitima(cpf, rs.getString("nome"), rs.getInt("idade"), rs.getString("sexo"));
			
			stmt.close();
			
			return vitima;
		}catch(SQLException e) {
			//System.err.println(e.getMessage());
		}
		return null;
	}
}