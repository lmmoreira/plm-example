package br.eximia.nfp.service.interfaces;

import br.eximia.nfp.model.Usuario;
import br.eximia.springutils.data.DataService;

public interface UsuarioService extends DataService<Usuario, Long> {
	
	Usuario findByUsuario(String usuario);
	Usuario getLoggedUser();
	
}
