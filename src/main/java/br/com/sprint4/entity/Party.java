package br.com.sprint4.entity;

import br.com.sprint4.enums.Ideology;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "PARTY")
public class Party {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotBlank
    @Column(name = "NAME")
    private String name;

    @NotBlank
    @Column(name = "ACRONYM")
    private String acronym;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "IDEOLOGY")
    private Ideology ideology;

    @NotNull
    @Column(name = "FOUNDATION")
    private LocalDate foundation;

    @OneToMany(mappedBy = "party")
    private List<Associate> associates;
}
