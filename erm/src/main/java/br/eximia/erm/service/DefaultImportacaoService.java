package br.eximia.erm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.eximia.erm.dao.ImportacaoDao;
import br.eximia.erm.model.Importacao;
import br.eximia.erm.model.TipoImportacao;
import br.eximia.erm.service.interfaces.ImportacaoService;
import br.eximia.springutils.data.AbstractDataService;

@Service("defaultImportacaoService")
public class DefaultImportacaoService extends AbstractDataService<Importacao, Long> implements ImportacaoService {
	
	ImportacaoDao importacaoDao;

	@Autowired
	public DefaultImportacaoService(ImportacaoDao importacaoDao) {
		super();
		this.importacaoDao = importacaoDao;
	}

	@Override
	public JpaRepository<Importacao, Long> getDao() {
		return importacaoDao;
	}

	@Override
	@Transactional(readOnly=true)
	public List<Importacao> findByTipo(TipoImportacao tipoImportacao) {
		return importacaoDao.findByTipo(tipoImportacao);
	}

	@Override
	@Transactional
	public void deleteByTipo(TipoImportacao tipoImportacao) {
		importacaoDao.deleteByTipo(tipoImportacao);
	}
	
}
