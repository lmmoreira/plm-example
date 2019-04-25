package br.eximia.erm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.eximia.erm.model.Importacao;
import br.eximia.erm.model.TipoImportacao;

public interface ImportacaoDao extends JpaRepository<Importacao, Long> {
	List<Importacao> findByTipo(TipoImportacao tipoImportacao);
	
	@Modifying
    @Query("delete from Importacao i where i.tipo = ?1")
    void deleteByTipo(TipoImportacao tipoImportacao);
	
}