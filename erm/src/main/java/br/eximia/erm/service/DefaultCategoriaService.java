package br.eximia.erm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.eximia.erm.dao.CategoriaDao;
import br.eximia.erm.model.Categoria;
import br.eximia.erm.service.interfaces.CategoriaService;
import br.eximia.springutils.data.AbstractDataService;

@Service("defaultCategoriaService")
public class DefaultCategoriaService extends AbstractDataService<Categoria, Long> implements CategoriaService {
	
	CategoriaDao categoriaDao;

	@Autowired
	public DefaultCategoriaService(CategoriaDao categoriaDao) {
		super();
		this.categoriaDao = categoriaDao;
	}
	
	@Override
	@Transactional(readOnly=true)
	public Categoria findByCategoria(String categoria) {
		return categoriaDao.findByCategoria(categoria);
	}

	@Override
	public JpaRepository<Categoria, Long> getDao() {
		return categoriaDao;
	}
	
}
