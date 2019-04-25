package br.eximia.erm.service.interfaces;

import java.util.List;

import br.eximia.erm.model.Importacao;
import br.eximia.erm.model.ImportacaoDetalhe;
import br.eximia.erm.model.TipoImportacao;
import br.eximia.springutils.data.DataService;

public interface ImportacaoDetalheService extends DataService<ImportacaoDetalhe, Long> {
	List<ImportacaoDetalhe> findByImportacao(Importacao importacao);
	void deleteByImportacao(Importacao importacao);
	void deleteByImportacaoTipo(TipoImportacao tipoImportacao);
}
