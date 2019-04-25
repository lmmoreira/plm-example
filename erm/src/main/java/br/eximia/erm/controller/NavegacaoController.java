package br.eximia.erm.controller;

import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.eximia.jsfutils.SessionUtils;

@Component
@Scope("session")
@Qualifier("navegacaoController")
public class NavegacaoController {
	
	public void checkStatusSession(ComponentSystemEvent event) {
		Map<String, String> params =FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String status = params.get("status");
		SessionUtils.setOnSession("listStatus", status);
	}
	
}
