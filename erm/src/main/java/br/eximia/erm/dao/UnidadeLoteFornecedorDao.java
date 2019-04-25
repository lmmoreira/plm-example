package br.eximia.erm.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.eximia.erm.model.UnidadeLoteFornecedor;

public interface UnidadeLoteFornecedorDao extends JpaRepository<UnidadeLoteFornecedor, Long> {
	UnidadeLoteFornecedor findByUnidade(String unidade);
}