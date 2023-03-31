package io.github.guisso.gruposestudo;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Luis Guisso &lt;luis.guisso at ifnmg.edu.br&gt;
 */
@Local
public interface PessoaServiceLocal {

    void salvar(Pessoa pessoa);

    List<Pessoa> buscarTodasPessoas();

    List<Object[]> buscarNomesIdades();

    List<PessoaNomeIdadeDto> buscarNomesIdadesDto();

    List<Pessoa> localizarMaiores25();

    List<Pessoa> localizarMaioresQue(Short idadeMinima);

    List<Pessoa> localizarFaixaIdade(Short idadeMinima, Short idadeMaxima);
    
}
