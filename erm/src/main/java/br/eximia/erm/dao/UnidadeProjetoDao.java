package br.eximia.erm.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.eximia.erm.model.UnidadeProjeto;

public interface UnidadeProjetoDao extends JpaRepository<UnidadeProjeto, Long> {
	UnidadeProjeto findByUnidade(String unidade);
}