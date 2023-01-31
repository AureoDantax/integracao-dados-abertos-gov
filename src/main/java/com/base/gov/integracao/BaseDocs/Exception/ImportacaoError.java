package com.base.gov.integracao.BaseDocs.Exception;

import com.base.gov.integracao.BaseDocs.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Entity
@SuperBuilder
@Data
@NoArgsConstructor

public class ImportacaoError extends BaseEntity {

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
    private String row;

    public ImportacaoError(String entityName, boolean b, String errorMessage, String row) {
        this.entityName = entityName;
        this.treated = treated;
        this.errorMessage = errorMessage;
        this.row = String.valueOf(row);
    }
}
