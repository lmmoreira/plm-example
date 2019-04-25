package br.eximia.erm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.eximia.erm.model.Log;

public interface LogDao extends JpaRepository<Log, Long> {
    List<Log> findByLocal(String local);
}