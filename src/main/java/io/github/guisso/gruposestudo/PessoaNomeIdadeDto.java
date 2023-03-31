package io.github.guisso.gruposestudo;

/**
 *
 * @author Luis Guisso &lt;luis.guisso at ifnmg.edu.br&gt;
 */
public class PessoaNomeIdadeDto {

    private String nome;
    private Short idade;

    public PessoaNomeIdadeDto() {
    }

    public PessoaNomeIdadeDto(String nome, Short idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Short getIdade() {
        return idade;
    }

    public void setIdade(Short idade) {
        this.idade = idade;
    }

}
