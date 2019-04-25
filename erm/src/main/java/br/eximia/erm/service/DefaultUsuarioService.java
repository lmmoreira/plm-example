package br.eximia.erm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.eximia.erm.dao.UsuarioDao;
import br.eximia.erm.model.Usuario;
import br.eximia.erm.service.interfaces.UsuarioService;
import br.eximia.securityutils.SecurityUtils;
import br.eximia.springutils.data.AbstractDataService;

@Service("defaultUsuarioService")
public class DefaultUsuarioService extends AbstractDataService<Usuario, Long> implements UsuarioService {
	
	UsuarioDao usuarioDao;

	@Autowired
	public DefaultUsuarioService(UsuarioDao usuarioDao) {
		super();
		this.usuarioDao = usuarioDao;
	}

	@Override
	@Transactional(readOnly=true)
	public Usuario findByUsuario(String usuario) {
		return usuarioDao.findByUsuario(usuario);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Usuario getLoggedUser() {
		return this.findByUsuario(SecurityUtils.userName());
	}

	@Override
	public JpaRepository<Usuario, Long> getDao() {
		return usuarioDao;
	}

	@Override
	public List<Usuario> findByAprovador(Boolean aprovador) {
		return usuarioDao.findByAprovador(aprovador);
	}
	
}
