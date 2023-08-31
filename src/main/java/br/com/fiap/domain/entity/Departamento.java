package br.com.fiap.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_DP", uniqueConstraints = {
        @UniqueConstraint(name = "UK_NM_DP", columnNames = {"ID_NM"})
})
public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_DP")
    @SequenceGenerator(name = "SQ_DP", sequenceName = "SQ_DP")
    @Column(name = "ID_DP")
    private Long id;

    @Column(name = "ID_NM", nullable = false)
    private String nome;

    public Departamento() {
    }

    public Departamento(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public Departamento setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Departamento setNome(String nome) {
        this.nome = nome;
        return this;
    }

    @Override
    public String toString() {
        return "Departamento{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
