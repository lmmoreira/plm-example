package br.eximia.erm.model;

import br.eximia.springutils.context.SpringApplicationContext;
import br.eximia.springutils.internationalization.Messages;

public enum Estado {
    
    AC("enums.estado.AC"),
    AL("enums.estado.AL"),
    AP("enums.estado.AP"),
    AM("enums.estado.AM"),
    BA("enums.estado.BA"),
    CE("enums.estado.CE"),
    DF("enums.estado.DF"),
    ES("enums.estado.ES"),
    GO("enums.estado.GO"),
    MA("enums.estado.MA"),
    MT("enums.estado.MT"),
    MS("enums.estado.MS"),
    MG("enums.estado.MG"),
    PA("enums.estado.PA"),
    PB("enums.estado.PB"),
    PR("enums.estado.PR"),
    PE("enums.estado.PE"),
    PI("enums.estado.PI"),
    RR("enums.estado.RR"),
    RO("enums.estado.RO"),
    RJ("enums.estado.RJ"),
    RN("enums.estado.RN"),
    RS("enums.estado.RS"),
    SC("enums.estado.SC"),
    SP("enums.estado.SP"),
    SE("enums.estado.SE"),
    TO("enums.estado.TO");

    private String value;

    private Estado(String value) {
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
