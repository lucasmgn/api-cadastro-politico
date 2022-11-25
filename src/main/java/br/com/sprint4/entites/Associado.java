package br.com.sprint4.entites;

import br.com.sprint4.enums.Cargo;
import br.com.sprint4.enums.Sexo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "ASSOCIADO")
public class Associado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotBlank
    @Column(name = "NOME")
    private String nome;

//    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "CARGO")
    private Cargo cargo;

//    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "SEXO")
    private Sexo sexo;

//    @NotNull
    @Column(name = "NASCIMENTO")
    private OffsetDateTime nascimento;

//    @OneToOne //estava gerando erro no flyway
//    private Partido partido;
}
