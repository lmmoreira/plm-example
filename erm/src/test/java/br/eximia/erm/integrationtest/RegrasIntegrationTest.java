package br.eximia.erm.integrationtest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.eximia.erm.service.interfaces.RegraService;
import br.eximia.jsfutils.converter.IdAsString;
import br.eximia.springutils.test.AbstractSpringIntegrationTest;

public class RegrasIntegrationTest extends AbstractSpringIntegrationTest{

	@Autowired
	RegraService defaultRegraService;
	
	@Test
	public void test(){
		System.out.println(defaultRegraService.findAll());
		System.out.println(defaultRegraService.findById(3L));
		IdAsString asString = (IdAsString) defaultRegraService.findById(3L);
		
		if(asString != null)
			System.out.println(asString.getIdAsString());
	}
	
}
