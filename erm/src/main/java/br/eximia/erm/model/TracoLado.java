package br.eximia.erm.model;

import br.eximia.springutils.context.SpringApplicationContext;
import br.eximia.springutils.internationalization.Messages;

public enum TracoLado {
    
    Esquerdo("enums.tracoLado.esquerdo"),
    Direito("enums.tracoLado.direito");

    private String value;

    private TracoLado(String value) {
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
