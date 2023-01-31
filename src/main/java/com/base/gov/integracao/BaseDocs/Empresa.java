package com.base.gov.integracao.BaseDocs;


import com.base.gov.integracao.BaseDocs.DadosComplementares.PorteEmpresa;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Entity(name = "empresas")
@SuperBuilder
@NoArgsConstructor
public class Empresa extends BaseEntity {

    @Id
    @Column(name = "cnpj_base", nullable = false) //8 primeiros digitos
    private String cnpj;

    @Column(name = "razao_social", nullable = false)
    private String razaoSocial;

    @ManyToOne(cascade = CascadeType.DETACH)
    private Natureza naturezaJuridica;

    @ManyToOne(cascade = CascadeType.DETACH)
    private Qualificacao qualificacao;

    @Column(name = "capital_social", nullable = false)
    private String capital;

    @ManyToOne(cascade = CascadeType.DETACH)
    private PorteEmpresa porte;

    /*O ENTE FEDERATIVO RESPONSÁVEL É PREENCHIDO PARA OS CASOS
    DE ÓRGÃOS E ENTIDADES DO GRUPO DE NATUREZA JURÍDICA 1XXX.
    PARA AS DEMAIS NATUREZAS, ESTE ATRIBUTO FICA EM BRANCO.*/
    @Column(name = "ente_federativo")
    private String enteFederativo;
}



