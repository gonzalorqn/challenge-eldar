package com.eldar.challenge.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import com.eldar.challenge.api.TarjetaApi;
import com.eldar.challenge.dto.TarjetaDTO;
import com.eldar.challenge.models.Tarjeta;
import com.eldar.challenge.services.TarjetaService;

@Controller
@RequestMapping("${openapi.swaggerChallengeEldar.base-path:/}")
public class TarjetaController implements TarjetaApi {

	@Autowired
	private TarjetaService tarjetaService;

	private final NativeWebRequest request;

	@Autowired
	public TarjetaController(NativeWebRequest request) {
		this.request = request;
	}

	@Override
	public Optional<NativeWebRequest> getRequest() {
		return Optional.ofNullable(request);
	}

	@Override
	public ResponseEntity<TarjetaDTO> crearTarjeta(@Valid TarjetaDTO tarjetaDTO) {
		Tarjeta tarjetaNueva = tarjetaService.crearTarjeta(tarjetaDTO.getMarca(), tarjetaDTO.getNumero(),
				tarjetaDTO.getCardholder(), tarjetaDTO.getFechaVencimiento());

		TarjetaDTO tarjetaNuevaDto = new TarjetaDTO();
		tarjetaNuevaDto.setId(tarjetaNueva.getId());
		tarjetaNuevaDto.setMarca(tarjetaNueva.getMarca().getValue());
		tarjetaNuevaDto.setNumero(tarjetaNueva.getNumero());
		tarjetaNuevaDto.setCardholder(tarjetaNueva.getCardholder());
		tarjetaNuevaDto.setFechaVencimiento(tarjetaNueva.getFechaVencimiento());

		return ResponseEntity.ok().body(tarjetaNuevaDto);
	}

	@Override
	public ResponseEntity<String> getIsTarjetaValida(String idTarjeta) {
		String retorno = "";
		if (tarjetaService.isTarjetaValida(Long.parseLong(idTarjeta))) {
			retorno = "La tarjeta indicada es válida para operar en el sistema.";
		} else {
			retorno = "La tarjeta indicada está vencida y no es válida para operar en el sistema.";
		}

		return ResponseEntity.ok().body(retorno);
	}

	@Override
	public ResponseEntity<TarjetaDTO> getTarjeta(String idTarjeta) {
		Tarjeta tarjeta = tarjetaService.obtenerTarjeta(Long.parseLong(idTarjeta));

		TarjetaDTO tarjetaDto = new TarjetaDTO();
		tarjetaDto.setId(tarjeta.getId());
		tarjetaDto.setMarca(tarjeta.getMarca().getValue());
		tarjetaDto.setNumero(tarjeta.getNumero());
		tarjetaDto.setCardholder(tarjeta.getCardholder());
		tarjetaDto.setFechaVencimiento(tarjeta.getFechaVencimiento());
		return ResponseEntity.ok().body(tarjetaDto);
	}

}
