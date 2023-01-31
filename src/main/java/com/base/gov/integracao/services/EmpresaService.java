package com.base.gov.integracao.services;

import com.base.gov.integracao.BaseDocs.DadosComplementares.PorteEmpresa;
import com.base.gov.integracao.BaseDocs.Empresa;
import com.base.gov.integracao.BaseDocs.Natureza;
import com.base.gov.integracao.BaseDocs.Qualificacao;
import com.base.gov.integracao.repositories.EmpresaRepository;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.InputStreamReader;

import static com.base.gov.integracao.BaseDocs.BaseEntity.CSV_ENCODING;
import static com.base.gov.integracao.BaseDocs.ColunaIndex.*;


@Service
public class EmpresaService implements PersistDataFile {


    @Autowired
    EmpresaRepository empresaRepository;

    @SneakyThrows
    @Override
    public void readAndPersistAttchment(InputStream inputStream) {

        InputStreamReader streamReader = new InputStreamReader(inputStream, CSV_ENCODING);
        CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
        CSVReader csvReader = new CSVReaderBuilder(streamReader).withCSVParser(parser).build();

        String[] conteudo;

        while ((conteudo = csvReader.readNext()) != null) {


            Empresa empresa = Empresa.builder()
                    .cnpj(conteudo[COLUNA_CNPJ.getIndex()])
                    .razaoSocial(conteudo[COLUNA_RAZAO_SOCIAL.getIndex()])
                    .naturezaJuridica(Natureza.builder().codigo(conteudo[COLUNA_NATUREZA.getIndex()]).build())
                    .qualificacao(Qualificacao.builder().codigo(conteudo[COLUNA_QUALIFICACAO.getIndex()]).build())
                    .capital(conteudo[COLUNA_CAPITAL.getIndex()])
                    .porte(PorteEmpresa.builder().codigo(conteudo[COLUNA_PORTE.getIndex()]).build())
                    .enteFederativo(conteudo[COLUNA_ENTE_FEDERATIVO.getIndex()])
                    .build();
            empresaRepository.save(empresa);


        }
        csvReader.close();
        streamReader.close();
    }

}

