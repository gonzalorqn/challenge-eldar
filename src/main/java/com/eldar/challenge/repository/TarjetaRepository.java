package com.eldar.challenge.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eldar.challenge.models.Tarjeta;

public interface TarjetaRepository extends JpaRepository<Tarjeta, Long> {

	Optional<Tarjeta> findByNumero(String numero);

}
