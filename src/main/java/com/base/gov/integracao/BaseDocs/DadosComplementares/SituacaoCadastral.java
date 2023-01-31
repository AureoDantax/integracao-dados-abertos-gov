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
public class SituacaoCadastral extends BaseEntity {

    @Id
    private String codigo;

    @Column(nullable = false)
    private String descricao;
}
