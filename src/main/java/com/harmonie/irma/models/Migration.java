package com.harmonie.irma.models;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the migrations database table.
 * 
 */
@Entity
@Table(name="migrations")
@NamedQuery(name="Migration.findAll", query="SELECT m FROM Migration m")
public class Migration implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String name;

	@Column(name="run_on")
	private Timestamp runOn;

	public Migration() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getRunOn() {
		return this.runOn;
	}

	public void setRunOn(Timestamp runOn) {
		this.runOn = runOn;
	}

}