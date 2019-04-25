package br.eximia.erm.model.validator;

import java.util.Collection;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidProcessoEspecialListValidator implements ConstraintValidator<ValidProcessoEspecialList, Iterable<?>> {

	@Override
	public boolean isValid( Iterable<?> targets, ConstraintValidatorContext ctx) {
		return (!(((Collection<?>)targets).size()>3));
    }

	@Override
	public void initialize(ValidProcessoEspecialList arg0) {
		//
	}
}