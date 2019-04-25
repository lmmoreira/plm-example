package br.eximia.erm.service.interfaces;

import br.eximia.erm.model.Ata;
import br.eximia.erm.model.Material;
import br.eximia.erm.model.Programa;
import br.eximia.erm.model.Radical;
import br.eximia.erm.model.SubAta;
import br.eximia.erm.model.Traco;
import br.eximia.springutils.data.DataService;

public interface RadicalService extends DataService<Radical, Long> {
	
	Radical findByRadical(Long radical);
	Radical findByProgramaAndAtaAndSubAta(Programa programa, Ata ata, SubAta subAta);
	Long getNextRadical(Programa programa, Ata ata, SubAta subAta, Traco traco, Boolean especular, Material pnEspecular);
	void plusOneToRadical(Programa programa, Ata ata, SubAta subAta);
	
}
