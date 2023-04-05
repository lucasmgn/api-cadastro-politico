package br.com.sprint4.entity;

import br.com.sprint4.enums.Office;
import br.com.sprint4.enums.Sex;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "ASSOCIATE")
public class Associate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotBlank
    @Column(name = "NAME")
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "OFFICE")
    private Office office;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "SEX")
    private Sex sex;

    @NotNull
    @Column(name = "BIRTH")
    private LocalDate birth;

    @ManyToOne
    private Party party;
}
