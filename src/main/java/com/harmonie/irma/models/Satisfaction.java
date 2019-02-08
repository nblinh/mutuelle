package com.harmonie.irma.models;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the satisfaction database table.
 * 
 */
@Entity
@NamedQuery(name="Satisfaction.findAll", query="SELECT s FROM Satisfaction s")
public class Satisfaction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String commentaire;

	@Column(name="date_creation")
	private Timestamp dateCreation;

	@Column(name="note_globale")
	private Integer noteGlobale;

	@Column(name="note_simplicite")
	private Integer noteSimplicite;

	@Column(name="reponse_trouvee")
	private Integer reponseTrouvee;

	private Integer reutilisera;

	public Satisfaction() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCommentaire() {
		return this.commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public Timestamp getDateCreation() {
		return this.dateCreation;
	}

	public void setDateCreation(Timestamp dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Integer getNoteGlobale() {
		return this.noteGlobale;
	}

	public void setNoteGlobale(Integer noteGlobale) {
		this.noteGlobale = noteGlobale;
	}

	public Integer getNoteSimplicite() {
		return this.noteSimplicite;
	}

	public void setNoteSimplicite(Integer noteSimplicite) {
		this.noteSimplicite = noteSimplicite;
	}

	public Integer getReponseTrouvee() {
		return this.reponseTrouvee;
	}

	public void setReponseTrouvee(Integer reponseTrouvee) {
		this.reponseTrouvee = reponseTrouvee;
	}

	public Integer getReutilisera() {
		return this.reutilisera;
	}

	public void setReutilisera(Integer reutilisera) {
		this.reutilisera = reutilisera;
	}

}