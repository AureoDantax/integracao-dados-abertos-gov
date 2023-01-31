package com.base.gov.integracao.controllers;

import com.base.gov.integracao.services.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Controller
@RequestMapping(value = "/csv")
public class DadosAbertosFileImporter {

    @Autowired
    private NaturezaService naturezaService;
    @Autowired
    private CnaesService cnaesService;
    @Autowired
    private QualificacaoService qualificacaoService;
    @Autowired
    private PaisService paisService;
    @Autowired
    private MunicipioService municipioService;
    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private EstabelecimentoService estabelecimentoService;
    @Autowired
    private MotivoService motivoService;

    @Autowired
    private SimplesService simplesService;

    @Autowired
    private SocioService sociosService;

    @PostMapping("/upload")
    public ResponseEntity<String> createObj(@RequestPart(required = false)
                                    MultipartFile fileNatureza,
                                            MultipartFile fileQualificacao,
                                            MultipartFile fileCnaes,
                                            MultipartFile filePais,
                                            MultipartFile fileMotivo,
                                            MultipartFile fileMunicipio,
                                            MultipartFile fileEmpresas,
                                            MultipartFile fileEstabelecimento,
                                            MultipartFile fileSimples,
                                            MultipartFile fileSocios) {


        log.info("Recebendo arquivos: ");
        try {
            try {
                log.info("Persistindo dados dos seguintes arquivos: " +
                        "\n -> " + fileNatureza.getOriginalFilename() +
                        "\n -> " + filePais.getOriginalFilename() +
                        "\n -> " + fileCnaes.getOriginalFilename() +
                        "\n -> " + fileQualificacao.getOriginalFilename() +
                        "\n -> " + fileMunicipio.getOriginalFilename() +
                        "\n -> " + fileEmpresas.getOriginalFilename()
                );
            } catch (Exception e) {
                log.info("Persistindo dados dos arquivos" );
            }


            processIfNeeded(fileNatureza, naturezaService);
            processIfNeeded(fileQualificacao, qualificacaoService);
            processIfNeeded(fileCnaes, cnaesService);
            processIfNeeded(fileMotivo, motivoService);
            processIfNeeded(filePais, paisService);
            processIfNeeded(fileMunicipio, municipioService);
            processIfNeeded(fileEmpresas, empresaService);
            processIfNeeded(fileEstabelecimento, estabelecimentoService);
            processIfNeeded(fileSimples, simplesService);
            processIfNeeded(fileSocios, sociosService);

            log.info("Dados persisitidos com sucesso");
            return ResponseEntity.ok("Arquivos salvos no banco");
        } catch (Exception e) {
            log.error("Falha ao carregar o arquivo" + " motivo: " + e.getMessage());
            return new ResponseEntity("error: Falha ao carregar arquivos\n" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    private void processIfNeeded(MultipartFile multipartFile, PersistDataFile service) throws IOException {
        if (ObjectUtils.isNotEmpty(multipartFile)) {
            InputStream inputStream = multipartFile.getInputStream();
            service.readAndPersistAttchment(inputStream);
        }
    }
}


