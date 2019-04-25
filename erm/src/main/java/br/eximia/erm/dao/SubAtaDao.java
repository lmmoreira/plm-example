package br.eximia.erm.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.eximia.erm.model.SubAta;

public interface SubAtaDao extends JpaRepository<SubAta, Long> {
    SubAta findBySubAta(String subAta);
}