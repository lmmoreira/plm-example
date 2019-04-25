package br.eximia.erm.model;

import br.eximia.springutils.context.SpringApplicationContext;
import br.eximia.springutils.internationalization.Messages;

public enum StatusMaterial {
    
    Pendente("enums.statusMaterial.pendente"),
    Aprovado("enums.statusMaterial.aprovado"),
    Cancelado("enums.statusMaterial.cancelado");

    private String value;

    private StatusMaterial(String value) {
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
