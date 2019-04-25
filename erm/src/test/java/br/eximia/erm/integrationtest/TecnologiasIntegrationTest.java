package br.eximia.erm.integrationtest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.eximia.erm.service.interfaces.TecnologiaService;
import br.eximia.springutils.test.AbstractSpringIntegrationTest;

public class TecnologiasIntegrationTest extends AbstractSpringIntegrationTest{

	@Autowired
	TecnologiaService defaultTecnologiaService;
	
	@Test
	public void test(){
		System.out.println(defaultTecnologiaService.findAll());
	}
	
}
