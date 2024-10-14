package com.eldar.challenge.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.eldar.challenge.dto.TarjetaDTO;
import com.eldar.challenge.enums.MarcaTarjetaEnum;
import com.eldar.challenge.models.Tarjeta;
import com.eldar.challenge.services.TarjetaService;

@ExtendWith(MockitoExtension.class)
class TarjetaControllerTest {

	@InjectMocks
	private TarjetaController controller;

	@Mock
	private TarjetaService tarjetaService;

	TarjetaDTO tarjetaDto;
	Tarjeta tarjeta;

	@BeforeEach
	void onStart() {
		MockitoAnnotations.openMocks(this);

		tarjetaDto = new TarjetaDTO();
		tarjetaDto.setId(123L);
		tarjetaDto.setMarca("Naranja");
		tarjetaDto.setCardholder("Juan Perez");
		tarjetaDto.setNumero("1111 2222 3333 4444");
		tarjetaDto.setFechaVencimiento("06/25");

		tarjeta = new Tarjeta();
		tarjeta.setId(123L);
		tarjeta.setMarca(MarcaTarjetaEnum.NARA);
		tarjeta.setCardholder("Juan Perez");
		tarjeta.setNumero("1111 2222 3333 4444");
		tarjeta.setFechaVencimiento("06/25");
	}

	@Test
	void crearTarjeta() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		when(tarjetaService.crearTarjeta(anyString(), anyString(), anyString(), anyString())).thenReturn(tarjeta);

		ResponseEntity<TarjetaDTO> response = controller.crearTarjeta(tarjetaDto);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(tarjetaDto, response.getBody());
	}

	@Test
	void getTarjeta() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		when(tarjetaService.obtenerTarjeta(anyLong())).thenReturn(tarjeta);

		ResponseEntity<TarjetaDTO> response = controller.getTarjeta("123");

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(tarjetaDto, response.getBody());
	}

	@Test
	void getIsTarjetaValida() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		// caso tarjeta válida
		when(tarjetaService.isTarjetaValida(anyLong())).thenReturn(true);

		ResponseEntity<String> response = controller.getIsTarjetaValida("123");

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("La tarjeta indicada es válida para operar en el sistema.", response.getBody());

		// caso tarjeta inválida
		when(tarjetaService.isTarjetaValida(anyLong())).thenReturn(false);

		response = controller.getIsTarjetaValida("123");

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("La tarjeta indicada está vencida y no es válida para operar en el sistema.", response.getBody());
	}
}
