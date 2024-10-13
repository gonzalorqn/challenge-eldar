package com.eldar.challenge.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum MarcaTarjetaEnum {

	VISA("VISA"),

	NARA("Naranja"),

	AMEX("American Express");

	private String value;

	MarcaTarjetaEnum(String value) {
		this.value = value;
	}

	@JsonValue
	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static MarcaTarjetaEnum fromValue(String text) {
		for (MarcaTarjetaEnum b : MarcaTarjetaEnum.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		throw new IllegalArgumentException("Valor inesperado '" + text
				+ "'. Los valores posibles de la marca de la tarjeta son: 'VISA', 'Naranja', 'American Express'.");
	}
}
