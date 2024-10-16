package com.eldar.challenge.services.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eldar.challenge.enums.MarcaTarjetaEnum;
import com.eldar.challenge.models.Tarjeta;
import com.eldar.challenge.repository.TarjetaRepository;
import com.eldar.challenge.services.TarjetaService;

@Service
public class TarjetaServiceImpl implements TarjetaService {

	@Autowired
	private TarjetaRepository tarjetaRepository;

	@Override
	public Tarjeta crearTarjeta(String marca, String numero, String cardholder, String fechaVencimiento) {
		Optional<Tarjeta> tarjetaOptional = tarjetaRepository.findByNumero(numero);
		if (tarjetaOptional.isPresent()) {
			throw new IllegalArgumentException(
					"Valor inesperado '" + numero + "'. No pueden existir 2 tarjetas con el mismo número.");
		}

		verificarFechaVencimiento(fechaVencimiento);

		Tarjeta tarjeta = new Tarjeta();
		tarjeta.setMarca(MarcaTarjetaEnum.fromValue(marca));
		tarjeta.setNumero(numero);
		tarjeta.setCardholder(cardholder);
		tarjeta.setFechaVencimiento(fechaVencimiento);

		return tarjetaRepository.save(tarjeta);
	}

	@Override
	public Tarjeta obtenerTarjeta(Long idTarjeta) {
		Optional<Tarjeta> tarjetaOptional = tarjetaRepository.findById(idTarjeta);
		if (tarjetaOptional.isPresent()) {
			return tarjetaOptional.get();
		} else {
			throw new IllegalArgumentException("No existe una tarjeta con el ID '" + idTarjeta + "'.");
		}
	}

	@Override
	public boolean isTarjetaValida(Long idTarjeta) {
		Optional<Tarjeta> tarjetaOptional = tarjetaRepository.findById(idTarjeta);
		if (tarjetaOptional.isPresent()) {
			Tarjeta tarjeta = tarjetaOptional.get();

			// Parseo la fecha a Date
			SimpleDateFormat formatter = new SimpleDateFormat("MM/yy");
			try {
				Date fechaVencimiento = formatter.parse(tarjeta.getFechaVencimiento());
				Date fechaActual = new Date();
				return fechaActual.before(fechaVencimiento);
			} catch (ParseException e) {
				e.printStackTrace();
				return false;
			}
		} else {
			throw new IllegalArgumentException("No existe una tarjeta con el ID '" + idTarjeta + "'.");
		}
	}

	/**
	 * Verifica que la fecha de vencimiento de la tarjeta tenga el formato correcto
	 * y sea coherente.
	 * 
	 * @param fechaVencimiento Fecha a verificar.
	 */
	private void verificarFechaVencimiento(String fechaVencimiento) {
		// Parseo de la fecha de vencimiento
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
		try {
			YearMonth.parse(fechaVencimiento, formatter);
		} catch (DateTimeParseException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Valor inesperado '" + fechaVencimiento
					+ "'. La fecha de vencimiento debe seguir el formato 'MM/yy', ejemplo: 12/26.");
		}
	}
}
