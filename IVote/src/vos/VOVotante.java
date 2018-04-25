package vos;

import java.sql.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class VOVotante {
	@JsonProperty(value="id")
	Long id;
	@JsonProperty(value="usuario")
	String usuario;
	@JsonProperty(value="genero")
	String genero;
	@JsonProperty(value="fechaNacimiento")
	Date fechaNacimiento;
	@JsonProperty(value="password")
	String password;
	
	public VOVotante(@JsonProperty(value="id")Long id, @JsonProperty(value="usuario")String usuario, @JsonProperty(value="genero")String genero, @JsonProperty(value="fechaNacimiento")Date fechaNacimiento, @JsonProperty(value="password")String password) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.genero = genero;
		this.fechaNacimiento = fechaNacimiento;
		this.password = password;
	}
	
	public VOVotante() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
