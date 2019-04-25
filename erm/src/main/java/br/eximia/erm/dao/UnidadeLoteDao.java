package br.eximia.erm.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.eximia.erm.model.UnidadeLote;

public interface UnidadeLoteDao extends JpaRepository<UnidadeLote, Long> {
	UnidadeLote findByUnidade(String unidade);
}