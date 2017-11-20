package br.com.jparestng.servico;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.jparestng.modelo.Endereco;
import br.com.jparestng.modelo.Estudante;
import br.com.jparestng.repositorio.EstudanteRepositorio;

@Service
public class EstudanteServico {
	
    @PersistenceUnit
    private EntityManagerFactory emf;
    
	@Autowired
	EstudanteRepositorio estudanteRepositorio;    
    
    public Estudante criar(Estudante estudante) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Endereco endereco = estudante.getEndereco();
		estudante.setEndereco(null);
		em.persist(estudante);
		if (null != endereco) {
			endereco.setId(estudante.getId());
			em.persist(endereco);
		}
		em.getTransaction().commit();
		em.close();
		return buscarPessoaPeloId(estudante.getId());    	
    }
	public Estudante buscarPessoaPeloId(Long id) {
		Estudante estudanteSalvo = estudanteRepositorio.findOne(id);
		if (estudanteSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return estudanteSalvo;
	}	
}
