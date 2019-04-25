package br.eximia.erm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.eximia.erm.dao.UnidadeProjetoDao;
import br.eximia.erm.model.UnidadeProjeto;
import br.eximia.erm.service.interfaces.UnidadeProjetoService;
import br.eximia.springutils.data.AbstractDataService;

@Service("defaultUnidadeProjetoService")
public class DefaultUnidadeProjetoService extends AbstractDataService<UnidadeProjeto, Long> implements UnidadeProjetoService {
	
	UnidadeProjetoDao unidadeProjetoDao;

	@Autowired
	public DefaultUnidadeProjetoService(UnidadeProjetoDao unidadeProjetoDao) {
		super();
		this.unidadeProjetoDao = unidadeProjetoDao;
	}
	
	@Override
	@Transactional(readOnly=true)
	public UnidadeProjeto findByUnidade(String unidade) {
		return unidadeProjetoDao.findByUnidade(unidade);
	}

	@Override
	public JpaRepository<UnidadeProjeto, Long> getDao() {
		return unidadeProjetoDao;
	}
	
}
