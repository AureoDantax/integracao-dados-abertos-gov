package com.base.gov.integracao.services;

import com.base.gov.integracao.BaseDocs.Motivo;
import com.base.gov.integracao.repositories.ImportacaoErrorLogRepository;
import com.base.gov.integracao.repositories.MotivoRepository;
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
import java.io.Reader;
import java.util.Arrays;

import static com.base.gov.integracao.BaseDocs.BaseEntity.CSV_ENCODING;
import static com.base.gov.integracao.BaseDocs.ColunaIndex.COLUNA_CODIGO;
import static com.base.gov.integracao.BaseDocs.ColunaIndex.COLUNA_DESC;

@Service
public class MotivoService implements PersistDataFile {

    @Autowired
    ImportacaoErrorLogRepository errorLogRepository;
    @Autowired
    MotivoRepository motivoRepository;

    @SneakyThrows
    @Override
    public void readAndPersistAttchment(InputStream inputStream) {

        Reader streamReader = new InputStreamReader(inputStream, CSV_ENCODING);
        CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
        CSVReader csvReader = new CSVReaderBuilder(streamReader).withCSVParser(parser).build();
        String[] conteudo;


        while ((conteudo = csvReader.readNext()) != null) {

            try {
                Motivo motivo = Motivo.builder()
                        .codigo(conteudo[COLUNA_CODIGO.getIndex()])
                        .descricao(conteudo[COLUNA_DESC.getIndex()])
                        .build();
                motivoRepository.save(motivo);
            } catch (Exception ex) {
                PersistError.persist(Motivo.class.getSimpleName(), ex.getMessage(), Arrays.toString(conteudo),errorLogRepository);
            }
        }
        csvReader.close();
        streamReader.close();
    }


}

