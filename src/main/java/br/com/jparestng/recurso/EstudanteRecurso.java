package br.com.jparestng.recurso;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.jparestng.modelo.Estudante;
import br.com.jparestng.repositorio.EstudanteRepositorio;
import br.com.jparestng.servico.EstudanteServico;
//comentário

@RestController
@RequestMapping("/estudantes")
public class EstudanteRecurso {
	@Autowired
	EstudanteRepositorio estudanteRepositorio;
	@Autowired
	private EstudanteServico estudanteServico;	
	
	@PostMapping
	public ResponseEntity<Estudante> criar(@Valid @RequestBody Estudante estudante, HttpServletResponse response) {
		Estudante estudanteSalva = estudanteServico.criar(estudante);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(estudanteSalva.getId()).toUri();
			response.setHeader("Location", uri.toASCIIString());
			
	  //return ResponseEntity.created(uri).body(categoriaSalva);
		return ResponseEntity.status(HttpStatus.CREATED).body(estudanteSalva);
	}

	@GetMapping("/{id}")
	public Estudante buscarPeloCodigo(@PathVariable Long id) {
		return estudanteRepositorio.findOne(id);
	}	
	
	@GetMapping
	public List<Estudante> listar(){
		return estudanteRepositorio.findAll();		
	}
}
