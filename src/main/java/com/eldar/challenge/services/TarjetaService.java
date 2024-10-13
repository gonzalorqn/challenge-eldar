package com.eldar.challenge.services;

import org.springframework.stereotype.Service;

import com.eldar.challenge.enums.MarcaTarjetaEnum;
import com.eldar.challenge.models.Tarjeta;

@Service
public interface TarjetaService {

	/**
	 * Crea una nueva tarjeta y guarda su metadata.
	 * 
	 * @param marca            Marca de la tarjeta.
	 * @param numero           Número de la tarjeta.
	 * @param cardholder       Nombre y apellido del dueño de la tarjeta.
	 * @param fechaVencimiento Fecha de vencimiento de la tarjeta.
	 * @return Objeto Tarjeta creado.
	 */
	public Tarjeta crearTarjeta(MarcaTarjetaEnum marca, String numero, String cardholder, String fechaVencimiento);

	/**
	 * Obtiene todos los datos de una tarjeta. A partir del id de la tarjeta,
	 * recupera los datos desde el repositorio.
	 * 
	 * @param idTarjeta El id de la tarjeta a buscar.
	 * @return objeto Tarjeta encontrado.
	 */
	public Tarjeta obtenerTarjeta(Long idTarjeta);

	/**
	 * Verifica si una tarjeta es válida para operar en el sistema. Para ello, se
	 * chequea que no esté vencida.
	 * 
	 * @param idTarjeta El id de la tarjeta a consultar.
	 * @return booleano que indica si es válida o no.
	 */
	public boolean isTarjetaValida(Long idTarjeta);
}
