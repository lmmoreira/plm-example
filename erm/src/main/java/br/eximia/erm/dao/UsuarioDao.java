package br.eximia.erm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.eximia.erm.model.Usuario;

public interface UsuarioDao extends JpaRepository<Usuario, Long> {
    Usuario findByUsuario(String usuario);
    List<Usuario> findByAprovador(Boolean aprovador);
}