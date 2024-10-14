package com.eldar.challenge.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.eldar.challenge.dto.TasaOperacionDTO;
import com.eldar.challenge.enums.MarcaTarjetaEnum;
import com.eldar.challenge.models.Operacion;
import com.eldar.challenge.models.Tarjeta;
import com.eldar.challenge.repository.OperacionRepository;
import com.eldar.challenge.repository.TarjetaRepository;
import com.eldar.challenge.services.impl.OperacionServiceImpl;

@ExtendWith(MockitoExtension.class)
class OperacionServiceImplTest {

	@InjectMocks
	private OperacionServiceImpl service;

	@Mock
	private TarjetaRepository tarjetaRepository;

	@Mock
	private OperacionRepository operacionRepository;

	@Mock
	private TarjetaService tarjetaService;

	Tarjeta tarjeta;
	Operacion operacion;

	@BeforeEach
	void onStart() {
		MockitoAnnotations.openMocks(this);

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
		operacion.setFechaTransaccion(new Date());
	}

	@Test
	void realizarOperacion() {
		// caso correcto
		when(tarjetaRepository.findById(anyLong())).thenReturn(Optional.of(tarjeta));
		when(tarjetaService.isTarjetaValida(anyLong())).thenReturn(true);
		when(operacionRepository.save(any(Operacion.class))).thenReturn(operacion);

		Operacion retorno = service.realizarOperacion(123L, new BigDecimal(200));

		assertNotNull(retorno);
		assertEquals(operacion, retorno);

		// casos importes inválidos
		assertThrows(IllegalArgumentException.class, () -> service.realizarOperacion(123L, new BigDecimal(1000)));
		assertThrows(IllegalArgumentException.class, () -> service.realizarOperacion(123L, new BigDecimal(0)));
		assertThrows(IllegalArgumentException.class, () -> service.realizarOperacion(123L, new BigDecimal(-500)));

		// caso tarjeta vencida
		when(tarjetaService.isTarjetaValida(anyLong())).thenReturn(false);

		assertThrows(IllegalArgumentException.class, () -> service.realizarOperacion(123L, new BigDecimal(200)));

		// caso tarjeta inexistente
		when(tarjetaRepository.findById(anyLong())).thenReturn(Optional.empty());

		assertThrows(IllegalArgumentException.class, () -> service.realizarOperacion(123L, new BigDecimal(200)));
	}

	@Test
	void consultarTasa() {
		// casos correctos
		when(operacionRepository.findById(anyLong())).thenReturn(Optional.of(operacion));

		TasaOperacionDTO retorno = service.consultarTasa(123L);

		assertEquals(MarcaTarjetaEnum.NARA.getValue(), retorno.getMarcaTarjeta());
		assertEquals(200f, retorno.getImporte());

		tarjeta.setMarca(MarcaTarjetaEnum.VISA);

		when(operacionRepository.findById(anyLong())).thenReturn(Optional.of(operacion));

		retorno = service.consultarTasa(123L);

		assertEquals(MarcaTarjetaEnum.VISA.getValue(), retorno.getMarcaTarjeta());
		assertEquals(200f, retorno.getImporte());

		tarjeta.setMarca(MarcaTarjetaEnum.AMEX);

		when(operacionRepository.findById(anyLong())).thenReturn(Optional.of(operacion));

		retorno = service.consultarTasa(123L);

		assertEquals(MarcaTarjetaEnum.AMEX.getValue(), retorno.getMarcaTarjeta());
		assertEquals(200f, retorno.getImporte());

		// caso operación inexistente
		when(operacionRepository.findById(anyLong())).thenReturn(Optional.empty());

		assertThrows(IllegalArgumentException.class, () -> service.consultarTasa(123L));
	}
}
