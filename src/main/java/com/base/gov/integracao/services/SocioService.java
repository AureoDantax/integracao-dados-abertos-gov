package com.base.gov.integracao.services;

import com.base.gov.integracao.BaseDocs.DadosComplementares.TipoSocio;
import com.base.gov.integracao.BaseDocs.Empresa;
import com.base.gov.integracao.BaseDocs.Pais;
import com.base.gov.integracao.BaseDocs.Qualificacao;
import com.base.gov.integracao.BaseDocs.Socios;
import com.base.gov.integracao.repositories.ImportacaoErrorLogRepository;
import com.base.gov.integracao.repositories.SocioRepository;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import static com.base.gov.integracao.BaseDocs.ColunaIndex.*;

@Service
public class SocioService implements PersistDataFile {

    public static final DateTimeFormatter format
            = DateTimeFormatter.ofPattern("yyyyMMdd");


    @Autowired
    SocioRepository socioRepository;

    @Autowired
    ImportacaoErrorLogRepository errorLogRepository;

    @SneakyThrows
    @Override
    public void readAndPersistAttchment(InputStream inputStream) {

        InputStreamReader streamReader = new InputStreamReader(inputStream);
        CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
        CSVReader csvReader = new CSVReaderBuilder(streamReader).withCSVParser(parser).build();

        String[] conteudo;

        while ((conteudo = csvReader.readNext()) != null) {

            try {
                Socios socios = Socios.builder()
                        .cnpjBase(Empresa.builder().cnpj(conteudo[COLUNA_CNPJ.getIndex()]).build())
                        .tipoSocio(TipoSocio.builder().id(conteudo[COLUNA_DESC.getIndex()]).build())
                        .nomeSocio(conteudo[COLUNA_NOME_SOCIO.getIndex()])
                        .cpfCnpjSocio(conteudo[COLUNA_CPF_SOCIO.getIndex()])
                        .qualificacaoSocio(Qualificacao.builder().codigo(conteudo[COLUNA_QUALIF_SOCIO.getIndex()]).build())
                        .inicioSociedade(LocalDate.parse(conteudo[COLUNA_DATA_SOCIEDADE.getIndex()], format))
                        .pais(Pais.builder().codigo(conteudo[COLUNA_CODIGO_PAIS.getIndex()]).build())
                        .cpfRepresentante(conteudo[COLUNA_CPF_REPRES.getIndex()])
                        .nomeRepresentante(conteudo[COLUNA_NOME_REPRES.getIndex()])
                        .qualificacaoSocio(Qualificacao.builder().codigo(conteudo[COLUNA_QUALIF_REPRES.getIndex()]).build())
                        .faixaEtaria(conteudo[COLUNA_FAIXA_ETARIA.getIndex()])

                        .build();
                socioRepository.save(socios);
            }catch (Exception ex) {
                PersistError.persist(Socios.class.getSimpleName(), ex.getMessage(), Arrays.toString(conteudo),errorLogRepository);
            }
        }
        streamReader.close();
        csvReader.close();

    }


}

