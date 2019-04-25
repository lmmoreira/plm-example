package br.eximia.erm.service.interfaces;

import java.util.List;

import br.eximia.erm.model.Ata;
import br.eximia.erm.model.Material;
import br.eximia.erm.model.Programa;
import br.eximia.erm.model.StatusMaterial;
import br.eximia.erm.model.SubAta;
import br.eximia.erm.model.Usuario;
import br.eximia.springutils.data.DataService;

public interface MaterialService extends DataService<Material, Long> {
	Material findByNcode(Long ncode);
	List<Material> findByProgramaAndAtaOAndSubAtaOAndTracoTraco(Programa programa, Ata ata, SubAta subAta, Long traco);
	Material findByEspecificacao(String especificacao);
	Long getMaxNCode();
	List<Material> findByPnLike(String pn);
	List<Material> findByDestinoNaoLido(Usuario destino);
	List<Material> findByAprovadoresStatusAndUser(StatusMaterial status, Usuario aprovador);
	List<Material> findByAprovadoresStatusAndUserCampos(StatusMaterial status, Usuario aprovador);
	List<Material> findByPnAndNcodeNotLike(String pn, Long ncode);
	List<Material> findByMateriaisAprovados();
	List<Material> findByMateriaisPendentes();
	List<Material> findByMateriaisCancelados();
	List<Material> findByNcodeLike(String ncode);
	List<Material> readNativeProcesses(String sql);
	List<Material> putProcessoEspecialListOnFields(List<Material> results);
}
