package com.mysena.cdsp3.app.entities;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "inventario")
public class Inventario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idinventario;
	private String fecha;
	private int cantidadtotal;
	private int cantidadmovimiento;
	private int saldo;
	
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "insumo")
	private Insumo insumo;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "movimiento")
	private Movimiento movimiento;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario")
	private Usuario usuario;

	public Inventario() {
		super();
	}
	
	public Inventario(int idinventario, String fecha, int cantidadtotal, int cantidadmovimiento, int saldo,
			Insumo insumo, Movimiento movimiento, Usuario usuario) {
		super();
		this.idinventario = idinventario;
		this.fecha = fecha;
		this.cantidadtotal = cantidadtotal;
		this.cantidadmovimiento = cantidadmovimiento;
		this.saldo = saldo;
		this.insumo = insumo;
		this.movimiento = movimiento;
		this.usuario = usuario;
	}

	public int getIdinventario() {
		return idinventario;
	}

	public void setIdinventario(int idinventario) {
		this.idinventario = idinventario;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public int getCantidadtotal() {
		return cantidadtotal;
	}

	public void setCantidadtotal(int cantidadtotal) {
		this.cantidadtotal = cantidadtotal;
	}

	public int getCantidadmovimiento() {
		return cantidadmovimiento;
	}

	public void setCantidadmovimiento(int cantidadmovimiento) {
		this.cantidadmovimiento = cantidadmovimiento;
	}

	public int getSaldo() {
		return saldo;
	}

	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}

	public Insumo getInsumo() {
		return insumo;
	}

	public void setInsumo(Insumo insumo) {
		this.insumo = insumo;
	}

	public Movimiento getMovimiento() {
		return movimiento;
	}

	public void setMovimiento(Movimiento movimiento) {
		this.movimiento = movimiento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Inventario [idinventario=" + idinventario + ", fecha=" + fecha + ", cantidadtotal=" + cantidadtotal
				+ ", cantidadmovimiento=" + cantidadmovimiento + ", saldo=" + saldo + ", insumo=" + insumo
				+ ", movimiento=" + movimiento + ", usuario=" + usuario + "]";
	}
	
}
