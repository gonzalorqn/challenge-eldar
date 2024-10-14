package com.eldar.challenge.services.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eldar.challenge.dto.TasaOperacionDTO;
import com.eldar.challenge.models.Operacion;
import com.eldar.challenge.models.Tarjeta;
import com.eldar.challenge.repository.OperacionRepository;
import com.eldar.challenge.repository.TarjetaRepository;
import com.eldar.challenge.services.OperacionService;
import com.eldar.challenge.services.TarjetaService;

@Service
public class OperacionServiceImpl implements OperacionService {

	@Autowired
	private TarjetaRepository tarjetaRepository;

	@Autowired
	private OperacionRepository operacionRepository;

	@Autowired
	private TarjetaService tarjetaService;

	@Override
	public Operacion realizarOperacion(Long idTarjeta, BigDecimal importe) {
		Optional<Tarjeta> tarjetaOptional = tarjetaRepository.findById(idTarjeta);
		if (tarjetaOptional.isPresent()) {
			if (!tarjetaService.isTarjetaValida(idTarjeta)) {
				throw new IllegalArgumentException(
						"La tarjeta indicada está vencida y no es válida para operar en el sistema.");
			}

			Tarjeta tarjeta = tarjetaOptional.get();

			// Chequeo que el importe sea menor a 1000 y mayor a 0
			int comparisonMenor = importe.compareTo(BigDecimal.valueOf(1000));
			int comparisonMayor = importe.compareTo(BigDecimal.valueOf(0));

			if (comparisonMenor < 0 && comparisonMayor > 0) {
				Operacion operacion = new Operacion();
				operacion.setImporte(importe);
				operacion.setFechaTransaccion(new Date());
				operacion.setTarjeta(tarjeta);

				return operacionRepository.save(operacion);
			} else {
				throw new IllegalArgumentException(
						"Valor inesperado '" + importe + "'. El importe debe ser menor a 1000 y mayor a 0.");
			}
		} else {
			throw new IllegalArgumentException("No existe una tarjeta con el ID '" + idTarjeta + "'.");
		}
	}

	@Override
	public TasaOperacionDTO consultarTasa(Long idOperacion) {
		// TODO Auto-generated method stub
		return null;
	}

}
