package com.eldar.challenge.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import com.eldar.challenge.api.TarjetaApi;
import com.eldar.challenge.dto.TarjetaDTO;

import java.util.Optional;

import javax.validation.Valid;

@Controller
@RequestMapping("${openapi.swaggerChallengeEldar.base-path:/}")
public class TarjetaController implements TarjetaApi {

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
		// TODO Auto-generated method stub
		return TarjetaApi.super.crearTarjeta(tarjetaDTO);
	}

	@Override
	public ResponseEntity<Boolean> getIsTarjetaValida(String idTarjeta) {
		// TODO Auto-generated method stub
		return TarjetaApi.super.getIsTarjetaValida(idTarjeta);
	}

	@Override
	public ResponseEntity<TarjetaDTO> getTarjeta(String idTarjeta) {
		// TODO Auto-generated method stub
		return TarjetaApi.super.getTarjeta(idTarjeta);
	}

}
