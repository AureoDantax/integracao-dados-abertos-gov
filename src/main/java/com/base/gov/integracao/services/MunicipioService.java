package com.base.gov.integracao.services;

import com.base.gov.integracao.BaseDocs.Municipio;
import com.base.gov.integracao.repositories.ImportacaoErrorLogRepository;
import com.base.gov.integracao.repositories.MunicipioRepository;
import com.base.gov.integracao.services.PersistError.PersistError;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

import static com.base.gov.integracao.BaseDocs.ColunaIndex.COLUNA_CODIGO;
import static com.base.gov.integracao.BaseDocs.ColunaIndex.COLUNA_DESC;

@Service
public class MunicipioService implements PersistDataFile {

    @Autowired
    ImportacaoErrorLogRepository errorLogRepository;
    @Autowired
    MunicipioRepository municipioRepository;

    @SneakyThrows
    @Override
    public void readAndPersistAttchment(InputStream inputStream) {

        InputStreamReader streamReader = new InputStreamReader(inputStream);
        CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
        CSVReader csvReader = new CSVReaderBuilder(streamReader).withCSVParser(parser).build();

        String[] conteudo;

        while ((conteudo = csvReader.readNext()) != null) {

            try {


                Municipio municipio = Municipio.builder()
                        .codigo(conteudo[COLUNA_CODIGO.getIndex()])
                        .descricao(conteudo[COLUNA_DESC.getIndex()]).build();
                municipioRepository.save(municipio);
            } catch (Exception ex) {
                PersistError.persist(Municipio.class.getSimpleName(), ex.getMessage(), Arrays.toString(conteudo),errorLogRepository);
            }

        }
    }

}

