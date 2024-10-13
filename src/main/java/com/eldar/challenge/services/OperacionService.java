package com.eldar.challenge.services;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.eldar.challenge.dto.TasaOperacionDTO;
import com.eldar.challenge.models.Operacion;

@Service
public interface OperacionService {

	/**
	 * Realiza una operación en el sistema con la tarjeta especificada y guarda la
	 * información de dicha operación. El importe debe ser menor a 1000 y la tarjeta
	 * debe ser válida para realizar la operación.
	 * 
	 * @param idTarjeta El id de la tarjeta para realizar la operación.
	 * @param importe   Importe de la operación.
	 * @return Objeto Operacion creado.
	 */
	public Operacion realizarOperacion(Long idTarjeta, BigDecimal importe);

	/**
	 * Calcula la tasa de una operación.
	 * 
	 * @param idOperacion El id de la operación a consultar.
	 * @return Objeto con la tasa, el importe y la marca de la tarjeta de la
	 *         operación.
	 */
	public TasaOperacionDTO consultarTasa(Long idOperacion);
}
