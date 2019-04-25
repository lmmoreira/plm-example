package br.eximia.erm.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.eximia.erm.model.Ata;

public interface AtaDao extends JpaRepository<Ata, Long> {
    Ata findByAta(String ata);
}