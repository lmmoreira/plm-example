package br.eximia.erm.integrationtest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.eximia.erm.service.interfaces.CidadeService;
import br.eximia.springutils.test.AbstractSpringIntegrationTest;

public class CidadesIntegrationTest extends AbstractSpringIntegrationTest{

	@Autowired
	CidadeService defaultCidadeService;
	
	@Test
	public void test(){
		System.out.println(defaultCidadeService.findAll());
	}
	
}
