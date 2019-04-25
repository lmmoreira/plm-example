package br.eximia.erm.model;

import java.util.ArrayList;
import java.util.List;

public class FornecedorMensagemVo {
	
	String mensagem = "";
	Boolean criador = false;
	List<FornecedorAprovador> para = new ArrayList<FornecedorAprovador>();

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public List<FornecedorAprovador> getPara() {
		return para;
	}

	public void setPara(List<FornecedorAprovador> para) {
		this.para = para;
	}

	public Boolean getCriador() {
		return criador;
	}

	public void setCriador(Boolean criador) {
		this.criador = criador;
	}
	
}
