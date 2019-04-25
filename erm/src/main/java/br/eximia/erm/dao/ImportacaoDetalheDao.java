package br.eximia.erm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.eximia.erm.model.Importacao;
import br.eximia.erm.model.ImportacaoDetalhe;
import br.eximia.erm.model.TipoImportacao;

public interface ImportacaoDetalheDao extends JpaRepository<ImportacaoDetalhe, Long> {
	List<ImportacaoDetalhe> findByImportacao(Importacao importacao);
	
	@Modifying
    @Query("delete from ImportacaoDetalhe i where i.importacao = ?1")
    void deleteByImportacao(Importacao importacao);
	
	@Modifying
    @Query("delete from ImportacaoDetalhe i where i.importacao.tipo = ?1")
    void deleteByImportacaoTipo(TipoImportacao tipoImportacao);
	
}