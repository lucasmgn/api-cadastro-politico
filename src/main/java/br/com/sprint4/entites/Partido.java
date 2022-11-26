package br.com.sprint4.entites;

import br.com.sprint4.enums.Ideologia;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "PARTIDO")
public class Partido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotBlank
    @Column(name = "NOME")
    private String nome;

    @NotBlank
    @Column(name = "SIGLA")
    private String sigla;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "IDEOLOGIA")
    private Ideologia ideologia;

    @NotNull
    @Column(name = "FUNDACAO")
    private LocalDate fundacao;

    @OneToMany(mappedBy = "partido_id")
    private List<Associado> associados;
}
