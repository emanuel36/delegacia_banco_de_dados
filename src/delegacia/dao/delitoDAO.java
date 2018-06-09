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
			//System.err.println(e.getMessage());
		}
		return false;
	}
	
	public boolean removerDelito(Delito delito) {
		String sql = "DELETE FROM delito WHERE id = ?";
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, delito.getId());
			
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
			//System.err.println(e.getMessage());
		}
		return delitos;
	}
	
    public Delito buscarDelitoId(int id) {
        String sql = "SELECT * FROM delito WHERE id = ?";
         
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
             
            ResultSet rs = stmt.executeQuery();
            rs.next();
             
            Delito delito = new Delito(rs.getString("nome"), rs.getInt("id"));
             
            stmt.close();
             
            return delito;
        }catch(SQLException e){
            //System.err.println(e.getMessage());
        }
        return null;
    }
	
	public Delito buscarDelito(String nome) {
		String sql = "SELECT * FROM delito WHERE nome like ? order by nome";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, '%' + nome + '%');
			
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			Delito delito = new Delito(rs.getString("nome"), rs.getInt("id"));
			
			stmt.close();
			
			return delito;
		}catch(SQLException e){
			//System.err.println(e.getMessage());
		}
		return null;
	}
	
	public void getNumeroDelitos(){
		String sql = "select D.nome, count(O.id_delito) from delito as D left join ocorrencia as O on O.id_delito = D.id group by D.nome order by count(O.id_delito) desc ";
		
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				System.out.println(rs.getString("nome") + ": " + rs.getInt("count"));
			}
			
		}catch(SQLException e) {
			//System.err.println(e.getMessage());
		}
	}
}
