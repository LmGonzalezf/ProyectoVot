package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class VOCandidatoLista {

	@JsonProperty(value="idCandidato")
	Long idCandidato;
	@JsonProperty(value="numeroLista")
	String numeroLista;
	@JsonProperty(value="idLista")
	Long idLista;
	
	public VOCandidatoLista(@JsonProperty(value="idCandidato")Long idCandidato,@JsonProperty(value="numeroLista") String numeroLista,@JsonProperty(value="idLista") Long idLista) {
		super();
		this.idCandidato = idCandidato;
		this.numeroLista = numeroLista;
		this.idLista = idLista;
	}

	public Long getIdCandidato() {
		return idCandidato;
	}

	public void setIdCandidato(Long idCandidato) {
		this.idCandidato = idCandidato;
	}

	public String getNumeroLista() {
		return numeroLista;
	}

	public void setNumeroLista(String numeroLista) {
		this.numeroLista = numeroLista;
	}

	public Long getIdLista() {
		return idLista;
	}

	public void setIdLista(Long idLista) {
		this.idLista = idLista;
	}
	
	

}
