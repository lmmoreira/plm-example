package br.eximia.erm.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.eximia.erm.model.Programa;

public interface ProgramaDao extends JpaRepository<Programa, Long> {
    Programa findByPrograma(String programa);
}