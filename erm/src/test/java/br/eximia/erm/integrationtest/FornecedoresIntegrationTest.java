package br.eximia.erm.integrationtest;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.eximia.erm.model.Fornecedor;
import br.eximia.erm.model.StatusFornecedor;
import br.eximia.erm.model.Usuario;
import br.eximia.erm.service.interfaces.FornecedorService;
import br.eximia.erm.service.interfaces.MaterialService;
import br.eximia.erm.service.interfaces.UnidadeFornecedorService;
import br.eximia.erm.service.interfaces.UsuarioService;
import br.eximia.springutils.test.AbstractSpringIntegrationTest;

public class FornecedoresIntegrationTest extends AbstractSpringIntegrationTest{

	@Autowired
	FornecedorService defaultFornecedorService;
	
	@Autowired
	MaterialService defaultMateriaisService;
	
	@Autowired
	UsuarioService defaultUsuarioService;
	
	@Autowired
	UnidadeFornecedorService defaultUnidadeFornecedor;
	
	@Test
	public void test(){
		Usuario u = defaultUsuarioService.findById(999999999L);
		List<Fornecedor> f1 = defaultFornecedorService.findByAprovadoresStatusAndUser(StatusFornecedor.Pendente, u);
		System.out.println(f1);
	}
	
}
