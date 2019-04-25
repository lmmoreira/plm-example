package br.eximia.erm.service.interfaces;

import java.util.List;

import br.eximia.erm.model.Fornecedor;
import br.eximia.erm.model.StatusFornecedor;
import br.eximia.erm.model.Usuario;
import br.eximia.springutils.data.DataService;

public interface FornecedorService extends DataService<Fornecedor, Long> {
	Fornecedor findByFornecedor(String fornecedor);
	List<Fornecedor> findByAprovadoresStatusAndUser(StatusFornecedor status, Usuario aprovador);
	List<Fornecedor> findByFornecedoresAprovados();
	List<Fornecedor> findByFornecedoresPendentes();
	List<Fornecedor> findByFornecedoresCancelados();
	void checkFornecedorRevalidacao();
	List<Fornecedor> findByDestinoNaoLido(Usuario destino);
}
