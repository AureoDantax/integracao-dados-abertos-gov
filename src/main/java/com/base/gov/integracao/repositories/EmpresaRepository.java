package com.base.gov.integracao.repositories;

import com.base.gov.integracao.BaseDocs.Empresa;
import org.springframework.data.repository.CrudRepository;

public interface EmpresaRepository extends CrudRepository <Empresa,String > {
}
