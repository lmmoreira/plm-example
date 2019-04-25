package br.eximia.erm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.eximia.erm.dao.TecnologiaDao;
import br.eximia.erm.model.Tecnologia;
import br.eximia.erm.service.interfaces.TecnologiaService;
import br.eximia.springutils.data.AbstractDataService;

@Service("defaultTecnologiaService")
public class DefaultTecnologiaService extends AbstractDataService<Tecnologia, Long> implements TecnologiaService {
	
	TecnologiaDao tecnologiaDao;

	@Autowired
	public DefaultTecnologiaService(TecnologiaDao tecnologiaDao) {
		super();
		this.tecnologiaDao = tecnologiaDao;
	}
	
	@Override
	@Transactional(readOnly=true)
	public Tecnologia findByTecnologia(String tecnologia) {
		return tecnologiaDao.findByTecnologia(tecnologia);
	}

	@Override
	public JpaRepository<Tecnologia, Long> getDao() {
		return tecnologiaDao;
	}
	
}
