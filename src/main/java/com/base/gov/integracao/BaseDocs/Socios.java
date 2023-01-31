package com.base.gov.integracao.BaseDocs;


import com.base.gov.integracao.BaseDocs.DadosComplementares.TipoSocio;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
public class Socios extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @OneToOne
    private Empresa cnpjBase;

    @ManyToOne(cascade = CascadeType.DETACH)
    private TipoSocio tipoSocio;

    @Column(nullable = false)
    private String nomeSocio;

    @Column(nullable = false)
    private String cpfCnpjSocio;

    @ManyToOne(cascade = CascadeType.DETACH)
    private Qualificacao qualificacaoSocio;

    @Column(nullable = false)
    private LocalDate inicioSociedade;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "codigo_pais")
    private Pais pais;

    @Column(nullable = false)
    private String cpfRepresentante;

    @Column(nullable = false)
    private String nomeRepresentante;

    @ManyToOne(cascade = CascadeType.DETACH)
    private Qualificacao qualificacaoRepresentante;

    @Column
    private String faixaEtaria;


}
