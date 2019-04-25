package br.eximia.erm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.eximia.erm.model.MaterialMensagem;

public interface MaterialMensagemDao extends JpaRepository<MaterialMensagem, Long> {
	@Query(value = "Select * from MATERIAIS_MENSAGENS MS inner join MATERIAIS M on M.IDMATERIAL = MS.MATERIAL where MS.DESTINO = ?1 and MS.LIDO is null and M.PUBLICADO = 0 and M.CRIADOR = ?1 ", nativeQuery = true)
	List<MaterialMensagem> findByDestinoNaoLido(Long destino);
}