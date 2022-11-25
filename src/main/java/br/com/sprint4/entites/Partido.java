package br.com.sprint4.entites;

import br.com.sprint4.enums.Ideologia;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

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

//    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "IDEOLOGIA")
    private Ideologia ideologia;

//    O campo deve ser salvo no banco seguindo o formato da ISO 8601.
//    Mas na hora de serializar e enviar no response a data têm que estar no
//    formato brasileiro de datas
    @NotNull
    @Column(name = "FUNDACAO")
    private OffsetDateTime fundacao;

//    @OneToMany(mappedBy = "PARTIDO")
//    private List<Associado> associados;
}
