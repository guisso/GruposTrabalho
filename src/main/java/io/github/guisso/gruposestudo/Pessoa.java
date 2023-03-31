package io.github.guisso.gruposestudo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Luis Guisso &lt;luis.guisso at ifnmg.edu.br&gt;
 */
@Entity
//<editor-fold defaultstate="collapsed" desc="Queries">
@NamedQueries({
    @NamedQuery(
            name = "Pessoa.findAll",
            query = "SELECT p FROM Pessoa p"
    ),
    @NamedQuery(
            name = "Pessoa.nameAndAge",
            query = "SELECT p.nome, p.idade FROM Pessoa p"
    ),
        // TODO Resolver falha na JPQL
//    @NamedQuery(
//            name = "Pessoa.nameAndAgeDto",
//            query = "SELECT new io.github.guisso.gruposestudo.PessoaNomeIdadeDto(p.nome, p.idade) FROM Pessoa p"
//    )
    @NamedQuery(
            name = "Pessoa.maiores25",
            query = "SELECT p "
                    + "FROM Pessoa p "
                    + "WHERE p.idade > 25"
    ),
    @NamedQuery(
            name = "Pessoa.maiores",
            query = "SELECT p "
                    + "FROM Pessoa p "
                    + "WHERE p.idade > :idadeMinima"
    ),
    @NamedQuery(
            name = "Pessoa.idadeEntre",
            query = "SELECT p "
                    + "FROM Pessoa p "
                    + "WHERE p.idade > :idadeMinima "
                    + "AND p.idade < :idadeMaxima"
    ),
})
//</editor-fold>
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 65, nullable = false)
    private String nome;

    @Column(nullable = false)
    private LocalDate nascimento;

//    @Transient
    private Short idade;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Endereco endereco;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pessoa_id")
    private List<Telefone> telefones;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "pessoa_id")
    private List<Cadastro> cadastros;

    public Pessoa() {
        telefones = new ArrayList<>();
        cadastros = new ArrayList<>();
    }

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
        idade = (short) nascimento.until(LocalDate.now(), 
                ChronoUnit.YEARS);
    }

    public Short getIdade() {
        return idade;
    }

    public void setIdade(Short idade) {
        this.idade = idade;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public List<Cadastro> getCadastros() {
        return cadastros;
    }

    public void setCadastros(List<Cadastro> cadastros) {
        this.cadastros = cadastros;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="hashCode/equals/toString">
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        return hashCode() == obj.hashCode();
    }

    @Override
    public String toString() {
        return "Pessoa{"
                + "id=" + id
                + ", nome=" + nome
                + ", nascimento=" + nascimento
                + ", idade=" + idade
                + ", endereco=" + endereco
                + ", telefones=" + telefones
                + ", cadastros=" + cadastros
                + '}';
    }
    //</editor-fold>

}
