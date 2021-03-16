package com.sistemalima.dsescola.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistemalima.dsescola.entities.Formulario;

@Repository
public interface FormularioRepository extends JpaRepository<Formulario, Long>{

}
