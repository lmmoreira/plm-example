package br.eximia.erm.model;

import br.eximia.springutils.context.SpringApplicationContext;
import br.eximia.springutils.internationalization.Messages;

public enum StatusFornecedor {
    
    Pendente("enums.statusFornecedor.pendente"),
    Cancelado("enums.statusFornecedor.cancelado"),
    Aprovado("enums.statusFornecedor.aprovado");

    private String value;

    private StatusFornecedor(String value) {
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
