package br.eximia.erm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.eximia.erm.dao.ImportacaoDetalheDao;
import br.eximia.erm.model.Importacao;
import br.eximia.erm.model.ImportacaoDetalhe;
import br.eximia.erm.model.TipoImportacao;
import br.eximia.erm.service.interfaces.ImportacaoDetalheService;
import br.eximia.springutils.data.AbstractDataService;

@Service("defaultImportacaoDetalheService")
public class DefaultImportacaoDetalheService extends AbstractDataService<ImportacaoDetalhe, Long> implements ImportacaoDetalheService {
	
	ImportacaoDetalheDao importacaoDetalheDao;

	@Autowired
	public DefaultImportacaoDetalheService(ImportacaoDetalheDao importacaoDetalheDao) {
		super();
		this.importacaoDetalheDao = importacaoDetalheDao;
	}

	@Override
	public JpaRepository<ImportacaoDetalhe, Long> getDao() {
		return importacaoDetalheDao;
	}

	@Override
	@Transactional(readOnly=true)
	public List<ImportacaoDetalhe> findByImportacao(Importacao importacao) {
		return importacaoDetalheDao.findByImportacao(importacao);
	}

	@Override
	@Transactional
	public void deleteByImportacao(Importacao importacao) {
		importacaoDetalheDao.deleteByImportacao(importacao);
	}

	@Override
	@Transactional
	public void deleteByImportacaoTipo(TipoImportacao tipoImportacao) {
		importacaoDetalheDao.deleteByImportacaoTipo(tipoImportacao);
	}
	
}
