package delegacia.pojo;

public class Criminoso {
	private long cpf;
	private String nome;
	private int idade;
	private String sexo;

	public Criminoso(long cpf, String nome, int idade, String sexo) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.idade = idade;
		this.sexo = sexo;
	}
	
	public Criminoso() {
		super();
		this.cpf  = 0;
		this.nome = "";
		this.idade = 0;
		this.sexo = "";
	}
	
	public long getCpf() {
		return cpf;
	}
	public void setCpf(long cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String toString() {
		return "Criminoso [cpf=" + cpf + ", nome=" + nome + ", idade=" + idade + ", sexo=" + sexo + "]";
	}
}
