package br.eximia.erm.unittest.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.eximia.erm.dao.CidadeDao;
import br.eximia.erm.model.Cidade;
import br.eximia.erm.service.DefaultCidadeService;

@RunWith(MockitoJUnitRunner.class)
public class DefaultCidadeTest {
	
	@Mock(name="cidadeDao")
	private CidadeDao defaultCidadeDao;
	
	@InjectMocks
	private DefaultCidadeService defaultCidadeService;
	
	@Test
	public void findAllTest(){
		Mockito.when(defaultCidadeDao.findAll()).thenReturn(getCidades());
		List<Cidade> actual = defaultCidadeService.findAll();
		Assert.assertFalse(actual.isEmpty());
	}
	
	@Test
	public void findByCidadeTest(){
		Mockito.when(defaultCidadeDao.findByCidade(Mockito.anyString())).thenReturn(getCidades().get(0));
		Cidade actual = defaultCidadeService.findByCidade(Mockito.anyString());
		Assert.assertFalse(actual == null);
	}
	
	private List<Cidade> getCidades(){
		List<Cidade> cidades = new ArrayList<Cidade>();
		cidades.add(new Cidade());
		return cidades;
	}
	
}
