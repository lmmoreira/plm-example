package br.eximia.nfp.integrationtest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.eximia.nfp.model.Fornecedor;
import br.eximia.nfp.service.interfaces.FornecedorService;
import br.eximia.nfp.service.interfaces.UsuarioService;
import br.eximia.springutils.test.AbstractSpringIntegrationTest;

public class FornecedoresIntegrationTest extends AbstractSpringIntegrationTest{

	@Autowired
	FornecedorService defaultFornecedorService;
	
	@Autowired
	UsuarioService defaultUsuarioService;
	
	@Test
	public void test(){
		Fornecedor f = defaultFornecedorService.findById(2651L);
		System.out.println(f);
	}
	
}
