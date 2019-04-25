package br.eximia.erm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.eximia.erm.dao.TracoDao;
import br.eximia.erm.model.Traco;
import br.eximia.erm.model.TracoLado;
import br.eximia.erm.service.interfaces.TracoService;
import br.eximia.springutils.data.AbstractDataService;

@Service("defaultTracoService")
public class DefaultTracoService extends AbstractDataService<Traco, Long> implements TracoService {
	
	TracoDao tracoDao;

	@Autowired
	public DefaultTracoService(TracoDao tracoDao) {
		super();
		this.tracoDao = tracoDao;
	}
	
	@Override
	@Transactional(readOnly=true)
	public Traco findByTraco(Integer traco) {
		return tracoDao.findByTraco(traco);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Traco findByTracoAndLado(Integer traco, TracoLado lado) {
		return tracoDao.findByTracoAndLado(traco, lado);
	}

	@Override
	public JpaRepository<Traco, Long> getDao() {
		return tracoDao;
	}
	
}
