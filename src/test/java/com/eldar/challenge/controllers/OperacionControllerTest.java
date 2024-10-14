package com.eldar.challenge.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.Date;

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

import com.eldar.challenge.dto.OperacionDTO;
import com.eldar.challenge.dto.TarjetaDTO;
import com.eldar.challenge.dto.TasaOperacionDTO;
import com.eldar.challenge.enums.MarcaTarjetaEnum;
import com.eldar.challenge.models.Operacion;
import com.eldar.challenge.models.Tarjeta;
import com.eldar.challenge.services.OperacionService;

@ExtendWith(MockitoExtension.class)
class OperacionControllerTest {

	@InjectMocks
	private OperacionController controller;

	@Mock
	private OperacionService operacionService;

	TarjetaDTO tarjetaDto;
	OperacionDTO operacionDto;
	TasaOperacionDTO tasaOperacionDto;
	Tarjeta tarjeta;
	Operacion operacion;

	@BeforeEach
	void onStart() {
		MockitoAnnotations.openMocks(this);

		Date fechaTransaccion = new Date();

		tarjetaDto = new TarjetaDTO();
		tarjetaDto.setId(123L);
		tarjetaDto.setMarca("Naranja");
		tarjetaDto.setCardholder("Juan Perez");
		tarjetaDto.setNumero("1111 2222 3333 4444");
		tarjetaDto.setFechaVencimiento("06/25");

		operacionDto = new OperacionDTO();
		operacionDto.setId(123L);
		operacionDto.setImporte(200f);
		operacionDto.setTarjeta(tarjetaDto);
		// Pasaje de Date a OffsetDateTime
		ZoneId zoneId = ZoneId.systemDefault();
		operacionDto.setFechaTransaccion(
				fechaTransaccion.toInstant().atOffset(zoneId.getRules().getOffset(fechaTransaccion.toInstant())));

		tasaOperacionDto = new TasaOperacionDTO();
		tasaOperacionDto.setImporte(200f);
		tasaOperacionDto.setMarcaTarjeta("Naranja");
		tasaOperacionDto.setTasaOperacion("1,20%");

		tarjeta = new Tarjeta();
		tarjeta.setId(123L);
		tarjeta.setMarca(MarcaTarjetaEnum.NARA);
		tarjeta.setCardholder("Juan Perez");
		tarjeta.setNumero("1111 2222 3333 4444");
		tarjeta.setFechaVencimiento("06/25");

		operacion = new Operacion();
		operacion.setId(123L);
		operacion.setImporte(new BigDecimal(200));
		operacion.setTarjeta(tarjeta);
		operacion.setFechaTransaccion(fechaTransaccion);
	}

	@Test
	void realizarOperacion() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		// caso correcto
		when(operacionService.realizarOperacion(anyLong(), any(BigDecimal.class))).thenReturn(operacion);

		ResponseEntity<OperacionDTO> response = controller.realizarOperacion("123", "200");

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(operacionDto, response.getBody());

		// caso importe inválido
		assertThrows(IllegalArgumentException.class, () -> controller.realizarOperacion("123", "200a"));
	}

	@Test
	void consultarTasaOperacion() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		when(operacionService.consultarTasa(anyLong())).thenReturn(tasaOperacionDto);

		ResponseEntity<TasaOperacionDTO> response = controller.consultarTasaOperacion("123");

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(tasaOperacionDto, response.getBody());
	}
}
