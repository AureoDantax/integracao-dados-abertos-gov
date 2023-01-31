package com.base.gov.integracao.services.PersistError;

import com.base.gov.integracao.BaseDocs.Exception.ImportacaoError;
import com.base.gov.integracao.repositories.ImportacaoErrorLogRepository;


public interface PersistError {


    static void persist(String entityName, String errorMessage, String payload, ImportacaoErrorLogRepository errorLogRepository) {
        ImportacaoError errorLog = new ImportacaoError(entityName, false, errorMessage, payload);
        saveErrorLog(errorLog, errorLogRepository);
    }

    private static void saveErrorLog(ImportacaoError errorLog, ImportacaoErrorLogRepository errorLogRepository) {
        errorLogRepository.save(errorLog);
    }
}


