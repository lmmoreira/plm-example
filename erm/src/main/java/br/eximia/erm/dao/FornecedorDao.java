package br.eximia.erm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.eximia.erm.model.Fornecedor;

public interface FornecedorDao extends JpaRepository<Fornecedor, Long> {
	Fornecedor findByFornecedor(String fornecedor);
	@Query(value = "Select * from FORNECEDORES F left join FORNECEDORES_APROVADORES FA on F.IDFORNECEDOR = FA.FORNECEDOR where (F.PUBLICADO = true) and (  (FA.USUARIO = ?2 and FA.STATUS = ?1) or (FA.USUARIO = ?2 and F.DATA_REAVALIACAO < sysdate()) )", nativeQuery = true)
	List<Fornecedor> findByAprovadoresStatusAndUser(String status, Long aprovador);
	
	@Query(value = "Select distinct M.* from FORNECEDORES M inner join FORNECEDORES_MENSAGENS MS on M.IDFORNECEDOR = MS.FORNECEDOR where M.CRIADOR = ?1 and M.PUBLICADO = 0 and MS.DESTINO = ?1 ", nativeQuery = true)
	List<Fornecedor> findByDestinoNaoLido(Long destino);
	
	@Query(value = " Select * from FORNECEDORES F "+ 
				   " left join FORNECEDORES_APROVADORES FA on F.IDFORNECEDOR = FA.FORNECEDOR "+ 
				   " where F.IDFORNECEDOR in ( "+ 
					"						Select T.IDFORNECEDOR from ( "+ 
					"							Select "+  
					"								sum(case when FA.status='Pendente' then 1 else 0 end) Pendentes, "+ 
					"								sum(case when FA.status='Aprovado' then 1 else 0 end) Aprovados, "+ 
					"								sum(case when FA.status='Cancelado' then 1 else 0 end) Cancelados, "+ 
					"								F.IDFORNECEDOR "+ 
					"								from FORNECEDORES F "+  
					"								left join FORNECEDORES_APROVADORES FA on F.IDFORNECEDOR = FA.FORNECEDOR "+ 
					"								group by f.idfornecedor "+ 
				    "                               having Pendentes = 0 and Cancelados = 0 and (Pendentes + Aprovados + Cancelados) > 0 "+ 
				    "						) T "+ 
					"					) group by IDFORNECEDOR " , nativeQuery = true)
	List<Fornecedor> findByFornecedoresAprovados();
	@Query(value = " Select * from FORNECEDORES F "+ 
				   " left join FORNECEDORES_APROVADORES FA on F.IDFORNECEDOR = FA.FORNECEDOR "+ 
				   " where F.IDFORNECEDOR in ( "+ 
					"						Select T.IDFORNECEDOR from ( "+ 
					"							Select "+  
					"								sum(case when FA.status='Pendente' then 1 else 0 end) Pendentes, "+ 
					"								sum(case when FA.status='Aprovado' then 1 else 0 end) Aprovados, "+ 
					"								sum(case when FA.status='Cancelado' then 1 else 0 end) Cancelados, "+ 
					"								F.IDFORNECEDOR "+ 
					"								from FORNECEDORES F "+  
					"								left join FORNECEDORES_APROVADORES FA on F.IDFORNECEDOR = FA.FORNECEDOR "+ 
					"								group by f.idfornecedor "+ 
				    "                               having (Pendentes > 0 and Cancelados = 0) or (Pendentes + Aprovados + Cancelados) = 0 "+
				    "						) T "+ 
					"					) group by IDFORNECEDOR " , nativeQuery = true)
	List<Fornecedor> findByFornecedoresPendentes();
	@Query(value = " Select * from FORNECEDORES F "+ 
			   " left join FORNECEDORES_APROVADORES FA on F.IDFORNECEDOR = FA.FORNECEDOR "+ 
			   " where F.IDFORNECEDOR in ( "+ 
				"						Select T.IDFORNECEDOR from ( "+ 
				"							Select "+  
				"								sum(case when FA.status='Pendente' then 1 else 0 end) Pendentes, "+ 
				"								sum(case when FA.status='Aprovado' then 1 else 0 end) Aprovados, "+ 
				"								sum(case when FA.status='Cancelado' then 1 else 0 end) Cancelados, "+ 
				"								F.IDFORNECEDOR "+ 
				"								from FORNECEDORES F "+  
				"								left join FORNECEDORES_APROVADORES FA on F.IDFORNECEDOR = FA.FORNECEDOR "+ 
				"								group by f.idfornecedor "+ 
			    "                               having Cancelados > 0 "+
			    "						) T "+ 
				"					) group by IDFORNECEDOR " , nativeQuery = true)
	List<Fornecedor> findByFornecedoresCancelados();
}