package vos;

import java.sql.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class VOCandidato {
	@JsonProperty(value="id")
	Long id;
	@JsonProperty(value="nombre")
	String nombre;
	@JsonProperty(value="genero")
	String genero;
	@JsonProperty(value="fechaNacimiento")
	Date fechaNacimiento;
	
	public VOCandidato(@JsonProperty(value="id")Long id, @JsonProperty(value="nombre")String nombre, @JsonProperty(value="genero")String genero, @JsonProperty(value="fechaNacimiento")Date fechaNacimiento) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.genero = genero;
		this.fechaNacimiento = fechaNacimiento;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	
	
}
