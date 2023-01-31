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
public class Motivo {

    @Id
    @Column(nullable = false)
    private String codigo;

    @Column(name = "motivo",nullable = false)
    private String descricao;
}
