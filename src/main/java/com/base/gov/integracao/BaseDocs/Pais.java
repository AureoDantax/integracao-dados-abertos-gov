package com.base.gov.integracao.BaseDocs;


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
public class Pais {
    @Id
    private String codigo;

    @Column(nullable = false)
    private String descricao;
}
