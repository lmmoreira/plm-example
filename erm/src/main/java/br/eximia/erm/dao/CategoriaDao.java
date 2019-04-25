package br.eximia.erm.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.eximia.erm.model.Categoria;

public interface CategoriaDao extends JpaRepository<Categoria, Long> {
	Categoria findByCategoria(String categoria);
}