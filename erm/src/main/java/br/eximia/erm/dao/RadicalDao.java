package br.eximia.erm.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.eximia.erm.model.Ata;
import br.eximia.erm.model.Programa;
import br.eximia.erm.model.Radical;
import br.eximia.erm.model.SubAta;

public interface RadicalDao extends JpaRepository<Radical, Long> {
	Radical findByRadical(Long radical);
	Radical findByProgramaAndAtaAndSubAta(Programa programa, Ata ata, SubAta subAta);
}