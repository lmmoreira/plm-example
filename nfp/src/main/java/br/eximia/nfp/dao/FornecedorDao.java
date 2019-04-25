package br.eximia.nfp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.eximia.nfp.model.Fornecedor;

public interface FornecedorDao extends JpaRepository<Fornecedor, Long> {
	Fornecedor findByFornecedor(String fornecedor);
}