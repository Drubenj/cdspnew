package com.mysena.cdsp3.app.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="cate_menu")
@Data
public class CateMenu implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_categoria")
	private Integer id;
	
	@Column(name="nombre")
	private String nombre;
	
	@OneToMany(mappedBy="categoria")
	private List<Menu> ListaMenu;

	public CateMenu() {
	}

	public CateMenu(Integer id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "CateMenu [id=" + id + ", nombre=" + nombre + "]";
	}

	public CateMenu(String nombre) {
		super();
		this.nombre = nombre;
	}
	
	
	
}
