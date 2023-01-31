package com.base.gov.integracao.services;

import com.base.gov.integracao.BaseDocs.*;
import com.base.gov.integracao.BaseDocs.DadosComplementares.MatrizFilial;
import com.base.gov.integracao.BaseDocs.DadosComplementares.SituacaoCadastral;
import com.base.gov.integracao.repositories.EstabelecimentoRepository;
import com.base.gov.integracao.repositories.ImportacaoErrorLogRepository;
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
public class EstabelecimentoService implements PersistDataFile {

    public static final DateTimeFormatter format
            = DateTimeFormatter.ofPattern("yyyyMMdd");

    @Autowired
    ImportacaoErrorLogRepository errorLogRepository;
    @Autowired
    EstabelecimentoRepository estabelecimentoRepository;

    @SneakyThrows
    @Override
    public void readAndPersistAttchment(InputStream inputStream) {

        InputStreamReader streamReader = new InputStreamReader(inputStream);
        CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
        CSVReader csvReader = new CSVReaderBuilder(streamReader).withCSVParser(parser).build();

        String[] conteudo;

        while ((conteudo = csvReader.readNext()) != null) {

            try {
                Estabelecimento estabelecimento = Estabelecimento.builder()
                        .cnpjBase(Empresa.builder().cnpj(conteudo[COLUNA_CNPJ.getIndex()]).build())
                        .cnpjOrdem(conteudo[COLUNA_CNPJ_ORDEM.getIndex()])
                        .cnpjDv(conteudo[COLUNA_CNPJ_DV.getIndex()])
                        .matrizFilial(MatrizFilial.builder().codigo(conteudo[COLUNA_MATRIZ_FILIAL.getIndex()]).build())
                        .nomeFantasia(conteudo[COLUNA_NOME.getIndex()])
                        .situacCadastral(SituacaoCadastral.builder().codigo(conteudo[COLUNA_SITUACAO.getIndex()]).build())
                        .dataSituacCadastral(LocalDate.parse(conteudo[COLUNA_DATA_SITUAC_CADASTRAL.getIndex()], format))
                        .motivoSituacCadastral(Motivo.builder().codigo(conteudo[COLUNA_MOTIVO.getIndex()]).build())
                        .nomeCidadeExt(conteudo[COLUNA_CIDADE_EXTERIOR.getIndex()])
                        .codigoPais(Pais.builder().codigo(conteudo[COLUNA_PAIS.getIndex()]).build())
                        .dataInicioAtividade(LocalDate.parse(conteudo[COLUNA_INICIO_ATIVIDADE.getIndex()], format))
                        .cnaePrincipal(Cnaes.builder().codigo(conteudo[COLUNA_CNAE_PRINC.getIndex()]).build())
                        .cnaeSecundario(Cnaes.builder().codigo(conteudo[COLUNA_CNAE_SEC.getIndex()]).build())
                        .tipoLogradouro(conteudo[COLUNA_TIPO_LOGRAD.getIndex()])
                        .logradouro(conteudo[COLUNA_LOGRAD.getIndex()])
                        .numero(conteudo[COLUNA_NUMERO.getIndex()])
                        .complemento(conteudo[COLUNA_COMP.getIndex()])
                        .bairro(conteudo[COLUNA_BAIRRO.getIndex()])
                        .cep(conteudo[COLUNA_CEP.getIndex()])
                        .uf(conteudo[COLUNA_UF.getIndex()].toUpperCase())
                        .municipio(Municipio.builder().codigo(conteudo[COLUNA_MUNICIP.getIndex()]).build())
                        .dddPrinc(conteudo[COLUNA_DDD_PRINC.getIndex()])
                        .telefPrinc(conteudo[COLUNA_TELEF_PRINC.getIndex()])
                        .dddSec(conteudo[COLUNA_DDD_SEC.getIndex()])
                        .telefSec(conteudo[COLUNA_TELEF_SEC.getIndex()])
                        .email(conteudo[COLUNA_EMAIL.getIndex()])
                        .situacEspecial(conteudo[COLUNA_SITUAC_ESPEC.getIndex()])
                        .dataSituacEspecial(conteudo[COLUNA_DATA_SITUAC_ESPEC.getIndex()])
                        .build();
                estabelecimentoRepository.save(estabelecimento);
            } catch (Exception ex) {
                PersistError.persist(Cnaes.class.getSimpleName(), ex.getMessage(), Arrays.toString(conteudo),errorLogRepository);
            }

        }
        csvReader.close();
        streamReader.close();
    }

}

