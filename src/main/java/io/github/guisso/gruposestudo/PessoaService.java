package io.github.guisso.gruposestudo;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
//        Query q = em.createQuery("SELECT p FROM Pessoa p");
//        return (List<Pessoa>) q.getResultList();

//        TypedQuery q = em.createQuery("SELECT p FROM Pessoa p", Pessoa.class);
//        return q.getResultList();
        return em.createNamedQuery("Pessoa.findAll", Pessoa.class)
                .getResultList();
    }

    @Override
    public List<Object[]> buscarNomesIdades() {
        return em.createNamedQuery("Pessoa.nameAndAge", Object[].class)
                .getResultList();
    }

    @Override
    public List<PessoaNomeIdadeDto> buscarNomesIdadesDto() {
        return null;
        // TODO Resolver falha na JPQL
//        return em.createNamedQuery("Pessoa.nameAndAgeDto",
//                PessoaNomeIdadeDto.class);
    }

    @Override
    public List<Pessoa> localizarMaiores25() {
        return em.createNamedQuery("Pessoa.maiores25", Pessoa.class)
                .getResultList();
    }

    @Override
    public List<Pessoa> localizarMaioresQue(Short idadeMinima) {
        TypedQuery q = em.createNamedQuery("Pessoa.maiores", Pessoa.class);
        q.setParameter("idadeMinima", idadeMinima);
        return q.getResultList();
    }

    @Override
    public List<Pessoa> localizarFaixaIdade(Short idadeMinima, Short idadeMaxima) {
        TypedQuery q = em.createNamedQuery("Pessoa.idadeEntre", Pessoa.class);
        q.setParameter("idadeMinima", idadeMinima);
        q.setParameter("idadeMaxima", idadeMaxima);
        return q.getResultList();
    }

}
