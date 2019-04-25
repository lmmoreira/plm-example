package br.eximia.erm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.eximia.erm.dao.SubAtaDao;
import br.eximia.erm.model.SubAta;
import br.eximia.erm.service.interfaces.SubAtaService;
import br.eximia.springutils.data.AbstractDataService;

@Service("defaultSubAtaService")
public class DefaultSubAtaService extends AbstractDataService<SubAta, Long> implements SubAtaService {
	
	SubAtaDao subAtaDao;

	@Autowired
	public DefaultSubAtaService(SubAtaDao subAtaDao) {
		super();
		this.subAtaDao = subAtaDao;
	}
	
	@Override
	@Transactional(readOnly=true)
	public SubAta findBySubAta(String subAta) {
		return subAtaDao.findBySubAta(subAta);
	}

	@Override
	public JpaRepository<SubAta, Long> getDao() {
		return subAtaDao;
	}
	
}
