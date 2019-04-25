package br.eximia.erm.model.lazy;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

import br.eximia.erm.model.Material;
import br.eximia.erm.model.StatusMaterial;
import br.eximia.erm.service.interfaces.MaterialService;
import br.eximia.springutils.controller.lazy.LazyModel;

public class MaterialLazyModel extends LazyModel<Material, Long> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	MaterialService defaultMaterialService;
	
	private StatusMaterial status;
	
	public MaterialLazyModel(StatusMaterial status) {
		super();
		this.status = status;
	}

	@Override
	public List<Material> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		
		String sql = "";
		String wheres = "";
		
		for(String key : filters.keySet()){
			wheres += " AND UPPER(" + key.replaceAll("--", ".") + ") like upper('%" + filters.get(key) + "%') "; 
		}
		
		if(this.status.equals(StatusMaterial.Pendente)){
			sql = this.getNativePendentes(wheres);
		}else if(this.status.equals(StatusMaterial.Cancelado)){
			sql = this.getNativeCancelados(wheres);
		} else if(this.status.equals(StatusMaterial.Aprovado)){
			sql = this.getNativeAprovado(wheres);
		}
			
		if(!(sortField.equals("sortBy"))){
			String order = sortOrder.equals(SortOrder.ASCENDING) ? " ASC " : " DESC ";
			sql += " order by " + sortField.replaceAll("--", ".") + " " + order;
		} else {
			sql += " order by m.ncode ";
		}
		
		List<Material> results =  getMaterialService().readNative(sql);
		//rowCount
        int dataSize = results.size();
        this.setRowCount(dataSize);
 
        //paginate
        if(dataSize > pageSize) {
            try {
                return getMaterialService().putProcessoEspecialListOnFields(results.subList(first, first + pageSize));
            }
            catch(IndexOutOfBoundsException e) {
                return getMaterialService().putProcessoEspecialListOnFields(results.subList(first, first + (dataSize % pageSize)));
            }
        }
        else {
            return getMaterialService().putProcessoEspecialListOnFields(results);
        }
        
	}
	
	private String getNativeAprovado(String wheres){
		return  " Select * from MATERIAIS M "+ 
	
				" left JOIN CATEGORIAS CAT on CAT.IDCATEGORIA = M.CATEGORIA "+
				" left JOIN TECNOLOGIAS TEC on TEC.IDTECNOLOGIA = CAT.TECNOLOGIA "+
				" left JOIN USUARIOS USER ON USER.IDUSUARIO = M.CRIADOR "+
				" left JOIN FORNECEDORES FORN on FORN.IDFORNECEDOR = M.FORNECEDOR "+
				" left JOIN UNIDADES_FORNECEDOR UF on UF.IDUNIDADEF = M.UN_FORNECEDOR "+
				" left JOIN UNIDADES_LOTE_FORNECEDOR ULF on ULF.IDUNIDADELF = M.UN_L_FORNECEDOR  "+
				" left JOIN UNIDADES_LOTE UL on UL.IDUNIDADEL = M.UN_L  "+
				
				" LEFT JOIN MATERIAIS_PROCESSOS MP ON M.IDMATERIAL = MP.MATERIAL "+
				" LEFT JOIN PROCESSO_ESPECIAL PE ON MP.PROCESSO = PE.IDPROCESSO "+
				" LEFT JOIN MATERIAIS_INFORMACOES MI ON M.IDMATERIAL = MI.MATERIAL "+

				
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
				"								) T ) "+
				" " + wheres + " "+
				"							 group by IDMATERIAL  ";
	}
	
	private String getNativePendentes(String wheres){
		return  " Select * from MATERIAIS M "+ 
	
				" left JOIN CATEGORIAS CAT on CAT.IDCATEGORIA = M.CATEGORIA "+
				" left JOIN TECNOLOGIAS TEC on TEC.IDTECNOLOGIA = CAT.TECNOLOGIA "+
				" left JOIN USUARIOS USER ON USER.IDUSUARIO = M.CRIADOR "+
				" left JOIN FORNECEDORES FORN on FORN.IDFORNECEDOR = M.FORNECEDOR "+
				" left JOIN UNIDADES_FORNECEDOR UF on UF.IDUNIDADEF = M.UN_FORNECEDOR "+
				" left JOIN UNIDADES_LOTE_FORNECEDOR ULF on ULF.IDUNIDADELF = M.UN_L_FORNECEDOR  "+
				" left JOIN UNIDADES_LOTE UL on UL.IDUNIDADEL = M.UN_L  "+

				" LEFT JOIN MATERIAIS_PROCESSOS MP ON M.IDMATERIAL = MP.MATERIAL "+
				" LEFT JOIN PROCESSO_ESPECIAL PE ON MP.PROCESSO = PE.IDPROCESSO "+
				" LEFT JOIN MATERIAIS_INFORMACOES MI ON M.IDMATERIAL = MI.MATERIAL "+
				
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
				"								) T ) "+
				" " + wheres + " "+
				"							 group by IDMATERIAL ";
	}
	
	private String getNativeCancelados(String wheres){
		return  " Select * from MATERIAIS M "+ 
	
				" left JOIN CATEGORIAS CAT on CAT.IDCATEGORIA = M.CATEGORIA "+
				" left JOIN TECNOLOGIAS TEC on TEC.IDTECNOLOGIA = CAT.TECNOLOGIA "+
				" left JOIN USUARIOS USER ON USER.IDUSUARIO = M.CRIADOR "+
				" left JOIN FORNECEDORES FORN on FORN.IDFORNECEDOR = M.FORNECEDOR "+
				" left JOIN UNIDADES_FORNECEDOR UF on UF.IDUNIDADEF = M.UN_FORNECEDOR "+
				" left JOIN UNIDADES_LOTE_FORNECEDOR ULF on ULF.IDUNIDADELF = M.UN_L_FORNECEDOR  "+
				" left JOIN UNIDADES_LOTE UL on UL.IDUNIDADEL = M.UN_L  "+

				" LEFT JOIN MATERIAIS_PROCESSOS MP ON M.IDMATERIAL = MP.MATERIAL "+
				" LEFT JOIN PROCESSO_ESPECIAL PE ON MP.PROCESSO = PE.IDPROCESSO "+
				" LEFT JOIN MATERIAIS_INFORMACOES MI ON M.IDMATERIAL = MI.MATERIAL "+
				
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
				"								) T ) "+
				" " + wheres + " "+
				"							 group by IDMATERIAL  ";
	}
	
	private MaterialService getMaterialService(){
		return ((MaterialService)(this.getBean(MaterialService.class)));
	}

}
