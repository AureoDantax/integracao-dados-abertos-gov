package com.base.gov.integracao.BaseDocs.Exception;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Entity
@SuperBuilder
@Data
@NoArgsConstructor

public class ImportacaoError {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;


    @Column(nullable = false)
    private String entityName;

    @Column(nullable = false)
    private boolean treated;

    @Column(nullable = false)
    private String errorMessage;

    @Lob
    @Column(nullable = false)
    private String payload;

    public ImportacaoError(String entityName, boolean b, String errorMessage, String payload) {
        this.entityName = entityName;
        this.treated = treated;
        this.errorMessage = errorMessage;
        this.payload = String.valueOf(payload);
    }
}
