package com.base.gov.integracao.BaseDocs;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Entity(name = "municipios")
@SuperBuilder
@NoArgsConstructor
public class Municipio {

    @Id
    @Column(nullable = false)
    private String codigo;

    @Column(name = "municipio", nullable = false)
    private String descricao;

}
