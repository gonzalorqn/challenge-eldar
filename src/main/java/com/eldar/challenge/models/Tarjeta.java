package com.eldar.challenge.models;

import java.util.Date;

import com.eldar.challenge.enums.MarcaTarjetaEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "tarjeta")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tarjeta {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "marca")
	@Enumerated(EnumType.STRING)
	private MarcaTarjetaEnum marca;

	@Column(name = "numero")
	private String numero;

	@Column(name = "fecha_vencimiento")
	private Date fechaVencimiento;

	@Column(name = "cardholder")
	private String cardholder;

	@Override
	public boolean equals(Object obj) {
		// Si se compara al mismo objeto devuelvo true
		if (obj == this) {
			return true;
		}

		// Si no es una instancia de Tarjeta devuelvo false
		if (!(obj instanceof Tarjeta)) {
			return false;
		}

		// Casteo del objeto
		Tarjeta tarjeta = (Tarjeta) obj;

		// Comparo segun número de tarjeta
		return this.numero.equals(tarjeta.numero);
	}
}
