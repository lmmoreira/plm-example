package br.eximia.erm.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.eximia.erm.model.Traco;
import br.eximia.erm.model.TracoLado;

public interface TracoDao extends JpaRepository<Traco, Long> {
    Traco findByTraco(Integer traco);
    Traco findByTracoAndLado(Integer traco, TracoLado lado);
}