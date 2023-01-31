package com.base.gov.integracao.BaseDocs.DadosComplementares;


import com.base.gov.integracao.BaseDocs.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
public class TipoSocio extends BaseEntity {
    @Id
    private String id;

    @Column
    private String descricao;
}
