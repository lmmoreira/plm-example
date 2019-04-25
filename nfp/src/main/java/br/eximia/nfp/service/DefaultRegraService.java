package br.eximia.nfp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.eximia.nfp.dao.RegraDao;
import br.eximia.nfp.model.Regra;
import br.eximia.nfp.service.interfaces.RegraService;
import br.eximia.springutils.data.AbstractDataService;

@Service("defaultRegraService")
public class DefaultRegraService extends AbstractDataService<Regra, Long> implements RegraService {
	
	RegraDao regraDao;

	@Autowired
	public DefaultRegraService(RegraDao regraDao) {
		super();
		this.regraDao = regraDao;
	}

	@Override
	@Transactional(readOnly=true)
	public Regra findByRegra(String regra) {
		return regraDao.findByRegra(regra);
	}

	@Override
	public JpaRepository<Regra, Long> getDao() {
		return regraDao;
	}
	
}
