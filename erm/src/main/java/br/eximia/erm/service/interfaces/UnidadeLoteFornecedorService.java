package br.eximia.erm.service.interfaces;

import br.eximia.erm.model.UnidadeLoteFornecedor;
import br.eximia.springutils.data.DataService;

public interface UnidadeLoteFornecedorService extends DataService<UnidadeLoteFornecedor, Long> {
	
	UnidadeLoteFornecedor findByUnidade(String unidade);
	
}
