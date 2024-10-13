package com.eldar.challenge.models;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "operacion")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Operacion {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "importe")
	private BigDecimal importe;

	@ManyToOne
	@JoinColumn(name = "tarjeta_id")
	private Tarjeta tarjeta;

	@Column(name = "fecha_transaccion")
	private Date fechaTransaccion;
}
