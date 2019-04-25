package br.eximia.erm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.eximia.erm.model.Ata;
import br.eximia.erm.model.Material;
import br.eximia.erm.model.Programa;
import br.eximia.erm.model.SubAta;

public interface MaterialDao extends JpaRepository<Material, Long> {
	Material findByNcode(Long ncode);
	
	List<Material> findByProgramaAndAtaOAndSubAtaOAndTracoTraco(Programa programa, Ata ata, SubAta subAta, Long traco);
	
	Material findByEspecificacao(String especificacao);
	@Query("SELECT max(t.ncode) FROM Material t")
	Long getMaxNCode();
	
	@Query(value = "Select * from MATERIAIS where ncode LIKE ?1", nativeQuery = true)
	List<Material> findByNcodeLike(String ncode);
	
	@Query(value = "Select * from MATERIAIS where pn LIKE ?1", nativeQuery = true)
	List<Material> findByPnLike(String pn);
	
	@Query(value = "Select * from MATERIAIS M left join MATERIAIS_APROVADORES MA on M.IDMATERIAL = MA.MATERIAL where MA.USUARIO = ?2 and MA.STATUS = ?1 and M.PUBLICADO = true ", nativeQuery = true)
	List<Material> findByCriadorAndPendingMessages(Long criador);
	
	@Query(value = "Select distinct M.* from MATERIAIS M inner join MATERIAIS_MENSAGENS MS on M.IDMATERIAL = MS.MATERIAL where M.CRIADOR = ?1 and M.PUBLICADO = 0 and MS.DESTINO = ?1 ", nativeQuery = true)
	List<Material> findByDestinoNaoLido(Long destino);
	
	@Query(value = "Select * from MATERIAIS M left join MATERIAIS_APROVADORES_CAMPOS MA on M.IDMATERIAL = MA.MATERIAL where MA.USUARIO = ?2 and MA.STATUS = ?1 and M.PUBLICADO = true ", nativeQuery = true)
	List<Material> findByAprovadoresStatusAndUserCampos(String status, Long aprovador);
	
	@Query(value = "Select * from MATERIAIS M left join MATERIAIS_APROVADORES MA on M.IDMATERIAL = MA.MATERIAL where MA.USUARIO = ?2 and MA.STATUS = ?1 and M.PUBLICADO = true ", nativeQuery = true)
	List<Material> findByAprovadoresStatusAndUser(String status, Long aprovador);
	
	List<Material> findByPnAndNcodeNotLike(String pn, Long ncode);
	@Query(value = " Select * from MATERIAIS M "+ 
					" left join MATERIAIS_APROVADORES MA on M.IDMATERIAL = MA.MATERIAL "+ 
					"	    where M.IDMATERIAL in ( "+
					"								Select T.IDMATERIAL from ( "+
					"									Select "+
					"										sum(case when MA.status='Pendente' then 1 else 0 end) Pendentes, "+
					"										sum(case when MA.status='Aprovado' then 1 else 0 end) Aprovados, "+
					"										sum(case when MA.status='Cancelado' then 1 else 0 end) Cancelados, "+
					"										M.IDMATERIAL "+
					"										from MATERIAIS M "+ 
					"										left join MATERIAIS_APROVADORES MA on M.IDMATERIAL = MA.MATERIAL "+
					"										group by m.idmaterial "+
					"	                                    having (Pendentes = 0 and Cancelados = 0) and (Pendentes + Aprovados + Cancelados) > 0 "+
					"								) T "+
					"							) group by IDMATERIAL  ", nativeQuery = true)
	List<Material> findByMateriaisAprovados();
	@Query(value = " Select * from MATERIAIS M "+ 
			" left join MATERIAIS_APROVADORES MA on M.IDMATERIAL = MA.MATERIAL "+ 
			"	    where M.IDMATERIAL in ( "+
			"								Select T.IDMATERIAL from ( "+
			"									Select "+
			"										sum(case when MA.status='Pendente' then 1 else 0 end) Pendentes, "+
			"										sum(case when MA.status='Aprovado' then 1 else 0 end) Aprovados, "+
			"										sum(case when MA.status='Cancelado' then 1 else 0 end) Cancelados, "+
			"										M.IDMATERIAL "+
			"										from MATERIAIS M "+ 
			"										left join MATERIAIS_APROVADORES MA on M.IDMATERIAL = MA.MATERIAL "+
			"										group by m.idmaterial "+
			"	                                    having (Pendentes > 0 and Cancelados = 0) or (Pendentes + Aprovados + Cancelados) = 0 "+
			"								) T "+
			"							) group by IDMATERIAL ", nativeQuery = true)
	List<Material> findByMateriaisPendentes();
	@Query(value = " Select * from MATERIAIS M "+ 
			" left join MATERIAIS_APROVADORES MA on M.IDMATERIAL = MA.MATERIAL "+ 
			"	    where M.IDMATERIAL in ( "+
			"								Select T.IDMATERIAL from ( "+
			"									Select "+
			"										sum(case when MA.status='Pendente' then 1 else 0 end) Pendentes, "+
			"										sum(case when MA.status='Aprovado' then 1 else 0 end) Aprovados, "+
			"										sum(case when MA.status='Cancelado' then 1 else 0 end) Cancelados, "+
			"										M.IDMATERIAL "+
			"										from MATERIAIS M "+ 
			"										left join MATERIAIS_APROVADORES MA on M.IDMATERIAL = MA.MATERIAL "+
			"										group by m.idmaterial "+
			"	                                    having Cancelados > 0 "+
			"								) T "+
			"							) group by IDMATERIAL  ", nativeQuery = true)
	List<Material> findByMateriaisCancelados();
	
	
	
}