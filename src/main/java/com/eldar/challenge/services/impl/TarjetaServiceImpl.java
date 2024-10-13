package com.eldar.challenge.services.impl;

import java.util.Date;

import com.eldar.challenge.enums.MarcaTarjetaEnum;
import com.eldar.challenge.models.Tarjeta;
import com.eldar.challenge.services.TarjetaService;

public class TarjetaServiceImpl implements TarjetaService {

	@Override
	public Tarjeta crearTarjeta(MarcaTarjetaEnum marca, String numero, String cardholder, Date fechaVencimiento) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tarjeta obtenerTarjeta(Long idTarjeta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isTarjetaValida(Long idTarjeta) {
		// TODO Auto-generated method stub
		return false;
	}

}
