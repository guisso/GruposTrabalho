package io.github.guisso.gruposestudo;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Luis Guisso &lt;luis.guisso at ifnmg.edu.br&gt;
 */
@Stateless
public class PessoaService implements PessoaServiceLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void salvar(Pessoa pessoa) {
        em.persist(pessoa);
    }

    @Override
    public List<Pessoa> buscarTodasPessoas() {
        Query q = em.createQuery("SELECT p FROM Pessoa p");

        return (List<Pessoa>) q.getResultList();
    }

}
