package com.base.gov.integracao.BaseDocs;

public enum ColunaIndex {

    COLUNA_CODIGO(0),
    COLUNA_DESC(1),
    COLUNA_CNPJ(0),
    COLUNA_RAZAO_SOCIAL(1),
    COLUNA_NATUREZA(2),
    COLUNA_QUALIFICACAO(3),
    COLUNA_CAPITAL(4),
    COLUNA_PORTE(5),
    COLUNA_ENTE_FEDERATIVO(6),
    COLUNA_CNPJ_ORDEM(1),
    COLUNA_CNPJ_DV(2),
    COLUNA_MATRIZ_FILIAL(3),
    COLUNA_NOME(4),
    COLUNA_SITUACAO(5),
    COLUNA_DATA_SITUAC_CADASTRAL(6),
    COLUNA_MOTIVO(7),
    COLUNA_CIDADE_EXTERIOR(8),
    COLUNA_PAIS(9),
    COLUNA_INICIO_ATIVIDADE(10),
    COLUNA_CNAE_PRINC(11),
    COLUNA_CNAE_SEC(12),
    COLUNA_TIPO_LOGRAD(13),
    COLUNA_LOGRAD(14),
    COLUNA_NUMERO(15),
    COLUNA_COMP(16),
    COLUNA_BAIRRO(17),
    COLUNA_CEP(18),
    COLUNA_UF(19),
    COLUNA_MUNICIP(20),
    COLUNA_DDD_PRINC(21),
    COLUNA_TELEF_PRINC(22),
    COLUNA_DDD_SEC(23),
    COLUNA_TELEF_SEC(24),
    COLUNA_EMAIL(25),
    COLUNA_SITUAC_ESPEC(26),
    COLUNA_DATA_SITUAC_ESPEC(27),
    COLUNA_OPCAO_SIMPLES(1),
    COLUNA_DATA_SIMPLES(2),
    COLUNA_EXCLUSAO_SIMPLES(3),
    COLUNA_OPCAO_MEI(4),
    COLUNA_DATA_MEI(5),
    COLUNA_EXCLUSAO_MEI(6),
    COLUNA_NOME_SOCIO(2),
    COLUNA_CPF_SOCIO(3),
    COLUNA_QUALIF_SOCIO(4),
    COLUNA_DATA_SOCIEDADE(5),
    COLUNA_CODIGO_PAIS(6),
    COLUNA_CPF_REPRES(7),
    COLUNA_NOME_REPRES(8),
    COLUNA_QUALIF_REPRES(9),
    COLUNA_FAIXA_ETARIA(10);




    private int index;
    ColunaIndex(int index) {
        this.index = index;

    }

    public int getIndex(){
        return index;
    }

}
