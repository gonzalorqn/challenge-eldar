package com.eldar.challenge.controllers;

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
import com.eldar.challenge.dto.TasaOperacionDTO;

@Controller
@RequestMapping("${openapi.swaggerChallengeEldar.base-path:/}")
public class OperacionController implements OperacionApi {

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
		// TODO Auto-generated method stub
		return OperacionApi.super.realizarOperacion(idTarjeta, importe);
	}

}
