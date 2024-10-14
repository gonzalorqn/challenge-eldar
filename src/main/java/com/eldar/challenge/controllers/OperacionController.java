package com.eldar.challenge.controllers;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import com.eldar.challenge.api.OperacionApi;
import com.eldar.challenge.dto.OperacionDTO;
import com.eldar.challenge.dto.TarjetaDTO;
import com.eldar.challenge.dto.TasaOperacionDTO;
import com.eldar.challenge.models.Operacion;
import com.eldar.challenge.models.Tarjeta;
import com.eldar.challenge.services.OperacionService;

@Controller
@RequestMapping("${openapi.swaggerChallengeEldar.base-path:/}")
public class OperacionController implements OperacionApi {

	@Autowired
	private OperacionService operacionService;

	private final NativeWebRequest request;

	@Autowired
	public OperacionController(NativeWebRequest request) {
		this.request = request;
	}

	@Override
	public Optional<NativeWebRequest> getRequest() {
		return Optional.ofNullable(request);
	}

	@Override
	public ResponseEntity<TasaOperacionDTO> consultarTasaOperacion(String idOperacion) {
		// TODO Auto-generated method stub
		return OperacionApi.super.consultarTasaOperacion(idOperacion);
	}

	@Override
	public ResponseEntity<OperacionDTO> realizarOperacion(@NotNull @Valid String idTarjeta,
			@NotNull @Valid String importe) {
		try {
			BigDecimal importeBigDecimal = new BigDecimal(importe);
			Operacion operacionNueva = operacionService.realizarOperacion(Long.parseLong(idTarjeta), importeBigDecimal);

			OperacionDTO operacionNuevaDto = new OperacionDTO();
			operacionNuevaDto.setId(operacionNueva.getId());
			operacionNuevaDto.setImporte(operacionNueva.getImporte().floatValue());

			// Pasaje de Date a OffsetDateTime
			ZoneId zoneId = ZoneId.systemDefault();
			Date fechaTransaccion = operacionNueva.getFechaTransaccion();
			operacionNuevaDto.setFechaTransaccion(
					fechaTransaccion.toInstant().atOffset(zoneId.getRules().getOffset(fechaTransaccion.toInstant())));

			Tarjeta tarjeta = operacionNueva.getTarjeta();

			TarjetaDTO tarjetaDto = new TarjetaDTO();
			tarjetaDto.setId(tarjeta.getId());
			tarjetaDto.setMarca(tarjeta.getMarca().getValue());
			tarjetaDto.setNumero(tarjeta.getNumero());
			tarjetaDto.setCardholder(tarjeta.getCardholder());
			tarjetaDto.setFechaVencimiento(tarjeta.getFechaVencimiento());

			operacionNuevaDto.setTarjeta(tarjetaDto);

			return ResponseEntity.ok().body(operacionNuevaDto);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Valor inesperado '" + importe + "'. El importe debe ser un número.");
		}
	}

}
