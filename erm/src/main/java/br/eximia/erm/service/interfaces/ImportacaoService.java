package br.eximia.erm.service.interfaces;

import java.util.List;

import br.eximia.erm.model.Importacao;
import br.eximia.erm.model.TipoImportacao;
import br.eximia.springutils.data.DataService;

public interface ImportacaoService extends DataService<Importacao, Long> {
	List<Importacao> findByTipo(TipoImportacao tipoImportacao);
	void deleteByTipo(TipoImportacao tipoImportacao);
}
