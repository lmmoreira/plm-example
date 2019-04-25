package br.eximia.erm.service;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import br.eximia.erm.model.Importacao;
import br.eximia.erm.model.ImportacaoDetalhe;
import br.eximia.erm.model.StatusImportacao;
import br.eximia.erm.service.interfaces.ImportacaoDetalheService;
import br.eximia.erm.service.interfaces.ImportacaoService;
import br.eximia.springutils.context.SpringApplicationContext;
import br.eximia.springutils.data.DataService;
import br.eximia.springutils.data.domain.GenericEntity;
import br.eximia.springutils.internationalization.Messages;

public abstract class AbstractImportacao<E extends GenericEntity<E>, T extends Serializable> {
	
	public Importacao importacao;
	
	@Autowired
	private Messages messages;
	
	@Autowired
	private DataService<E, T> defaultGenericDataService;
	
	@Autowired
	private ImportacaoService defaultImportacaoService;
	
	@Autowired
	private ImportacaoDetalheService defaultImportacaoDetalheService;
	
	protected abstract void parse();
	protected abstract String getFileName();
	
	private String getFileLocationPrefix(){
		return "/resources/importacao/";
	}
	
	public String getFile(){
		return this.getFileLocationPrefix() + this.getFileName();
	}
	
	public Importacao importar(Importacao importacao) {
		this.importacao = importacao;
		importacao.setStatus(StatusImportacao.Execucao);
		importacao.setData(new Date());
		defaultImportacaoDetalheService.deleteByImportacaoTipo(importacao.getTipo());
		defaultImportacaoService.deleteByTipo(importacao.getTipo());
		defaultImportacaoService.save(importacao);
		this.executar();
		return importacao;
	}
	
	private void finalizar(){
		this.importacao.setStatus(StatusImportacao.Finalizada);
		defaultImportacaoService.save(importacao);
	}
	
	@SuppressWarnings("unchecked")
	protected void detalhes() {
		for(Object obj : this.getImportacao().getItems()){
			
			try {
				defaultGenericDataService.save(((E) obj));
				this.getImportacao().getDetalhes().add(this.getNovoDetalhe(obj));
			} catch (Exception e) {
				this.getImportacao().getDetalhes().add(this.getNovoErro(obj, e));
			}
			
		}
	}
	
	public void executar(){
		new Thread() {
			@Override
			public void run() {
				parse();
				detalhes();
				finalizar();
			}
		}.start();

	}
	
	public Importacao getImportacao() {
		return importacao;
	}
	
	protected ImportacaoDetalhe getNovoDetalhe(Object o){
		ImportacaoDetalhe detalhe = new ImportacaoDetalhe();
		detalhe.setData(new Date());
		detalhe.setImportacao(this.getImportacao());
		detalhe.setMensagem(messages.getMessage("importacao.item.sucesso") + o);
		return detalhe;
	}
	
	protected ImportacaoDetalhe getNovoDetalhe(Object o, String message){
		ImportacaoDetalhe detalhe = new ImportacaoDetalhe();
		detalhe.setData(new Date());
		detalhe.setImportacao(this.getImportacao());
		detalhe.setMensagem(messages.getMessage(message) + o);
		return detalhe;
	}
	
	protected ImportacaoDetalhe getNovoErro(Object o, Exception e){
		ImportacaoDetalhe detalhe = new ImportacaoDetalhe();
		detalhe.setData(new Date());
		detalhe.setImportacao(this.getImportacao());
		detalhe.setMensagem(messages.getMessage("importacao.item.erro") + o + " - " + e.getMessage());
		return detalhe;
	}
	
	public Object getBean(Class<?> classType) {
        return SpringApplicationContext.getBean(classType);
    }
	
	public Messages getMessages(){
		return (Messages) this.getBean(Messages.class);
	}
	
}
