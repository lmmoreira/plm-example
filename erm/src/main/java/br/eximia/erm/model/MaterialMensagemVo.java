package br.eximia.erm.model;

import java.util.ArrayList;
import java.util.List;

public class MaterialMensagemVo {
	
	String mensagem = "";
	Boolean criador = false;
	List<MaterialAprovador> para = new ArrayList<MaterialAprovador>();

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public List<MaterialAprovador> getPara() {
		return para;
	}

	public void setPara(List<MaterialAprovador> para) {
		this.para = para;
	}

	public Boolean getCriador() {
		return criador;
	}

	public void setCriador(Boolean criador) {
		this.criador = criador;
	}
	
}
