package com.base.gov.integracao.repositories;

import com.base.gov.integracao.BaseDocs.Exception.ImportacaoError;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ImportacaoErrorLogRepository extends CrudRepository<ImportacaoError, UUID> {

}
