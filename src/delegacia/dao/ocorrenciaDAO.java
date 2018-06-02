package delegacia.dao;

import java.sql.*;
import java.util.ArrayList;

import delegacia.jdbc.ConnectionFactory;
import delegacia.pojo.Ocorrencia;
import delegacia.dao.delitoDAO;
import delegacia.dao.vitimaDAO;
import delegacia.dao.criminosoDAO;

public class ocorrenciaDAO {
	private Connection connection;
	criminosoDAO criminosoDAO = new criminosoDAO();
	delitoDAO delitoDAO = new delitoDAO();
	vitimaDAO vitimaDAO = new vitimaDAO();

	public ocorrenciaDAO() {
		super();
		this.connection = new ConnectionFactory().getConnection();;
	}
	
	public boolean adicionarOcorrencia(Ocorrencia ocorrencia) {
		if(delitoDAO.buscarDelito(ocorrencia.getId_delito()) == null){
			System.out.println("Delito não localizado!");
			return false;
		}
		
		if(criminosoDAO.buscarCriminoso(ocorrencia.getCpf_criminoso()) == null){
			System.out.println("Criminoso não localizado!");
			return false;
		}
		
		if(vitimaDAO.buscarVitima(ocorrencia.getCpf_vitima()) == null) {
			System.out.println("Vítima não localizada!");
			return false;
		}
		
		String sql = "INSERT INTO ocorrencia (id_delito, cpf_vitima, cpf_criminoso, descricao) VALUES (?,?,?,?)";
		try{
		
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, ocorrencia.getId_delito());
			ps.setLong(2, ocorrencia.getCpf_vitima());
			ps.setLong(3, ocorrencia.getCpf_criminoso());
			ps.setString(4, ocorrencia.getDescricao());
			//ps.setDate(5, java.sql.Date.valueOf(ocorrencia.getData()));
			//ps.setTime(6, java.sql.Time.valueOf(ocorrencia.getHora()));
			
			
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
	
	public boolean removerOcorrencia(Ocorrencia ocorrencia) {
		String sql = "DELETE FROM ocorrencia WHERE id = ?";
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, ocorrencia.getId());
			
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
	
	public ArrayList<Ocorrencia> getOcorrencias() {
		ArrayList<Ocorrencia> ocorrencias = new ArrayList<Ocorrencia>();
		String sql = "SELECT * FROM ocorrencia";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();	
			
			while(rs.next()) {
				Ocorrencia ocorrencia = new Ocorrencia();
				ocorrencia.setCpf_vitima(rs.getLong("cpf_vitima"));
				ocorrencia.setCpf_criminoso(rs.getLong("cpf_criminoso"));
				ocorrencia.setDescricao(rs.getString("descricao"));
				ocorrencia.setData(rs.getString("data"));
				ocorrencia.setHora(rs.getString("hora"));
				ocorrencias.add(ocorrencia);
			}
			ps.close();
			rs.close();
		}
		catch(SQLException e) {
			System.err.println(e.getMessage());
		}
		return ocorrencias;
	}
	
	public Ocorrencia buscarOcorrencia(int id) {
		String sql = "SELECT * FROM ocorrencia WHERE id = ?";
		
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			Ocorrencia ocorrencia = new Ocorrencia(rs.getInt("id"), rs.getInt("id_delito"), rs.getLong("cpf_vitima"), rs.getLong("cpf_criminoso"), rs.getString("descricao"), rs.getString("data"), rs.getString("hora"));
			
			stmt.close();
			
			return ocorrencia;
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
