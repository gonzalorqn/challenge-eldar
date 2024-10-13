package com.eldar.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eldar.challenge.models.Operacion;

public interface OperacionRepository extends JpaRepository<Operacion, Long> {

}
