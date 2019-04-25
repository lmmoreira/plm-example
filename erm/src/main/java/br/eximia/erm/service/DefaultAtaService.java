package br.eximia.erm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.eximia.erm.dao.AtaDao;
import br.eximia.erm.model.Ata;
import br.eximia.erm.service.interfaces.AtaService;
import br.eximia.springutils.data.AbstractDataService;

@Service("defaultAtaService")
public class DefaultAtaService extends AbstractDataService<Ata, Long> implements AtaService {
	
	AtaDao ataDao;

	@Autowired
	public DefaultAtaService(AtaDao ataDao) {
		super();
		this.ataDao = ataDao;
	}
	
	@Override
	@Transactional(readOnly=true)
	public Ata findByAta(String ata) {
		return ataDao.findByAta(ata);
	}

	@Override
	public JpaRepository<Ata, Long> getDao() {
		return ataDao;
	}
	
}
