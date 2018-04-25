package vos;

import java.sql.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class VOGanadores {

	@JsonProperty(value="id")
	Long id;
	@JsonProperty(value="idCandidato")
	Long idCandidato;
	@JsonProperty(value="idEleccion")
	Long idEleccion;
	@JsonProperty(value="votos")
	Long votos;
	
	
	public VOGanadores() {
		// TODO Auto-generated constructor stub
	}

	
	public VOGanadores(@JsonProperty(value="id")Long id,@JsonProperty(value="idCandidato") Long idCandidato,@JsonProperty(value="idEleccion") Long idEleccion,@JsonProperty(value="votos") Long votos) {
		super();
		this.id = id;
		this.idCandidato = idCandidato;
		this.idEleccion = idEleccion;
		this.votos = votos;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getIdCandidato() {
		return idCandidato;
	}


	public void setIdCandidato(Long idCandidato) {
		this.idCandidato = idCandidato;
	}


	public Long getIdEleccion() {
		return idEleccion;
	}


	public void setIdEleccion(Long idEleccion) {
		this.idEleccion = idEleccion;
	}


	public Long getVotos() {
		return votos;
	}


	public void setVotos(Long votos) {
		this.votos = votos;
	}
	
}
