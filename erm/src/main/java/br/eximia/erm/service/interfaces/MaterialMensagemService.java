package br.eximia.erm.service.interfaces;

import java.util.List;

import br.eximia.erm.model.MaterialMensagem;
import br.eximia.erm.model.Usuario;
import br.eximia.springutils.data.DataService;

public interface MaterialMensagemService extends DataService<MaterialMensagem, Long> {
	List<MaterialMensagem> findByDestinoNaoLido(Usuario destino);
}
