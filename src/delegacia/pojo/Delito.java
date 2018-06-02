package delegacia.pojo;

public class Delito {
	private int id;
	private String nome;
	
	public Delito(String nome, int id) {
		super();
		this.nome = nome;
		this.id = id;
	}
	
	public Delito(String nome) {
		super();
		this.nome = nome;
	}
	
	public Delito() {
		super();
		this.nome = "";
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String toString() {
		return "Delito [id=" + id + ", nome=" + nome + "]";
	}
	
}
