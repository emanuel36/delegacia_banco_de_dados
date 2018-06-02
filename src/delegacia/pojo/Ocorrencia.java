package delegacia.pojo;

public class Ocorrencia {
	private int id;
	private int id_delito;
	private long cpf_vitima;
	private long cpf_criminoso;
	private String descricao;
	private String data;
	private String hora;
	
	public Ocorrencia(int id, int id_delito, long cpf_vitima, long cpf_criminoso, String descricao, String data, String hora) {
		super();
		this.id = id;
		this.id_delito = id_delito;
		this.cpf_vitima = cpf_vitima;
		this.cpf_criminoso = cpf_criminoso;
		this.descricao = descricao;
		this.data = data;
		this.hora = hora;
	}
	
	public Ocorrencia(int id_delito, long cpf_vitima, long cpf_criminoso, String descricao, String data, String hora) {
		super();
		this.id_delito = id_delito;
		this.cpf_vitima = cpf_vitima;
		this.cpf_criminoso = cpf_criminoso;
		this.descricao = descricao;
		this.data = data;
		this.hora = hora;
	}
	
	public Ocorrencia() {
		super();
		this.id_delito = 0;
		this.cpf_vitima = 0;
		this.cpf_criminoso = 0;
		this.descricao = "";
		this.data = "";
		this.hora = "";
	}
	
	public int getId_delito() {
		return id_delito;
	}
	public void setId_delito(int id_delito) {
		this.id_delito = id_delito;
	}
	public long getCpf_vitima() {
		return cpf_vitima;
	}
	public void setCpf_vitima(long cpf_vitima) {
		this.cpf_vitima = cpf_vitima;
	}
	public long getCpf_criminoso() {
		return cpf_criminoso;
	}
	public void setCpf_criminoso(long cpf_criminoso) {
		this.cpf_criminoso = cpf_criminoso;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String toString() {
		return "Ocorrencia [id=" + id + ", id_delito=" + id_delito + ", cpf_vitima=" + cpf_vitima + ", cpf_criminoso=" + cpf_criminoso + ", descricao=" + descricao + ", data=" + data + ", hora=" + hora + "]";
	}
}
