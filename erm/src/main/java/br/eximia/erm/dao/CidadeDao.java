package br.eximia.erm.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.eximia.erm.model.Cidade;

public interface CidadeDao extends JpaRepository<Cidade, Long> {
    Cidade findByCidade(String cidade);
}