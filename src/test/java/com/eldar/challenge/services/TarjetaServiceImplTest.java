package com.eldar.challenge.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.eldar.challenge.enums.MarcaTarjetaEnum;
import com.eldar.challenge.models.Tarjeta;
import com.eldar.challenge.repository.TarjetaRepository;
import com.eldar.challenge.services.impl.TarjetaServiceImpl;

@ExtendWith(MockitoExtension.class)
class TarjetaServiceImplTest {

	@InjectMocks
	private TarjetaServiceImpl service;

	@Mock
	private TarjetaRepository tarjetaRepository;

	Tarjeta tarjeta;

	@BeforeEach
	void onStart() {
		MockitoAnnotations.openMocks(this);

		tarjeta = new Tarjeta();
		tarjeta.setId(123L);
		tarjeta.setMarca(MarcaTarjetaEnum.NARA);
		tarjeta.setCardholder("Juan Perez");
		tarjeta.setNumero("1111 2222 3333 4444");
		tarjeta.setFechaVencimiento("06/25");
	}

	@Test
	void crearTarjeta() {
		// caso correcto
		when(tarjetaRepository.findByNumero(anyString())).thenReturn(Optional.empty());
		when(tarjetaRepository.save(any(Tarjeta.class))).thenReturn(tarjeta);

		Tarjeta retorno = service.crearTarjeta(MarcaTarjetaEnum.NARA.getValue(), "1111 2222 3333 4444", "Juan Perez",
				"06/25");

		assertNotNull(retorno);
		assertEquals(tarjeta, retorno);

		// caso fecha vencimiento inválida
		assertThrows(IllegalArgumentException.class, () -> service.crearTarjeta(MarcaTarjetaEnum.NARA.getValue(),
				"1111 2222 3333 4444", "Juan Perez", "06/2025"));

		// caso tarjeta existente
		when(tarjetaRepository.findByNumero(anyString())).thenReturn(Optional.of(tarjeta));

		assertThrows(IllegalArgumentException.class, () -> service.crearTarjeta(MarcaTarjetaEnum.NARA.getValue(),
				"1111 2222 3333 4444", "Juan Perez", "06/25"));
	}

	@Test
	void obtenerTarjeta() {
		// caso correcto
		when(tarjetaRepository.findById(anyLong())).thenReturn(Optional.of(tarjeta));

		Tarjeta retorno = service.obtenerTarjeta(123L);

		assertNotNull(retorno);
		assertEquals(tarjeta, retorno);

		// caso tarjeta inexistente
		when(tarjetaRepository.findById(anyLong())).thenReturn(Optional.empty());

		assertThrows(IllegalArgumentException.class, () -> service.obtenerTarjeta(123L));
	}

	@Test
	void isTarjetaValida() {
		// caso correcto
		when(tarjetaRepository.findById(anyLong())).thenReturn(Optional.of(tarjeta));

		Boolean retorno = service.isTarjetaValida(123L);

		assertNotNull(retorno);
		assertEquals(true, retorno);

		// caso tarjeta inexistente
		when(tarjetaRepository.findById(anyLong())).thenReturn(Optional.empty());

		assertThrows(IllegalArgumentException.class, () -> service.isTarjetaValida(123L));
	}
}
