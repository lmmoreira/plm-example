package br.eximia.erm.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.eximia.erm.model.Tecnologia;

public interface TecnologiaDao extends JpaRepository<Tecnologia, Long> {
    Tecnologia findByTecnologia(String tecnologia);
}