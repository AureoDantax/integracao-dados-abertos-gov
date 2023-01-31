package com.base.gov.integracao.services;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.base.gov.integracao.BaseDocs.Qualificacao;
import com.base.gov.integracao.repositories.QualificacaoRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.InputStreamReader;

import static com.base.gov.integracao.BaseDocs.BaseEntity.CSV_ENCODING;
import static com.base.gov.integracao.BaseDocs.ColunaIndex.COLUNA_CODIGO;
import static com.base.gov.integracao.BaseDocs.ColunaIndex.COLUNA_DESC;

@Service
public class QualificacaoService implements PersistDataFile {


    @Autowired
    QualificacaoRepository qualificacaoRepository;

    @SneakyThrows
    @Override
    public void readAndPersistAttchment(InputStream inputStream) {

        InputStreamReader streamReader = new InputStreamReader(inputStream, CSV_ENCODING);
        CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
        CSVReader csvReader = new CSVReaderBuilder(streamReader).withCSVParser(parser).build();

        String[] conteudo;

        while ((conteudo = csvReader.readNext()) != null) {


            Qualificacao qualificacao = Qualificacao.builder()
                    .codigo(conteudo[COLUNA_CODIGO.getIndex()])
                    .descricao(conteudo[COLUNA_DESC.getIndex()]).build();
            qualificacaoRepository.save(qualificacao);


        }
        csvReader.close();
        streamReader.close();
    }
}

