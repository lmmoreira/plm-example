package br.eximia.erm.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Strings;

import br.eximia.erm.model.Importacao;
import br.eximia.erm.model.TipoImportacao;
import br.eximia.erm.service.DefaultImportacaoFactory;
import br.eximia.erm.service.interfaces.ImportacaoService;
import br.eximia.jsfutils.MessageUtils;
import br.eximia.jsfutils.UrlUtils;
import br.eximia.springutils.internationalization.Messages;

@Component
@Scope("session")
@Qualifier("importacoesController")
public class ImportacoesController {

	@Autowired
	DefaultImportacaoFactory defaultImportacaoFactory;
	
	@Autowired
	ImportacaoService defaultImportacaoService;
	
	@Autowired
	Messages messages;
	
	private TipoImportacao tipoImportacao = null;

	public void handleFileUpload(FileUploadEvent event) {
		
		MessageUtils.jsfInfoMessage(messages.getMessage("importacoes.sucesso"));
				
		try {
			defaultImportacaoFactory.getInstance(tipoImportacao).importar(new Importacao(tipoImportacao, event.getFile().getInputstream()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public Importacao getImportacao(){
		if(!Strings.isNullOrEmpty(UrlUtils.getUrlParam("tipoImportacao")))
			tipoImportacao = TipoImportacao.valueOf(UrlUtils.getUrlParam("tipoImportacao"));
		
		List<Importacao> importacaoes = defaultImportacaoService.findByTipo(tipoImportacao);
		return importacaoes.isEmpty() ? this.getNovaImportacao() : defaultImportacaoService.findByTipo(tipoImportacao).get(0);
	}
	
	public StreamedContent getFile() {
		InputStream stream = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream(defaultImportacaoFactory.getInstance(tipoImportacao).getFile());
		return new DefaultStreamedContent(stream, "application/vnd.ms-excel", tipoImportacao.name() + ".xls");
	}
	
	private Importacao getNovaImportacao(){
		Importacao i = new Importacao();
		i.setTipo(tipoImportacao);
		return i;
	}

}
