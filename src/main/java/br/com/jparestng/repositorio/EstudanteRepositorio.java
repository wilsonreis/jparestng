package br.com.jparestng.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jparestng.modelo.Estudante;

public interface EstudanteRepositorio extends JpaRepository<Estudante, Long> {

}
