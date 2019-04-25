package br.eximia.nfp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.eximia.nfp.model.Regra;

public interface RegraDao extends JpaRepository<Regra, Long> {
    Regra findByRegra(String regra);
}