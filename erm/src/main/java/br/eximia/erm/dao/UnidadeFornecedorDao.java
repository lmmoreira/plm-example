package br.eximia.erm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.eximia.erm.model.UnidadeFornecedor;

public interface UnidadeFornecedorDao extends JpaRepository<UnidadeFornecedor, Long> {
	UnidadeFornecedor findByUnidade(String unidade);
	
	@Query( "select o from UnidadeFornecedor o where o.unidade not in :itens" )
	List<UnidadeFornecedor> findByUnidadesArmazenagem(@Param("itens") List<String> itens);
}