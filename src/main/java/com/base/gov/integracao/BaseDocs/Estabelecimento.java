package com.base.gov.integracao.BaseDocs;



import com.base.gov.integracao.BaseDocs.DadosComplementares.MatrizFilial;
import com.base.gov.integracao.BaseDocs.DadosComplementares.SituacaoCadastral;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@Entity(name = "estabelecimentos")
@SuperBuilder
@NoArgsConstructor
public class Estabelecimento extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "cnpj_base")
    private Empresa cnpjBase; //8 primeiros digitos

    @Column(nullable = false)
    private String cnpjOrdem; //do 9 ao 12 digito

    @Column(nullable = false) // digito verificador
    private String cnpjDv;

    @ManyToOne(cascade=CascadeType.DETACH)
    private MatrizFilial matrizFilial; //1 matriz 2 filial

    @Column
    private String nomeFantasia;

    @ManyToOne(cascade = CascadeType.DETACH)
    private SituacaoCadastral situacCadastral;

    @Column(nullable = false)
    private LocalDate dataSituacCadastral;

    @ManyToOne(cascade=CascadeType.DETACH)
    private Motivo motivoSituacCadastral;

    @Column
    private String nomeCidadeExt; // Nome da cidade no exterior

    @ManyToOne(cascade=CascadeType.MERGE,fetch = FetchType.LAZY)
    @JoinColumn(name="codigo_pais")
    private Pais codigoPais;

    @Column(nullable = false)
    private LocalDate dataInicioAtividade;

    @ManyToOne(cascade=CascadeType.MERGE,fetch = FetchType.EAGER)
    private Cnaes cnaePrincipal;

    @ManyToOne(cascade=CascadeType.DETACH)
    private Cnaes cnaeSecundario;

    @Column(nullable = false)
    private String tipoLogradouro;

    @Column(nullable = false)
    private String logradouro;

    @Column
    private String numero;

    @Column
    private String complemento;

    @Column(nullable = false)
    private String bairro;

    @Column(nullable = false)
    private String cep;

    @Column(nullable = false)
    private String uf;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Municipio municipio;

    @Column
    private String dddPrinc;

    @Column
    private String telefPrinc;

    @Column
    private String dddSec;

    @Column
    private String telefSec;

    @Column
    private String email; //correio eletronico

    @Column
    private String situacEspecial;

    @Column
    private String dataSituacEspecial;


}

