package br.eximia.erm.service.interfaces;

import java.util.List;

import br.eximia.erm.model.UnidadeFornecedor;
import br.eximia.springutils.data.DataService;

public interface UnidadeFornecedorService extends DataService<UnidadeFornecedor, Long> {
	
	UnidadeFornecedor findByUnidade(String unidade);
	List<UnidadeFornecedor> findByUnidadesArmazenagem();
	
}
