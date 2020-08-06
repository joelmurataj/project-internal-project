package com.amd.internal.project.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="\"rank\"")
public class Rank implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_rank", nullable = false, length = 11)
	private int idRank;
	
	@Column(name = "name", nullable = false, length = 45, unique=true)
	private String name;

	public int getIdRank() {
		return idRank;
	}

	public void setIdRank(int idRank) {
		this.idRank = idRank;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
}
