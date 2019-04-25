package br.eximia.erm.model;

import br.eximia.springutils.context.SpringApplicationContext;
import br.eximia.springutils.internationalization.Messages;

public enum TipoImportacao {
    
	Tecnologias("enums.tipoImportacao.tecnologias"),
	Categorias("enums.tipoImportacao.categorias"),
    Processos("enums.tipoImportacao.processos"),
    UnidadesFornecedor("enums.tipoImportacao.unidadesFornecedor"),
    UnidadesLoteFornecedor("enums.tipoImportacao.unidadesLoteFornecedor"),
    UnidadesLote("enums.tipoImportacao.unidadesLote"),
	Usuarios("enums.tipoImportacao.usuarios"),
	Fornecedores("enums.tipoImportacao.fornecedores"),
	Materiais("enums.tipoImportacao.materiais");

    private String value;

    private TipoImportacao(String value) {
        this.value = value;
    }
    
    private Messages getMessages(){
		return (Messages) SpringApplicationContext.getBean(Messages.class);
	}

    public String getValue() {
        return this.getMessages().getMessage(value);
    }

    public void setValue(String value) {
        this.value = value;
    }

	@Override
    public String toString() {
        return value;
    }

}
