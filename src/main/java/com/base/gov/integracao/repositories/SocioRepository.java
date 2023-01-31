package com.base.gov.integracao.repositories;

import com.base.gov.integracao.BaseDocs.Socios;
import org.springframework.data.repository.CrudRepository;

public interface SocioRepository extends CrudRepository <Socios, Integer> {
}
