package io.github.guisso.gruposestudo;

import javax.ejb.Local;

/**
 *
 * @author Luis Guisso &lt;luis.guisso at ifnmg.edu.br&gt;
 */
@Local
public interface PessoaServiceLocal {

    void salvar(Pessoa pessoa);
    
}
