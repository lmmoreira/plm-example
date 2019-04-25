package br.eximia.erm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.eximia.erm.dao.UnidadeLoteDao;
import br.eximia.erm.model.UnidadeLote;
import br.eximia.erm.service.interfaces.UnidadeLoteService;
import br.eximia.springutils.data.AbstractDataService;

@Service("defaultUnidadeLoteService")
public class DefaultUnidadeLoteService extends AbstractDataService<UnidadeLote, Long> implements UnidadeLoteService {
	
	UnidadeLoteDao unidadeLoteDao;

	@Autowired
	public DefaultUnidadeLoteService(UnidadeLoteDao unidadeLoteDao) {
		super();
		this.unidadeLoteDao = unidadeLoteDao;
	}
	
	@Override
	@Transactional(readOnly=true)
	public UnidadeLote findByUnidade(String unidade) {
		return unidadeLoteDao.findByUnidade(unidade);
	}

	@Override
	public JpaRepository<UnidadeLote, Long> getDao() {
		return unidadeLoteDao;
	}
	
}
