package br.eximia.erm.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.eximia.erm.model.Regra;

public interface RegraDao extends JpaRepository<Regra, Long> {
    Regra findByRegra(String regra);
}