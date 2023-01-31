package com.base.gov.integracao.services;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.base.gov.integracao.BaseDocs.*;
import com.base.gov.integracao.repositories.SimplesRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.base.gov.integracao.BaseDocs.ColunaIndex.*;

@Service
public class SimplesService implements PersistDataFile {

    public static final DateTimeFormatter format
            = DateTimeFormatter.ofPattern("yyyyMMdd");


    @Autowired
    SimplesRepository simplesRepository;

    @SneakyThrows
    @Override
    public void readAndPersistAttchment(InputStream inputStream) {

        InputStreamReader streamReader = new InputStreamReader(inputStream);
        CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
        CSVReader csvReader = new CSVReaderBuilder(streamReader).withCSVParser(parser).build();

        String[] conteudo;

        while ((conteudo = csvReader.readNext()) != null) {


            Simples simples = Simples.builder()
                    .cnpjBase(Empresa.builder().cnpj(conteudo[COLUNA_CNPJ.getIndex()]).build())
                    .opcaoSimples(conteudo[COLUNA_OPCAO_SIMPLES.getIndex()])
                    .dataOpcaoSimples(LocalDate.parse(conteudo[COLUNA_DATA_SITUAC_CADASTRAL.getIndex()], format))
                    .dataOpcaoSimples(LocalDate.parse(conteudo[COLUNA_EXCLUSAO_SIMPLES.getIndex()], format))
                    .opcaoMei(conteudo[COLUNA_OPCAO_MEI.getIndex()])
                    .dataOpcaoMei(LocalDate.parse(conteudo[COLUNA_DATA_MEI.getIndex()], format))
                    .dataExclusaoMei(LocalDate.parse(conteudo[COLUNA_EXCLUSAO_MEI.getIndex()], format))
                    .build();

            simplesRepository.save(simples);


        }
        csvReader.close();
        streamReader.close();
    }

}

