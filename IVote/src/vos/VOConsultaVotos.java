package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class VOConsultaVotos {
	@JsonProperty(value="tipo")
	String tipo;
	@JsonProperty(value="votos")
	Long votos;
	
	
	public VOConsultaVotos(@JsonProperty(value="tipo")String tipo, @JsonProperty(value="votos")Long votos) {
		super();
		this.tipo = tipo;
		this.votos = votos;
	}


	public VOConsultaVotos() {
		// TODO Auto-generated constructor stub
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public Long getVotos() {
		return votos;
	}


	public void setVotos(Long votos) {
		this.votos = votos;
	}
	
	
	
	
	
}
