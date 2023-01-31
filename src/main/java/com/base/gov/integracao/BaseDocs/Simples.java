package com.base.gov.integracao.BaseDocs;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@Entity(name = "simples_nacional")
@SuperBuilder
@NoArgsConstructor
public class Simples extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @OneToOne(cascade = CascadeType.DETACH)
    private Empresa cnpjBase; //8 primeiros digitos

    @Column(name = "simples_nacional", nullable = false)
    private String opcaoSimples;

    @Column(name = "optou_pelo_simples", nullable = false)
    private LocalDate dataOpcaoSimples;

    @Column
    private LocalDate dataExclusaoSimples;

    @Column(name = "optou_pelo_mei", nullable = false)
    private String opcaoMei;

    @Column
    private LocalDate dataOpcaoMei;

    @Column
    private LocalDate dataExclusaoMei;
}



