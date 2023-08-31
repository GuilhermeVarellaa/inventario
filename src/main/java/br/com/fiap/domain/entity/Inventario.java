package br.com.fiap.domain.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table(name = "TB_INV")
public class Inventario  {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_INV")
    @SequenceGenerator(name = "SQ_INV", sequenceName = "SQ_INV")
    @Column(name = "ID_INV")
    private Long id;
    @Column(name = "INI_INV", nullable = false)
     private LocalDate inicio;

    @Column(name = "FIM_INV")
    private LocalDate fim;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_DP",
            referencedColumnName = "ID_DP",
            foreignKey = @ForeignKey(name = "FK_DP_INV")
    )
    private Departamento departamento;

    @Column(name = "RLT_INV")
    private String relatorio;

    public Inventario() {
    }

    public Inventario(Long id, Departamento departamento, LocalDate inicio, LocalDate fim, String relatorio) {
        this.id = id;
        this.departamento = departamento;
        this.inicio = inicio;
        this.fim = fim;
        this.relatorio = relatorio;
    }

    public Long getId() {
        return id;
    }

    public Inventario setId(Long id) {
        this.id = id;
        return this;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public Inventario setDepartamento(Departamento departamento) {
        this.departamento = departamento;
        return this;
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public Inventario setInicio(LocalDate inicio) {
        this.inicio = inicio;
        return this;
    }

    public LocalDate getFim() {
        return fim;
    }

    public Inventario setFim(LocalDate fim) {
        this.fim = fim;
        return this;
    }

    public String getRelatorio() {
        return relatorio;
    }

    public Inventario setRelatorio(String relatorio) {
        this.relatorio = relatorio;
        return this;
    }

    @Override
    public String toString() {
        return "Inventario{" +
                "id=" + id +
                ", departamento=" + departamento +
                ", inicio=" + inicio +
                ", fim=" + fim +
                ", relatorio='" + relatorio + '\'' +
                '}';
    }
}
