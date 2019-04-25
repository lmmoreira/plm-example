package br.eximia.erm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.eximia.erm.model.ProcessoEspecial;

public interface ProcessoEspecialDao extends JpaRepository<ProcessoEspecial, Long> {
	ProcessoEspecial findByProcesso(String processo);
	@Query("SELECT max(t.ncode) FROM ProcessoEspecial t")
	Long getMaxNCode();
}