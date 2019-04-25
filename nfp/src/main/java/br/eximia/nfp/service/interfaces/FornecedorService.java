package br.eximia.nfp.service.interfaces;

import br.eximia.nfp.model.Fornecedor;
import br.eximia.springutils.data.DataService;

public interface FornecedorService extends DataService<Fornecedor, Long> {
	Fornecedor findByFornecedor(String fornecedor);
}
