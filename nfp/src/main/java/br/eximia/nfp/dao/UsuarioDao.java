package br.eximia.nfp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.eximia.nfp.model.Usuario;

public interface UsuarioDao extends JpaRepository<Usuario, Long> {
    Usuario findByUsuario(String usuario);
}